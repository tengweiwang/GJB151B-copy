package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ddgdd on 2018/10/22 0022 11:39
 */
@Entity
@Table(name = "manage_sys_develop", schema = "gjb151b", catalog = "")
public class ManageSysDevelopEntity {
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
    private Integer devGnd;
    private Integer devSpecial;
    private Integer devInterport;
    private Integer devLowsensitive;
    private Integer devEmp;
    private Integer devStatic;
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
    private String devCe101;
    private String devCe102;
    private String devCe106;
    private String devCe107;
    private String devCs101;
    private String devCs102;
    private String devCs103;
    private String devCs104;
    private String devCs105;
    private String devCs106;
    private String devCs109;
    private String devCs112;
    private String devCs114;
    private String devCs115;
    private String devCs116;
    private String devRe101;
    private String devRe102;
    private String devRe103;
    private String devRs101;
    private String devRs103;
    private String devRs105;
    private String projectList;
    private String devAdviceProofread;
    private String devAdviceAudit;
    private String devAdviceAuthorize;
    private Timestamp devCreateTime;
    private Timestamp devNewTime;
    private Timestamp devModifyTime;
    private Timestamp devProofreadTime;
    private Timestamp devAuditTime;
    private Timestamp devAuthorizeTime;
    private Timestamp devUpdateTime;
    private Integer devOpeator;
    private Integer devStatus;
    private Integer devFreSelect;
    private String devSubsysSource;
    private String devSubsysComRef;
    private String devSubsysQuantity;
    private String devSubsysEnvironment;
    private String devPowername;

    @Id
    @Column(name = "dev_id", nullable = false)
    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    @Basic
    @Column(name = "dev_itemid", nullable = false, length = 50)
    public String getDevItemid() {
        return devItemid;
    }

    public void setDevItemid(String devItemid) {
        this.devItemid = devItemid;
    }

    @Basic
    @Column(name = "dev_name", nullable = true, length = 50)
    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }
