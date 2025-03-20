package kr.co.cyberline.modal;

import kr.co.cyberline.cmm.web.model.CylUserVO;
import org.apache.ibatis.type.Alias;

@Alias("excelDVO")
public class ExcelDVO extends CylUserVO {

    private String PROG_ID;
    private String ORDR;
    private String COLUMN_EN;
    private String COLUMN_KR;
    private String FORMAT_TY;
    private String FORMAT_ETC;
    
	public String getPROG_ID() {
		return PROG_ID;
	}
	public void setPROG_ID(String pROG_ID) {
		PROG_ID = pROG_ID;
	}
	public String getORDR() {
		return ORDR;
	}
	public void setORDR(String oRDR) {
		ORDR = oRDR;
	}
	public String getCOLUMN_EN() {
		return COLUMN_EN;
	}
	public void setCOLUMN_EN(String cOLUMN_EN) {
		COLUMN_EN = cOLUMN_EN;
	}
	public String getCOLUMN_KR() {
		return COLUMN_KR;
	}
	public void setCOLUMN_KR(String cOLUMN_KR) {
		COLUMN_KR = cOLUMN_KR;
	}
	public String getFORMAT_TY() {
		return FORMAT_TY;
	}
	public void setFORMAT_TY(String fORMAT_TY) {
		FORMAT_TY = fORMAT_TY;
	}
	public String getFORMAT_ETC() {
		return FORMAT_ETC;
	}
	public void setFORMAT_ETC(String fORMAT_ETC) {
		FORMAT_ETC = fORMAT_ETC;
	}
}
