package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.StandardLimitValueEntity;
import entity.StandardLimitValuePic;
import service.StandardLimitValuesService;
import utils.BaseResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ddgdd on 2018/9/10 0010 11:26
 */
public class StandardLimitValuesAction extends ActionSupport {
    //标准限值最大条件集合
    private String voltage;
    private String voltageNum;
    private String powerSupply;
    private int receiveLaunch;
    private int keyEquipment;
    private int staticElectricity;
    private int specialEquipment;
    private String projectId;
    private int secondaryPlatform;
    private int antennaRemoval;
    //    private int stdValueChange;
    private String imgNumNow;

    private String picOneNow;
    private String picTwoNow;
    private int devId;
    private int receiveLaunchCE106;
    private String devName;

    BaseResponse<List<StandardLimitValuePic>> stdLimitValuesResponse = new BaseResponse<List<StandardLimitValuePic>>();
    BaseResponse<List<Map<String, String>>> stdTextResponse = new BaseResponse<List<Map<String, String>>>();
    BaseResponse<HashMap<Integer,List<StandardLimitValuePic>>> biPicResponse = new BaseResponse<>();


    public String getLimitValuesPic() {
        StandardLimitValueEntity standardLimitValueEntity = new StandardLimitValueEntity();
        standardLimitValueEntity.setVoltage(voltage);
        standardLimitValueEntity.setVoltageNum(voltageNum);
        standardLimitValueEntity.setPowerSupply(powerSupply);
        standardLimitValueEntity.setReceiveLaunch(receiveLaunch);
        standardLimitValueEntity.setKeyEquipment(keyEquipment);
        standardLimitValueEntity.setStaticElectricity(staticElectricity);
        standardLimitValueEntity.setSpecialEquipment(specialEquipment);
        standardLimitValueEntity.setProjectId(projectId);
        standardLimitValueEntity.setSecondaryPlatform(secondaryPlatform);
//        standardLimitValueEntity.setStdValueChange(stdValueChange);
        standardLimitValueEntity.setImgNumNow(imgNumNow);
        stdLimitValuesResponse = StandardLimitValuesService.getLimitValuesPic(standardLimitValueEntity,devId, devName);

        return "success";
    }

    public String getLimitValuesBiPic() {
        StandardLimitValueEntity standardLimitValueEntity = new StandardLimitValueEntity();
        standardLimitValueEntity.setVoltageNum(voltageNum);
        standardLimitValueEntity.setProjectId(projectId);
        biPicResponse = StandardLimitValuesService.getLimitValuesBiPic(standardLimitValueEntity, picOneNow, picTwoNow,devId, devName);

        return "success";
    }

    public String getLimitValuesText() {
        StandardLimitValueEntity standardLimitValueEntity = new StandardLimitValueEntity();
        standardLimitValueEntity.setReceiveLaunch(receiveLaunch);
        standardLimitValueEntity.setProjectId(projectId);
        standardLimitValueEntity.setVoltage(voltage);
        standardLimitValueEntity.setAntennaRemoval(antennaRemoval);
        standardLimitValueEntity.setKeyEquipment(keyEquipment);
        standardLimitValueEntity.setStaticElectricity(staticElectricity);

        stdTextResponse = StandardLimitValuesService.getLimitValuesText(standardLimitValueEntity, receiveLaunchCE106);

        return "success";
    }


    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }

    public int getReceiveLaunch() {
        return receiveLaunch;
    }

    public void setReceiveLaunch(int receiveLaunch) {
        this.receiveLaunch = receiveLaunch;
    }

    public int getStaticElectricity() {
        return staticElectricity;
    }

    public void setStaticElectricity(int staticElectricity) {
        this.staticElectricity = staticElectricity;
    }

    public int getSpecialEquipment() {
        return specialEquipment;
    }

    public void setSpecialEquipment(int specialEquipment) {
        this.specialEquipment = specialEquipment;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getVoltageNum() {
        return voltageNum;
    }

    public void setVoltageNum(String voltageNum) {
        this.voltageNum = voltageNum;
    }

    public int getKeyEquipment() {
        return keyEquipment;
    }

    public void setKeyEquipment(int keyEquipment) {
        this.keyEquipment = keyEquipment;
    }

    public int getAntennaRemoval() {
        return antennaRemoval;
    }

    public void setAntennaRemoval(int antennaRemoval) {
        this.antennaRemoval = antennaRemoval;
    }

    public int getSecondaryPlatform() {
        return secondaryPlatform;
    }

    public void setSecondaryPlatform(int secondaryPlatform) {
        this.secondaryPlatform = secondaryPlatform;
    }

    public BaseResponse<List<StandardLimitValuePic>> getStdLimitValuesResponse() {
        return stdLimitValuesResponse;
    }

    public void setStdLimitValuesResponse(BaseResponse<List<StandardLimitValuePic>> stdLimitValuesResponse) {
        this.stdLimitValuesResponse = stdLimitValuesResponse;
    }

    public BaseResponse<List<Map<String, String>>> getStdTextResponse() {
        return stdTextResponse;
    }

    public void setStdTextResponse(BaseResponse<List<Map<String, String>>> stdTextResponse) {
        this.stdTextResponse = stdTextResponse;
    }

    //    public int getStdValueChange() {
//        return stdValueChange;
//    }
//
//    public void setStdValueChange(int stdValueChange) {
//        this.stdValueChange = stdValueChange;
//    }


    public String getImgNumNow() {
        return imgNumNow;
    }

    public void setImgNumNow(String imgNumNow) {
        this.imgNumNow = imgNumNow;
    }

    public String getPicOneNow() {
        return picOneNow;
    }

    public void setPicOneNow(String picOneNow) {
        this.picOneNow = picOneNow;
    }

    public String getPicTwoNow() {
        return picTwoNow;
    }

    public void setPicTwoNow(String picTwoNow) {
        this.picTwoNow = picTwoNow;
    }

    public BaseResponse<HashMap<Integer,List<StandardLimitValuePic>>> getBiPicResponse() {
        return biPicResponse;
    }

    public void setBiPicResponse(BaseResponse<HashMap<Integer,List<StandardLimitValuePic>>> biPicResponse) {
        this.biPicResponse = biPicResponse;
    }

    public int getDevId(){return  devId;}

    public void setDevId(int devId){ this.devId = devId;}

    public int getReceiveLaunchCE106() {return receiveLaunchCE106;}

    public void setReceiveLaunchCE106(int receiveLaunchCE106) {this.receiveLaunchCE106 = receiveLaunchCE106;}

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }
}
