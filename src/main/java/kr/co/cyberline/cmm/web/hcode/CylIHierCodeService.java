package kr.co.cyberline.cmm.web.hcode;

import java.sql.SQLException;
import java.util.List;

public interface CylIHierCodeService {
    List<CylHierCodeVO> selectHierGroupCodeList(CylHierCodeVO var1) throws SQLException;

    int selectHierGroupCodeListCnt(CylHierCodeVO var1) throws SQLException;

    CylHierCodeVO selectHierGroupCode(CylHierCodeVO var1) throws SQLException;

    boolean isHierGroupCodeExists(CylHierCodeVO var1) throws SQLException;

    int insertHierGroupCode(CylHierCodeVO var1) throws SQLException;

    int updateHierGroupCode(CylHierCodeVO var1) throws SQLException;

    int deleteHierGroupCode(CylHierCodeVO var1) throws SQLException;

    int truncateHierGroupCode() throws SQLException;

    List<CylHierCodeVO> selectHierDetailCodeList(CylHierCodeVO var1);

    int selectHierDetailCodeListCnt(CylHierCodeVO var1);

    CylHierCodeVO selectHierDetailCode(CylHierCodeVO var1);

    boolean isHierDetailCodeExists(CylHierCodeVO var1);

    int insertHierDetailCode(CylHierCodeVO var1);

    int updateHierDetailCode(CylHierCodeVO var1);

    int deleteHierDetailCode(CylHierCodeVO var1);

    int deleteGroupHierDetailCode(CylHierCodeVO var1) throws SQLException;

    void truncateHierDetailCode() throws SQLException;

    List<CylHierCodeVO> selectHierCodeTreeList(CylHierCodeVO var1) throws SQLException;

    List<CylHierCodeVO> selectHierGroupIdList(CylHierCodeVO var1) throws SQLException;

    boolean isExistHierCode(CylHierCodeVO var1) throws SQLException;

    int deleteHierCode(CylHierCodeVO var1) throws SQLException;

    int deleteHierCodeDetail(CylHierCodeVO var1) throws SQLException;

    int hierCodeSubListUpdateProc(CylHierCodeVO var1, String var2);

    List<CylHierCodeVO> selectOperationHierCodeList(CylHierCodeVO var1);

    List<CylHierCodeVO> selectHierCodeList(CylHierCodeVO var1);
}
