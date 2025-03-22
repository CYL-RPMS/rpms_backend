package kr.co.cyberline.cmm.util.file.xls;

import java.util.ArrayList;
import java.util.Hashtable;
import jxl.write.WritableCellFormat;

public class CylWriteExcelParameter {
    private ArrayList<Hashtable<String, Object>> rowData;

    private String[] header;

    private String title;

    private WritableCellFormat titleFormat;

    private WritableCellFormat headerFormat;

    private WritableCellFormat rowFormat;

    private String filePath;

    private String fileNm;

    private String sheetNm;

    public String getFileNm() {
        return this.fileNm;
    }

    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String[] getHeader() {
        return this.header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public WritableCellFormat getHeaderFormat() {
        return this.headerFormat;
    }

    public void setHeaderFormat(WritableCellFormat headerFormat) {
        this.headerFormat = headerFormat;
    }

    public ArrayList<Hashtable<String, Object>> getRowData() {
        return this.rowData;
    }

    public void setRowData(ArrayList<Hashtable<String, Object>> rowData) {
        this.rowData = rowData;
    }

    public WritableCellFormat getRowFormat() {
        return this.rowFormat;
    }

    public void setRowFormat(WritableCellFormat rowFormat) {
        this.rowFormat = rowFormat;
    }

    public String getSheetNm() {
        return this.sheetNm;
    }

    public void setSheetNm(String sheetNm) {
        this.sheetNm = sheetNm;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WritableCellFormat getTitleFormat() {
        return this.titleFormat;
    }

    public void setTitleFormat(WritableCellFormat titleFormat) {
        this.titleFormat = titleFormat;
    }
}
