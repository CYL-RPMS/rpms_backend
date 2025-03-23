
package kr.co.cyberline.cmm.util.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * kr.co.cyberline.common.web.view
 * JxlsParam.java
 * </pre>
 * 
 * @Author : ktj
 * @Date : 2014. 12. 17.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2014. 12. 17., 수정자 : ktj, 수정내용 : 최초등록
 * </pre>
 */
public class JxlsParam {
	public final static String EXCEL_EXT_XLS = "xls";
	public final static String EXCEL_EXT_XLSX = "xlsx";

	private String outputFileNm;
	private String templateFileNm;
	private String ext;
	private int totalCnt;
	private List sheetNames;
	private String title;
	private String listNm;
	private boolean multiSe;

	public JxlsParam() {
		this(EXCEL_EXT_XLS, "", "", "제목이 설정되지 않았습니다.", new ArrayList(), "list", false, 0);
	}

	public JxlsParam(String ext, String templateFileNm, String outputFileNm, String title) {
		this(ext, templateFileNm, outputFileNm, title, new ArrayList(), "list", false, 0);
	}

	public JxlsParam(String ext, String templateFileNm, String outputFileNm, String title, int totalCnt) {
		this(ext, templateFileNm, outputFileNm, title, new ArrayList(), "list", false, totalCnt);
	}

	public JxlsParam(String ext, String templateFileNm, String outputFileNm, String title, List sheetNames, String listNm, boolean multiSe) {
		this(ext, templateFileNm, outputFileNm, title, sheetNames, listNm, multiSe, 0);
	}

	public JxlsParam(String ext, String templateFileNm, String outputFileNm, String title, List sheetNames, String listNm, boolean multiSe,
                     int totalCnt) {
		this.ext = ext;
		this.templateFileNm = templateFileNm;
		this.outputFileNm = outputFileNm;
		this.title = title;
		this.sheetNames = sheetNames;
		this.listNm = listNm;
		this.multiSe = multiSe;
		this.totalCnt = totalCnt;
	}

	public String getOutputFileNm() {
		return outputFileNm;
	}

	public void setOutputFileNm(String outputFileNm) {
		this.outputFileNm = outputFileNm;
	}

	public String getTemplateFileNm() {
		return templateFileNm;
	}

	public void setTemplateFileNm(String templateFileNm) {
		this.templateFileNm = templateFileNm;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public List getSheetNames() {
		return sheetNames;
	}

	public void setSheetNames(List sheetNames) {
		this.sheetNames = sheetNames;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getListNm() {
		return listNm;
	}

	public void setListNm(String listNm) {
		this.listNm = listNm;
	}

	public boolean isMultiSe() {
		return multiSe;
	}

	public void setMultiSe(boolean multiSe) {
		this.multiSe = multiSe;
	}
}