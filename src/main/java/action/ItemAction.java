package action;

import com.opensymphony.xwork2.ActionSupport;
//import consts.ItemNamePrefixEnum;
import consts.ItemNamePrefix;
import dao.ItemDao;
import entity.ManageSysDevelopEntity;
import entity.UserInfoEntity;
import entity.ViewProject;
import org.apache.struts2.ServletActionContext;
import service.AdminService;
import service.ItemService;
import utils.BaseResponse;
import utils.UUIDUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemAction extends ActionSupport{

    private String devItemId;
    private String devName;
    private String user_new;
    private String user_proofread;
    private String user_audit;
    private String user_authorize;
    private Map<String, List<String>> user_New = new HashMap<>();
    private BaseResponse findAllItemResponse = new BaseResponse<List<ViewProject>>();
    private BaseResponse addItemResponse = new BaseResponse<String>();
    private BaseResponse deleteItemResponse = new BaseResponse<String>();
    private BaseResponse updateItemResponse = new BaseResponse<String>();
    private BaseResponse updateItemNameResponse = new BaseResponse<String>();
    private BaseResponse findAllResponse = new BaseResponse<List<UserInfoEntity>>();
    private BaseResponse exportItemResponse = new BaseResponse();
    private List<String> list1;
    public String findAllItem(){
        findAllItemResponse = ItemService.findAllItem();
        return "success";
    }

    public String addItem(){
        ManageSysDevelopEntity s = new ManageSysDevelopEntity ();
        s.setDevItemid(UUIDUtils.getUUID());
        String uniqueDevName = ItemNamePrefix.getValue() + this.devName;
        s.setDevName(uniqueDevName);
        s.setDevStatus(0);
        s.setDevAdviceProofread("[]");
        s.setDevAdviceAudit("[]");
        s.setDevAdviceAuthorize("[]");
        addItemResponse = ItemService.addItem(s, user_new, user_proofread, user_audit, user_authorize);
        return "success";
    }

    public String deleteItem(){
        deleteItemResponse=ItemService.deleteItem(this.devName);
        return "success";
    }

    public String updateItemName(){
        updateItemNameResponse=ItemService.updateItemName(this.devItemId, this.devName);
        return "success";
    }

    public String updateOperator(){
        updateItemResponse=ItemService.updateItem(this.devItemId, this.user_new, this.user_proofread, this.user_audit, this.user_authorize);
        return "success";
    }

    public String exportItem() {

        try {
            exportItemResponse = ItemService.exportItem(devItemId);
        } catch (SQLException e) {
            exportItemResponse.setMessage("系统导出异常，请稍后重试！");
            exportItemResponse.setStatus("error");
            e.printStackTrace();
        } catch (Exception e) {
            exportItemResponse.setMessage("系统导出异常，请稍后重试！");
            exportItemResponse.setStatus("error");
            e.printStackTrace();
        }
        return "success";
    }

    public BaseResponse<List<UserInfoEntity>> getFindAllResponse() {
        return findAllResponse;
    }

    public BaseResponse<List> getFindAllItemResponse() {
        return findAllItemResponse;
    }

    public BaseResponse<String> getAddItemResponse() {
        return addItemResponse;
    }

    public BaseResponse<String> getDeleteItemResponse() {
        return deleteItemResponse;
    }

    public BaseResponse<String> getUpdateItemResponse() {
        return updateItemResponse;
    }

    public BaseResponse<String> getUpdateItemNameResponse() {
        return updateItemNameResponse;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getUser_new() {
        return user_new;
    }

    public void setUser_new(String user_new) {
        this.user_new = user_new;
    }

    public String getUser_proofread() {
        return user_proofread;
    }

    public void setUser_proofread(String user_proofread) {
        this.user_proofread = user_proofread;
    }

    public String getUser_audit() {
        return user_audit;
    }

    public void setUser_audit(String user_audit) {
        this.user_audit = user_audit;
    }

    public String getUser_authorize() {
        return user_authorize;
    }

    public void setUser_authorize(String user_authorize) {
        this.user_authorize = user_authorize;
    }

    public List getList1() {
        return list1;
    }
    public void setList1(List list1) {
        this.list1 = list1;
    }

    public Map getUser_New(){return user_New;}
    public void setUser_New(Map user_New){this.user_New = user_New;}

    public String getDevItemId() {
        return devItemId;
    }

    public void setDevItemId(String devItemId) {
        this.devItemId = devItemId;
    }

    public BaseResponse getExportItemResponse() {
        return exportItemResponse;
    }

    public void setExportItemResponse(BaseResponse exportItemResponse) {
        this.exportItemResponse = exportItemResponse;
    }

}
