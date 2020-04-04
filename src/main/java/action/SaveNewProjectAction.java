package action;

import entity.ManageSysDevelopEntity;
import service.SaveNewProjectService;
import utils.BaseResponse;

import java.sql.Timestamp;

/**
 * Created by ddgdd on 2018/10/12 0012 17:11
 */
public class SaveNewProjectAction {

    private int devId;
    private String devItemid;
    private String devName;
    private Integer devSubsysEqp;
    private String devSubsysEqpName;
    private String devSubsysEqpModel;
    private String devSubsysEqpNum;
    private String devSupplier;
    private Integer devPrimaryPlatform;
    private Integer devSecondaryPlatform;
    private Integer devAttribute;
    private Integer devKey;
    private Integer devInstall;
    private Integer devSpecial;
    private Integer devInterport;
    private Integer devLowsensitive;
    private Integer devEmp;
    private Integer devStatic;
    private Integer devGnd;
    private String devPowername;
    private String devPowerport;
    private String devPowersupply;
    private String devVoltage;
    private String devVoltagenum;
    private Integer devAntenna;
    private Integer devReceiveLaunch;
    private Integer devModulation;
    private String devFreqOptional;
    private String devFreqFhLow;
    private String devFreqFhMid;
    private String devFreqFhHigh;
    private String devFreqDsss;
    private String devCE101;
    private String devCE102;
    private String devCE106;
    private String devCE107;
    private String devCS101;
    private String devCS102;
    private String devCS103;
    private String devCS104;
    private String devCS105;
    private String devCS106;
    private String devCS109;
    private String devCS112;
    private String devCS114;
    private String devCS115;
    private String devCS116;
    private String devRE101;
    private String devRE102;
    private String devRE103;
    private String devRS101;
    private String devRS103;
    private String devRS105;
    private String projectList;
    private Timestamp devCreateTime;
    private Timestamp devNewTime;
    private Timestamp devModifyTime;
    private Timestamp devProofreadTime;
    private Timestamp devAuditTime;
    private Timestamp devAuthorizeTime;
    private Timestamp devUpdateTime;
    private Integer devOpeator;
    private Integer devStatus;
    private String devAdviceProofread;
    private String devAdviceAudit;
    private String devAdviceAuthorize;
    private String userName;
    private Integer devStatusOriginal;
    private int userId;
    private Integer devFreSelect;
    private String  devSubsysSource;
    private String  devSubsysComRef;
    private String  devSubsysQuantity;
    private String  devSubsysEnvironment;

    //返回结果
    BaseResponse saveNewResponse = new BaseResponse();
    BaseResponse updateProjectResponse = new BaseResponse();

    public String  saveNewProject() {
        System.out.println("power_port:"+devPowerport);
        System.out.println("power_supply:"+devPowersupply);
        System.out.println("voltage:"+devVoltage);
        System.out.println("voltage_num:"+devVoltagenum);
        ManageSysDevelopEntity manSysDev = SaveNewProjectService.findProjectById(devId);
        manSysDev = getManSysDev(manSysDev);
        saveNewResponse = SaveNewProjectService.saveNewProject(manSysDev);

        return "success";
    }

    //根据devStatusOriginal更新项目状态
    public String updateProjectStatus(){
        ManageSysDevelopEntity manSysDev = SaveNewProjectService.findProjectById(devId);
        if(devStatusOriginal == 1){
            manSysDev = getProofread(manSysDev);
        }else if(devStatusOriginal == 2){
            manSysDev = getAudit(manSysDev);
        }else if(devStatusOriginal == 3 || devStatusOriginal == 4){
            manSysDev = getAuthorize(manSysDev);
        }
        updateProjectResponse = SaveNewProjectService.saveNewProject(manSysDev);

        return "success";
    }

    public ManageSysDevelopEntity getProofread(ManageSysDevelopEntity manSysDev){
        manSysDev.setDevStatus(devStatus);
        manSysDev.setDevOpeator(userId);
        manSysDev.setDevProofreadTime(new Timestamp(System.currentTimeMillis()));
        //通过判定devAdviceProofread是否为null判定用户是否输入意见
        if(devAdviceProofread != null){
            manSysDev.setDevAdviceProofread(devAdviceProofread);
        }
        return manSysDev;
    }

