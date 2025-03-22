package kr.co.cyberline.pms.common.service;

import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;
import org.apache.ibatis.type.Alias;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 공통 VO
 * </p>
 *
 * @author ktj
 * @since 2015. 11. 23
 * @version 1.0
 */
@Alias("commonVO")
public class CommonVO extends CylWebDefaultVO {

	public List<HashMap> list;
	public List<CommonVO> user_check;
	public List<CommonVO> voList;

	private String table_name;
	private String table_name_co;
	private String col;
	private String column_name;
	private String data_type;
	private String col_pk;
	private String fk;
	private String nullable;
	private String col_default;
	private String column_name_co;

	/* 사용자 내외부 구분 */
	private String user_type;

	/* 부서 */
	private String dept_cd;
	private String dept_nm;
	private String use_yn;

	/* 내부 사용자 */
	private String user_id;
	private String join_at;
	private String etr_dt;
	private String kor_nm;
	private String brth_dt;
	private String rank_cd;
	private String rank_nm;
	private String spot_cd;
	private String spot_nm;
	private String upr_dept_cd;
	private String uupr_dept_cd;
	private String upr_dept_nm;
	private String uupr_dept_nm;

	/* 외부사용자 */
	private String user_nm;
	private String rrno;
	private String org_nm;
	private String brthdy;
	private String hp_no;
	private String email;
	private String mfcmm_se_cd;

	/* 기관 */
	private String org_id;
	private String org_ty_cd;
	private String bizrno;
	private String eng_nm;
	private String tel_no;
	private String hmpg;
	private String addr;
	private String etc;

	/* 평가 */
	private String evtb_id;
	private String evtb_nm;
	private String evl_se_cd;
	private String evl_mfcmm_id;
	private String evl_mfcmm_nm;
	private String crte_user_id;
	private String crte_dttm;
	private String updt_user_id;
	private String updt_dttm;

	/*평가 위원회*/
	private String mfcmm_id;
	private String yr;
	private String mfcmm_nm;
	private String se_cd;
	private String dc;

	/*과제관련*/
	private String bass_pn_id;
	private String exc_mby_cd;
	private String kor_pjt_nm;
	private String rsch_rspnber_nm;
	private String tot_rsch_pd_st;
	private String tot_rsch_pd_en;
	private String rsch_rspnber_id;
	private String rsch_rspnber_rank_nm;
	private String rsch_rspnber_hp;
	private String rsch_rspnber_tel;
	private String rsch_rspnber_rrno;
	private String rsch_rspnber_email;

	/*성과*/
	private String rst_id;
	private String rst_cd;

	/*제안*/
	private String propse_id;
	private String propse_yr;
	private String pjt_nm;
	private String rno;
	private String nm;

	/*계층코드*/
	private String grp_id;
	private String code;
	private String code_nm;
	private String use_at;
	private String upr_code;
	private String code_dc;
	private String code_sn;


	/*검색*/
	private String cond_dept_cd;
	private String cond_upr_dept_cd;
	private String cond_uupr_dept_cd;
	private String cond_kor_nm;
	private String cond_user_nm;
	private String cond_mfcmm_se_cd;
	private String cond_org_nm;			//기관명 검색
	private String cond_yr;
	private String cond_se_cd;
	private String cond_evtb_nm;
	private String cond_evl_se_cd;
	private String cond_mfcmm_nm;
	private String cond_bass_pn_id;
	private String cond_exc_mby_cd;
	private String cond_kor_pjt_nm;
	private String cond_rsch_rspnber_nm;
	private String cond_tot_rsch_pd_st;
	private String cond_tot_rsch_pd_en;
	private String cond_propse_yr;
	private String cond_pjt_nm;
	private String cond_rno;
	private String cond_nm;
	private String cond_code_nm;

	public List<HashMap> getList() {
		return list;
	}

	public void setList(List<HashMap> list) {
		this.list = list;
	}

	public List<CommonVO> getUser_check() {
		return user_check;
	}

	public void setUser_check(List<CommonVO> user_check) {
		this.user_check = user_check;
	}

	public List<CommonVO> getVoList() {
		return voList;
	}

