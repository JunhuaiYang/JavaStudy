package hospitalRegister;

public class Config {
    public static String TableDepartment = "department";
    public static String TableDoctor = "doctor";
    public static String TableCategoryRegister = "register_category";
    public static String TablePatient = "patient";
    public static String TableRegister = "register";

    public static String NameTableColumnDepartmentNumber = "depid";
    public static String NameTableColumnDepartmentName = "name";
    public static String NameTableColumnDepartmentPronounce = "py";

    public static String NameTableColumnDoctorNumber = "docid";
    public static String NameTableColumnDoctorDepartmentNumber = "depid";
    public static String NameTableColumnDoctorName = "name";
    public static String NameTableColumnDoctorPronounce = "py";
    public static String NameTableColumnDoctorPassword = "password";
    public static String NameTableColumnDoctorIsSpecialist = "speciallist";
    public static String NameTableColumnDoctorLastLogin = "last_login_datetime";

    public static String NameTableColumnCategoryRegisterNumber = "catid";
    public static String NameTableColumnCategoryRegisterName = "name";
    public static String NameTableColumnCategoryRegisterPronounce = "py";
    public static String NameTableColumnCategoryRegisterDepartment = "depid";
    public static String NameTableColumnCategoryRegisterIsSpecialist = "speciallist";
    public static String NameTableColumnCategoryRegisterMaxRegisterNumber = "max_reg_number";
    public static String NameTableColumnCategoryRegisterFee = "reg_fee";

    public static String NameTableColumnPatientNumber = "pid";
    public static String NameTableColumnPatientName = "name";
    public static String NameTableColumnPatientPassword = "password";
    public static String NameTableColumnPatientBalance = "balance";
    public static String NameTableColumnPatientLastLogin = "last_login_datetime";

    public static String NameTableColumnRegisterNumber = "reg_id";
    public static String NameTableColumnRegisterCategoryNumber = "catid";
    public static String NameTableColumnRegisterDoctorNumber = "docid";
    public static String NameTableColumnRegisterPatientNumber = "pid";
    public static String NameTableColumnRegisterCurrentRegisterCount = "current_reg_count";
    public static String NameTableColumnRegisterUnregister = "unreg";
    public static String NameTableColumnRegisterFee = "reg_fee";
    public static String NameTableColumnRegisterDateTime = "reg_datetime";

}
