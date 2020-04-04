package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.UserLogin;
import org.apache.commons.lang.StringUtils;
import service.LoginService;
import utils.BaseResponse;

import java.util.Map;

/**
 * Created by ddgdd on 2018/8/23 0023 11:40
 */
public class LoginAction extends ActionSupport{

    private String userName;
    private String userPassword;

    private BaseResponse checkUserResponse = new BaseResponse<Integer>();

    public String checkUser() {

        if(StringUtils.isNotBlank(userName)) {
            if(StringUtils.isNotBlank(userPassword)) {
                checkUserResponse = LoginService.checkUser(userName, userPassword);
            }else {
                checkUserResponse.setMessage("密码不能为空");
            }
        }else {
            checkUserResponse.setMessage("用户名不能为空");
        }

        return "success";
    }

    public String logout() {
        ActionContext.getContext().getSession().clear();

        return "success";
    }


//    @Override
//    public UserLogin getModel() {
////        if(userInfoEntity == null) {
////            userInfoEntity = new UserInfoEntity();
////        }
//        return userInfoEntity;
////    }

//    public UserInfoEntity getUserInfoEntity() {
//        return userInfoEntity;
//    }
//
//    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
//        this.userInfoEntity = userInfoEntity;
//    }


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

    public BaseResponse<Integer> getCheckUserResponse() {
        return checkUserResponse;
    }

    public void setCheckUserResponse(BaseResponse<Integer> checkUserResponse) {
        this.checkUserResponse = checkUserResponse;
    }
}
