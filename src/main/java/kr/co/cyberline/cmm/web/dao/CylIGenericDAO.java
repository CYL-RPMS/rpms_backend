package kr.co.cyberline.cmm.web.dao;

import java.util.List;

public interface CylIGenericDAO<T> {
    List<T> selectList(String paramString, Object paramObject);

    int insert(String paramString, Object paramObject);

    int update(String paramString, Object paramObject);

    int delete(String paramString, Object paramObject);
}
