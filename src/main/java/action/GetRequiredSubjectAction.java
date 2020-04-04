package action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang.StringUtils;
import utils.BaseResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;

public class GetRequiredSubjectAction extends ActionSupport {
    private int primary_platform;
    private int secondary_platform;
    private String power_port;
    private String power_supply;
    private String voltage;
    private String voltage_num;
    private int special_equipment;
    private int antenna_removal;
    private int receive_launch;
    private int anolitude_modulation;
    private int ground_line;
    private int static_electricity;
    private int interconnected_port;
    private int emp_reinforce;
    private int key_equipment;
    private int install_mode;
    private int low_frequency_sensitive;
    private int attribute;
    private String optList;

    private static final String CE101 = "CE101";
    private static final String CE102 = "CE102";
    private static final String CE106 = "CE106";
    private static final String CE107 = "CE107";
    private static final String CS101 = "CS101";
    private static final String CS102 = "CS102";
    private static final String CS103 = "CS103";
    private static final String CS104 = "CS104";
    private static final String CS105 = "CS105";
    private static final String CS106 = "CS106";
    private static final String CS109 = "CS109";
    private static final String CS112 = "CS112";
    private static final String CS114 = "CS114";
    private static final String CS115 = "CS115";
    private static final String CS116 = "CS116";
    private static final String RE101 = "RE101";
    private static final String RE102 = "RE102";
    private static final String RE103 = "RE103";
    private static final String RS101 = "RS101";
    private static final String RS103 = "RS103";
    private static final String RS105 = "RS105";
    private String[] projectList = {
            CE101, CE102, CE106, CE107, CS101, CS102, CS103, CS104, CS105, CS106,
            CS109, CS112, CS114, CS115, CS116, RE101, RE102, RE103, RS101, RS103,
            RS105
    };

    private ArrayList<String> standardProject = new ArrayList<>();
    private ArrayList<String> protocolProject = new ArrayList<>();
    private ArrayList<String> extraProject = new ArrayList<>();
    private HashMap<String, ArrayList<String>> allProject = new HashMap<>();
    private BaseResponse<HashMap<String, ArrayList<String>>> requiredSubjectResponse = new BaseResponse<>();

    public String getSubject() throws Exception {
        System.out.println(">>> getSubject called");
        System.out.println(this.toString());
        System.out.println(">>> info print over");
        System.out.println("attribute:"+this.attribute);

        JSONArray power_port_list = (JSONArray) JSON.parse(power_port);
        JSONArray power_supply_list = (JSONArray) JSON.parse(power_supply);
        JSONArray voltage_list = (JSONArray) JSON.parse(voltage);
        JSONArray voltage_num_list = (JSONArray) JSON.parse(voltage_num);
        JSONArray opt_list = (JSONArray) JSON.parse(optList);
        this.generateStandardProject(power_port_list, power_supply_list, opt_list);
        this.generateProtocolProject(power_port_list, power_supply_list, opt_list);
        this.generateExtraProject();

        this.allProject.put("standard", this.standardProject);
        this.allProject.put("protocol", this.protocolProject);
        this.allProject.put("extra", this.extraProject);

        System.out.println(this.allProject);

        requiredSubjectResponse.setMessage("测试一下");
        requiredSubjectResponse.setStatus("success");
        requiredSubjectResponse.setData(this.allProject);
        return "success";
    }