//
//    @Basic
//    @Column(name = "dev_subsys_source", nullable = true, length = -1)
//    public String getDevSubsysSource() { return  devSubsysSource;}
//
//    public void setDevSubsysSource(String devSubsysSource) {this.devSubsysSource = devSubsysSource; }
//
//    @Basic
//    @Column(name = "dev_subsys_com_ref", nullable = true, length = -1)
//    public String getDevSubsysComRef() {return devSubsysComRef;}
//
//    public void setDevSubsysComRef(String devSubsysComRef) {this.devSubsysComRef = devSubsysComRef;}
//
//    @Basic
//    @Column(name = "dev_subsys_quantity", nullable = true, length = -1)
//    public String getDevSubsysQuantity() {return  devSubsysQuantity;}
//
//    public void setDevSubsysQuantity(String devSubsysQuantity) {this.devSubsysQuantity = devSubsysQuantity;}

    @Basic
    @Column(name = "dev_subsys_environment", nullable = true, length = -1)
    public String getDevSubsysEnvironment() {return  devSubsysEnvironment;}

    public void setDevSubsysEnvironment(String devSubsysEnvironment) {this.devSubsysEnvironment = devSubsysEnvironment;}


    @Basic
    @Column(name = "dev_subsys_eqp", nullable = true)
    public Integer getDevSubsysEqp() {
        return devSubsysEqp;
    }

    public void setDevSubsysEqp(Integer devSubsysEqp) {
        this.devSubsysEqp = devSubsysEqp;
    }

    @Basic
    @Column(name = "dev_subsys_eqp_name", nullable = true, length = 50)
    public String getDevSubsysEqpName() {
        return devSubsysEqpName;
    }

    public void setDevSubsysEqpName(String devSubsysEqpName) {
        this.devSubsysEqpName = devSubsysEqpName;
    }

    @Basic
    @Column(name = "dev_subsys_eqp_model", nullable = true, length = 50)
    public String getDevSubsysEqpModel() {
        return devSubsysEqpModel;
    }

    public void setDevSubsysEqpModel(String devSubsysEqpModel) {
        this.devSubsysEqpModel = devSubsysEqpModel;
    }

    @Basic
    @Column(name = "dev_subsys_eqp_num", nullable = true, length = 50)
    public String getDevSubsysEqpNum() {
        return devSubsysEqpNum;
    }

    public void setDevSubsysEqpNum(String devSubsysEqpNum) {
        this.devSubsysEqpNum = devSubsysEqpNum;
    }

    @Basic
    @Column(name = "dev_supplier", nullable = true, length = 50)
    public String getDevSupplier() {
        return devSupplier;
    }

    public void setDevSupplier(String devSupplier) {
        this.devSupplier = devSupplier;
    }

    @Basic
    @Column(name = "dev_primary_platform", nullable = true)
    public Integer getDevPrimaryPlatform() {
        return devPrimaryPlatform;
    }

    public void setDevPrimaryPlatform(Integer devPrimaryPlatform) {
        this.devPrimaryPlatform = devPrimaryPlatform;
    }

    @Basic
    @Column(name = "dev_secondary_platform", nullable = true)
    public Integer getDevSecondaryPlatform() {
        return devSecondaryPlatform;
    }

    public void setDevSecondaryPlatform(Integer devSecondaryPlatform) {
        this.devSecondaryPlatform = devSecondaryPlatform;
    }

    @Basic
    @Column(name = "dev_attribute", nullable = true)
    public Integer getDevAttribute() {
        return devAttribute;
    }

    public void setDevAttribute(Integer devAttribute) {
        this.devAttribute = devAttribute;
    }

    @Basic
    @Column(name = "dev_key", nullable = true)
    public Integer getDevKey() {
        return devKey;
    }

    public void setDevKey(Integer devKey) {
        this.devKey = devKey;
    }

    @Basic
    @Column(name = "dev_install", nullable = true)
    public Integer getDevInstall() {
        return devInstall;
    }

    public void setDevInstall(Integer devInstall) {
        this.devInstall = devInstall;
    }

    @Basic
    @Column(name = "dev_GND", nullable = true)
    public Integer getDevGnd() {
        return devGnd;
    }

    public void setDevGnd(Integer devGnd) {
        this.devGnd = devGnd;
    }

    @Basic
    @Column(name = "dev_special", nullable = true)
    public Integer getDevSpecial() {
        return devSpecial;
    }

    public void setDevSpecial(Integer devSpecial) {
        this.devSpecial = devSpecial;
    }

    @Basic
    @Column(name = "dev_interport", nullable = true)
    public Integer getDevInterport() {
        return devInterport;
    }

    public void setDevInterport(Integer devInterport) {
        this.devInterport = devInterport;
    }

    @Basic
    @Column(name = "dev_lowsensitive", nullable = true)
    public Integer getDevLowsensitive() {
        return devLowsensitive;
    }

    public void setDevLowsensitive(Integer devLowsensitive) {
        this.devLowsensitive = devLowsensitive;
    }

    @Basic
    @Column(name = "dev_emp", nullable = true)
    public Integer getDevEmp() {
        return devEmp;
    }

    public void setDevEmp(Integer devEmp) {
        this.devEmp = devEmp;
    }

    @Basic
    @Column(name = "dev_static", nullable = true)
    public Integer getDevStatic() {
        return devStatic;
    }

    public void setDevStatic(Integer devStatic) {
        this.devStatic = devStatic;
    }

    @Basic
    @Column(name = "dev_powerport", nullable = true)
    public String getDevPowerport() {
        return devPowerport;
    }

    public void setDevPowerport(String devPowerport) {
        this.devPowerport = devPowerport;
    }

    @Basic
    @Column(name = "dev_powersupply", nullable = true)
    public String getDevPowersupply() {
        return devPowersupply;
    }

    public void setDevPowersupply(String devPowersupply) {
        this.devPowersupply = devPowersupply;
    }

    @Basic
    @Column(name = "dev_voltage", nullable = true)
    public String getDevVoltage() {
        return devVoltage;
    }

    public void setDevVoltage(String devVoltage) {
        this.devVoltage = devVoltage;
    }

    @Basic
    @Column(name = "dev_voltagenum", nullable = true)
    public String getDevVoltagenum() {
        return devVoltagenum;
    }

    public void setDevVoltagenum(String devVoltagenum) {
        this.devVoltagenum = devVoltagenum;
    }

    @Basic
    @Column(name = "dev_antenna", nullable = true)
    public Integer getDevAntenna() {
        return devAntenna;
    }

    public void setDevAntenna(Integer devAntenna) {
        this.devAntenna = devAntenna;
    }

    @Basic
    @Column(name = "dev_receive_launch", nullable = true)
    public Integer getDevReceiveLaunch() {
        return devReceiveLaunch;
    }

    public void setDevReceiveLaunch(Integer devReceiveLaunch) {
        this.devReceiveLaunch = devReceiveLaunch;
    }

    @Basic
    @Column(name = "dev_modulation", nullable = true)
    public Integer getDevModulation() {
        return devModulation;
    }

    public void setDevModulation(Integer devModulation) {
        this.devModulation = devModulation;
    }

    @Basic
    @Column(name = "dev_freq_optional", nullable = true, length = -1)
    public String getDevFreqOptional() {
        return devFreqOptional;
    }

    public void setDevFreqOptional(String devFreqOptional) {
        this.devFreqOptional = devFreqOptional;
    }

    @Basic
    @Column(name = "dev_freq_FH_low", nullable = true, length = -1)
    public String getDevFreqFhLow() {
        return devFreqFhLow;
    }

    public void setDevFreqFhLow(String devFreqFhLow) {
        this.devFreqFhLow = devFreqFhLow;
    }

    @Basic
    @Column(name = "dev_freq_FH_mid", nullable = true, length = -1)
    public String getDevFreqFhMid() {
        return devFreqFhMid;
    }

    public void setDevFreqFhMid(String devFreqFhMid) {
        this.devFreqFhMid = devFreqFhMid;
    }

    @Basic
    @Column(name = "dev_freq_FH_high", nullable = true, length = -1)
    public String getDevFreqFhHigh() {
        return devFreqFhHigh;
    }

    public void setDevFreqFhHigh(String devFreqFhHigh) {
        this.devFreqFhHigh = devFreqFhHigh;
    }

    @Basic
    @Column(name = "dev_freq_DSSS", nullable = true, length = -1)
    public String getDevFreqDsss() {
        return devFreqDsss;
    }

    public void setDevFreqDsss(String devFreqDsss) {
        this.devFreqDsss = devFreqDsss;
    }

    @Basic
    @Column(name = "dev_CE101", nullable = true, length = -1)
    public String getDevCe101() {
        return devCe101;
    }

    public void setDevCe101(String devCe101) {
        this.devCe101 = devCe101;
    }

    @Basic
    @Column(name = "dev_CE102", nullable = true, length = -1)
    public String getDevCe102() {
        return devCe102;
    }

    public void setDevCe102(String devCe102) {
        this.devCe102 = devCe102;
    }

    @Basic
    @Column(name = "dev_CE106", nullable = true, length = -1)
    public String getDevCe106() {
        return devCe106;
    }

    public void setDevCe106(String devCe106) {
        this.devCe106 = devCe106;
    }

    @Basic
    @Column(name = "dev_CE107", nullable = true, length = -1)
    public String getDevCe107() {
        return devCe107;
    }

    public void setDevCe107(String devCe107) {
        this.devCe107 = devCe107;
    }

    @Basic
    @Column(name = "dev_CS101", nullable = true, length = -1)
    public String getDevCs101() {
        return devCs101;
    }

    public void setDevCs101(String devCs101) {
        this.devCs101 = devCs101;
    }

    @Basic
    @Column(name = "dev_CS102", nullable = true, length = -1)
    public String getDevCs102() {
        return devCs102;
    }

    public void setDevCs102(String devCs102) {
        this.devCs102 = devCs102;
    }

    @Basic
    @Column(name = "dev_CS103", nullable = true, length = -1)
    public String getDevCs103() {
        return devCs103;
    }

    public void setDevCs103(String devCs103) {
        this.devCs103 = devCs103;
    }

    @Basic
    @Column(name = "dev_CS104", nullable = true, length = -1)
    public String getDevCs104() {
        return devCs104;
    }

    public void setDevCs104(String devCs104) {
        this.devCs104 = devCs104;
    }

    @Basic
    @Column(name = "dev_CS105", nullable = true, length = -1)
    public String getDevCs105() {
        return devCs105;
    }

    public void setDevCs105(String devCs105) {
        this.devCs105 = devCs105;
    }

    @Basic
    @Column(name = "dev_CS106", nullable = true, length = -1)
    public String getDevCs106() {
        return devCs106;
    }

    public void setDevCs106(String devCs106) {
        this.devCs106 = devCs106;
    }

    @Basic
    @Column(name = "dev_CS109", nullable = true, length = -1)
    public String getDevCs109() {
        return devCs109;
    }

    public void setDevCs109(String devCs109) {
        this.devCs109 = devCs109;
    }

    @Basic
    @Column(name = "dev_CS112", nullable = true, length = -1)
    public String getDevCs112() {
        return devCs112;
    }

    public void setDevCs112(String devCs112) {
        this.devCs112 = devCs112;
    }

    @Basic
    @Column(name = "dev_CS114", nullable = true, length = -1)
    public String getDevCs114() {
        return devCs114;
    }

    public void setDevCs114(String devCs114) {
        this.devCs114 = devCs114;
    }

    @Basic
    @Column(name = "dev_CS115", nullable = true, length = -1)
    public String getDevCs115() {
        return devCs115;
    }

    public void setDevCs115(String devCs115) {
        this.devCs115 = devCs115;
    }

    @Basic
    @Column(name = "dev_CS116", nullable = true, length = -1)
    public String getDevCs116() {
        return devCs116;
    }

    public void setDevCs116(String devCs116) {
        this.devCs116 = devCs116;
    }

    @Basic
    @Column(name = "dev_RE101", nullable = true, length = -1)
    public String getDevRe101() {
        return devRe101;
    }

    public void setDevRe101(String devRe101) {
        this.devRe101 = devRe101;
    }

    @Basic
    @Column(name = "dev_RE102", nullable = true, length = -1)
    public String getDevRe102() {
        return devRe102;
    }

    public void setDevRe102(String devRe102) {
        this.devRe102 = devRe102;
    }

    @Basic
    @Column(name = "dev_RE103", nullable = true, length = -1)
    public String getDevRe103() {
        return devRe103;
    }

    public void setDevRe103(String devRe103) {
        this.devRe103 = devRe103;
    }

    @Basic
    @Column(name = "dev_RS101", nullable = true, length = -1)
    public String getDevRs101() {
        return devRs101;
    }

    public void setDevRs101(String devRs101) {
        this.devRs101 = devRs101;
    }

    @Basic
    @Column(name = "dev_RS103", nullable = true, length = -1)
    public String getDevRs103() {
        return devRs103;
    }

    public void setDevRs103(String devRs103) {
        this.devRs103 = devRs103;
    }

    @Basic
    @Column(name = "project_list", nullable = true, length = -1)
    public String getProjectList() {
        return projectList;
    }

    public void setProjectList(String projectList) {
        this.projectList = projectList;
    }

    @Basic
    @Column(name = "dev_RS105", nullable = true, length = -1)
    public String getDevRs105() {
        return devRs105;
    }

    public void setDevRs105(String devRs105) {
        this.devRs105 = devRs105;
    }

    @Basic
    @Column(name = "dev_advice_proofread", nullable = true, length = -1)
    public String getDevAdviceProofread() {
        return devAdviceProofread;
    }

    public void setDevAdviceProofread(String devAdviceProofread) {
        this.devAdviceProofread = devAdviceProofread;
    }

    @Basic
    @Column(name = "dev_advice_audit", nullable = true, length = -1)
    public String getDevAdviceAudit() {
        return devAdviceAudit;
    }

    public void setDevAdviceAudit(String devAdviceAudit) {
        this.devAdviceAudit = devAdviceAudit;
    }

    @Basic
    @Column(name = "dev_advice_authorize", nullable = true, length = -1)
    public String getDevAdviceAuthorize() {
        return devAdviceAuthorize;
    }

    public void setDevAdviceAuthorize(String devAdviceAuthorize) {
        this.devAdviceAuthorize = devAdviceAuthorize;
    }

    @Basic
    @Column(name = "dev_create_time", nullable = false)
    public Timestamp getDevCreateTime() {
        return devCreateTime;
    }

    public void setDevCreateTime(Timestamp devCreateTime) {
        this.devCreateTime = devCreateTime;
    }

    @Basic
    @Column(name = "dev_new_time", nullable = true)
    public Timestamp getDevNewTime() {
        return devNewTime;
    }

    public void setDevNewTime(Timestamp devNewTime) {
        this.devNewTime = devNewTime;
    }

    @Basic
    @Column(name = "dev_modify_time", nullable = true)
    public Timestamp getDevModifyTime() {
        return devModifyTime;
    }

    public void setDevModifyTime(Timestamp devModifyTime) {
        this.devModifyTime = devModifyTime;
    }

    @Basic
    @Column(name = "dev_proofread_time", nullable = true)
    public Timestamp getDevProofreadTime() {
        return devProofreadTime;
    }

    public void setDevProofreadTime(Timestamp devProofreadTime) {
        this.devProofreadTime = devProofreadTime;
    }

    @Basic
    @Column(name = "dev_audit_time", nullable = true)
    public Timestamp getDevAuditTime() {
        return devAuditTime;
    }

    public void setDevAuditTime(Timestamp devAuditTime) {
        this.devAuditTime = devAuditTime;
    }

    @Basic
    @Column(name = "dev_authorize_time", nullable = true)
    public Timestamp getDevAuthorizeTime() {
        return devAuthorizeTime;
    }

    public void setDevAuthorizeTime(Timestamp devAuthorizeTime) {
        this.devAuthorizeTime = devAuthorizeTime;
    }

    @Basic
    @Column(name = "dev_update_time", nullable = false)
    public Timestamp getDevUpdateTime() {
        return devUpdateTime;
    }

    public void setDevUpdateTime(Timestamp devUpdateTime) {
        this.devUpdateTime = devUpdateTime;
    }

    @Basic
    @Column(name = "dev_opeator", nullable = true)
    public Integer getDevOpeator() {
        return devOpeator;
    }

    public void setDevOpeator(Integer devOpeator) {
        this.devOpeator = devOpeator;
    }

    @Basic
    @Column(name = "dev_status", nullable = true)
    public Integer getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(Integer devStatus) {
        this.devStatus = devStatus;
    }

    @Basic
    @Column(name = "dev_fre_select", nullable = true)
    public Integer getDevFreSelect() {
        return devFreSelect;
    }

    public void setDevFreSelect(Integer devFreSelect) {
        this.devFreSelect = devFreSelect;
    }

    @Basic
    @Column(name = "dev_subsys_source", nullable = true)
    public String getDevSubsysSource() {
        return devSubsysSource;
    }

    public void setDevSubsysSource(String devSubsysSource) {
        this.devSubsysSource = devSubsysSource;
    }

    @Basic
    @Column(name = "dev_subsys_com_ref", nullable = true)
    public String getDevSubsysComRef() {
        return devSubsysComRef;
    }

    public void setDevSubsysComRef(String devSubsysComRef) {
        this.devSubsysComRef = devSubsysComRef;
    }

    @Basic
    @Column(name = "dev_subsys_quantity", nullable = true)
    public String getDevSubsysQuantity() {
        return devSubsysQuantity;
    }


    public void setDevSubsysQuantity(String devSubsysQuantity) {
        this.devSubsysQuantity = devSubsysQuantity;
    }

    @Basic
    @Column(name = "dev_subsys_environment", nullable = true)
    public String getDevSubsysEnvironmrnt() {
        return devSubsysEnvironment;
    }

    public void setDevSubsysEnvironmrnt(String devSubsysEnvironmrnt) {
        this.devSubsysEnvironment = devSubsysEnvironmrnt;
    }
    @Column(name = "dev_powername", nullable = true)
    public String getDevPowername() {
        return devPowername;
    }

    public void setDevPowername(String devPowername) {
        this.devPowername = devPowername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManageSysDevelopEntity that = (ManageSysDevelopEntity) o;

        if (devId != that.devId) return false;
        if (devItemid != null ? !devItemid.equals(that.devItemid) : that.devItemid != null) return false;
        if (devName != null ? !devName.equals(that.devName) : that.devName != null) return false;
        if (devSubsysEqp != null ? !devSubsysEqp.equals(that.devSubsysEqp) : that.devSubsysEqp != null) return false;
        if (devSubsysEqpName != null ? !devSubsysEqpName.equals(that.devSubsysEqpName) : that.devSubsysEqpName != null)
            return false;
        if (devSubsysEqpModel != null ? !devSubsysEqpModel.equals(that.devSubsysEqpModel) : that.devSubsysEqpModel != null)
            return false;
        if (devSubsysEqpNum != null ? !devSubsysEqpNum.equals(that.devSubsysEqpNum) : that.devSubsysEqpNum != null)
            return false;
        if (devSupplier != null ? !devSupplier.equals(that.devSupplier) : that.devSupplier != null) return false;
        if (devPrimaryPlatform != null ? !devPrimaryPlatform.equals(that.devPrimaryPlatform) : that.devPrimaryPlatform != null)
            return false;
        if (devSecondaryPlatform != null ? !devSecondaryPlatform.equals(that.devSecondaryPlatform) : that.devSecondaryPlatform != null)
            return false;
        if (devAttribute != null ? !devAttribute.equals(that.devAttribute) : that.devAttribute != null) return false;
        if (devKey != null ? !devKey.equals(that.devKey) : that.devKey != null) return false;
        if (devInstall != null ? !devInstall.equals(that.devInstall) : that.devInstall != null) return false;
        if (devGnd != null ? !devGnd.equals(that.devGnd) : that.devGnd != null) return false;
        if (devSpecial != null ? !devSpecial.equals(that.devSpecial) : that.devSpecial != null) return false;
        if (devInterport != null ? !devInterport.equals(that.devInterport) : that.devInterport != null) return false;
        if (devLowsensitive != null ? !devLowsensitive.equals(that.devLowsensitive) : that.devLowsensitive != null)
            return false;
        if (devEmp != null ? !devEmp.equals(that.devEmp) : that.devEmp != null) return false;
        if (devStatic != null ? !devStatic.equals(that.devStatic) : that.devStatic != null) return false;
        if (devPowerport != null ? !devPowerport.equals(that.devPowerport) : that.devPowerport != null) return false;
        if (devPowersupply != null ? !devPowersupply.equals(that.devPowersupply) : that.devPowersupply != null)
            return false;
        if (devVoltage != null ? !devVoltage.equals(that.devVoltage) : that.devVoltage != null) return false;
        if (devVoltagenum != null ? !devVoltagenum.equals(that.devVoltagenum) : that.devVoltagenum != null)
            return false;
        if (devAntenna != null ? !devAntenna.equals(that.devAntenna) : that.devAntenna != null) return false;
        if (devReceiveLaunch != null ? !devReceiveLaunch.equals(that.devReceiveLaunch) : that.devReceiveLaunch != null)
            return false;
        if (devModulation != null ? !devModulation.equals(that.devModulation) : that.devModulation != null)
            return false;
        if (devFreqOptional != null ? !devFreqOptional.equals(that.devFreqOptional) : that.devFreqOptional != null)
            return false;
        if (devFreqFhLow != null ? !devFreqFhLow.equals(that.devFreqFhLow) : that.devFreqFhLow != null) return false;
        if (devFreqFhMid != null ? !devFreqFhMid.equals(that.devFreqFhMid) : that.devFreqFhMid != null) return false;
        if (devFreqFhHigh != null ? !devFreqFhHigh.equals(that.devFreqFhHigh) : that.devFreqFhHigh != null)
            return false;
        if (devFreqDsss != null ? !devFreqDsss.equals(that.devFreqDsss) : that.devFreqDsss != null) return false;
        if (devCe101 != null ? !devCe101.equals(that.devCe101) : that.devCe101 != null) return false;
        if (devCe102 != null ? !devCe102.equals(that.devCe102) : that.devCe102 != null) return false;
        if (devCe106 != null ? !devCe106.equals(that.devCe106) : that.devCe106 != null) return false;
        if (devCe107 != null ? !devCe107.equals(that.devCe107) : that.devCe107 != null) return false;
        if (devCs101 != null ? !devCs101.equals(that.devCs101) : that.devCs101 != null) return false;
        if (devCs102 != null ? !devCs102.equals(that.devCs102) : that.devCs102 != null) return false;
        if (devCs103 != null ? !devCs103.equals(that.devCs103) : that.devCs103 != null) return false;
        if (devCs104 != null ? !devCs104.equals(that.devCs104) : that.devCs104 != null) return false;
        if (devCs105 != null ? !devCs105.equals(that.devCs105) : that.devCs105 != null) return false;
        if (devCs106 != null ? !devCs106.equals(that.devCs106) : that.devCs106 != null) return false;
        if (devCs109 != null ? !devCs109.equals(that.devCs109) : that.devCs109 != null) return false;
        if (devCs112 != null ? !devCs112.equals(that.devCs112) : that.devCs112 != null) return false;
        if (devCs114 != null ? !devCs114.equals(that.devCs114) : that.devCs114 != null) return false;
        if (devCs115 != null ? !devCs115.equals(that.devCs115) : that.devCs115 != null) return false;
        if (devCs116 != null ? !devCs116.equals(that.devCs116) : that.devCs116 != null) return false;
        if (devRe101 != null ? !devRe101.equals(that.devRe101) : that.devRe101 != null) return false;
        if (devRe102 != null ? !devRe102.equals(that.devRe102) : that.devRe102 != null) return false;
        if (devRe103 != null ? !devRe103.equals(that.devRe103) : that.devRe103 != null) return false;
        if (devRs101 != null ? !devRs101.equals(that.devRs101) : that.devRs101 != null) return false;
        if (devRs103 != null ? !devRs103.equals(that.devRs103) : that.devRs103 != null) return false;
        if (devRs105 != null ? !devRs105.equals(that.devRs105) : that.devRs105 != null) return false;
        if (devAdviceProofread != null ? !devAdviceProofread.equals(that.devAdviceProofread) : that.devAdviceProofread != null)
            return false;
        if (devAdviceAudit != null ? !devAdviceAudit.equals(that.devAdviceAudit) : that.devAdviceAudit != null)
            return false;
        if (devAdviceAuthorize != null ? !devAdviceAuthorize.equals(that.devAdviceAuthorize) : that.devAdviceAuthorize != null)
            return false;
        if (devCreateTime != null ? !devCreateTime.equals(that.devCreateTime) : that.devCreateTime != null)
            return false;
        if (devNewTime != null ? !devNewTime.equals(that.devNewTime) : that.devNewTime != null) return false;
        if (devModifyTime != null ? !devModifyTime.equals(that.devModifyTime) : that.devModifyTime != null)
            return false;
        if (devProofreadTime != null ? !devProofreadTime.equals(that.devProofreadTime) : that.devProofreadTime != null)
            return false;
        if (devAuditTime != null ? !devAuditTime.equals(that.devAuditTime) : that.devAuditTime != null) return false;
        if (devAuthorizeTime != null ? !devAuthorizeTime.equals(that.devAuthorizeTime) : that.devAuthorizeTime != null)
            return false;
        if (devUpdateTime != null ? !devUpdateTime.equals(that.devUpdateTime) : that.devUpdateTime != null)
            return false;
        if (devOpeator != null ? !devOpeator.equals(that.devOpeator) : that.devOpeator != null) return false;
        if (devStatus != null ? !devStatus.equals(that.devStatus) : that.devStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = devId;
        result = 31 * result + (devItemid != null ? devItemid.hashCode() : 0);
        result = 31 * result + (devName != null ? devName.hashCode() : 0);
        result = 31 * result + (devSubsysEqp != null ? devSubsysEqp.hashCode() : 0);
        result = 31 * result + (devSubsysEqpName != null ? devSubsysEqpName.hashCode() : 0);
        result = 31 * result + (devSubsysEqpModel != null ? devSubsysEqpModel.hashCode() : 0);
        result = 31 * result + (devSubsysEqpNum != null ? devSubsysEqpNum.hashCode() : 0);
        result = 31 * result + (devSupplier != null ? devSupplier.hashCode() : 0);
        result = 31 * result + (devPrimaryPlatform != null ? devPrimaryPlatform.hashCode() : 0);
        result = 31 * result + (devSecondaryPlatform != null ? devSecondaryPlatform.hashCode() : 0);
        result = 31 * result + (devAttribute != null ? devAttribute.hashCode() : 0);
        result = 31 * result + (devKey != null ? devKey.hashCode() : 0);
        result = 31 * result + (devInstall != null ? devInstall.hashCode() : 0);
        result = 31 * result + (devGnd != null ? devGnd.hashCode() : 0);
        result = 31 * result + (devSpecial != null ? devSpecial.hashCode() : 0);
        result = 31 * result + (devInterport != null ? devInterport.hashCode() : 0);
        result = 31 * result + (devLowsensitive != null ? devLowsensitive.hashCode() : 0);
        result = 31 * result + (devEmp != null ? devEmp.hashCode() : 0);
        result = 31 * result + (devStatic != null ? devStatic.hashCode() : 0);
        result = 31 * result + (devPowerport != null ? devPowerport.hashCode() : 0);
        result = 31 * result + (devPowersupply != null ? devPowersupply.hashCode() : 0);
        result = 31 * result + (devVoltage != null ? devVoltage.hashCode() : 0);
        result = 31 * result + (devVoltagenum != null ? devVoltagenum.hashCode() : 0);
        result = 31 * result + (devAntenna != null ? devAntenna.hashCode() : 0);
        result = 31 * result + (devReceiveLaunch != null ? devReceiveLaunch.hashCode() : 0);
        result = 31 * result + (devModulation != null ? devModulation.hashCode() : 0);
        result = 31 * result + (devFreqOptional != null ? devFreqOptional.hashCode() : 0);
        result = 31 * result + (devFreqFhLow != null ? devFreqFhLow.hashCode() : 0);
        result = 31 * result + (devFreqFhMid != null ? devFreqFhMid.hashCode() : 0);
        result = 31 * result + (devFreqFhHigh != null ? devFreqFhHigh.hashCode() : 0);
        result = 31 * result + (devFreqDsss != null ? devFreqDsss.hashCode() : 0);
        result = 31 * result + (devCe101 != null ? devCe101.hashCode() : 0);
        result = 31 * result + (devCe102 != null ? devCe102.hashCode() : 0);
        result = 31 * result + (devCe106 != null ? devCe106.hashCode() : 0);
        result = 31 * result + (devCe107 != null ? devCe107.hashCode() : 0);
        result = 31 * result + (devCs101 != null ? devCs101.hashCode() : 0);
        result = 31 * result + (devCs102 != null ? devCs102.hashCode() : 0);
        result = 31 * result + (devCs103 != null ? devCs103.hashCode() : 0);
        result = 31 * result + (devCs104 != null ? devCs104.hashCode() : 0);
        result = 31 * result + (devCs105 != null ? devCs105.hashCode() : 0);
        result = 31 * result + (devCs106 != null ? devCs106.hashCode() : 0);
        result = 31 * result + (devCs109 != null ? devCs109.hashCode() : 0);
        result = 31 * result + (devCs112 != null ? devCs112.hashCode() : 0);
        result = 31 * result + (devCs114 != null ? devCs114.hashCode() : 0);
        result = 31 * result + (devCs115 != null ? devCs115.hashCode() : 0);
        result = 31 * result + (devCs116 != null ? devCs116.hashCode() : 0);
        result = 31 * result + (devRe101 != null ? devRe101.hashCode() : 0);
        result = 31 * result + (devRe102 != null ? devRe102.hashCode() : 0);
        result = 31 * result + (devRe103 != null ? devRe103.hashCode() : 0);
        result = 31 * result + (devRs101 != null ? devRs101.hashCode() : 0);
        result = 31 * result + (devRs103 != null ? devRs103.hashCode() : 0);
        result = 31 * result + (devRs105 != null ? devRs105.hashCode() : 0);
        result = 31 * result + (devAdviceProofread != null ? devAdviceProofread.hashCode() : 0);
        result = 31 * result + (devAdviceAudit != null ? devAdviceAudit.hashCode() : 0);
        result = 31 * result + (devAdviceAuthorize != null ? devAdviceAuthorize.hashCode() : 0);
        result = 31 * result + (devCreateTime != null ? devCreateTime.hashCode() : 0);
        result = 31 * result + (devNewTime != null ? devNewTime.hashCode() : 0);
        result = 31 * result + (devModifyTime != null ? devModifyTime.hashCode() : 0);
        result = 31 * result + (devProofreadTime != null ? devProofreadTime.hashCode() : 0);
        result = 31 * result + (devAuditTime != null ? devAuditTime.hashCode() : 0);
        result = 31 * result + (devAuthorizeTime != null ? devAuthorizeTime.hashCode() : 0);
        result = 31 * result + (devUpdateTime != null ? devUpdateTime.hashCode() : 0);
        result = 31 * result + (devOpeator != null ? devOpeator.hashCode() : 0);
        result = 31 * result + (devStatus != null ? devStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ManageSysDevelopEntity{" +
                "devId=" + devId +
                ", devItemid='" + devItemid + '\'' +
                ", devName='" + devName + '\'' +
                ", devSubsysEqp=" + devSubsysEqp +
                ", devSubsysEqpName='" + devSubsysEqpName + '\'' +
                ", devSubsysEqpModel='" + devSubsysEqpModel + '\'' +
                ", devSubsysEqpNum='" + devSubsysEqpNum + '\'' +
                ", devSupplier='" + devSupplier + '\'' +
                ", devPrimaryPlatform=" + devPrimaryPlatform +
                ", devSecondaryPlatform=" + devSecondaryPlatform +
                ", devAttribute=" + devAttribute +
                ", devKey=" + devKey +
                ", devInstall=" + devInstall +
                ", devGnd=" + devGnd +
                ", devSpecial=" + devSpecial +
                ", devInterport=" + devInterport +
                ", devLowsensitive=" + devLowsensitive +
                ", devEmp=" + devEmp +
                ", devStatic=" + devStatic +
                ", devPowerport='" + devPowerport + '\'' +
                ", devPowersupply='" + devPowersupply + '\'' +
                ", devVoltage='" + devVoltage + '\'' +
                ", devVoltagenum='" + devVoltagenum + '\'' +
                ", devAntenna=" + devAntenna +
                ", devReceiveLaunch=" + devReceiveLaunch +
                ", devModulation=" + devModulation +
                ", devFreqOptional='" + devFreqOptional + '\'' +
                ", devFreqFhLow='" + devFreqFhLow + '\'' +
                ", devFreqFhMid='" + devFreqFhMid + '\'' +
                ", devFreqFhHigh='" + devFreqFhHigh + '\'' +
                ", devFreqDsss='" + devFreqDsss + '\'' +
                ", devCe101='" + devCe101 + '\'' +
                ", devCe102='" + devCe102 + '\'' +
                ", devCe106='" + devCe106 + '\'' +
                ", devCe107='" + devCe107 + '\'' +
                ", devCs101='" + devCs101 + '\'' +
                ", devCs102='" + devCs102 + '\'' +
                ", devCs103='" + devCs103 + '\'' +
                ", devCs104='" + devCs104 + '\'' +
                ", devCs105='" + devCs105 + '\'' +
                ", devCs106='" + devCs106 + '\'' +
                ", devCs109='" + devCs109 + '\'' +
                ", devCs112='" + devCs112 + '\'' +
                ", devCs114='" + devCs114 + '\'' +
                ", devCs115='" + devCs115 + '\'' +
                ", devCs116='" + devCs116 + '\'' +
                ", devRe101='" + devRe101 + '\'' +
                ", devRe102='" + devRe102 + '\'' +
                ", devRe103='" + devRe103 + '\'' +
                ", devRs101='" + devRs101 + '\'' +
                ", devRs103='" + devRs103 + '\'' +
                ", devRs105='" + devRs105 + '\'' +
                ", devAdviceProofread='" + devAdviceProofread + '\'' +
                ", devAdviceAudit='" + devAdviceAudit + '\'' +
                ", devAdviceAuthorize='" + devAdviceAuthorize + '\'' +
                ", devCreateTime=" + devCreateTime +
                ", devNewTime=" + devNewTime +
                ", devModifyTime=" + devModifyTime +
                ", devProofreadTime=" + devProofreadTime +
                ", devAuditTime=" + devAuditTime +
                ", devAuthorizeTime=" + devAuthorizeTime +
                ", devUpdateTime=" + devUpdateTime +
                ", devOpeator=" + devOpeator +
                ", devStatus=" + devStatus +
                '}';
    }
}