	public void setVoList(List<CommonVO> voList) {
		this.voList = voList;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getTable_name_co() {
		return table_name_co;
	}

	public void setTable_name_co(String table_name_co) {
		this.table_name_co = table_name_co;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getCol_pk() {
		return col_pk;
	}

	public void setCol_pk(String col_pk) {
		this.col_pk = col_pk;
	}

	public String getFk() {
		return fk;
	}

	public void setFk(String fk) {
		this.fk = fk;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getCol_default() {
		return col_default;
	}

	public void setCol_default(String col_default) {
		this.col_default = col_default;
	}

	public String getColumn_name_co() {
		return column_name_co;
	}

	public void setColumn_name_co(String column_name_co) {
		this.column_name_co = column_name_co;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getDept_cd() {
		return dept_cd;
	}

	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
	}

	public String getDept_nm() {
		return dept_nm;
	}

	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getJoin_at() {
		return join_at;
	}

	public void setJoin_at(String join_at) {
		this.join_at = join_at;
	}

	public String getEtr_dt() {
		return etr_dt;
	}

	public void setEtr_dt(String etr_dt) {
		this.etr_dt = etr_dt;
	}

	public String getKor_nm() {
		return kor_nm;
	}

	public void setKor_nm(String kor_nm) {
		this.kor_nm = kor_nm;
	}

	public String getBrth_dt() {
		return brth_dt;
	}

	public void setBrth_dt(String brth_dt) {
		this.brth_dt = brth_dt;
	}

	public String getRank_cd() {
		return rank_cd;
	}

	public void setRank_cd(String rank_cd) {
		this.rank_cd = rank_cd;
	}

	public String getRank_nm() {
		return rank_nm;
	}

	public void setRank_nm(String rank_nm) {
		this.rank_nm = rank_nm;
	}

	public String getSpot_cd() {
		return spot_cd;
	}

	public void setSpot_cd(String spot_cd) {
		this.spot_cd = spot_cd;
	}

	public String getSpot_nm() {
		return spot_nm;
	}

	public void setSpot_nm(String spot_nm) {
		this.spot_nm = spot_nm;
	}

	public String getUpr_dept_cd() {
		return upr_dept_cd;
	}

	public void setUpr_dept_cd(String upr_dept_cd) {
		this.upr_dept_cd = upr_dept_cd;
	}

	public String getUupr_dept_cd() {
		return uupr_dept_cd;
	}

	public void setUupr_dept_cd(String uupr_dept_cd) {
		this.uupr_dept_cd = uupr_dept_cd;
	}

	public String getUpr_dept_nm() {
		return upr_dept_nm;
	}

	public void setUpr_dept_nm(String upr_dept_nm) {
		this.upr_dept_nm = upr_dept_nm;
	}

	public String getUupr_dept_nm() {
		return uupr_dept_nm;
	}

	public void setUupr_dept_nm(String uupr_dept_nm) {
		this.uupr_dept_nm = uupr_dept_nm;
	}

	public String getUser_nm() {
		return user_nm;
	}

	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}

	public String getRrno() {
		return rrno;
	}

	public void setRrno(String rrno) {
		this.rrno = rrno;
	}

	public String getOrg_nm() {
		return org_nm;
	}

	public void setOrg_nm(String org_nm) {
		this.org_nm = org_nm;
	}

	public String getBrthdy() {
		return brthdy;
	}

	public void setBrthdy(String brthdy) {
		this.brthdy = brthdy;
	}

	public String getHp_no() {
		return hp_no;
	}

	public void setHp_no(String hp_no) {
		this.hp_no = hp_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMfcmm_se_cd() {
		return mfcmm_se_cd;
	}

	public void setMfcmm_se_cd(String mfcmm_se_cd) {
		this.mfcmm_se_cd = mfcmm_se_cd;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getOrg_ty_cd() {
		return org_ty_cd;
	}

	public void setOrg_ty_cd(String org_ty_cd) {
		this.org_ty_cd = org_ty_cd;
	}

	public String getBizrno() {
		return bizrno;
	}

	public void setBizrno(String bizrno) {
		this.bizrno = bizrno;
	}

	public String getEng_nm() {
		return eng_nm;
	}

	public void setEng_nm(String eng_nm) {
		this.eng_nm = eng_nm;
	}

	public String getTel_no() {
		return tel_no;
	}

	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}

	public String getHmpg() {
		return hmpg;
	}

	public void setHmpg(String hmpg) {
		this.hmpg = hmpg;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getEvtb_id() {
		return evtb_id;
	}

	public void setEvtb_id(String evtb_id) {
		this.evtb_id = evtb_id;
	}

	public String getEvtb_nm() {
		return evtb_nm;
	}

	public void setEvtb_nm(String evtb_nm) {
		this.evtb_nm = evtb_nm;
	}

	public String getEvl_se_cd() {
		return evl_se_cd;
	}

	public void setEvl_se_cd(String evl_se_cd) {
		this.evl_se_cd = evl_se_cd;
	}

	public String getEvl_mfcmm_id() {
		return evl_mfcmm_id;
	}

	public void setEvl_mfcmm_id(String evl_mfcmm_id) {
		this.evl_mfcmm_id = evl_mfcmm_id;
	}

	public String getEvl_mfcmm_nm() {
		return evl_mfcmm_nm;
	}

	public void setEvl_mfcmm_nm(String evl_mfcmm_nm) {
		this.evl_mfcmm_nm = evl_mfcmm_nm;
	}

	public String getCrte_user_id() {
		return crte_user_id;
	}

	public void setCrte_user_id(String crte_user_id) {
		this.crte_user_id = crte_user_id;
	}

	public String getCrte_dttm() {
		return crte_dttm;
	}

	public void setCrte_dttm(String crte_dttm) {
		this.crte_dttm = crte_dttm;
	}

	public String getUpdt_user_id() {
		return updt_user_id;
	}

	public void setUpdt_user_id(String updt_user_id) {
		this.updt_user_id = updt_user_id;
	}

	public String getUpdt_dttm() {
		return updt_dttm;
	}

	public void setUpdt_dttm(String updt_dttm) {
		this.updt_dttm = updt_dttm;
	}

	public String getMfcmm_id() {
		return mfcmm_id;
	}

	public void setMfcmm_id(String mfcmm_id) {
		this.mfcmm_id = mfcmm_id;
	}

	public String getYr() {
		return yr;
	}

	public void setYr(String yr) {
		this.yr = yr;
	}

	public String getMfcmm_nm() {
		return mfcmm_nm;
	}

	public void setMfcmm_nm(String mfcmm_nm) {
		this.mfcmm_nm = mfcmm_nm;
	}

	public String getSe_cd() {
		return se_cd;
	}

	public void setSe_cd(String se_cd) {
		this.se_cd = se_cd;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getBass_pn_id() {
		return bass_pn_id;
	}

	public void setBass_pn_id(String bass_pn_id) {
		this.bass_pn_id = bass_pn_id;
	}

	public String getExc_mby_cd() {
		return exc_mby_cd;
	}

	public void setExc_mby_cd(String exc_mby_cd) {
		this.exc_mby_cd = exc_mby_cd;
	}

	public String getKor_pjt_nm() {
		return kor_pjt_nm;
	}

	public void setKor_pjt_nm(String kor_pjt_nm) {
		this.kor_pjt_nm = kor_pjt_nm;
	}

	public String getRsch_rspnber_nm() {
		return rsch_rspnber_nm;
	}

	public void setRsch_rspnber_nm(String rsch_rspnber_nm) {
		this.rsch_rspnber_nm = rsch_rspnber_nm;
	}

	public String getTot_rsch_pd_st() {
		return tot_rsch_pd_st;
	}

	public void setTot_rsch_pd_st(String tot_rsch_pd_st) {
		this.tot_rsch_pd_st = tot_rsch_pd_st;
	}

	public String getTot_rsch_pd_en() {
		return tot_rsch_pd_en;
	}

	public void setTot_rsch_pd_en(String tot_rsch_pd_en) {
		this.tot_rsch_pd_en = tot_rsch_pd_en;
	}

	public String getRst_id() {
		return rst_id;
	}

	public void setRst_id(String rst_id) {
		this.rst_id = rst_id;
	}

	public String getRst_cd() {
		return rst_cd;
	}

	public void setRst_cd(String rst_cd) {
		this.rst_cd = rst_cd;
	}

	public String getPropse_id() {
		return propse_id;
	}

	public void setPropse_id(String propse_id) {
		this.propse_id = propse_id;
	}

	public String getPropse_yr() {
		return propse_yr;
	}

	public void setPropse_yr(String propse_yr) {
		this.propse_yr = propse_yr;
	}

	public String getPjt_nm() {
		return pjt_nm;
	}

	public void setPjt_nm(String pjt_nm) {
		this.pjt_nm = pjt_nm;
	}

	public String getRno() {
		return rno;
	}

	public void setRno(String rno) {
		this.rno = rno;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getGrp_id() {
		return grp_id;
	}

	public void setGrp_id(String grp_id) {
		this.grp_id = grp_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode_nm() {
		return code_nm;
	}

	public void setCode_nm(String code_nm) {
		this.code_nm = code_nm;
	}

	public String getUse_at() {
		return use_at;
	}

	public void setUse_at(String use_at) {
		this.use_at = use_at;
	}

	public String getUpr_code() {
		return upr_code;
	}

	public void setUpr_code(String upr_code) {
		this.upr_code = upr_code;
	}

	public String getCode_dc() {
		return code_dc;
	}

	public void setCode_dc(String code_dc) {
		this.code_dc = code_dc;
	}

	public String getCode_sn() {
		return code_sn;
	}

	public void setCode_sn(String code_sn) {
		this.code_sn = code_sn;
	}

	public String getCond_dept_cd() {
		return cond_dept_cd;
	}

	public void setCond_dept_cd(String cond_dept_cd) {
		this.cond_dept_cd = cond_dept_cd;
	}

	public String getCond_upr_dept_cd() {
		return cond_upr_dept_cd;
	}

	public void setCond_upr_dept_cd(String cond_upr_dept_cd) {
		this.cond_upr_dept_cd = cond_upr_dept_cd;
	}

	public String getCond_uupr_dept_cd() {
		return cond_uupr_dept_cd;
	}

	public void setCond_uupr_dept_cd(String cond_uupr_dept_cd) {
		this.cond_uupr_dept_cd = cond_uupr_dept_cd;
	}

	public String getCond_kor_nm() {
		return cond_kor_nm;
	}

	public void setCond_kor_nm(String cond_kor_nm) {
		this.cond_kor_nm = cond_kor_nm;
	}

	public String getCond_user_nm() {
		return cond_user_nm;
	}

	public void setCond_user_nm(String cond_user_nm) {
		this.cond_user_nm = cond_user_nm;
	}

	public String getCond_mfcmm_se_cd() {
		return cond_mfcmm_se_cd;
	}

	public void setCond_mfcmm_se_cd(String cond_mfcmm_se_cd) {
		this.cond_mfcmm_se_cd = cond_mfcmm_se_cd;
	}

	public String getCond_org_nm() {
		return cond_org_nm;
	}

	public void setCond_org_nm(String cond_org_nm) {
		this.cond_org_nm = cond_org_nm;
	}

	public String getCond_yr() {
		return cond_yr;
	}

	public void setCond_yr(String cond_yr) {
		this.cond_yr = cond_yr;
	}

	public String getCond_se_cd() {
		return cond_se_cd;
	}

	public void setCond_se_cd(String cond_se_cd) {
		this.cond_se_cd = cond_se_cd;
	}

	public String getCond_evtb_nm() {
		return cond_evtb_nm;
	}

	public void setCond_evtb_nm(String cond_evtb_nm) {
		this.cond_evtb_nm = cond_evtb_nm;
	}

	public String getCond_evl_se_cd() {
		return cond_evl_se_cd;
	}

	public void setCond_evl_se_cd(String cond_evl_se_cd) {
		this.cond_evl_se_cd = cond_evl_se_cd;
	}

	public String getCond_mfcmm_nm() {
		return cond_mfcmm_nm;
	}

	public void setCond_mfcmm_nm(String cond_mfcmm_nm) {
		this.cond_mfcmm_nm = cond_mfcmm_nm;
	}

	public String getCond_bass_pn_id() {
		return cond_bass_pn_id;
	}

	public void setCond_bass_pn_id(String cond_bass_pn_id) {
		this.cond_bass_pn_id = cond_bass_pn_id;
	}

	public String getCond_exc_mby_cd() {
		return cond_exc_mby_cd;
	}

	public void setCond_exc_mby_cd(String cond_exc_mby_cd) {
		this.cond_exc_mby_cd = cond_exc_mby_cd;
	}

	public String getCond_kor_pjt_nm() {
		return cond_kor_pjt_nm;
	}

	public void setCond_kor_pjt_nm(String cond_kor_pjt_nm) {
		this.cond_kor_pjt_nm = cond_kor_pjt_nm;
	}

	public String getCond_rsch_rspnber_nm() {
		return cond_rsch_rspnber_nm;
	}

	public void setCond_rsch_rspnber_nm(String cond_rsch_rspnber_nm) {
		this.cond_rsch_rspnber_nm = cond_rsch_rspnber_nm;
	}

	public String getCond_tot_rsch_pd_st() {
		return cond_tot_rsch_pd_st;
	}

	public void setCond_tot_rsch_pd_st(String cond_tot_rsch_pd_st) {
		this.cond_tot_rsch_pd_st = cond_tot_rsch_pd_st;
	}

	public String getCond_tot_rsch_pd_en() {
		return cond_tot_rsch_pd_en;
	}

	public void setCond_tot_rsch_pd_en(String cond_tot_rsch_pd_en) {
		this.cond_tot_rsch_pd_en = cond_tot_rsch_pd_en;
	}

	public String getCond_propse_yr() {
		return cond_propse_yr;
	}

	public void setCond_propse_yr(String cond_propse_yr) {
		this.cond_propse_yr = cond_propse_yr;
	}

	public String getCond_pjt_nm() {
		return cond_pjt_nm;
	}

	public void setCond_pjt_nm(String cond_pjt_nm) {
		this.cond_pjt_nm = cond_pjt_nm;
	}

	public String getCond_rno() {
		return cond_rno;
	}

	public void setCond_rno(String cond_rno) {
		this.cond_rno = cond_rno;
	}

	public String getCond_nm() {
		return cond_nm;
	}

	public void setCond_nm(String cond_nm) {
		this.cond_nm = cond_nm;
	}

	public String getCond_code_nm() {
		return cond_code_nm;
	}

	public void setCond_code_nm(String cond_code_nm) {
		this.cond_code_nm = cond_code_nm;
	}

	public String getRsch_rspnber_id() {
		return rsch_rspnber_id;
	}

	public void setRsch_rspnber_id(String rsch_rspnber_id) {
		this.rsch_rspnber_id = rsch_rspnber_id;
	}

	public String getRsch_rspnber_rank_nm() {
		return rsch_rspnber_rank_nm;
	}

	public void setRsch_rspnber_rank_nm(String rsch_rspnber_rank_nm) {
		this.rsch_rspnber_rank_nm = rsch_rspnber_rank_nm;
	}

	public String getRsch_rspnber_hp() {
		return rsch_rspnber_hp;
	}

	public void setRsch_rspnber_hp(String rsch_rspnber_hp) {
		this.rsch_rspnber_hp = rsch_rspnber_hp;
	}

	public String getRsch_rspnber_tel() {
		return rsch_rspnber_tel;
	}

	public void setRsch_rspnber_tel(String rsch_rspnber_tel) {
		this.rsch_rspnber_tel = rsch_rspnber_tel;
	}

	public String getRsch_rspnber_rrno() {
		return rsch_rspnber_rrno;
	}

	public void setRsch_rspnber_rrno(String rsch_rspnber_rrno) {
		this.rsch_rspnber_rrno = rsch_rspnber_rrno;
	}

	public String getRsch_rspnber_email() {
		return rsch_rspnber_email;
	}

	public void setRsch_rspnber_email(String rsch_rspnber_email) {
		this.rsch_rspnber_email = rsch_rspnber_email;
	}
}
