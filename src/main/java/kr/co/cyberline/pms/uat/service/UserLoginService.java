package kr.co.cyberline.pms.uat.service;

import kr.co.cyberline.cmm.model.UserVO;

public interface UserLoginService {

    UserVO actionLogin(UserVO userVO);
    /*[통신분쟁]2021-08-23 / Kait-63 / by ihpark / 로그인 횟수제한 및 비밀번호 변경*/
    UserVO selectLoginHist(String user_id);
    int insertHist(String user_id);
    int updateFailCnt(String user_id);
    int updateIsLock(String user_id);
    int deleteHist(String user_id);
    UserVO findByRefreshToken(String refresh_token);
    int updateRefreshToken(UserVO userVO);
    int insertLoginHist(Object param);
}
