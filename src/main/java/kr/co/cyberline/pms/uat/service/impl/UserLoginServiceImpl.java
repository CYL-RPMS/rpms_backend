package kr.co.cyberline.pms.uat.service.impl;

import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.pms.uat.service.UserLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>로그인 Service 구현</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

    @Resource(name = "userLoginDAO")
    private UserLoginDAO userLoginDAO;

    @Override
    public UserVO actionLogin(UserVO userVO) {
        return userLoginDAO.actionLogin(userVO);
    }

    /*[통신분쟁]2021-08-23 / Kait-63 / by ihpark / 로그인 횟수제한 및 비밀번호 변경*/
    @Override
    public UserVO selectLoginHist(String user_id) {
        return userLoginDAO.selectLoginHist(user_id);
    }

    @Override
    public int insertHist(String user_id) {
        return userLoginDAO.insertHist(user_id);
    }

    @Override
    public int updateFailCnt(String user_id) {
        return userLoginDAO.updateFailCnt(user_id);
    }

    @Override
    public int updateIsLock(String user_id) {
        return userLoginDAO.updateIsLock(user_id);
    }

    @Override
    public int deleteHist(String user_id) {
        return userLoginDAO.deleteHist(user_id);
    }

    @Override
    public UserVO findByRefreshToken(String refresh_token)  {
        return userLoginDAO.findByRefreshToken(refresh_token);
    };

    @Override
    public int updateRefreshToken(UserVO userVO)  {
        return userLoginDAO.updateRefreshToken(userVO);
    };

    @Override
    public int insertLoginHist(Object param) {
        return userLoginDAO.insertLoginHist(param);
    }

}