    /**
     *
     * @param power_port_list
     * @param power_supply_list
     */
    // 判断所需要测试的标准项目，并将名称写入
    private void generateStandardProject(JSONArray power_port_list, JSONArray power_supply_list, JSONArray opt_list) {
        int optListLength = opt_list.size();
        String string1 = "1";
        String string2 = "2";
        String string3 = "3";
        for(int i=0; i<power_port_list.size();i++) {
            switch (this.primary_platform) {
                // 水面舰船
                case 1:
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CE101);
                        this.standardProject.add(CE102);
                    }
                    if(this.attribute == 1) {
//                        if (antenna_removal == 1) {
//                            this.standardProject.add(CE106);
//                        }
                        for(int j=0; j<optListLength; j++){
                            if(string1.equals(opt_list.getJSONObject(j).getString("opt_install_mode"))){
                                this.standardProject.add(CE106);
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CS101);
                        this.standardProject.add(CS106);
                    }
                    if (ground_line == 1 && low_frequency_sensitive == 1) {
                        this.standardProject.add(CS102);
                    }
//                    if (power_supply_list.get(i).equals("1")) {
//                        this.standardProject.add(CS106);
//                    }
                    if (special_equipment == 1 && install_mode != 5) {
                        this.standardProject.add(CS109);
                    }
                    if (static_electricity == 1) {
                        this.standardProject.add(CS112);
                    }
                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
                        this.standardProject.add(CS114);
                        this.standardProject.add(CS116);
                    }
                    this.standardProject.add(RE101);
                    this.standardProject.add(RE102);
//                    if (antenna_removal == 2 && (receive_launch == 1 || receive_launch == 3)) {
//                        this.standardProject.add(RE103);
//                    }
                    for(int j=0; j<optListLength; j++){
                        JSONObject optSingle = opt_list.getJSONObject(j);
                        if(string2.equals(optSingle.getString("opt_install_mode")) && (string1.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style")))){
                            this.standardProject.add(RE103);
                            break;
                        }
                    }
                    this.standardProject.add(RS101);
                    this.standardProject.add(RS103);
                    if (emp_reinforce == 0) {
                        this.standardProject.add(RS105);
                    }
                    break;
                case 2:
                    // 潜艇
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CE101);
                        this.standardProject.add(CE102);
                    }
//                    if (antenna_removal == 1) {
//                        this.standardProject.add(CE106);
//                    }
                    if(this.attribute == 1) {
                        for (int j = 0; j < optListLength; j++) {
                            if (string1.equals(opt_list.getJSONObject(j).getString("opt_install_mode"))) {
                                this.standardProject.add(CE106);
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CS101);
                        this.standardProject.add(CS106);
                    }
                    if (ground_line == 1 && low_frequency_sensitive == 1) {
                        this.standardProject.add(CS102);
                    }
//                    if (power_supply_list.get(i).equals("1")) {
//                        this.standardProject.add(CS106);
//                    }
                    if (special_equipment == 1 && install_mode != 5) {
                        this.standardProject.add(CS109);
                    }
                    if (static_electricity == 1) {
                        this.standardProject.add(CS112);
                    }
                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
                        this.standardProject.add(CS114);
                        this.standardProject.add(CS116);
                    }
                    this.standardProject.add(RE101);
                    this.standardProject.add(RE102);
//                    if (antenna_removal == 2 && (receive_launch == 1 || receive_launch == 3)) {
//                        this.standardProject.add(RE103);
//                    }
                    for(int j=0; j<optListLength; j++){
                        JSONObject optSingle = opt_list.getJSONObject(j);
                        if(string2.equals(optSingle.getString("opt_install_mode")) && (string1.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style")))){
                            this.standardProject.add(RE103);
                            break;
                        }
                    }
                    this.standardProject.add(RS101);
                    this.standardProject.add(RS103);
                    if (emp_reinforce == 0 ) {
                        this.standardProject.add(RS105);
                    }
                    break;
                case 3:
                    // 陆军飞机
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CE101);
                        this.standardProject.add(CE102);
                    }
//                    if (antenna_removal == 1) {
//                        this.standardProject.add(CE106);
//                    }
                    if(this.attribute == 1) {
                        for (int j = 0; j < optListLength; j++) {
                            if (string1.equals(opt_list.getJSONObject(j).getString("opt_install_mode"))) {
                                this.standardProject.add(CE106);
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CS101);
                    }
                    if (static_electricity == 1) {
                        this.standardProject.add(CS112);
                    }
                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
                        this.standardProject.add(CS114);
                        this.standardProject.add(CS115);
                        this.standardProject.add(CS116);
                    }
                    this.standardProject.add(RE101);
                    this.standardProject.add(RE102);
