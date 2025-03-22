package kr.co.cyberline.cmm.model;

import org.apache.ibatis.type.Alias;

@Alias("msDeptVO")
public class MsDeptVO {

	private String CompanySeq;
	private String DeptName;
	private String DeptSeq;
	private String UppDeptSeq;
	private String UppDeptName;
	private String Remark;
	private String Seq;
	private String DispSeq;
	private String UseYn;
	private String LastDateTime;
	private String DeptLevel;
	private String DocDeptSeq;
	
	public String getCompanySeq() {
		return CompanySeq;
	}
	public void setCompanySeq(String companySeq) {
		CompanySeq = companySeq;
	}
	public String getDeptName() {
		return DeptName;
	}
	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
	public String getDeptSeq() {
		return DeptSeq;
	}
	public void setDeptSeq(String deptSeq) {
		DeptSeq = deptSeq;
	}
	public String getUppDeptSeq() {
		return UppDeptSeq;
	}
	public void setUppDeptSeq(String uppDeptSeq) {
		UppDeptSeq = uppDeptSeq;
	}
	public String getUppDeptName() {
		return UppDeptName;
	}
	public void setUppDeptName(String uppDeptName) {
		UppDeptName = uppDeptName;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getSeq() {
		return Seq;
	}
	public void setSeq(String seq) {
		Seq = seq;
	}
	public String getDispSeq() {
		return DispSeq;
	}
	public void setDispSeq(String dispSeq) {
		DispSeq = dispSeq;
	}
	public String getUseYn() {
		return UseYn;
	}
	public void setUseYn(String useYn) {
		UseYn = useYn;
	}
	public String getLastDateTime() {
		return LastDateTime;
	}
	public void setLastDateTime(String lastDateTime) {
		LastDateTime = lastDateTime;
	}
	public String getDeptLevel() {
		return DeptLevel;
	}
	public void setDeptLevel(String deptLevel) {
		DeptLevel = deptLevel;
	}
	public String getDocDeptSeq() {
		return DocDeptSeq;
	}
	public void setDocDeptSeq(String docDeptSeq) {
		DocDeptSeq = docDeptSeq;
	}
}
