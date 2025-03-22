package kr.co.cyberline.cmm.web.dao;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

public abstract class CylAbstractDAO<T> implements CylIGenericDAO<T> {
    protected CylIBaseDAO baseDAO;

    @Resource(name = "baseDAO")
    public void setBaseDAO(CylIBaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }

    protected abstract String getNameSpace();

    private String getQueryId(String queryId) {
        return getNameSpace() + "." + queryId;
    }

    protected T get(String queryId, Object params) {
        return (T)this.baseDAO.get(queryId, params);
    }

    public List<T> selectList(Object param) {
        return (List)this.baseDAO.selectList(getQueryId("selectList"), param);
    }

    public List<T> selectList(String queryId, Object params) {
        return (List)this.baseDAO.selectList(getQueryId(queryId), params);
    }

    public int selectListCount(Object param) {
        return this.baseDAO.getCount(getQueryId("selectListCount"), param);
    }

    protected int selectListCount(String queryId, Object param) {
        return this.baseDAO.getCount(getQueryId(queryId), param);
    }

    public T select(String queryId, Object params) {
        return (T)this.baseDAO.selectOne(getQueryId(queryId), params);
    }

    public <K, V> Map<K, V> selectMap(String queryId, Object params, String keyProperty) {
        return this.baseDAO.selectMap(queryId, params, keyProperty);
    }

    public T getById(Object param) {
        return get(getQueryId("getById"), param);
    }

    public boolean isExist(Object param) {
        int count = this.baseDAO.getCount(getQueryId("isExist"), param);
        return (count > 0);
    }

    protected boolean isExist(String queryId, Object param) {
        int count = this.baseDAO.getCount(getQueryId(queryId), param);
        return (count > 0);
    }

    public int insert(Object param) {
        return this.baseDAO.insert(getQueryId("insert"), param);
    }

    public int insert(String queryId, Object params) {
        return this.baseDAO.insert(getQueryId(queryId), params);
    }

    public int update(Object param) {
        return this.baseDAO.update(getQueryId("update"), param);
    }

    public int update(String queryId, Object params) {
        return this.baseDAO.update(getQueryId(queryId), params);
    }

    public int delete(Object param) {
        return this.baseDAO.delete(getQueryId("delete"), param);
    }

    public int delete(String queryId, Object param) {
        return this.baseDAO.delete(getQueryId(queryId), param);
    }

    public int delete(String queryId) {
        return this.baseDAO.delete(getQueryId(queryId));
    }

    public int getCount(String queryId, Object params) {
        Object abc = select(queryId, params);
        Integer count = (Integer)abc;
        if (count == null)
            return 0;
        return count.intValue();
    }
}
