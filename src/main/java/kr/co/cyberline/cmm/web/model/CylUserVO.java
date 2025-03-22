package kr.co.cyberline.cmm.web.model;

import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("cylUserVO")
public class CylUserVO extends CylWebDefaultVO {
    private String user_id;

    private String login_id;

    private String passwd;

    private String user_nm;

    private String user_sttus_cd;

    private String crte_user_id;

    private Date crte_dttm;

    private String updt_user_id;

    private Date updt_dttm;

    private String author_id;

    private String author_nm_kr;

    private String dept_cd;

    private String dept_nm;

    public CylUserVO() {
        super("1", "A.CRTE_DTTM", "DESC");
    }

    public CylUserVO(String pagingEnable, String condOrder, String condAlign) {
        super(pagingEnable, condOrder, condAlign);
    }

    public CylUserVO(String user_id, String passwd) {
        this.user_id = user_id;
        this.passwd = passwd;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLogin_id() {
        return this.login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUser_nm() {
        return this.user_nm;
    }

    public void setUser_nm(String user_nm) {
        this.user_nm = user_nm;
    }

    public String getUser_sttus_cd() {
        return this.user_sttus_cd;
    }

    public void setUser_sttus_cd(String user_sttus_cd) {
        this.user_sttus_cd = user_sttus_cd;
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

    public String getAuthor_id() {
        return this.author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_nm_kr() {
        return this.author_nm_kr;
    }

    public void setAuthor_nm_kr(String author_nm_kr) {
        this.author_nm_kr = author_nm_kr;
    }

    public String getDept_cd() {
        return this.dept_cd;
    }

    public void setDept_cd(String dept_cd) {
        this.dept_cd = dept_cd;
    }

    public String getDept_nm() {
        return this.dept_nm;
    }

    public void setDept_nm(String dept_nm) {
        this.dept_nm = dept_nm;
    }
}
