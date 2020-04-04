package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.AreaBreakType;
import consts.PathStoreEnum;
import dao.SaveNewProjectDao;
import entity.ManageSysDevelopEntity;
import utils.itext7.LeftParagraph;
import utils.itext7.MidParagraph;
import utils.itext7.PageFooter;
import utils.itext7.bodyParagraph;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 为pdf传递限值
 * 类别标识：1 文字 2 双图 3 单图 4 单图+文字 5 特殊处理的项目
 * 最后修改的结果就是直接在这个函数里面进行pdf的填充
 * </p>
 *
 * @Author: teng 870006096@qq.com
 * @Data: Created on 5:48 PM 2019/7/23
 * @Modified By: fcupup
 */

public class GeneratePdfLimitService {
    private static SaveNewProjectDao saveNewProjectDao = SaveNewProjectDao.getInstance();
    private static int devId;
    private static HashMap<String, String> limitNameMap = new HashMap<String, String>(){
        {
            put("8","适用于水面舰船和潜艇的CE101限值（DC）" );
            put("9","适用于水面舰船和潜艇的CE101限值（50Hz）");
            put("10","适用于水面舰船和潜艇的CE101限值（400Hz）");
            put("11_1","适用于海军ASW飞机，陆军飞机（包括机场维护工作区）和空间系统的CE101限值（受试设备额定电源电压（AC和DC）>28V )");
            put("11_2","适用于海军ASW飞机，陆军飞机（包括机场维护工作区）和空间系统的CE101限值（受试设备额定电源电压（AC和DC）<=28V )");
            put("14","CE102限值（AC和DC）");
            put("CE106_a","EUT天线端口传导发射不应超过以下限值（接收）");
            put("CE106_b_c","EUT天线端口传导发射不应超过以下限值（发射）");
            put("CE106_a_b_c","EUT天线端口传导发射不应超过以下限值（收/发）");
            put("CE107_1","随手动或自动开关操作而产生的开关瞬态传导发射不应超过下列值（交流电源线）");
            put("CE107_2","随手动或自动开关操作而产生的开关瞬态传导发射不应超过下列值（直流电源线）");
            put("21_1","CS101电压限值（受试设备额定电源电压 >28V）");
            put("21_2","CS101电压限值（受试设备额定电源电压 <=28V）");
            put("22","CS101功率限值");
            put("CS102","CS102限值");
            put("CS106","CS106限值");
            put("37","CS109限值");
            put("CS112_10_11_A","限值（A类EUT）");
            put("CS112_10_11_B","限值（B类EUT）");
            put("39_1","CS114校验限值");
            put("39_2","CS114校验限值");
            put("39_3","CS114校验限值");
            put("39_4","CS114校验限值");
            put("39_5","CS114校验限值");
            put("39_6","CS114校验限值");
            put("39_7","CS114校验限值");
            put("39_8","CS114校验限值");
            put("39_9","CS114校验限值");
            put("39_10","CS114校验限值");
            put("39_11","CS114校验限值");
            put("39_12","CS114校验限值");
            put("39_13","CS114校验限值");
            put("44","CS115波形");
            put("CS115","CS115限值");
            put("48","CS116限值");
            put("CS116","CS116限值");
            put("51","适用于陆军的RE101限值");
            put("52","适用于海军的RE101限值");
            put("55_1","适用于水面舰船的RE102限值（甲板下）");
            put("55_2","适用于水面舰船的RE102限值（甲板上）");
            put("56_1","适用于潜艇的RE102限值（压力舱内）");
            put("56_2","适用于潜艇的RE102限值（压力舱外）");
            put("57_1","适用于飞机和空间系统的RE102限值（固定翼飞机内部（首尾间距≧25m））");
            put("57_2","适用于飞机和空间系统的RE102限值（固定翼飞机内部（首尾间距<25m））");
            put("57_3","适用于飞机和空间系统的RE102限值（固定翼飞机外部（2MHz～18GHz）和直升机）");
            put("58_1","适用于地面的RE102限值（海军（固定的）和空军）");
            put("58_2","适用于地面的RE102限值（海军（移动的）和陆军）");
            put("RE103","RE103限值");
            put("65","适用于海军的RS101限值");
            put("66","适用于陆军的RS101限值");
            put("RS103_1","RS103限值");
            put("RS103_2","RS103限值");
            put("RS103_3","RS103限值");
            put("RS103_4","RS103限值");
            put("RS103_5","RS103限值");
            put("RS103_6","RS103限值");
            put("RS103_7","RS103限值");
            put("RS103_8","RS103限值");
            put("RS103_9","RS103限值");
            put("RS103_10","RS103限值");
            put("73","RS105限值");
        }
    };

    // 定义全局的字体静态变量
    private static PdfFont titlefont;
    private static PdfFont headfont;
    private static PdfFont keyfont;
    private static PdfFont textfont;
    // 最大宽度
    private static int maxWidth = 520;

