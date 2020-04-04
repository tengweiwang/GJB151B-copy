package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import dao.UserProjectDao;
import dao.ViewProjectDao;
import entity.UserProject;
import entity.ViewProject;
import java.util.*;
import java.lang.*;
import java.util.List;
import java.util.Map;


public class ViewProjectService {
    private static ViewProjectDao viewProjectDao = ViewProjectDao.getInstance();
    private static UserProjectDao userProjectDao = UserProjectDao.getInstance();
    public  static  Map<String, List<ViewProject>> allProject(String userName) {
        List<String> ids = new ArrayList<>();
        List<String> newList = new ArrayList<>();
        List<String> proofreadList = new ArrayList<>();
        List<String> auditList = new ArrayList<>();
        List<String> authorizeList = new ArrayList<>();
        List<ViewProject> showNew = new ArrayList<>();
        List<ViewProject> showProofread = new ArrayList<>();
        List<ViewProject> showAudit = new ArrayList<>();
        List<ViewProject> showAuthorize = new ArrayList<>();
        List<ViewProject> showModify = new ArrayList<>();
        List<ViewProject> endNew = new ArrayList<>();
        List<ViewProject> endProofread = new ArrayList<>();
        List<ViewProject> endAudit = new ArrayList<>();
        List<ViewProject> endAuthorize = new ArrayList<>();
        Map<String, List<ViewProject>> projectResult = new HashMap<>();
        Map<String, List<String>> userMap = projectSpilt(userName);
        if (userMap == null) {
            return null;
        } else {
            ids = userMap.get("projects");
            newList = userMap.get("userNew");
            proofreadList = userMap.get("userProofread");
            auditList = userMap.get("userAudit");
            authorizeList = userMap.get("userAuthorize");
            Map<String, Integer> projectDic = new HashMap<String, Integer>();
            Map<String, Integer> projectIds = new HashMap<>();
            List<ViewProject> lm = viewProjectDao.viewAll(ids);
            if(lm == null){
                return null;
            }else {

                for (int i = 0; i < lm.size(); i++) {
                    projectDic.put(lm.get(i).getDevItemid(), lm.get(i).getDevStatus());
                    projectIds.put(lm.get(i).getDevItemid(), i);
                }
                if (newList != null && newList.size() != 0) {
                    for (String str : newList) {
                        if(projectDic.get(str) != null) {
                            if (projectDic.get(str) == 0) {
                                showNew.add(lm.get(projectIds.get(str)));
                            } else if (projectDic.get(str) == 4) {
                                showModify.add(lm.get(projectIds.get(str)));
                            } else {
                                endNew.add(lm.get(projectIds.get(str)));
                            }
                        }
                    }
                }
                if (proofreadList != null && proofreadList.size() != 0) {
                    for (String str : proofreadList) {
                        if(projectDic.get(str) != null) {
                            if (projectDic.get(str) == 1) {
                                showProofread.add(lm.get(projectIds.get(str)));
                            } else {
                                endProofread.add(lm.get(projectIds.get(str)));
                            }
                        }
                    }
                }
                if (auditList != null && auditList.size() != 0) {
                    for (String str : auditList) {
                        if(projectDic.get(str) != null) {
                            if (projectDic.get(str) == 2) {
                                showAudit.add(lm.get(projectIds.get(str)));
                            } else {
                                endAudit.add(lm.get(projectIds.get(str)));
                            }
                        }
                    }
                }
                if (authorizeList != null && authorizeList.size() != 0) {
                    for (String str : authorizeList) {
                        if(projectDic.get(str) != null) {
                            if (projectDic.get(str) == 3) {
                                showAuthorize.add(lm.get(projectIds.get(str)));
                            } else {
                                endAuthorize.add(lm.get(projectIds.get(str)));
                            }
                        }
                    }
                }
                projectResult.put("showNew", showNew);
                projectResult.put("showProofread", showProofread);
                projectResult.put("showAudit", showAudit);
                projectResult.put("showAuthorize", showAuthorize);
                projectResult.put("showModify", showModify);
                projectResult.put("endNew", endNew);
                projectResult.put("endProofread", endProofread);
                projectResult.put("endAudit", endAudit);
                projectResult.put("endAuthorize", endAuthorize);


                return projectResult;
            }

        }

    }



    public static Map<String, List<String>> projectSpilt(String userName) {
        UserProject userProject = userProjectDao.FindUser(userName);
        if (userProject == null) {
            return null;
        } else {
            Map<String, List<String>> projectDic = new HashMap<String, List<String>>();
            List<String> newList = new ArrayList<String>();
            List<String> proofreadList = new ArrayList<String>();
            List<String> auditList = new ArrayList<String>();
            List<String> authorizeList = new ArrayList<String>();
            List<String> projects = new ArrayList<>();
            JSONArray newJson = (JSONArray) JSON.parse(userProject.getUserNew());
            JSONArray proofreadJson = (JSONArray) JSON.parse(userProject.getUserProofread());
            JSONArray auditJson = (JSONArray) JSON.parse(userProject.getUserAudit());
            JSONArray authorizeJson = (JSONArray) JSON.parse(userProject.getUserAuthorize());
            if (newJson != null && newJson.size() != 0) {
                for (int i = 0; i < newJson.size(); i++) {
                    newList.add(newJson.getString(i));
                    projects.add(newJson.getString(i));
                }
            }
            if (proofreadJson != null && proofreadJson.size() != 0) {
                for (int i = 0; i < proofreadJson.size(); i++) {
                    proofreadList.add(proofreadJson.getString(i));
                    projects.add(proofreadJson.getString(i));
                }
            }
            if (auditJson != null && auditJson.size() != 0) {
                for (int i = 0; i < auditJson.size(); i++) {
                    auditList.add(auditJson.getString(i));
                    projects.add(auditJson.getString(i));
                }
            }
            if (authorizeJson != null && authorizeJson.size() != 0) {
//            String str4 = userProject.getUserAuthorize().substring(1,userProject.getUserAuthorize().length()-1);
//            s4 = str4.split(",");
                for (int i = 0; i < authorizeJson.size(); i++) {
                    authorizeList.add(authorizeJson.getString(i));
                    projects.add(authorizeJson.getString(i));
                }
            }
            projectDic.put("userNew", newList);
            projectDic.put("userProofread", proofreadList);
            projectDic.put("userAudit", auditList);
            projectDic.put("userAuthorize", authorizeList);
            projectDic.put("projects", projects);
            System.out.println("ProjectDic中的projects"+projectDic.get("projects"));

            return projectDic;
        }

    }


}



