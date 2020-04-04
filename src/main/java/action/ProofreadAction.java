package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.ManageSysDevelopEntity;
import utils.BaseResponse;
import service.SaveNewProjectService;
import service.UpdateProjectService;

import java.sql.Timestamp;

public class ProofreadAction extends ActionSupport {
    private int devId;
    private int devStatus;
    private int devStatusOriginal;
    private String userName;
    private String devItemid;
    private String devAdviceProofread;
    private String devAdviceAudit;
    private String devAdviceAuthorize;
    //    private String userList;
    private BaseResponse<ManageSysDevelopEntity> proofreadResultResponse = new BaseResponse<ManageSysDevelopEntity>();

    public String proofreadFindProject(){
        ManageSysDevelopEntity manSys = SaveNewProjectService.findProjectById(devId);
        if(manSys != null){
            proofreadResultResponse.setStatus("success");
            proofreadResultResponse.setData(manSys);
        }else {
            proofreadResultResponse.setStatus("error");
            proofreadResultResponse.setMessage("获取项目信息失败");
        }

        return "success";
    }

    public String updateProjectStatus(){
        System.out.println("userName在这里哈"+userName);
        System.out.println("devAdviceProofread:"+devAdviceProofread);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String statusProject = "success";
        System.out.println("传过来的devAdviceString"+devAdviceAudit);
        if(devStatus == 5){
            if(devAdviceAuthorize == null) {
                statusProject = UpdateProjectService.updateAuthorizeStatus(devId, devStatus, timestamp);
            }else{
                statusProject = UpdateProjectService.updateAuthorizeAdvice(devId, devStatus, devAdviceAuthorize, timestamp);
            }
            if(statusProject.equals("success")){
                proofreadResultResponse.setStatus("success");
            }else{
                proofreadResultResponse.setStatus("error");
                proofreadResultResponse.setMessage("项目状态更新失败");
            }
        }else {
            if(devStatus == 2) {
                if(devAdviceProofread == null) {
                    statusProject = UpdateProjectService.updateProofreadStatus(devId, devStatus, timestamp);
                }else{
                    statusProject = UpdateProjectService.updateProofreadAdvice(devId, devStatus, devAdviceProofread, timestamp);
                }
            }else if(devStatus == 3){
                if(devAdviceAudit == null) {
                    statusProject = UpdateProjectService.updateAuditStatus(devId, devStatus, timestamp);
                }else{
                    statusProject = UpdateProjectService.updateAuditAdvice(devId, devStatus, devAdviceAudit, timestamp);
                }
            }
            String statusUser = UpdateProjectService.updateUserProject(userName, devItemid, devStatus);
            System.out.println("statusUser" + statusUser);
            if (statusProject.equals("success")) {
                if (statusUser.equals("success")) {
                    proofreadResultResponse.setStatus("success");
                } else {
                    proofreadResultResponse.setStatus("error");
                    proofreadResultResponse.setMessage("用户项目列表更新失败");
                }
            } else {
                proofreadResultResponse.setStatus("error");
                proofreadResultResponse.setMessage("项目状态更新失败");
            }
        }
        return "success";

    }


    public String updateProjectAdvice(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String status = "success";
        if(devStatusOriginal == 1) {
            status = UpdateProjectService.updateProofreadAdvice(devId, devStatus, devAdviceProofread, timestamp);
        }else if(devStatusOriginal == 2) {
            status = UpdateProjectService.updateAuditAdvice(devId, devStatus, devAdviceAudit, timestamp);
        }else if(devStatusOriginal == 3){
            status = UpdateProjectService.updateAuthorizeAdvice(devId, devStatus, devAdviceAuthorize, timestamp);
        }
        if(status.equals("success")){
            proofreadResultResponse.setStatus("success");
        }else{
            proofreadResultResponse.setStatus("error");
            proofreadResultResponse.setMessage("校对意见更新失败");
        }
        return "success";

    }


    public String saveAdviceDraft(){
        String status = UpdateProjectService.draftAdvice(devId, devAdviceProofread);
        if(status.equals("success")){
            proofreadResultResponse.setStatus("success");
        }else{
            proofreadResultResponse.setStatus("error");
            proofreadResultResponse.setMessage("保存草稿失败");
        }
        return "success";
    }



    public String test(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String status = UpdateProjectService.updateAuditAdvice(5,2,"['123']", timestamp);
        if(status.equals("success")){
            proofreadResultResponse.setStatus("success");
        }else{
            proofreadResultResponse.setStatus("error");
            proofreadResultResponse.setMessage("保存草稿失败");
        }
        return "success";
    }











    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public int getDevStatus(){
        return devStatus;
    }

    public void setDevStatus(int devStatus){
        this.devStatus = devStatus;
    }

    public int getDevStatusOriginal(){ return devStatusOriginal;}

    public void setDevStatusOriginal(int devStatusOriginal){ this.devStatusOriginal=devStatusOriginal;}

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getDevItemid(){
        return devItemid;
    }

    public void setDevItemid(String devItemid){
        this.devItemid=devItemid;
    }

    public String getDevAdviceAudit(){ return  devAdviceAudit;}

    public void setDevAdviceAudit(String devAdviceAudit){ this.devAdviceAudit=devAdviceAudit;}

    public String getDevAdviceAuthorize(){ return devAdviceAuthorize;}

    public void setDevAdviceAuthorize(String devAdviceAuthorize){ this.devAdviceAuthorize=devAdviceAuthorize;}

//    public String getUserList(){
//        return userList;
//    }
//
//    public void setUserList(String userList){
//        this.userList = userList;
//    }

    public String getDevAdviceProofread(){
        return devAdviceProofread;
    }

    public void setDevAdviceProofread(String devAdviceProofread){
        this.devAdviceProofread = devAdviceProofread;
    }

    public BaseResponse<ManageSysDevelopEntity> getProofreadResultResponse(){return proofreadResultResponse;}

    public void setProofreadResultResponse(BaseResponse<ManageSysDevelopEntity> proofreadResultResponse){this.proofreadResultResponse = proofreadResultResponse;}

}
