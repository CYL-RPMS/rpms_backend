package kr.co.cyberline.cmm.web.code;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;
import org.apache.ibatis.type.Alias;

@Alias("cylCmmCodeVO")
public class CylCmmCodeVO extends CylWebDefaultVO implements Serializable {
    private String code_id;
    private String upr_code_id;
    private String code_id_nm;
    private String cd_id_dc;
    private String use_at;
    private String cl_code;
    private String crte_user_id;
    private Date crte_dttm;
    private String updt_user_id;
    private Date updt_dttm;
    private int lvl;
    private String operation;
    private String searchType;
    private String searchKeyword;
    private String condUseAt;
    private List<CylCmmCodeDetailVO> codeDetailList;
    private Map codeDetailMap;

    public CylCmmCodeVO() {
        super("1", "A.CRTE_DTTM", "DESC");
    }

    public String getCode_id() {
        return this.code_id;
    }

    public void setCode_id(String code_id) {
        this.code_id = code_id;
    }

    public String getUpr_code_id() {
        return this.upr_code_id;
    }

    public void setUpr_code_id(String upr_code_id) {
        this.upr_code_id = upr_code_id;
    }

    public String getCode_id_nm() {
        return this.code_id_nm;
    }

    public void setCode_id_nm(String code_id_nm) {
        this.code_id_nm = code_id_nm;
    }

    public String getCd_id_dc() {
        return this.cd_id_dc;
    }

    public void setCd_id_dc(String cd_id_dc) {
        this.cd_id_dc = cd_id_dc;
    }

    public String getUse_at() {
        return this.use_at;
    }

    public void setUse_at(String use_at) {
        this.use_at = use_at;
    }

    public String getCl_code() {
        return this.cl_code;
    }

    public void setCl_code(String cl_code) {
        this.cl_code = cl_code;
    }

    public String getCrte_user_id() {
        return this.crte_user_id;
    }

    public void setCrte_user_id(String crte_user_id) {
        this.crte_user_id = crte_user_id;
    }

    public Date getCrte_dttm() {
        return this.crte_dttm;
    }

    public void setCrte_dttm(Date crte_dttm) {
        this.crte_dttm = crte_dttm;
    }

    public String getUpdt_user_id() {
        return this.updt_user_id;
    }

    public void setUpdt_user_id(String updt_user_id) {
        this.updt_user_id = updt_user_id;
    }

    public Date getUpdt_dttm() {
        return this.updt_dttm;
    }

    public void setUpdt_dttm(Date updt_dttm) {
        this.updt_dttm = updt_dttm;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<CylCmmCodeDetailVO> getCodeDetailList() {
        return this.codeDetailList;
    }

    public void setCodeDetailList(List<CylCmmCodeDetailVO> codeDetailList) {
        this.codeDetailList = codeDetailList;
    }

    public int getLvl() {
        return this.lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String getSearchType() {
        return this.searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchKeyword() {
        return this.searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getCondUseAt() {
        return this.condUseAt;
    }

    public void setCondUseAt(String condUseAt) {
        this.condUseAt = condUseAt;
    }

    public Map getCodeDetailMap() {
        return this.codeDetailMap;
    }

    public void setCodeDetailMap(Map codeDetailMap) {
        this.codeDetailMap = codeDetailMap;
    }
}
