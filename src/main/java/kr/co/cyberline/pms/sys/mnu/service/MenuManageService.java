/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 메뉴 관리 Service Interface
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.mnu.service;

import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.web.model.MenuManageVO;
import kr.co.cyberline.pms.sys.ath.service.AuthorManageVO;

import java.util.List;

/**
 * <p>메뉴 관리 Service Interface</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
public interface MenuManageService {

    /**
     * <p>메뉴관리 목록 조회</p>
     *
     * @param menuManageVO
     * @return list
     */
    List<MenuManageVO> selectMenuManageList(MenuManageVO menuManageVO);

    /**
     * <p>권한에 따른 메뉴 목록 조회</p>
     *
     * @param authorManageVO
     * @return int
     */
    List<MenuManageVO> selectAuthorMenuManageList(AuthorManageVO authorManageVO);

    /**
     * <p>사용자 권한에 따른 메뉴 목록 조회</p>
     *
     * @param userVO
     * @return int
     */
    List<MenuManageVO> selectUserAuthorMenuRelateList(UserVO userVO);

    /**
     * <p>메뉴관리 상세 조회</p>
     *
     * @param menuManageVO
     * @return menuManageVO
     */
    MenuManageVO selectMenuManageDetail(MenuManageVO menuManageVO);

    /**
     * <p>메뉴관리 등록</p>
     *
     * @param menuManageVO
     * @return int
     */
    int insertMenuManage(MenuManageVO menuManageVO);

    /**
     * <p>권한에 대한 메뉴정보 등록</p>
     *
     * @param menuManageVO
     * @return int
     */
    int insertMenuAuthorRelate(MenuManageVO menuManageVO);

    /**
     * <p>메뉴관리 수정</p>
     *
     * @param menuManageVO
     * @return int
     */
    int updateMenuManage(MenuManageVO menuManageVO);

    /**
     * <p>메뉴관리 삭제</p>
     *
     * @param menuManageVO
     * @return int
     */
    int deleteMenuManage(MenuManageVO menuManageVO);

    /**
     * <p>권한에 대한 메뉴 정보 삭제</p>
     *
     * @param menuManageVO
     * @return int
     */
    int deleteMenuAuthorRelate(MenuManageVO menuManageVO);
}
