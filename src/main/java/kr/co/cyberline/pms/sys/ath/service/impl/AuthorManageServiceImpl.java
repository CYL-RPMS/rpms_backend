/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 권한관리 Service 구현
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.ath.service.impl;

import kr.co.cyberline.pms.sys.ath.service.AuthorManageService;
import kr.co.cyberline.pms.sys.ath.service.AuthorManageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>권한 관리 Service 구현</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Service("authorManageService")
public class AuthorManageServiceImpl implements AuthorManageService {

    @Resource(name = "authorManageDAO")
    private AuthorManageDAO authorManageDAO;

    @Override
    public List<AuthorManageVO> selectAuthorManageList(AuthorManageVO authorManageVO) {
        return authorManageDAO.selectAuthorManageList(authorManageVO);
    }

    @Override
    public AuthorManageVO selectAuthorManageDetail(AuthorManageVO authorManageVO) {
        return authorManageDAO.selectAuthorManageDetail(authorManageVO);
    }

    @Override
    public int insertAuthorManage(AuthorManageVO authorManageVO) {
        return authorManageDAO.insertAuthorManage(authorManageVO);
    }

    @Override
    public int updateAuthorManage(AuthorManageVO authorManageVO) {
        return authorManageDAO.updateAuthorManage(authorManageVO);
    }

    @Override
    public int deleteAuthorManage(AuthorManageVO authorManageVO) {
        return authorManageDAO.deleteAuthorManage(authorManageVO);
    }
}
