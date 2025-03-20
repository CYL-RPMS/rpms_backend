package kr.co.cyberline.dao;

import kr.co.cyberline.cmm.web.dao.CylIBaseDAO;
import org.apache.ibatis.session.SqlSession;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class CylMyBatisBase2DAO implements CylIBaseDAO {
    private SqlSession sqlSession;

    @Resource(name = "cylcmm.sqlSession2")
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    /**
     * mapper interface 사용
     * @param clazz
     * @return
     */
    public Object getMapper(Class clazz) {
        return this.sqlSession.getMapper(clazz);
    }

    /**
     * 입력 처리 SQL mapping 을 실행한다.
     *
     * @param queryId - 입력 처리 SQL mapping 쿼리 ID
     * @return 입력 시 selectKey 를 사용하여 key 를 딴 경우 해당 key
     */
    public int insert(String queryId) {
        return this.sqlSession.insert(queryId);
    }

    /**
     * 입력 처리 SQL mapping 을 실행한다.
     *
     * @param queryId - 입력 처리 SQL mapping 쿼리 ID
     * @param parameterObject - 입력 처리 SQL mapping 입력 데이터를 세팅한 파라메터 객체(보통 VO 또는 Map)
     * @return 입력 시 selectKey 를 사용하여 key 를 딴 경우 해당 key
     */
    public int insert(String queryId, Object parameterObject) {
        return this.sqlSession.insert(queryId, parameterObject);
    }

    /**
     * 수정 처리 SQL mapping 을 실행한다.
     *
     * @param queryId - 수정 처리 SQL mapping 쿼리 ID
     * @return DBMS가 지원하는 경우 update 적용 결과 count
     */
    public int update(String queryId) {
        return this.sqlSession.update(queryId);
    }

    /**
     * 수정 처리 SQL mapping 을 실행한다.
     *
     * @param queryId - 수정 처리 SQL mapping 쿼리 ID
     * @param parameterObject - 수정 처리 SQL mapping 입력 데이터(key 조건 및 변경 데이터)를 세팅한 파라메터 객체(보통 VO 또는 Map)
     * @return DBMS가 지원하는 경우 update 적용 결과 count
     */
    public int update(String queryId, Object parameterObject) {
        return this.sqlSession.update(queryId, parameterObject);
    }

    /**
     * 삭제 처리 SQL mapping 을 실행한다.
     *
     * @param queryId - 삭제 처리 SQL mapping 쿼리 ID
     * @return DBMS가 지원하는 경우 delete 적용 결과 count
     */
    public int delete(String queryId) {
        return this.sqlSession.delete(queryId);
    }

    /**
     * 삭제 처리 SQL mapping 을 실행한다.
     *
     * @param queryId - 삭제 처리 SQL mapping 쿼리 ID
     * @param parameterObject - 삭제 처리 SQL mapping 입력 데이터(일반적으로 key 조건)를 세팅한 파라메터 객체(보통 VO 또는 Map)
     * @return DBMS가 지원하는 경우 delete 적용 결과 count
     */
    public int delete(String queryId, Object parameterObject) {
        return this.sqlSession.delete(queryId, parameterObject);
    }

    /**
     * 단건조회 처리 SQL mapping 을 실행한다.
     *
     * @param queryId - 단건 조회 처리 SQL mapping 쿼리 ID
     * @return 결과 객체 - SQL mapping 파일에서 지정한 resultClass/resultMap 에 의한 단일 결과 객체(보통 VO 또는 Map)
     */
    public Object selectOne(String queryId) {
        return this.sqlSession.selectOne(queryId);
    }

    /**
     * 단건조회 처리 SQL mapping 을 실행한다.
     *
     * @param queryId - 단건 조회 처리 SQL mapping 쿼리 ID
     * @param parameterObject - 단건 조회 처리 SQL mapping 입력 데이터(key)를 세팅한 파라메터 객체(보통 VO 또는 Map)
     * @return 결과 객체 - SQL mapping 파일에서 지정한 resultClass/resultMap 에 의한 단일 결과 객체(보통 VO 또는 Map)
     */
    public Object selectOne(String queryId, Object parameterObject) {
        return this.sqlSession.selectOne(queryId, parameterObject);
    }

    /**
     * 단건 조회 Map
     * @param queryId
     * @param parameterObject
     * @param keyProperty
     * @return
     */
    public <K, V> Map<K, V> selectMap(String queryId, Object parameterObject, String keyProperty) {
        return this.sqlSession.selectMap(queryId, parameterObject, keyProperty);
    }

    /**
     * 리스트 조회 처리 SQL mapping 을 실행한다.
     *
     * @param queryId - 리스트 조회 처리 SQL mapping 쿼리 ID
     * @return 결과 List 객체 - SQL mapping 파일에서 지정한 resultClass/resultMap 에 의한 결과 객체(보통 VO 또는 Map)의 List
     */
    public List<?> selectList(String queryId) {
        return this.sqlSession.selectList(queryId);
    }

    /**
     * 리스트 조회 처리 SQL mapping 을 실행한다.
     *
     * @param queryId - 리스트 조회 처리 SQL mapping 쿼리 ID
     * @param parameterObject - 리스트 조회 처리 SQL mapping 입력 데이터(조회 조건)를 세팅한 파라메터 객체(보통 VO 또는 Map)
     * @return 결과 List 객체 - SQL mapping 파일에서 지정한 resultClass/resultMap 에 의한 결과 객체(보통 VO 또는 Map)의 List
     */
    public List<?> selectList(String queryId, Object parameterObject) {
        return this.sqlSession.selectList(queryId, parameterObject);
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
        Object abc = this.sqlSession.selectOne(queryId, params);
        Integer count = (Integer)abc;
        return count == null ? 0 : count;
    }

    /**
     * 리스트 조회 처리 SQL mapping 을 실행한다.
     *
     * @param queryId - 리스트 조회 처리 SQL mapping 쿼리 ID
     * @param parameterObject - 입력 처리 SQL mapping 입력 데이터를 세팅한 파라메터 객체(보통 VO 또는 Map)
     * @param keyProperty - key값이 될 field
     * @return 결과 List 객체 - SQL mapping 파일에서 지정한 resultClass/resultMap 에 의한 결과 객체(보통 VO 또는 Map)의 Map
     */
    public Map<?, ?> map(String queryId, Object parameterObject, String keyProperty) {
        return this.sqlSession.selectMap(queryId, parameterObject, keyProperty);
    }

    /**
     * 하나의 객체를 가져오는 메소드
     * 반드시 리터값을 VO가 되어야 된다.
     * @param queryId
     * @param params
     * @return
     */
    public Object get(String queryId, Object params) {
        return this.sqlSession.selectOne(queryId, params);
    }
}
