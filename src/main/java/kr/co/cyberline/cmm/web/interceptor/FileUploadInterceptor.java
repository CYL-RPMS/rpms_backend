package kr.co.cyberline.cmm.web.interceptor;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.file.CylFileService;
import kr.co.cyberline.cmm.web.file.CylFileVO;
import kr.co.cyberline.cmm.web.util.CylFileUploadUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import java.util.*;

@Component
public class FileUploadInterceptor implements HandlerInterceptor {

    @Value("${system.storage.path}")
    private String stroagePath;

    @Value("${system.storage.atch.path}")
    private String atchPath;

    @Resource(name = "atchFileIdGnrService")
    private EgovIdGnrService atchFileIdGnrService;

    @Resource(name = "cylFileService")
    private CylFileService fileService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler
    ) throws Exception {
        String contentType = request.getContentType() != null ? request.getContentType() : "";
        if (contentType.indexOf("multipart/form-data") != -1) {
            MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;
            Iterator<String> itr = multiReq.getFileNames();

            CylFileVO fileVO = new CylFileVO();
            Map<String, Object> map = new HashMap<String, Object>();

            // view 단에서 return 받을 insert한 file 정보
            List<Map<String, Object>> paramFileList = new ArrayList<Map<String, Object>>();


            this.getParameterMap(map, multiReq);

            if (itr != null) {
                // 사용여부
                fileVO.setUse_at("Y");

                int rs = 0;
                if (fileVO != null) {
                    // 저장파일경로
                    String storeFilePath = stroagePath + atchPath;
                    // 파일 업로드
                    List<CylFileVO> file_list = CylFileUploadUtil.multiPartUploadFiles(request, CylStringUtil.nvl(storeFilePath), "");
                    // 파일속성 등록
                    String mode = "";
                    String atch_file_id = "";
                    if (CollectionUtils.isNotEmpty(file_list)) {
                        for (CylFileVO vo : file_list) {
                            if(!StringUtils.equals(mode, vo.getMode())){
                                if(map.get(vo.getMode() + "_id") == null || ObjectUtils.isEmpty(map.get(vo.getMode() + "_id"))) {
                                    atch_file_id = atchFileIdGnrService.getNextStringId();
                                    fileVO.setAtch_file_id(atch_file_id);
                                    map.put(vo.getMode(), atch_file_id);

                                    fileService.insertAtchFile(fileVO);
                                    map.put(vo.getMode() + "_id", atch_file_id);
                                    map.put(vo.getMode() + "_nm", vo.getOrginl_file_nm());

                                }
                                mode = vo.getMode();
                            } else if(map.get(vo.getMode() + "_nm") != null && !ObjectUtils.isEmpty(map.get(vo.getMode() + "_nm"))){
                                map.put(vo.getMode() + "_nm", map.get(vo.getMode() + "_nm") + "|,|" + vo.getOrginl_file_nm());
                            }
                            // 파일속성상세 등록
                            vo.setAtch_file_id(atch_file_id);
                            vo.setUse_at(fileVO.getUse_at());

                            fileService.insertAtchFileDetail(vo);

                            // param_file_list 객체 추가
                            map.put("atch_file_id",vo.getAtch_file_id());
                            map.put("file_sn", vo.getFile_sn());
                            map.put("file_stre_cours", vo.getFile_stre_cours());
                            map.put("stre_file_nm", vo.getStre_file_nm());
                            paramFileList.add(map);
                        }
                    }
                }
            }
            request.setAttribute("parameterMap", map);
            request.setAttribute("paramFileList", paramFileList);
        }
        return true;
    }

    private void getParameterMap(Map<String, Object> params, MultipartHttpServletRequest request) throws Exception {
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String param_name = (String) enu.nextElement();
            params.put(param_name, request.getParameter(param_name));
        }
    }

}
