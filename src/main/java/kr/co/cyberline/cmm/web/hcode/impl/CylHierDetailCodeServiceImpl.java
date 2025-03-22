package kr.co.cyberline.cmm.web.hcode.impl;

import java.util.List;
import javax.annotation.Resource;
import kr.co.cyberline.cmm.web.hcode.CylHierCodeVO;
import kr.co.cyberline.cmm.web.hcode.CylIHierDetailCodeService;
import org.springframework.stereotype.Service;

@Service("cylHierDetailCodeService")
public class CylHierDetailCodeServiceImpl implements CylIHierDetailCodeService {
    @Resource(
            name = "cylHierDetailCodeDAO"
    )
    CylHierDetailCodeDAO hierDetailCodeDAO;

    public CylHierDetailCodeServiceImpl() {
    }

    public List<CylHierCodeVO> selectHiercodeDetailList(CylHierCodeVO cylHierCodeVO) {
        return this.hierDetailCodeDAO.selectHiercodeDetailList(cylHierCodeVO);
    }

    public List<CylHierCodeVO> selectHiercodeSubList(CylHierCodeVO cylHierCodeVO) {
        return this.hierDetailCodeDAO.selectHiercodeSubList(cylHierCodeVO);
    }

    public int selectHiercodeSubListTotCnt(CylHierCodeVO condHierCodeVO) {
        return this.hierDetailCodeDAO.selectHiercodeSubListTotCnt(condHierCodeVO);
    }
}
