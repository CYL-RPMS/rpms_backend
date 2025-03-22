package kr.co.cyberline.cmm.web.code.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.cyberline.cmm.web.code.CylCmmCodeDetailVO;
import kr.co.cyberline.cmm.web.code.CylCmmCodeVO;
import kr.co.cyberline.cmm.web.dao.CylAbstractDAO;
import org.springframework.stereotype.Repository;

@Repository("cylCmmCodeDAO")
public class CylCmmCodeDAO extends CylAbstractDAO {
    public CylCmmCodeDAO() {
    }

    protected String getNameSpace() {
        return "CylCmmCode";
    }

    public List<CylCmmCodeVO> retrieveCmmCodeList(CylCmmCodeVO cmmCodeVO) {
        return this.selectList("retrieveCmmCodeList", cmmCodeVO);
    }

    public List<CylCmmCodeVO> retrieveCmmCodeTreeList(CylCmmCodeVO cmmCodeVO) {
        return this.selectList("retrieveCmmCodeTreeList", cmmCodeVO);
    }

    public List<CylCmmCodeDetailVO> retrieveCmmCodeDetailList(CylCmmCodeDetailVO cylCmmCodeDetailVO) {
        return this.selectList("retrieveCmmCodeDetailList", cylCmmCodeDetailVO);
    }

    public Map retrieveCmmCodeDetailMap(CylCmmCodeDetailVO cylCmmCodeDetailVO) {
        return this.selectMap("retrieveCmmCodeDetailMap", cylCmmCodeDetailVO, "CODE");
    }

    public List<CylCmmCodeDetailVO> selectOperationCodeList(CylCmmCodeVO commonCodeVO) {
        return (List<CylCmmCodeDetailVO>) this.baseDAO.selectList(commonCodeVO.getOperation(), commonCodeVO);
    }

    public int insertCmmCodeManage(CylCmmCodeVO cmmCodeVO) {
        return this.insert("insertCmmCodeManage", cmmCodeVO);
    }

    public CylCmmCodeVO selectCmmCodeManageDetail(CylCmmCodeVO cmmCodeVO) {
        return (CylCmmCodeVO)this.select("selectCmmCodeManageDetail", cmmCodeVO);
    }

    public int deleteCmmCodeManage(CylCmmCodeVO cmmCodeVO) {
        return this.delete("deleteCmmCodeManage", cmmCodeVO);
    }

    public CylCmmCodeDetailVO selectCmmCodeDetailManageDetail(CylCmmCodeDetailVO cmmCodeDetailVO) {
        return (CylCmmCodeDetailVO)this.select("selectCmmCodeDetailManageDetail", cmmCodeDetailVO);
    }

    public int insertCmmCodeDetailManage(CylCmmCodeDetailVO cylCmmCodeDetailVO) {
        return this.insert("insertCmmCodeDetailManage", cylCmmCodeDetailVO);
    }

    public int deleteCmmCodeDetailManage(CylCmmCodeDetailVO cylCmmCodeDetailVO) {
        return this.delete("deleteCmmCodeDetailManage", cylCmmCodeDetailVO);
    }

    public int updateCmmCodeManage(CylCmmCodeVO cylCmmCodeVO) {
        return this.update("updateCmmCodeManage", cylCmmCodeVO);
    }

    public int allDeleteCmmCodeDetailManage(CylCmmCodeVO cmmCodeVO) {
        return this.delete("allDeleteCmmCodeDetailManage", cmmCodeVO);
    }

    public int updateCmmCodeDetailManage(CylCmmCodeDetailVO cylCmmCodeDetailVO) {
        return this.update("updateCmmCodeDetailManage", cylCmmCodeDetailVO);
    }

    public List<CylCmmCodeDetailVO> selectCmmCodeManageSearch(HashMap<String, String> map) {
        return this.selectList("selectCmmCodeManageSearch", map);
    }
}
