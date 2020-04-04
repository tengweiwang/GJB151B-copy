package service;

import dao.AdminDao;
import dao.LoginDao;
import entity.UserInfoEntity;
import entity.UserLogin;
import utils.BaseResponse;
import java.util.List;


public class AdminService {
    private static AdminDao adminDao = AdminDao.getInstance();
    private static LoginDao loginDao = LoginDao.getInstance();

    public static BaseResponse<List> findAll() {
        BaseResponse<List> findAllResponse = new BaseResponse<>();
        List<UserInfoEntity> conts = adminDao.findAll();
        if(conts == null) {
            findAllResponse.setStatus("error");
            findAllResponse.setMessage("目前没有用户");
        }else {
            findAllResponse.setStatus("success");
            findAllResponse.setData(conts);
            findAllResponse.setMessage("目前有用户");
        }
        return findAllResponse;
    }

    public static BaseResponse<String> addUser(UserInfoEntity s) {
        BaseResponse<String> addUserResponse = new BaseResponse<>();
        UserLogin userLogin = loginDao.checkUser(s.getUserName());
        if(userLogin == null) {
            String userName = adminDao.addUser(s);
            if(userName == null){
                addUserResponse.setStatus("error");
                addUserResponse.setData(userName);
                addUserResponse.setMessage("添加失败");
            }
            else{
                addUserResponse.setStatus("success");
                addUserResponse.setData(userName);
                addUserResponse.setMessage("成功添加用户'"+userName+"'的信息");
            }
        }else{
            addUserResponse.setStatus("error");
            addUserResponse.setData(s.getUserName());
            addUserResponse.setMessage("用户名'"+s.getUserName()+"'已存在");
        }
        return addUserResponse;

    }
    public static BaseResponse<String> deleteUser(int userId){
        BaseResponse<String> deleteUserResponse = new BaseResponse<>();
        String userName=adminDao.deleteUser(userId);
        if(userName == null){
            deleteUserResponse.setStatus("error");
            deleteUserResponse.setData(userName);
            deleteUserResponse.setMessage("用户名为'"+userName+"'的用户信息删除失败");
        }
        else{
            deleteUserResponse.setStatus("success");
            deleteUserResponse.setData(userName);
            deleteUserResponse.setMessage("用户名为'"+userName+"'的用户信息删除成功");
        }
        return deleteUserResponse;
    }

    public static BaseResponse<String> resetPassword(int userId,String userPassword){
        BaseResponse<String> resetPasswordResponse = new BaseResponse<>();
        String userName= adminDao.resetPassword(userId,userPassword);
        if(userName == null){
            resetPasswordResponse.setStatus("error");
            resetPasswordResponse.setData(userName);
            resetPasswordResponse.setMessage("用户名为'"+userName+"'的密码重置失败");
        }
        else{
            resetPasswordResponse.setStatus("success");
            resetPasswordResponse.setData(userName);
            resetPasswordResponse.setMessage("用户名为'"+userName+"'的密码重置成功");
        }
        return resetPasswordResponse;
    }

    public static BaseResponse<String> updateUser(int userId,String userName,int userLevel,String userRemark) {
        BaseResponse<String> updateUserResponse = new BaseResponse<>();
        userName=adminDao.updateUser(userId,userName,userLevel,userRemark);
        if(userName == null){
            updateUserResponse.setStatus("error");
            updateUserResponse.setData(userName);
            updateUserResponse.setMessage("用户名为'"+userName+"'的信息修改失败");
        }
        else{
            updateUserResponse.setStatus("success");
            updateUserResponse.setData(userName);
            updateUserResponse.setMessage("用户名为'"+userName+"'的信息修改成功");
        }
        return updateUserResponse;
    }
}
