package kr.co.cyberline.cmm.web.file;

import java.util.Date;
import java.util.List;
import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;
import org.apache.ibatis.type.Alias;

@Alias("cylFileVO")
public class CylFileVO extends CylWebDefaultVO {
    private String atch_file_id;
    private String atch_file_nm;
    private int file_sn;
    private String use_at;
    private String file_stre_cours;
    private String stre_file_nm;
    private String orginl_file_nm;
    private String file_extsn;
    private String file_cn;
    private long file_size = 0L;
    private Date crte_dttm;
    private String real_path;
    private String session_id;
    private String file_path;
    private String file_nm;
    private String delAtchFileIds;
    private List<CylFileVO> atchFiles;
    private List<CylFileVO> atchFiles1;
    private List<CylFileVO> atchFiles2;
    private List<CylFileVO> atchFiles3;
    private List<CylFileVO> atchFiles4;
    private List<CylFileVO> atchFiles5;

    public CylFileVO() {
        super("1", "A.CRTE_DTTM", "DESC");
    }

    public CylFileVO(String pagingEnable, String condOrder, String condAlign) {
        super(pagingEnable, condOrder, condAlign);
    }

    public String getAtch_file_id() {
        return this.atch_file_id;
    }

    public void setAtch_file_id(String atch_file_id) {
        this.atch_file_id = atch_file_id;
    }

    public int getFile_sn() {
        return this.file_sn;
    }

    public void setFile_sn(int file_sn) {
        this.file_sn = file_sn;
    }

    public String getUse_at() {
        return this.use_at;
    }

    public void setUse_at(String use_at) {
        this.use_at = use_at;
    }

    public String getFile_stre_cours() {
        return this.file_stre_cours;
    }

    public void setFile_stre_cours(String file_stre_cours) {
        this.file_stre_cours = file_stre_cours;
    }

    public String getStre_file_nm() {
        return this.stre_file_nm;
    }

    public void setStre_file_nm(String stre_file_nm) {
        this.stre_file_nm = stre_file_nm;
    }

    public String getOrginl_file_nm() {
        return this.orginl_file_nm;
    }

    public void setOrginl_file_nm(String orginl_file_nm) {
        this.orginl_file_nm = orginl_file_nm;
    }

    public String getFile_extsn() {
        return this.file_extsn;
    }

    public void setFile_extsn(String file_extsn) {
        this.file_extsn = file_extsn;
    }

    public String getFile_cn() {
        return this.file_cn;
    }

    public void setFile_cn(String file_cn) {
        this.file_cn = file_cn;
    }

    public long getFile_size() {
        return this.file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public Date getCrte_dttm() {
        return this.crte_dttm;
    }

    public void setCrte_dttm(Date crte_dttm) {
        this.crte_dttm = crte_dttm;
    }

    public String getReal_path() {
        return this.real_path;
    }

    public void setReal_path(String real_path) {
        this.real_path = real_path;
    }

    public String getSession_id() {
        return this.session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public List<CylFileVO> getAtchFiles() {
        return this.atchFiles;
    }

    public void setAtchFiles(List<CylFileVO> atchFiles) {
        this.atchFiles = atchFiles;
    }

    public String getFile_path() {
        return this.file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_nm() {
        return this.file_nm;
    }

    public void setFile_nm(String file_nm) {
        this.file_nm = file_nm;
    }

    public String getAtch_file_nm() {
        return this.atch_file_nm;
    }

    public void setAtch_file_nm(String atch_file_nm) {
        this.atch_file_nm = atch_file_nm;
    }

    public List<CylFileVO> getAtchFiles2() {
        return this.atchFiles2;
    }

    public void setAtchFiles2(List<CylFileVO> atchFiles2) {
        this.atchFiles2 = atchFiles2;
    }

    public List<CylFileVO> getAtchFiles3() {
        return this.atchFiles3;
    }

    public void setAtchFiles3(List<CylFileVO> atchFiles3) {
        this.atchFiles3 = atchFiles3;
    }

    public List<CylFileVO> getAtchFiles4() {
        return this.atchFiles4;
    }

    public void setAtchFiles4(List<CylFileVO> atchFiles4) {
        this.atchFiles4 = atchFiles4;
    }

    public List<CylFileVO> getAtchFiles1() {
        return this.atchFiles1;
    }

    public void setAtchFiles1(List<CylFileVO> atchFiles1) {
        this.atchFiles1 = atchFiles1;
    }

    public List<CylFileVO> getAtchFiles5() {
        return this.atchFiles5;
    }

    public void setAtchFiles5(List<CylFileVO> atchFiles5) {
        this.atchFiles5 = atchFiles5;
    }

    public String getDelAtchFileIds() {
        return this.delAtchFileIds;
    }

    public void setDelAtchFileIds(String delAtchFileIds) {
        this.delAtchFileIds = delAtchFileIds;
    }
}
