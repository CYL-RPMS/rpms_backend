/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 권한관리 컨트롤러
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.ath.web;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cyberline.cmm.web.model.MenuManageVO;
import kr.co.cyberline.cmm.web.model.ProgramManageVO;
import kr.co.cyberline.cmm.web.pagination.CylPaginationSupport;
import kr.co.cyberline.pms.sys.ath.service.AuthorManageService;
import kr.co.cyberline.pms.sys.ath.service.AuthorManageVO;
import kr.co.cyberline.pms.sys.mnu.service.MenuManageService;
import kr.co.cyberline.pms.sys.pgm.service.ProgramManageService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>권한 관리 컨트롤러</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Controller("authorManageController")
@RequestMapping(value = "/sys/ath")
public class AuthorManageController {

    @Resource(name = "authorManageService")
    private AuthorManageService authorManageService;

    @Resource(name = "menuManageService")
    private MenuManageService menuManageService;

    @Resource(name = "programManageService")
    private ProgramManageService programManageService;

    /**
     * <p>권한관리 목록 화면</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/list.*")
    public ModelAndView list(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute AuthorManageVO authorManageVO
    )  {

        CylPaginationSupport.setPaginationVO(
                request, authorManageVO);
        List<AuthorManageVO> list = authorManageService.selectAuthorManageList(authorManageVO);

        model.addAttribute("list", list);
        model.addAttribute("authorManageVO", authorManageVO);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>권한관리 상세 화면</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/detail.*")
    public ModelAndView detail(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute AuthorManageVO authorManageVO
    )  {
        AuthorManageVO detail = authorManageService.selectAuthorManageDetail(authorManageVO);
        // 권한에 따른 메뉴 목록 조회
        List<MenuManageVO> menuList = menuManageService.selectAuthorMenuManageList(authorManageVO);

        if ( CollectionUtils.isNotEmpty(menuList) ){
            for ( MenuManageVO menuManageVO : menuList ){
                // TODO : 권한에 대한 메뉴 프로그램 목록 조회
                ProgramManageVO programManageVO = new ProgramManageVO();
                programManageVO.setMenu_id(menuManageVO.getMenu_id());
                programManageVO.setAuthor_id(authorManageVO.getAuthor_id());
                menuManageVO.setPrgmList(programManageService.selectAuthorMenuProgramList(programManageVO));
            }
        }

        model.addAttribute("vo", detail);
        model.addAttribute("menuList", menuList);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>권한관리 등록 화면</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/create.*")
    public ModelAndView create(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
    )  {
    	//전체 메뉴목록조회
        MenuManageVO menuManageVO = new MenuManageVO();
        menuManageVO.setPagingEnable(menuManageVO.PAGING_ENABLE_OFF);
        
        List<MenuManageVO> menuList = menuManageService.selectMenuManageList(menuManageVO);
        if( CollectionUtils.isNotEmpty(menuList) ){
	        for ( MenuManageVO vo : menuList ){
	            // TODO : 메뉴 프로그램 목록 조회
	            ProgramManageVO programManageVO = new ProgramManageVO();
	            programManageVO.setMenu_id(vo.getMenu_id());
	            vo.setPrgmList(programManageService.selectMenuProgramList(programManageVO));
	        }
        }

        model.addAttribute("mode", "CREATE");
        model.addAttribute("menuList", menuList);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>권한관리 수정 화면</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/update.*")
    public ModelAndView update(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute AuthorManageVO authorManageVO
    )  {
        // 권한 상세 정보
        AuthorManageVO detail = authorManageService.selectAuthorManageDetail(authorManageVO);
        // 권한에 따른 메뉴 목록 조회
        List<MenuManageVO> menuList = menuManageService.selectAuthorMenuManageList(authorManageVO);
        if ( CollectionUtils.isNotEmpty(menuList) ){
            for ( MenuManageVO menuManageVO : menuList ){
                // TODO : 권한에 대한 메뉴 프로그램 목록 조회
                ProgramManageVO programManageVO = new ProgramManageVO();
                programManageVO.setMenu_id(menuManageVO.getMenu_id());
                programManageVO.setAuthor_id(authorManageVO.getAuthor_id());
                menuManageVO.setPrgmList(programManageService.selectAuthorMenuProgramList(programManageVO));
            }
        }
        model.addAttribute("vo", detail);
        model.addAttribute("menuList", menuList);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>권한관리 등록 진행</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/createProc.*")
    public ModelAndView createProc(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute AuthorManageVO authorManageVO
            , @ModelAttribute MenuManageVO menuManageVO
    )  {
        // 권한정보 등록
        int rs = authorManageService.insertAuthorManage(authorManageVO);

        // 해당 권한의 메뉴 등록
        if ( ArrayUtils.isNotEmpty(menuManageVO.getMenuIdArray()) ) {
            menuManageVO.setAuthor_id(authorManageVO.getAuthor_id());
            for ( String menuId : menuManageVO.getMenuIdArray()){
                // 권한에 따른 메뉴 정보 등록
                menuManageVO.setMenu_id(menuId);
                rs += menuManageService.insertMenuAuthorRelate(menuManageVO);
            }
        }

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>권한관리 수정 진행</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/updateProc.*")
    public ModelAndView updateProc(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute AuthorManageVO authorManageVO
            , @ModelAttribute MenuManageVO menuManageVO
    )  {
        int rs = authorManageService.updateAuthorManage(authorManageVO);

        // 해당 권한의 메뉴 등록
        if ( ArrayUtils.isNotEmpty(menuManageVO.getMenuIdArray()) ){
            menuManageVO.setAuthor_id(authorManageVO.getAuthor_id());
            // 권한에 대한 메뉴 정보 전체 삭제
            rs += menuManageService.deleteMenuAuthorRelate(menuManageVO);
            for ( String menuId : menuManageVO.getMenuIdArray()){
                // 권한에 따른 메뉴 정보 등록
                menuManageVO.setMenu_id(menuId);
                rs += menuManageService.insertMenuAuthorRelate(menuManageVO);
            }
        }

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**deleteProc
     * <p>권한관리 삭제</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/deleteProc.*")
    public ModelAndView deleteProc(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute AuthorManageVO authorManageVO
    )  {

        MenuManageVO menuManageVO = new MenuManageVO();
        // 권한에 대한 메뉴 정보 전체 삭제
        menuManageVO.setAuthor_id(authorManageVO.getAuthor_id());
        int rs = menuManageService.deleteMenuAuthorRelate(menuManageVO);
        rs += authorManageService.deleteAuthorManage(authorManageVO);

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }
}
