package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.ViewProject;
import utils.BaseResponse;

public class TestAction extends ActionSupport {
    private BaseResponse<ViewProject> testResponse = new BaseResponse<>();
    public String testAction(){
        ViewProject testViewProject = new ViewProject();
        testViewProject.setDevName("test");
        testViewProject.setDevItemid("a/b");
        testViewProject.setDevId(1);
        testViewProject.setDevStatus(1);
        testResponse.setStatus("success");
        testResponse.setData(testViewProject);
        System.out.println("haha");
        return "success";
    }

    public BaseResponse<ViewProject> getTestResponse() {
        return testResponse;
    }

    public void setTestResponse(BaseResponse<ViewProject> testResponse) {
        this.testResponse = testResponse;
    }
}
