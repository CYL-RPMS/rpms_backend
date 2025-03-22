package kr.co.cyberline.cmm.web.model;

import java.util.Date;
import java.util.List;
import kr.co.cyberline.cmm.util.lang.CylArrayUtil;
import kr.co.cyberline.cmm.util.lang.CylCollectionUtil;
import org.apache.ibatis.type.Alias;

@Alias("menuManageVO")
public class MenuManageVO extends CylWebDefaultVO {
    private static final long serialVersionUID = -399179621748367615L;

    private String menu_id;

    private String upr_menu_id;

    private String upr_menu_nm_kr;

    private String upr_menu_nm_en;

    private String menu_nm_kr;

    private String menu_nm_en;

    private String menu_url;

    private String menu_path;

    private int menu_sn;

    private String menu_se;

    private String menu_dc;

    private String menu_img_path;

    private String menu_img_nm;

    private String use_at;

    private String hide_at;

    private String crte_user_id;

    private Date crte_dttm;

    private String updt_user_id;

    private Date updt_dttm;

    private int lvl;

    public String[] menuIdArray;

    public String[] prgmIdArray;

    private String prgm_id;

    private String author_id;

    public List<MenuManageVO> subMenuList;

    public List<ProgramManageVO> prgmList;

    private String searchType;

    private String searchKeyword;

    private String condHideAt;

    private String author_slct1;

    private String author_slct2;

    private String author_slct3;

    private String author_slct4;

    private String author_slct5;

    private String author_slct1_url;

    private String author_slct2_url;

    private String author_slct3_url;

    private String author_slct4_url;

    private String author_slct5_url;

    public List<MenuManageVO> menuList;

    public MenuManageVO() {
        super("1", "A.MENU_SN", "ASC");
    }

    public String getMenu_id() {
        return this.menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getUpr_menu_id() {
        return this.upr_menu_id;
    }

    public void setUpr_menu_id(String upr_menu_id) {
        this.upr_menu_id = upr_menu_id;
    }

    public String getUpr_menu_nm_kr() {
        return this.upr_menu_nm_kr;
    }

    public void setUpr_menu_nm_kr(String upr_menu_nm_kr) {
        this.upr_menu_nm_kr = upr_menu_nm_kr;
    }

    public String getUpr_menu_nm_en() {
        return this.upr_menu_nm_en;
    }

    public void setUpr_menu_nm_en(String upr_menu_nm_en) {
        this.upr_menu_nm_en = upr_menu_nm_en;
    }

    public String getMenu_nm_kr() {
        return this.menu_nm_kr;
    }

    public void setMenu_nm_kr(String menu_nm_kr) {
        this.menu_nm_kr = menu_nm_kr;
    }

    public String getMenu_nm_en() {
        return this.menu_nm_en;
    }

    public void setMenu_nm_en(String menu_nm_en) {
        this.menu_nm_en = menu_nm_en;
    }

    public String getMenu_url() {
        return this.menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public int getMenu_sn() {
        return this.menu_sn;
    }

    public void setMenu_sn(int menu_sn) {
        this.menu_sn = menu_sn;
    }

    public String getMenu_dc() {
        return this.menu_dc;
    }

    public void setMenu_dc(String menu_dc) {
        this.menu_dc = menu_dc;
    }

    public String getMenu_img_path() {
        return this.menu_img_path;
    }

    public void setMenu_img_path(String menu_img_path) {
        this.menu_img_path = menu_img_path;
    }

    public String getMenu_img_nm() {
        return this.menu_img_nm;
    }

    public void setMenu_img_nm(String menu_img_nm) {
        this.menu_img_nm = menu_img_nm;
    }

    public String getUse_at() {
        return this.use_at;
    }

    public void setUse_at(String use_at) {
        this.use_at = use_at;
    }

    public String getHide_at() {
        return this.hide_at;
    }

    public void setHide_at(String hide_at) {
        this.hide_at = hide_at;
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

    public int getLvl() {
        return this.lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String[] getMenuIdArray() {
        return (String[])CylArrayUtil.copy((Object[])this.menuIdArray);
    }

    public void setMenuIdArray(String[] menuIdArray) {
        this.menuIdArray = (String[])CylArrayUtil.copy((Object[])menuIdArray);
    }

    public String[] getPrgmIdArray() {
        return (String[])CylArrayUtil.copy((Object[])this.prgmIdArray);
    }

    public void setPrgmIdArray(String[] prgmIdArray) {
        this.prgmIdArray = (String[])CylArrayUtil.copy((Object[])prgmIdArray);
    }

    public String getPrgm_id() {
        return this.prgm_id;
    }

    public void setPrgm_id(String prgm_id) {
        this.prgm_id = prgm_id;
    }

    public String getAuthor_id() {
        return this.author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public List<MenuManageVO> getSubMenuList() {
        return CylCollectionUtil.copy(this.subMenuList);
    }

    public void setSubMenuList(List<MenuManageVO> subMenuList) {
        this.subMenuList = CylCollectionUtil.copy(subMenuList);
    }

    public List<ProgramManageVO> getPrgmList() {
        return CylCollectionUtil.copy(this.prgmList);
    }

    public void setPrgmList(List<ProgramManageVO> prgmList) {
        this.prgmList = CylCollectionUtil.copy(prgmList);
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

    public String getCondHideAt() {
        return this.condHideAt;
    }

    public void setCondHideAt(String condHideAt) {
        this.condHideAt = condHideAt;
    }

    public String getMenu_path() {
        return this.menu_path;
    }

    public void setMenu_path(String menu_path) {
        this.menu_path = menu_path;
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

    public List<MenuManageVO> getMenuList() {
        return this.menuList;
    }

    public void setMenuList(List<MenuManageVO> menuList) {
        this.menuList = menuList;
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

    public String getAuthor_slct1_url() {
        return this.author_slct1_url;
    }

    public void setAuthor_slct1_url(String author_slct1_url) {
        this.author_slct1_url = author_slct1_url;
    }

    public String getAuthor_slct2_url() {
        return this.author_slct2_url;
    }

    public void setAuthor_slct2_url(String author_slct2_url) {
        this.author_slct2_url = author_slct2_url;
    }

    public String getAuthor_slct3_url() {
        return this.author_slct3_url;
    }

    public void setAuthor_slct3_url(String author_slct3_url) {
        this.author_slct3_url = author_slct3_url;
    }

    public String getAuthor_slct4_url() {
        return this.author_slct4_url;
    }

    public void setAuthor_slct4_url(String author_slct4_url) {
        this.author_slct4_url = author_slct4_url;
    }

    public String getAuthor_slct5_url() {
        return this.author_slct5_url;
    }

    public void setAuthor_slct5_url(String author_slct5_url) {
        this.author_slct5_url = author_slct5_url;
    }

    public String getMenu_se() {
        return this.menu_se;
    }

    public void setMenu_se(String menu_se) {
        this.menu_se = menu_se;
    }
}