    //
    private static int sizeRate = 2;
    // 静态代码块 初始化字体
    static {
        try {
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
            titlefont = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
            headfont = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
            keyfont = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
            textfont = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 设置字体大小
    //一级标题字体大小
    int bigSize = 14;
    //二级标题字体大小
    int midSize = 12;
    //三级标题字体大小
    int smallSize = 11;


    public static JSONObject getProjectLimit(int devId, JSONArray projectList, Document document, Map<String, Integer> catalogs, PdfDocument pdf) throws Exception {
        // 设置中文字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H",true);

        // 设置字体大小
        //一级标题字体大小
        int bigSize = 14;
        //二级标题字体大小
        int midSize = 12;
        //三级标题字体大小
        int smallSize = 11;
        int pageNumber = 5;
        setDevId(devId);
        JSONObject projectLimit = new JSONObject();
        ManageSysDevelopEntity manageSysDevelopEntity = findProjectById(devId);
        int projectLength = projectList.size();
        ArrayList projectsList = new ArrayList();
        for (int i = 0; i < projectLength; i++) {
            projectsList.add(projectList.getString(i));
        }
        String testProjectPrefix = "3.2.";
        int currentPicNumber = 1;
        Collections.sort(projectsList);
        for(int i=0; i<projectLength; i++){
            pageNumber++;
            int projectOrder = i + 1;
            String catalogProjectName = testProjectPrefix + projectOrder;
          switch((String) projectsList.get(i)){
              case "CE101":
                  JSONArray limitCE101 = new JSONArray();
                  JSONObject devCE101 = (JSONObject) JSON.parse(manageSysDevelopEntity.getDevCe101());
                  JSONArray CE101Limit = devCE101.getJSONArray("limit_value");
                  JSONArray CE101LimitCurrent = devCE101.getJSONArray("limit_value_current");
                  for(int j=0; j<3 ;j++){
                      if(!CE101Limit.getJSONObject(j).isEmpty()){
                          String picNum = CE101Limit.getJSONObject(j).getString("pic");
//                          String picCurrent = CE101LimitCurrent.getJSONObject(j).getString("pic");
                          String pic = getLimitPic("CE101", CE101Limit.getJSONObject(j).getString("pic"), CE101LimitCurrent.getJSONObject(j).getString("pic"), j+1);
                          JSONObject singleLimit = new JSONObject();
                          singleLimit.put("category", 3);
                          singleLimit.put("pic", pic);
                          singleLimit.put("title", limitNameMap.get(picNum));
                          limitCE101.add(singleLimit);
                      }
                  }
                  projectLimit.put("CE101", limitCE101);
                  String CE101ValuePicTitle = "图3-" + currentPicNumber + "\t" + limitCE101.getJSONObject(0).getString("title");
                  Paragraph module3_2para2 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CE101", font, smallSize);
                  Paragraph module3_2para3 = new LeftParagraph("试验内容：25Hz~10kHz\ua0a0电源线传导发射", textfont, smallSize);
                  Paragraph module3_2para4 = new LeftParagraph("限值要求：" + "电源线传导发射不应超过图3-" + currentPicNumber + "中的限值要求", font, smallSize);
                  String resCE101PicPath = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitCE101.getJSONObject(0).getString("pic"));
                  document.add(module3_2para2);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CE101试验", pdf.getNumberOfPages());
                  document.add(module3_2para3);
                  document.add(module3_2para4);
                  if (resCE101PicPath == null) {
                      Paragraph module3_2para5 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(module3_2para5);
                  } else {
                      Paragraph CE101ValuePicTitlePara = new MidParagraph(CE101ValuePicTitle, font, smallSize);
                      Image CE101Pic = new Image(ImageDataFactory.create(resCE101PicPath));
                      document.add(CE101Pic);
                      document.add(CE101ValuePicTitlePara);
                      currentPicNumber++;
                  }
                  break;
              case "CE102":
                  JSONArray limitCE102 = new JSONArray();
                  JSONObject devCE102 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCe102());
                  JSONObject CE102Limit = devCE102.getJSONObject("limit_value");
                  JSONObject CE102LimitCurrent = devCE102.getJSONObject("limit_value_current");
                  String picNum = CE102Limit.getString("pic");
                  String pic = getLimitPic("CE102", picNum, CE102LimitCurrent.getString("pic"), 0);
                  JSONObject singleLimit = new JSONObject();
                  singleLimit.put("category", 3);
                  singleLimit.put("pic", pic);
                  singleLimit.put("title", limitNameMap.get(picNum));
                  limitCE102.add(singleLimit);
                  projectLimit.put("CE102", limitCE102);
                  String CE102ValuePicTitle = "图3-" + currentPicNumber + "\t" + limitCE102.getJSONObject(0).getString("title");
                  Paragraph CE102para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CE102", font, smallSize);
                  Paragraph CE102para2 = new LeftParagraph("试验内容：10kHz~10MHz\ua0a0电源线传导发射", font, smallSize);
                  Paragraph CE102para3 = new LeftParagraph("限值要求：" + "电源线传导发射不应超过图3-" + currentPicNumber + "中的限值要求", font, smallSize);
                  document.add(CE102para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CE102试验", pdf.getNumberOfPages());
                  document.add(CE102para2);
                  document.add(CE102para3);
                  String resCE102PicPath = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitCE102.getJSONObject(0).getString("pic"));
                  if (resCE102PicPath == null) {
                      Paragraph CE102para4 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(CE102para4);
                  } else {
                      Paragraph CE102ValuePicTitlePara = new MidParagraph(CE102ValuePicTitle, font, smallSize);
                      Image CE102Pic = new Image(ImageDataFactory.create(resCE102PicPath));
                      document.add(CE102Pic);
                      document.add(CE102ValuePicTitlePara);
                      currentPicNumber++;
                  }
                  break;
              case "CE106":
                  JSONArray limitCE106 = new JSONArray();
                  JSONObject devCE106 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCe106());
                  String CE106LimitCurrent = devCE106.getJSONObject("limit_value_current").getString("text");
                  JSONObject singleLimitCE106 = new JSONObject();
                  singleLimitCE106.put("category", 1);
                  singleLimitCE106.put("text", CE106LimitCurrent);
                  limitCE106.add(singleLimitCE106);
                  projectLimit.put("CE106", limitCE106);
                  Paragraph CE106para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CE106", font, smallSize);
                  Paragraph CE106TestContent = new LeftParagraph("试验内容：10kHz~40GHz\ua0a0天线端口传导发射", font, smallSize);
                  Paragraph CE106para2 = new LeftParagraph("限值要求：" + CE106LimitCurrent, font, smallSize);
//                  Paragraph CE106para3 = new bodyParagraph(CE106LimitCurrent, font, smallSize);
                  document.add(CE106para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CE106试验", pdf.getNumberOfPages());
                  document.add(CE106TestContent);
                  document.add(CE106para2);
//                  document.add(CE106para3);
                  break;
              case "CE107":
                  JSONArray limitCE107 = new JSONArray();
                  JSONObject devCE107 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCe107());
                  JSONArray CE107LimitCurrent = devCE107.getJSONArray("limit_value_current");
                  for(int j=0; j<2; j++){
                      if(!CE107LimitCurrent.getJSONObject(j).isEmpty()){
                          JSONObject singleLimitCE107 = new JSONObject();
                          singleLimitCE107.put("category", 1);
                          singleLimitCE107.put("text", CE107LimitCurrent.getJSONObject(j).getString("text"));
                          limitCE107.add(singleLimitCE107);
                      }
                  }
                  projectLimit.put("CE107", limitCE107);
                  Paragraph CE107para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CE107", font, smallSize);
                  Paragraph CE107TestContent = new LeftParagraph("试验内容：电源线尖峰信号（时域）传导发射", font, smallSize);
                  Paragraph CE107para2 = new LeftParagraph("限值要求：" + limitCE107.getJSONObject(0).getString("text"), font, smallSize);
//                  Paragraph CE107para3 = new bodyParagraph(limitCE107.getJSONObject(0).getString("text"), font, smallSize);
                  document.add(CE107para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CE107试验", pdf.getNumberOfPages());
                  document.add(CE107TestContent);
                  document.add(CE107para2);
//                  document.add(CE107para3);
                  break;
              case "CS101":
                  JSONArray limitCS101 = new JSONArray();
                  JSONObject devCS101 = (JSONObject) JSON.parse(manageSysDevelopEntity.getDevCs101());
                  JSONArray CS101Limit = devCS101.getJSONArray("limit_value");
                  JSONArray CS101LimitCurrent = devCS101.getJSONArray("limit_value_current");
                  for(int j=0; j<2; j++){
                      if(!CS101Limit.getJSONObject(j).isEmpty()){
                          String picNum1 = CS101Limit.getJSONObject(j).getString("pic_one");
                          String picNum2 = CS101Limit.getJSONObject(j).getString("pic_two");
                          String pic1 = getLimitBiPic("CS101", picNum1, CS101LimitCurrent.getJSONObject(j).getString("pic_one"), j+1, 1);
                          String pic2 = getLimitBiPic("CS101", picNum2, CS101LimitCurrent.getJSONObject(j).getString("pic_two"), j+1, 2);
                          JSONObject singleLimitCS101 = new JSONObject();
                          singleLimitCS101.put("category", 2);
                          singleLimitCS101.put("pic1", pic1);
                          singleLimitCS101.put("title1", limitNameMap.get(picNum1));
                          singleLimitCS101.put("pic2", pic2);
                          singleLimitCS101.put("title2", limitNameMap.get(picNum2));
                          limitCS101.add(singleLimitCS101);
                      }
                  }
                  Paragraph CS101para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CS101", font, smallSize);
                  Paragraph CS101para2 = new LeftParagraph("试验内容：25Hz~150kHz\ua0a0电源线传导敏感度", font, smallSize);
                  int nextPicNumber = currentPicNumber + 1;
                  Paragraph CS101para3 = new LeftParagraph("限值要求：当按图3-" + currentPicNumber + "和图3-" + nextPicNumber + "规定的限值进行试验时，EUT不应出现任何故障、性能下降或偏离规定的指标值，或超出单个设备和分系统规范中给出的指标允差" ,font, smallSize);
                  document.add(CS101para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0CS101试验", pdf.getNumberOfPages());
                  document.add(CS101para2);
                  document.add(CS101para3);
                  String resCS101Pic1Path = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitCS101.getJSONObject(0).getString("pic1"));
                  if (resCS101Pic1Path == null) {
                      Paragraph CS101para4 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(CS101para4);
                  } else {
                      String CS101ValuePic1Title = "图3-" + currentPicNumber + "\t" + limitCS101.getJSONObject(0).getString("title1");
                      Paragraph CS101ValuePic1TitlePara = new MidParagraph(CS101ValuePic1Title, font, smallSize);
                      Image CS101Pic1 = new Image(ImageDataFactory.create(resCS101Pic1Path));
                      document.add(CS101Pic1);
                      document.add(CS101ValuePic1TitlePara);
                      currentPicNumber++;
                  }
//                  Paragraph CS101para5 = new LeftParagraph("限值二名称：" + limitCS101.getJSONObject(0).getString("title2"), textfont, 12);
//                  CS101para5.setMarginTop(50);
//                  CS101para5.setMarginBottom(5);
//                  Paragraph CS101para6 = new LeftParagraph("限值二图片：", textfont, 12);
//                  CS101para6.setMarginBottom(5);
//                  document.add(CS101para5);
//                  document.add(CS101para6);
                  String resCS101Pic2Path = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitCS101.getJSONObject(0).getString("pic1"));
                  if (resCS101Pic2Path == null) {
                      Paragraph CS101para7 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(CS101para7);
                  } else {
                      String CS101ValuePic2Title = "图3-" + currentPicNumber + "\t" + limitCS101.getJSONObject(0).getString("title2");
                      Paragraph CS101ValuePic2TitlePara = new MidParagraph(CS101ValuePic2Title, font, smallSize);
                      Image CS101Pic2 = new Image(ImageDataFactory.create(resCS101Pic2Path));
                      document.add(CS101Pic2);
                      document.add(CS101ValuePic2TitlePara);
                      currentPicNumber++;
                  }
                  break;
              case "CS102":
                  JSONArray limitCS102 = new JSONArray();
                  JSONObject devCS102 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCs102());
                  String CS102LimitCurrent = devCS102.getJSONObject("limit_value_current").getString("text");
                  JSONObject singleLimitCS102 = new JSONObject();
                  singleLimitCS102.put("category", 1);
                  singleLimitCS102.put("text", CS102LimitCurrent);
                  limitCS102.add(singleLimitCS102);
                  projectLimit.put("CS102", limitCS102);
                  Paragraph CS102para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CS102", font, smallSize);
                  Paragraph CS102TestContent = new LeftParagraph("试验内容：25Hz~50kHz\ua0a0地线传导敏感度", font, smallSize);
                  Paragraph CS102para2 = new LeftParagraph("限值要求：" + limitCS102.getJSONObject(0).getString("text"), font, smallSize);
//                  Paragraph CS102para3 = new bodyParagraph(limitCS102.getJSONObject(0).getString("text"), font, smallSize);
                  document.add(CS102para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CS102试验", pdf.getNumberOfPages());
                  document.add(CS102TestContent);
                  document.add(CS102para2);
                  break;
              case "CS103":
                  JSONArray limitCS103 = new JSONArray();
                  JSONObject devCS103 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCs103());
                  JSONObject singleLimitCS103 = new JSONObject();
                  singleLimitCS103.put("category", 5);
                  limitCS103.add(singleLimitCS103);
                  projectLimit.put("CS103", limitCS103);
                  String CS103ValueText = JSON.parseObject(manageSysDevelopEntity.getDevCs103()).getJSONObject("limit_value_current").getString("value");
                  Paragraph CS103para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CS103", font, smallSize);
                  Paragraph CS103TestContent = new LeftParagraph("试验内容：15kHz~10GHz\ua0a0天线端口互调传导敏感度", font, smallSize);
                  Paragraph CS103para2 = new LeftParagraph("限值要求：当按限值要求进行试验时，EUT不应出现超过规定允差的任何互调产物", font, smallSize);
                  Paragraph CS103ValueTextPara = new bodyParagraph(CS103ValueText, font, smallSize);
                  document.add(CS103para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CS103试验", pdf.getNumberOfPages());
                  document.add(CS103TestContent);
                  document.add(CS103para2);
                  document.add(CS103ValueTextPara);
                  break;
              case "CS104":
                  JSONArray limitCS104 = new JSONArray();
                  JSONObject devCS104 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCs104());
                  JSONObject singleLimitCS104 = new JSONObject();
                  singleLimitCS104.put("category", 5);
                  limitCS104.add(singleLimitCS104);
                  projectLimit.put("CS104", limitCS104);
                  String CS104ValueText = JSON.parseObject(manageSysDevelopEntity.getDevCs104()).getJSONObject("limit_value_current").getString("value");
                  Paragraph CS104para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CS104", font, smallSize);
                  Paragraph CS104TestContent = new LeftParagraph("试验内容：25Hz~20GHz\ua0a0天线端口无用信号抑制传导敏感度", font, smallSize);
                  Paragraph CS104para2 = new LeftParagraph("限值要求：当按限值要求进行试验时，EUT不应出现超过规定允差的任何不希望的响应", font, smallSize);
                  Paragraph CS104ValueTextPara = new bodyParagraph(CS104ValueText, font, smallSize);
                  document.add(CS104para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CS104试验", pdf.getNumberOfPages());
                  document.add(CS104TestContent);
                  document.add(CS104para2);
                  document.add(CS104ValueTextPara);
                  break;
              case "CS105":
                  JSONArray limitCS105 = new JSONArray();
                  JSONObject devCS105 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCs105());
                  JSONObject singleLimitCS105 = new JSONObject();
                  singleLimitCS105.put("category", 5);
                  limitCS105.add(singleLimitCS105);
                  projectLimit.put("CS105", limitCS105);
                  String CS105ValueText = JSON.parseObject(manageSysDevelopEntity.getDevCs105()).getJSONObject("limit_value_current").getString("value");
                  Paragraph CS105para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CS105", font, smallSize);
                  Paragraph CS105TestContent = new LeftParagraph("试验内容：25Hz~20GHz\ua0a0天线端口交调传导敏感度", font, smallSize);
                  Paragraph CS105para2 = new LeftParagraph("限值要求：当按限值要求进行试验时，EUT不应由于交调而出现超过规定允差的任何不希望有的响应", font, smallSize);
                  Paragraph CS105ValueTextPara = new bodyParagraph(CS105ValueText, font, smallSize);
                  document.add(CS105para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CS105试验", pdf.getNumberOfPages());
                  document.add(CS105TestContent);
                  document.add(CS105para2);
                  document.add(CS105ValueTextPara);
                  break;
              case "CS106":
                  JSONArray limitCS106 = new JSONArray();
                  JSONObject devCS106 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCs106());
                  String CS106LimitCurrent = devCS106.getJSONObject("limit_value_current").getString("text");
                  JSONObject singleLimitCS106 = new JSONObject();
                  singleLimitCS106.put("category", 1);
                  singleLimitCS106.put("text", CS106LimitCurrent);
                  limitCS106.add(singleLimitCS106);
                  projectLimit.put("CS106", limitCS106);
                  Paragraph CS106para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CS106", font, smallSize);
                  Paragraph CS106TestContentPara = new LeftParagraph("试验内容：电源线尖峰信号传导敏感度", font, smallSize);
                  Paragraph CS106para2 = new LeftParagraph("限值要求：" + limitCS106.getJSONObject(0).getString("text"), font, smallSize);
//                  Paragraph CS106para3 = new bodyParagraph(limitCS106.getJSONObject(0).getString("text"), font, smallSize);
                  document.add(CS106para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CS106试验", pdf.getNumberOfPages());
                  document.add(CS106TestContentPara);
                  document.add(CS106para2);
//                  document.add(CS106para3);
                  break;
              case "CS109":
                  JSONArray limitCS109 = new JSONArray();
                  JSONObject devCS109 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCs109());
                  JSONObject CS109Limit = devCS109.getJSONObject("limit_value");
                  JSONObject CS109LimitCurrent = devCS109.getJSONObject("limit_value_current");
                  String picNumCS109 =CS109Limit.getString("pic");
                  String picCS109 = getLimitPic("CS109", picNumCS109, CS109LimitCurrent.getString("pic"), 0);
                  JSONObject singleLimitCS109 = new JSONObject();
                  singleLimitCS109.put("category", 3);
                  singleLimitCS109.put("pic", picCS109);
                  singleLimitCS109.put("title", limitNameMap.get(picNumCS109));
                  limitCS109.add(singleLimitCS109);
                  projectLimit.put("CS109", limitCS109);
                  String CS109ValuePicTitle = "图3-" + currentPicNumber + "\t" + limitCS109.getJSONObject(0).getString("title");
                  Paragraph CS109para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CS109", font, smallSize);
                  Paragraph CS109para2 = new LeftParagraph("试验内容：50Hz~100kHz\ua0a0壳体电流传导敏感度", font, smallSize);
                  Paragraph CS109para3 = new LeftParagraph("限值要求：" + "当按图3-" + currentPicNumber + "限值进行试验时，EUT不应出现任何故障、性能降低或偏离规定的指标值，或" +
                          "超出单个设备和分系统规范中给出的指标允差", font, smallSize);
                  document.add(CS109para1);
                  document.add(CS109para2);
                  document.add(CS109para3);
                  String resCS109PicPath = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitCS109.getJSONObject(0).getString("pic"));
                  if (resCS109PicPath == null) {
                      Paragraph CS109para4 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(CS109para4);
                  } else {
                      Paragraph CS109ValuePicTitlePara = new MidParagraph(CS109ValuePicTitle, font, smallSize);
                      Image CS109Pic = new Image(ImageDataFactory.create(resCS109PicPath));
                      document.add(CS109Pic);
                      document.add(CS109ValuePicTitlePara);
                      currentPicNumber++;
                  }
                  break;
              case "CS112":
                  JSONArray limitCS112 = new JSONArray();
                  JSONObject devCS112 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCs112());
                  String CS112LimitDefault = devCS112.getJSONObject("limit_value").getString("text");
                  String CS112LimitCurrent = devCS112.getJSONObject("limit_value_current").getString("text");
                  JSONObject singleLimitCS112 = new JSONObject();
                  singleLimitCS112.put("category", 1);
                  singleLimitCS112.put("text", CS112LimitCurrent);
                  limitCS112.add(singleLimitCS112);
                  projectLimit.put("CS112", limitCS112);
                  Paragraph CS112para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CS112", font, smallSize);
                  Paragraph CS112TestContentPara = new LeftParagraph("试验内容：静电放电敏感度", font, smallSize);
                  Paragraph CS112para2 = new LeftParagraph("限值要求：", font, smallSize);
                  Paragraph CS112para3 = new Paragraph();
                  if (CS112LimitDefault.equals(CS112LimitCurrent)) {
                      String defaultText =  "接触放电法限值" + "\n" +
                              "试验等级" + "\ua0a0\ua0a0" + "试验电压kV" + "\n" +
                              "一" + "\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0" + "2" + "\n" +
                              "二" + "\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0" + "4" + "\n" +
                              "三" + "\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0" + "6" + "\n" +
                              "四" + "\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0" + "8" + "\n" + "\n" +
                              "\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0" + "空气放电法限值" + "\n" +
                              "试验等级" + "\ua0a0\ua0a0" + "试验电压kV" + "\n" +
                              "一" + "\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0" + "2" + "\n" +
                              "二" + "\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0" + "4" + "\n" +
                              "三" + "\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0" + "8" + "\n" +
                              "四" + "\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0" + "15";
                      CS112para3 = new bodyParagraph(defaultText, font, smallSize);



                  } else {
                      CS112para3 = new bodyParagraph(limitCS112.getJSONObject(0).getString("text"), font, smallSize);
                  }
                  document.add(CS112para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CS112试验", pdf.getNumberOfPages());
                  document.add(CS112TestContentPara);
                  document.add(CS112para2);
                  document.add(CS112para3);
                  break;
              case "CS114":
                  JSONArray limitCS114 = new JSONArray();
                  JSONObject devCS114 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCs114());
                  JSONObject CS114Limit = devCS114.getJSONObject("limit_value");
                  JSONObject CS114LimitCurrent = devCS114.getJSONObject("limit_value_current");
                  String picNumCS114 =CS114Limit.getString("pic");
                  String picCS114 = getLimitPic("CS114", picNumCS114, CS114LimitCurrent.getString("pic"), 0);
                  JSONObject singleLimitCS114 = new JSONObject();
                  singleLimitCS114.put("category", 3);
                  singleLimitCS114.put("pic", picCS114);
                  singleLimitCS114.put("title", limitNameMap.get(picNumCS114));
                  limitCS114.add(singleLimitCS114);
                  projectLimit.put("CS114", limitCS114);
                  String CS114ValuePicTitle = "图3-" + currentPicNumber + "\t" + limitCS114.getJSONObject(0).getString("title");
                  Paragraph CS114para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CS114", font, smallSize);
                  Paragraph CS114para2 = new LeftParagraph("试验内容：4kHz~400MHz\ua0a0电缆束注入传导敏感度", font, smallSize);
                  Paragraph CS114para3 = new LeftParagraph("限值要求：" + "当给注入探头输入按图3-" + currentPicNumber + "校验并按要求调制的测试信号时，EUT不应" +
                          "出现任何故障、性能降低或偏离规定的指标值，或超出单个设备和分系统规范中给出的指标允差。如果受试电缆上的实际感应电流高于限值6dB，即使定向耦合器上监测的正向功率电平低于" +
                          "校验值，当EUT不敏感时，也认为它满足要求", font, smallSize);
                  document.add(CS114para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CS114试验", pdf.getNumberOfPages());
                  document.add(CS114para2);
                  document.add(CS114para3);
                  String resCS114PicPath = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitCS114.getJSONObject(0).getString("pic"));
                  if (resCS114PicPath == null) {
                      Paragraph CS114para4 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(CS114para4);
                  } else {
                      Paragraph CS114ValuePicTitlePara = new MidParagraph(CS114ValuePicTitle, font, smallSize);
                      Image CS114Pic = new Image(ImageDataFactory.create(resCS114PicPath));
                      document.add(CS114Pic);
                      document.add(CS114ValuePicTitlePara);
                      currentPicNumber++;
                  }
                  break;
              case "CS115":
                  JSONArray limitCS115 = new JSONArray();
                  JSONObject devCS115 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCs115());
                  JSONObject CS115Limit = devCS115.getJSONObject("limit_value");
                  JSONObject CS115LimitCurrent = devCS115.getJSONObject("limit_value_current");
                  String picNumCS115 =CS115Limit.getString("pic");
                  String picCS115 = getLimitPic("CS115", picNumCS115, CS115LimitCurrent.getString("pic"), 0);
                  JSONObject singleLimitCS115 = new JSONObject();
                  singleLimitCS115.put("category", 4);
                  singleLimitCS115.put("pic", picCS115);
                  singleLimitCS115.put("title", limitNameMap.get(picNumCS115));
                  singleLimitCS115.put("text", CS115LimitCurrent.getString("text"));
                  limitCS115.add(singleLimitCS115);
                  projectLimit.put("CS115", limitCS115);
                  String CS115ValuePicTitle = "图3-" + currentPicNumber + "\t" + limitCS115.getJSONObject(0).getString("text");
                  Paragraph CS115para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CS115", font, smallSize);
                  Paragraph CS115para2 = new LeftParagraph("试验内容：电缆束脉冲激励传导敏感度", font, smallSize);
                  Paragraph CS115para3 = new LeftParagraph("限值要求：" + "当按图3-" + currentPicNumber + limitCS115.getJSONObject(0).getString("text"), font, smallSize);
