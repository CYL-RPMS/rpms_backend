package kr.co.cyberline.cmm.util.file.xls;

import java.util.ArrayList;
import java.util.Hashtable;

public class CylExportExcelVO {
    private String fileNm;

    private String title;

    private String sheetNm;

    private String[] header;

    private String filePath;

    private ArrayList<Hashtable<String, Object>> dataList;

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileNm() {
        return this.fileNm;
    }

    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSheetNm() {
        return this.sheetNm;
    }

    public void setSheetNm(String sheetNm) {
        this.sheetNm = sheetNm;
    }

    public String[] getHeader() {
        return this.header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public ArrayList<Hashtable<String, Object>> getDataList() {
        return this.dataList;
    }

    public void setDataList(ArrayList<Hashtable<String, Object>> dataList) {
        this.dataList = dataList;
    }
}
