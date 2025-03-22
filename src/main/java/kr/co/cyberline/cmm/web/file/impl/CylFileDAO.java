package kr.co.cyberline.cmm.web.file.impl;

import java.util.List;
import kr.co.cyberline.cmm.web.dao.CylAbstractDAO;
import kr.co.cyberline.cmm.web.file.CylFileVO;
import org.springframework.stereotype.Repository;

@Repository
public class CylFileDAO extends CylAbstractDAO {
    public CylFileDAO() {
    }

    protected String getNameSpace() {
        return "CylCmmFile";
    }

    public List<CylFileVO> selectAtchFileList(CylFileVO fileVO) {
        return this.selectList("selectAtchFileList", fileVO);
    }

    public CylFileVO selectAtchFileDetail(CylFileVO fileVO) {
        return (CylFileVO)this.select("selectAtchFileDetail", fileVO);
    }

    public int insertAtchFile(CylFileVO fileVO) {
        return this.insert("insertAtchFile", fileVO);
    }

    public int updateAtchFileUseAt(CylFileVO fileVO) {
        return this.update("updateAtchFileUseAt", fileVO);
    }

    public int updateAtchFileDetailUseAt(CylFileVO fileVO) {
        return this.update("updateAtchFileDetailUseAt", fileVO);
    }

    public int insertAtchFileDetail(CylFileVO fileVO) {
        return this.insert("insertAtchFileDetail", fileVO);
    }

    public int deleteAtchFile(CylFileVO fileVO) {
        return this.delete("deleteAtchFile", fileVO);
    }

    public int deleteAtchFileDetail(CylFileVO fileVO) {
        return this.delete("deleteAtchFileDetail", fileVO);
    }
}