//                  Paragraph CS115para5 = new bodyParagraph(limitCS115.getJSONObject(0).getString("text"), font, smallSize);
                  document.add(CS115para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CS115试验", pdf.getNumberOfPages());
                  document.add(CS115para2);
                  document.add(CS115para3);
//                  document.add(CS115para5);
                  String resCS115PicPath = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitCS115.getJSONObject(0).getString("pic"));
                  if (resCS115PicPath == null) {
                      Paragraph CS115para4 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(CS115para4);
                  } else {
                      Paragraph CS115ValuePicTitlePara = new MidParagraph(CS115ValuePicTitle, font, smallSize);
                      Image CS115Pic = new Image(ImageDataFactory.create(resCS115PicPath));
                      document.add(CS115Pic);
                      document.add(CS115ValuePicTitlePara);
                      currentPicNumber++;
                  }
                  break;
              case "CS116":
                  JSONArray limitCS116 = new JSONArray();
                  JSONObject devCS116 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevCs116());
                  JSONObject CS116Limit = devCS116.getJSONObject("limit_value");
                  JSONObject CS116LimitCurrent = devCS116.getJSONObject("limit_value_current");
                  String picNumCS116 =CS116Limit.getString("pic");
                  String picCS116 = getLimitPic("CS116", picNumCS116, CS116LimitCurrent.getString("pic"), 0);
                  JSONObject singleLimitCS116 = new JSONObject();
                  singleLimitCS116.put("category", 4);
                  singleLimitCS116.put("pic", picCS116);
                  singleLimitCS116.put("title", limitNameMap.get(picNumCS116));
                  singleLimitCS116.put("text", CS116LimitCurrent.getString("text"));
                  limitCS116.add(singleLimitCS116);
                  projectLimit.put("CS116", limitCS116);
                  String CS116ValuePicTitle = "图3-" + currentPicNumber + "\t" + limitCS116.getJSONObject(0).getString("title");
                  Paragraph CS116para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目CS116", font, smallSize);
                  Paragraph CS116para2 = new LeftParagraph("试验内容：10kHz~100MHz\ua0a0电缆和电源线阻尼正弦瞬态传导敏感度", font, smallSize);
                  Paragraph CS116para3 = new LeftParagraph("限值要求：" + limitCS116.getJSONObject(0).getString("text") + "标准限值见图3-" + currentPicNumber, font, smallSize);
