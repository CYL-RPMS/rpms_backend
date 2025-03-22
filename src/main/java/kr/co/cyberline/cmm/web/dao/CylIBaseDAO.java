package kr.co.cyberline.cmm.web.dao;

import java.util.List;
import java.util.Map;

public interface CylIBaseDAO {
    Object getMapper(Class paramClass);

    int insert(String paramString);

    int insert(String paramString, Object paramObject);

    int update(String paramString);

    int update(String paramString, Object paramObject);

    int delete(String paramString);

    int delete(String paramString, Object paramObject);

    Object selectOne(String paramString);

    <K, V> Map<K, V> selectMap(String paramString1, Object paramObject, String paramString2);

    Object selectOne(String paramString, Object paramObject);

    List<?> selectList(String paramString);

    List<?> selectList(String paramString, Object paramObject);

    int getCount(String paramString, Object paramObject);

    Map<?, ?> map(String paramString1, Object paramObject, String paramString2);

    Object get(String paramString, Object paramObject);
}
