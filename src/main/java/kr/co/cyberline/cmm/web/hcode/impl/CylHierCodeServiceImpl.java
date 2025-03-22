package kr.co.cyberline.cmm.web.hcode.impl;

import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import kr.co.cyberline.cmm.web.hcode.CylHierCodeVO;
import kr.co.cyberline.cmm.web.hcode.CylIHierCodeService;
import org.springframework.stereotype.Service;

@Service("cylHierCodeService")
public class CylHierCodeServiceImpl implements CylIHierCodeService {
    @Resource(
            name = "cylHierCodeDAO"
    )
    private CylHierCodeDAO hierCodeDAO;

    public CylHierCodeServiceImpl() {
    }

    public List<CylHierCodeVO> selectHierGroupCodeList(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.hierCodeDAO.selectHierGroupCodeList(hierCodeVO);
    }

    public int selectHierGroupCodeListCnt(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.hierCodeDAO.selectHierGroupCodeListCnt(hierCodeVO);
    }

    public CylHierCodeVO selectHierGroupCode(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.hierCodeDAO.selectHierGroupCode(hierCodeVO);
    }

    public boolean isHierGroupCodeExists(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.hierCodeDAO.isHierGroupCodeExists(hierCodeVO);
    }

    public int insertHierGroupCode(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.hierCodeDAO.insertHierGroupCode(hierCodeVO);
    }

    public int updateHierGroupCode(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.hierCodeDAO.updateHierGroupCode(hierCodeVO);
    }

    public int deleteHierGroupCode(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.hierCodeDAO.deleteHierGroupCode(hierCodeVO);
    }

    public int truncateHierGroupCode() throws SQLException {
        return this.hierCodeDAO.truncateHierGroupCode();
    }

    public List<CylHierCodeVO> selectHierDetailCodeList(CylHierCodeVO hierCodeVO) {
        return this.hierCodeDAO.selectList(hierCodeVO);
    }

    public int selectHierDetailCodeListCnt(CylHierCodeVO hierCodeVO) {
        return this.hierCodeDAO.selectListCount(hierCodeVO);
    }

    public CylHierCodeVO selectHierDetailCode(CylHierCodeVO hierCodeVO) {
        return (CylHierCodeVO)this.hierCodeDAO.getById(hierCodeVO);
    }

    public boolean isHierDetailCodeExists(CylHierCodeVO hierCodeVO) {
        return this.hierCodeDAO.isExist(hierCodeVO);
    }

    public int insertHierDetailCode(CylHierCodeVO hierCodeVO) {
        return this.hierCodeDAO.insert(hierCodeVO);
    }

    public int updateHierDetailCode(CylHierCodeVO hierCodeVO) {
        return this.hierCodeDAO.update(hierCodeVO);
    }

    public int deleteHierDetailCode(CylHierCodeVO hierCodeVO) {
        return this.hierCodeDAO.delete(hierCodeVO);
    }

    public int deleteGroupHierDetailCode(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.hierCodeDAO.deleteGroupHierDetailCode(hierCodeVO);
    }

    public void truncateHierDetailCode() throws SQLException {
        this.hierCodeDAO.truncateHierDetailCode();
    }

    public List<CylHierCodeVO> selectHierCodeTreeList(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.hierCodeDAO.selectHierCodeTreeList(hierCodeVO);
    }

    public List<CylHierCodeVO> selectHierGroupIdList(CylHierCodeVO hierCodeVO) throws SQLException {
        return this.hierCodeDAO.selectHierGroupIdList(hierCodeVO);
    }

    public boolean isExistHierCode(CylHierCodeVO hierCodeVO) throws SQLException {
        boolean result = false;
        if (hierCodeVO.getGrpCdFlag().equals("1")) {
            result = this.hierCodeDAO.isHierGroupCodeExists(hierCodeVO);
        } else {
            result = this.hierCodeDAO.isExist(hierCodeVO);
        }

        return result;
    }

    public int deleteHierCode(CylHierCodeVO hierCodeVO) throws SQLException {
        int rs = 0;
        if (hierCodeVO.getGrpCdFlag().equals("1")) {
            rs = rs + this.hierCodeDAO.deleteGroupHierDetailCode(hierCodeVO);
            rs += this.hierCodeDAO.deleteHierGroupCode(hierCodeVO);
        } else {
            rs = rs + this.hierCodeDAO.deleteHierCode(hierCodeVO);
        }

        return rs;
    }

    public int deleteHierCodeDetail(CylHierCodeVO hierCodeVO) throws SQLException {
        int rs = 0;
        String[] var3 = hierCodeVO.getHierCodeArr();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String code = var3[var5];
            hierCodeVO.setCode(code);
            rs += this.hierCodeDAO.deleteHierCode(hierCodeVO);
        }

        return rs;
    }

    public int hierCodeSubListUpdateProc(CylHierCodeVO hierCodeVO, String subListArr) {
        int rs = 0;
        String[] arr = subListArr.split(",");
        String[] var5 = arr;
        int var6 = arr.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String hierCodeInfoArr = var5[var7];
            String[] hierCodeInfo = hierCodeInfoArr.split(":");
            hierCodeVO.setCode(hierCodeInfo[0]);
            hierCodeVO.setCode_nm(hierCodeInfo[1]);
            hierCodeVO.setCode_dc(hierCodeInfo[2]);
            hierCodeVO.setCode_sn(hierCodeInfo[3]);
            hierCodeVO.setCode_use_at(hierCodeInfo[4]);
            rs += this.hierCodeDAO.update(hierCodeVO);
        }

        return rs;
    }

    public List<CylHierCodeVO> selectOperationHierCodeList(CylHierCodeVO hierCodeVO) {
        return this.hierCodeDAO.selectOperationHierCodeList(hierCodeVO);
    }

    public List<CylHierCodeVO> selectHierCodeList(CylHierCodeVO hierCodeVO) {
        return this.hierCodeDAO.selectHierCodeList(hierCodeVO);
    }
}