//                    if (antenna_removal == 2 && (receive_launch == 1 || receive_launch == 3)) {
//                        this.standardProject.add(RE103);
//                    }
                    for(int j=0; j<optListLength; j++){
                        JSONObject optSingle = opt_list.getJSONObject(j);
                        if(string2.equals(optSingle.getString("opt_install_mode")) && (string1.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style")))){
                            this.standardProject.add(RE103);
                            break;
                        }
                    }
                    this.standardProject.add(RS101);
                    this.standardProject.add(RS103);
                    if (emp_reinforce == 0 && key_equipment == 1) {
                        this.standardProject.add(RS105);
                    }
                    break;
                case 4:
                    // 海军飞机
                    if (power_port_list.get(i).equals("1") && special_equipment == 3) {
                        this.standardProject.add(CE101);
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CE102);
                    }
//                    if (antenna_removal == 1) {
//                        this.standardProject.add(CE106);
//                    }
                    if(this.attribute == 1) {
                        for (int j = 0; j < optListLength; j++) {
                            if (string1.equals(opt_list.getJSONObject(j).getString("opt_install_mode"))) {
                                this.standardProject.add(CE106);
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CS101);
                    }
                    if (static_electricity == 1) {
                        this.standardProject.add(CS112);
                    }
                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
                        this.standardProject.add(CS114);
                        this.standardProject.add(CS115);
                        this.standardProject.add(CS116);
                    }
                    if (special_equipment == 3) {
                        this.standardProject.add(RE101);
                    }
                    this.standardProject.add(RE102);
//                    if (antenna_removal == 2 && (receive_launch == 1 || receive_launch == 3)) {
//                        this.standardProject.add(RE103);
//                    }
                    for(int j=0; j<optListLength; j++){
                        JSONObject optSingle = opt_list.getJSONObject(j);
                        if(string2.equals(optSingle.getString("opt_install_mode")) && (string1.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style")))){
                            this.standardProject.add(RE103);
                            break;
                        }
                    }
                    if (special_equipment == 3) {
                        this.standardProject.add(RS101);
                    }
                    this.standardProject.add(RS103);
                    if (emp_reinforce == 0 ) {
                        this.standardProject.add(RS105);
                    }
                    break;
                case 5:
                    // 空军飞机
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CE102);
                    }
//                    if (antenna_removal == 1) {
//                        this.standardProject.add(CE106);
//                    }
                    if(this.attribute == 1) {
                        for (int j = 0; j < optListLength; j++) {
                            if (string1.equals(opt_list.getJSONObject(j).getString("opt_install_mode"))) {
                                this.standardProject.add(CE106);
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CS101);
                    }
                    if (static_electricity == 1) {
                        this.standardProject.add(CS112);
                    }
                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
                        this.standardProject.add(CS114);
                        this.standardProject.add(CS115);
                        this.standardProject.add(CS116);
                    }
                    this.standardProject.add(RE102);
//                    if (antenna_removal == 2 && (receive_launch == 1 || receive_launch == 3)) {
//                        this.standardProject.add(RE103);
//                    }
                    for(int j=0; j<optListLength; j++){
                        JSONObject optSingle = opt_list.getJSONObject(j);
                        if(string2.equals(optSingle.getString("opt_install_mode")) && (string1.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style")))){
                            this.standardProject.add(RE103);
                            break;
                        }
                    }
                    if (special_equipment == 2) {
                        this.standardProject.add(RS101);
                    }
                    this.standardProject.add(RS103);
                    break;
                case 6:
                    // 空间系统
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CE102);
                    }
//                    if (antenna_removal == 1) {
//                        this.standardProject.add(CE106);
//                    }
                    if(this.attribute == 1) {
                        for (int j = 0; j < optListLength; j++) {
                            if (string1.equals(opt_list.getJSONObject(j).getString("opt_install_mode"))) {
                                this.standardProject.add(CE106);
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CS101);
                    }
                    if (static_electricity == 1) {
                        this.standardProject.add(CS112);
                    }
                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
                        this.standardProject.add(CS114);
                        this.standardProject.add(CS115);
                        this.standardProject.add(CS116);
                    }
                    this.standardProject.add(RE102);
