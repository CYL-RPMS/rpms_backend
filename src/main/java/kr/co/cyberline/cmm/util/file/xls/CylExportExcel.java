package kr.co.cyberline.cmm.util.file.xls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CylExportExcel {
    private static final Logger logger = LoggerFactory.getLogger(CylExportExcel.class);

    public static String createExcel(CylExportExcelVO cylExportExcelVO) {
        CylWriteExcelParameter wep = new CylWriteExcelParameter();
        setData(cylExportExcelVO, wep);
        CylWriteExcelHandler.defaultWriteExcel(wep);
        logger.info("## ExcelExport : end");
        return wep.getFilePath() + wep.getFileNm();
    }

    public static String createExcel(CylExportExcelVO cylExportExcelVO, CylWriteExcelParameter wep) throws Exception {
        setData(cylExportExcelVO, wep);
        CylWriteExcelHandler.customWriteExcel(wep);
        logger.info("## ExcelExport : end");
        return wep.getFilePath() + wep.getFileNm();
    }

    private static void setData(CylExportExcelVO cylExportExcelVO, CylWriteExcelParameter wep) {
        logger.info("## ExcelExport : begin");
        String excelFile = cylExportExcelVO.getFileNm();
        String excelTitle = cylExportExcelVO.getTitle();
        String excelSheetNm = cylExportExcelVO.getSheetNm();
        String[] excelHeader = cylExportExcelVO.getHeader();
        wep.setFilePath(cylExportExcelVO.getFilePath());
        wep.setFileNm(excelFile);
        wep.setTitle(excelTitle);
        wep.setSheetNm(excelSheetNm);
        wep.setHeader(excelHeader);
        wep.setRowData(cylExportExcelVO.getDataList());
        logger.info("## excelFile ::: " + cylExportExcelVO.getFileNm());
        logger.info("## excelTitle ::: " + cylExportExcelVO.getTitle());
        logger.info("## excelSheetNm ::: " + cylExportExcelVO.getSheetNm());
        logger.info("## excelSheetNm ::: " + cylExportExcelVO.getSheetNm());
        logger.info("## excelPath ::: " + wep.getFilePath());
    }
}
