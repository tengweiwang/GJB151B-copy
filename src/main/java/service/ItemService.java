package service;


import com.alibaba.fastjson.JSONObject;
//import com.sun.xml.internal.rngom.parse.host.Base;
//import consts.ItemNamePrefixEnum;
import consts.ItemNamePrefix;
import consts.ItemStatusEnum;
import consts.PathStoreEnum;
import dao.ItemDao;
import entity.ItemOperater;
import entity.ManageSysDevelopEntity;
import org.apache.commons.io.FileUtils;
import utils.BaseResponse;

import java.io.*;
import java.util.*;


public class ItemService {

    private static ItemDao itemDao = ItemDao.getInstance();


    public static BaseResponse<List> findAllItem() {
        BaseResponse<List> findAllItemResponse = new BaseResponse<>();
        List<ManageSysDevelopEntity> item;
        List<ItemOperater> itemList = new ArrayList<>();
        item = itemDao.findAllItem();
        for (int i = 0; i < item.size(); i++) {
            ItemOperater itemOperater = new ItemOperater();
            itemOperater = itemDao.findOperater(item.get(i).getDevItemid(), itemOperater);
            itemOperater.setItemId(item.get(i).getDevItemid());
            itemOperater.setItemName(item.get(i).getDevName());
            itemOperater.setDevStatus(item.get(i).getDevStatus());
            itemOperater.setStatus(ItemStatusEnum.getStatusByOrder(item.get(i).getDevStatus()));
            itemList.add(itemOperater);
        }
        if (item == null) {
            findAllItemResponse.setStatus("error");
            findAllItemResponse.setMessage("目前没有项目");
        } else {
            findAllItemResponse.setStatus("success");
            findAllItemResponse.setData(itemList);
            findAllItemResponse.setMessage("目前有项目");
        }
        return findAllItemResponse;
    }

    public static BaseResponse<String> addItem(ManageSysDevelopEntity s, String user_new, String proofread, String audit, String authorize) {
        BaseResponse<String> addItemResponse = new BaseResponse<>();
        ManageSysDevelopEntity check = itemDao.checkItem(s.getDevName());
        if (check == null) {
            String devItemid = itemDao.addItem(s);
            if (devItemid == null) {
                addItemResponse.setStatus("error");
                addItemResponse.setData(s.getDevName());
                addItemResponse.setMessage("操作失败");
            } else {
                String result = itemDao.addOperater(devItemid, user_new, proofread, audit, authorize);
                if (result == "success") {
                    addItemResponse.setStatus("success");
                    addItemResponse.setData(s.getDevName());
                    addItemResponse.setMessage("成功添加项目'" + s.getDevName() + "'");
                } else {
                    addItemResponse.setStatus("error");
                    addItemResponse.setData(s.getDevName());
                    addItemResponse.setMessage("更新用户表失败");
                }
            }
        } else {
            addItemResponse.setStatus("error");
            addItemResponse.setData(s.getDevName());
            addItemResponse.setMessage("项目名'" + s.getDevName() + "'已存在");
        }
        //创建文件夹
        String targetPath = PathStoreEnum.WINDOWS_CHANGEDIMG_SOURTHPATH.getValue() + s.getDevName() + "_" +  "图片";
        File dir = new File(targetPath);
        if (! dir.exists()) {
            dir.mkdir();
        }
        return addItemResponse;
    }

    public static BaseResponse<String> deleteItem(String devName) {
        BaseResponse<String> deleteItemResponse = new BaseResponse<>();
        String devItemid = itemDao.deleteItem(devName);

        if (devItemid == null) {
            deleteItemResponse.setStatus("error");
            deleteItemResponse.setData(devItemid);
            deleteItemResponse.setMessage("项目'" + devName + "'删除失败");
        } else {
            String result = deleteOptater(devItemid);
            if (result == "success") {
                deleteItemResponse.setStatus("success");
                deleteItemResponse.setData(devItemid);
                deleteItemResponse.setMessage("项目'" + devName + "'删除成功");
            } else {
                deleteItemResponse.setStatus("error");
                deleteItemResponse.setData(devItemid);
                deleteItemResponse.setMessage("项目'" + devName + "'在用户表中删除失败");
            }
        }
        //删除文件夹
        String targetPath = PathStoreEnum.WINDOWS_CHANGEDIMG_SOURTHPATH.getValue() + devName + "_" + "图片";
        File dir = new File(targetPath);
        if (dir.exists()) {
            dir.delete();
        }
        return deleteItemResponse;
    }

