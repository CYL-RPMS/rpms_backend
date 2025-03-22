package kr.co.cyberline.cmm.model;

import kr.co.cyberline.cmm.web.model.CylUserVO;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("userVO")
public class UserVO extends CylUserVO {

    private static final long serialVersionUID = 5568851809563515412L;

    private String searchType;              // 검색 구분
    private String searchKeyword;           // 검색 키워드
    private String condUserSttusCd;
    private String condAuthorNm;
    private String condAuthorId;
    private String condDeptCd;
    private String[] authorIds;            // 사용자 다중 권한
    private String psitn_nm;
    private String offic;
    private String team;
    private String lvl;
    
    private String user_en_nm;
    private String user_ch_nm;
    private String brthdy;
    private String sexdstn;
    private String rsrchr_no;
    private String moblphon_f;
    private String moblphon_m;
    private String moblphon_l;
    private String email_f;
    private String email_l;
    private String ntis_othbc_at;
    private String ntis_indvdlinfo_prcuse_at;
    private String nlty_nm;
    private String nlty_cd;
    private String mdlp_field;
    private String ci_cd;
    
    private String instt_no;
    private String instt_nm;
    private String dept_nm;
    private String clsf;
    private String ofcps;
    private String emplmn_de;
    private String hffc_at;
    private String instt_confm_sttus;
    
    
    public List<AuthorManageVO> authorList;
    
    public UserVO(){
    
    }

    public UserVO(String pagingEnable, String condOrder, String condAlign){
        super(pagingEnable, condOrder, condAlign);
    }

    public UserVO(String user_id, String passwd){
        super(user_id, passwd);
    }

    
	public String getSearchType() {
		return searchType;
	}
	public List<AuthorManageVO> getAuthorList() {
		return authorList;
	}
	
	public void setAuthorList(List<AuthorManageVO> authorList) {
		this.authorList = authorList;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getCondUserSttusCd() {
		return condUserSttusCd;
	}
	public void setCondUserSttusCd(String condUserSttusCd) {
		this.condUserSttusCd = condUserSttusCd;
	}
	public String getCondAuthorNm() {
		return condAuthorNm;
	}
	public void setCondAuthorNm(String condAuthorNm) {
		this.condAuthorNm = condAuthorNm;
	}
	public String getCondAuthorId() {
		return condAuthorId;
	}
	public void setCondAuthorId(String condAuthorId) {
		this.condAuthorId = condAuthorId;
	}
	public String getCondDeptCd() {
		return condDeptCd;
	}
	public void setCondDeptCd(String condDeptCd) {
		this.condDeptCd = condDeptCd;
	}
	public String[] getAuthorIds() {
		return authorIds;
	}
	public void setAuthorIds(String[] authorIds) {
		this.authorIds = authorIds;
	}
	public String getPsitn_nm() {
		return psitn_nm;
	}
	public void setPsitn_nm(String psitn_nm) {
		this.psitn_nm = psitn_nm;
	}
	public String getOffic() {
		return offic;
	}
	public void setOffic(String offic) {
		this.offic = offic;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getLvl() {
		return lvl;
	}
	public void setLvl(String lvl) {
		this.lvl = lvl;
	}
	public String getUser_en_nm() {
		return user_en_nm;
	}
	public void setUser_en_nm(String user_en_nm) {
		this.user_en_nm = user_en_nm;
	}
	public String getUser_ch_nm() {
		return user_ch_nm;
	}
	public void setUser_ch_nm(String user_ch_nm) {
		this.user_ch_nm = user_ch_nm;
	}
	public String getBrthdy() {
		return brthdy;
	}
	public void setBrthdy(String brthdy) {
		this.brthdy = brthdy;
	}
	public String getSexdstn() {
		return sexdstn;
	}
	public void setSexdstn(String sexdstn) {
		this.sexdstn = sexdstn;
	}
	public String getRsrchr_no() {
		return rsrchr_no;
	}
	public void setRsrchr_no(String rsrchr_no) {
		this.rsrchr_no = rsrchr_no;
	}
	public String getMoblphon_f() {
		return moblphon_f;
	}
	public void setMoblphon_f(String moblphon_f) {
		this.moblphon_f = moblphon_f;
	}
	public String getMoblphon_m() {
		return moblphon_m;
	}
	public void setMoblphon_m(String moblphon_m) {
		this.moblphon_m = moblphon_m;
	}
	public String getMoblphon_l() {
		return moblphon_l;
	}
	public void setMoblphon_l(String moblphon_l) {
		this.moblphon_l = moblphon_l;
	}
	public String getEmail_f() {
		return email_f;
	}
	public void setEmail_f(String email_f) {
		this.email_f = email_f;
	}
	public String getEmail_l() {
		return email_l;
	}
	public void setEmail_l(String email_l) {
		this.email_l = email_l;
	}
	public String getNtis_othbc_at() {
		return ntis_othbc_at;
	}
	public void setNtis_othbc_at(String ntis_othbc_at) {
		this.ntis_othbc_at = ntis_othbc_at;
	}
	public String getNtis_indvdlinfo_prcuse_at() {
		return ntis_indvdlinfo_prcuse_at;
	}
	public void setNtis_indvdlinfo_prcuse_at(String ntis_indvdlinfo_prcuse_at) {
		this.ntis_indvdlinfo_prcuse_at = ntis_indvdlinfo_prcuse_at;
	}
	public String getNlty_nm() {
		return nlty_nm;
	}
	public void setNlty_nm(String nlty_nm) {
		this.nlty_nm = nlty_nm;
	}
	public String getNlty_cd() {
		return nlty_cd;
	}
	public void setNlty_cd(String nlty_cd) {
		this.nlty_cd = nlty_cd;
	}
	public String getMdlp_field() {
		return mdlp_field;
	}
	public void setMdlp_field(String mdlp_field) {
		this.mdlp_field = mdlp_field;
	}
	public String getCi_cd() {
		return ci_cd;
	}
	public void setCi_cd(String ci_cd) {
		this.ci_cd = ci_cd;
	}
	public String getInstt_no() {
		return instt_no;
	}
	public void setInstt_no(String instt_no) {
		this.instt_no = instt_no;
	}
	public String getInstt_nm() {
		return instt_nm;
	}
	public void setInstt_nm(String instt_nm) {
		this.instt_nm = instt_nm;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getClsf() {
		return clsf;
	}
	public void setClsf(String clsf) {
		this.clsf = clsf;
	}
	public String getOfcps() {
		return ofcps;
	}
	public void setOfcps(String ofcps) {
		this.ofcps = ofcps;
	}
	public String getEmplmn_de() {
		return emplmn_de;
	}
	public void setEmplmn_de(String emplmn_de) {
		this.emplmn_de = emplmn_de;
	}
	public String getHffc_at() {
		return hffc_at;
	}
	public void setHffc_at(String hffc_at) {
		this.hffc_at = hffc_at;
	}
	public String getInstt_confm_sttus() {
		return instt_confm_sttus;
	}
	public void setInstt_confm_sttus(String instt_confm_sttus) {
		this.instt_confm_sttus = instt_confm_sttus;
	}
}
