package hospitalRegister;

import java.sql.*;

public class DBConnector {
    private static DBConnector instance = null;
    private Connection connection;
    private Connection transactionConnection;
    private Statement statement;
    private Statement transactionStatement;

    private DBConnector() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    static public DBConnector getInstance() {
        try {
            if (instance == null)
                instance = new DBConnector();
        } catch (ClassNotFoundException e) {
            System.err.print("cannot load sql driver.");
            System.exit(1);
        }
        return instance;
    }

    public void connectDataBase(
            String hostName,
            Integer port,
            String dbName,
            String userName,
            String password) throws SQLException {
        String url = "jdbc:mysql://" + hostName +
                        ":" + port +
                        "/" + dbName +
                        "?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
        connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement();
        transactionConnection =  DriverManager.getConnection(url, userName, password);
        transactionConnection.setAutoCommit(false);
        transactionStatement = transactionConnection.createStatement();
    }

    public void disconnectDataBase() throws SQLException {
        connection.close();
    }

    /**  获得全表内容
     * @param tableName  表名
     * @return ResultSet
     */
    public ResultSet getWholeTable(String tableName){
        try {
            return statement.executeQuery("select * from " + tableName);
        } catch (SQLException e) {
            return null;
        }
    }


    /** 病人信息
     * @param number
     * @return
     */
    public ResultSet getPatientInfo(String number) {
        try {
            return statement.executeQuery(
                    "select * from " + Config.TablePatient +
                            " where " + Config.ColumnPatientNumber + "=" + number);
        } catch (SQLException e) {
            return null;
        }
    }

