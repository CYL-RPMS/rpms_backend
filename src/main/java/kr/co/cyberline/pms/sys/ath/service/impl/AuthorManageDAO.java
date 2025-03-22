/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 권한관리 Data Access Object
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.ath.service.impl;

import kr.co.cyberline.cmm.web.dao.CylAbstractDAO;
import kr.co.cyberline.pms.sys.ath.service.AuthorManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>권한 관리 Data Access Object</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Repository("authorManageDAO")
public class AuthorManageDAO extends CylAbstractDAO<AuthorManageVO> {

    @Override
    protected String getNameSpace() {
        return "AuthorManage";
    }

    /**
     * <p>권한관리 목록 조회</p>
     *
     * @param authorManageVO
     * @return list
     */
    public List<AuthorManageVO> selectAuthorManageList(AuthorManageVO authorManageVO) {
        return selectList("selectAuthorManageList", authorManageVO);
    }

    /**
     * <p>권한관리 상세 조회</p>
     *
     * @param authorManageVO
     * @return authorManageVO
     */
    public AuthorManageVO selectAuthorManageDetail(AuthorManageVO authorManageVO) {
        return select("selectAuthorManageDetail", authorManageVO);
    }

    /**
     * <p>권한관리 등록</p>
     *
     * @param authorManageVO
     * @return int
     */
    public int insertAuthorManage(AuthorManageVO authorManageVO) {
        return insert("insertAuthorManage", authorManageVO);
    }

    /**
     * <p>권한관리 수정</p>
     *
     * @param authorManageVO
     * @return int
     */
    public int updateAuthorManage(AuthorManageVO authorManageVO) {
        return update("updateAuthorManage", authorManageVO);
    }

    /**
     * <p>권한관리 삭제</p>
     *
     * @param authorManageVO
     * @return int
     */
    public int deleteAuthorManage(AuthorManageVO authorManageVO) {
        return delete("deleteAuthorManage", authorManageVO);
    }
}
