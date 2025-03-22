/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 게시판 마스터 VO
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.cmm.model;

import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * <p>게시판 마스터 Value Object</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Alias("boardMasterVO")
public class BoardMasterVO extends CylWebDefaultVO {

    private static final long serialVersionUID = -7732974950213694991L;

    private String bbs_id;                  // 게시판아이디
    private String bbs_nm;                  // 게시판이름
    private String bbs_intrcn;              // 게시판소개
    private String bbs_ty_code;             // 게시판유형코드
    private String reply_posbl_at;          // 답장가능여부
    private String file_atch_posbl_at;      // 파일첨부가능여부
    private int atch_file_posbl_number;     // 첨부파일가능숫자
    private String answer_posbl_at;         // 댓글 사용여부
    private String use_at;                  // 사용여부
    private String crte_user_id;            // 등록사용자아이디
    private Date crte_dttm;                 // 등록일시
    private String updt_user_id;            // 수정사용자아이디
    private Date updt_dttm;                 // 수정일시

    private String searchType;              // 검색 구분
    private String searchKeyword;           // 검색 키워드
    private String condUseAt;               // 검색 사용 여부
    private String condFileAtchPosblAt;     // 검색 파일첨부가능여부
    private String condReplyPosblAt;        // 검색 답장가능여부
    private String condAnswerPosblAt;        // 검색 댓글가능여부


    public BoardMasterVO(){
        super(PAGING_ENABLE_ON, "A.CRTE_DTTM", "DESC");
    }

    public String getBbs_id() {
        return bbs_id;
    }

    public void setBbs_id(String bbs_id) {
        this.bbs_id = bbs_id;
    }

    public String getBbs_nm() {
        return bbs_nm;
    }

    public void setBbs_nm(String bbs_nm) {
        this.bbs_nm = bbs_nm;
    }

    public String getBbs_intrcn() {
        return bbs_intrcn;
    }

    public void setBbs_intrcn(String bbs_intrcn) {
        this.bbs_intrcn = bbs_intrcn;
    }

    public String getBbs_ty_code() {
        return bbs_ty_code;
    }

    public void setBbs_ty_code(String bbs_ty_code) {
        this.bbs_ty_code = bbs_ty_code;
    }

    public String getReply_posbl_at() {
        return reply_posbl_at;
    }

    public void setReply_posbl_at(String reply_posbl_at) {
        this.reply_posbl_at = reply_posbl_at;
    }

    public String getFile_atch_posbl_at() {
        return file_atch_posbl_at;
    }

    public void setFile_atch_posbl_at(String file_atch_posbl_at) {
        this.file_atch_posbl_at = file_atch_posbl_at;
    }

    public int getAtch_file_posbl_number() {
        return atch_file_posbl_number;
    }

    public void setAtch_file_posbl_number(int atch_file_posbl_number) {
        this.atch_file_posbl_number = atch_file_posbl_number;
    }

    public String getAnswer_posbl_at() {
        return answer_posbl_at;
    }

    public void setAnswer_posbl_at(String answer_posbl_at) {
        this.answer_posbl_at = answer_posbl_at;
    }

    public String getUse_at() {
        return use_at;
    }

    public void setUse_at(String use_at) {
        this.use_at = use_at;
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

    public String getCondUseAt() {
        return condUseAt;
    }

    public void setCondUseAt(String condUseAt) {
        this.condUseAt = condUseAt;
    }

    public String getCondFileAtchPosblAt() {
        return condFileAtchPosblAt;
    }

    public void setCondFileAtchPosblAt(String condFileAtchPosblAt) {
        this.condFileAtchPosblAt = condFileAtchPosblAt;
    }

    public String getCondReplyPosblAt() {
        return condReplyPosblAt;
    }

    public void setCondReplyPosblAt(String condReplyPosblAt) {
        this.condReplyPosblAt = condReplyPosblAt;
    }

    public String getCondAnswerPosblAt() {
        return condAnswerPosblAt;
    }

    public void setCondAnswerPosblAt(String condAnswerPosblAt) {
        this.condAnswerPosblAt = condAnswerPosblAt;
    }
}