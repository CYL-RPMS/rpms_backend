/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 권한관리 Service Interface
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.ath.service;

import java.util.List;

/**
 * <p>권한 관리 Service Interface</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
public interface AuthorManageService {

    /**
     * <p>권한관리 목록 조회</p>
     *
     * @param authorManageVO
     * @return list
     */
    List<AuthorManageVO> selectAuthorManageList(AuthorManageVO authorManageVO);

    /**
     * <p>권한관리 상세 조회</p>
     *
     * @param authorManageVO
     * @return authorManageVO
     */
    AuthorManageVO selectAuthorManageDetail(AuthorManageVO authorManageVO);

    /**
     * <p>권한관리 등록</p>
     *
     * @param authorManageVO
     * @return int
     */
    int insertAuthorManage(AuthorManageVO authorManageVO);

    /**
     * <p>권한관리 수정</p>
     *
     * @param authorManageVO
     * @return int
     */
    int updateAuthorManage(AuthorManageVO authorManageVO);

    /**
     * <p>권한관리 삭제</p>
     *
     * @param authorManageVO
     * @return int
     */
    int deleteAuthorManage(AuthorManageVO authorManageVO);
}