    public ManageSysDevelopEntity getAudit(ManageSysDevelopEntity manSysDev){
        manSysDev.setDevStatus(devStatus);
        manSysDev.setDevOpeator(userId);
        manSysDev.setDevAuditTime(new Timestamp(System.currentTimeMillis()));
        if(devAdviceAudit != null){
            manSysDev.setDevAdviceAudit(devAdviceAudit);
        }

        return manSysDev;
    }

    public ManageSysDevelopEntity getAuthorize(ManageSysDevelopEntity manSysDev){
        manSysDev.setDevStatus(devStatus);
        manSysDev.setDevOpeator(userId);
        manSysDev.setDevAuthorizeTime(new Timestamp(System.currentTimeMillis()));
        if(devAdviceAuthorize != null){
            manSysDev.setDevAdviceAuthorize(devAdviceAuthorize);
        }
        return manSysDev;
    }

    public ManageSysDevelopEntity getManSysDev(ManageSysDevelopEntity manSysDev) {

        //页面3的内容
        manSysDev.setDevSubsysSource(devSubsysSource);
        manSysDev.setDevSubsysComRef(devSubsysComRef);
        manSysDev.setDevSubsysQuantity(devSubsysQuantity);
        manSysDev.setDevSubsysEnvironment(devSubsysEnvironment);
        manSysDev.setDevSubsysEqp(devSubsysEqp);
        manSysDev.setDevSubsysEqpName(devSubsysEqpName);
        manSysDev.setDevSubsysEqpModel(devSubsysEqpModel);
        manSysDev.setDevSubsysEqpNum(devSubsysEqpNum);
        manSysDev.setDevSupplier(devSupplier);
        manSysDev.setDevPrimaryPlatform(devPrimaryPlatform);
        manSysDev.setDevSecondaryPlatform(devSecondaryPlatform);

        //页面4的内容
        manSysDev.setDevAttribute(devAttribute);
        manSysDev.setDevKey(devKey);
        manSysDev.setDevInstall(devInstall);
        manSysDev.setDevGnd(devGnd);
        manSysDev.setDevSpecial(devSpecial);
        manSysDev.setDevInterport(devInterport);
        manSysDev.setDevLowsensitive(devLowsensitive);
        manSysDev.setDevEmp(devEmp);
        manSysDev.setDevStatic(devStatic);
        manSysDev.setDevPowername(devPowername);
        manSysDev.setDevPowerport(devPowerport);
        manSysDev.setDevPowersupply(devPowersupply);
        manSysDev.setDevVoltage(devVoltage);
        manSysDev.setDevVoltagenum(devVoltagenum);
        manSysDev.setDevAntenna(devAntenna);
        manSysDev.setDevReceiveLaunch(devReceiveLaunch);
        manSysDev.setDevModulation(devModulation);

        //页面4的内容
        manSysDev.setDevFreqOptional(devFreqOptional);
        manSysDev.setDevFreqFhLow(devFreqFhLow);
        manSysDev.setDevFreqFhMid(devFreqFhMid);
        manSysDev.setDevFreqFhHigh(devFreqFhHigh);
        manSysDev.setDevFreqDsss(devFreqDsss);

        //页面5的内容
        manSysDev.setDevCe101(devCE101);
        manSysDev.setDevCe102(devCE102);
        manSysDev.setDevCe106(devCE106);
        manSysDev.setDevCe107(devCE107);
        manSysDev.setDevCs101(devCS101);
        manSysDev.setDevCs102(devCS102);
        manSysDev.setDevCs103(devCS103);
        manSysDev.setDevCs104(devCS104);
        manSysDev.setDevCs105(devCS105);
        manSysDev.setDevCs106(devCS106);
        manSysDev.setDevCs109(devCS109);
        manSysDev.setDevCs112(devCS112);
        manSysDev.setDevCs114(devCS114);
        manSysDev.setDevCs115(devCS115);
        manSysDev.setDevCs116(devCS116);
        manSysDev.setDevRe101(devRE101);
        manSysDev.setDevRe102(devRE102);
        manSysDev.setDevRe103(devRE103);
        manSysDev.setDevRs101(devRS101);
        manSysDev.setDevRs103(devRS103);
        manSysDev.setDevRs105(devRS105);

        //更新所有可用的项目列表
        manSysDev.setProjectList(projectList);
        //更新用频选择状态
        manSysDev.setDevFreSelect(devFreSelect);

        //设置编制完成时间（如果项目状态变为待审核，才需要设置编制完成时间）
        if(devStatus == 1) {
            manSysDev.setDevNewTime(new Timestamp(System.currentTimeMillis()));
        }else { //否则，设置最近修改
            manSysDev.setDevModifyTime(new Timestamp(System.currentTimeMillis()));
        }

        //项目状态
        manSysDev.setDevStatus(devStatus);
        //项目修改人id
        manSysDev.setDevOpeator(userId);

        return manSysDev;
    }



    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public String getDevItemid() {
        return devItemid;
    }

