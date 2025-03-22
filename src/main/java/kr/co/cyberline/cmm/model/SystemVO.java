package kr.co.cyberline.cmm.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("systemVO")
public class SystemVO extends UserVO {
	
	/* 사용자권한 이력 관리 */
	private int author_sn;  // 순번
	private String rm;		// 비고
	private String before_author_id; // 이전권한
	
	/* 로그인 이력 관리 */
	private String login_hist_id; // 로그인이력 아이디
	private String login_ip;      // 로그인 아이피
	private String login_mthd;	  // 로그인 방식
	private Date crte_dttm;		  // 등록일시
	
    private String condLoginMthdCd; // 검색 로그인방식 구분 코드
	
	/* 로그 관리 */
	private String sn; 			// 순번
	private String nm;      	// 이름
	private String length;	  	// 크기
	private String modifed;		// 수정일자
	private String path;		// 경로
	private String se;			// 구분
	
	/* 서버 정보 */
	private String cpu_use;
	private String cpu_per;
	private String cpu_idle_per;
	private String hdd_tot;
	private String hdd_use;
	private String hdd_idle;
	private String hdd_use_per;
	private String hdd_idle_per;
	private String mem_tot;
	private String mem_free;
	private String mem_use_per;
	private String mem_idle_per;
	
	
	public SystemVO() {
		super("1", "A.CRTE_DTTM", "DESC");
	}
	
	public int getAuthor_sn() {
		return author_sn;
	}


	public void setAuthor_sn(int author_sn) {
		this.author_sn = author_sn;
	}


	public String getRm() {
		return rm;
	}


	public void setRm(String rm) {
		this.rm = rm;
	}


	public String getBefore_author_id() {
		return before_author_id;
	}


	public void setBefore_author_id(String before_author_id) {
		this.before_author_id = before_author_id;
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
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getModifed() {
		return modifed;
	}
	public void setModifed(String modifed) {
		this.modifed = modifed;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSe() {
		return se;
	}
	public void setSe(String se) {
		this.se = se;
	}
	public String getCpu_use() {
		return cpu_use;
	}
	public void setCpu_use(String cpu_use) {
		this.cpu_use = cpu_use;
	}
	public String getCpu_per() {
		return cpu_per;
	}
	public void setCpu_per(String cpu_per) {
		this.cpu_per = cpu_per;
	}
	public String getCpu_idle_per() {
		return cpu_idle_per;
	}
	public void setCpu_idle_per(String cpu_idle_per) {
		this.cpu_idle_per = cpu_idle_per;
	}
	public String getHdd_tot() {
		return hdd_tot;
	}
	public void setHdd_tot(String hdd_tot) {
		this.hdd_tot = hdd_tot;
	}
	public String getHdd_use() {
		return hdd_use;
	}
	public void setHdd_use(String hdd_use) {
		this.hdd_use = hdd_use;
	}
	public String getHdd_idle() {
		return hdd_idle;
	}
	public void setHdd_idle(String hdd_idle) {
		this.hdd_idle = hdd_idle;
	}
	public String getHdd_use_per() {
		return hdd_use_per;
	}
	public void setHdd_use_per(String hdd_use_per) {
		this.hdd_use_per = hdd_use_per;
	}
	public String getHdd_idle_per() {
		return hdd_idle_per;
	}
	public void setHdd_idle_per(String hdd_idle_per) {
		this.hdd_idle_per = hdd_idle_per;
	}
	public String getMem_tot() {
		return mem_tot;
	}
	public void setMem_tot(String mem_tot) {
		this.mem_tot = mem_tot;
	}
	public String getMem_free() {
		return mem_free;
	}
	public void setMem_free(String mem_free) {
		this.mem_free = mem_free;
	}
	public String getMem_use_per() {
		return mem_use_per;
	}
	public void setMem_use_per(String mem_use_per) {
		this.mem_use_per = mem_use_per;
	}
	public String getMem_idle_per() {
		return mem_idle_per;
	}
	public void setMem_idle_per(String mem_idle_per) {
		this.mem_idle_per = mem_idle_per;
	}
	    
}
