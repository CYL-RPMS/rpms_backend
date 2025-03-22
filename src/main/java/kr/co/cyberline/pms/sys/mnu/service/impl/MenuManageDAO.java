/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 메뉴관리 Data Access Object
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.mnu.service.impl;

import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.web.dao.CylAbstractDAO;
import kr.co.cyberline.cmm.web.model.MenuManageVO;
import kr.co.cyberline.pms.sys.ath.service.AuthorManageVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>메뉴 관리 Data Access Object</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Repository("menuManageDAO")
public class MenuManageDAO extends CylAbstractDAO<MenuManageVO> {

    @Override
    protected String getNameSpace() {
        return "MenuManage";
    }

    /**
     * <p>메뉴관리 목록 조회</p>
     *
     * @param menuManageVO
     * @return list
     */
    public List<MenuManageVO> selectMenuManageList(MenuManageVO menuManageVO) {
        return selectList("selectMenuManageList", menuManageVO);
    }

    /**
     * <p>권한에 따른 메뉴 목록 조회</p>
     *
     * @param authorManageVO
     * @return int
     */
    public List<MenuManageVO> selectAuthorMenuManageList(AuthorManageVO authorManageVO) {
        return selectList("selectAuthorMenuManageList", authorManageVO);
    }

    /**
     * <p>사용자 권한에 따른 메뉴 목록 조회</p>
     *
     * @param userVO
     * @return int
     */
    public List<MenuManageVO> selectUserAuthorMenuRelateList(UserVO userVO) {
        return selectList("selectUserAuthorMenuRelateList", userVO);
    }

    /**
     * <p>메뉴관리 상세 조회</p>
     *
     * @param menuManageVO
     * @return menuManageVO
     */
    public MenuManageVO selectMenuManageDetail(MenuManageVO menuManageVO) {
        return select("selectMenuManageDetail", menuManageVO);
    }

    /**
     * <p>메뉴관리 등록</p>
     *
     * @param menuManageVO
     * @return int
     */
    public int insertMenuManage(MenuManageVO menuManageVO) {
        return insert("insertMenuManage", menuManageVO);
    }

    /**
     * <p>메뉴권한 등록</p>
     *
     * @param menuManageVO
     * @return int
     */
    public int insertMenuAuthorRelate(MenuManageVO menuManageVO) {
        return insert("insertMenuAuthorRelate", menuManageVO);
    }

    /**
     * <p>메뉴에 대한 프로그램 등록</p>
     *
     * @param menuManageVO
     * @return int
     */
    public int insertMenuProgramRelate(MenuManageVO menuManageVO) {
        return insert("insertMenuProgramRelate", menuManageVO);
    }

    /**
     * <p>메뉴관리 수정</p>
     *
     * @param menuManageVO
     * @return int
     */
    public int updateMenuManage(MenuManageVO menuManageVO) {
        return update("updateMenuManage", menuManageVO);
    }

    /**
     * <p>메뉴관리 삭제</p>
     *
     * @param menuManageVO
     * @return int
     */
    public int deleteMenuManage(MenuManageVO menuManageVO) {
        if ( menuManageVO != null ){
            MenuManageVO mvo = new MenuManageVO();
            mvo.setUpr_menu_id(menuManageVO.getMenu_id());
            List<MenuManageVO> subList = selectSubMenuList(mvo);
            if (CollectionUtils.isNotEmpty(subList)) {
                int size = subList.size();
                for ( int i = 0 ; i < size; i++ ) {
                    if ( i < (size - 1) ) {
                        MenuManageVO vo = subList.get(i);
                        deleteMenuManage(vo);
                    } else {
                        break;
                    }
                }
            }
            return deleteMenuProgramAuthorRelate(menuManageVO) +
                    deleteMenuAuthorRelate(menuManageVO) +
                    deleteMenuProgramRelate(menuManageVO) +
                    delete("deleteMenuManage", menuManageVO);
        }
        return 0;
    }

    /**
     * 하위 메뉴 목록 조회
     * @param menuManageVO
     * @return
     */
    public List<MenuManageVO> selectSubMenuList(MenuManageVO menuManageVO){
        return selectList("selectSubMenuList", menuManageVO);
    }

    /**
     * <p>권한에 대한 메뉴 관계 삭제</p>
     *
     * @param menuManageVO
     * @return int
     */
    public int deleteMenuAuthorRelate(MenuManageVO menuManageVO) {
        return delete("deleteMenuAuthorRelate", menuManageVO);
    }

    /**
     * <p>메뉴에 대한 프로그램 삭제</p>
     *
     * @param menuManageVO
     * @return int
     */
    public int deleteMenuProgramRelate(MenuManageVO menuManageVO) {
        return delete("deleteMenuProgramRelate", menuManageVO);
    }

    /**
     * <p>메뉴에 대한 프로그램 권한 관계 삭제</p>
     *
     * @param menuManageVO
     * @return int
     */
    public int deleteMenuProgramAuthorRelate(MenuManageVO menuManageVO){
        return delete("deleteMenuProgramAuthorRelate", menuManageVO);
    }
}
