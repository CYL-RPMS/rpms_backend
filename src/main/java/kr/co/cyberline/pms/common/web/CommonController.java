/**
 * Author    : ktj
 * Date      : 2015. 11. 23
 * Company   : Cyber Line Co., Ltd.
 * Description : 공통 조회 컨트롤러
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */

package kr.co.cyberline.pms.common.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cyberline.cmm.CmmVar;
import kr.co.cyberline.cmm.util.vo.JxlsParam;
import kr.co.cyberline.cmm.web.code.CylCmmCodeMap;
import kr.co.cyberline.cmm.web.file.CylFileService;
import kr.co.cyberline.cmm.web.file.CylFileVO;
import kr.co.cyberline.cmm.web.view.CylFileDownView;
import kr.co.cyberline.pms.common.service.CommonService;
import kr.co.cyberline.pms.common.service.CommonVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 공통 조회 컨트롤러
 * </p>
 *
 * @author ktj
 * @version 1.0
 * @since 2015. 11. 23
 */
@Controller("commonController")
@RequestMapping(value = "/common")
public class CommonController {

    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "cylFileService")
    private CylFileService cylFileService;

    @RequestMapping(value = "/atchFileDownProc")
    public ModelAndView atchFileDownProc(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute CylFileVO fileVO) {
        // 파일 상세 조회
        CylFileVO fv = cylFileService.selectAtchFileDetail(fileVO);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(CylFileDownView.FILE_PARAM_ORGINL_FILE_NAME, fv.getOrginl_file_nm());
        paramMap.put(CylFileDownView.FILE_PARAM_UNIQ_FILE_NAME, fv.getStre_file_nm());
        paramMap.put(CylFileDownView.FILE_PARAM_FILE_PATH, fv.getFile_stre_cours());
        model.addAttribute(CylFileDownView.FILE_PARAM, paramMap);
        return new ModelAndView("fileDownView", model);
    }

    @RequestMapping(value = "/download")
    public ModelAndView doDownload(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        Map<String, String> paramMap = new HashMap<String, String>();
        try{
            String path = URLDecoder.decode(request.getParameter("file_path"), "UTF-8");
            String fileName = URLDecoder.decode(request.getParameter("file_nm"), "UTF-8");
            paramMap.put(CylFileDownView.FILE_PARAM_ORGINL_FILE_NAME, fileName);
            paramMap.put(CylFileDownView.FILE_PARAM_UNIQ_FILE_NAME, fileName);
            paramMap.put(CylFileDownView.FILE_PARAM_FILE_PATH, realPath+path);
        }catch (UnsupportedEncodingException ue){
            ue.printStackTrace();
        }
        model.addAttribute(CylFileDownView.FILE_PARAM, paramMap);
        return new ModelAndView("fileDownView", model);

        /*paramMap.put(FileDownView.FILE_PARAM_FILE_NAME, request.getParameter("file_nm"));
        paramMap.put(FileDownView.FILE_PARAM_FILE_PATH, request.getParameter("file_path"));
        model.addAttribute(CylFileDownView.FILE_PARAM, paramMap);
        return new ModelAndView("filePathDownView", model);*/

    }

    @RequestMapping(value = "/excelDownList")
    public ModelAndView excelDownList(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute CommonVO commonVO) {
        List<CommonVO> list = commonService.selectTabList(commonVO).getVoList();
		JxlsParam jxlsParam = new JxlsParam(JxlsParam.EXCEL_EXT_XLS, "table", "테이블 명세서", "테이블 명세서", 4);
        model.addAttribute("list", list);
        model.addAttribute("jxlsParam", jxlsParam);
        return new ModelAndView("jxlsView", model);
    }

    /**
     * 세션정보(추가 가능)
     *
     * @param request
     * @param response
     * @param model
     * @return mav
     * @
     */
    @RequestMapping(value = "/sessionLoad")
    public ModelAndView sessionLoad(HttpServletRequest request, ModelMap model, @ModelAttribute CommonVO commonVO) {
        model.addAttribute("cdMap", request.getSession().getAttribute(CmmVar.CODE_MAP));
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * 새션갱신
     */
    @RequestMapping(value = "/commonSessionRef")
    public ModelAndView commonSessionRef(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                                         @ModelAttribute CommonVO commonVO) {
        model.addAttribute("result", true);
        return new ModelAndView("jsonViewExtension", model);
    }

    /**
     * 공통코드
     */
    @RequestMapping(value = "/cmmCodeList")
    public ModelAndView cmmCodeList(ModelMap model) {
        model.addAttribute("cdMap", CylCmmCodeMap.getInstance().getCodeValues());
        return new ModelAndView("jsonViewExtension", model);
    }

}
