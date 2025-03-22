/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 댓글 VO
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.cmm.model;

import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * <p>댓글 Value Object</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Alias("commentVO")
public class CommentVO extends CylWebDefaultVO {
	private static final long serialVersionUID = 2331188214752795586L;

    private String bbs_id;                  // 게시판아이디
    private int ntt_id;                     // 게시물아이디
    private int answer_no;                  // 댓글번호
    private String wrter_id;                // 작성자아이디
    private String wrter_nm;                // 작성자이름
    private String answer;                  // 댓글
    private String use_at;                  // 사용여부
    private String passwd;                  // 비밀번호
    private String crte_user_id;            // 등록사용자아이디
    private Date crte_dttm;                 // 등록일시
    private String updt_user_id;            // 수정사용자아이디
    private Date updt_dttm;                 // 수정일시

    public CommentVO(){
        super(PAGING_ENABLE_ON, "A.CRTE_DTTM", "DESC");
    }

    public String getBbs_id() {
        return bbs_id;
    }

    public void setBbs_id(String bbs_id) {
        this.bbs_id = bbs_id;
    }

    public int getNtt_id() {
        return ntt_id;
    }

    public void setNtt_id(int ntt_id) {
        this.ntt_id = ntt_id;
    }

    public int getAnswer_no() {
        return answer_no;
    }

    public void setAnswer_no(int answer_no) {
        this.answer_no = answer_no;
    }

    public String getWrter_id() {
        return wrter_id;
    }

    public void setWrter_id(String wrter_id) {
        this.wrter_id = wrter_id;
    }

    public String getWrter_nm() {
        return wrter_nm;
    }

    public void setWrter_nm(String wrter_nm) {
        this.wrter_nm = wrter_nm;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = CylStringUtil.unscript(answer);
    }

    public String getUse_at() {
        return use_at;
    }

    public void setUse_at(String use_at) {
        this.use_at = use_at;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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
}
