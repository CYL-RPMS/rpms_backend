/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 사용자 지원 컨트롤러
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.uat.bbs.web;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cyberline.cmm.CmmVar;
import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.file.CylFileService;
import kr.co.cyberline.cmm.web.file.CylFileVO;
import kr.co.cyberline.cmm.web.pagination.CylPaginationInfoExtension;
import kr.co.cyberline.cmm.web.pagination.CylPaginationSupport;
import kr.co.cyberline.cmm.web.util.CylBeanUtils;
import kr.co.cyberline.cmm.web.util.CylWebUtils;
import kr.co.cyberline.cmm.web.view.CylFileDownView;
import kr.co.cyberline.pms.exm.service.FileExtensionVO;
import kr.co.cyberline.pms.sys.bbs.service.BBSManageService;
import kr.co.cyberline.pms.sys.bbs.service.BoardMasterVO;
import kr.co.cyberline.pms.sys.bbs.service.BoardVO;
import kr.co.cyberline.pms.sys.bbs.service.CommentVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>게시판 컨트롤러</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Controller("Boardontroller")
@RequestMapping(value = "/uat/usp/bbs")
public class BoardController{

    @Value("${system.storage.path}")
    private String stroagePath;

    @Value("${system.storage.atch.path}")
    private String atchPath;

    @Value("${system.storage.tempPath}")
    private String tempPath;

    @Resource(name = "bbsManageService")
    private BBSManageService bbsManageService;

    @Resource(name = "cylFileService")
    private CylFileService fileService;

    @Resource(name = "bbsIdGnrService")
    private EgovIdGnrService bbsIdGnrServicel;

    @Resource(name = "atchFileIdGnrService")
    private EgovIdGnrService atchFileIdGnrService;

