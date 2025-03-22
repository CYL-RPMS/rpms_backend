package kr.co.cyberline.cmm.web.code;

import java.io.Serializable;
import java.util.Date;
import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;
import org.apache.ibatis.type.Alias;

@Alias("cylCmmCodeDetailVO")
public class CylCmmCodeDetailVO extends CylWebDefaultVO implements Serializable {
    private String code_id;
    private String code;
    private String code_nm;
    private String code_dc;
    private String use_at;
    private String crte_user_id;
    private Date crte_dttm;
    private String updt_user_id;
    private Date updt_dttm;
    private String ordr;

    public CylCmmCodeDetailVO() {
    }

    public String getCode_id() {
        return this.code_id;
    }

    public void setCode_id(String code_id) {
        this.code_id = code_id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode_nm() {
        return this.code_nm;
    }

    public void setCode_nm(String code_nm) {
        this.code_nm = code_nm;
    }

    public String getCode_dc() {
        return this.code_dc;
    }

    public void setCode_dc(String code_dc) {
        this.code_dc = code_dc;
    }

    public String getUse_at() {
        return this.use_at;
    }

    public void setUse_at(String use_at) {
        this.use_at = use_at;
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

    public String getOrdr() {
        return this.ordr;
    }

    public void setOrdr(String ordr) {
        this.ordr = ordr;
    }
}
