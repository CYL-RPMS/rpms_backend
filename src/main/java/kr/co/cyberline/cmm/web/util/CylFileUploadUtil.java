package kr.co.cyberline.cmm.web.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import kr.co.cyberline.cmm.util.CylResourceCloseHelper;
import kr.co.cyberline.cmm.util.file.CylFileUtil;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.file.CylFileVO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class CylFileUploadUtil {
    public static List<CylFileVO> copyUploadFiles(List<CylFileVO> atchFiles, String tempPath, String where, String serverSubPath) {
        List<CylFileVO> list = new ArrayList<>();
        if (atchFiles != null && atchFiles.size() > 0) {
            serverSubPath = CylStringUtil.isNotBlank(serverSubPath) ? (CylFileUtil.SEPERATOR + serverSubPath) : "";
            for (CylFileVO fv : atchFiles) {
                File source = new File(CylWebUtils.filePathBlackList(tempPath + CylFileUtil.SEPERATOR + fv.getStre_file_nm()));
                File target = new File(CylWebUtils.filePathBlackList(where + serverSubPath + CylFileUtil.SEPERATOR + fv.getStre_file_nm()));
                try {
                    CylFileUtil.copyFile(source, target);
                    CylFileUtil.deleteFile(source.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fv.setFile_stre_cours(where + serverSubPath);
                list.add(fv);
            }
        }
        return list;
    }

    public static List<CylFileVO> multiPartUploadFiles(HttpServletRequest request, String where, String serverSubPath) {
        List<CylFileVO> list = new ArrayList<>();
        MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest)request;
        Iterator<?> fileIter = mptRequest.getFileNames();
        while (fileIter.hasNext()) {
            String se = (String)fileIter.next();
            List<MultipartFile> mFiles = mptRequest.getFiles(se);
            if (!CollectionUtils.isEmpty(mFiles))
                for (MultipartFile mFile : mFiles)
                    doFileUpload(where, serverSubPath, list, mFile, se);
        }
        return list;
    }

    private static void doFileUpload(String where, String serverSubPath, List<CylFileVO> list, MultipartFile mFile, String se) {
        CylFileVO vo = new CylFileVO();
        serverSubPath = CylStringUtil.isNotBlank(serverSubPath) ? (CylFileUtil.SEPERATOR + serverSubPath) : "";
        String tmp = mFile.getOriginalFilename();
        if (tmp.lastIndexOf("\\") >= 0)
            tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
        vo.setOrginl_file_nm(tmp);
        vo.setFile_stre_cours(where + serverSubPath);
        vo.setStre_file_nm(CylFileUtil.getPhysicalFileName());
        vo.setFile_extsn(FilenameUtils.getExtension(tmp));
        vo.setFile_size(mFile.getSize());
        vo.setMode(se);
        if (mFile.getSize() > 0L) {
            InputStream is = null;
            try {
                is = mFile.getInputStream();
                CylFileUtil.saveFile(is, new File(CylWebUtils.filePathBlackList(vo.getFile_stre_cours() + CylFileUtil.SEPERATOR + vo.getStre_file_nm())));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CylResourceCloseHelper.close(new Closeable[] { is });
            }
            list.add(vo);
        }
    }
}