    /**
     * <p>게시판 목록</p>
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
            , @ModelAttribute BoardVO boardVO
    )  {

        // TODO : 게시판에 대한 게시글 목록 조회
        CylPaginationSupport.setPaginationVO(request, boardVO);

        // 사용가능 한것만...
        boardVO.setUse_at("Y");
        List<BoardVO> list = bbsManageService.selectBBSArticleList(boardVO);

        model.addAttribute("list", list);
        model.addAttribute("boardVO", boardVO);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시물 상세</p>
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
            , @ModelAttribute BoardVO boardVO
    )  {
        UserVO userVO = (UserVO) request.getSession().getAttribute(CmmVar.SESSION_USER_INFO);

        // 게시판 마스터 정보 조회
        BoardMasterVO boardMasterVO = getBBSMasterDetail(response, boardVO);
        // 게시물 상세 정보
        BoardVO detail = bbsManageService.selectBBSArticleDetail(boardVO);

        // 댓글 사용여부가 가능이라면...
        if ( "Y".equals(boardMasterVO.getAnswer_posbl_at()) ){
            // 게시물 코멘트 목록 조회
            CommentVO commentVO = new CommentVO();
            commentVO.setBbs_id(boardVO.getBbs_id());
            commentVO.setNtt_id(boardVO.getNtt_id());
            commentVO.setUse_at("Y");
            commentVO.setPageIndex(boardVO.getPageIndex());
            CylPaginationInfoExtension cmmtPagination = CylPaginationSupport.setPaginationVO(
                    request, commentVO);
            List<CommentVO> cmmtList = bbsManageService.selectBBSArticleCommentList(commentVO);
            cmmtPagination.setTotalRecordCountVO(cmmtList);
            model.addAttribute("cmmtList", cmmtList);
            model.addAttribute("commentVO", commentVO);
            model.addAttribute("cmmtPagination", cmmtPagination);
        }


        // 첨부파일 업로드 사용여부가 사용가능 이라면..
        if ( "Y".equals(boardMasterVO.getFile_atch_posbl_at()) ){
            CylFileVO fileVO = new CylFileVO(CylFileVO.PAGING_ENABLE_OFF, "A.CRTE_DTTM", "DESC");
            fileVO.setAtch_file_id(detail.getAtch_file_id());
            fileVO.setUse_at("Y");
            // 첨부파일 목록 조회
            model.addAttribute("atchFileList", fileService.selectAtchFileList(fileVO));
        }

        // 게시물 조회수 증가
        bbsManageService.updateBBSArticleReadCnt(boardVO);

        model.addAttribute("vo", detail);
        model.addAttribute("userVO", userVO);
        model.addAttribute("boardMasterVO", boardMasterVO);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시물 등록</p>
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
            , @ModelAttribute BoardVO boardVO
    )  {
        UserVO userVO = (UserVO) request.getSession().getAttribute(CmmVar.SESSION_USER_INFO);

        // 게시판 마스터 정보 조회
        BoardMasterVO boardMasterVO = getBBSMasterDetail(response, boardVO);

        // 답글 게시물 쓰기
        if ( boardVO.getParnts_ntt_id() != 0 ){
            // 상위 게시물 상세 정보
            BoardVO uprBoardVO = new BoardVO();
            uprBoardVO.setBbs_id(boardVO.getBbs_id());
            uprBoardVO.setNtt_id(boardVO.getParnts_ntt_id());
            BoardVO uprDetail = bbsManageService.selectBBSArticleDetail(uprBoardVO);
            CylBeanUtils.copyProperties(uprDetail, boardVO);
            boardVO.setParnts_ntt_id(uprBoardVO.getNtt_id());
        }

        model.addAttribute("mode", "CREATE");
        model.addAttribute("userVO", userVO);
        model.addAttribute("boardVO", boardVO);
        model.addAttribute("boardMasterVO", boardMasterVO);
        model.addAttribute("tempPath", tempPath);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시물 수정</p>
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
            , @ModelAttribute BoardVO boardVO
    )  {
        UserVO userVO = (UserVO) request.getSession().getAttribute(CmmVar.SESSION_USER_INFO);

        // 게시판 마스터 정보 조회
        BoardMasterVO boardMasterVO = getBBSMasterDetail(response, boardVO);
        // 게시물 상세 정보
        BoardVO detail = bbsManageService.selectBBSArticleDetail(boardVO);

        // 첨부파일 업로드 사용여부가 사용가능 이라면..
        if ( "Y".equals(boardMasterVO.getFile_atch_posbl_at()) ){
            CylFileVO fileVO = new CylFileVO(CylFileVO.PAGING_ENABLE_OFF, "A.CRTE_DTTM", "DESC");
            fileVO.setAtch_file_id(detail.getAtch_file_id());
            fileVO.setUse_at("Y");
            // 첨부파일 목록 조회
            model.addAttribute("atchFileList", fileService.selectAtchFileList(fileVO));
        }

        CylBeanUtils.copyProperties(detail, boardVO);
        model.addAttribute("mode", "UPDATE");
        model.addAttribute("userVO", userVO);
        model.addAttribute("boardVO", boardVO);
        model.addAttribute("boardMasterVO", boardMasterVO);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시판 등록 진행</p>
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
            , @ModelAttribute BoardVO boardVO
            , @ModelAttribute FileExtensionVO fileVO
    ) throws FdlException {
        UserVO userVO = (UserVO) request.getSession().getAttribute(CmmVar.SESSION_USER_INFO);

        // 게시판 마스터 정보 조회
        BoardMasterVO boardMasterVO = getBBSMasterDetail(response, boardVO);
        boardVO.setNtt_id(bbsIdGnrServicel.getNextIntegerId());

        // 익명게시판이 아니라면...
        if ( !"BBST02".equals(boardMasterVO.getBbs_ty_code()) ){
            boardVO.setCrte_user_id(userVO.getUser_id());
            boardVO.setNtcr_id(userVO.getUser_id());
            boardVO.setNtcr_nm(userVO.getUser_nm());
        }

        // 첨부파일 업로드 사용여부가 사용가능 이라면..
        if ( "Y".equals(boardMasterVO.getFile_atch_posbl_at()) ){
            // 첨부파일아이디
            fileVO.setAtch_file_id(atchFileIdGnrService.getNextStringId());
            
            // 사용여부
            fileVO.setUse_at("Y");
            // 파일 업로드
            bbsManageService.doBBSArticleFileUpload(request, fileVO, true);
            boardVO.setAtch_file_id(fileVO.getAtch_file_id());
        }

        // 게시물 등록
        int rs = bbsManageService.insertBBSArticle(boardVO);

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시판 수정 진행</p>
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
            , @ModelAttribute BoardVO boardVO
            , @ModelAttribute CylFileVO fileVO
    ) throws FdlException {
        UserVO userVO = (UserVO) request.getSession().getAttribute(CmmVar.SESSION_USER_INFO);

        // 게시판 마스터 정보 조회
        BoardMasterVO boardMasterVO = getBBSMasterDetail(response, boardVO);
        // 게시물 상세 정보
        BoardVO detail = bbsManageService.selectBBSArticleDetail(boardVO);

        // 익명게시판이 아니라면...
        if ( !"BBST02".equals(boardMasterVO.getBbs_ty_code()) ){
            boardVO.setUpdt_user_id(userVO.getUser_id());
        } else {
            // 익명 게시판이라면.. 비밀번호 유효 검증 여부 확인
            if ( request.getSession().getAttribute(CmmVar.SESSION_USER_PASSWD_EXPIRE) == null ){
                model.addAttribute("result", String.valueOf(false));
                model.addAttribute("actionMessage", "비밀번호 검사 재인증 해주시기 바랍니다.");
                return new ModelAndView("jsonViewExtension", model);
            }
        }

        // 첨부파일 업로드 사용여부가 사용가능 이라면..
        if ( "Y".equals(boardMasterVO.getFile_atch_posbl_at()) ){
            boolean mstrRegAt = false;
            // 첨부파일아이디
            if ( detail.getAtch_file_id() == null ){
                mstrRegAt = true;
                fileVO.setAtch_file_id(atchFileIdGnrService.getNextStringId());
            } else {
                fileVO.setAtch_file_id(detail.getAtch_file_id());
            }

            // 사용여부
            fileVO.setUse_at("Y");
            // 파일 업로드
            bbsManageService.doBBSArticleFileUpload(request, fileVO, mstrRegAt);
            boardVO.setAtch_file_id(fileVO.getAtch_file_id());
        }

        // 게시물 수정
        int rs = bbsManageService.updateBBSArticle(boardVO);

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시판 삭제 진행</p>
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
            , @ModelAttribute BoardVO boardVO
    )  {
        UserVO userVO = (UserVO) request.getSession().getAttribute(CmmVar.SESSION_USER_INFO);

        // 게시판 마스터 정보 조회
        BoardMasterVO boardMasterVO = getBBSMasterDetail(response, boardVO);
        // 게시물 상세 정보
        BoardVO detail = bbsManageService.selectBBSArticleDetail(boardVO);

        // 익명게시판이 아니라면...
        if ( !"BBST02".equals(boardMasterVO.getBbs_ty_code()) ){
            boardVO.setUpdt_user_id(userVO.getUser_id());
        } else {
            // 익명 게시판이라면 ... 비밀번호 체크
            if ( request.getSession().getAttribute(CmmVar.SESSION_USER_PASSWD_EXPIRE) == null ){
                model.addAttribute("result", String.valueOf(false));
                model.addAttribute("actionMessage", "비밀번호 검사 재인증 해주시기 바랍니다.");
                return new ModelAndView("jsonViewExtension", model);
            }
        }

        // 게시물 사용안함
        boardVO.setUse_at("N");
        // 게시물 사용 여부 수정
        int rs = bbsManageService.updateBBSArticleUseAt(boardVO);
        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시물 댓글 등록 진행</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/commentCreateProc.*")
    public ModelAndView commentCreateProc(
            HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute CommentVO commentVO
    )  {
        UserVO userVO = (UserVO) request.getSession().getAttribute(CmmVar.SESSION_USER_INFO);

        BoardVO boardVO = new BoardVO();
        boardVO.setBbs_id(commentVO.getBbs_id());
        // 게시판 마스터 정보 조회
        BoardMasterVO boardMasterVO = getBBSMasterDetail(response, boardVO);
        // 게시물 상세 정보
        //BoardVO detail = bbsManageService.selectBBSArticleDetail(boardVO);

        // 댓글 사용여부 체크
        if ( "N".equals(boardMasterVO.getAnswer_posbl_at()) ){
            CylWebUtils.setAjaxAction(response, 401, "댓글 기능을 이용할 수 없습니다.");
        }

        // 익명게시판이 아니라면...
        if ( !"BBST02".equals(boardMasterVO.getBbs_ty_code()) ){
            commentVO.setCrte_user_id(userVO.getUser_id());
            commentVO.setWrter_id(userVO.getUser_id());
            commentVO.setWrter_nm(userVO.getUser_nm());
        }

        // 게시물 댓글 등록
        int rs = bbsManageService.insertBBSArticleComment(commentVO);

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시물 댓글 수정 진행</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/commentUpdateProc.*")
    public ModelAndView commentUpdateProc(
            HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute CommentVO commentVO
    )  {
        UserVO userVO = (UserVO) request.getSession().getAttribute(CmmVar.SESSION_USER_INFO);

        BoardVO boardVO = new BoardVO();
        boardVO.setBbs_id(commentVO.getBbs_id());
        // 게시판 마스터 정보 조회
        BoardMasterVO boardMasterVO = getBBSMasterDetail(response, boardVO);

        // 댓글 사용여부 체크
        if ( "N".equals(boardMasterVO.getAnswer_posbl_at()) ){
            CylWebUtils.setAjaxAction(response, 401, "댓글 기능을 이용할 수 없습니다.");
        }

        // 익명게시판 이라면...
        if ( "BBST02".equals(boardMasterVO.getBbs_ty_code()) ){
            // 익명 게시판이라면 ... 비밀번호 체크
            if ( request.getSession().getAttribute(CmmVar.SESSION_USER_PASSWD_EXPIRE) == null ){
                model.addAttribute("result", String.valueOf(false));
                model.addAttribute("actionMessage", "비밀번호 검사 재인증 해주시기 바랍니다.");
                return new ModelAndView("jsonViewExtension", model);
            }
        } else {
            // 익명게시판이 아니라면...
            commentVO.setUpdt_user_id(userVO.getUser_id());
        }

        // 게시물 댓글 수정
        int rs = bbsManageService.updateBBSArticleComment(commentVO);

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시물 댓글 삭제 진행</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/commentDeleteProc.*")
    public ModelAndView commentDeleteProc(
            HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute CommentVO commentVO
    )  {
        UserVO userVO = (UserVO) request.getSession().getAttribute(CmmVar.SESSION_USER_INFO);

        BoardVO boardVO = new BoardVO();
        boardVO.setBbs_id(commentVO.getBbs_id());
        // 게시판 마스터 정보 조회
        BoardMasterVO boardMasterVO = getBBSMasterDetail(response, boardVO);

        // 댓글 사용여부 체크
        if ( "N".equals(boardMasterVO.getAnswer_posbl_at()) ){
            CylWebUtils.setAjaxAction(response, 401, "댓글 기능을 이용할 수 없습니다.");
        }

        // 익명게시판 이라면...
        if ( "BBST02".equals(boardMasterVO.getBbs_ty_code()) ){
            // 익명 게시판이라면 ... 비밀번호 체크
            if ( request.getSession().getAttribute(CmmVar.SESSION_USER_PASSWD_EXPIRE) == null ){
                model.addAttribute("result", String.valueOf(false));
                model.addAttribute("actionMessage", "비밀번호 검사 재인증 해주시기 바랍니다.");
                return new ModelAndView("jsonViewExtension", model);
            }
        }

        // 게시물 댓글 수정
        commentVO.setUse_at("N");
        int rs = bbsManageService.updateBBSArticleCommentUseAt(commentVO);

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>첨부파일 다운</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/atchFileDownProc.*")
    public ModelAndView exampleAtchFileDownProc(
            HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute CylFileVO fileVO
    )  {

        // TODO : 디테일한 파일다운 가능 권한 검증 로직 필요

        // 파일 상세 조회
        CylFileVO fv = fileService.selectAtchFileDetail(fileVO);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(CylFileDownView.FILE_PARAM_ORGINL_FILE_NAME, fv.getOrginl_file_nm());
        paramMap.put(CylFileDownView.FILE_PARAM_UNIQ_FILE_NAME, fv.getStre_file_nm());
        paramMap.put(CylFileDownView.FILE_PARAM_FILE_PATH, fv.getFile_stre_cours());
        model.addAttribute(CylFileDownView.FILE_PARAM, paramMap);
        return new ModelAndView("fileDownView", model);
    }

    /**
     * <p>첨부파일 삭제</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/atchFileDeleteProc.*")
    public ModelAndView atchFileDeleteProc(
            HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute CylFileVO fileVO
            , @ModelAttribute BoardVO boardVO
    )  {

        // 게시판 마스터 정보 조회
        BoardMasterVO boardMasterVO = getBBSMasterDetail(response, boardVO);
        // 익명게시판이 이라면...
        if ( "BBST02".equals(boardMasterVO.getBbs_ty_code()) ){
            // 익명 게시판이라면 ... 비밀번호 체크
            if ( request.getSession().getAttribute(CmmVar.SESSION_USER_PASSWD_EXPIRE) == null ){
                model.addAttribute("result", String.valueOf(false));
                model.addAttribute("actionMessage", "비밀번호 검사 재인증 해주시기 바랍니다.");
                return new ModelAndView("jsonViewExtension", model);
            }
        }

        // 파일삭제 ( 실제 물리도 삭제 )
        int rs = fileService.deleteAtchFileDetail(fileVO.getAtch_file_id(), fileVO.getFile_sn(), true, false);
        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시물 비밀번호 검증</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/passwordCheckProc.*")
    public ModelAndView passwordCheckProc(
            HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute BoardVO boardVO
    )  {

        // 게시물 상세 정보
        BoardVO detail = bbsManageService.selectBBSArticleDetail(boardVO);
        if ( detail == null || CylStringUtil.isEmpty(boardVO.getPasswd())){
            model.addAttribute("result", String.valueOf(false));
            model.addAttribute("actionMessage", "정보를 다시 확인 해주시기 바랍니다.");
        } else {
            if (!CylStringUtil.nvl(detail.getPasswd()).equals(boardVO.getPasswd()) ){
                model.addAttribute("result", String.valueOf(false));
                model.addAttribute("actionMessage", "비밀번호가 일치 하지 않습니다.");
            } else {
                model.addAttribute("result", String.valueOf(true));
                request.getSession().setAttribute(CmmVar.SESSION_USER_PASSWD_EXPIRE, true);
            }
        }

        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>댓글 비밀번호 검증</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/commentPasswordCheckProc.*")
    public ModelAndView commentPasswordCheckProc(
            HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute CommentVO commentVO
    )  {

        // 댓글 상세 정보
        CommentVO detail = bbsManageService.selectBBSArticleCommentDetail(commentVO);
        if ( detail == null || CylStringUtil.isEmpty(commentVO.getPasswd())){
            model.addAttribute("result", String.valueOf(false));
            model.addAttribute("actionMessage", "정보를 다시 확인 해주시기 바랍니다.");
        } else {
            if ( !CylStringUtil.nvl(detail.getPasswd()).equals(commentVO.getPasswd()) ){
                model.addAttribute("result", String.valueOf(false));
                model.addAttribute("actionMessage", "비밀번호가 일치 하지 않습니다.");
            } else {
                model.addAttribute("result", String.valueOf(true));
                request.getSession().setAttribute(CmmVar.SESSION_USER_PASSWD_EXPIRE, true);
            }
        }

        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>게시판 마스터의 정보 조회</p>
     *
     * @param response
     * @param boardVO
     * @return
     */
    private BoardMasterVO getBBSMasterDetail(HttpServletResponse response, @ModelAttribute BoardVO boardVO) {

        BoardMasterVO boardMasterVO = new BoardMasterVO();
        boardMasterVO.setBbs_id(boardVO.getBbs_id());
        // 게시판 마스터 상세 정보 조회
        boardMasterVO = bbsManageService.selectBBSMasterDetail(boardMasterVO);

        // 사용여부 체크
        if ( boardMasterVO == null || "N".equals(boardMasterVO.getUse_at()) ){
            CylWebUtils.setAjaxAction(response, 401, "이용할 수 없는 게시판 입니다.");
        }

        return boardMasterVO;
    }
}