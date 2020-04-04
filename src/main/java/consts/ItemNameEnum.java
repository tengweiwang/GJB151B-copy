package consts;

public enum ItemNameEnum {
    CE101("CE101", "25Hz~10kHz 电源线传到发射"),
    CE102("CE102", "10kHz~10MHz 电源线传导发射"),
    CE106("CE106", "10kHz~40GHz 天线端口传导发射"),
    CE107("CE107", "电源线尖峰信号(时域)传导发射"),
    CS101("CS101", "25Hz~150kHz 电源线传导敏感度"),
    CS102("CS102", "地线传导敏感度"),
    CS103("CS103", "天线端口互调传导敏感度"),
    CS104("CS104", "25Hz~20GHz 天线端口无用信号抑制传导敏感度"),
    CS105("CS105", "25Hz~20GHz 天线端口交调敏感度"),
    CS106("CS106", "电源线尖峰信号传导敏感度"),
    CS109("CS109", "50Hz~100kHz 壳体电流传导敏感度"),
    CS112("CS112", "静电放电敏感度"),
    CS114("CS114", "4kHz~400MHz 电缆束注入传导敏感度"),
    CS115("CS115", "电缆束注入脉冲激励传导敏感度"),
    CS116("CS116", "10kHz~100MHz 电缆和电源线阻尼正弦瞬态传导敏感度"),
    RE101("RE101", "25Hz~100kHz 磁场辐射发射"),
    RE102("RE102", "10kHz~!18GHz 电厂辐射发射"),
    RE103("RE103", "10kHz~40GHz 天线谐波和乱真输出辐射发射"),
    RS101("RS101", "20Hz~100kHz 磁场辐射敏感度"),
    RS103("RS103", "25Hz~100kHz 电厂辐射敏感度"),
    RS105("RS105", "瞬态电磁场辐射敏感度");

    private String code;
    private String msg;

    ItemNameEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getItemNameByCode(String code) {
        for (ItemNameEnum itemNameEnum : ItemNameEnum.values()) {
            if (itemNameEnum.getCode().equals(code)) {
                return itemNameEnum.getCode() + "  " + itemNameEnum.getMsg();
            }
        }
        return  null;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
