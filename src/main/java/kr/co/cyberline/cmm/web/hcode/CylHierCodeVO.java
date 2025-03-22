package kr.co.cyberline.cmm.web.hcode;

import java.util.Date;
import java.util.List;
import kr.co.cyberline.cmm.web.annotation.CylKeepCondition;
import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;
import org.apache.ibatis.type.Alias;

@Alias("cylHierCodeVO")
public class CylHierCodeVO extends CylWebDefaultVO {
    private String grp_id;
    private String grp_id_nm;
    private String grp_dc;
    private String grp_use_at;
    private String grp_sn;
    private String code;
    private String code_nm;
    private String code_dc;
    private String code_use_at;
    private String code_sn;
    private String upr_code;
    private String lcls_cd;
    private String mcls_cd;
    private String scls_cd;
    private String crte_user_id;
    private String crte_user_nm;
    private Date crte_dttm;
    private String updt_user_id;
    private String updt_user_nm;
    private Date updt_dttm;
    private String[] hierCodeArr;
    private String project_se;
    private String operation;
    private List list;
    @CylKeepCondition
    private String grpCdFlag;
    @CylKeepCondition
    private String condGroupId;
    @CylKeepCondition
    private String condGroupNm;
    @CylKeepCondition
    private String condGroupUseAt;
    @CylKeepCondition
    private String condCode;
    @CylKeepCondition
    private String condCodeNm;
    @CylKeepCondition
    private String condCodeUseAt;

    public CylHierCodeVO() {
        super("1", "A.CODE_SN", "ASC");
    }

    public String getGrp_id() {
        return this.grp_id;
    }

    public void setGrp_id(String grp_id) {
        this.grp_id = grp_id;
    }

    public String getGrp_id_nm() {
        return this.grp_id_nm;
    }

    public void setGrp_id_nm(String grp_id_nm) {
        this.grp_id_nm = grp_id_nm;
    }

    public String getGrp_dc() {
        return this.grp_dc;
    }

    public void setGrp_dc(String grp_dc) {
        this.grp_dc = grp_dc;
    }

    public String getGrp_use_at() {
        return this.grp_use_at;
    }

    public void setGrp_use_at(String grp_use_at) {
        this.grp_use_at = grp_use_at;
    }

    public String getGrp_sn() {
        return this.grp_sn;
    }

    public void setGrp_sn(String grp_sn) {
        this.grp_sn = grp_sn;
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

    public String getCode_use_at() {
        return this.code_use_at;
    }

    public void setCode_use_at(String code_use_at) {
        this.code_use_at = code_use_at;
    }

    public String getCode_sn() {
        return this.code_sn;
    }

    public void setCode_sn(String code_sn) {
        this.code_sn = code_sn;
    }

    public String getUpr_code() {
        return this.upr_code;
    }

    public void setUpr_code(String upr_code) {
        this.upr_code = upr_code;
    }

    public String getLcls_cd() {
        return this.lcls_cd;
    }

    public void setLcls_cd(String lcls_cd) {
        this.lcls_cd = lcls_cd;
    }

    public String getMcls_cd() {
        return this.mcls_cd;
    }

    public void setMcls_cd(String mcls_cd) {
        this.mcls_cd = mcls_cd;
    }

    public String getScls_cd() {
        return this.scls_cd;
    }

    public void setScls_cd(String scls_cd) {
        this.scls_cd = scls_cd;
    }

    public String getCrte_user_id() {
        return this.crte_user_id;
    }

    public void setCrte_user_id(String crte_user_id) {
        this.crte_user_id = crte_user_id;
    }

    public String getCrte_user_nm() {
        return this.crte_user_nm;
    }

    public void setCrte_user_nm(String crte_user_nm) {
        this.crte_user_nm = crte_user_nm;
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

    public String getUpdt_user_nm() {
        return this.updt_user_nm;
    }

    public void setUpdt_user_nm(String updt_user_nm) {
        this.updt_user_nm = updt_user_nm;
    }

    public Date getUpdt_dttm() {
        return this.updt_dttm;
    }

    public void setUpdt_dttm(Date updt_dttm) {
        this.updt_dttm = updt_dttm;
    }

    public String[] getHierCodeArr() {
        return this.hierCodeArr;
    }

    public void setHierCodeArr(String[] hierCodeArr) {
        this.hierCodeArr = hierCodeArr;
    }

    public String getGrpCdFlag() {
        return this.grpCdFlag;
    }

    public void setGrpCdFlag(String grpCdFlag) {
        this.grpCdFlag = grpCdFlag;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getCondGroupId() {
        return this.condGroupId;
    }

    public void setCondGroupId(String condGroupId) {
        this.condGroupId = condGroupId;
    }

    public String getCondGroupNm() {
        return this.condGroupNm;
    }

    public void setCondGroupNm(String condGroupNm) {
        this.condGroupNm = condGroupNm;
    }

    public String getCondGroupUseAt() {
        return this.condGroupUseAt;
    }

    public void setCondGroupUseAt(String condGroupUseAt) {
        this.condGroupUseAt = condGroupUseAt;
    }

    public String getCondCode() {
        return this.condCode;
    }

    public void setCondCode(String condCode) {
        this.condCode = condCode;
    }

    public String getCondCodeNm() {
        return this.condCodeNm;
    }

    public void setCondCodeNm(String condCodeNm) {
        this.condCodeNm = condCodeNm;
    }

    public String getCondCodeUseAt() {
        return this.condCodeUseAt;
    }

    public void setCondCodeUseAt(String condCodeUseAt) {
        this.condCodeUseAt = condCodeUseAt;
    }

    public void setHierCodeArr(String hierCodeArr) {
        this.hierCodeArr = hierCodeArr.split(",");
    }

    public String getProject_se() {
        return this.project_se;
    }

    public void setProject_se(String project_se) {
        this.project_se = project_se;
    }
}
