package kr.co.cyberline.cmm.controller;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.cyberline.cmm.service.*;
import kr.co.cyberline.cmm.util.sec.CylScrtyUtil;
import kr.co.cyberline.cmm.web.file.CylFileService;
import kr.co.cyberline.cmm.web.file.CylFileVO;
import kr.co.cyberline.cmm.web.pagination.CylPaginationSupport;
import kr.co.cyberline.cmm.web.view.CylFileDownView;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

@RestController
public class CommController {

    @Resource(name = "commService")
    private CommService commService;

    @Resource(name = "cylFileService")
    private CylFileService fileService;

    @RequestMapping(value = "/comm/{command}/{namespace}/{queryid}")
    @ResponseBody
    public Map<String, Object> commonApiMapping(HttpServletRequest request, HttpServletResponse response,
                                         @PathVariable("command") String command, @PathVariable("namespace") String namespace,
                                         @PathVariable("queryid") String queryid) throws JSONException, NoSuchAlgorithmException, SQLException {
        Map<String, Object> params = new HashMap<>();
        String jStr = readJSONStringFromRequest(request);
        if (!request.getParameterNames().hasMoreElements() && StringUtils.isNotEmpty(jStr)) {
            JSONObject json_obj = new JSONObject(jStr);
            params = getJSONParameterMap(json_obj);
        } else {
            Enumeration enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String param_name = (String) enu.nextElement();
                params.put(param_name, request.getParameter(param_name));
            }
        }

        if (params.size() > 0 && params.get("pagingEnable") != null) {
            CylPaginationSupport.setPaginationMap(request, params);
        }
        if (params.get("encrypt") != null) {
            params.put("passwd", CylScrtyUtil.encryptPassword(params.get("login_id").toString(), params.get("passwd").toString()));
        }

        params.put("command", command);
        params.put("namespace", namespace);
        params.put("queryid", queryid);

        Map<String, Object> returnMap = new HashMap<>();

        returnMap.put(params.get("command").toString(), commService.commonApi(params));
        returnMap.put("selectKey", params.get("selectKey"));

        return returnMap;
    }

    @RequestMapping(value = "/comm/download/{atch_file_id}")
    public ModelAndView commonFileDownload(HttpServletRequest request, HttpServletResponse response,
                                           @PathVariable("atch_file_id") String atch_file_id, HttpSession session, ModelMap model) throws Exception {
        Map<String, String> paramMap = new HashMap<String, String>();

        CylFileVO vo = new CylFileVO();
        vo.setAtch_file_id(atch_file_id);
        vo.setFile_sn(1);

        CylFileVO fileVO = fileService.selectAtchFileDetail(vo);

        paramMap.put(CylFileDownView.FILE_PARAM_ORGINL_FILE_NAME, fileVO.getOrginl_file_nm());
        paramMap.put(CylFileDownView.FILE_PARAM_UNIQ_FILE_NAME, fileVO.getStre_file_nm());
        paramMap.put(CylFileDownView.FILE_PARAM_FILE_PATH, fileVO.getFile_stre_cours());

        model.addAttribute(CylFileDownView.FILE_PARAM, paramMap);
        return new ModelAndView("fileDownView", model);

    }

    @RequestMapping(value = "/comm/download/{atch_file_id}/{file_sn}")
    public ModelAndView commonFileDownload(HttpServletRequest request, HttpServletResponse response,
                                           @PathVariable("atch_file_id") String atch_file_id, @PathVariable("file_sn") String file_sn, HttpSession session, ModelMap model) throws Exception {
        Map<String, String> paramMap = new HashMap<String, String>();

        CylFileVO vo = new CylFileVO();
        vo.setAtch_file_id(atch_file_id);
        vo.setFile_sn(Integer.parseInt(file_sn));

        CylFileVO fileVO = fileService.selectAtchFileDetail(vo);

        paramMap.put(CylFileDownView.FILE_PARAM_ORGINL_FILE_NAME, fileVO.getOrginl_file_nm());
        paramMap.put(CylFileDownView.FILE_PARAM_UNIQ_FILE_NAME, fileVO.getStre_file_nm());
        paramMap.put(CylFileDownView.FILE_PARAM_FILE_PATH, fileVO.getFile_stre_cours());

        model.addAttribute(CylFileDownView.FILE_PARAM, paramMap);
        return new ModelAndView("fileDownView", model);

    }

    public static String readJSONStringFromRequest(HttpServletRequest request) {
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }

    public static Map<String, Object> getJSONParameterMap(JSONObject json_obj) {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator it = json_obj.keys();

        while (it.hasNext()) {
            String key = (String) it.next();
            if (json_obj.isNull(key)) {
                continue;
            }
            try {
                Object obj = json_obj.get(key);
                if (obj instanceof JSONObject) {
                    JSONObject json = (JSONObject) obj;
                    map.put(key, getJSONParameterMap(json));
                } else if (obj instanceof JSONArray) {
                    JSONArray array = (JSONArray) obj;
                    List list = new ArrayList();
                    for (int i = 0; i < array.length(); i++) {
                        list.add(getJSONParameterMap(array.getJSONObject(i)));
                    }
                    map.put(key, list);
                } else {
                    map.put(key, obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