//                  Paragraph CS116para5 = new bodyParagraph(limitCS116.getJSONObject(0).getString("text"), font, smallSize);
                  document.add(CS116para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "CS116试验", pdf.getNumberOfPages());
                  document.add(CS116para2);
                  document.add(CS116para3);
//                  document.add(CS116para5);
                  String resCS116PicPath = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitCS116.getJSONObject(0).getString("pic"));
                  if (resCS116PicPath == null) {
                      Paragraph CS116para4 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(CS116para4);
                  } else {
                      Paragraph CS116ValuePicTitlePara = new MidParagraph(CS116ValuePicTitle, font, smallSize);
                      Image CS116Pic = new Image(ImageDataFactory.create(resCS116PicPath));
                      document.add(CS116Pic);
                      document.add(CS116ValuePicTitlePara);
                      currentPicNumber++;
                  }
                  break;
              case "RE101":
                  JSONArray limitRE101 = new JSONArray();
                  JSONObject devRE101 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevRe101());
                  JSONObject RE101Limit = devRE101.getJSONObject("limit_value");
                  JSONObject RE101LimitCurrent = devRE101.getJSONObject("limit_value_current");
                  String picNumRE101 =RE101Limit.getString("pic");
                  String picRE101 = getLimitPic("RE101", picNumRE101, RE101LimitCurrent.getString("pic"), 0);
                  JSONObject singleLimitRE101 = new JSONObject();
                  singleLimitRE101.put("category", 3);
                  singleLimitRE101.put("pic", picRE101);
                  singleLimitRE101.put("title", limitNameMap.get(picNumRE101));
                  limitRE101.add(singleLimitRE101);
                  projectLimit.put("RE101", limitRE101);
                  String RE101ValuePicTitle = "图3-" + currentPicNumber + "\t" + limitRE101.getJSONObject(0).getString("title");
                  Paragraph RE101para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目RE101", font, smallSize);
                  Paragraph RE101para2 = new LeftParagraph("试验内容：25Hz~100kHz\ua0a0磁场辐射发射", font, smallSize);
                  Paragraph RE101para3 = new LeftParagraph("限值要求：" + "测试距离为7cm时，磁场辐射发射不应超过图3-" + currentPicNumber + "的限值", font, smallSize);
                  document.add(RE101para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "RE101试验", pdf.getNumberOfPages());
                  document.add(RE101para2);
                  document.add(RE101para3);
                  String resRE101PicPath = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitRE101.getJSONObject(0).getString("pic"));
                  if (resRE101PicPath == null) {
                      Paragraph RE101para4 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(RE101para4);
                  } else {
                      Paragraph RE101ValuePicTitlePara = new MidParagraph(RE101ValuePicTitle, font, smallSize);
                      Image RE101Pic = new Image(ImageDataFactory.create(resRE101PicPath));
                      document.add(RE101Pic);
                      document.add(RE101ValuePicTitlePara);
                      currentPicNumber++;
                  }
                  break;
              case "RE102":
                  JSONArray limitRE102 = new JSONArray();
                  JSONObject devRE102 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevRe102());
                  JSONObject RE102Limit = devRE102.getJSONObject("limit_value");
                  JSONObject RE102LimitCurrent = devRE102.getJSONObject("limit_value_current");
                  String picNumRE102 =RE102Limit.getString("pic");
                  String picRE102 = getLimitPic("RE102", picNumRE102, RE102LimitCurrent.getString("pic"), 0);
                  JSONObject singleLimitRE102 = new JSONObject();
                  singleLimitRE102.put("category", 3);
                  singleLimitRE102.put("pic", picRE102);
                  singleLimitRE102.put("title", limitNameMap.get(picNumRE102));
                  limitRE102.add(singleLimitRE102);
                  projectLimit.put("RE102", limitRE102);
                  String RE102ValuePicTitle = "图3-" + currentPicNumber + "\t" + limitRE102.getJSONObject(0).getString("title");
                  Paragraph RE102para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目RE102", font, smallSize);
                  Paragraph RE102para2 = new LeftParagraph("试验内容：10kHz~18GHz\ua0a0电场辐射发射", font, smallSize);
                  Paragraph RE102para3 = new LeftParagraph("限值要求：" + "电场辐射发射不应超过图3-" + currentPicNumber + "的限值", font, smallSize);
                  document.add(RE102para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "RE102试验", pdf.getNumberOfPages());
                  document.add(RE102para2);
                  document.add(RE102para3);
                  String resRE102PicPath = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitRE102.getJSONObject(0).getString("pic"));
                  if (resRE102PicPath == null) {
                      Paragraph RE101para4 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(RE101para4);
                  } else {
                      Paragraph RE102ValuePicTitlePara = new MidParagraph(RE102ValuePicTitle, font, smallSize);
                      Image RE102Pic = new Image(ImageDataFactory.create(resRE102PicPath));
                      document.add(RE102Pic);
                      document.add(RE102ValuePicTitlePara);
                      currentPicNumber++;
                  }
                  break;
              case "RE103":
                  JSONArray limitRE103 = new JSONArray();
                  JSONObject devRE103 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevRe103());
                  String RE103LimitCurrent = devRE103.getJSONObject("limit_value_current").getString("text");
                  JSONObject singleLimitRE103 = new JSONObject();
                  singleLimitRE103.put("category", 1);
                  singleLimitRE103.put("text", RE103LimitCurrent);
                  limitRE103.add(singleLimitRE103);
                  projectLimit.put("RE103", limitRE103);
                  Paragraph RE103para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目RE103", font, smallSize);
                  Paragraph RE103TestContent = new LeftParagraph("试验内容：10kHz~40GHz\ua0a0天线谐波和乱真输出辐射发射", font, smallSize);
                  Paragraph RE103para2 = new LeftParagraph("限值要求：" + limitRE103.getJSONObject(0).getString("text"), font, smallSize);
