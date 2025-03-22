/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 사용자 지원 컨트롤러
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.uat.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cyberline.cmm.CmmVar;
import kr.co.cyberline.cmm.jwt.security.JwtTokenProvider;
import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.web.model.MenuManageVO;
import kr.co.cyberline.pms.sys.mnu.service.MenuManageService;
import kr.co.cyberline.pms.sys.mnu.service.MenuMap;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>사용자 지원 컨트롤러</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Controller("userSupportController")
@RequestMapping(value = "/uat/usp")
@RequiredArgsConstructor
public class UserSupportController {

    @Resource(name = "menuManageService")
    private MenuManageService menuManageService;

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * <p>사용자 메뉴 조회</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/userMenuList.*")
    public ModelAndView userLoginMain(
            HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute("menuManageVO") MenuManageVO menuManageVO
    ) {

        List<Map<String, Object>> menuList = null;
        // TODO : 사용자 메뉴 목록 조회
        if ( !CollectionUtils.isEmpty(MenuMap.getInstance().getMenuList(request.getRequestedSessionId())) ){
            // 상위 메뉴 > 하위메뉴 ( 대메뉴에 대한 하위메뉴 조회 )
            //menuList = MenuMap.getInstance().getMenuList(request.getRequestedSessionId(), menuManageVO.getMenu_id(), "N");
            // 상위 메뉴 > 하위메뉴 ( 1dept 이하의 하위 메뉴 조회 )
            menuList = MenuMap.getInstance().getMenuList(request.getRequestedSessionId(), 1, "N");
        }
        model.addAttribute("menuList", menuList);
        return new ModelAndView("jsonViewExtension", model);
    }

    @RequestMapping(value = "/MenuList.*")
    public ModelAndView MenuList(
            HttpServletRequest request
            , ModelMap model
            , @ModelAttribute("menuManageVO") MenuManageVO menuManageVO
    ) {
        UserVO userVO = new UserVO();
        String authorId;

        String token = jwtTokenProvider.resolveToken(request);
        boolean valid = jwtTokenProvider.validateToken(token);
        if(!valid) {
            userVO.setAuthor_id("ROLE_ANONYMOUS");
        } else {
            authorId = jwtTokenProvider.getAuthorIdFromToken(token);
            userVO.setAuthor_id(authorId);
        }

        List<MenuManageVO> menuList = menuManageService.selectUserAuthorMenuRelateList(userVO);
        // TODO :  궗 슜 옄 沅뚰븳 쓽 硫붾돱 紐⑸줉 Cache Map
        MenuMap.getInstance().setMenuList(userVO.getUser_id(), menuList);
        MenuMap.getInstance().setMenuList(request.getRequestedSessionId(), menuList);

        model.addAttribute(CmmVar.MENU_LIST, menuList);
        model.addAttribute(CmmVar.MENU_MAP, MenuMap.getInstance().getMenuList(userVO.getUser_id(), 1, "N"));
        return new ModelAndView("jsonView", model);
    }
}