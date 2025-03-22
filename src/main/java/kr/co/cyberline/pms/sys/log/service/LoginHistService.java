package kr.co.cyberline.pms.sys.log.service;

import java.util.List;

/**
 * <p>로그인 이력 관리 Service Interface</p>
 *
 * @author 김 학 성 
 * @since 2015. 10. 13
 * @version 1.0
 */
public interface LoginHistService {
	
	/**
	 * <p>로그인,로그아웃 이력 등록</p>
	 * 
	 * @param userVO
	 * @return int
	 */
	int insertLoginHist(LoginHistVO loginHistVO);
	
	/**
	 * <p>로그인이력 목록 조회</p>
	 * 
	 * @param loginHistVO
	 * @return list
	 */
	List<LoginHistVO> selectLoginHistList(LoginHistVO loginHistVO);
	/*[통신분쟁]2021-08-23 / Kait-62 / by ihpark / 개인정보 관련 이력*/
	int insertSessionTimeOut(LoginHistVO loginHistVO);
	
}
