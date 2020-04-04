package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import entity.StandardLimitValueEntity;
import entity.StandardLimitValuePic;
import org.apache.commons.lang.StringUtils;
import utils.Base64Utils;
import utils.BaseResponse;
import utils.ConfigUtils;

import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashSet;
import consts.PathStoreEnum;

/**
 * Created by ddgdd on 2018/9/10 0010 11:27
 */
public class StandardLimitValuesService {

    public static BaseResponse<List<StandardLimitValuePic>> getLimitValuesPic(StandardLimitValueEntity stdLimValEntity, int devId, String devName) {
        BaseResponse<List<StandardLimitValuePic>> stdLimitValuesResponse = new BaseResponse<List<StandardLimitValuePic>>();
        Map<String, String> dataMap = new HashMap<>();
//        String picNum = "";
        List<StandardLimitValuePic> picList = new ArrayList<>();
        List<String> picNum = new ArrayList<>();
        stdLimitValuesResponse.setStatus("success");
        int secondaryPlatform = -1;
        switch (stdLimValEntity.getProjectId()) {
            case "CE101":
                secondaryPlatform = stdLimValEntity.getSecondaryPlatform();
                JSONArray voltage = (JSONArray) JSON.parse(stdLimValEntity.getVoltage());
                JSONArray voltage_num = (JSONArray) JSON.parse(stdLimValEntity.getVoltageNum());
//                voltage = deduplication(voltage);
//                voltage_num = deduplication(voltage_num);
                System.out.println("voltage:"+voltage.size());
                System.out.println("voltage_num:"+voltage_num.size());
                for(int i=0;i<voltage.size();i++) {
                    if (secondaryPlatform >= 21 && secondaryPlatform <= 26) {  //潜艇
                        if (voltage.getString(i).equals("1")) {
                            picNum.add("8");
                            //                        stdLimitValuesResponse.setData("8");
                        } else if (voltage.getString(i).equals("2")) {
                            picNum.add("9");
                            //                        stdLimitaluesResponse.setData("9");
                        } else if (voltage.getString(i).equals("3")) {
                            picNum.add("10");
                            //                        stdLimitValuesResponse.setData("10");
                        }
                    } else if (secondaryPlatform >= 11 && secondaryPlatform <= 18) {  //水面舰船
                        if (voltage.getString(i).equals("2")) {
                            picNum.add("9");
                            //                        stdLimitValuesResponse.setData("9");
                        } else if (voltage.getString(i).equals("3")) {
                            picNum.add("10");
                            //                        stdLimitValuesResponse.setData("10");
                        } else if (voltage.getString(i).equals("1")) {  //水面舰船直流先用图8
                            picNum.add("8");
                        }
                    } else if ((secondaryPlatform >= 31 && secondaryPlatform <= 35) ||
                            (secondaryPlatform >= 61 && secondaryPlatform <= 63) ||
//                            ((secondaryPlatform >= 41 && secondaryPlatform <= 45) && stdLimValEntity.getSpecialEquipment() == 3)) {  //海军ASW飞机、陆军飞机、空间系统
                              (secondaryPlatform >= 41 && secondaryPlatform <= 45)) {  //海军ASW飞机、陆军飞机、空间系统
                        if (voltage_num.getString(i).equals("1")) {
                            picNum.add("11_2");
                            //                        stdLimitValuesResponse.setData("11-1");
                        } else if (voltage_num.getString(i).equals("2")) {
                            picNum.add("11_1");
                            //                        stdLimitValuesResponse.setData("11-2");
                        }
                    }
                }
                break;
            case "CE102":
                picNum.add("14");
                break;
            case "CS109":
                picNum.add("37");
                break;
            case "RE101":
                secondaryPlatform = stdLimValEntity.getSecondaryPlatform();
                if (secondaryPlatform == 11 || secondaryPlatform == 13 || secondaryPlatform == 15 || secondaryPlatform == 17 ||
                        secondaryPlatform == 21 || secondaryPlatform == 23 || secondaryPlatform == 25 ||
                        (secondaryPlatform >= 41 && secondaryPlatform <= 45)) {
                    picNum.add("52");
                } else if (secondaryPlatform == 12 || secondaryPlatform == 14 || secondaryPlatform == 16 || secondaryPlatform == 18 ||
                        secondaryPlatform == 22 || secondaryPlatform == 24 || secondaryPlatform == 26 ||
                        (secondaryPlatform >= 31 && secondaryPlatform <= 35) || (secondaryPlatform >= 61 && secondaryPlatform <= 63)) {
                    picNum.add("51");
                }
                break;
            case "RE102":
                secondaryPlatform = stdLimValEntity.getSecondaryPlatform();
                if (secondaryPlatform >= 11 && secondaryPlatform <= 14) {
                    picNum.add("55_2");
                } else if (secondaryPlatform >= 15 && secondaryPlatform <= 18) {
                    picNum.add("55_1");
                } else if (secondaryPlatform >= 23 && secondaryPlatform <= 24) {
                    picNum.add("56_1");
                } else if ((secondaryPlatform >= 21 && secondaryPlatform <= 22) ||
                        (secondaryPlatform >= 25 && secondaryPlatform <= 26)) {
                    picNum.add("56_2");
                } else if (secondaryPlatform == 41) {
                    picNum.add("57_4");
                } else if ((secondaryPlatform >= 31 && secondaryPlatform <= 33) || (secondaryPlatform >= 42 && secondaryPlatform <= 43) || (secondaryPlatform >= 51 && secondaryPlatform <= 53) || secondaryPlatform == 61 ) {
                    picNum.add("57_3");
                } else if (secondaryPlatform == 34 || secondaryPlatform == 44 || secondaryPlatform == 54 || secondaryPlatform == 62) {
                    picNum.add("57_1");
                } else if (secondaryPlatform == 35 || secondaryPlatform == 45 || secondaryPlatform == 55 || secondaryPlatform == 63) {
                    picNum.add("57_2");
                } else if (secondaryPlatform == 91 || secondaryPlatform == 92 || secondaryPlatform == 81) {
                    picNum.add("58_1");
                } else if (secondaryPlatform == 71 || secondaryPlatform == 72 || secondaryPlatform == 82) {
                    picNum.add("58_2");
                }
                break;
            case "RS101":
                secondaryPlatform = stdLimValEntity.getSecondaryPlatform();
                if (secondaryPlatform == 11 || secondaryPlatform == 13 || secondaryPlatform == 15 || secondaryPlatform == 17 ||
                        secondaryPlatform == 21 || secondaryPlatform == 23 || secondaryPlatform == 25 ||
                        (secondaryPlatform >= 41 && secondaryPlatform <= 45) ||
                        (secondaryPlatform >= 81 && secondaryPlatform <= 82)) {
                    picNum.add("65");
                } else if (secondaryPlatform == 12 || secondaryPlatform == 14 || secondaryPlatform == 16 || secondaryPlatform == 18 ||
                        secondaryPlatform == 22 || secondaryPlatform == 24 || secondaryPlatform == 26 ||
                        (secondaryPlatform >= 31 && secondaryPlatform <= 35) || (secondaryPlatform >= 61 && secondaryPlatform <= 63) ||
                (secondaryPlatform >= 71 && secondaryPlatform <= 72)) {
                    picNum.add("66");
                }
                break;
            case "RS105":
                secondaryPlatform = stdLimValEntity.getSecondaryPlatform();
                if ((secondaryPlatform >= 11 && secondaryPlatform <= 18) ||
                        (secondaryPlatform >= 21 && secondaryPlatform <= 26) ||
                        (secondaryPlatform >= 31 && secondaryPlatform <= 35) ||
                        (secondaryPlatform >= 41 && secondaryPlatform <= 45) ||
                        (secondaryPlatform >= 81 && secondaryPlatform <= 82)) {
                    picNum.add("73");
                }
                break;
            case "CS115":
                picNum.add("44");
                break;
            case "CS116":
                picNum.add("48");
                break;
            case "RS103":
                secondaryPlatform = stdLimValEntity.getSecondaryPlatform();
                if ((secondaryPlatform >= 11 && secondaryPlatform <= 14) ||
                        secondaryPlatform == 21) {
                    picNum.add("RS103_1");
                } else if (secondaryPlatform == 22) {
                    picNum.add("RS103_1_1");
                } else if ((secondaryPlatform >= 15 && secondaryPlatform <= 16) ||
                        secondaryPlatform == 25 ) {
                    picNum.add("RS103_2");
                } else if (secondaryPlatform == 26) {
                    picNum.add("RS103_2_1");
                } else if (secondaryPlatform >= 17 && secondaryPlatform <= 18) {
                    picNum.add("RS103_3");
                } else if (secondaryPlatform == 23 ) {
                    picNum.add("RS103_4");
                } else if (secondaryPlatform == 24 ) {
                    picNum.add("RS103_4_1");
                } else if (secondaryPlatform >= 51 && secondaryPlatform <= 52) {
                    picNum.add("RS103_5");
                } else if (secondaryPlatform >= 31 && secondaryPlatform <= 35) {
                    picNum.add("RS103_5_1");
                } else if (secondaryPlatform >= 41 && secondaryPlatform <= 42) {
                    picNum.add("RS103_5_2");
                } else if (secondaryPlatform >= 43 && secondaryPlatform <= 45) {
                    picNum.add("RS103_6");
                } else if (secondaryPlatform >= 53 && secondaryPlatform <= 55) {
                    picNum.add("RS103_7");
                } else if (secondaryPlatform >= 61 && secondaryPlatform <= 63) {
                    picNum.add("RS103_8");
                } else if (secondaryPlatform >= 71 && secondaryPlatform <= 72) {
                    picNum.add("RS103_9");
                } else if ((secondaryPlatform >= 81 && secondaryPlatform <= 82)) {
                    picNum.add("RS103_10");
                } else if (secondaryPlatform >= 91 && secondaryPlatform <= 92) {
                    picNum.add("RS103_10_1");
                }

                break;
            case "CS114":
                secondaryPlatform = stdLimValEntity.getSecondaryPlatform();
                JSONArray power_supply = (JSONArray) JSON.parse(stdLimValEntity.getPowerSupply());
                if (secondaryPlatform == 11 || secondaryPlatform == 13 || secondaryPlatform == 21) {
                    picNum.add("39_2");
                } else if (secondaryPlatform == 12 || secondaryPlatform == 14 || secondaryPlatform == 22) {
                    picNum.add("39_2");
                } else if (secondaryPlatform == 15 || secondaryPlatform == 25) {
                    picNum.add("39_4");
                } else if ((secondaryPlatform == 16 || secondaryPlatform == 26) ||
                        (secondaryPlatform >= 81 && secondaryPlatform <= 82) ||
                        (secondaryPlatform >= 91 && secondaryPlatform <= 92)) {
                    picNum.add("39_4");
                } else if (secondaryPlatform == 17) {
                    picNum.add("39_6");
                } else if (secondaryPlatform == 18) {
                    picNum.add("39_6");
                } else if (secondaryPlatform == 23) {
                    picNum.add("39_8");
                } else if (secondaryPlatform == 24) {
                    picNum.add("39_8");
                } else if ((secondaryPlatform >= 31 && secondaryPlatform <= 35) ||
                        (secondaryPlatform >= 51 && secondaryPlatform <= 52)) {
                    picNum.add("39_9");
                } else if (secondaryPlatform >= 41 && secondaryPlatform <= 42) {
                    picNum.add("39_10");
                } else if (secondaryPlatform >= 43 && secondaryPlatform <= 45) {
                    picNum.add("39_11");
                } else if ((secondaryPlatform >= 53 && secondaryPlatform <= 55) ||
                        (secondaryPlatform >= 61 && secondaryPlatform <= 63)) {
                    picNum.add("39_12");
                } else if (secondaryPlatform >= 71 && secondaryPlatform <= 72) {
                    picNum.add("39_13");
                }
                break;
            default:
                stdLimitValuesResponse.setStatus("error");
                stdLimitValuesResponse.setMessage("不存在此项目");

                return stdLimitValuesResponse;

        }
        picNum = removeDuplicate(picNum);
        if(picNum.size() != 0) {
            for (int j = 0; j < picNum.size(); j++) {
                if (!StringUtils.isEmpty(picNum.get(j))) {
                    int stdValueChange = 0;
                    if (stdLimValEntity.getImgNumNow() != null) {
                        JSONArray imgNumNow = (JSONArray) JSON.parse(stdLimValEntity.getImgNumNow());
                        if (StringUtils.equals(imgNumNow.getString(j), picNum.get(j))) {
                            stdValueChange = 1;
                        }
                    }

                    //            dataMap = getImgDetails(picNum, stdValueChange, stdLimValEntity.getProjectId());
                    //            stdLimitValuesResponse.setData(dataMap);
                    int value = j + 1;
                    String projectId = stdLimValEntity.getProjectId();
                    if (stdLimValEntity.getProjectId().equals("CE101")) {
                        projectId = value + "_" + stdLimValEntity.getProjectId();
                        System.out.println("projectId:" + projectId);
                    }
                    StandardLimitValuePic stdPic = getImgDetails(picNum.get(j), stdValueChange, projectId, devId, devName);
                    picList.add(stdPic);
                    //            stdLimitValuesResponse.setData(stdPic);
                } else {
                    stdLimitValuesResponse.setStatus("error");
                    stdLimitValuesResponse.setMessage("不满足任何标准限值条件");
                    return stdLimitValuesResponse;
                }
            }

//        if (StringUtils.isEmpty(picNum)) {
//            stdLimitValuesResponse.setStatus("error");
//            stdLimitValuesResponse.setMessage("不满足任何标准限值条件");
//        } else {
//            int stdValueChange = 0;
//            if (StringUtils.equals(stdLimValEntity.getImgNumNow(), picNum)) {
//                stdValueChange = 1;
//            }
//
//            dataMap = getImgDetails(picNum, stdValueChange, stdLimValEntity.getProjectId());
//            stdLimitValuesResponse.setData(dataMap);
//        }
            stdLimitValuesResponse.setData(picList);
        }else{
            stdLimitValuesResponse.setStatus("error");
            stdLimitValuesResponse.setMessage("不满足任何标准限值条件");
        }
        return stdLimitValuesResponse;
    }

