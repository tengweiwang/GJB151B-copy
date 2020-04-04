package consts;

public enum PathStoreEnum {
    WINDOWS_SQLDATA_AND_IMG_EXPORT_PATH("C://Users//GJB_user//Desktop//导出//"),
//        WINDOWS_SQLDATA_AND_IMG_EXPORT_PATH("/Users/chenzhehao/Desktop/导出/"),
//
        WINDOWS_CHANGEDIMG_SOURTHPATH("D://GJB151B//gjb151b//changed_img//"),
//    WINDOWS_CHANGEDIMG_SOURTHPATH("/Users/chenzhehao/GJB151B/gjb151b/changed_img/"),

    WINDOWS_STANDADIMG_PATH("D://GJB151B//gjb151b//standard_img//"),
//    WINDOWS_STANDADIMG_PATH("/Users/wangtengteng/Desktop/GJB151B/standard_img/"),
//    WINDOWS_CONFIG_PATH("/Users/chenzhehao/GJB151B/config.properties");
    WINDOWS_CONFIG_PATH("D://GJB151B//config.properties");





    private String value;

    PathStoreEnum(String value) {
        this.value = value;
    }

    public String  getValue() {
        return this.value;
    }
}
