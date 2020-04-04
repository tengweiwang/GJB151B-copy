package service;

import com.opensymphony.xwork2.ActionContext;
import dao.LoginDao;
import entity.UserLogin;
import org.apache.commons.lang.StringUtils;
import utils.BaseResponse;
import utils.MD5;

import java.util.Map;

/**
 * Created by ddgdd on 2018/8/23 0023 11:43
 */
public class LoginService {

    private static LoginDao loginDao = LoginDao.getInstance();

    public static BaseResponse<UserLogin> checkUser(String userName, String userPassword) {
        BaseResponse<UserLogin> checkUserResponse = new BaseResponse<UserLogin>();
        UserLogin userLogin = loginDao.checkUser(userName);
        if(userLogin == null) {
            checkUserResponse.setMessage("不存在此用户信息");
        }else {
            String passwordMd5 = MD5.md5(userPassword);
            if(StringUtils.equals(passwordMd5, userLogin.getUserPassword())) {
                checkUserResponse.setStatus("success");
                checkUserResponse.setData(userLogin);
                checkUserResponse.setMessage("用户登录成功");
                Map session = (Map) ActionContext.getContext().getSession();
                session.put("userLogin", userLogin);
            } else {
                checkUserResponse.setMessage("用户密码错误");
            }
        }

        return checkUserResponse;
    }


}
