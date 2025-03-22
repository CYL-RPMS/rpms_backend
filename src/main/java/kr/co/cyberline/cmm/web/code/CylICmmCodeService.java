package kr.co.cyberline.cmm.web.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CylICmmCodeService {
    List<CylCmmCodeVO> retrieveCmmCodeList(CylCmmCodeVO var1);

    List<CylCmmCodeVO> retrieveCmmCodeTreeList(CylCmmCodeVO var1);

    List<CylCmmCodeDetailVO> retrieveCmmCodeDetailList(CylCmmCodeDetailVO var1);

    Map retrieveCmmCodeDetailMap(CylCmmCodeDetailVO var1);

    List<CylCmmCodeDetailVO> selectOperationCodeList(CylCmmCodeVO var1);

    CylCmmCodeVO selectCmmCodeManageDetail(CylCmmCodeVO var1);

    CylCmmCodeDetailVO selectCmmCodeDetailManageDetail(CylCmmCodeDetailVO var1);

    int insertCmmCodeManage(CylCmmCodeVO var1);

    int insertCmmCodeDetailManage(CylCmmCodeDetailVO var1);

    int deleteCmmCodeManage(CylCmmCodeVO var1);

    int deleteCmmCodeDetailManage(CylCmmCodeDetailVO var1);

    int allDeleteCmmCodeDetailManage(CylCmmCodeVO var1);

    int updateCmmCodeManage(CylCmmCodeVO var1);

    int updateCmmCodeDetailManage(CylCmmCodeDetailVO var1);

    List<CylCmmCodeDetailVO> selectCmmCodeManageSearch(HashMap<String, String> var1);
}
