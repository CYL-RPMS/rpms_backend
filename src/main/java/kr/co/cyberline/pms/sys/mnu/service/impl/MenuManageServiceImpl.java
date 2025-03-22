/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 메뉴관리 Service 구현
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.mnu.service.impl;

import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.web.model.MenuManageVO;
import kr.co.cyberline.pms.sys.mnu.service.MenuManageService;
import kr.co.cyberline.pms.sys.ath.service.AuthorManageVO;
import kr.co.cyberline.pms.sys.pgm.service.impl.ProgramManageDAO;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>메뉴 관리 Service 구현</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Service("menuManageService")
public class MenuManageServiceImpl implements MenuManageService {

    @Resource(name = "programManageDAO")
    private ProgramManageDAO programManageDAO;

    @Resource(name = "menuManageDAO")
    private MenuManageDAO menuManageDAO;


    @Override
    public List<MenuManageVO> selectMenuManageList(MenuManageVO menuManageVO) {
        return menuManageDAO.selectMenuManageList(menuManageVO);
    }

    @Override
    public List<MenuManageVO> selectAuthorMenuManageList(AuthorManageVO authorManageVO) {
        return menuManageDAO.selectAuthorMenuManageList(authorManageVO);
    }

    @Override
    public List<MenuManageVO> selectUserAuthorMenuRelateList(UserVO userVO) {
        return menuManageDAO.selectUserAuthorMenuRelateList(userVO);
    }

    @Override
    public MenuManageVO selectMenuManageDetail(MenuManageVO menuManageVO) {
        return menuManageDAO.selectMenuManageDetail(menuManageVO);
    }

    @Override
    public int insertMenuManage(MenuManageVO menuManageVO) {
        int rs = 0;
        if ( menuManageVO != null ){
            rs = menuManageDAO.insertMenuManage(menuManageVO);

            if ( ArrayUtils.isNotEmpty(menuManageVO.getPrgmIdArray()) ){
                for ( String prgmId : menuManageVO.getPrgmIdArray() ){
                    menuManageVO.setPrgm_id(prgmId);
                    rs += menuManageDAO.insertMenuProgramRelate(menuManageVO);
                }
            }
        }
        return rs;
    }

    @Override
    public int insertMenuAuthorRelate(MenuManageVO menuManageVO) {
        return menuManageDAO.insertMenuAuthorRelate(menuManageVO);
    }

    @Override
    public int updateMenuManage(MenuManageVO menuManageVO) {
        int rs = 0;
        if ( menuManageVO != null ){
            rs = menuManageDAO.updateMenuManage(menuManageVO);
            menuManageDAO.deleteMenuProgramRelate(menuManageVO);
            if ( ArrayUtils.isNotEmpty(menuManageVO.getPrgmIdArray()) ){
                for ( String prgmId : menuManageVO.getPrgmIdArray() ){
                    menuManageVO.setPrgm_id(prgmId);
                    rs += menuManageDAO.insertMenuProgramRelate(menuManageVO);
                }
            }
        }
        return rs;
    }

    @Override
    public int deleteMenuManage(MenuManageVO menuManageVO) {
        return menuManageDAO.deleteMenuManage(menuManageVO);
    }

    @Override
    public int deleteMenuAuthorRelate(MenuManageVO menuManageVO) {
        return menuManageDAO.deleteMenuAuthorRelate(menuManageVO);
    }
}
