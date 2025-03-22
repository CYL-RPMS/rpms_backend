package kr.co.cyberline.cmm.web.hcode.impl;

import java.sql.SQLException;
import java.util.List;
import kr.co.cyberline.cmm.web.dao.CylAbstractDAO;
import kr.co.cyberline.cmm.web.hcode.CylHierCodeVO;
import org.springframework.stereotype.Repository;

@Repository("cylHierCodeDAO")
public class CylHierCodeDAO extends CylAbstractDAO {
    public CylHierCodeDAO() {
    }

    protected String getNameSpace() {
        return "CylCmmHierCode";
    }

    public List<CylHierCodeVO> selectHierGroupCodeList(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.selectList("selectHierGroupCodeList", hierCodeVO);
    }

    public int selectHierGroupCodeListCnt(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.selectListCount("selectHierGroupCodeListCnt", hierCodeVO);
    }

    public CylHierCodeVO selectHierGroupCode(CylHierCodeVO hierCodeVO) throws SQLException {
        return (CylHierCodeVO)this.select("selectHierGroupCode", hierCodeVO);
    }

    public boolean isHierGroupCodeExists(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.isExist("isHierGroupCodeExists", hierCodeVO);
    }

    public int insertHierGroupCode(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.insert("insertHierGroupCode", hierCodeVO);
    }

    public int updateHierGroupCode(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.update("updateHierGroupCode", hierCodeVO);
    }

    public int deleteHierGroupCode(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.delete("deleteHierGroupCode", hierCodeVO);
    }

    public int truncateHierGroupCode() throws SQLException {
        return this.delete("truncateHierGroupCode");
    }

    public void truncateHierDetailCode() throws SQLException {
        this.delete("truncateHierDetailCode");
    }

    public int deleteGroupHierDetailCode(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.delete("deleteGroupHierDetailCode", hierCodeVO);
    }

    public List<CylHierCodeVO> selectHierCodeTreeList(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.selectList("selectHierCodeTreeList", hierCodeVO);
    }

    public List<CylHierCodeVO> selectHierCodeForCmmCodeList(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.selectList("selectHierCodeForCmmCodeList", hierCodeVO);
    }

    public List<CylHierCodeVO> selectHierGroupIdList(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.selectList("selectHierGroupIdList", hierCodeVO);
    }

    public int deleteHierCode(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.delete("deleteHierCode", hierCodeVO);
    }

    public List<CylHierCodeVO> selectOperationHierCodeList(CylHierCodeVO hierCodeVO) {
        return (List<CylHierCodeVO>) this.baseDAO.selectList(hierCodeVO.getOperation(), hierCodeVO);
    }

    public List<CylHierCodeVO> selectHierCodeList(CylHierCodeVO hierCodeVO) {
        return this.selectList("selectHierCodeList", hierCodeVO);
    }
}
