package kr.co.cyberline.service;

import java.sql.SQLException;
import java.util.Map;

public interface CommService {
    public Object commonApi(Map params) throws SQLException;
}
