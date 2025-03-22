package kr.co.cyberline.cmm.util.file.xls;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import jxl.Workbook;
import jxl.format.*;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CylWriteExcelHandler {
    private static final Logger logger = LoggerFactory.getLogger(CylWriteExcelHandler.class);

    private static final int MAX_ROW_COUNT = 50000;

    public static boolean defaultWriteExcel(CylWriteExcelParameter wep) {
        boolean bMakeExcel = true;
        try {
            wep.setTitleFormat(getDefaultTitleFormat());
            wep.setHeaderFormat(getDefaultHeaderFormat());
            wep.setRowFormat(getDefaultRowFormat());
            writeExcel(wep);
        } catch (IOException io) {
            logger.error(io.toString());
        } catch (WriteException e) {
            logger.error(e.toString());
        }
        return bMakeExcel;
    }

    public static boolean customWriteExcel(CylWriteExcelParameter wep) throws Exception {
        boolean bMakeExcel = true;
        try {
            wep.setTitleFormat(wep.getTitleFormat());
            wep.setHeaderFormat(wep.getHeaderFormat());
            wep.setRowFormat(wep.getRowFormat());
            writeExcel(wep);
        } catch (IOException io) {
            logger.error(io.toString());
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return bMakeExcel;
    }

    public static void writeExcel(CylWriteExcelParameter wep) throws WriteException, IOException {
        File excelFile = new File(wep.getFilePath());
        if (!excelFile.isDirectory())
            excelFile.mkdirs();
        excelFile = new File(wep.getFilePath() + wep.getFileNm());
        logger.info("## createFileName = " + excelFile.getName());
        WritableWorkbook workbook = Workbook.createWorkbook(excelFile);
        logger.info("## workBook Created");
        ArrayList<Hashtable<String, Object>> alRowData = wep.getRowData();
        try {
            if (alRowData.size() > 50000) {
                int nSelCount = Math.abs(alRowData.size() / 50000) + 1;
                int nStartPoint = 0;
                int nEndPoint = 0;
                for (int i = 0; i < nSelCount; i++) {
                    WritableSheet sheet = workbook.createSheet(wep.getSheetNm() + "_" + i, i);
                    if (i == 0)
                        writeTitle(sheet, wep.getHeader(), wep.getTitle(), wep.getTitleFormat());
                    writeHeader(sheet, wep.getHeader(), wep.getHeaderFormat());
                    nStartPoint = i * 50000;
                    nEndPoint = (i + 1) * 50000;
                    if (i != nSelCount - 1) {
                        selWriteExcel(alRowData, sheet, nStartPoint, nEndPoint, wep.getRowFormat());
                    } else {
                        selWriteExcel(alRowData, sheet, nStartPoint, alRowData.size(), wep.getRowFormat());
                    }
                }
            } else {
                WritableSheet sheet = workbook.createSheet(wep.getSheetNm(), 0);
                writeTitle(sheet, wep.getHeader(), wep.getTitle(), wep.getTitleFormat());
                writeHeader(sheet, wep.getHeader(), wep.getHeaderFormat());
                selWriteExcel(alRowData, sheet, 0, alRowData.size(), wep.getRowFormat());
            }
        } catch (Exception e) {
            logger.error(e.toString());
        } finally {
            workbook.write();
            workbook.close();
        }
    }

    private static void writeTitle(WritableSheet sheet, String[] arrHeader, String szTitle, WritableCellFormat titleFormat) throws WriteException {
        int nCellCount = arrHeader.length;
        sheet.mergeCells(0, 0, nCellCount, 0);
        Label titlelabel = new Label(0, 0, szTitle, (CellFormat)titleFormat);
        sheet.addCell((WritableCell)titlelabel);
    }

    private static void writeHeader(WritableSheet sheet, String[] arrHeader, WritableCellFormat headerFormat) throws WriteException {
        int nCellCount = arrHeader.length;
        Label headlabelCount = new Label(0, 1, "순번", (CellFormat)headerFormat);
                sheet.addCell((WritableCell)headlabelCount);
        Label headlabel = null;
        for (int nCellNum = 0; nCellNum < nCellCount; nCellNum++) {
            headlabel = new Label(nCellNum + 1, 1, arrHeader[nCellNum], (CellFormat)headerFormat);
            sheet.addCell((WritableCell)headlabel);
        }
    }

    private static void selWriteExcel(ArrayList<Hashtable<String, Object>> alRowData, WritableSheet sheet, int nStart, int nEnd, WritableCellFormat cellFormat) throws WriteException {
        Hashtable htCellData = null;
        String szCellData = "";
        Number num = null;
        Label label2 = null;
        int k = 0;
        for (int rownum = nStart; rownum < nEnd; rownum++) {
            htCellData = alRowData.get(rownum);
            for (int nCellnum = 0; nCellnum <= htCellData.size(); nCellnum++) {
                if (nCellnum == 0) {
                    num = new Number(nCellnum, k + 2, (rownum + 1), (CellFormat)cellFormat);
                    sheet.addCell((WritableCell)num);
                } else {
                    szCellData = (String)htCellData.get(Integer.toString(nCellnum));
                    label2 = new Label(nCellnum, k + 2, szCellData, (CellFormat)cellFormat);
                    sheet.setColumnView(nCellnum, 20);
                    sheet.addCell((WritableCell)label2);
                }
            }
            k++;
        }
    }

    public static WritableCellFormat getDefaultTitleFormat() throws WriteException {
        WritableFont wf_title = new WritableFont(WritableFont.createFont("굴림"), 14, WritableFont.BOLD, false, UnderlineStyle.SINGLE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT);
                WritableCellFormat wcf_title_format = new WritableCellFormat(wf_title);
        wcf_title_format.setVerticalAlignment(VerticalAlignment.CENTRE);
        wcf_title_format.setAlignment(Alignment.CENTRE);
        return wcf_title_format;
    }

    public static WritableCellFormat getDefaultHeaderFormat() throws WriteException {
        WritableFont wf_header = new WritableFont(WritableFont.createFont("굴림"), 10, WritableFont.BOLD, false, UnderlineStyle.SINGLE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT);
                WritableCellFormat wcf_header_format = new WritableCellFormat(wf_header);
        wcf_header_format.setVerticalAlignment(VerticalAlignment.CENTRE);
        wcf_header_format.setAlignment(Alignment.CENTRE);
        wcf_header_format.setBorder(Border.ALL, BorderLineStyle.THIN);
        wcf_header_format.setWrap(true);
        return wcf_header_format;
    }

    public static WritableCellFormat getDefaultRowFormat() throws WriteException {
        WritableFont wf_content = new WritableFont(WritableFont.createFont("굴림"), 10, WritableFont.NO_BOLD, false, UnderlineStyle.SINGLE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT);
                WritableCellFormat wcf_content_format = new WritableCellFormat(wf_content);
        wcf_content_format.setVerticalAlignment(VerticalAlignment.CENTRE);
        wcf_content_format.setAlignment(Alignment.JUSTIFY);
        wcf_content_format.setBorder(Border.ALL, BorderLineStyle.THIN);
        wcf_content_format.setWrap(true);
        return wcf_content_format;
    }
}
