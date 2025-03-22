package kr.co.cyberline.cmm.web.model;

import java.util.Date;
import kr.co.cyberline.cmm.util.lang.CylArrayUtil;
import org.apache.ibatis.type.Alias;

@Alias("programManageVO")
public class ProgramManageVO extends CylWebDefaultVO {
    private static final long serialVersionUID = -7536964616290193535L;

    private String prgm_id;

    private String prgm_nm_kr;

    private String prgm_nm_en;

    private String prgm_cd;

    private String prgm_cd_nm;

    private String prgm_dc;

    private String prgm_url;

    private String prgm_url_cd;

    private String prgm_url_cd_nm;

    private String crte_user_id;

    private Date crte_dttm;

    private String updt_user_id;

    private Date updt_dttm;

    private String menu_id;

    private String menu_se;

    private String author_id;

    private String fnc_grp_id;

    private String fnc_grp_nm_kr;

    private String fnc_grp_nm_en;

    public String[] prgmIdArray;

    private String searchType;

    private String searchKeyword;

    private String condPrgmCd;

    private String condPrgmUrlCd;

    private String author_slct1;

    private String author_slct2;

    private String author_slct3;

    private String author_slct4;

    private String author_slct5;

    public ProgramManageVO() {
        super("1", "A.PRGM_CD", "ASC");
    }

    public String getPrgm_id() {
        return this.prgm_id;
    }

    public void setPrgm_id(String prgm_id) {
        this.prgm_id = prgm_id;
    }

    public String getPrgm_nm_kr() {
        return this.prgm_nm_kr;
    }

    public void setPrgm_nm_kr(String prgm_nm_kr) {
        this.prgm_nm_kr = prgm_nm_kr;
    }

    public String getPrgm_nm_en() {
        return this.prgm_nm_en;
    }

    public void setPrgm_nm_en(String prgm_nm_en) {
        this.prgm_nm_en = prgm_nm_en;
    }

    public String getPrgm_cd() {
        return this.prgm_cd;
    }

    public void setPrgm_cd(String prgm_cd) {
        this.prgm_cd = prgm_cd;
    }

    public String getPrgm_cd_nm() {
        return this.prgm_cd_nm;
    }

    public void setPrgm_cd_nm(String prgm_cd_nm) {
        this.prgm_cd_nm = prgm_cd_nm;
    }

    public String getPrgm_dc() {
        return this.prgm_dc;
    }

    public void setPrgm_dc(String prgm_dc) {
        this.prgm_dc = prgm_dc;
    }

    public String getPrgm_url() {
        return this.prgm_url;
    }

    public void setPrgm_url(String prgm_url) {
        this.prgm_url = prgm_url;
    }

    public String getPrgm_url_cd() {
        return this.prgm_url_cd;
    }

    public void setPrgm_url_cd(String prgm_url_cd) {
        this.prgm_url_cd = prgm_url_cd;
    }

    public String getPrgm_url_cd_nm() {
        return this.prgm_url_cd_nm;
    }

    public void setPrgm_url_cd_nm(String prgm_url_cd_nm) {
        this.prgm_url_cd_nm = prgm_url_cd_nm;
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

    public String getMenu_id() {
        return this.menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getAuthor_id() {
        return this.author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getFnc_grp_id() {
        return this.fnc_grp_id;
    }

    public void setFnc_grp_id(String fnc_grp_id) {
        this.fnc_grp_id = fnc_grp_id;
    }

    public String[] getPrgmIdArray() {
        return (String[])CylArrayUtil.copy((Object[])this.prgmIdArray);
    }

    public void setPrgmIdArray(String[] prgmIdArray) {
        this.prgmIdArray = (String[])CylArrayUtil.copy((Object[])prgmIdArray);
    }

    public String getFnc_grp_nm_kr() {
        return this.fnc_grp_nm_kr;
    }

    public void setFnc_grp_nm_kr(String fnc_grp_nm_kr) {
        this.fnc_grp_nm_kr = fnc_grp_nm_kr;
    }

    public String getFnc_grp_nm_en() {
        return this.fnc_grp_nm_en;
    }

    public void setFnc_grp_nm_en(String fnc_grp_nm_en) {
        this.fnc_grp_nm_en = fnc_grp_nm_en;
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

    public String getCondPrgmCd() {
        return this.condPrgmCd;
    }

    public void setCondPrgmCd(String condPrgmCd) {
        this.condPrgmCd = condPrgmCd;
    }

    public String getCondPrgmUrlCd() {
        return this.condPrgmUrlCd;
    }

    public void setCondPrgmUrlCd(String condPrgmUrlCd) {
        this.condPrgmUrlCd = condPrgmUrlCd;
    }

    public String getAuthor_slct1() {
        return this.author_slct1;
    }

    public void setAuthor_slct1(String author_slct1) {
        this.author_slct1 = author_slct1;
    }

    public String getAuthor_slct2() {
        return this.author_slct2;
    }

    public void setAuthor_slct2(String author_slct2) {
        this.author_slct2 = author_slct2;
    }

    public String getAuthor_slct3() {
        return this.author_slct3;
    }

    public void setAuthor_slct3(String author_slct3) {
        this.author_slct3 = author_slct3;
    }

    public String getAuthor_slct4() {
        return this.author_slct4;
    }

    public void setAuthor_slct4(String author_slct4) {
        this.author_slct4 = author_slct4;
    }

    public String getAuthor_slct5() {
        return this.author_slct5;
    }

    public void setAuthor_slct5(String author_slct5) {
        this.author_slct5 = author_slct5;
    }

    public String getMenu_se() {
        return this.menu_se;
    }

    public void setMenu_se(String menu_se) {
        this.menu_se = menu_se;
    }
}
