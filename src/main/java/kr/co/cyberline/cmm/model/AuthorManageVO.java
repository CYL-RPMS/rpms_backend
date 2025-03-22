package kr.co.cyberline.cmm.model;

import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

/**
 * <p>권한 관리 Value Object</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Alias("authorManageVO")
public class AuthorManageVO extends CylWebDefaultVO {
	
	private static final long serialVersionUID = -7925826898495353165L;
	
	private String author_id;               // 권한코드
    private String author_nm_kr;            // 권한국문이름
    private String author_nm_en;            // 권한영문이름
    private String author_dc;               // 권한 설명
    private int author_priort;              // 권한 우선순위
    private String crte_user_id;            // 최초등록자ID
    private Date crte_dttm;                 // 최초등록일시
    private String updt_user_id;            // 최종수정자ID
    private Date updt_dttm;                 // 최종수정일시
    private String use_at;                  // 사용여부

    private List<String> authorIdList;      // 다중 권한 목록
    
    private String searchType;				// 검색 구분
    private String searchKeyword;			// 검색 키워드
    
    
    public AuthorManageVO(){
        super(PAGING_ENABLE_ON, "A.CRTE_DTTM", "DESC");
    }
    
	public String getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_nm_kr() {
		return author_nm_kr;
	}
	public void setAuthor_nm_kr(String author_nm_kr) {
		this.author_nm_kr = author_nm_kr;
	}
	public String getAuthor_nm_en() {
		return author_nm_en;
	}
	public void setAuthor_nm_en(String author_nm_en) {
		this.author_nm_en = author_nm_en;
	}
	public String getAuthor_dc() {
		return author_dc;
	}
	public void setAuthor_dc(String author_dc) {
		this.author_dc = author_dc;
	}
	public int getAuthor_priort() {
		return author_priort;
	}
	public void setAuthor_priort(int author_priort) {
		this.author_priort = author_priort;
	}
	public String getCrte_user_id() {
		return crte_user_id;
	}
	public void setCrte_user_id(String crte_user_id) {
		this.crte_user_id = crte_user_id;
	}
	public Date getCrte_dttm() {
		return crte_dttm;
	}
	public void setCrte_dttm(Date crte_dttm) {
		this.crte_dttm = crte_dttm;
	}
	public String getUpdt_user_id() {
		return updt_user_id;
	}
	public void setUpdt_user_id(String updt_user_id) {
		this.updt_user_id = updt_user_id;
	}
	public Date getUpdt_dttm() {
		return updt_dttm;
	}
	public void setUpdt_dttm(Date updt_dttm) {
		this.updt_dttm = updt_dttm;
	}
	public String getUse_at() {
		return use_at;
	}
	public void setUse_at(String use_at) {
		this.use_at = use_at;
	}
	public List<String> getAuthorIdList() {
		return authorIdList;
	}
	public void setAuthorIdList(List<String> authorIdList) {
		this.authorIdList = authorIdList;
	}
	public String getSearchType() {
		return searchType;
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
}
