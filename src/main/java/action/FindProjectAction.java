package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.ViewProject;
import utils.BaseResponse;
import java.util.List;
import java.util.Map;

import service.ViewProjectService;

public class FindProjectAction extends ActionSupport {

    private String userName;
    private BaseResponse<Map<String, List<ViewProject>>> projectResultResponse = new BaseResponse<Map<String, List<ViewProject>>>();

    public String showProject() {

        Map<String, List<ViewProject>> projectResults = ViewProjectService.allProject(userName);
        if (projectResults == null) {
            projectResultResponse.setStatus("error");
            projectResultResponse.setMessage("用户项目列表更新失败");
        } else {
            projectResultResponse.setData(projectResults);
            projectResultResponse.setStatus("success");
        }
        return "success";


    }

    public String getUserName(){return userName;}
    public void setUserName(String userName){this.userName = userName;}
    public BaseResponse<Map<String,List<ViewProject>>> getProjectResultResponse(){return projectResultResponse;}
    public void setProjectResultResponsesult(BaseResponse<Map<String,List<ViewProject>>> projectResultResponse){this.projectResultResponse = projectResultResponse;}
}
