package kr.co.cyberline.dao;

import kr.co.cyberline.cmm.web.dao.CylIBaseDAO;
import kr.co.cyberline.cmm.web.dao.CylIGenericDAO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public abstract class CylAbstract3DAO<T> implements CylIGenericDAO<T> {
    protected CylIBaseDAO baseDAO;

    @Resource(name = "cylcmm.baseDAO3")
    public void setBaseDAO(CylIBaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }

    /**
     * 네임 스페이스 이름
     */
    protected abstract String getNameSpace();

    /**
     * queryId를 가져온다. namespace=true 면 namespace를 붙여서 가져온다.
     *
     * @param queryId
     * @return
     */
    private String getQueryId(String queryId) {
        return this.getNameSpace() + "." + queryId;
    }

    /**
     * 하나의 객체를 가져오는 메소드 반드시 리터값을 VO가 되어야 된다.
     *
     * @param queryId
     * @param params
     * @return
     */
    protected T get(String queryId, Object params) {
        return (T) this.baseDAO.get(queryId, params);
    }

    /**
     * 목록을 조회한다.
     *
     * @param param
     * @return
     */
    public List<T> selectList(Object param) {
        return (List<T>) this.baseDAO.selectList(this.getQueryId("selectList"), param);
    }

    /**
     * 목록을 조회한다.
     *
     * @param queryId
     * @param params
     * @return
     */
    public List<T> selectList(String queryId, Object params) {
        return (List<T>) this.baseDAO.selectList(this.getQueryId(queryId), params);
    }

    /**
     * 목록의 카운트를 조회한다.
     *
     * @param param
     * @return
     */
    public int selectListCount(Object param) {
        return this.baseDAO.getCount(this.getQueryId("selectListCount"), param);
    }

    /**
     * 목록의 카운트를 조회한다.
     *
     * @param queryId
     * @param param
     * @return
     */
    protected int selectListCount(String queryId, Object param) {
        return this.baseDAO.getCount(this.getQueryId(queryId), param);
    }

    /**
     * 단일건을 조회한다.
     *
     * @param queryId
     * @param params
     * @return
     */
    public T select(String queryId, Object params) {
        return (T) this.baseDAO.selectOne(this.getQueryId(queryId), params);
    }

    /**
     * 단일건 조회 맵
     * @param queryId
     * @param params
     * @param keyProperty
     * @return
     */
    public <K, V> Map<K, V> selectMap(String queryId, Object params, String keyProperty) {
        return this.baseDAO.selectMap(queryId, params, keyProperty);
    }

    public T getById(Object param) {
        return this.get(this.getQueryId("getById"), param);
    }

    /**
     * 존재여부를 확인 한다
     * @param param
     * @return
     */
    public boolean isExist(Object param) {
        int count = this.baseDAO.getCount(this.getQueryId("isExist"), param);
        return count > 0;
    }

    /**
     * 존재여부를 확인 한다
     * @param queryId
     * @param param
     * @return
     */
    protected boolean isExist(String queryId, Object param) {
        int count = this.baseDAO.getCount(this.getQueryId(queryId), param);
        return count > 0;
    }

    /**
     * 등록한다.
     *
     * @param param
     * @return
     */
    public int insert(Object param) {
        return this.baseDAO.insert(this.getQueryId("insert"), param);
    }

    /**
     * 등록한다.
     *
     * @param queryId
     * @param params
     * @return
     */
    public int insert(String queryId, Object params) {
        return this.baseDAO.insert(this.getQueryId(queryId), params);
    }

    /**
     * 수정한다.
     *
     * @param param
     * @return
     */
    public int update(Object param) {
        return this.baseDAO.update(this.getQueryId("update"), param);
    }

    /**
     * 수정한다.
     *
     * @param queryId
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public int update(String queryId, Object params) {
        return this.baseDAO.update(this.getQueryId(queryId), params);
    }

    /**
     * 삭제한다.
     *
     * @param param
     * @return
     */
    public int delete(Object param) {
        return this.baseDAO.delete(this.getQueryId("delete"), param);
    }

    /**
     * 삭제한다.
     *
     * @param queryId
     * @param param
     * @return
     */
    public int delete(String queryId, Object param) {
        return this.baseDAO.delete(this.getQueryId(queryId), param);
    }

    /**
     * 삭제한다.
     *
     * @param queryId
     * @return
     */
    public int delete(String queryId) {
        return this.baseDAO.delete(this.getQueryId(queryId));
    }

    /**
     * 단순 카운트 조회시 사용 ex) select count(*) as cnt from user
     *
     * @param queryId
     * @param params
     * @return
     * @throws Exception
     */
    public int getCount(String queryId, Object params) {
        Object abc = select(queryId, params);
        Integer count = (Integer)abc;

        return count == null ? 0 : count.intValue();
    }
}
