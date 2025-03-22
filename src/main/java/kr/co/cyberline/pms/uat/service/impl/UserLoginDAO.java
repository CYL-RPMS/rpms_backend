package kr.co.cyberline.pms.uat.service.impl;

import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.web.dao.CylAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>로그인 Data Access Object</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Repository("userLoginDAO")
public class UserLoginDAO extends CylAbstractDAO<UserVO> {

    @Override
    protected String getNameSpace() {
        return "UserLogin";
    }

    /**
     * <p>사용자 로그인 처리</p>
     *
     * @param userVO
     * @return userVO
     */
    public UserVO actionLogin(UserVO userVO) {
        return select("actionLogin", userVO);
    }

    /**
     * <p>사용자 권한 목록 조회</p>
     *
     * @param userVO
     * @return userVO
     */
    public List<UserVO> selectUserAuthorList(UserVO userVO) {
        return selectList("selectUserAuthorList", userVO);
    }

    /*[통신분쟁]2021-08-23 / Kait-63 / by ihpark / 로그인 횟수제한 및 비밀번호 변경*/
    public UserVO selectLoginHist(String user_id) {
        return select("selectLoginHist", user_id);
    }
    public int insertHist(String user_id) {
        return insert("insertHist", user_id);
    }
    public int updateFailCnt(String user_id) {
        return update("updateFailCnt", user_id);
    }
    public int updateIsLock(String user_id) {
        return update("updateIsLock", user_id);
    }
    public int deleteHist(String user_id) {
        return delete("deleteHist", user_id);
    }
    public UserVO findByRefreshToken(String refresh_token) {
        return select("findByRefreshToken", refresh_token);
    }

    int updateRefreshToken(UserVO userVO) {
        return update("updateRefreshToken", userVO);
    }

    int insertLoginHist(Object param) {
        return insert("insertLoginHist", param);
    }

}