    public static BaseResponse<HashMap<Integer,List<StandardLimitValuePic>>> getLimitValuesBiPic(StandardLimitValueEntity stdLimValEntity, String picOneNow, String picTwoNow, int devId, String devName) {
        BaseResponse<HashMap<Integer,List<StandardLimitValuePic>>> biPicBaseResponse = new BaseResponse<>();
        biPicBaseResponse.setStatus("success");
        HashMap<Integer,List<StandardLimitValuePic>> limitValuePicsMap = new HashMap<>();
//        List<StandardLimitValuePic> limitValuePics = new ArrayList<>();
        List<String> picNum1 = new ArrayList<>();
        List<String> picNum2 = new ArrayList<>();
        JSONArray voltageNum = (JSONArray) JSON.parse(stdLimValEntity.getVoltageNum());
        voltageNum = deduplication(voltageNum);
        for(int i=0; i<voltageNum.size();i++) {
            switch (stdLimValEntity.getProjectId()) {
                case "CS101":
                    //                int voltageNum = stdLimValEntity.getVoltageNum();
                    if (voltageNum.getString(i).equals("1")) {
                        picNum1.add("21_2");
                        picNum2.add("22");
                    } else if (voltageNum.getString(i).equals("2")) {
                        picNum1.add("21_1");
                        picNum2.add("22");
                    }
                    break;
                default:
                    biPicBaseResponse.setStatus("error");
                    biPicBaseResponse.setMessage("不存在此项目");

                    return biPicBaseResponse;

            }
        }
        picNum1 = removeDuplicate(picNum1);
        if(picNum1.size() != 0) {
            for (int j = 0; j < picNum1.size(); j++) {
                List<StandardLimitValuePic> limitValuePics = new ArrayList<>();
                if ((!StringUtils.isEmpty(picNum1.get(j))) && (!StringUtils.isEmpty(picNum2.get(j)))) {
                    int stdValueChange = 0;
                    if (picOneNow != null) {
                        JSONArray picOneNowList = (JSONArray) JSON.parse(picOneNow);
                        if (StringUtils.equals(picOneNowList.getString(j), picNum1.get(j))) {
                            stdValueChange = 1;
                        }
                    }
                    int value = j + 1;
                    String projectId = value + "_" + stdLimValEntity.getProjectId();
                    StandardLimitValuePic stdPic1 = getImgDetails(picNum1.get(j), stdValueChange, projectId.concat("_1"), devId, devName);
                    limitValuePics.add(stdPic1);

                    stdValueChange = 0;
                    if (picTwoNow != null) {
                        JSONArray picTwoNowList = (JSONArray) JSON.parse(picTwoNow);
                        if (StringUtils.equals(picTwoNowList.getString(j), picNum2.get(j))) {
                            stdValueChange = 1;
                        }
                    }
                    StandardLimitValuePic stdPic2 = getImgDetails(picNum2.get(j), stdValueChange, projectId.concat("_2"), devId, devName);
                    limitValuePics.add(stdPic2);
                    limitValuePicsMap.put(j + 1, limitValuePics);
                    //            biPicBaseResponse.setData(limitValuePics);
                } else {
                    biPicBaseResponse.setStatus("error");
                    biPicBaseResponse.setMessage("不满足任何标准限值条件");
                    return biPicBaseResponse;
                }
            }

            biPicBaseResponse.setData(limitValuePicsMap);
        }else{
            biPicBaseResponse.setStatus("error");
            biPicBaseResponse.setMessage("不满足任何标准限值条件");
            return biPicBaseResponse;
        }

        return biPicBaseResponse;
    }

