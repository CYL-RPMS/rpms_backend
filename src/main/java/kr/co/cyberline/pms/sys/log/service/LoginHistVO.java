package kr.co.cyberline.pms.sys.log.service;

import kr.co.cyberline.cmm.model.UserVO;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("loginHistVO")
public class LoginHistVO extends UserVO {
	
	private String login_hist_id; // 로그인이력 아이디
	private String login_ip;      // 로그인 아이피
	private String login_mthd;	  // 로그인 방식
	private String login_device;  // 접속 디바이스
	private Date crte_dttm;		  // 등록일시
	
    private String condLoginMthdCd; // 검색 로그인방식 구분 코드

	/*[통신분쟁]2021-08-23 / Kait-62 / by ihpark / 개인정보 관련 이력*/
	private String condsubject;
	private String condlogin_id;
	private String condip;
	private String condauthor;
	private String condaction_st_dt;
	private String condaction_end_dt;
	private String cef_login_id;
	private String cef_user_id;
	private String cef_author_id;
	private String cef_ip;
	private Date cef_action_date;
	private String cef_p_subject;
	private String cef_p_action;

	public String getCondsubject() {
		return condsubject;
	}

	public void setCondsubject(String condsubject) {
		this.condsubject = condsubject;
	}

	public String getCondlogin_id() {
		return condlogin_id;
	}

	public void setCondlogin_id(String condlogin_id) {
		this.condlogin_id = condlogin_id;
	}

	public String getCondip() {
		return condip;
	}

	public void setCondip(String condip) {
		this.condip = condip;
	}

	public String getCondauthor() {
		return condauthor;
	}

	public void setCondauthor(String condauthor) {
		this.condauthor = condauthor;
	}

	public String getCondaction_st_dt() {
		return condaction_st_dt;
	}

	public void setCondaction_st_dt(String condaction_st_dt) {
		this.condaction_st_dt = condaction_st_dt;
	}

	public String getCondaction_end_dt() {
		return condaction_end_dt;
	}

	public void setCondaction_end_dt(String condaction_end_dt) {
		this.condaction_end_dt = condaction_end_dt;
	}

	public String getCef_login_id() {
		return cef_login_id;
	}

	public void setCef_login_id(String cef_login_id) {
		this.cef_login_id = cef_login_id;
	}

	public String getCef_user_id() {
		return cef_user_id;
	}

	public void setCef_user_id(String cef_user_id) {
		this.cef_user_id = cef_user_id;
	}

	public String getCef_author_id() {
		return cef_author_id;
	}

	public void setCef_author_id(String cef_author_id) {
		this.cef_author_id = cef_author_id;
	}

	public String getCef_ip() {
		return cef_ip;
	}

	public void setCef_ip(String cef_ip) {
		this.cef_ip = cef_ip;
	}

	public Date getCef_action_date() {
		return cef_action_date;
	}

	public void setCef_action_date(Date cef_action_date) {
		this.cef_action_date = cef_action_date;
	}

	public String getCef_p_subject() {
		return cef_p_subject;
	}

	public void setCef_p_subject(String cef_p_subject) {
		this.cef_p_subject = cef_p_subject;
	}

	public String getCef_p_action() {
		return cef_p_action;
	}

	public void setCef_p_action(String cef_p_action) {
		this.cef_p_action = cef_p_action;
	}

	public LoginHistVO(){
		super("1", "A.CRTE_DTTM", "DESC");
	}

	public String getLogin_hist_id() {
		return login_hist_id;
	}
	
	public void setLogin_hist_id(String login_hist_id) {
		this.login_hist_id = login_hist_id;
	}
	
	public String getLogin_ip() {
		return login_ip;
	}
	
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	
	public String getLogin_mthd() {
		return login_mthd;
	}
	
	public void setLogin_mthd(String login_mthd) {
		this.login_mthd = login_mthd;
	}

	public String getLogin_device() { return login_device; }

	public void setLogin_device(String login_device) { this.login_device = login_device; }

	public Date getCrte_dttm() {
		return crte_dttm;
	}
	
	public void setCrte_dttm(Date crte_dttm) {
		this.crte_dttm = crte_dttm;
	}

	public String getCondLoginMthdCd() {
		return condLoginMthdCd;
	}

	public void setCondLoginMthdCd(String condLoginMthdCd) {
		this.condLoginMthdCd = condLoginMthdCd;
	}
	
}
