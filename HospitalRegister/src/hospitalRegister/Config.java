package hospitalRegister;

public class Config {
    public static String TableDepartment = "department";
    public static String TableDoctor = "doctor";
    public static String TableCategoryRegister = "register_category";
    public static String TablePatient = "patient";
    public static String TableRegister = "register";

    public static String ColumnDepartmentNumber = "depid";
    public static String ColumnDepartmentName = "name";
    public static String ColumnDepartmentPronounce = "py";

    public static String ColumnDoctorNumber = "docid";
    public static String ColumnDoctorDepartmentNumber = "depid";
    public static String ColumnDoctorName = "name";
    public static String ColumnDoctorPronounce = "py";
    public static String ColumnDoctorPassword = "password";
    public static String ColumnDoctorIsSpecialist = "speciallist";
    public static String ColumnDoctorLastLogin = "last_login_datetime";

    public static String ColumnCategoryRegisterNumber = "catid";
    public static String ColumnCategoryRegisterName = "name";
    public static String ColumnCategoryRegisterPronounce = "py";
    public static String ColumnCategoryRegisterDepartment = "depid";
    public static String ColumnCategoryRegisterIsSpecialist = "speciallist";
    public static String ColumnCategoryRegisterMaxRegisterNumber = "max_reg_number";
    public static String ColumnCategoryRegisterFee = "reg_fee";

    public static String ColumnPatientNumber = "pid";
    public static String ColumnPatientName = "name";
    public static String ColumnPatientPassword = "password";
    public static String ColumnPatientBalance = "balance";
    public static String ColumnPatientLastLogin = "last_login_datetime";

    public static String ColumnRegisterNumber = "reg_id";
    public static String ColumnRegisterCategoryNumber = "catid";
    public static String ColumnRegisterDoctorNumber = "docid";
    public static String ColumnRegisterPatientNumber = "pid";
    public static String ColumnRegisterCurrentRegisterCount = "current_reg_count";
    public static String ColumnRegisterFee = "reg_fee";
    public static String ColumnRegisterDateTime = "reg_datetime";

}
