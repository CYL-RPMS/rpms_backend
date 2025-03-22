package kr.co.cyberline.cmm.web.hcode.impl;

import java.util.List;
import kr.co.cyberline.cmm.web.dao.CylAbstractDAO;
import kr.co.cyberline.cmm.web.hcode.CylHierCodeVO;
import org.springframework.stereotype.Repository;

@Repository("cylHierDetailCodeDAO")
public class CylHierDetailCodeDAO extends CylAbstractDAO<CylHierCodeVO> {
    public CylHierDetailCodeDAO() {
    }

    protected String getNameSpace() {
        return "CylCmmHierDetailCode";
    }

    public List<CylHierCodeVO> selectHiercodeDetailList(CylHierCodeVO cylHierCodeVO) {
        return this.selectList("selectHiercodeDetailList", cylHierCodeVO);
    }

    public List<CylHierCodeVO> selectHiercodeSubList(CylHierCodeVO cylHierCodeVO) {
        return this.selectList("selectHiercodeSubList", cylHierCodeVO);
    }

    public int selectHiercodeSubListTotCnt(CylHierCodeVO condHierCodeVO) {
        return this.selectListCount("selectHiercodeSubListTotCnt", condHierCodeVO);
    }
}
