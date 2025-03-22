/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 게시판 VO
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.cmm.model;

import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * <p>게시판 Value Object</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Alias("boardVO")
public class BoardVO extends CylWebDefaultVO {

    private static final long serialVersionUID = -494486134440630235L;

    private String bbs_id;                  // 게시판아이디
    private int ntt_id;                     // 게시물아이디
    private String ntt_sj;                  // 게시물제목
    private String ntt_cn;                  // 게시물내용
    private int parnts_ntt_id;              // 부모게시글ID
    private String use_at;                  // 사용여부
    private int rdcnt;                      // 조회수
    private String ntce_bgnde;              // 게시시작일
    private String ntce_endde;              // 게시종료일
    private String ntcr_id;                 // 게시자아이디
    private String ntcr_nm;                 // 게시자이름
    private String passwd;                  // 게시자이름
    private String atch_file_id;            // 첨부파일아이디
    private String crte_user_id;            // 등록사용자아이디
    private Date crte_dttm;                 // 등록일시
    private String updt_user_id;            // 수정사용자아이디
    private Date updt_dttm;                 // 수정일시
    private int lvl;                        // 게시물 Level dept

    private String searchType;              // 검색 구분
    private String searchKeyword;           // 검색 키워드

    public BoardVO(){
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

    public String getNtt_sj() {
        return ntt_sj;
    }

    public void setNtt_sj(String ntt_sj) {
        this.ntt_sj = CylStringUtil.unscript(ntt_sj);
    }

    public String getNtt_cn() {
        return ntt_cn;
    }

    public void setNtt_cn(String ntt_cn) {
        this.ntt_cn = CylStringUtil.unscript(ntt_cn);
    }

    public int getParnts_ntt_id() {
        return parnts_ntt_id;
    }

    public void setParnts_ntt_id(int parnts_ntt_id) {
        this.parnts_ntt_id = parnts_ntt_id;
    }

    public String getUse_at() {
        return use_at;
    }

    public void setUse_at(String use_at) {
        this.use_at = use_at;
    }

    public int getRdcnt() {
        return rdcnt;
    }

    public void setRdcnt(int rdcnt) {
        this.rdcnt = rdcnt;
    }

    public String getNtce_bgnde() {
        return ntce_bgnde;
    }

    public void setNtce_bgnde(String ntce_bgnde) {
        this.ntce_bgnde = ntce_bgnde;
    }

    public String getNtce_endde() {
        return ntce_endde;
    }

    public void setNtce_endde(String ntce_endde) {
        this.ntce_endde = ntce_endde;
    }

    public String getNtcr_id() {
        return ntcr_id;
    }

    public void setNtcr_id(String ntcr_id) {
        this.ntcr_id = ntcr_id;
    }

    public String getNtcr_nm() {
        return ntcr_nm;
    }

    public void setNtcr_nm(String ntcr_nm) {
        this.ntcr_nm = ntcr_nm;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAtch_file_id() {
        return atch_file_id;
    }

    public void setAtch_file_id(String atch_file_id) {
        this.atch_file_id = atch_file_id;
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

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = CylStringUtil.unscript(searchType);
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}