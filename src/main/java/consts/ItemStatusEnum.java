package consts;

import org.apache.commons.lang.enums.Enum;

/**
 * <p>
 *
 * </p>
 *
 * @Author: fcupup 875894948@qq.com
 * @Data: Created on 4:13 PM 2019/6/11
 * @Modified By:
 */
public enum ItemStatusEnum {
    STATUS0(0, "待编制"),
    STATUS1(1, "待校对"),
    STATUS2(2, "待审核"),
    STATUS3(3, "待批准"),
    STATUS4(4, "待修改"),
    STATUS5(5, "已完成");

    private Integer order;
    private String status;

    ItemStatusEnum(Integer order, String status) {
        this.order = order;
        this.status = status;
    }

    public static String getStatusByOrder(Integer order) {
        for (ItemStatusEnum statusEnum : ItemStatusEnum.values()) {
            if (statusEnum.order.equals(order)) {
                return statusEnum.getStatus();
            }
        }
        return null;
    }

    public Integer getOrder() {
        return this.order;
    }

    public String getStatus() {
        return this.status;
    }

}
