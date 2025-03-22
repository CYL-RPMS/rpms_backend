/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 예제 컨트롤러
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.exm.web;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.util.file.CylFileUtil;
import kr.co.cyberline.cmm.web.file.CylFileService;
import kr.co.cyberline.cmm.web.file.CylFileVO;
import kr.co.cyberline.cmm.web.pagination.CylPaginationInfoExtension;
import kr.co.cyberline.cmm.web.pagination.CylPaginationSupport;
import kr.co.cyberline.cmm.web.util.CylFileUploadUtil;
import kr.co.cyberline.cmm.web.view.CylFileDownView;
import kr.co.cyberline.pms.exm.service.FileExtensionVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>예제 컨트롤러</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Controller("exampleController")
@RequestMapping(value = "/exm")
public class ExampleManageController {

    @Value("${system.storage.path}")
    private String stroagePath;

    @Value("${system.storage.atch.path}")
    private String atchPath;

    @Value("${system.storage.tempPath}")
    private String tempPath;

    @Resource(name = "cylFileService")
    private CylFileService fileService;

    @Resource(name = "atchFileIdGnrService")
    private EgovIdGnrService egovIdGnrService;

    private String exampleAtchFileMainView = "";

    private String exampleAtchFileDetailView = "";

    private String exampleValidatorMainView = "";

    private String exampleValidatorDetailView = "";

    /**
     * <p>예제 첨부파일메인</p>
     *
     * @param request
     * @param response
     * @param mav
     * @return mav
     * @
     */
    @RequestMapping(value = "/exampleAtchFileMain.*")
    public ModelAndView exampleAtchFileMain(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelAndView mav
    )  {
        mav.setViewName(exampleAtchFileMainView);
        return mav;
    }

    /**
     * <p>예제 첨부파일 목록</p>
     *
     * @param request
     * @param response
     * @param mav
     * @return mav
     * @
     */
    @RequestMapping(value = "/exampleAtchFileList.*")
    public ModelAndView exampleAtchFileList(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelAndView mav
            , @ModelAttribute CylFileVO fileVO
    )  {

        CylPaginationInfoExtension pagination = CylPaginationSupport.setPaginationVO(request, fileVO, "1", 30, 10);
        // 첨부파일 목록 조회
        List<CylFileVO> fileList = fileService.selectAtchFileList(fileVO);
        pagination.setTotalRecordCountVO(fileList);

        mav.addObject("fileVO", fileVO);
        mav.addObject("fileList", fileList);
        mav.addObject("pagination", pagination);
        mav.addObject("tempPath", tempPath);
        
        mav.setViewName(exampleAtchFileDetailView);

        return mav;
    }