    public static BaseResponse<List<Map<String, String>>> getLimitValuesText(StandardLimitValueEntity stdLimValEntity, int receiveLaunchCE106) {
        BaseResponse<List<Map<String, String>>> stdLimitValuesResponse = new BaseResponse<List<Map<String, String>>>();
        Map<String, String> dataMap = new HashMap<>();
        stdLimitValuesResponse.setStatus("success");
        List<String> textNum = new ArrayList<String>();
        List CE107List = new ArrayList();
        List textList = new ArrayList();
        switch (stdLimValEntity.getProjectId()) {
            case "CE106":
//                int receiveLaunch = stdLimValEntity.getReceiveLaunch();
                if (receiveLaunchCE106 == 2) {
                    textNum.add("CE106_a");
                    dataMap.put("textNum", "CE106_a");
                } else if (receiveLaunchCE106 == 1) {
                    textNum.add("CE106_b");
                    textNum.add("CE106_c");
                    dataMap.put("textNum", "CE106_b_c");
                } else if (receiveLaunchCE106 == 3) {
                    textNum.add("CE106_a");
                    textNum.add("CE106_b");
                    textNum.add("CE106_c");
                    dataMap.put("textNum", "CE106_a_b_c");
                }
                break;
            case "CE107":
                JSONArray voltage = (JSONArray) JSON.parse(stdLimValEntity.getVoltage());
                voltage = deduplication(voltage);
                for(int i=0 ; i<voltage.size();i++) {
                    if (voltage.get(i).equals("2") || voltage.get(i).equals("3")) {
                        textNum.add("CE107_1");
//                        dataMap.put("textNum", "CE107_1");
                    } else if (voltage.get(i).equals("1")) {
                        textNum.add("CE107_2");
//                        dataMap.put("textNum", "CE107_2");
                    }
                }
                break;
            case "CS102":
                textNum.add("CS102");
                dataMap.put("textNum", "CS102");
                break;
            case "CS106":
                textNum.add("CS106");
                dataMap.put("textNum", "CS106");
                break;
            case "RE103":
                if (stdLimValEntity.getAntennaRemoval() == 2 && stdLimValEntity.getReceiveLaunch() == 1) {
                    textNum.add("RE103");
                    dataMap.put("textNum", "RE103");
                }else if(stdLimValEntity.getAntennaRemoval() == 2 && stdLimValEntity.getReceiveLaunch() == 3){
                    textNum.add("RE103");
                    dataMap.put("textNum", "RE103");
                }
                break;
            case "CS112":
                int keyEquip = stdLimValEntity.getKeyEquipment();
                int staticElec = stdLimValEntity.getStaticElectricity();
                if (keyEquip == 1 && staticElec == 1) {
                    textNum.add("CS112_10_A");
                    textNum.add("CS112_11_A");
                    dataMap.put("textNum", "CS112_10_11_A");
                } else if (keyEquip == 0 && staticElec == 1) {
                    textNum.add("CS112_10_B");
                    textNum.add("CS112_11_B");
                    dataMap.put("textNum", "CS112_10_11_B");
                }
                break;
            case "CS115":
                textNum.add("CS115");
                dataMap.put("textNum", "CS115");
                break;
            case "CS116":
                textNum.add("CS116");
                dataMap.put("textNum", "CS116");
                break;
            default:
                stdLimitValuesResponse.setStatus("error");
                stdLimitValuesResponse.setMessage("不存在此项目");

                return stdLimitValuesResponse;
        }

        if (!textNum.isEmpty()) {
            StringBuilder textDescription = new StringBuilder();
            if(stdLimValEntity.getProjectId().equals("CE107")==false) {
                for (String index : textNum) {
                    String eachDescription = ConfigUtils.getValueByKey("limitValues.properties", "DESCRIPTION_", index);
//                    if(index.equals("CS116")){
//                        textDescription.append("当按照规定的信号波形和标准限值图规定的峰值电流进行试验时，EUT不应出现任何故障、性能降低或偏离规定的指标值，或超出单个设备或分系统规范中给出的指标允差。至少应在0.01MHz、0.1MHz、1MHz、10MHz、30MHz和100MHz频点上进行测试。如果还有其他已知的可能对安装设备造成影响的频率，例如平台谐振频率，则在这些频率上也要进行测试。测试信号重重复率从不小于0.5个脉冲/秒至不大于1个脉冲/秒。在每个频率点应世家脉冲5min。");
//                        textDescription.append("测试信号重重复率从不小于0.5个脉冲/秒至不大于1个脉冲/秒。在每个频率点应世家脉冲5min。");
//                    }else {
                        textDescription.append(eachDescription);
//                    }

                }
                dataMap.put("textDescription", textDescription.toString());
                textList.add(dataMap);
                stdLimitValuesResponse.setData(textList);
            }else{
                textNum = removeDuplicate(textNum);
                for (String index : textNum) {
                    Map<String, String> CE107Map = new HashMap<>();
                    textDescription.delete(0, textDescription.length());
                    String eachDescription = ConfigUtils.getValueByKey("limitValues.properties", "DESCRIPTION_", index);
                    textDescription.append(eachDescription);
                    CE107Map.put("textDescription", textDescription.toString());
                    CE107Map.put("textNum", index);
                    CE107List.add(CE107Map);
                }
                stdLimitValuesResponse.setData(CE107List);
            }
        } else {
            stdLimitValuesResponse.setStatus("error");
            stdLimitValuesResponse.setMessage("不满足任何标准限值条件");
        }

        return stdLimitValuesResponse;
    }

