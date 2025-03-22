package kr.co.cyberline.cmm.service.impl;

import kr.co.cyberline.cmm.model.AuthorManageVO;
import kr.co.cyberline.cmm.model.SystemVO;
import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.service.SystemService;
import kr.co.cyberline.cmm.web.model.MenuManageVO;
import kr.co.cyberline.cmm.web.model.ProgramManageVO;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("systemService")
public class SystemServiceImple implements SystemService {
	
	@Resource(name = "systemDAO")
	private SystemDAO systemDAO;
	
	@Override
    public List<MenuManageVO> selectUserAuthorMenuRelateList(UserVO userVO) {
        return systemDAO.selectUserAuthorMenuRelateList(userVO);
    }
	
	@Override
	public List<ProgramManageVO> selectUserAuthorMenuProgramList(UserVO userVO) {
	    return systemDAO.selectUserAuthorMenuProgramList(userVO);
	}
	
	@Override
	public int insertLoginHist(SystemVO systemVO) {
		return systemDAO.insertLoginHist(systemVO);
	}
	
	@Override
	public int insertAuthorHist(SystemVO systemVO) {
		int rs = 0;
		if ( systemVO != null ){
			int sn = selectAuthorHistSn(systemVO); // 권한이력 등록 시 해당 사용자의 순번 조회 및 지정
			systemVO.setAuthor_sn(sn); // 순번
			if( ArrayUtils.isNotEmpty(systemVO.getAuthorIds())){
				for( String authorId : systemVO.getAuthorIds() ){
					systemVO.setAuthor_id(authorId);
					systemDAO.insertAuthorHist(systemVO);
				}
			}
		}
		return rs;
	}
	
	@Override
	public int selectAuthorHistSn(SystemVO systemVO) {
		return systemDAO.selectAuthorHistSn(systemVO);
	}
	
	@Override
    public int insertUserAuthor(UserVO userVO) {
        int rs = 0;
        if ( userVO != null && ArrayUtils.isNotEmpty(userVO.getAuthorIds()) ){
            for ( String authorId : userVO.getAuthorIds() ){
                userVO.setAuthor_id(authorId);
                rs += systemDAO.insertUserAuthor(userVO);
            }
        }
        return rs;
    }
	
	@Override
    public List<AuthorManageVO> selectUserAuthorList(UserVO userVO) {
        return systemDAO.selectUserAuthorList(userVO);
    }
}