    /**
     * <p>예제 파일관리 다운</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/exampleAtchFileDownProc.*")
    public ModelAndView exampleAtchFileDownProc(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute CylFileVO fileVO
    )  {

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
     * <p>예제 파일관리 등록 (ajax form)</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/exampleAtchFileUploadProc.*")
    public ModelAndView exampleAtchFileUploadProc(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute CylFileVO fileVO
    ) throws FdlException {

        // 첨부파일아이디
        fileVO.setAtch_file_id(egovIdGnrService.getNextStringId());
        // 사용여부
        fileVO.setUse_at("Y");

        // 저장파일경로
        String storeFilePath = stroagePath + atchPath;
        // 파일 업로드
        List<CylFileVO> uploadFileList = CylFileUploadUtil.multiPartUploadFiles(request, storeFilePath, fileVO.getAtch_file_id());

        // 파일속성 등록
        int rs = fileService.insertAtchFile(fileVO);
        for ( CylFileVO vo : uploadFileList ){
            // 파일속성상세 등록
            rs += fileService.insertAtchFileDetail(fileVO);
        }

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>예제 파일관리 등록 (fileupload)</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/exampleAtchFileUploadProc2.*")
    public ModelAndView exampleAtchFileUploadProc2(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model
            , @ModelAttribute FileExtensionVO fileVO
    ) throws FdlException {
    	
        // 임시 폴더 경로
        String tempFilePath = FilenameUtils.separatorsToSystem(request.getSession().getServletContext().getRealPath(""))
                + tempPath + CylFileUtil.SEPERATOR + request.getRequestedSessionId();

        // 저장 대상 폴더 경로
        String storeFilePath = stroagePath + atchPath;
        
        int rs = 0;
        if ( fileVO != null && CollectionUtils.isNotEmpty(fileVO.getAtchFile1()) ){
            rs = doFileUpload(fileVO, tempFilePath, storeFilePath, fileVO.getAtchFile1());
        }
        if ( fileVO != null && CollectionUtils.isNotEmpty(fileVO.getAtchFile2()) ){
            rs = doFileUpload(fileVO, tempFilePath, storeFilePath, fileVO.getAtchFile2());
        }
        
        if ( fileVO != null && CollectionUtils.isNotEmpty(fileVO.getAtchFile3()) ){
            rs = doFileUpload(fileVO, tempFilePath, storeFilePath, fileVO.getAtchFile3());
        }
        
        if ( fileVO != null && CollectionUtils.isNotEmpty(fileVO.getAtchFile4()) ){
            rs = doFileUpload(fileVO, tempFilePath, storeFilePath, fileVO.getAtchFile4());
        }
        
        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

	private int doFileUpload(FileExtensionVO fileVO, String tempFilePath, String storeFilePath, List<CylFileVO> fileList) throws FdlException
			 {
		int rs;
		// 첨부파일아이디
		fileVO.setAtch_file_id(egovIdGnrService.getNextStringId());
		// 사용여부
		fileVO.setUse_at("Y");
		// 파일 업로드
		List<CylFileVO> uploadFileList = CylFileUploadUtil.copyUploadFiles(fileList, tempFilePath, storeFilePath, fileVO.getAtch_file_id());
		// 파일속성 등록
		rs = fileService.insertAtchFile(fileVO);
		for ( CylFileVO vo : uploadFileList ){
		    // 파일속성상세 등록
		    vo.setAtch_file_id(fileVO.getAtch_file_id());
		    rs += fileService.insertAtchFileDetail(vo);
		}
		return rs;
	}

    /**
     * <p>예제 첨부파일 삭제</p>
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/exampleAtchFileDeleteProc.*")
    public ModelAndView exampleAtchFileDeleteProc(
              HttpServletRequest request
            , HttpServletResponse response
            , @ModelAttribute CylFileVO fileVO
            , ModelMap model
    )  {

        // 파일 삭제 ( fileVO : 삭제할 파일 객체, boolean : 파일실제삭제여부, boolean : 사용 여부 수정 )
        /* ex )
         * 파일 상세 목록 실제 파일 삭제 하면서 DB 삭제 하겠다. ( fileVO, true, false )
         * 파일 상세 목록 실제 파일 삭제 하지 않고 DB 삭제 하겠다. ( fileVO, false, false )
         * 파일 상세 목록 실제 파일 삭제 하지 않고 사용하지 않음으로 하겠다. ( fileVO, false, true )
         * 파일 상세 목록 실제 파일 삭제 하면서 사용하지 않음으로 하겠다. ( fileVO, true, true )
         */
        int rs = fileService.deleteAtchFiles(fileVO, false, false);

        model.addAttribute("result", String.valueOf(rs > 0));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * <p>예제 Spring Validator 메인</p>
     *
     * @param request
     * @param response
     * @return mav
     * @
     */
    @RequestMapping(value = "/exampleValidatorMain.*")
    public ModelAndView exampleValidatorMain(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelAndView mav
    )  {
        mav.setViewName(exampleValidatorMainView);
        return mav;
    }

    /**
     * <p>예제 Spring Validator 상세</p>
     *
     * @param request
     * @param response
     * @return mav
     * @
     */
    @RequestMapping(value = "/exampleValidatorDetail.*")
    public ModelAndView exampleValidatorDetail(
              HttpServletRequest request
            , HttpServletResponse response
            , ModelAndView mav
            , @ModelAttribute UserVO userVO
    )  {
        mav.setViewName(exampleValidatorDetailView);
        return mav;
    }

    /**
     * <p>예제 Spring Validator 체크</p>
     *
     * @param request
     * @param response
     * @param mav
     * @return mav
     * @
     */
    @RequestMapping(value = "/exampleValidatorProc.*")
    public ModelAndView exampleValidatorProc(
            HttpServletRequest request
            , HttpServletResponse response
            , ModelAndView mav
            , @ModelAttribute UserVO userVO
            , BindingResult bindingResult
    )  {


        if ( bindingResult.hasErrors() ){
            System.out.println("# validation Error!!!! #");
        } else {
            System.out.println("# validation Success!!!! #");
        }

        mav.setViewName(exampleValidatorDetailView);
        return mav;
    }
}
