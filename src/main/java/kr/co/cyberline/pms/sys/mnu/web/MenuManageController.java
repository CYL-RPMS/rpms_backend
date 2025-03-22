/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 메뉴 관리 컨트롤러
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.mnu.web;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.model.MenuManageVO;
import kr.co.cyberline.cmm.web.model.ProgramManageVO;
import kr.co.cyberline.cmm.web.pagination.CylPaginationSupport;
import kr.co.cyberline.pms.sys.mnu.service.MenuManageService;
import kr.co.cyberline.pms.sys.pgm.service.ProgramManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>메뉴 관리 컨트롤러</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Controller("menuManageController")
@RequestMapping(value = "/sys/mnu")
public class MenuManageController {

    @Resource(name = "menuManageService")
    private MenuManageService menuManageService;

    @Resource(name = "programManageService")
    private ProgramManageService programManageService;

    @Resource(name = "menuIdGnrService")
    private EgovIdGnrService menuIdGnrService;


    /**
     * <p>메뉴관리 목록 화면</p>
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
            , @ModelAttribute MenuManageVO menuManageVO
    )  {
        CylPaginationSupport.setPaginationVO(request, menuManageVO);
        List<MenuManageVO> list = menuManageService.selectMenuManageList(menuManageVO);

        model.addAttribute("list", list);
        model.addAttribute("menuManageVO", menuManageVO);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>메뉴관리 상세 화면</p>
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
            , @ModelAttribute MenuManageVO menuManageVO
    )  {
        // 메뉴 상세 정보 조회
        MenuManageVO detail = menuManageService.selectMenuManageDetail(menuManageVO);

        // TODO : 메뉴에 대한 프로그램 목록 조회
        ProgramManageVO programManageVO = new ProgramManageVO();
        programManageVO.setMenu_id(menuManageVO.getMenu_id());
        List<ProgramManageVO> prgmList = programManageService.selectMenuProgramList(programManageVO);

        model.addAttribute("vo", detail);
        model.addAttribute("prgmList", prgmList);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>메뉴관리 등록 화면</p>
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
            , @ModelAttribute MenuManageVO menuManageVO
    )  {
        // 하위메뉴등록 시...
        if ( CylStringUtil.isNotBlank(menuManageVO.getUpr_menu_id()) ){
            // 상위 메뉴 정보를 조회한다.
            menuManageVO.setMenu_id(menuManageVO.getUpr_menu_id());
            MenuManageVO uprMenuDetail = menuManageService.selectMenuManageDetail(menuManageVO);
            menuManageVO.setUpr_menu_nm_kr(uprMenuDetail.getMenu_nm_kr());
            menuManageVO.setUpr_menu_nm_en(uprMenuDetail.getMenu_nm_en());
        }

        model.addAttribute("vo", menuManageVO);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>메뉴관리 수정 화면</p>
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
            , @ModelAttribute MenuManageVO menuManageVO
    )  {
        MenuManageVO detail = menuManageService.selectMenuManageDetail(menuManageVO);
        // TODO : 메뉴에 대한 프로그램 목록 조회
        ProgramManageVO programManageVO = new ProgramManageVO();
        programManageVO.setMenu_id(menuManageVO.getMenu_id());
        List<ProgramManageVO> prgmList = programManageService.selectMenuProgramList(programManageVO);

        model.addAttribute("vo", detail);
        model.addAttribute("prgmList", prgmList);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>메뉴관리 등록 진행</p>
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
            , @ModelAttribute MenuManageVO menuManageVO
    ) throws FdlException {
        menuManageVO.setMenu_id(menuIdGnrService.getNextStringId());
        int rs = menuManageService.insertMenuManage(menuManageVO);
        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>메뉴관리 수정 진행</p>
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
            , @ModelAttribute MenuManageVO menuManageVO
    )  {
        int rs = menuManageService.updateMenuManage(menuManageVO);
        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>메뉴관리 삭제</p>
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
            , @ModelAttribute MenuManageVO menuManageVO
    )  {
        // 메뉴 삭제
        int rs = menuManageService.deleteMenuManage(menuManageVO);
        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }
}