//                    if (antenna_removal == 2 && (receive_launch == 1 || receive_launch == 3)) {
//                        this.standardProject.add(RE103);
//                    }
                    for(int j=0; j<optListLength; j++){
                        JSONObject optSingle = opt_list.getJSONObject(j);
                        if(string2.equals(optSingle.getString("opt_install_mode")) && (string1.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style")))){
                            this.standardProject.add(RE103);
                            break;
                        }
                    }
                    this.standardProject.add(RS103);
                    break;
                case 7:
                    // 陆军地面
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CE102);
                    }
//                    if (antenna_removal == 1) {
//                        this.standardProject.add(CE106);
//                    }
                    if(this.attribute == 1) {
                        for (int j = 0; j < optListLength; j++) {
                            if (string1.equals(opt_list.getJSONObject(j).getString("opt_install_mode"))) {
                                this.standardProject.add(CE106);
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CS101);
                    }
                    if (static_electricity == 1) {
                        this.standardProject.add(CS112);
                    }
                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
                        this.standardProject.add(CS114);
                        this.standardProject.add(CS115);
                        this.standardProject.add(CS116);
                    }
                    this.standardProject.add(RE102);
//                    if (antenna_removal == 2 && (receive_launch == 1 || receive_launch == 3)) {
//                        this.standardProject.add(RE103);
//                    }
                    for(int j=0; j<optListLength; j++){
                        JSONObject optSingle = opt_list.getJSONObject(j);
                        if(string2.equals(optSingle.getString("opt_install_mode")) && (string1.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style")))){
                            this.standardProject.add(RE103);
                            break;
                        }
                    }
                    if(special_equipment == 2){
                        this.standardProject.add(RS101);
                    }
                    this.standardProject.add(RS103);
                    break;
                case 8:
                    // 海军地面
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CE102);
                    }
//                    if (antenna_removal == 1) {
//                        this.standardProject.add(CE106);
//                    }
                    if(this.attribute == 1) {
                        for (int j = 0; j < optListLength; j++) {
                            if (string1.equals(opt_list.getJSONObject(j).getString("opt_install_mode"))) {
                                this.standardProject.add(CE106);
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CS101);
                    }
                    if (static_electricity == 1) {
                        this.standardProject.add(CS112);
                    }
                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
                        this.standardProject.add(CS114);
                        this.standardProject.add(CS115);
                        this.standardProject.add(CS116);
                    }
                    this.standardProject.add(RE102);
//                    if (antenna_removal == 2 && (receive_launch == 1 || receive_launch == 3)) {
//                        this.standardProject.add(RE103);
//                    }
                    for(int j=0; j<optListLength; j++){
                        JSONObject optSingle = opt_list.getJSONObject(j);
                        if(string2.equals(optSingle.getString("opt_install_mode")) && (string1.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style")))){
                            this.standardProject.add(RE103);
                            break;
                        }
                    }
                    this.standardProject.add(RS101);
                    this.standardProject.add(RS103);
                    if (emp_reinforce == 0 ) {
                        this.standardProject.add(RS105);
                    }
                    break;
                case 9:
                    // 空军地面
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CE102);
                    }
