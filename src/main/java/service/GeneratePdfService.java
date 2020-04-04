package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import consts.ItemNameEnum;
import consts.ItemNamePrefix;
import consts.PathStoreEnum;
import dao.GeneratePdfDao;
import dao.ItemDao;
import entity.ManageSysDevelopEntity;
import entity.ManageSysPdfEntity;
import utils.itext7.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @Author: fcupup 875894948@qq.com
 * @Data: Created on 2:48 PM 2019/7/18
 * @Modified By:
 */
public class GeneratePdfService {

    public static final String DEST = "src/main/resources/pdf/report.pdf";
    public static final String CATALOGDEST = "src/main/resources/pdf/catalog.pdf";

    // 定义全局的字体静态变量
    private static PdfFont titlefont;
    private static PdfFont headfont;
    private static PdfFont keyfont;
    private static PdfFont textfont;
    // 最大宽度
    private static int maxWidth = 520;
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



    public void generatePdfCore(String targetPath, String devItemId) throws Exception {
        GeneratePdfDao.getInstance().deleteAllData();
        GeneratePdfService generatePdfService = new GeneratePdfService();
        generatePdfService.generatePdf(targetPath, devItemId);
    }

    private void GenerateDataBase(String devItemId) {
        ManageSysDevelopEntity manageSysDevelopEntity = ItemDao.getInstance().getItemByDevItemId(devItemId);
        ManageSysPdfEntity pdfEntity;
        GeneratePdfDao pdfDao = GeneratePdfDao.getInstance();
        int pageNumber = 2;

        //1  任务依据
        //1.1 任务来源
        pdfEntity = new ManageSysPdfEntity();
        String itemSource = manageSysDevelopEntity.getDevSubsysSource();
        pdfEntity.setTitle("1  任务依据,1.1  任务来源");
        pdfEntity.setStartPageNumber("1," + pageNumber + "&" + "2," + pageNumber);
        pdfEntity.setContent(itemSource);
        pdfEntity.setEndPageNumber(pageNumber);
        pdfDao.addData(pdfEntity);

        //1.2 任务依据
        pageNumber = pageNumber + 1;
        pdfEntity = new ManageSysPdfEntity();
        String itemRef = manageSysDevelopEntity.getDevSubsysComRef();
        pdfEntity.setTitle("1.2  编制依据和引用文件");
        pdfEntity.setStartPageNumber("2," + pageNumber);
        pdfEntity.setContent(itemRef);
        pdfEntity.setEndPageNumber(pageNumber);
        pdfDao.addData(pdfEntity);

        //2 被试品数量及技术状态要求
        pageNumber = pageNumber + 1;
        pdfEntity = new ManageSysPdfEntity();
        String quantity = manageSysDevelopEntity.getDevSubsysQuantity();
        pdfEntity.setTitle("2  被试品数量及技术状态要求");
        pdfEntity.setStartPageNumber("1," + pageNumber);
        pdfEntity.setContent(quantity);
        pdfEntity.setEndPageNumber(pageNumber);
        pdfDao.addData(pdfEntity);

        //3 试验项目及要求
        //3.1 试验环境与条件要求
        pageNumber = pageNumber + 1;
        pdfEntity = new ManageSysPdfEntity();
        String testEnvironment = manageSysDevelopEntity.getDevSubsysEnvironment();
        pdfEntity.setTitle("3  试验项目及要求,3.1  试验环境与条件要求");
        pdfEntity.setStartPageNumber("1," + pageNumber + "&" + "2," + pageNumber);
        pdfEntity.setContent(testEnvironment);
        pdfEntity.setEndPageNumber(pageNumber);
        pdfDao.addData(pdfEntity);

        //3.2  试验项目及要求 需要修改数据来源
        pageNumber = pageNumber + 1;
        pdfEntity = new ManageSysPdfEntity();
        String projectList = manageSysDevelopEntity.getProjectList();
        JSONArray projectArray = JSON.parseArray(projectList);
        int length = projectArray.size();
        JSONArray imgList = new JSONArray();
        for (int i = 0; i < length; i++) {
            imgList.add("standard39_5.png");
        }
        pdfEntity.setTitle("3.2  试验项目及要求");
        pdfEntity.setStartPageNumber("2," + pageNumber);
        pageNumber = GeneratePdfLimitService.getLimitPageNumber(JSON.parseArray(projectList));
        pdfEntity.setEndPageNumber(pageNumber);
        pdfDao.addData(pdfEntity);

        //3.3 标准剪裁说明
        pageNumber = pageNumber + 1;
        pdfEntity = new ManageSysPdfEntity();
        JSONArray projectCutArray = new JSONArray();
        JSONArray projectCutReasonArray = new JSONArray();
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCe101()).getString("remark"))) {
            projectCutArray.add("CE101");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCe101()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCe102()).getString("remark"))) {
            projectCutArray.add("CE102");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCe102()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCe106()).getString("remark"))) {
            projectCutArray.add("CE106");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCe106()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCe107()).getString("remark"))) {
            projectCutArray.add("CE107");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCe107()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCs101()).getString("remark"))) {
            projectCutArray.add("CS101");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCs101()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCs102()).getString("remark"))) {
            projectCutArray.add("CS102");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCs102()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCs103()).getString("remark"))) {
            projectCutArray.add("CS103");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCs103()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCs104()).getString("remark"))) {
            projectCutArray.add("CS104");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCs104()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCs105()).getString("remark"))) {
            projectCutArray.add("CS105");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCs105()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCs106()).getString("remark"))) {
            projectCutArray.add("CS106");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCs106()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCs109()).getString("remark"))) {
            projectCutArray.add("CS109");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCs109()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCs112()).getString("remark"))) {
            projectCutArray.add("CS112");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCs112()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCs114()).getString("remark"))) {
            projectCutArray.add("CS114");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCs114()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCs115()).getString("remark"))) {
            projectCutArray.add("CS115");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCs115()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevCs116()).getString("remark"))) {
            projectCutArray.add("CS116");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevCs116()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevRe101()).getString("remark"))) {
            projectCutArray.add("RE101");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevRe101()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevRe102()).getString("remark"))) {
            projectCutArray.add("RE102");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevRe102()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevRe103()).getString("remark"))) {
            projectCutArray.add("RE103");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevRe103()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevRs101()).getString("remark"))) {
            projectCutArray.add("RS101");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevRs101()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevRs103()).getString("remark"))) {
            projectCutArray.add("RS103");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevRs103()).getString("remark"));
        }
        if (! "".equals(JSON.parseObject(manageSysDevelopEntity.getDevRs105()).getString("remark"))) {
            projectCutArray.add("RS105");
            projectCutReasonArray.add(JSON.parseObject(manageSysDevelopEntity.getDevRs105()).getString("remark"));
        }
        JSONObject jsonObject3_2 = new JSONObject();
        jsonObject3_2.put("projectsCut", projectCutArray.toJSONString());
        jsonObject3_2.put("projectsCutReason", projectCutReasonArray.toJSONString());

        pdfEntity.setTitle("3.3  标准剪裁说明");;
        pdfEntity.setStartPageNumber("2," + pageNumber);
        pdfEntity.setContent(jsonObject3_2.toJSONString());
        pdfEntity.setEndPageNumber(pageNumber);
        pdfDao.addData(pdfEntity);
    }

    /**
     * 这个函数是用来给pdf生成目录所用，目录仅支持一级、二级、三级标题
     *
     * @param document pdf文件
     * @param titleMap 包含标题和索引页号信息
     */
    private void generateCatalog(Document document, TreeMap<String, String[]> titleMap) {
        for (Map.Entry<String, String[]> entry : titleMap.entrySet()) {
            String title = entry.getKey();
            String[] titles = title.split(",");
            int length = titles.length;
            String[] titleForms = new String[length];
            String[] pageNumbers = new String[length];
            for (int i = 0; i < length; i++) {
                String[] formAndNumber = entry.getValue();
                titleForms[i] = formAndNumber[i].split(",")[0];
                pageNumbers[i] = formAndNumber[i].split(",")[1];
            }
            addIndex(document, titles, titleForms, pageNumbers);
        }
        document.add(new PageFooter(1));
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

    }

    /**
     * 这个函数被上面的generateCatalog调用，主要是将核心的共享代码块分离出来了，这里的addIndex是给pdf添加具体的
     * 目录索引
     *
     * @param document 需要添加的pdf文件
     * @param titles 含有1个或者多个的title（标题）
     * @param titleForms 含有1个或者多个的titleForm（标题级别）1代表一级标题 2代表二级标题 3代表三级标题
     * @param pageNumbers 含有1个或者是多个的pageNumber（页码）和标题相对应
     */
    private void addIndex(Document document, String[] titles, String[] titleForms, String[] pageNumbers) {
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            Paragraph paragraph = new LeftParagraph();
            paragraph.addTabStops(new TabStop(540, TabAlignment.RIGHT, new DottedLine()));
            paragraph.setFont(titlefont);
            switch (titleForms[i]) {
                case "1" :
                    paragraph.setFontSize(16);
                    paragraph.add(titles[i]);
                    break;
                case "2" :
                    paragraph.setFontSize(14);
                    paragraph.add("\ua0a0\ua0a0\ua0a0\ua0a0" + titles[i]);
                    break;
                case "3" :
                    paragraph.setFontSize(12);
                    paragraph.add("\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0\ua0a0" + titles[i]);
                    break;
                default:
                    break;
            }
            paragraph.add(new Tab());
            paragraph.add(pageNumbers[i]);
            paragraph.setMarginBottom(0);
            document.add(paragraph);
        }

    }

    /**
     *
     * @param dest pdf导出位置
     * @param devItemId 需要导出pdf的项目研制id
     * @throws Exception
     */
    public void generatePdf(String dest, String devItemId) throws Exception {
        //初始化pdf文件
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // 事件监听器，页眉页脚
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new MyEventHandler());

        // Map：记录标题对应页数
        Map<String, Integer> catalogs = new TreeMap<String, Integer>(new Comparator<String>() {
            // 按put顺序排序
            public int compare(String o1, String o2) {
                return 1;
            }
        });

        // 设置中文字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H",true);

        // 设置字体大小
        //一级标题字体大小
        int bigSize = 14;
        //二级标题字体大小
        int midSize = 12;
        //三级标题字体大小
        int smallSize = 11;

        ItemDao itemDao = ItemDao.getInstance();
        GeneratePdfDao pdfDao = GeneratePdfDao.getInstance();
        ManageSysPdfEntity pdfEntity;

        //gjb151b文件的前三页
        //第一页内容
        //项目对应研制要求的所有信息
        ManageSysDevelopEntity manageSysDevelopEntity = itemDao.getItemByDevItemId(devItemId);
        ManageSysDevelopEntity manageSysDevelop = manageSysDevelopEntity;

        /** 章节1 */
        // 任务来源
        String workSource = manageSysDevelop.getDevSubsysSource();
        // 编制依据和引用文件 （红）
        String proof = manageSysDevelop.getDevSubsysComRef();

        // 正文第一页
        LeftParagraph t1 = new LeftParagraph("1\ua0a0任务依据", font, bigSize);
        LeftParagraph t1_1 = new LeftParagraph("1.1\ua0a0任务来源", font, midSize);
        Paragraph c1_1 = new BodyParagraph1(workSource, font, smallSize);
        LeftParagraph t1_2 = new LeftParagraph("1.2\ua0a0编制依据和引用文件", font, midSize);
        bodyParagraph c1_2p1 = new bodyParagraph(proof, font, smallSize);

        document.add(t1);
        catalogs.put("1\ua0a0\ua0a0任务依据", pdf.getNumberOfPages());
        document.add(t1_1);
        catalogs.put("1.1\ua0a0\ua0a0任务来源", pdf.getNumberOfPages());
        document.add(c1_1);
        document.add(t1_2);
        catalogs.put("1.2\ua0a0\ua0a0编制依据和引用文件", pdf.getNumberOfPages());
        document.add(c1_2p1);

        /** 章节2 */
        //被试品数量及技术状态要求
        String testQuantityAndState = manageSysDevelop.getDevSubsysQuantity();
        LeftParagraph t2 = new LeftParagraph("2\ua0a0被试品数量及技术状态要求", font, bigSize);
        bodyParagraph t2_1 = new bodyParagraph(testQuantityAndState, font, smallSize);
        Paragraph t2_2 = new BodyParagraph1("被试品符合鉴定技术状态和工艺状态，符合成品协议书和产品规范的要求。", font, smallSize);
        Paragraph t2_3 = new BodyParagraph1("软件均已通过软件测评。", font, smallSize);

        document.add(t2);
        catalogs.put("2\ua0a0\ua0a0被试品数量及技术状态要求", pdf.getNumberOfPages());
        document.add(t2_1);
        document.add(t2_2);
        document.add(t2_3);


        /** 章节3 */
        //试验环境与条件要求
        String testEnvironment = manageSysDevelop.getDevSubsysEnvironment();

        LeftParagraph t3 = new LeftParagraph("3\ua0a0试验项目及要求", font, bigSize);
        LeftParagraph t3_1 = new LeftParagraph("3.1\ua0a0试验环境与条件要求", font, midSize);
        Paragraph t3_1p1 = new BodyParagraph1("本项试验环境条件要求如下：", font, smallSize);
        Paragraph t3_1p2 = new BodyParagraph1(testEnvironment, font, smallSize);
        LeftParagraph t3_2 = new LeftParagraph("3.2\ua0a0试验项目及限值要求", font, midSize);

        document.add(t3);
        catalogs.put("3\ua0a0\ua0a0试验项目及要求", pdf.getNumberOfPages());
        document.add(t3_1);
        catalogs.put("3.1\ua0a0\ua0a0试验环境与条件要求", pdf.getNumberOfPages());
        document.add(t3_1p1);
        document.add(t3_1p2);
        document.add(t3_2);
        catalogs.put("3.2\ua0a0\ua0a0试验项目及限值要求", pdf.getNumberOfPages());

        //需要测试的项目列表
        String projects = manageSysDevelop.getProjectList();
        JSONArray projectArray = JSON.parseArray(projects);
        int devId = manageSysDevelop.getDevId();
        GeneratePdfLimitService.getProjectLimit(devId, projectArray, document, catalogs, pdf);