//                  Paragraph RE103para3 = new bodyParagraph(limitRE103.getJSONObject(0).getString("text"), textfont, 12);
                  document.add(RE103para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "RE103试验", pdf.getNumberOfPages());
                  document.add(RE103TestContent);
                  document.add(RE103para2);
//                  document.add(RE103para3);
                  break;
              case "RS101":
                  JSONArray limitRS101 = new JSONArray();
                  JSONObject devRS101 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevRs101());
                  JSONObject RS101Limit = devRS101.getJSONObject("limit_value");
                  JSONObject RS101LimitCurrent = devRS101.getJSONObject("limit_value_current");
                  String picNumRS101 =RS101Limit.getString("pic");
                  String picRS101 = getLimitPic("RS101", picNumRS101, RS101LimitCurrent.getString("pic"), 0);
                  JSONObject singleLimitRS101 = new JSONObject();
                  singleLimitRS101.put("category", 3);
                  singleLimitRS101.put("pic", picRS101);
                  singleLimitRS101.put("title", limitNameMap.get(picNumRS101));
                  limitRS101.add(singleLimitRS101);
                  projectLimit.put("RS101", limitRS101);
                  String RS101ValuePicTitle = "图3-" + currentPicNumber + "\t" + limitRS101.getJSONObject(0).getString("title");
                  Paragraph RS101para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目RS101", font, smallSize);
                  Paragraph RS101para2 = new LeftParagraph("试验内容：25Hz~100kHz\ua0a0磁场辐射敏感度", font, smallSize);
                  Paragraph RS101para3 = new LeftParagraph("限值要求：" + "当按图3-" + currentPicNumber + "所示的磁场进行试验时，" +
                          "EUT不应出现任何故障、性能降低或偏离规定的指标值，或超出的那个设备和分系统规范中给出的指标允差", font, smallSize);
                  document.add(RS101para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "RS101试验", pdf.getNumberOfPages());
                  document.add(RS101para2);
                  document.add(RS101para3);
                  String resRS101PicPath = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitRS101.getJSONObject(0).getString("pic"));
                  if (resRS101PicPath == null) {
                      Paragraph RS101para4 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(RS101para4);
                  } else {
                      Paragraph RS101ValuePicTitlePara = new MidParagraph(RS101ValuePicTitle, font, smallSize);
                      Image RS101Pic = new Image(ImageDataFactory.create(resRS101PicPath));
                      document.add(RS101Pic);
                      document.add(RS101ValuePicTitlePara);
                      currentPicNumber++;
                  }
                  break;
              case "RS103":
                  JSONArray limitRS103 = new JSONArray();
                  JSONObject devRS103 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevRs103());
                  JSONObject RS103Limit = devRS103.getJSONObject("limit_value");
                  JSONObject RS103LimitCurrent = devRS103.getJSONObject("limit_value_current");
                  String picNumRS103 =RS103Limit.getString("pic");
                  String picRS103 = getLimitPic("RS103", picNumRS103, RS103LimitCurrent.getString("pic"), 0);
                  JSONObject singleLimitRS103 = new JSONObject();
                  singleLimitRS103.put("category", 3);
                  singleLimitRS103.put("pic", picRS103);
                  singleLimitRS103.put("title", limitNameMap.get(picNumRS103));
                  limitRS103.add(singleLimitRS103);
                  projectLimit.put("RS103", limitRS103);
                  String RS103ValuePicTitle = "图3-" + currentPicNumber + "\t" + limitRS103.getJSONObject(0).getString("title");
                  Paragraph RS103para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目RS103", font, smallSize);
                  Paragraph RS103para2 = new LeftParagraph("试验内容：10kHz~40GHz\ua0a0电场辐射敏感度", font, smallSize);
                  Paragraph RS103para3 = new LeftParagraph("限值要求：" + "当按图3-" + currentPicNumber + "并按要求调制的辐射电场进行试验时，" +
                          "设备不应出现任何故障、性能降低或偏离规定的指标值，或超出的那个设备和分系统规范中给出的指标允差", font, smallSize);
                  document.add(RS103para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "RS103试验", pdf.getNumberOfPages());
                  document.add(RS103para2);
                  document.add(RS103para3);
                  String resRS103PicPath = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitRS103.getJSONObject(0).getString("pic"));
                  if (resRS103PicPath == null) {
                      Paragraph RS103para4 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(RS103para4);
                  } else {
                      Paragraph RS103ValuePicTitlePara = new MidParagraph(RS103ValuePicTitle, font, smallSize);
                      Image RS103Pic = new Image(ImageDataFactory.create(resRS103PicPath));
                      document.add(RS103Pic);
                      document.add(RS103ValuePicTitlePara);
                      currentPicNumber++;
                  }
                  break;
              case "RS105":
                  JSONArray limitRS105 = new JSONArray();
                  JSONObject devRS105 = (JSONObject)JSON.parse(manageSysDevelopEntity.getDevRs105());
                  JSONObject RS105Limit = devRS105.getJSONObject("limit_value");
                  JSONObject RS105LimitCurrent = devRS105.getJSONObject("limit_value_current");
                  String picNumRS105 =RS105Limit.getString("pic");
                  String picRS105 = getLimitPic("RS105", picNumRS105, RS105LimitCurrent.getString("pic"), 0);
                  JSONObject singleLimitRS105 = new JSONObject();
                  singleLimitRS105.put("category", 3);
                  singleLimitRS105.put("pic", picRS105);
                  singleLimitRS105.put("title", limitNameMap.get(picNumRS105));
                  limitRS105.add(singleLimitRS105);
                  projectLimit.put("RS105", limitRS105);
                  String RS105ValuePicTitle = "图3-" + currentPicNumber + '\t' + limitRS105.getJSONObject(0).getString("title");
                  Paragraph RS105para1 = new LeftParagraph(catalogProjectName + "\ua0a0\ua0a0" + "试验项目RS105", font, smallSize);
                  Paragraph RS105para2 = new LeftParagraph("试验内容：瞬态电磁场辐射敏感度", font, smallSize);
                  Paragraph RS105para3 = new LeftParagraph("限值要求：" + "当按图3-" + currentPicNumber + "所示的试验信号的波形和幅度进行试验时，" +
                          "EUT不应出现任何故障、性能降低或偏离规定的指标值，或超出的那个设备和分系统规范中给出的指标允差。" +
                          "至少施加5个脉冲，重复频率不超过一个脉冲/分", font, smallSize);
                  document.add(RS105para1);
                  catalogs.put(catalogProjectName + "\ua0a0\ua0a0" + "RS105试验", pdf.getNumberOfPages());
                  document.add(RS105para2);
                  document.add(RS105para3);
                  String resRS105PicPath = getCurrentPicPath(manageSysDevelopEntity.getDevName(), limitRS105.getJSONObject(0).getString("pic"));
                  if (resRS105PicPath == null) {
                      Paragraph RS105para4 = new MidParagraph("未找到相关限值图片", font, smallSize);
                      document.add(RS105para4);
                  } else {
                      Paragraph RS105ValuePicTitlePara = new MidParagraph(RS105ValuePicTitle, font, smallSize);
                      Image RS105Pic = new Image(ImageDataFactory.create(resRS105PicPath));
                      document.add(RS105Pic);
                      document.add(RS105ValuePicTitlePara);
                      currentPicNumber++;
                  }
                  break;
              default:

          }

        }

        return projectLimit;
    }

    /**
     * 通过测试项目列表，算出显示测试项目限值需要的页面数量，并返回所占页数的最后一页
     */
    public static int getLimitPageNumber(JSONArray projectList) {
        int pageNumber = 5;
        for (int i = 0; i < projectList.size(); i++) {
            switch (projectList.getString(i)) {
                case "CE101" :
                    pageNumber++;
                    break;
                case "CE102" :
                    pageNumber++;
                    break;
                case "CE106" :
                    pageNumber++;
                    break;
                case "CE107" :
                    pageNumber++;
                    break;
                case "CS101" :
                    pageNumber += 2;
                    break;
                case "CS102" :
                    pageNumber++;
                    break;
                case "CS103" :
                    pageNumber++;
                    break;
                case "CS104" :
                    pageNumber++;
                    break;
                case "CS105" :
                    pageNumber++;
                    break;
                case "CS106" :
                    pageNumber++;
                    break;
                case "CS109" :
                    pageNumber++;
                    break;
                case "CS112" :
                    pageNumber++;
                    break;
                case "CS114" :
                    pageNumber++;
                    break;
                case "CS115" :
                    pageNumber++;
                    break;
                case "CS116" :
                    pageNumber++;
                    break;
                case "RE101" :
                    pageNumber++;
                    break;
                case "RE102" :
                    pageNumber++;
                    break;
                case "RE103" :
                    pageNumber++;
                    break;
                case "RS101" :
                    pageNumber++;
                    break;
                case "RS103" :
                    pageNumber++;
                    break;
                case "RS105" :
                    pageNumber++;
                    break;
                default:
                    break;
            }
        }
        return pageNumber;
    }

    public static ManageSysDevelopEntity findProjectById(int id) {
        return saveNewProjectDao.findProjectById(id);
    }

    /**
     * 判断限值单图片是否进行更改
     *
     * @Author: teng 870006096@qq.com
     * @Data: Created on 22:48 PM 2019/7/23
     * @Modified By:
     */

    public static String getLimitPic(String project, String pic, String picCurrent, int location){
        StringBuilder limit = new StringBuilder();
//        if(pic != null){
            if(pic.equals(picCurrent)){
                //标准图库
                limit.append("standard");
                limit.append(pic);
                limit.append(".png");
            }else{
                //更改后的图库前缀
                limit.append("change");
                if(location != 0) {
                    //CE101多限值情况
                    limit.append(devId);
                    limit.append("_");
                    limit.append(location);
                    limit.append("_");
                    limit.append(project);
                    limit.append(".png");
                }else{
                    limit.append(devId);
                    limit.append("_");
                    limit.append(project);
                    limit.append(".png");
                }

            }
//        }
        return limit.toString();
    }

    /**
     * 判断限值双图片是否进行更改
     *
     * @Author: teng 870006096@qq.com
     * @Data: Created on 01:48 PM 2019/7/24
     * @Modified By:
     */
    public static String getLimitBiPic(String project, String pic, String picCurrent, int location, int picLocation){
        StringBuilder limit = new StringBuilder();
//        if(pic != null){
            if(pic.equals(picCurrent)){
                //标准图库
                limit.append("standard");
                limit.append(pic);
                limit.append(".png");
            }else{
                //更改后的图库前缀
                limit.append("change");
                limit.append(devId);
                limit.append("_");
                limit.append(location);
                limit.append("_");
                limit.append(project);
                limit.append("_");
                limit.append(picLocation);
                limit.append(".png");
            }
//        }
        return limit.toString();
    }

    private static String getCurrentPicPath(String devName, String picName) {
        File standardPic = new File(PathStoreEnum.WINDOWS_STANDADIMG_PATH.getValue() + picName);
        File changedPic = new File(PathStoreEnum.WINDOWS_CHANGEDIMG_SOURTHPATH.getValue() + devName + "/" + picName);
        if (standardPic.isFile() && standardPic.exists()) {
            return PathStoreEnum.WINDOWS_STANDADIMG_PATH.getValue() + picName;
        } else if (changedPic.isFile() && changedPic.exists()) {
            return PathStoreEnum.WINDOWS_CHANGEDIMG_SOURTHPATH.getValue() + devName + "/" + picName;
        } else {
            return null;
        }
    }


    public HashMap<String, String> getLimitNameMap() {return limitNameMap;}

    public void setLimitNameMap(HashMap<String, String> limitNameMap) {this.limitNameMap = limitNameMap;}

    public static int getDevId() {return devId;}

    public static void setDevId(int devId1) {devId = devId1;}
}
