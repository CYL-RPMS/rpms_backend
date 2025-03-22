package kr.co.cyberline.cmm.web.hcode;

import java.util.List;

public interface CylIHierDetailCodeService {
    List<CylHierCodeVO> selectHiercodeDetailList(CylHierCodeVO var1);

    List<CylHierCodeVO> selectHiercodeSubList(CylHierCodeVO var1);

    int selectHiercodeSubListTotCnt(CylHierCodeVO var1);
}
