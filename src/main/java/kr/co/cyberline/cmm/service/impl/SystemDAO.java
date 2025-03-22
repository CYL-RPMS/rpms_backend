package kr.co.cyberline.cmm.service.impl;

import kr.co.cyberline.cmm.model.AuthorManageVO;
import kr.co.cyberline.cmm.model.SystemVO;
import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.web.dao.CylAbstractDAO;
import kr.co.cyberline.cmm.web.model.MenuManageVO;
import kr.co.cyberline.cmm.web.model.ProgramManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("systemDAO")
public class SystemDAO<T> extends CylAbstractDAO<T> {
		
	protected String nameSpace;
	
	@Override
    protected String getNameSpace() {
        return this.nameSpace;
    }
	
	/**
     * <p>사용자 권한에 따른 메뉴 목록 조회</p>
     *
     * @param userVO
     * @return int
     */
    public List<MenuManageVO> selectUserAuthorMenuRelateList(UserVO userVO) {
    	this.nameSpace = "MenuManage";
    	
        return (List<MenuManageVO>) selectList("selectUserAuthorMenuRelateList", userVO);
    }
    
    /**
     * <p>사용자 권한에 해당하는 메뉴의 프로그램 목록 조회</p>
     *
     * @param userVO
     * @return programManageVO
     */
    public List<ProgramManageVO> selectUserAuthorMenuProgramList(UserVO userVO) {
    	this.nameSpace = "ProgramManage";
    	
        return (List<ProgramManageVO>) selectList("selectUserAuthorMenuProgramList", userVO);
    }
    
    /**
	 * <p>로그인,로그아웃 이력 등록</p>
	 * @param SystemVO
	 * @return int
	 */
	public int insertLoginHist(SystemVO systemVO) {
		this.nameSpace = "LoginHist";
		
		return insert("insertLoginHist", systemVO);
	}
	
	/**
	 * <p>사용자 권한 이력 등록</p>
	 * @param SystemVO
	 * @return int
	 */
	public int insertAuthorHist(SystemVO systemVO) {
		this.nameSpace = "AuthorHist";
		
		return insert("insertAuthorHist", systemVO);
	}
	
	/**
	 * <p>사용자 권한 이력 등록할 시 해당 사용자의 순번 조회</p>
	 * @param SystemVO
	 * @return int
	 */
	public int selectAuthorHistSn(SystemVO systemVO) {
		this.nameSpace = "AuthorHist";
		
		return selectListCount("selectAuthorHistSn", systemVO);
	}
	
	/**
     * <p>사용자 권한 등록</p>
     *
     * @param userVO
     * @return int
     */
    public int insertUserAuthor(UserVO userVO) {
    	this.nameSpace = "UserManage";
    	
        return insert("insertUserAuthor", userVO);
    }
    
    /**
     * <p>사용자 권한 목록 조회</p>
     *
     * @param userVO
     * @return list
     */
    public List<AuthorManageVO> selectUserAuthorList(UserVO userVO) {
    	this.nameSpace = "UserManage";
    	
        return (List<AuthorManageVO>) selectList("selectUserAuthorList", userVO);
    }
}
