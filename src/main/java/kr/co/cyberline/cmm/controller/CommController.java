package kr.co.cyberline.cmm.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.cyberline.cmm.jwt.security.JwtTokenProvider;
import kr.co.cyberline.cmm.service.*;
import kr.co.cyberline.cmm.util.sec.CylScrtyUtil;
import kr.co.cyberline.cmm.web.file.CylFileService;
import kr.co.cyberline.cmm.web.file.CylFileVO;
import kr.co.cyberline.cmm.web.pagination.CylPaginationSupport;
import kr.co.cyberline.cmm.web.view.CylFileDownView;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class CommController {

    private final JwtTokenProvider jwtTokenProvider;

    @Resource(name = "commService")
    private CommService commService;

    @Resource(name = "cylFileService")
    private CylFileService fileService;

    @RequestMapping(value = "/comm/{command}/{namespace}/{queryid}")
    @ResponseBody
    public Map<String, Object> commonApiMapping(
            HttpServletRequest request
            ,@PathVariable("command") String command
            ,@PathVariable("namespace") String namespace
            ,@PathVariable("queryid") String queryid
            ,@RequestParam Map<String, String> queryParams
            ,@RequestBody(required = false) Map<String, Object> bodyParams) throws  NoSuchAlgorithmException, SQLException {
        Map<String, Object> params = new HashMap<>();

        // GET 요청에서 사용
        if (queryParams != null) {
            params.putAll(queryParams);
        }

        // JSON 바디 파라미터 추가 POST 요청에서 사용
        if (bodyParams != null) {
            params.putAll(bodyParams);
        }

        if (params.size() > 0 && params.get("pagingEnable") != null) {
            CylPaginationSupport.setPaginationMap(request,params);
        }
        if (params.get("encrypt") != null) {
            params.put("passwd", CylScrtyUtil.encryptPassword(params.get("login_id").toString(), params.get("passwd").toString()));
        }

        //사용자 토큰 검사
        String token = jwtTokenProvider.resolveToken(request);
        boolean valid = jwtTokenProvider.validateToken(token);
        params.put("login_user_id", valid ? jwtTokenProvider.getUserIdFromToken(token) : "GUEST");
        params.put("login_user_loginId", valid ? jwtTokenProvider.getLoginIdFromToken(token) :"GUEST");
        params.put("login_author_id", valid ? jwtTokenProvider.getAuthorIdFromToken(token) : "GUEST");

        params.put("command", command);
        params.put("namespace", namespace);
        params.put("queryid", queryid);

        Map<String, Object> returnMap = new HashMap<>();

        returnMap.put(params.get("command").toString(), commService.commonApi(params));
        returnMap.put("selectKey", params.get("selectKey"));

        return returnMap;
    }

    @RequestMapping(value = "/comm/download/{atch_file_id}")
    public ModelAndView commonFileDownload(@PathVariable("atch_file_id") String atch_file_id, ModelMap model) throws Exception {
        Map<String, String> paramMap = new HashMap<String, String>();

        CylFileVO vo = new CylFileVO();
        vo.setAtch_file_id(atch_file_id);
        vo.setFile_sn(1);

        return getModelAndView(model, paramMap, vo);

    }

    @RequestMapping(value = "/comm/download/{atch_file_id}/{file_sn}")
    public ModelAndView commonFileDownload(@PathVariable("atch_file_id") String atch_file_id, @PathVariable("file_sn") String file_sn, ModelMap model) throws Exception {
        Map<String, String> paramMap = new HashMap<String, String>();

        CylFileVO vo = new CylFileVO();
        vo.setAtch_file_id(atch_file_id);
        vo.setFile_sn(Integer.parseInt(file_sn));

        return getModelAndView(model, paramMap, vo);

    }

    private ModelAndView getModelAndView(ModelMap model, Map<String, String> paramMap, CylFileVO vo) {
        CylFileVO fileVO = fileService.selectAtchFileDetail(vo);

        paramMap.put(CylFileDownView.FILE_PARAM_ORGINL_FILE_NAME, fileVO.getOrginl_file_nm());
        paramMap.put(CylFileDownView.FILE_PARAM_UNIQ_FILE_NAME, fileVO.getStre_file_nm());
        paramMap.put(CylFileDownView.FILE_PARAM_FILE_PATH, fileVO.getFile_stre_cours());

        model.addAttribute(CylFileDownView.FILE_PARAM, paramMap);
        return new ModelAndView("fileDownView", model);
    }

}
