package kr.co.cyberline.modal;

import kr.co.cyberline.cmm.web.model.CylUserVO;
import org.apache.ibatis.type.Alias;

@Alias("excelMVO")
public class ExcelMVO extends CylUserVO {

	private String PROG_ID;
	private String PROG_TITLE_NM;
	private String PROG_SHEET_NM;
	private String NAMESPACE;
	private String QUERY_ID;
	
	public String getPROG_ID() {
		return PROG_ID;
	}
	public void setPROG_ID(String pROG_ID) {
		PROG_ID = pROG_ID;
	}
	public String getPROG_TITLE_NM() {
		return PROG_TITLE_NM;
	}
	public void setPROG_TITLE_NM(String pROG_TITLE_NM) {
		PROG_TITLE_NM = pROG_TITLE_NM;
	}
	public String getPROG_SHEET_NM() {
		return PROG_SHEET_NM;
	}
	public void setPROG_SHEET_NM(String pROG_SHEET_NM) {
		PROG_SHEET_NM = pROG_SHEET_NM;
	}
	public String getNAMESPACE() {
		return NAMESPACE;
	}
	public void setNAMESPACE(String nAMESPACE) {
		NAMESPACE = nAMESPACE;
	}
	public String getQUERY_ID() {
		return QUERY_ID;
	}
	public void setQUERY_ID(String qUERY_ID) {
		QUERY_ID = qUERY_ID;
	}
}
