package kr.co.cyberline.pms.sys.log.service;

import java.util.List;


/**
 * <p> 사용자권한 이력 관리 Service Interface</p>
 *
 * @author 김 학 성 
 * @since 2015. 10. 13
 * @version 1.0
 */
public interface AuthorHistService {
	
	/**
	 * <p>사용자권한관리 이력 등록</p>
	 * 
	 * @param userAuthorHistVO
	 * @return int
	 */
	int insertAuthorHist(AuthorHistVO authorHistVO);
	
	/**
	 * <p>사용자권한이력 목록 조회</p>
	 * 
	 * @param authorHistVO
	 * @return list
	 */
	List<AuthorHistVO> selectAuthorHistList(AuthorHistVO authorHistVO);
	
	/**
	 * <p>사용자 권한 이력 등록할 시 해당 사용자의 순번 조회</p>
	 * 
	 * @param authorHistVO
	 * @return list
	 */
	int selectAuthorHistSn(AuthorHistVO authorHistVO);
}
