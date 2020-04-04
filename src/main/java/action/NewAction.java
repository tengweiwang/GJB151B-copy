package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import utils.Base64Utils;
import utils.BaseResponse;

import java.io.File;
import java.io.IOException;
import consts.PathStoreEnum;

/**
 * Created by ddgdd on 2018/9/7 0007 10:20
 */
public class NewAction extends ActionSupport {
    private String imgBase64;
    private String stdProject;

    // TODO 之后还要传入项目名

    private File upImg;
    private String upImgContentType;
    private String upImgFileName;

    private int devId;
    private String devName;

    private BaseResponse<String> saveImgResponse = new BaseResponse<>();
    private BaseResponse uploadImgResponse = new BaseResponse();

    public String saveImg() {
        imgBase64 = imgBase64.split(",")[1];
//        StringBuilder prefix = new StringBuilder("d://GJB151B//img//");
//        StringBuilder prefix = new StringBuilder("img/");
        StringBuilder prefix = new StringBuilder(PathStoreEnum.WINDOWS_CHANGEDIMG_SOURTHPATH.getValue());
        prefix.append(devName);
        prefix.append("_图片//");
        StringBuilder imgName = new StringBuilder(String.valueOf(devId));
        imgName.append("_");
        imgName.append(stdProject);
        prefix.append(imgName);
        System.out.println("stdProject:"+stdProject);

        Boolean base64ToImg = Base64Utils.Base64ToImg(imgBase64, prefix.append(".png").toString());
        if(base64ToImg) { //存入成功
            saveImgResponse.setStatus("success");
            saveImgResponse.setData(imgName.toString());
        }else {
            saveImgResponse.setMessage("标准限值修改失败，请稍后再试。");
        }
//        String currentPath = getClass().getResource(".").getFile().toString();
//        System.out.println(currentPath);

        return "success";
    }

    public String uploadImg() {
//        StringBuilder prefix = new StringBuilder("/Users/wangtengteng/Documents/GJB151B/img/");
//        StringBuilder prefix = new StringBuilder("d://GJB151B//img//");
//        StringBuilder prefix = new StringBuilder("img/");
        StringBuilder prefix = new StringBuilder(PathStoreEnum.WINDOWS_CHANGEDIMG_SOURTHPATH.getValue());
        prefix.append(devName);
        prefix.append("_图片//");
        StringBuilder imgName = new StringBuilder(String.valueOf(devId));
        imgName.append("_");
        imgName.append(stdProject);
//        prefix.append(String.valueOf(devId));
//        prefix.append("_");
//        prefix.append(stdProject);
        prefix.append(imgName);
        prefix.append(".");
        prefix.append(upImgContentType.split("/")[1]);

        try {
            FileUtils.copyFile(upImg, new File(prefix.toString()));
            uploadImgResponse.setStatus("success");
            uploadImgResponse.setData(imgName.toString());

        } catch (IOException e) {
            e.printStackTrace();
            uploadImgResponse.setMessage("图片上传失败，请稍后再试。");
        }

        return "success";
    }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public String getStdProject() {
        return stdProject;
    }

    public void setStdProject(String stdProject) {
        this.stdProject = stdProject;
    }

    public File getUpImg() {
        return upImg;
    }

    public void setUpImg(File upImg) {
        this.upImg = upImg;
    }

    public String getUpImgContentType() {
        return upImgContentType;
    }

    public void setUpImgContentType(String upImgContentType) {
        this.upImgContentType = upImgContentType;
    }

    public String getUpImgFileName() {
        return upImgFileName;
    }

    public void setUpImgFileName(String upImgFileName) {
        this.upImgFileName = upImgFileName;
    }

    public BaseResponse<String> getSaveImgResponse() {
        return saveImgResponse;
    }

    public void setSaveImgResponse(BaseResponse<String> saveImgResponse) {
        this.saveImgResponse = saveImgResponse;
    }

    public BaseResponse getUploadImgResponse() {
        return uploadImgResponse;
    }

    public void setUploadImgResponse(BaseResponse uploadImgResponse) {
        this.uploadImgResponse = uploadImgResponse;
    }

    public int getDevId(){ return devId;}

    public void setDevId(int devId){ this.devId = devId;}

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }
}
