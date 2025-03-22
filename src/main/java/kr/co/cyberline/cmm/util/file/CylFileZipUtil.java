package kr.co.cyberline.cmm.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FilenameUtils;

public class CylFileZipUtil {
    private static final int COMPRESSION_LEVEL9 = 9;

    private static final int COMPRESSION_LEVEL8 = 8;

    private static final int COMPRESSION_LEVEL7 = 7;

    private static final int COMPRESSION_LEVEL6 = 6;

    private static final int COMPRESSION_LEVEL5 = 5;

    private static final int COMPRESSION_LEVEL4 = 4;

    private static final int COMPRESSION_LEVEL3 = 3;

    private static final int COMPRESSION_LEVEL2 = 2;

    private static final int COMPRESSION_LEVEL1 = 1;

    public String createZipPath(String filePath) {
        File destPath = new File(filePath);
        File[] destFileList = destPath.listFiles();
        String zipFilePath = createZip(destFileList, "", filePath, 0);
        return zipFilePath;
    }

    public String createZipPath(String filePath, String outputFileName) {
        File destPath = new File(filePath);
        File[] destFileList = destPath.listFiles();
        String zipFilePath = createZip(destFileList, outputFileName, filePath, 0);
        return zipFilePath;
    }

    public String createZipPath(String filePath, String outputFileName, String outputFilePath) {
        File destPath = new File(filePath);
        File[] destFileList = destPath.listFiles();
        String zipFilePath = createZip(destFileList, outputFileName, outputFilePath, 0);
        return zipFilePath;
    }

    public String createZipPath(String filePath, String outputFileName, String outputFilePath, int compressionLvl) {
        File destPath = new File(filePath);
        File[] destFileList = destPath.listFiles();
        String zipFilePath = createZip(destFileList, outputFileName, outputFilePath, compressionLvl);
        return zipFilePath;
    }

    public String createZipFiles(File[] files) {
        String zipFilePath = createZip(files, "", files[0].getPath(), 0);
        return zipFilePath;
    }

    public String createZipFiles(File[] files, String outputFileName) {
        String zipFilePath = createZip(files, outputFileName, files[0].getPath(), 0);
        return zipFilePath;
    }

    public String createZipFiles(File[] files, String outputFileName, String outputFilePath) {
        String zipFilePath = createZip(files, outputFileName, outputFilePath, 0);
        return zipFilePath;
    }

    public String createZipFiles(File[] files, String outputFileName, String outputFilePath, int compressionLvl) {
        String zipFilePath = createZip(files, outputFileName, outputFilePath, compressionLvl);
        return zipFilePath;
    }

    private String createZip(File[] entryFiles, String outputFileName, String outputFilePath, int compressionLvl) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ZipOutputStream zos = null;
        BufferedInputStream inBos = null;
        String zipFilePath = "";
        try {
            if (outputFileName.equals("")) {
                outputFileName = FilenameUtils.getBaseName(entryFiles[0].getName()) + ".zip";
            } else if (FilenameUtils.getExtension(outputFileName).equals("")) {
                outputFileName = outputFileName + ".zip";
            } else if (!FilenameUtils.getExtension(outputFileName).equals("zip")) {
                throw new Exception(" >> outputFileName의 확장자는 zip이어야 합니다.");
            }
            outputFilePath = FilenameUtils.getFullPathNoEndSeparator(outputFilePath);
            zipFilePath = outputFilePath + File.separator + outputFileName;
            fos = new FileOutputStream(zipFilePath);
            bos = new BufferedOutputStream(fos);
            zos = new ZipOutputStream(bos);
            if (compressionLvl < 0 || compressionLvl > 9)
                throw new Exception(" >> 압축 레벨은 1~9사이의 정수값이어야 합니다.");
            if (compressionLvl <= 0)
                compressionLvl = 8;
            zos.setLevel(compressionLvl);
            for (int i = 0; i < entryFiles.length; i++) {
                File entryFile = entryFiles[i];
                inBos = new BufferedInputStream(new FileInputStream(entryFile));
                ZipEntry zipEntry = new ZipEntry(entryFile.getName());
                zipEntry.setTime(entryFile.lastModified());
                zos.putNextEntry(zipEntry);
                byte[] buffer = new byte[2048];
                int cnt = 0;
                while ((cnt = inBos.read(buffer, 0, 2048)) != -1)
                    zos.write(buffer, 0, cnt);
                zos.closeEntry();
            }
            zos.finish();
        } catch (Exception ex) {
            zipFilePath = "";
            ex.printStackTrace();
        } finally {
            if (zos != null)
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (inBos != null)
                try {
                    inBos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return zipFilePath;
    }
}
