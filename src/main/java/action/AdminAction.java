package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.UserInfoEntity;
import java.sql.Timestamp;
import java.util.List;
import service.AdminService;
import utils.BaseResponse;
import utils.MD5;

/**
 * Created by Bailejin on 2018/8/23 0023 11:40
 */
public class AdminAction extends ActionSupport{

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
    private BaseResponse findAllResponse = new BaseResponse<List<UserInfoEntity>>();
    private BaseResponse addUserResponse = new BaseResponse<String>();
    private BaseResponse deleteUserResponse = new BaseResponse<String>();
    private BaseResponse resetPasswordResponse = new BaseResponse<String>();
    private BaseResponse updateUserResponse = new BaseResponse<String>();

    public String findAll(){
        findAllResponse = AdminService.findAll();
        return "success";
    }

    public String addUser(){
        UserInfoEntity s = new UserInfoEntity();
        s.setUserName(this.userName);
        s.setUserPassword(MD5.md5(this.userPassword));
        s.setUserLevel(this.userLevel);
        s.setUserRemark(this.userRemark);
        s.setUserNew("[]");
        s.setUserProofread("[]");
        s.setUserAudit("[]");
        s.setUserAuthorize("[]");
        addUserResponse = AdminService.addUser(s);
        return "success";
    }

    public String deleteUser(){
        deleteUserResponse=AdminService.deleteUser(this.userId);
        return "success";
    }

    public String updateUser(){
        updateUserResponse=AdminService.updateUser(this.userId,this.userName,this.userLevel,this.userRemark);
        return "success";
    }

    public String resetPassword(){
        resetPasswordResponse=AdminService.resetPassword(this.userId, this.userPassword);
        return "success";
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public BaseResponse<List> getFindAllResponse() {
        return findAllResponse;
    }

    public BaseResponse<String> getAddUserResponse() {
        return addUserResponse;
    }

    public BaseResponse<String> getDeleteUserResponse() {
        return deleteUserResponse;
    }

    public BaseResponse<String> getResetPasswordResponse() {
        return resetPasswordResponse;
    }

    public BaseResponse<String> getUpdateUserResponse() {
        return updateUserResponse;
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


    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }


    public Timestamp getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Timestamp userCreateTime) {
        this.userCreateTime = userCreateTime;
    }


    public Timestamp getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Timestamp userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }


}
