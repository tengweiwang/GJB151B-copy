package action;

import entity.ManageSysDevelopEntity;
import service.SaveNewProjectService;
import utils.BaseResponse;

/**
 * Created by ddgdd on 2018/10/17 0017 17:30
 */
public class ModifyProjectAction {
    private int devId;
    private BaseResponse<ManageSysDevelopEntity> modifyProjectResponse = new BaseResponse<>();

    public String findProjectById() {
        System.out.println("这是前端传到dao层来的"+devId);
        ManageSysDevelopEntity manSysDev = SaveNewProjectService.findProjectById(devId);
        if(manSysDev == null) {
            modifyProjectResponse.setMessage("查找项目失败，请稍后重试");
        }else {
            modifyProjectResponse.setStatus("success");
            modifyProjectResponse.setData(manSysDev);
            System.out.println(manSysDev.toString());
        }

        return "success";
    }

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public BaseResponse<ManageSysDevelopEntity> getModifyProjectResponse() {
        return modifyProjectResponse;
    }

    public void setModifyProjectResponse(BaseResponse<ManageSysDevelopEntity> modifyProjectResponse) {
        this.modifyProjectResponse = modifyProjectResponse;
    }
}