//                    if (antenna_removal == 1) {
//                        this.standardProject.add(CE106);
//                    }
                    if(this.attribute == 1) {
                        for (int j = 0; j < optListLength; j++) {
                            if (string1.equals(opt_list.getJSONObject(j).getString("opt_install_mode"))) {
                                this.standardProject.add(CE106);
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.standardProject.add(CS101);
                    }
                    if (static_electricity == 1) {
                        this.standardProject.add(CS112);
                    }
                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
                        this.standardProject.add(CS114);
                        this.standardProject.add(CS115);
                        this.standardProject.add(CS116);
                    }
                    this.standardProject.add(RE102);
//                    if (antenna_removal == 2 && (receive_launch == 1 || receive_launch == 3)) {
//                        this.standardProject.add(RE103);
//                    }
                    for(int j=0; j<optListLength; j++){
                        JSONObject optSingle = opt_list.getJSONObject(j);
                        if(string2.equals(optSingle.getString("opt_install_mode")) && (string1.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style")))){
                            this.standardProject.add(RE103);
                            break;
                        }
                    }
                    this.standardProject.add(RS103);
                    break;
            }
        }
        //去除重复
        standardProject  = new ArrayList<String>(new HashSet<String>(standardProject));
        //列表进行排序
        Collections.sort(standardProject);


    }

    // 判断所需要测试的合同项目，并将名称写入
    private void generateProtocolProject(JSONArray power_port_list, JSONArray power_supply_list, JSONArray opt_list) {
        int optListLength = opt_list.size();
        String string1 = "1";
        String string2 = "2";
        String string3 = "3";
        for(int i=0 ; i<power_port_list.size(); i++) {
            switch (this.primary_platform) {
                // 水面舰船
                case 1:
                    if (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2")) {
                        this.protocolProject.add(CE107);
                    }
                    if(attribute == 1) {
//                        if (receive_launch == 2 || receive_launch == 3) {
//                            this.protocolProject.add(CS103);
//                            this.protocolProject.add(CS104);
//                        }
//                        if ((receive_launch == 2 || receive_launch == 3) && anolitude_modulation == 1) {
//                            this.protocolProject.add(CS105);
//                        }
                        for(int j=0; j<optListLength; j++){
                            JSONObject optSingle = opt_list.getJSONObject(j);
                            if(string2.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style"))){
                                this.protocolProject.add(CS103);
                                this.protocolProject.add(CS104);
                                if(string1.equals(optSingle.getString("opt_modulation_mode"))){
                                    this.protocolProject.add(CS105);
                                }
                                break;
                            }
                        }

                    }
                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
                        this.protocolProject.add(CS115);
                    }
                    break;
                case 2:
                    if (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2")) {
                        this.protocolProject.add(CE107);
                    }
                    if(attribute == 1) {
                        for(int j=0; j<optListLength; j++){
                            JSONObject optSingle = opt_list.getJSONObject(j);
                            if(string2.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style"))){
                                this.protocolProject.add(CS103);
                                this.protocolProject.add(CS104);
                                if(string1.equals(optSingle.getString("opt_modulation_mode"))){
                                    this.protocolProject.add(CS105);
                                }
                                break;
                            }
                        }
                    }
                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
                        this.protocolProject.add(CS115);
                    }
                    break;
                case 3:
                    if (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2")) {
                        this.protocolProject.add(CE107);
                    }
                    if (ground_line == 1) {
                        this.protocolProject.add(CS102);
                    }
                    if(attribute == 1) {
                        for(int j=0; j<optListLength; j++){
                            JSONObject optSingle = opt_list.getJSONObject(j);
                            if(string2.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style"))){
                                this.protocolProject.add(CS103);
                                this.protocolProject.add(CS104);
                                if(string1.equals(optSingle.getString("opt_modulation_mode"))){
                                    this.protocolProject.add(CS105);
                                }
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.protocolProject.add(CS106);
                    }
//                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
//                        this.protocolProject.add(CS115);
//                    }
                    break;
                case 4:
                    if (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2")) {
                        this.protocolProject.add(CE107);
                    }
                    if (ground_line == 1) {
                        this.protocolProject.add(CS102);
                    }
                    if(attribute == 1) {
                        for(int j=0; j<optListLength; j++){
                            JSONObject optSingle = opt_list.getJSONObject(j);
                            if(string2.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style"))){
                                this.protocolProject.add(CS103);
                                this.protocolProject.add(CS104);
                                if(string1.equals(optSingle.getString("opt_modulation_mode"))){
                                    this.protocolProject.add(CS105);
                                }
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.protocolProject.add(CS106);
                    }
//                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
//                        this.protocolProject.add(CS115);
//                    }
                    break;
                case 5:
                    if (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2")) {
                        this.protocolProject.add(CE107);
                    }
                    if (ground_line == 1) {
                        this.protocolProject.add(CS102);
                    }
                    if(attribute == 1) {
                        for(int j=0; j<optListLength; j++){
                            JSONObject optSingle = opt_list.getJSONObject(j);
                            if(string2.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style"))){
                                this.protocolProject.add(CS103);
                                this.protocolProject.add(CS104);
                                if(string1.equals(optSingle.getString("opt_modulation_mode"))){
                                    this.protocolProject.add(CS105);
                                }
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.protocolProject.add(CS106);
                    }
//                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
//                        this.protocolProject.add(CS115);
//                    }
                    break;
                case 6:
                    if (power_port_list.get(i).equals("1")) {
                        this.protocolProject.add(CE101);
                    }
                    if (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2")) {
                        this.protocolProject.add(CE107);
                    }
                    if (ground_line == 1) {
                        this.protocolProject.add(CS102);
                    }
                    if(attribute == 1) {
                        for(int j=0; j<optListLength; j++){
                            JSONObject optSingle = opt_list.getJSONObject(j);
                            if(string2.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style"))){
                                this.protocolProject.add(CS103);
                                this.protocolProject.add(CS104);
                                if(string1.equals(optSingle.getString("opt_modulation_mode"))){
                                    this.protocolProject.add(CS105);
                                }
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.protocolProject.add(CS106);
                    }
//                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
//                        this.protocolProject.add(CS115);
//                    }
                    this.protocolProject.add(RE101);
                    this.protocolProject.add(RS101);
                    this.protocolProject.add(RS105);
                    break;
                case 7:
                    if (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2")) {
                        this.protocolProject.add(CE107);
                    }
                    if (ground_line == 1) {
                        this.protocolProject.add(CS102);
                    }
                    if(attribute == 1) {
                        for(int j=0; j<optListLength; j++){
                            JSONObject optSingle = opt_list.getJSONObject(j);
                            if(string2.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style"))){
                                this.protocolProject.add(CS103);
                                this.protocolProject.add(CS104);
                                if(string1.equals(optSingle.getString("opt_modulation_mode"))){
                                    this.protocolProject.add(CS105);
                                }
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.protocolProject.add(CS106);
                    }
//                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
//                        this.protocolProject.add(CS115);
//                    }
                    break;
                case 8:
                    if (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2")) {
                        this.protocolProject.add(CE107);
                    }
                    if (ground_line == 1) {
                        this.protocolProject.add(CS102);
                    }
                    if(attribute == 1) {
                        for(int j=0; j<optListLength; j++){
                            JSONObject optSingle = opt_list.getJSONObject(j);
                            if(string2.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style"))){
                                this.protocolProject.add(CS103);
                                this.protocolProject.add(CS104);
                                if(string1.equals(optSingle.getString("opt_modulation_mode"))){
                                    this.protocolProject.add(CS105);
                                }
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.protocolProject.add(CS106);
                    }
//                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
//                        this.protocolProject.add(CS115);
//                    }
                    break;
                case 9:
                    if (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2")) {
                        this.protocolProject.add(CE107);
                    }
                    if (ground_line == 1) {
                        this.protocolProject.add(CS102);
                    }
                    if(attribute == 1) {
                        for(int j=0; j<optListLength; j++){
                            JSONObject optSingle = opt_list.getJSONObject(j);
                            if(string2.equals(optSingle.getString("opt_work_style")) || string3.equals(optSingle.getString("opt_work_style"))){
                                this.protocolProject.add(CS103);
                                this.protocolProject.add(CS104);
                                if(string1.equals(optSingle.getString("opt_modulation_mode"))){
                                    this.protocolProject.add(CS105);
                                }
                                break;
                            }
                        }
                    }
                    if (power_port_list.get(i).equals("1")) {
                        this.protocolProject.add(CS106);
                    }
//                    if (interconnected_port == 1 && (power_port_list.get(i).equals("1") || power_port_list.get(i).equals("2"))) {
//                        this.protocolProject.add(CS115);
//                    }
                    break;
            }
        }
        protocolProject  = new ArrayList<String>(new HashSet<String>(protocolProject));
        Collections.sort(protocolProject);
    }

    // 将剩下的项目信息写入
    private void generateExtraProject(){
        for (String project: this.projectList){
            if ((!standardProject.contains(project)) && !protocolProject.contains(project)) {
                this.extraProject.add(project);
            }
        }
    }

    // 将标准项目和合同项目打包返回前端
    private String responsePacker() {
        String standardStr = StringUtils.join(this.standardProject, ",");
        String protocolStr = StringUtils.join(this.protocolProject, ",");
        String extraStr = StringUtils.join(this.extraProject, ",");
        String res = standardStr + "-" + protocolStr + "-" + extraStr;
        return res;
    }


    public int getPrimary_platform() {
        return primary_platform;
    }

    public void setPrimary_platform(int primary_platform) {
        this.primary_platform = primary_platform;
    }

    public int getSecondary_platform() {
        return secondary_platform;
    }

    public void setSecondary_platform(int secondary_platform) {
        this.secondary_platform = secondary_platform;
    }

    public String getPower_port() {
        return power_port;
    }

    public void setPower_port(String power_port) {
        this.power_port = power_port;
    }

    public String getPower_supply() {
        return power_supply;
    }

    public void setPower_supply(String power_supply) {
        this.power_supply = power_supply;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getVoltage_num() {
        return voltage_num;
    }

    public void setVoltage_num(String voltage_num) {
        this.voltage_num = voltage_num;
    }

    public int getSpecial_equipment() {
        return special_equipment;
    }

    public void setSpecial_equipment(int special_equipment) {
        this.special_equipment = special_equipment;
    }

    public int getAntenna_removal() {
        return antenna_removal;
    }

    public void setAntenna_removal(int antenna_removal) {
        this.antenna_removal = antenna_removal;
    }

    public int getReceive_launch() {
        return receive_launch;
    }

    public void setReceive_launch(int receive_launch) {
        this.receive_launch = receive_launch;
    }

    public int getAnolitude_modulation() {
        return anolitude_modulation;
    }

    public void setAnolitude_modulation(int anolitude_modulation) {
        this.anolitude_modulation = anolitude_modulation;
    }

    public int getGround_line() {
        return ground_line;
    }

    public void setGround_line(int ground_line) {
        this.ground_line = ground_line;
    }

    public int getStatic_electricity() {
        return static_electricity;
    }

    public void setStatic_electricity(int static_electricity) {
        this.static_electricity = static_electricity;
    }

    public int getInterconnected_port() {
        return interconnected_port;
    }

    public void setInterconnected_port(int interconnected_port) {
        this.interconnected_port = interconnected_port;
    }

    public int getEmp_reinforce() {
        return emp_reinforce;
    }

    public void setEmp_reinforce(int emp_reinforce) {
        this.emp_reinforce = emp_reinforce;
    }

    public int getKey_equipment() {
        return key_equipment;
    }

    public void setKey_equipment(int key_equipment) {
        this.key_equipment = key_equipment;
    }

    public int getInstall_mode() {
        return install_mode;
    }

    public void setInstall_mode(int install_mode) {
        this.install_mode = install_mode;
    }

    public int getLow_frequency_sensitive() {
        return low_frequency_sensitive;
    }

    public void setLow_frequency_sensitive(int low_frequency_sensitive) {
        this.low_frequency_sensitive = low_frequency_sensitive;
    }

    public  int getAttribute(){ return  attribute; }

    public void setAttribute(int attribute){ this.attribute = attribute; }

    public String getOptList() {return optList;}

    public void setOptList(String optList) { this.optList = optList; }

    @Override
    public String toString() {
        return "GetRequiredSubjectAction{" +
                "primary_platform=" + primary_platform +
                ", secondary_platform=" + secondary_platform +
                ", power_port=" + power_port +
                ", power_supply=" + power_supply +
                ", voltage=" + voltage +
                ", voltage_num=" + voltage_num +
                ", special_equipment=" + special_equipment +
                ", antenna_removal=" + antenna_removal +
                ", receive_launch=" + receive_launch +
                ", anolitude_modulation=" + anolitude_modulation +
                ", ground_line=" + ground_line +
                ", static_electricity=" + static_electricity +
                ", interconnected_port=" + interconnected_port +
                ", emp_reinforce=" + emp_reinforce +
                ", key_equipment=" + key_equipment +
                ", install_mode=" + install_mode +
                ", low_frequency_sensitive=" + low_frequency_sensitive +
                ", attribute=" + attribute +
                '}';
    }

    public BaseResponse<HashMap<String, ArrayList<String>>> getRequiredSubjectResponse() {
        return requiredSubjectResponse;
    }

    public void setRequiredSubjectResponse(BaseResponse<HashMap<String, ArrayList<String>>> requiredSubjectResponse) {
        this.requiredSubjectResponse = requiredSubjectResponse;
    }
}