    private static StandardLimitValuePic
    getImgDetails(String imgNum, int stdValueChange, String projectId, int devId, String devName) {
        Map<String, String> dataMap = new HashMap<>();

        String imgDescription = ConfigUtils.getValueByKey("limitValues.properties", "DESCRIPTION_", imgNum);
        String imgMinX = ConfigUtils.getValueByKey("limitValues.properties", "MINX_", imgNum);
        String imgMaxX = ConfigUtils.getValueByKey("limitValues.properties", "MAXX_", imgNum);
        String imgMinY = ConfigUtils.getValueByKey("limitValues.properties", "MINY_", imgNum);
        String imgMaxY = ConfigUtils.getValueByKey("limitValues.properties", "MAXY_", imgNum);
        String imgAxisX = ConfigUtils.getValueByKey("limitValues.properties", "AXISX_", imgNum);
        String imgAxisY = ConfigUtils.getValueByKey("limitValues.properties", "AXISY_", imgNum);

        StandardLimitValuePic stdPic = new StandardLimitValuePic();
        stdPic.setImgNum(imgNum);
        stdPic.setImgInfo(imgDescription);
        stdPic.setImgMinX(imgMinX);
        stdPic.setImgMaxX(imgMaxX);
        stdPic.setImgMinY(imgMinY);
        stdPic.setImgMaxY(imgMaxY);
        stdPic.setImgAxisX(imgAxisX);
        stdPic.setImgAxisY(imgAxisY);
//        dataMap.put("imgNum", picNum);
//        dataMap.put("imgInfo", imgDescription);
//        dataMap.put("imgMinX", imgMinX);
//        dataMap.put("imgMaxX", imgMaxX);
//        dataMap.put("imgMinY", imgMinY);
//        dataMap.put("imgMaxY", imgMaxY);
//        dataMap.put("imgAxisX", imgAxisX);
//        dataMap.put("imgAxisY", imgAxisY);
//        dataMap.put(picNum, value);

        //要取的图片url
        StringBuilder imgUrl = new StringBuilder();
        if (stdValueChange == 0) {
//            imgUrl.append("d://GJB151B//img//standard");
//            imgUrl.append("img/standard");
            imgUrl.append(PathStoreEnum.WINDOWS_STANDADIMG_PATH.getValue());
            imgUrl.append("standard");
            imgUrl.append(imgNum);
            imgUrl.append(".png");
        } else {
//            imgUrl.append("d://GJB151B//img//");
//            imgUrl.append("img/");
            imgUrl.append(PathStoreEnum.WINDOWS_CHANGEDIMG_SOURTHPATH.getValue());
            imgUrl.append(devName);
            imgUrl.append("_图片//");
            imgUrl.append(String.valueOf(devId));
            imgUrl.append("_");
            imgUrl.append(projectId);
            imgUrl.append(".png");
        }
//        dataMap.put("imgSrc", Base64Utils.getImgStr(imgUrl.toString()));
        stdPic.setImgSrc(Base64Utils.getImgStr(imgUrl.toString()));

        return stdPic;
    }

    private static JSONArray deduplication(JSONArray array){
        JSONArray array_final = new JSONArray();
        for(int i=0; i<array.size();i++){
            int count=0;
            for(int j=0;j<array_final.size();j++){
                if(array.getString(i).equals(array_final.getString(j))){
                    break;
                }
                count++;
            }
            if(count == array_final.size()){
                array_final.add(array.getString(i));
            }
        }
        return array_final;
    }

    private static List<String> removeDuplicate(List<String> list) {
        LinkedHashSet<String> set = new LinkedHashSet<String>(list.size());
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

}