    public void setDevItemid(String devItemid) {
        this.devItemid = devItemid;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevSubsysSource() { return  devSubsysSource;}

    public void setDevSubsysSource(String devSubsysSource) {this.devSubsysSource = devSubsysSource; }

    public String getDevSubsysComRef() {return devSubsysComRef;}

    public void setDevSubsysComRef(String devSubsysComRef) {this.devSubsysComRef = devSubsysComRef;}

    public String getDevSubsysQuantity() {return  devSubsysQuantity;}

    public void setDevSubsysQuantity(String devSubsysQuantity) {this.devSubsysQuantity = devSubsysQuantity;}

    public String getDevSubsysEnvironment() {return  devSubsysEnvironment;}

    public void setDevSubsysEnvironment(String devSubsysEnvironment) {this.devSubsysEnvironment = devSubsysEnvironment;}

    public Integer getDevSubsysEqp() {
        return devSubsysEqp;
    }

    public void setDevSubsysEqp(Integer devSubsysEqp) {
        this.devSubsysEqp = devSubsysEqp;
    }

    public String getDevSubsysEqpName() {
        return devSubsysEqpName;
    }

    public void setDevSubsysEqpName(String devSubsysEqpName) {
        this.devSubsysEqpName = devSubsysEqpName;
    }

    public String getDevSubsysEqpModel() {
        return devSubsysEqpModel;
    }

    public void setDevSubsysEqpModel(String devSubsysEqpModel) {
        this.devSubsysEqpModel = devSubsysEqpModel;
    }

    public String getDevSubsysEqpNum() {
        return devSubsysEqpNum;
    }

    public void setDevSubsysEqpNum(String devSubsysEqpNum) {
        this.devSubsysEqpNum = devSubsysEqpNum;
    }

    public String getDevSupplier() {
        return devSupplier;
    }

    public void setDevSupplier(String devSupplier) {
        this.devSupplier = devSupplier;
    }

    public Integer getDevPrimaryPlatform() {
        return devPrimaryPlatform;
    }

    public void setDevPrimaryPlatform(Integer devPrimaryPlatform) {
        this.devPrimaryPlatform = devPrimaryPlatform;
    }

    public Integer getDevSecondaryPlatform() {
        return devSecondaryPlatform;
    }

    public void setDevSecondaryPlatform(Integer devSecondaryPlatform) {
        this.devSecondaryPlatform = devSecondaryPlatform;
    }

    public Integer getDevAttribute() {
        return devAttribute;
    }

    public void setDevAttribute(Integer devAttribute) {
        this.devAttribute = devAttribute;
    }

    public Integer getDevKey() {
        return devKey;
    }

    public void setDevKey(Integer devKey) {
        this.devKey = devKey;
    }

    public Integer getDevInstall() {
        return devInstall;
    }

    public void setDevInstall(Integer devInstall) {
        this.devInstall = devInstall;
    }

    public Integer getDevSpecial() {
        return devSpecial;
    }

    public void setDevSpecial(Integer devSpecial) {
        this.devSpecial = devSpecial;
    }

    public Integer getDevInterport() {
        return devInterport;
    }

    public void setDevInterport(Integer devInterport) {
        this.devInterport = devInterport;
    }

    public Integer getDevLowsensitive() {
        return devLowsensitive;
    }

    public void setDevLowsensitive(Integer devLowsensitive) {
        this.devLowsensitive = devLowsensitive;
    }

    public Integer getDevEmp() {
        return devEmp;
    }

    public void setDevEmp(Integer devEmp) {
        this.devEmp = devEmp;
    }

    public Integer getDevStatic() {
        return devStatic;
    }

    public void setDevStatic(Integer devStatic) {
        this.devStatic = devStatic;
    }

    public Integer getDevGnd() {
        return devGnd;
    }

    public void setDevGnd(Integer devGnd) {
        this.devGnd = devGnd;
    }

    public String getDevPowername() {return devPowername;}

    public void setDevPowername(String devPowername) { this.devPowername = devPowername;}

    public String getDevPowerport() {
        return devPowerport;
    }

    public void setDevPowerport(String devPowerport) {
        this.devPowerport = devPowerport;
    }

    public String getDevPowersupply() {
        return devPowersupply;
    }

    public void setDevPowersupply(String devPowersupply) {
        this.devPowersupply = devPowersupply;
    }

    public String getDevVoltage() {
        return devVoltage;
    }

    public void setDevVoltage(String devVoltage) {
        this.devVoltage = devVoltage;
    }

    public String getDevVoltagenum() {
        return devVoltagenum;
    }

    public void setDevVoltagenum(String devVoltagenum) {
        this.devVoltagenum = devVoltagenum;
    }

    public Integer getDevAntenna() {
        return devAntenna;
    }

    public void setDevAntenna(Integer devAntenna) {
        this.devAntenna = devAntenna;
    }

    public Integer getDevReceiveLaunch() {
        return devReceiveLaunch;
    }

    public void setDevReceiveLaunch(Integer devReceiveLaunch) {
        this.devReceiveLaunch = devReceiveLaunch;
    }

    public Integer getDevModulation() {
        return devModulation;
    }

    public void setDevModulation(Integer devModulation) {
        this.devModulation = devModulation;
    }

    public String getDevFreqOptional() {
        return devFreqOptional;
    }

    public void setDevFreqOptional(String devFreqOptional) {
        this.devFreqOptional = devFreqOptional;
    }

    public String getDevFreqFhLow() {
        return devFreqFhLow;
    }

    public void setDevFreqFhLow(String devFreqFhLow) {
        this.devFreqFhLow = devFreqFhLow;
    }

    public String getDevFreqFhMid() {
        return devFreqFhMid;
    }

    public void setDevFreqFhMid(String devFreqFhMid) {
        this.devFreqFhMid = devFreqFhMid;
    }

    public String getDevFreqFhHigh() {
        return devFreqFhHigh;
    }

    public void setDevFreqFhHigh(String devFreqFhHigh) {
        this.devFreqFhHigh = devFreqFhHigh;
    }

    public String getDevFreqDsss() {
        return devFreqDsss;
    }

    public void setDevFreqDsss(String devFreqDsss) {
        this.devFreqDsss = devFreqDsss;
    }

    public String getDevCE101() {
        return devCE101;
    }

    public void setDevCE101(String devCE101) {
        this.devCE101 = devCE101;
    }

    public String getDevCE102() {
        return devCE102;
    }

    public void setDevCE102(String devCE102) {
        this.devCE102 = devCE102;
    }

    public String getDevCE106() {
        return devCE106;
    }

    public void setDevCE106(String devCE106) {
        this.devCE106 = devCE106;
    }

    public String getDevCE107() {
        return devCE107;
    }

    public void setDevCE107(String devCE107) {
        this.devCE107 = devCE107;
    }

    public String getDevCS101() {
        return devCS101;
    }

    public void setDevCS101(String devCS101) {
        this.devCS101 = devCS101;
    }

    public String getDevCS102() {
        return devCS102;
    }

    public void setDevCS102(String devCS102) {
        this.devCS102 = devCS102;
    }

    public String getDevCS103() {
        return devCS103;
    }

    public void setDevCS103(String devCS103) {
        this.devCS103 = devCS103;
    }

    public String getDevCS104() {
        return devCS104;
    }

    public void setDevCS104(String devCS104) {
        this.devCS104 = devCS104;
    }

    public String getDevCS105() {
        return devCS105;
    }

    public void setDevCS105(String devCS105) {
        this.devCS105 = devCS105;
    }

    public String getDevCS106() {
        return devCS106;
    }

    public void setDevCS106(String devCS106) {
        this.devCS106 = devCS106;
    }

    public String getDevCS109() {
        return devCS109;
    }

    public void setDevCS109(String devCS109) {
        this.devCS109 = devCS109;
    }

    public String getDevCS112() {
        return devCS112;
    }

    public void setDevCS112(String devCS112) {
        this.devCS112 = devCS112;
    }

    public String getDevCS114() {
        return devCS114;
    }

    public void setDevCS114(String devCS114) {
        this.devCS114 = devCS114;
    }

    public String getDevCS115() {
        return devCS115;
    }

    public void setDevCS115(String devCS115) {
        this.devCS115 = devCS115;
    }

    public String getDevCS116() {
        return devCS116;
    }

    public void setDevCS116(String devCS116) {
        this.devCS116 = devCS116;
    }

    public String getDevRE101() {
        return devRE101;
    }

    public void setDevRE101(String devRE101) {
        this.devRE101 = devRE101;
    }

    public String getDevRE102() {
        return devRE102;
    }

    public void setDevRE102(String devRE102) {
        this.devRE102 = devRE102;
    }

    public String getDevRE103() {
        return devRE103;
    }

    public void setDevRE103(String devRE103) {
        this.devRE103 = devRE103;
    }

    public String getDevRS101() {
        return devRS101;
    }

    public void setDevRS101(String devRS101) {
        this.devRS101 = devRS101;
    }

    public String getDevRS103() {
        return devRS103;
    }

    public void setDevRS103(String devRS103) {
        this.devRS103 = devRS103;
    }

    public String getDevRS105() {
        return devRS105;
    }

    public void setDevRS105(String devRS105) {
        this.devRS105 = devRS105;
    }

    public String getProjectList() {
        return projectList;
    }

    public void setProjectList(String projectList) {
        this.projectList = projectList;
    }

    public Timestamp getDevCreateTime() {
        return devCreateTime;
    }

    public void setDevCreateTime(Timestamp devCreateTime) {
        this.devCreateTime = devCreateTime;
    }

    public Timestamp getDevNewTime() {
        return devNewTime;
    }

    public void setDevNewTime(Timestamp devNewTime) {
        this.devNewTime = devNewTime;
    }

    public Timestamp getDevModifyTime() {
        return devModifyTime;
    }

    public void setDevModifyTime(Timestamp devModifyTime) {
        this.devModifyTime = devModifyTime;
    }

    public Timestamp getDevProofreadTime() {
        return devProofreadTime;
    }

    public void setDevProofreadTime(Timestamp devProofreadTime) {
        this.devProofreadTime = devProofreadTime;
    }

    public Timestamp getDevAuditTime() {
        return devAuditTime;
    }

    public void setDevAuditTime(Timestamp devAuditTime) {
        this.devAuditTime = devAuditTime;
    }

    public Timestamp getDevAuthorizeTime() {
        return devAuthorizeTime;
    }

    public void setDevAuthorizeTime(Timestamp devAuthorizeTime) {
        this.devAuthorizeTime = devAuthorizeTime;
    }

    public Timestamp getDevUpdateTime() {
        return devUpdateTime;
    }

    public void setDevUpdateTime(Timestamp devUpdateTime) {
        this.devUpdateTime = devUpdateTime;
    }

    public Integer getDevOpeator() {
        return devOpeator;
    }

    public void setDevOpeator(Integer devOpeator) {
        this.devOpeator = devOpeator;
    }

    public Integer getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(Integer devStatus) {
        this.devStatus = devStatus;
    }

    public BaseResponse getSaveNewResponse() {
        return saveNewResponse;
    }

    public void setSaveNewResponse(BaseResponse saveNewResponse) {
        this.saveNewResponse = saveNewResponse;
    }

    public String getUserName(){
        return  userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getDevAdviceProofread(){
        return devAdviceProofread;
    }

    public void setDevAdviceProofread(String devAdviceProofread){
        this.devAdviceProofread = devAdviceProofread;
    }

    public String getDevAdviceAudit(){
        return devAdviceAudit;
    }

    public void setDevAdviceAudit(String devAdviceAudit){
        this.devAdviceAudit = devAdviceAudit;
    }

    public String getDevAdviceAuthorize(){
        return devAdviceAuthorize;
    }

    public void setDevAdviceAuthorize(String devAdviceAuthorize){
        this.devAdviceAuthorize = devAdviceAuthorize;
    }

    public Integer getDevStatusOriginal(){
        return devStatusOriginal;
    }

    public void setDevStatusOriginal(Integer devStatusOriginal){
        this.devStatusOriginal = devStatusOriginal;
    }

    public BaseResponse getUpdateProjectResponse() {
        return updateProjectResponse;
    }

    public void setUpdateProjectResponse(BaseResponse updateProjectResponse) {
        this.updateProjectResponse = updateProjectResponse;
    }

    public int getUserId(){ return userId;}

    public void setUserId(int userId){ this.userId = userId;}

    public Integer getDevFreSelect(){ return  devFreSelect;}

    public void setDevFreSelect(Integer devFreSelect){ this.devFreSelect = devFreSelect;}


}