    /** 医生信息
     * @param number
     * @return
     */
    public ResultSet getDoctorInfo(String number) {
        try {
            return statement.executeQuery(
                    "select * from " + Config.TableDoctor +
                            " where " + Config.ColumnDoctorNumber + "=" + number);
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * 尝试挂号
     * try adding register info to the database
     * @param registerCategoryNumber register category number
     * @param doctorNumber doctor number
     * @param patientNumber patient number
     * @param registerFee register fee
     * @return register number if registeration is successful, null otherwise.
     * @throws RegisterException if register failed
     */
    public int tryRegister(
            String registerCategoryNumber,
            String doctorNumber,
            String patientNumber,
            Double registerFee,
            boolean deductFromBalance,
            Double addToBalance) throws RegisterException {
        try{
            // decide the register id
            ResultSet result = transactionStatement.executeQuery(
                    "select * from " + Config.TableRegister +
                            " order by " + Config.ColumnRegisterNumber +
                            " desc limit 1"
            );
            int regNumber, currentCount;
            if (!result.next())
                regNumber = 0;
            else
                regNumber = Integer.parseInt(result.getString(Config.ColumnRegisterNumber)) + 1;

            result = transactionStatement.executeQuery(
                    "select * from " +  Config.TableRegister +
                            " where " + Config.ColumnRegisterCategoryNumber +
                            "=" + registerCategoryNumber +
                            " order by " + Config.ColumnCategoryRegisterNumber +
                            " desc limit 1"
            );
            if(!result.next())
                currentCount = 0;
             else
                currentCount = result.getInt(Config.ColumnRegisterCurrentRegisterCount);

            // decide patient id
            result = transactionStatement.executeQuery(
                    "select * from " + Config.TablePatient +
                            " where " + Config.ColumnPatientNumber +
                            "=" + patientNumber
            );
            if(!result.next())
                throw new RegisterException("patient does not exist", RegisterException.ErrorCode.patientNotExist);

            double balance = result.getDouble(Config.ColumnPatientBalance);

            // decide if exceeded the max register count
            result = transactionStatement.executeQuery(
                    "select " + Config.ColumnCategoryRegisterMaxRegisterNumber +
                            " from " + Config.TableCategoryRegister +
                            " where " + Config.ColumnCategoryRegisterNumber +
                            "=" + registerCategoryNumber
            );
            int maxRegCount;
            if(!result.next())
                throw new RegisterException("illegal table entry",
                        RegisterException.ErrorCode.registerCategoryNotFound);
            maxRegCount = result.getInt(Config.ColumnCategoryRegisterMaxRegisterNumber);

            if(currentCount > maxRegCount) {
                throw new RegisterException("max register number reached",
                        RegisterException.ErrorCode.registerNumberExceeded);
            }

            // try insert
            transactionStatement.executeUpdate(
                    String.format(
                            "insert into %s values (\"%06d\",\"%s\",\"%s\",\"%s\",%d,false,%s, current_timestamp)",
                            Config.TableRegister,
                            regNumber,
                            registerCategoryNumber,
                            doctorNumber,
                            patientNumber,
                            currentCount + 1,
                            registerFee
                    )
            );

            // deduct from balance
            if(deductFromBalance){
                transactionStatement.executeUpdate(
                        String.format("update %s set %s=%.2f where %s=%s",
                                Config.TablePatient,
                                Config.ColumnPatientBalance,
                                (balance -= registerFee),
                                Config.ColumnPatientNumber,
                                patientNumber)
                );
            }

            if(addToBalance != 0) {
                transactionStatement.executeUpdate(
                        String.format("update %s set %s=%.2f where %s=%s",
                                Config.TablePatient,
                                Config.ColumnPatientBalance,
                                (balance += addToBalance),
                                Config.ColumnPatientNumber,
                                patientNumber)
                );
            }

            // all successfulHH
            transactionConnection.commit();
            return regNumber;
        } catch (SQLException e) {
            try {
                transactionConnection.rollback();
            } catch (SQLException ee) { }
            throw new RegisterException("sql exception occurred", RegisterException.ErrorCode.sqlException);
        }
    }

    public ResultSet getRegisterForDoctor(String docNumber, String startTime, String endTime) {
        try {
                    String sql = "select reg." + Config.ColumnRegisterNumber +
                            ",pat." + Config.ColumnPatientName +
                            ",reg." + Config.ColumnRegisterDateTime +
                            ",cat." + Config.ColumnCategoryRegisterIsSpecialist + (
                            " from (select " + Config.ColumnRegisterNumber +
                                    "," + Config.ColumnRegisterPatientNumber +
                                    "," + Config.ColumnRegisterDateTime +
                                    "," + Config.ColumnRegisterCategoryNumber +
                                    " from " + Config.TableRegister +
                                    " where " + Config.ColumnRegisterDoctorNumber +
                                    "=" + docNumber +
                                    " and " + Config.ColumnRegisterDateTime +
                                    ">=\"" + startTime +
                                    "\" and " + Config.ColumnRegisterDateTime +
                                    "<=\"" + endTime +
                                    "\") as reg" ) + (
                            " inner join (select " + Config.ColumnPatientNumber +
                                    "," + Config.ColumnPatientName +
                                    " from " + Config.TablePatient +
                                    ") as pat" ) +
                            " on reg." + Config.ColumnRegisterPatientNumber +
                            "=pat." + Config.ColumnPatientNumber + (
                            " inner join (select " + Config.ColumnCategoryRegisterNumber +
                                    "," + Config.ColumnCategoryRegisterIsSpecialist +
                                    " from " + Config.TableCategoryRegister +
                                    ") as cat" ) +
                            " on reg." + Config.ColumnRegisterCategoryNumber +
                            "=cat." + Config.ColumnCategoryRegisterNumber;
            //System.out.println(sql);
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getIncomeInfo(String startTime, String endTime) {
        try {
                    String sql = "select dep." + Config.ColumnDepartmentName +
                            " as depname,reg." + Config.ColumnRegisterDoctorNumber +
                            ",doc." + Config.ColumnDoctorName +
                            " as docname,cat." + Config.ColumnCategoryRegisterIsSpecialist +
                            ",reg." + Config.ColumnRegisterCurrentRegisterCount +
                            ",SUM(reg." + Config.ColumnRegisterFee +
                            ") as sum from" + (
                            " (select * from " + Config.TableRegister +
                                    " where " + Config.ColumnRegisterDateTime +
                                    ">=\"" + startTime +
                                    "\" and " + Config.ColumnRegisterDateTime +
                                    "<=\"" + endTime +
                                    "\") as reg") +
                            " inner join" + (
                            " (select " + Config.ColumnDoctorNumber +
                                    "," + Config.ColumnDoctorName +
                                    "," + Config.ColumnDoctorDepartmentNumber +
                                    " from " + Config.TableDoctor +
                                    ") as doc") +
                            " on reg." + Config.ColumnRegisterDoctorNumber +
                            "=doc." + Config.ColumnDoctorNumber +
                            " inner join" + (
                            " (select " + Config.ColumnDepartmentNumber +
                                    "," + Config.ColumnDepartmentName +
                                    " from " + Config.TableDepartment +
                                    ") as dep") +
                            " on doc." + Config.ColumnDoctorDepartmentNumber +
                            "=dep." + Config.ColumnDepartmentNumber +
                            " inner join" + (
                            " (select " + Config.ColumnCategoryRegisterNumber +
                                    "," + Config.ColumnCategoryRegisterIsSpecialist +
                                    " from " + Config.TableCategoryRegister +
                                    ") as cat" ) +
                            " on reg." + Config.ColumnRegisterCategoryNumber +
                            "=cat." + Config.ColumnCategoryRegisterNumber +
                            " group by reg." + Config.ColumnRegisterDoctorNumber +
                            ",cat." + Config.ColumnCategoryRegisterIsSpecialist;
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updatePatientLoginTime(String patientId, String time){
        try {
            statement.executeUpdate(
                    "update " + Config.TablePatient +
                            " set " + Config.ColumnPatientLastLogin +
                            "=\"" + time +
                            "\" where " + Config.ColumnPatientNumber +
                            "=" + patientId
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    public void updateDoctorLoginTime(String doctorId, String time){
         try {
            statement.executeUpdate(
                    "update " + Config.TableDoctor +
                            " set " + Config.ColumnDoctorLastLogin +
                            "=\"" + time +
                            "\" where " + Config.ColumnRegisterDoctorNumber +
                            "=" + doctorId
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
}

class RegisterException extends Exception {
    public enum ErrorCode {
        noError,
        registerCategoryNotFound,
        registerNumberExceeded,
        patientNotExist,
        sqlException,
        retryTimeExceeded,
    }
    ErrorCode error;
    RegisterException(String reason, ErrorCode err){
        super(reason);
        error = err;
    }
}