    public static BaseResponse<String> updateItemName(String devItemId, String devName) {
        int length = ItemNamePrefix.getValue().length();
        if (devName.length() > length && ! devName.substring(0,length + 1).equals(ItemNamePrefix.getValue())) {

        } else {
            devName = ItemNamePrefix.getValue() + devName;
        }
        BaseResponse<String> updateItemNameResponse = new BaseResponse<>();
        ManageSysDevelopEntity manageSysDevelopEntity = itemDao.getItemByDevItemId(devItemId);
        String updateResult = itemDao.updateItemName(devItemId, devName);
        if (updateResult == "success") {
            updateItemNameResponse.setStatus("success");
            updateItemNameResponse.setData(devItemId);
            updateItemNameResponse.setMessage("更新项目列表成功");
        } else {
            updateItemNameResponse.setStatus("error");
            updateItemNameResponse.setData(devItemId);
            updateItemNameResponse.setMessage("更新项目列表失败");
        }
        //修改存储项目图片文件夹名称
        String oldPath = PathStoreEnum.WINDOWS_CHANGEDIMG_SOURTHPATH.getValue() + manageSysDevelopEntity.getDevName() + "_" +  "图片";
        String newPath = PathStoreEnum.WINDOWS_CHANGEDIMG_SOURTHPATH.getValue() + devName + "_" + "图片";
        File oldDir = new File(oldPath);
        File newDir = new File(newPath);
        if (oldDir.exists()) {
            oldDir.renameTo(newDir);
        }
        return updateItemNameResponse;
    }

    public static BaseResponse<String> updateItem(String devItemId, String user_new, String user_proofread, String user_audit, String user_authorize) {
        BaseResponse<String> updateItemResponse = new BaseResponse<>();
        String deleteResult = deleteOptater(devItemId);
        String addResult = itemDao.addOperater(devItemId, user_new, user_proofread, user_audit, user_authorize);
        if (deleteResult == "success" && addResult == "success") {
            updateItemResponse.setStatus("success");
            updateItemResponse.setData(devItemId);
            updateItemResponse.setMessage("更新成功");
        } else {
            updateItemResponse.setStatus("error");
            updateItemResponse.setData(devItemId);
            updateItemResponse.setMessage("更新失败");
        }
        return updateItemResponse;
    }

    public static String deleteOptater(String devItemid) {
        String resultNew = itemDao.deleteItemInUser_new(devItemid);
        String resultProofread = itemDao.deleteItemInUser_proofread(devItemid);
        String resultAudit = itemDao.deleteItemInUser_audit(devItemid);
        String resultAuthorize = itemDao.deleteItemInUser_authorize(devItemid);
        if (resultNew == "success" || resultProofread == "success" || resultAudit == "success" || resultAuthorize == "success") {
            return "success";
        } else
            return "error";
    }

