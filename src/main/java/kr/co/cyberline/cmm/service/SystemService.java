package kr.co.cyberline.cmm.service;

import kr.co.cyberline.cmm.model.AuthorManageVO;
import kr.co.cyberline.cmm.model.SystemVO;
import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.web.model.MenuManageVO;
import kr.co.cyberline.cmm.web.model.ProgramManageVO;

import java.util.List;

public interface SystemService {

	/**
     * <p>사용자 권한에 따른 메뉴 목록 조회</p>
     *
     * @param userVO
     * @return int
     */
	List<MenuManageVO> selectUserAuthorMenuRelateList(UserVO userVO);
	
	/**
     * <p>사용자 권한에 해당하는 메뉴의 프로그램 목록 조회</p>
     *
     * @param userVO
     * @return list
     */
    List<ProgramManageVO> selectUserAuthorMenuProgramList(UserVO userVO);
    
    /**
	 * <p>로그인,로그아웃 이력 등록</p>
	 * 
	 * @param userVO
	 * @return int
	 */
	int insertLoginHist(SystemVO systemVO);
	
	/**
	 * <p>사용자권한관리 이력 등록</p>
	 * 
	 * @param SystemVO
	 * @return int
	 */
	int insertAuthorHist(SystemVO systemVO);
	
	/**
	 * <p>사용자 권한 이력 등록할 시 해당 사용자의 순번 조회</p>
	 * 
	 * @param SystemVO
	 * @return list
	 */
	int selectAuthorHistSn(SystemVO systemVO);
	
	/**
     * <p>사용자 권한 등록</p>
     *
     * @param userVO
     * @return int
     */
    int insertUserAuthor(UserVO userVO);
    
    /**
     * <p>사용자 권한 목록 조회</p>
     *
     * @param userVO
     * @return list
     */
    List<AuthorManageVO> selectUserAuthorList(UserVO userVO);
}
