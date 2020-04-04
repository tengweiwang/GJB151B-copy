package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ddgdd on 2018/10/15 0015 9:36
 */
@Entity
@Table(name = "user_info", schema = "gjb151b", catalog = "")
public class UserInfoEntity {
    private int userId;
    private String userName;
    private String userPassword;
    private int userLevel;
    private String userNew;
    private String userProofread;
    private String userAudit;
    private String userAuthorize;
    private String userRemark;
    private Timestamp userCreateTime;
    private Timestamp userUpdateTime;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 25)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_password", nullable = false, length = 50)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_level", nullable = false)
    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    @Basic
    @Column(name = "user_new", nullable = true, length = -1)
    public String getUserNew() {
        return userNew;
    }

    public void setUserNew(String userNew) {
        this.userNew = userNew;
    }

    @Basic
    @Column(name = "user_proofread", nullable = true, length = -1)
    public String getUserProofread() {
        return userProofread;
    }

    public void setUserProofread(String userProofread) {
        this.userProofread = userProofread;
    }

    @Basic
    @Column(name = "user_audit", nullable = true, length = -1)
    public String getUserAudit() {
        return userAudit;
    }

    public void setUserAudit(String userAudit) {
        this.userAudit = userAudit;
    }

    @Basic
    @Column(name = "user_authorize", nullable = true, length = -1)
    public String getUserAuthorize() {
        return userAuthorize;
    }

    public void setUserAuthorize(String userAuthorize) {
        this.userAuthorize = userAuthorize;
    }

    @Basic
    @Column(name = "user_remark", nullable = true, length = -1)
    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    @Basic
    @Column(name = "user_create_time", nullable = false)
    public Timestamp getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Timestamp userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    @Basic
    @Column(name = "user_update_time", nullable = false)
    public Timestamp getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Timestamp userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfoEntity that = (UserInfoEntity) o;

        if (userId != that.userId) return false;
        if (userLevel != that.userLevel) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;
        if (userNew != null ? !userNew.equals(that.userNew) : that.userNew != null) return false;
        if (userProofread != null ? !userProofread.equals(that.userProofread) : that.userProofread != null)
            return false;
        if (userAudit != null ? !userAudit.equals(that.userAudit) : that.userAudit != null) return false;
        if (userAuthorize != null ? !userAuthorize.equals(that.userAuthorize) : that.userAuthorize != null)
            return false;
        if (userRemark != null ? !userRemark.equals(that.userRemark) : that.userRemark != null) return false;
        if (userCreateTime != null ? !userCreateTime.equals(that.userCreateTime) : that.userCreateTime != null)
            return false;
        if (userUpdateTime != null ? !userUpdateTime.equals(that.userUpdateTime) : that.userUpdateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + userLevel;
        result = 31 * result + (userNew != null ? userNew.hashCode() : 0);
        result = 31 * result + (userProofread != null ? userProofread.hashCode() : 0);
        result = 31 * result + (userAudit != null ? userAudit.hashCode() : 0);
        result = 31 * result + (userAuthorize != null ? userAuthorize.hashCode() : 0);
        result = 31 * result + (userRemark != null ? userRemark.hashCode() : 0);
        result = 31 * result + (userCreateTime != null ? userCreateTime.hashCode() : 0);
        result = 31 * result + (userUpdateTime != null ? userUpdateTime.hashCode() : 0);
        return result;
    }
}
