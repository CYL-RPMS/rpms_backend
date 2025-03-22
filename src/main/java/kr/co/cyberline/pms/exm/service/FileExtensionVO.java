package kr.co.cyberline.pms.exm.service;

import kr.co.cyberline.cmm.web.file.CylFileVO;

import java.util.List;

public class FileExtensionVO extends CylFileVO {

	private static final long serialVersionUID = -1787877979854737409L;

	private List<CylFileVO> atchFile1;
	private List<CylFileVO> atchFile2;
	private List<CylFileVO> atchFile3;
	private List<CylFileVO> atchFile4;
	
	public List<CylFileVO> getAtchFile1() {
		return atchFile1;
	}
	public void setAtchFile1(List<CylFileVO> atchFile1) {
		this.atchFile1 = atchFile1;
	}
	public List<CylFileVO> getAtchFile2() {
		return atchFile2;
	}
	public void setAtchFile2(List<CylFileVO> atchFile2) {
		this.atchFile2 = atchFile2;
	}
	public List<CylFileVO> getAtchFile3() {
		return atchFile3;
	}
	public void setAtchFile3(List<CylFileVO> atchFile3) {
		this.atchFile3 = atchFile3;
	}
	public List<CylFileVO> getAtchFile4() {
		return atchFile4;
	}
	public void setAtchFile4(List<CylFileVO> atchFile4) {
		this.atchFile4 = atchFile4;
	}
}
