/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 프로그램 관리 컨트롤러
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.pgm.web;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cyberline.cmm.web.model.ProgramManageVO;
import kr.co.cyberline.cmm.web.pagination.CylPaginationInfoExtension;
import kr.co.cyberline.cmm.web.pagination.CylPaginationSupport;
import kr.co.cyberline.pms.sys.pgm.service.ProgramManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>프로그램 관리 컨트롤러</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Controller("programManageController")
@RequestMapping(value = "/sys/pgm")
public class ProgramManageController{

    @Resource(name = "programManageService")
    private ProgramManageService programManageService;

    @Resource(name = "programIdGnrService")
    private EgovIdGnrService programIdGnrService;

    /**
     * <p>프로그램관리 목록 화면</p>
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
            , @ModelAttribute ProgramManageVO programManageVO
    )  {
        CylPaginationInfoExtension pagination = CylPaginationSupport.setPaginationVO(
                request, programManageVO);
        List<ProgramManageVO> list = programManageService.selectProgramManageList(programManageVO);
        pagination.setTotalRecordCountVO(list);

        model.addAttribute("list", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("programManageVO", programManageVO);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>프로그램관리 상세 화면</p>
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
            , @ModelAttribute ProgramManageVO programManageVO
    )  {
        // 프로그램 상세 정보 조회
        ProgramManageVO detail = programManageService.selectProgramManageDetail(programManageVO);

        model.addAttribute("vo", detail);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>프로그램관리 등록 화면</p>
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
            , @ModelAttribute ProgramManageVO programManageVO
    )  {
        model.addAttribute("vo", programManageVO);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>프로그램관리 수정 화면</p>
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
            , @ModelAttribute ProgramManageVO programManageVO
    )  {
        ProgramManageVO detail = programManageService.selectProgramManageDetail(programManageVO);
        model.addAttribute("vo", detail);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>프로그램관리 등록 진행</p>
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
            , @ModelAttribute ProgramManageVO programManageVO
    ) throws FdlException {
        programManageVO.setPrgm_id(programIdGnrService.getNextStringId());
        int rs = programManageService.insertProgramManage(programManageVO);
        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>프로그램관리 수정 진행</p>
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
            , @ModelAttribute ProgramManageVO programManageVO
    )  {
        int rs = programManageService.updateProgramManage(programManageVO);
        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>프로그램관리 삭제</p>
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
            , @ModelAttribute ProgramManageVO programManageVO
    )  {
        // 프로그램 삭제
        int rs = programManageService.deleteProgramManage(programManageVO);
        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>메뉴 프로그램 관리 팝업</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/listPopup.*")
    public ModelAndView listPopup(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute ProgramManageVO programManageVO
    )  {

        CylPaginationSupport.setPaginationVO(
                request, programManageVO);
        // TODO : 프로그램 목록 조회
        List<ProgramManageVO> list = programManageService.selectProgramManageList(programManageVO);

        model.addAttribute("list", list);
        model.addAttribute("programManageVO", programManageVO);
        return new ModelAndView("jsonViewExtension", model);
    }
}
