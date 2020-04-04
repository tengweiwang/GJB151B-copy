package service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import dao.UserProjectDao;
import dao.UpdateProjectDao;
import entity.UserProject;
import java.sql.Timestamp;

public class UpdateProjectService {
    private static UpdateProjectDao updateProjectDao = UpdateProjectDao.getInstance();
    private static UserProjectDao userProjectDao = UserProjectDao.getInstance();

    public static String updateProofreadStatus(int devId,int devStatus, Timestamp timestamp){
        return updateProjectDao.changeProofreadStatus(devId,devStatus, timestamp);
    }

    public static String updateAuditStatus(int devId,int devStatus, Timestamp timestamp){
        return updateProjectDao.changeAuditStatus(devId,devStatus, timestamp);
    }

    public static String updateAuthorizeStatus(int devId,int devStatus, Timestamp timestamp){
        return updateProjectDao.changeAuthorizeStatus(devId,devStatus, timestamp);
    }

    public static String updateProofreadAdvice(int devId, int devStatus, String devAdviceProofread, Timestamp timestamp){
        return updateProjectDao.changeProjectProofreadAdvice(devId, devStatus, devAdviceProofread, timestamp);
    }

    public static String updateAuditAdvice(int devId, int devStatus, String devAdviceAudit, Timestamp timestamp){
        return updateProjectDao.changeProjectAuditAdvice(devId, devStatus, devAdviceAudit, timestamp);
    }

    public static String updateAuthorizeAdvice(int devId, int devStatus, String devAdviceAuthorize, Timestamp timestamp){
        return updateProjectDao.changeProjectAuthorizeAdvice(devId, devStatus, devAdviceAuthorize, timestamp);
    }

    public static String draftAdvice(int devId, String devAdviceProofread){
        return updateProjectDao.saveDraft(devId, devAdviceProofread);
    }

    public static String updateUserProject(String userName, String devItemid, int devStatus){
//        String userStringList = updateProjectDao.selectUserProject(userName, userList);
        UserProject up = userProjectDao.FindUser(userName);
        System.out.println("userName是"+userName);
        String userStringList = "success";
        if(devStatus == 1){
            userStringList = up.getUserProofread();
        }else if(devStatus == 2){
            userStringList= up.getUserAudit();
        }else if(devStatus == 3){
            userStringList = up.getUserAuthorize();
        }
        if(userStringList != null){
            JSONArray userJsonList = (JSONArray) JSON.parse(userStringList);
            System.out.println("userJsonList"+userJsonList);
//            userJsonList.add(devItemid);
            //对项目列表进行查重
            int n = 0;
            for(int i = 0;i<userJsonList.size();i++){
                if(userJsonList.getString(i).equals(devItemid)){
                    break;
                }
                n++;
            }
            if(n == userJsonList.size()){
                userJsonList.add(devItemid);
            }
            userStringList = userJsonList.toString();
            System.out.println("userStringList"+userStringList);
            System.out.println("我都走到这啦");
            if(devStatus == 1){
                return (updateProjectDao.changeUserProofreadProject(userName, userStringList));
            } else if(devStatus == 2){
                return (updateProjectDao.changeUserAuditProject(userName, userStringList));
            }else {
                return (updateProjectDao.changeUserAuthorizeProject(userName, userStringList));
            }

        }else{
            System.out.println("是我返回null啦");
            return null;
        }
    }
}