    public static BaseResponse exportItem(String devItemId) throws Exception {
        String currentTime = getSecondTimeStamp();
        BaseResponse exportItemResponse;
        ManageSysDevelopEntity manageSysDevelop = ItemDao.getInstance().getItemByDevItemId(devItemId);
        File dir = new File(PathStoreEnum.WINDOWS_SQLDATA_AND_IMG_EXPORT_PATH.getValue() + currentTime);
        if (! dir.exists()) {
            dir.mkdir();
        }
        String sqlExportFinalPath = PathStoreEnum.WINDOWS_SQLDATA_AND_IMG_EXPORT_PATH.getValue() + currentTime + "/" + manageSysDevelop.getDevName();
        JSONObject sqlDataObject = new JSONObject();
        sqlDataObject.put("devItemId", manageSysDevelop.getDevItemid());
        sqlDataObject.put("devName", manageSysDevelop.getDevName());
        sqlDataObject.put("devSubsysEqp", manageSysDevelop.getDevSubsysEqp());
        sqlDataObject.put("devSubsysEqpName", manageSysDevelop.getDevSubsysEqpName());
        sqlDataObject.put("devSubsysEqpModel", manageSysDevelop.getDevSubsysEqpModel());
        sqlDataObject.put("devSubsysEqpNum", manageSysDevelop.getDevSubsysEqpNum());
        sqlDataObject.put("devSupplier", manageSysDevelop.getDevSupplier());
        sqlDataObject.put("devPrimaryPlatform", manageSysDevelop.getDevPrimaryPlatform());
        sqlDataObject.put("devSecondaryPlatform", manageSysDevelop.getDevSecondaryPlatform());
        sqlDataObject.put("devAttribute", manageSysDevelop.getDevAttribute());
        sqlDataObject.put("devKey", manageSysDevelop.getDevKey());
        sqlDataObject.put("devInstall", manageSysDevelop.getDevInstall());
        sqlDataObject.put("devGnd", manageSysDevelop.getDevGnd());
        sqlDataObject.put("devSpecial", manageSysDevelop.getDevSpecial());
        sqlDataObject.put("devInterport", manageSysDevelop.getDevInterport());
        sqlDataObject.put("devLowsensitive", manageSysDevelop.getDevLowsensitive());
        sqlDataObject.put("devEmp", manageSysDevelop.getDevEmp());
        sqlDataObject.put("devStatic", manageSysDevelop.getDevStatic());
        sqlDataObject.put("devPowerPort", manageSysDevelop.getDevPowerport());
        sqlDataObject.put("devPowersupply", manageSysDevelop.getDevPowersupply());
        sqlDataObject.put("devVoltage", manageSysDevelop.getDevVoltage());
        sqlDataObject.put("devVoltagenum", manageSysDevelop.getDevVoltagenum());
        sqlDataObject.put("devAntenna", manageSysDevelop.getDevAntenna());
        sqlDataObject.put("devReceiveLaunch", manageSysDevelop.getDevReceiveLaunch());
        sqlDataObject.put("devModulation", manageSysDevelop.getDevModulation());
        sqlDataObject.put("devFreqOptional", manageSysDevelop.getDevFreqOptional());
        sqlDataObject.put("devFreqFHLow", manageSysDevelop.getDevFreqFhLow());
        sqlDataObject.put("devFreqFHMid", manageSysDevelop.getDevFreqFhMid());
        sqlDataObject.put("devFreqFHHigh", manageSysDevelop.getDevFreqFhHigh());
        sqlDataObject.put("devFreqDSSS", manageSysDevelop.getDevFreqDsss());
        sqlDataObject.put("devCe101", manageSysDevelop.getDevCe101());
        sqlDataObject.put("devCe102", manageSysDevelop.getDevCe102());
        sqlDataObject.put("devCe106", manageSysDevelop.getDevCe106());
        sqlDataObject.put("devCe107", manageSysDevelop.getDevCe107());
        sqlDataObject.put("devCs101", manageSysDevelop.getDevCs101());
        sqlDataObject.put("devCs102", manageSysDevelop.getDevCs102());
        sqlDataObject.put("devCs103", manageSysDevelop.getDevCs103());
        sqlDataObject.put("devCs104", manageSysDevelop.getDevCs104());
        sqlDataObject.put("devCs105", manageSysDevelop.getDevCs105());
        sqlDataObject.put("devCs106", manageSysDevelop.getDevCs106());
        sqlDataObject.put("devCs109", manageSysDevelop.getDevCs109());
        sqlDataObject.put("devCs112", manageSysDevelop.getDevCs112());
        sqlDataObject.put("devCs114", manageSysDevelop.getDevCs114());
        sqlDataObject.put("devCs115", manageSysDevelop.getDevCs115());
        sqlDataObject.put("devCs116", manageSysDevelop.getDevCs116());
        sqlDataObject.put("devRe101", manageSysDevelop.getDevRe101());
        sqlDataObject.put("devRe102", manageSysDevelop.getDevRe102());
        sqlDataObject.put("devRe103", manageSysDevelop.getDevRe103());
        sqlDataObject.put("devRs101", manageSysDevelop.getDevRs101());
        sqlDataObject.put("devRs103", manageSysDevelop.getDevRs103());
        sqlDataObject.put("devRs105", manageSysDevelop.getDevRs105());
        sqlDataObject.put("devAdviceProofread", manageSysDevelop.getDevAdviceProofread());
        sqlDataObject.put("devAdviceAudit", manageSysDevelop.getDevAdviceAudit());
        sqlDataObject.put("devAdviceAuthorize", manageSysDevelop.getDevAdviceAuthorize());
        sqlDataObject.put("devCreateTime", manageSysDevelop.getDevCreateTime());
        sqlDataObject.put("devNewTime", manageSysDevelop.getDevNewTime());
        sqlDataObject.put("devModifyTime", manageSysDevelop.getDevModifyTime());
        sqlDataObject.put("devProofreadTime", manageSysDevelop.getDevProofreadTime());
        sqlDataObject.put("devAuditTime", manageSysDevelop.getDevAuditTime());
        sqlDataObject.put("devAuthorizeTime", manageSysDevelop.getDevAuthorizeTime());
        sqlDataObject.put("devUpdateTime", manageSysDevelop.getDevUpdateTime());
        sqlDataObject.put("devOperator", manageSysDevelop.getDevOpeator());
        sqlDataObject.put("devStatus", manageSysDevelop.getDevStatus());
        sqlDataObject.put("projectList", manageSysDevelop.getProjectList());
        sqlDataObject.put("devFreSelect", manageSysDevelop.getDevFreSelect());
        sqlDataObject.put("devSubsysSource", manageSysDevelop.getDevSubsysSource());
        sqlDataObject.put("devSubsysComRef", manageSysDevelop.getDevSubsysComRef());
        sqlDataObject.put("devSubsysQuantity", manageSysDevelop.getDevSubsysQuantity());
        sqlDataObject.put("devSubsysEnvironment", manageSysDevelop.getDevSubsysEnvironmrnt());
        sqlDataObject.put("devPowername", manageSysDevelop.getDevPowername());
        String sqlDataStr = sqlDataObject.toJSONString();
        //将项目数据存储到txt文件中，并导出至桌面
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(sqlExportFinalPath);
            fileWriter.write(sqlDataStr);
        } catch (IOException e) {
            e.printStackTrace();
            exportItemResponse = new BaseResponse();
            exportItemResponse.setMessage("系统导出项目异常！等稍后重试");
            return exportItemResponse;
        } finally {
            try {
                //将数据流写到目的地
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            File fileTxt = new File(sqlExportFinalPath);
            File fileJson = new File(sqlExportFinalPath + ".json");
            fileTxt.renameTo(fileJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        将项目修改过的限值图片copy到桌面
        String sourcePath = PathStoreEnum.WINDOWS_CHANGEDIMG_SOURTHPATH.getValue() + manageSysDevelop.getDevName() + "_" + "图片";
        File file = new File(sourcePath);
        if (file != null && file.exists()) {
            String targetPath = PathStoreEnum.WINDOWS_SQLDATA_AND_IMG_EXPORT_PATH.getValue() + currentTime + "/" + manageSysDevelop.getDevName() + "_" + "图片";
            //调用拷贝文件方法
            copyFolder(sourcePath, targetPath);
        }

//        导出项目pdf文件
        String pdfTargetPath = PathStoreEnum.WINDOWS_SQLDATA_AND_IMG_EXPORT_PATH.getValue() + currentTime + "/" + manageSysDevelop.getDevName() + ".pdf";
        GeneratePdfService generatePdfService = new GeneratePdfService();
        generatePdfService.generatePdfCore(pdfTargetPath, devItemId);
        exportItemResponse = new BaseResponse();
        exportItemResponse.setMessage("项目导出至桌面成功！");
        return exportItemResponse;

    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    private static  void copyFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" +
                            (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {//如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }
    }

    private static String getSecondTimeStamp() {
        Calendar cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        int hour = cale.get(Calendar.HOUR_OF_DAY);
        int minute = cale.get(Calendar.MINUTE);
        int second = cale.get(Calendar.SECOND);
        String monthStr = month >= 10 ? String.valueOf(month) : "0" + month;
        return String.valueOf(year) + monthStr + day + "-" + hour + minute + second;
    }
}