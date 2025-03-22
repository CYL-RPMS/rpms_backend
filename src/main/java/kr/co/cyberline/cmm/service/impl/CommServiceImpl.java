package kr.co.cyberline.cmm.service.impl;

import kr.co.cyberline.cmm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service(value = "CommService")
public class CommServiceImpl implements CommService{

    @Autowired
    private CommDAO commDAO;

    @Override
    public Object commonApi(Map params) throws SQLException {

        Object return_object = null;

        String command = (String)params.get("command");

        if ("select".equals(command)) {
            return_object = commDAO.commonSelect(params);
        } else if ("selectOne".equals(command)) {
            return_object = commDAO.commonSelectOne(params);
        } else if ("selectMap".equals(command)) {
            return_object = commDAO.commonSelectMap(params);
        } else if ("insert".equals(command)) {
            return_object = commDAO.commonInsert(params);
        } else if ("update".equals(command)) {
            return_object = commDAO.commonUpdate(params);
        } else if ("delete".equals(command)) {
            return_object = commDAO.commonDelete(params);
        }

        return return_object;
    }
}