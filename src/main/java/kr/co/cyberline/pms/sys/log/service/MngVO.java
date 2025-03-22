package kr.co.cyberline.pms.sys.log.service;

import kr.co.cyberline.cmm.model.UserVO;
import org.apache.ibatis.type.Alias;

@Alias("mngVO")
public class MngVO extends UserVO {

	private String sn; 			// 순번
	private String nm;      	// 이름
	private String length;	  	// 크기
	private String modifed;		// 수정일자
	private String path;		// 경로

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
}
