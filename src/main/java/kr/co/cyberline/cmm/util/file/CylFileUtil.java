package kr.co.cyberline.cmm.util.file;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kr.co.cyberline.cmm.util.CylResourceCloseHelper;
import kr.co.cyberline.cmm.util.lang.CylBaseUUIDUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CylFileUtil extends FileUtils {
    public static final int BUFFER_SIZE = 8192;

    public static final String SEPERATOR = File.separator;

    private static final Logger logger = LoggerFactory.getLogger(CylFileUtil.class);

    public static boolean fileExist(String strPathName) throws Exception {
        File f = null;
        try {
            f = new File(strPathName);
            if (!f.exists())
                return false;
        } catch (Exception e) {
            logger.error("fileExist Exception : " + e.getMessage());
            throw e;
        }
        return true;
    }

    public static boolean folderCreate(String path) throws Exception {
        boolean resultFlag = false;
        String folderPath = FilenameUtils.getFullPath(path);
        File dirCheck = new File(folderPath);
        if (!dirCheck.isDirectory())
            dirCheck.mkdirs();
        resultFlag = true;
        return resultFlag;
    }

    public static Iterator<File> iteratorDir(String path) {
        File filePath = new File(path);
        List<File> fileList = new ArrayList<>();
        if (filePath.exists()) {
            File[] files = filePath.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory())
                    fileList.add(files[i]);
            }
        }
        return fileList.iterator();
    }

    public static void fileCopy(String from, String to) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(from);
            fos = new FileOutputStream(to);
            int BUFSIZ = 1024;
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) > 0)
                fos.write(buf, 0, len);
        } catch (Exception exception) {

        } finally {
            if (fis != null)
                fis.close();
            if (fos != null)
                fos.close();
        }
    }

    public static boolean deleteFile(String filePath) {
        File file = null;
        File delFile = null;
        boolean isDelete = false;
        try {
            file = new File(filePath);
            if (!file.exists())
                return false;
            if (file.isFile()) {
                file.delete();
                return true;
            }
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                delFile = new File(filePath, files[i]);
                delFile.delete();
            }
            if (file.exists()) {
                if (file.delete()) {
                    isDelete = true;
                } else {
                    logger.info("File deletion failed : " + filePath);
                }
            } else {
                logger.info("Not exist File : " + filePath);
            }
        } catch (Exception e) {
            logger.error("File Delete Exception : " + filePath, e);
        }
        return isDelete;
    }

    public static String getFileExtention(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

    public static String getPhysicalFileName() {
        return CylBaseUUIDUtil.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static long saveFile(InputStream is, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        OutputStream os = null;
        long size = 0L;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                size += bytesRead;
                os.write(buffer, 0, bytesRead);
            }
        } finally {
            CylResourceCloseHelper.close(new Closeable[] { os });
        }
        return size;
    }
}
