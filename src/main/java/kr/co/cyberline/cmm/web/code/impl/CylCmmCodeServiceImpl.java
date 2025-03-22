package kr.co.cyberline.cmm.web.code.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import kr.co.cyberline.cmm.web.code.CylCmmCodeDetailVO;
import kr.co.cyberline.cmm.web.code.CylCmmCodeVO;
import kr.co.cyberline.cmm.web.code.CylICmmCodeService;
import org.springframework.stereotype.Service;

@Service("cylCmmCodeService")
public class CylCmmCodeServiceImpl implements CylICmmCodeService {
    @Resource(
            name = "cylCmmCodeDAO"
    )
    private CylCmmCodeDAO cylCmmCodeDAO;

    public CylCmmCodeServiceImpl() {
    }

    public List<CylCmmCodeVO> retrieveCmmCodeList(CylCmmCodeVO cmmCodeVO) {
        return this.cylCmmCodeDAO.retrieveCmmCodeList(cmmCodeVO);
    }

    public List<CylCmmCodeVO> retrieveCmmCodeTreeList(CylCmmCodeVO cmmCodeVO) {
        return this.cylCmmCodeDAO.retrieveCmmCodeTreeList(cmmCodeVO);
    }

    public List<CylCmmCodeDetailVO> retrieveCmmCodeDetailList(CylCmmCodeDetailVO cylCmmCodeDetailVO) {
        return this.cylCmmCodeDAO.retrieveCmmCodeDetailList(cylCmmCodeDetailVO);
    }

    public Map retrieveCmmCodeDetailMap(CylCmmCodeDetailVO cylCmmCodeDetailVO) {
        return this.cylCmmCodeDAO.retrieveCmmCodeDetailMap(cylCmmCodeDetailVO);
    }

    public List<CylCmmCodeDetailVO> selectOperationCodeList(CylCmmCodeVO commonCodeVO) {
        return this.cylCmmCodeDAO.selectOperationCodeList(commonCodeVO);
    }

    public int insertCmmCodeManage(CylCmmCodeVO cmmCodeVO) {
        return this.cylCmmCodeDAO.insertCmmCodeManage(cmmCodeVO);
    }

    public CylCmmCodeVO selectCmmCodeManageDetail(CylCmmCodeVO cmmCodeVO) {
        return this.cylCmmCodeDAO.selectCmmCodeManageDetail(cmmCodeVO);
    }

    public int deleteCmmCodeManage(CylCmmCodeVO cmmCodeVO) {
        return this.cylCmmCodeDAO.deleteCmmCodeManage(cmmCodeVO);
    }

    public CylCmmCodeDetailVO selectCmmCodeDetailManageDetail(CylCmmCodeDetailVO cmmCodeDetailVO) {
        return this.cylCmmCodeDAO.selectCmmCodeDetailManageDetail(cmmCodeDetailVO);
    }

    public int insertCmmCodeDetailManage(CylCmmCodeDetailVO cylCmmCodeDetailVO) {
        return this.cylCmmCodeDAO.insertCmmCodeDetailManage(cylCmmCodeDetailVO);
    }

    public int deleteCmmCodeDetailManage(CylCmmCodeDetailVO cylCmmCodeDetailVO) {
        return this.cylCmmCodeDAO.deleteCmmCodeDetailManage(cylCmmCodeDetailVO);
    }

    public int updateCmmCodeManage(CylCmmCodeVO cylCmmCodeVO) {
        return this.cylCmmCodeDAO.updateCmmCodeManage(cylCmmCodeVO);
    }

    public int allDeleteCmmCodeDetailManage(CylCmmCodeVO cmmCodeVO) {
        return this.cylCmmCodeDAO.allDeleteCmmCodeDetailManage(cmmCodeVO);
    }

    public int updateCmmCodeDetailManage(CylCmmCodeDetailVO cylCmmCodeDetailVO) {
        return this.cylCmmCodeDAO.updateCmmCodeDetailManage(cylCmmCodeDetailVO);
    }

    public List<CylCmmCodeDetailVO> selectCmmCodeManageSearch(HashMap<String, String> map) {
        return this.cylCmmCodeDAO.selectCmmCodeManageSearch(map);
    }
}
