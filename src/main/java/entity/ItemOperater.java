package entity;

public class ItemOperater {
    private String itemId;
    private String itemName;
    private String userNew;
    private String userProofread;
    private String userAudit;
    private String userAuthorize;
    private Integer devStatus;

    private String status;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUserNew() {
        return userNew;
    }

    public void setUserNew(String userNew) {
        this.userNew = userNew;
    }

    public String getUserProofread() {
        return userProofread;
    }

    public void setUserProofread(String userProofread) {
        this.userProofread = userProofread;
    }

    public String getUserAudit() {
        return userAudit;
    }

    public void setUserAudit(String userAudit) {
        this.userAudit = userAudit;
    }

    public String getUserAuthorize() {
        return userAuthorize;
    }

    public void setUserAuthorize(String userAuthorize) {
        this.userAuthorize = userAuthorize;
    }

    public Integer getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(Integer devStatus) {
        this.devStatus = devStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
