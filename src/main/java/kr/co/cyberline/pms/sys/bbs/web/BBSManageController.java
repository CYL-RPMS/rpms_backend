/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 게시판 관리 컨트롤러
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.bbs.web;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cyberline.cmm.jwt.security.JwtTokenProvider;
import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.pagination.CylPaginationSupport;
import kr.co.cyberline.cmm.web.util.CylBeanUtils;
import kr.co.cyberline.pms.sys.bbs.service.BBSManageService;
import kr.co.cyberline.pms.sys.bbs.service.BoardMasterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>게시판 관리 컨트롤러</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Controller("bbsManageController")
@RequestMapping(value = "/sys/bbs")
@RequiredArgsConstructor
public class BBSManageController {

    @Resource(name = "bbsManageService")
    private BBSManageService bbsManageService;

    @Resource(name = "bbsManageIdGnrService")
    private EgovIdGnrService bbsIdGnrService;

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * <p>게시판 관리 목록 화면</p>
     *
     * @param request
     * @param response
     * @param model
     * @return new ModelAndView("jsonViewExtension", model)
     * @
     */
    @RequestMapping(value = "/list.*")
    public ModelAndView list(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute BoardMasterVO boardMasterVO
    )  {
        CylPaginationSupport.setPaginationVO(request, boardMasterVO);
        // 게시판 관리 목록 조회
        List<BoardMasterVO> list = bbsManageService.selectBBSMasterList(boardMasterVO);

        model.addAttribute("list", list);
        model.addAttribute("boardMasterVO", boardMasterVO);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시판 관리 상세 화면</p>
     *
     * @param request
     * @param response
     * @param model
     * @return new ModelAndView("jsonViewExtension", model)
     * @
     */
    @RequestMapping(value = "/detail.*")
    public ModelAndView detail(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute BoardMasterVO boardMasterVO
    )  {

        // 게시판 관리 상세 정보 조회
        BoardMasterVO detail = bbsManageService.selectBBSMasterDetail(boardMasterVO);
        model.addAttribute("vo", detail);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시판 관리 등록 화면</p>
     *
     * @param request
     * @param response
     * @param model
     * @return new ModelAndView("jsonViewExtension", model)
     * @
     */
    @RequestMapping(value = "/create.*")
    public ModelAndView create(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute BoardMasterVO boardMasterVO
    )  {
        model.addAttribute("mode", "CREATE");
        model.addAttribute("boardMasterVO", boardMasterVO);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시판 관리 수정 화면</p>
     *
     * @param request
     * @param response
     * @param model
     * @return new ModelAndView("jsonViewExtension", model)
     * @
     */
    @RequestMapping(value = "/update.*")
    public ModelAndView update(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute BoardMasterVO boardMasterVO
    )  {
        // 게시판 관리 상세 정보 조회
        BoardMasterVO detail = bbsManageService.selectBBSMasterDetail(boardMasterVO);
        if ( detail != null ){
            CylBeanUtils.copyProperties(detail, boardMasterVO);
        }

        model.addAttribute("mode", "UPDATE");
        model.addAttribute("boardMasterVO", boardMasterVO);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시판 관리 등록 진행</p>
     *
     * @param request
     * @param response
     * @return new ModelAndView("jsonViewExtension", model)
     * @
     */
    @RequestMapping(value = "/createProc.*")
    public ModelAndView createProc(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute BoardMasterVO boardMasterVO
    ) throws FdlException {
        //UserVO userVO = (UserVO) request.getSession().getAttribute(CmmVar.SESSION_USER_INFO);
        UserVO userVO = new UserVO();
        String userId;

        String token = jwtTokenProvider.resolveToken(request);
        boolean valid = jwtTokenProvider.validateToken(token);
        if(!valid) {
            userVO.setAuthor_id("");
        } else {
            userId = jwtTokenProvider.getUserIdFromToken(token);
            userVO.setUser_id(userId);
        }

        // 게시판 관리 ID
        boardMasterVO.setBbs_id(bbsIdGnrService.getNextStringId());
        boardMasterVO.setCrte_user_id(userVO.getUser_id());
        boardMasterVO.setBbs_intrcn(CylStringUtil.unscript(boardMasterVO.getBbs_intrcn()));
        // 게시판 관리 등록
        int rs = bbsManageService.insertBBSMaster(boardMasterVO);

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시판 관리 수정 진행</p>
     *
     * @param request
     * @param response
     * @return new ModelAndView("jsonViewExtension", model)
     * @
     */
    @RequestMapping(value = "/updateProc.*")
    public ModelAndView updateProc(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute BoardMasterVO boardMasterVO
    )  {
        //UserVO userVO = (UserVO) request.getSession().getAttribute(CmmVar.SESSION_USER_INFO);
        UserVO userVO = new UserVO();
        String userId;

        String token = jwtTokenProvider.resolveToken(request);
        boolean valid = jwtTokenProvider.validateToken(token);
        if(!valid) {
            userVO.setAuthor_id("");
        } else {
            userId = jwtTokenProvider.getUserIdFromToken(token);
            userVO.setUser_id(userId);
        }
        boardMasterVO.setUpdt_user_id(userVO.getUser_id());
        boardMasterVO.setBbs_intrcn(CylStringUtil.unscript(boardMasterVO.getBbs_intrcn()));

        // 게시판 관리 수정
        int rs = bbsManageService.updateBBSMaster(boardMasterVO);

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시판 관리 삭제 진행</p>
     *
     * @param request
     * @param response
     * @return new ModelAndView("jsonViewExtension", model)
     * @
     */
    @RequestMapping(value = "/deleteProc.*")
    public ModelAndView deleteProc(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute BoardMasterVO boardMasterVO
    )  {

        // 게시판 관리 삭제
        int rs = bbsManageService.deleteBBSMaster(boardMasterVO);

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }
}
