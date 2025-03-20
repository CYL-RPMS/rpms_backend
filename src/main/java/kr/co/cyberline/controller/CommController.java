package kr.co.cyberline.controller;

import io.micrometer.common.util.StringUtils;
import kr.co.cyberline.cmm.util.sec.CylScrtyUtil;
import kr.co.cyberline.cmm.web.pagination.CylPaginationSupport;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
public class CommController {

    public ModelAndView commonApiMapping(HttpServletRequest request, HttpServletResponse response,
                                          @PathVariable("command") String command, @PathVariable("namespace") String namespace,
                                          @PathVariable("queryid") String queryid, HttpSession session, ModelMap model) throws JSONException, NoSuchAlgorithmException {
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

        model.addAttribute(params.get("command").toString(), commService.commonApi(params));

        return new ModelAndView("jsonView", model);
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
