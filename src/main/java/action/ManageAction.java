package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang.StringUtils;
import service.LoginService;
import service.ManageService;
import utils.BaseResponse;

/**
 * Created by ddgdd on 2018/8/28 0028 15:22
 */
public class ManageAction extends ActionSupport {

    private String userName;
    private String userPassword;
    private String newPassword;

    private BaseResponse changePasswordResponse = new BaseResponse();

    public String changePassword() {
        if(StringUtils.isNotBlank(userName)) {
            if(StringUtils.isNotBlank(userPassword)) {
                if(StringUtils.isNotBlank(newPassword)) {
                    changePasswordResponse = ManageService.changePassword(userName, userPassword, newPassword);
                }else {
                    changePasswordResponse.setMessage("新密码不能为空");
                }
            }else {
                changePasswordResponse.setMessage("密码不能为空");
            }
        }else {
            changePasswordResponse.setMessage("用户名不能为空");
        }
        System.out.println(changePasswordResponse.getStatus());
        System.out.println(changePasswordResponse.getMessage());

        return "success";
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public BaseResponse getChangePasswordResponse() {
        return changePasswordResponse;
    }

    public void setChangePasswordResponse(BaseResponse changePasswordResponse) {
        this.changePasswordResponse = changePasswordResponse;
    }
}
