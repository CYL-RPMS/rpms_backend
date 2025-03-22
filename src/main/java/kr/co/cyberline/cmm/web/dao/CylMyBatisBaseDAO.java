package kr.co.cyberline.cmm.web.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class CylMyBatisBaseDAO implements CylIBaseDAO {

    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Object getMapper(Class clazz) {
        return this.sqlSession.getMapper(clazz);
    }

    public int insert(String queryId) {
        return this.sqlSession.insert(queryId);
    }

    public int insert(String queryId, Object parameterObject) {
        return this.sqlSession.insert(queryId, parameterObject);
    }

    public int update(String queryId) {
        return this.sqlSession.update(queryId);
    }

    public int update(String queryId, Object parameterObject) {
        return this.sqlSession.update(queryId, parameterObject);
    }

    public int delete(String queryId) {
        return this.sqlSession.delete(queryId);
    }

    public int delete(String queryId, Object parameterObject) {
        return this.sqlSession.delete(queryId, parameterObject);
    }

    public Object selectOne(String queryId) {
        return this.sqlSession.selectOne(queryId);
    }

    public Object selectOne(String queryId, Object parameterObject) {
        return this.sqlSession.selectOne(queryId, parameterObject);
    }

    public <K, V> Map<K, V> selectMap(String queryId, Object parameterObject, String keyProperty) {
        return this.sqlSession.selectMap(queryId, parameterObject, keyProperty);
    }

    public List<?> selectList(String queryId) {
        return this.sqlSession.selectList(queryId);
    }

    public List<?> selectList(String queryId, Object parameterObject) {
        return this.sqlSession.selectList(queryId, parameterObject);
    }

    public int getCount(String queryId, Object params) {
        Object abc = this.sqlSession.selectOne(queryId, params);
        Integer count = (Integer)abc;
        if (count == null)
            return 0;
        return count.intValue();
    }

    public Map<?, ?> map(String queryId, Object parameterObject, String keyProperty) {
        return this.sqlSession.selectMap(queryId, parameterObject, keyProperty);
    }

    public Object get(String queryId, Object params) {
        return this.sqlSession.selectOne(queryId, params);
    }
}
