package kr.co.cyberline.cmm.model;

import kr.co.cyberline.cmm.web.model.CylUserVO;
import org.apache.ibatis.type.Alias;

@Alias("cmmVO")
public class CmmVO extends CylUserVO {

    private String view_nm;
    private String searchType;
    private String searchKeyword;
    private String return_function;
    private String multi_selector_se;
    private String seq;
    private String callBackFunction;

    public CmmVO(){
        super(PAGING_ENABLE_ON, "", "");
    }

    public String getView_nm() {
        return view_nm;
    }

    public void setView_nm(String view_nm) {
        this.view_nm = view_nm;
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

	public String getReturn_function() {
		return return_function;
	}

	public void setReturn_function(String return_function) {
		this.return_function = return_function;
	}

	public String getMulti_selector_se() {
		return multi_selector_se;
	}

	public void setMulti_selector_se(String multi_selector_se) {
		this.multi_selector_se = multi_selector_se;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

    public String getCallBackFunction() {
        return callBackFunction;
    }

    public void setCallBackFunction(String callBackFunction) {
        this.callBackFunction = callBackFunction;
    }
}
