package service;

import dao.ManageDao;
import entity.UserLogin;
import org.apache.commons.lang.StringUtils;
import utils.BaseResponse;
import utils.MD5;

/**
 * Created by ddgdd on 2018/8/28 0028 15:05
 */
public class ManageService {
    private static ManageDao manageDao = ManageDao.getInstance();

    public static BaseResponse changePassword(String userName, String userPassword, String newPassword) {
        BaseResponse changePasswordResponse = new BaseResponse();
        UserLogin user = manageDao.checkUser(userName);
        if(user == null) {
            changePasswordResponse.setMessage("不存在此用户");
        }else {
            String passwrodMd5 = MD5.md5(userPassword);
            if(!StringUtils.equals(passwrodMd5, user.getUserPassword())){
                changePasswordResponse.setMessage("用户原始密码输入有误");
            }else {
                String newPasswordMd5 = MD5.md5(newPassword);
                if(manageDao.changePassword(userName, newPasswordMd5)) {
                    changePasswordResponse.setStatus("success");
                }else {
                    changePasswordResponse.setMessage("用户密码更新失败，请稍后再试");
                }
            }
        }

        return changePasswordResponse;
    }

}
