package kr.co.cyberline.cmm.web.view;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class CylFileDownView extends AbstractView {

    @Value("${system.fileDown.encoding}")
    private String fileEncoding;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());



    public static final String FILE_PARAM = "storageFile";
    public static final String FILE_PARAM_ORGINL_FILE_NAME = "orginlFileNm";
    public static final String FILE_PARAM_UNIQ_FILE_NAME = "uniqFileNm";
    public static final String FILE_PARAM_FILE_PATH = "filePath";
    public static final String FILE_PARAM_FILE_LOCATION = "fileLocation";
    public static final String FILE_PARAM_FILE_DELETE = "fileDelete";
    public static final String FILE_DELETE_ENABLE = "Y";
    public static final String FILE_DELETE_DISABLE = "N";


    @Override
    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileNM = "";
        String uniqFileNM = "";
        String filePath = "";
        String fileNotFoundMsg = "The file does not exist";
        String filePathErrorMsg = "File information is incorrect";
        String fileDelete;

        Map<String, String> paramMap = (Map<String, String>) model.get(FILE_PARAM);
        ServletOutputStream outputStream = null;
        FileInputStream fis = null;

        if (paramMap == null) {
            paramMap = new HashMap<String, String>();
            paramMap.put(FILE_PARAM_UNIQ_FILE_NAME, request.getParameter(FILE_PARAM_UNIQ_FILE_NAME));
            paramMap.put(FILE_PARAM_FILE_PATH, request.getParameter(FILE_PARAM_FILE_PATH));
            paramMap.put(FILE_PARAM_FILE_LOCATION, request.getParameter(FILE_PARAM_FILE_LOCATION));
            paramMap.put(FILE_PARAM_FILE_DELETE, request.getParameter(FILE_PARAM_FILE_DELETE));
        }

        fileNM = paramMap.get(FILE_PARAM_ORGINL_FILE_NAME);
        uniqFileNM = paramMap.get(FILE_PARAM_UNIQ_FILE_NAME);
        filePath = paramMap.get(FILE_PARAM_FILE_PATH) + File.separator;
        fileDelete = paramMap.get(FILE_PARAM_FILE_DELETE);

        logger.info("##fileDownView [fileName] = " + fileNM);
        logger.info("##fileDownView [uniqFileName] = " + uniqFileNM);
        logger.info("##fileDownView [filePath] = " + filePath);
        logger.info("##fileDownView [fileEncoding] = " + fileEncoding);
        logger.info("##fileDownView [fileDelete] = " + fileDelete);

        response.setHeader("Content-Type", "text/html; charset=" + fileEncoding);

        if (!CylStringUtil.isNotBlank(fileNM) || !CylStringUtil.isNotBlank(uniqFileNM) || !CylStringUtil.isNotBlank(filePath)) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + fileNotFoundMsg + "');history.back();</script>");
            return;
        }

        //웹 취약점을 노린 파일다운로드 방지
        if(fileNM.lastIndexOf("../") > 0) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + filePathErrorMsg + "');history.back();</script>");
            return;
        }

        try {
            File tempFile = new File(filePath + File.separator + uniqFileNM);

            int filesize = (int) tempFile.length();
            logger.info("## fullFilePath = " + tempFile.getPath());
            logger.info("## fileExists = " + tempFile.exists());

            try {
                logger.info("tempFile.canRead() = " + tempFile.canRead());
                if (!tempFile.exists() || !tempFile.canRead()) {
                    PrintWriter out = response.getWriter();
                    logger.info("## " + fileNotFoundMsg);
                    out.println("<script>alert('" + fileNotFoundMsg + "');history.back();</script>");
                    return;
                }
            } catch (Exception e) {
                PrintWriter out = response.getWriter();
                logger.error(e.toString());
                logger.info("## " + fileNotFoundMsg);
                out.println("<script>alert('" + fileNotFoundMsg + "');history.back();</script>");
                return;
            }

            String fileExt = FilenameUtils.getExtension(fileNM);

            if (fileExt.trim().equalsIgnoreCase("txt")){
                response.setContentType("text/plain" );
            } else if (fileExt.trim().equalsIgnoreCase("doc")){
                response.setContentType("application/msword" );
            } else if (fileExt.trim().equalsIgnoreCase("docx")){
                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document" );
            } else if (fileExt.trim().equalsIgnoreCase("xls")){
                response.setContentType("application/vnd.ms-excel" );
            } else if (fileExt.trim().equalsIgnoreCase("xlsx")){
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
            } else if (fileExt.trim().equalsIgnoreCase("pdf")){
                response.setContentType("application/pdf" );
            } else if (fileExt.trim().equalsIgnoreCase("ppt")){
                response.setContentType("application/vnd.ms-powerpoint");
            } else if (fileExt.trim().equalsIgnoreCase("pptx")){
                response.setContentType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
            } else if (fileExt.trim().equalsIgnoreCase("hwp")){
                response.setContentType("application/hwp");
            } else{
                response.setContentType("application/octet-stream" );
            }

            String browser = request.getHeader("User-Agent");
            if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){
                fileNM = URLEncoder.encode(fileNM,"UTF-8").replaceAll("\\+", "%20");
            } else {
                fileNM = new String(fileNM.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setHeader("Content-Disposition", "attachment; filename=\""+ fileNM +"\"");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setContentLength((filesize));
            response.setHeader("cache-control", "no-cache");

            fis = new FileInputStream(tempFile);
            outputStream = response.getOutputStream();
            FileCopyUtils.copy(fis, outputStream);
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            response.setHeader("Content-Type", "text/html; charset=UTF-8");
            out.println("<script>alert('" + fileNotFoundMsg + "');history.back();</script>");

            logger.error(e.toString());
            logger.info("## " + fileNotFoundMsg);
        } finally {
            if(fis != null) {
                fis.close();
            }

            if (outputStream != null) {
                outputStream.flush();
            }

            if ( fileDelete != null && FILE_DELETE_ENABLE.equalsIgnoreCase(fileDelete) ){
                new File(filePath + File.separator + uniqFileNM).delete();
            }
        }
    }
}
