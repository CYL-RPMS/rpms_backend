package kr.co.cyberline.pms.sys.log.service;

import kr.co.cyberline.cmm.model.UserVO;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Alias("authorHistVO")
public class AuthorHistVO extends UserVO {

	private int sn;			// 순번
	private String rm;		// 비고
	private Timestamp crte_dttm; // 등록일시
	private String before_author_id; // 이전권한

	public AuthorHistVO(){
		super("1", "A.CRTE_DTTM", "DESC");
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
	}

	public Timestamp getCrte_dttm() {
		return crte_dttm;
	}

	public void setCrte_dttm(Timestamp crte_dttm) {
		this.crte_dttm = crte_dttm;
	}

	public String getBefore_author_id() {
		return before_author_id;
	}
	
	public void setBefore_author_id(String before_author_id) {
		this.before_author_id = before_author_id;
	}
	
}
