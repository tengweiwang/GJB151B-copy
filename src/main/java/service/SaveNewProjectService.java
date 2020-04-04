package service;

import dao.SaveNewProjectDao;
import entity.ManageSysDevelopEntity;
import org.apache.commons.lang.StringUtils;
import utils.BaseResponse;

/**
 * Created by ddgdd on 2018/10/12 0012 17:26
 */
public class SaveNewProjectService {
    private static SaveNewProjectDao saveNewProjectDao = SaveNewProjectDao.getInstance();

    public static BaseResponse saveNewProject(ManageSysDevelopEntity manageSysDevelopEntity) {
        String response = saveNewProjectDao.saveNewProject(manageSysDevelopEntity);
        BaseResponse saveNewResponse = new BaseResponse();
        if (StringUtils.equals(response, "success")) {
            saveNewResponse.setStatus("success");
        }else {
            saveNewResponse.setMessage("数据库更新失败");
        }

        return saveNewResponse;
    }


    public static ManageSysDevelopEntity findProjectById(int id) {
        return saveNewProjectDao.findProjectById(id);
    }
}
