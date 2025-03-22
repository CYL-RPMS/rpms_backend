package kr.co.cyberline.cmm.util.file.xls;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class CylXLSParser {
    private Sheet xlsWorkSheet = null;

    private boolean isParsed = false;

    private int numberOfRows = 0;

    private int numberOfColumns = 0;

    private String XLSFilePath = null;

    public CylXLSParser() {}

    public CylXLSParser(String xlsFilePath) {
        this();
        if (xlsFilePath != null) {
            this.XLSFilePath = xlsFilePath;
        } else {
            System.out.println("XLSParser.XLSParser() :: XLS 파일의 결로가 null이거나 잘못된 경로입니다.");
        }
    }

    public void clear() {
        System.out.println("XLSParser.clear() :: Cleaning member variables...START");
        this.xlsWorkSheet = null;
        this.isParsed = false;
        this.numberOfRows = 0;
        this.numberOfColumns = 0;
        this.XLSFilePath = null;
        System.out.println("XLSParser.clear() :: Cleaning member variables...DONE");
        System.out.println("XLSParser.clear() :: Call Garbage Collector_START");
        System.gc();
        System.out.println("XLSParser.clear() :: Call Garbage Collector_DONE");
    }

    public Cell getCell(int rowIndex, short columnIndex) {
        if (this.isParsed) {
            Cell[] row = getRow(rowIndex);
            if (row != null) {
                if (columnIndex < row.length)
                    return row[columnIndex];
                System.out.println("XLSParser.getCell() :: column index--> " + columnIndex);
                return null;
            }
            System.out.println("XLSParser.getCell() :: Row를 가져오지 못했습니다");
            return null;
        }
        System.out.println("XLSParser.getCell() :: Parser가 초기화되지 않았습니다.");
        return null;
    }

    public String getCellValue(int rowIndex, short columnIndex) {
        Cell cell = getCell(rowIndex, columnIndex);
        if (cell != null)
            return cell.getContents();
        return "";
    }

    public int getNumberOfColumns() {
        if (this.isParsed)
            return this.numberOfColumns;
        System.out.println("XLSParser.getNumberOfCellsPerSheet() :: Parser가 초기화되지 않았습니다.");
        return -1;
    }

    public int getNumOfRows() {
        if (this.isParsed)
            return this.numberOfRows;
        System.out.println("XLSParser.getNumOfRowsPerSheet() :: Parser가 초기화되지 않았습니다.");
        return -1;
    }

    public Cell[] getRow(int rowIndex) {
        if (rowIndex < 0) {
            System.out.println("XLSParser.getRow() :: 옳바르지 않은 row index입니다.--> " + rowIndex);
            return null;
        }
        if (this.isParsed) {
            if (rowIndex < this.numberOfRows)
                return this.xlsWorkSheet.getRow(rowIndex);
            System.out.println("XLSParser.getRow() :: SheetRow에 존재하지 않는 Row입니다.");
            return null;
        }
        System.out.println("XLSParser.getRow() :: Parser가 초기화되지 않았습니다.");
        return null;
    }

    public Cell[] getCol(int colIndex) {
        if (colIndex < 0) {
            System.out.println("XLSParser.getRow() :: 올바르지 않은 col index입니다.--> " + colIndex);
            return null;
        }
        if (this.isParsed) {
            if (colIndex < this.numberOfColumns)
                return this.xlsWorkSheet.getColumn(colIndex);
            System.out.println("XLSParser.getRow() :: Sheet에 존재하지 않는 Column입니다.");
            return null;
        }
        System.out.println("XLSParser.getRow() :: Parser가 초기화되지 않았습니다.");
        return null;
    }

    public String[] getRowValue(int rowIndex) {
        Cell[] row = getRow(rowIndex);
        String[] rowValue = new String[row.length];
        for (int i = 0; i < row.length; i++) {
            if (row[i] != null) {
                rowValue[i] = row[i].getContents();
            } else {
                rowValue[i] = new String("");
            }
        }
        return rowValue;
    }

    public Sheet getSheet() {
        if (this.isParsed)
            return this.xlsWorkSheet;
        System.out.println("XLSParser.getSheet() :: Parser가 초기화되지 않았습니다.");
        return null;
    }

    public String getSheetName() {
        if (this.isParsed)
            return this.xlsWorkSheet.getName();
        System.out.println("XLSParser.getSheetName() :: Parser가 초기화되지 않았습니다.");
        return null;
    }

    public String getXLSFilePath() {
        return this.XLSFilePath;
    }

    public boolean isParsed() {
        return this.isParsed;
    }

    public boolean parseXLS(File fromFile, int sheetIndex) {
        boolean result = false;
        if (fromFile != null && fromFile.isFile()) {
            this.XLSFilePath = fromFile.getPath();
            return parseXLS(sheetIndex);
        }
        System.out.println("XLSParser.parseXLS(String) :: XLS file is null or not a file");
        return false;
    }

    public boolean parseXLS(int sheetIndex) {
        if (this.XLSFilePath == null) {
            System.out.println("XLSParser.parseXLS() :: XLS 파일 경로가 지정되지 않았거나  .xls 타입이 아닙니다.");
            this.isParsed = false;
        } else {
            try {
                System.out.println("XLSParser.parseXLS() :: ----------XLS 파일 Parsing 준비 ------------" );
                        Workbook book = Workbook.getWorkbook(new File(this.XLSFilePath));
                System.out.println("XLSParser.parseXLS() :: xls 파일 Loading Done. Parsing 시작");
                if (book != null) {
                    int numberOfSheets = book.getNumberOfSheets();
                    System.out.println("XLSParser.parseXLS() :: XLS문서의 총 Sheet 수 = " + numberOfSheets);
                    if (sheetIndex < numberOfSheets) {
                        System.out.println("workSheet loding..");
                        this.xlsWorkSheet = book.getSheet(sheetIndex);
                        System.out.println("workSheet loding success");
                        this.numberOfRows = this.xlsWorkSheet.getRows();
                        this.numberOfColumns = this.xlsWorkSheet.getColumns();
                        System.out.println("XLSParser.parseXLS() :: ParsingXSL Sheet Row/Column --> " + this.numberOfRows + "/" + this.numberOfColumns);
                        this.isParsed = true;
                        System.out.println("XLSParser.parseXLS() :: --------------XLS 파일 Parsing Done---------" );
                    } else {
                        System.out.println("XLSParser.parseXLS() :: 전달된 Workbook에 원하는 Sheetindex에 해당하는 sheet가 없습니다. -->" + sheetIndex);
                        this.xlsWorkSheet = null;
                        this.isParsed = false;
                    }
                } else {
                    System.out.println("XLSParser.parseXLS() :: 전달된 Workbook이 null입니다.");
                    this.xlsWorkSheet = null;
                    this.isParsed = false;
                }
            } catch (IOException ex) {
                System.out.println("XLSParser.parseXLS() :: XLS File Loading중 오류 발생");
                        System.out.println("XLSParser.parseXLS() :: " + ex.toString());
                return false;
            } catch (BiffException ex) {
                System.out.println("XLSParser.parseXLS() :: XLS File Loading중 오류 발생");
                        System.out.println("XLSParser.parseXLS() :: " + ex.toString());
                return false;
            }
        }
        System.gc();
        return this.isParsed;
    }

    public boolean parseXLS(String path, int sheetIndex) {
        boolean result = false;
        System.out.println("XLSParser.parseXLS(String) :: Receive Parsing Request. ");
        if (path != null) {
            this.XLSFilePath = path;
            return parseXLS(sheetIndex);
        }
        System.out.println("XLSParser.parseXLS(String) :: XLS filepath is null or not valid format --> " + path);
        return false;
    }

    public void setMaxColumnSize(int columnSize) {
        if (this.isParsed) {
            if (columnSize >= 0) {
                this.numberOfColumns = columnSize;
            } else {
                System.out.println("XLSParser.setMaxColumnSize() :: 유효하지 않은 Column Size입니다.");
            }
        } else {
            System.out.println("XLSParser.setMaxColumnSize() :: Parsing된 문서에 한해서 column수를 지정할 수 있습니다.");
        }
    }

    public void setMaxRowSize(int rowNum) {
        if (this.isParsed) {
            if (rowNum >= 0) {
                this.numberOfRows = rowNum;
            } else {
                System.out.println("XLSParser.setMaxRowSize() :: 유효하지 않은 Row Size입니다.");
            }
        } else {
            System.out.println("XLSParser.setMaxRowSize() :: Parsing된 문서에 한해서 row수를 지정할 수 있습니다.");
        }
    }

    public void setXLSFilePath(String path) {
        if (path != null) {
            this.XLSFilePath = path;
        } else {
            System.out.println("XLSParser.setXLSFilePath() :: XLS 파일의 경로가 null이거나 잘못된 경로입니다.");
        }
    }
}