//
//
//
//
//
//        Integer allPageNumber = pdfDao.getLastData().getEndPageNumber();
//        String devName = "(" + manageSysDevelopEntity.getDevName() + ")" + "研制要求";
//        String manageCompany = "标准化电子研究院";
//        Paragraph page1para1 = new RightParagraph("密级：", PageSize.A4.getWidth() / 4 * 3, textfont, 14);
//        page1para1.setMarginTop(100);
//        page1para1.setMarginBottom(50);
//        Paragraph page1para2 = new MidParagraph(devName, titlefont, 20);
//        page1para2.setMarginBottom(5);
//        Paragraph page1para3 = new MidParagraph("电磁兼容性要求", titlefont, 20);
//        page1para3.setMarginBottom(10);
//        Paragraph page1para4 = new MidParagraph("共" + allPageNumber + "页", textfont, 14);
//        page1para4.setMarginBottom(400);
//        Paragraph page1para5 = new MidParagraph("(" + manageCompany + ")", textfont, 14);
//        page1para5.setMarginBottom(3);
//        Paragraph page1para6 = new MidParagraph("批准日期", textfont, 14);
//
//        document.add(page1para1);
//        document.add(page1para2);
//        document.add(page1para3);
//        document.add(page1para4);
//        document.add(page1para5);
//        document.add(page1para6);
//
//        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
//
//        //第二页内容
//        String develop = "编 制：___________  日期: ____年__月__日";
//        String proofread = "校 对：___________  日期: ____年__月__日";
//        String audit = "审 核：___________  日期: ____年__月__日";
//        String authorize = "批 准：___________  日期: ____年__月__日";
//
//        Paragraph page2para1 = new MidParagraph(devName, titlefont, 20);
//        page2para1.setMarginTop(160);
//        page2para1.setMarginBottom(5);
//        Paragraph page2para2 = new MidParagraph("电磁兼容性要求", titlefont, 20);
//        page2para2.setMarginBottom(5);
//        Paragraph page2para3 = new MidParagraph("签署页", titlefont, 14);
//        page2para3.setMarginBottom(300);
//
//        Paragraph page2para4 = new MidParagraph(develop, textfont, 14);
//        page2para4.setMarginBottom(5);
//        Paragraph page2para5 = new MidParagraph(proofread, textfont, 14);
//        page2para5.setMarginBottom(5);
//        Paragraph page2para6 = new MidParagraph(audit, textfont, 14);
//        page2para6.setMarginBottom(5);
//        Paragraph page2para7 = new MidParagraph(authorize, textfont, 14);
//
//        document.add(page2para1);
//        document.add(page2para2);
//        document.add(page2para3);
//        document.add(page2para4);
//        document.add(page2para5);
//        document.add(page2para6);
//        document.add(page2para7);
//
//        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
//
//        //第三页内容
//        Paragraph page3para1 = new MidParagraph("文件修改记录", textfont, 14);
//        page3para1.setMarginTop(100);
//        page3para1.setMarginBottom(5);
//        Table table = new Table(UnitValue.createPercentArray(new float[]{0.15f, 0.15f, 0.25f, 0.25f, 0.2f})).useAllAvailableWidth();
//        table.addCell(new Cell().add("版本号").setFont(textfont).setTextAlignment(TextAlignment.CENTER));
//        table.addCell(new Cell().add("修改内容").setFont(textfont).setTextAlignment(TextAlignment.CENTER));
//        table.addCell(new Cell().add("修改人").setFont(textfont).setTextAlignment(TextAlignment.CENTER));
//        table.addCell(new Cell().add("修改日期").setFont(textfont).setTextAlignment(TextAlignment.CENTER));
//        table.addCell(new Cell().add("备注").setFont(textfont).setTextAlignment(TextAlignment.CENTER));
//        for (int i = 0; i < 20; i++) {
//            table.addCell(new Cell().add("\n").setFont(textfont).setFontSize(14));
//            table.addCell(new Cell().add("").setFont(textfont).setFontSize(14));
//            table.addCell(new Cell().add("").setFont(textfont));
//            table.addCell(new Cell().add("").setFont(textfont));
//            table.addCell(new Cell().add("").setFont(textfont));
//        }
//
//        document.add(page3para1);
//        document.add(table);
//
//        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
//
//        //生成目录
//        java.util.List<ManageSysPdfEntity> pdfEntityList = pdfDao.getAllData();
//        TreeMap<String, String[]> titleMap = new TreeMap<>();
//        for (ManageSysPdfEntity component : pdfEntityList) {
//            String titles = component.getTitle();
//            String titleFormsAndPageNumber = component.getStartPageNumber();
//            if (titles != null) {
//                String[] formsAndPageNumber = titleFormsAndPageNumber.split("&");
//                titleMap.put(titles, formsAndPageNumber);
//            }
//        }
//        generateCatalog(document, titleMap);
//
//        //pdf正文开始
//        Integer pageNumber = 2;
//        //1 任务依据 & 1.1 任务来源
//        pdfEntity = pdfDao.getDataByTitle("任务依据");
//        //1.1 任务来源
//        String taskSource = pdfEntity.getContent();
//        Paragraph module1para1 = new LeftParagraph("1    任务依据", titlefont, 14);
//        module1para1.setMarginTop(100);
//        module1para1.setMarginBottom(10);
//        Paragraph module1_1para1 = new LeftParagraph("1.1    任务来源", titlefont, 14);
//        module1_1para1.setMarginBottom(5);
//        Paragraph module1_1para2 = new LeftParagraph(taskSource, textfont, 12);
//
//        document.add(module1para1);
//        document.add(module1_1para1);
//        document.add(module1_1para2);
//        document.add(new PageFooter(pageNumber));
//        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
//
//        //1.2 编制依据和引用文件
//        pageNumber = pdfDao.getPageNumberByTitle("编制依据和引用文件");
//        String comRef = pdfDao.getDataByTitle("编制依据和引用文件").getContent();
//        Paragraph module1_2para1 = new LeftParagraph("1.2    编制依据和引用文件", titlefont, 14);
//        module1_2para1.setMarginTop(100);
//        module1_2para1.setMarginBottom(5);
//        Paragraph module1_2para2 = new LeftParagraph(comRef, textfont, 12);
//
//        document.add(module1_2para1);
//        document.add(module1_2para2);
//        document.add(new PageFooter(pageNumber));
//        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
//
//        //2 被试品数量及技术状态要求
//        pageNumber = pdfDao.getPageNumberByTitle("被试品数量及技术状态要求");
//        String quantity = pdfDao.getDataByTitle("被试品数量及技术状态要求").getContent();
//        Paragraph module2para1 = new LeftParagraph("2    被试品状态及技术状态要求", titlefont, 14);
//        module2para1.setMarginTop(100);
//        module2para1.setMarginBottom(5);
//        Paragraph module2para2 = new LeftParagraph(quantity, textfont, 12);
//
//        document.add(module2para1);
//        document.add(module2para2);
//        document.add(new PageFooter(pageNumber));
//        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
//
//        //3 试验项目及要求
//        //3.1 试验环境与条件要求
//        pageNumber = pdfDao.getPageNumberByTitle("试验环境与条件要求");
//        String testEnvironment = pdfDao.getDataByTitle("试验环境与条件要求").getContent();
//        Paragraph module3para1 = new LeftParagraph("3    试验项目及要求", titlefont, 14);
//        module3para1.setMarginTop(100);
//        module3para1.setMarginBottom(5);
//        Paragraph module3_1para1 = new LeftParagraph("3.1    试验环境与条件要求", titlefont, 14);
//        module3_1para1.setMarginBottom(5);
//        Paragraph module3_1para2 = new LeftParagraph(testEnvironment, textfont, 12);
//
//        document.add(module3para1);
//        document.add(module3_1para1);
//        document.add(module3_1para2);
//        document.add(new PageFooter(pageNumber));
//        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
//
//        //3.2 试验项目及要求
//        pageNumber = pdfDao.getPageNumberByTitle("3.2  试验项目及要求");
//        String imgPathPrefix = PathStoreEnum.WINDOWS_STANDADIMG_PATH.getValue();
//        String projects = pdfDao.getDataByTitle("3.2  试验项目及要求").getContent();
//        String imgs = pdfDao.getDataByTitle("3.2  试验项目及要求").getPicture();
//        JSONArray projectArray = JSON.parseArray(projects);
//        JSONArray imgArray = JSON.parseArray(imgs);
//        Paragraph module3_2para1 = new LeftParagraph("3.2    试验项目及要求", titlefont, 14);
//        module3_2para1.setMarginTop(30);
//        module3_2para1.setMarginBottom(5);
//        document.add(module3_2para1);
//        GeneratePdfLimitService.getProjectLimit(manageSysDevelopEntity.getDevId(), JSON.parseArray(manageSysDevelopEntity.getProjectList()), document);
//
//        //3.3 标准剪裁说明
//        pageNumber = pdfDao.getPageNumberByTitle("3.3");
//        String projectsCutAndReason = pdfDao.getDataByTitle("3.3").getContent();
//        JSONObject projectsCutAndReasonObject = JSON.parseObject(projectsCutAndReason);
//        JSONArray projectCutArray = projectsCutAndReasonObject.getJSONArray("projectsCut");
//        JSONArray projectCutReasonArray = projectsCutAndReasonObject.getJSONArray("projectsCutReason");
//        Paragraph module3_3para1 = new LeftParagraph("3.3    标准剪裁说明", titlefont, 14);
//        module3_3para1.setMarginTop(50);
//        module3_3para1.setMarginBottom(5);
//        document.add(module3_3para1);
//        for (int i = 0; i < projectCutArray.size(); i++) {
//            int order = i + 1;
//            Paragraph module3_3para2 = new LeftParagraph(order + "  " + "剪裁项目：" + projectCutArray.getString(i), textfont, 12);
//            Paragraph module3_3para3 = new LeftParagraph("\ua0a0\ua0a0\ua0a0\ua0a0" + "剪裁理由：" + projectCutReasonArray.getString(i), textfont, 12);
//            document.add(module3_3para2);
//            document.add(module3_3para3);
//        }
//        document.add(new PageFooter(pageNumber));

        int totalNumberPages = pdf.getNumberOfPages();
        document.close();

        /** 封面和目录部分*/
        // 设置字体
        PdfFont font2 = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H",true);
        // 输出含有目录的pdf
        PdfWriter catalogWriter = new PdfWriter(CATALOGDEST);
        PdfDocument catalogPdf = new PdfDocument(catalogWriter);
        Document catalogDocument = new Document(catalogPdf);

        // 首页右上角的密级
        String degreeOfPassword = "";
        // 型号+名称
        String sizeAndName = manageSysDevelop.getDevName() + "__" + manageSysDevelop.getDevSubsysEqpName();
        // 首页标注的页数
        String totalPage = String.valueOf(totalNumberPages);
        // 编制单位
        String itemName = ItemNamePrefix.getValue();
        if ("_".equals(itemName.substring(itemName.length() - 1, itemName.length()))) {
            itemName = itemName.substring(0, itemName.length() - 1);
        }
        String Unit = itemName;

        // 首页年份 + 首页月份
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH) + 1;
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);


        // 编制日期
        Date outlineNewTime = manageSysDevelop.getDevNewTime();
        Calendar newCal = Calendar.getInstance();
        newCal.setTime(outlineNewTime);
        String newYear = String.valueOf(newCal.get(Calendar.YEAR));
        String newMonth = String.valueOf(newCal.get(Calendar.MONTH) + 1);
        String newDay = String.valueOf(newCal.get(Calendar.DAY_OF_MONTH));
        String orgDate = newYear + "年" + newMonth + "月" + newDay + "日";
        // 校对日期
        Date outlineProofreadTime = manageSysDevelop.getDevProofreadTime();
        Calendar proofreadCal = Calendar.getInstance();
        newCal.setTime(outlineProofreadTime);
        String proofreadYear = String.valueOf(proofreadCal.get(Calendar.YEAR));
        String proofreadMonth = String.valueOf(proofreadCal.get(Calendar.MONTH) + 1);
        String proofreadDay = String.valueOf(proofreadCal.get(Calendar.DAY_OF_MONTH));
        String checkDate = proofreadYear + "年" + proofreadMonth + "月" + proofreadDay + "日";
        // 审核日期
        Date outlineAuditTime = manageSysDevelop.getDevAuditTime();
        Calendar auditCal = Calendar.getInstance();
        auditCal.setTime(outlineAuditTime);
        String auditYear = String.valueOf(auditCal.get(Calendar.YEAR));
        String auditMonth = String.valueOf(auditCal.get(Calendar.MONTH) + 1);
        String auditDay = String.valueOf(auditCal.get(Calendar.DAY_OF_MONTH));
        String examDate = auditYear + "年" + auditMonth + "月" + auditDay + "日";
        // 批准日期
        Date outlineAuthorizeTime = manageSysDevelop.getDevAuthorizeTime();
        Calendar authorizeCal = Calendar.getInstance();
        newCal.setTime(outlineAuthorizeTime);
        String authorizeYear = String.valueOf(authorizeCal.get(Calendar.YEAR));
        String authorizeMonth = String.valueOf(authorizeCal.get(Calendar.MONTH) + 1);
        String authorizeDay = String.valueOf(authorizeCal.get(Calendar.DAY_OF_MONTH));
        String apprvDate = authorizeYear + "年" + authorizeMonth + "月" + authorizeDay + "日";

        // 首页
        LeftParagraph p01l01p1 = new LeftParagraph("定型文件"+multispace(120)+"密级："+
                degreeOfPassword, font2, midSize);
        MidParagraph p01l02 = new MidParagraph(sizeAndName, font2, bigSize);
        MidParagraph p01l03 = new MidParagraph("设计鉴定电磁兼容性研制要求", font2, bigSize);
        MidParagraph p01l04 = new MidParagraph("共"+totalPage+"页", font2, midSize);
        MidParagraph p01l05 = new MidParagraph(Unit, font2, midSize);
        MidParagraph p01l06 = new MidParagraph(year+"年"+month+"月", font2, midSize);

        breakline(catalogDocument, 2);
        catalogDocument.add(p01l01p1);
        breakline(catalogDocument, 15);
        catalogDocument.add(p01l02);
        catalogDocument.add(p01l03);
        catalogDocument.add(p01l04);
        breakline(catalogDocument, 40);
        catalogDocument.add(p01l05);
        catalogDocument.add(p01l06);

        // 第二页
        catalogDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        MidParagraph p02l01 = new MidParagraph(sizeAndName, font2, bigSize);
        MidParagraph p02l02 = new MidParagraph("设计鉴定电磁兼容性研制要求", font2, bigSize);
        MidParagraph p02l03 = new MidParagraph("签署页", font2, midSize);
        MidParagraph p02l04 = new MidParagraph("编制：______________ 日期："+orgDate, font2, midSize);
        MidParagraph p02l05 = new MidParagraph("校对：______________ 日期："+checkDate, font2, midSize);
        MidParagraph p02l06 = new MidParagraph("审核：______________ 日期："+examDate, font2, midSize);
        MidParagraph p02l07 = new MidParagraph("批准：______________ 日期："+apprvDate, font2, midSize);

        breakline(catalogDocument, 20);
        catalogDocument.add(p02l01);
        catalogDocument.add(p02l02);
        catalogDocument.add(p02l03);
        breakline(catalogDocument, 30);
        catalogDocument.add(p02l04);
        catalogDocument.add(p02l05);
        catalogDocument.add(p02l06);
        catalogDocument.add(p02l07);

        // 第三页
        catalogDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        MidParagraph p03l01 = new MidParagraph("文件修改记录", font2, midSize);
        float[] reviseTableWidth = new float[]{70, 105, 140, 210, 70};
        // 文件修改表格
        Table reviseTable = new Table(reviseTableWidth).setWidthPercent(100);
        reviseTable.addCell(new Cell().add(new Paragraph("版本号").setFont(font2).
                setFontSize(midSize))).setTextAlignment(TextAlignment.CENTER);
        reviseTable.addCell(new Cell().add(new Paragraph("修改内容").setFont(font2).
                setFontSize(midSize))).setTextAlignment(TextAlignment.CENTER);
        reviseTable.addCell(new Cell().add(new Paragraph("修改人").setFont(font2).
                setFontSize(midSize))).setTextAlignment(TextAlignment.CENTER);
        reviseTable.addCell(new Cell().add(new Paragraph("修改日期").setFont(font2).
                setFontSize(midSize))).setTextAlignment(TextAlignment.CENTER);
        reviseTable.addCell(new Cell().add(new Paragraph("备注").setFont(font2).
                setFontSize(midSize))).setTextAlignment(TextAlignment.CENTER);
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 5; j++){
                reviseTable.addCell(new Cell().add(new Paragraph("\n").setFont(font2).setFontSize(midSize)));
            }
        }
        catalogDocument.add(p03l01);
        catalogDocument.add(reviseTable);
        catalogDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        MidParagraph catalogSet = new MidParagraph("目 次", font2, midSize);
        catalogDocument.add(catalogSet);
        for (Map.Entry<String, Integer> entry: catalogs.entrySet()){
            new catalogText().createPdf(catalogDocument, CATALOGDEST, entry.getKey(), entry.getValue(), font2, smallSize);
        }
        catalogDocument.close();

        // 合并pdf 新pdf
        String newdest = dest;
        PdfDocument newPdf = new PdfDocument(new PdfWriter(newdest));
        PdfMerger merger = new PdfMerger(newPdf);

        catalogPdf = new PdfDocument(new PdfReader(CATALOGDEST));
        merger.merge(catalogPdf, 1, catalogPdf.getNumberOfPages());
        pdf = new PdfDocument(new PdfReader(DEST));
        merger.merge(pdf, 1, pdf.getNumberOfPages());

        catalogPdf.close();
        pdf.close();
        newPdf.close();
    }

    /** 多个空格*/
    public String multispace(int n){
        String space = "";
        for (int i = 0; i < n; i++){
            space = space.concat(" ");
        }
        return space;
    }

    /** 空行*/
    public void breakline(Document document, int n){
        for (int i = 0; i < n; i++){
            document.add(new Paragraph(" "));
        }
    }

    /** 页眉页脚监听器*/
    protected class MyEventHandler implements IEventHandler {
        public void handleEvent(Event event) {
            try{
                PdfFont font = PdfFontFactory.createFont("STSong-Light",
                        "UniGB-UCS2-H",true);
                PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
                PdfDocument pdfDoc = docEvent.getDocument();
                PdfPage page = docEvent.getPage();
                int pageNumber = pdfDoc.getPageNumber(page);
                Rectangle pageSize = page.getPageSize();
                PdfCanvas pdfCanvas = new PdfCanvas(
                        page.newContentStreamBefore(), page.getResources(), pdfDoc);

                pdfCanvas.beginText()
                        .setFontAndSize(font, 10)
                        .moveText(pageSize.getWidth() - 150, pageSize.getTop() - 20)
                        .showText("电磁兼容性研制要求")
                        .moveText(-pageSize.getWidth() / 2 + 150, -pageSize.getTop() + 30)
                        .showText(String.valueOf(pageNumber))
                        .endText();

                pdfCanvas.release();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
