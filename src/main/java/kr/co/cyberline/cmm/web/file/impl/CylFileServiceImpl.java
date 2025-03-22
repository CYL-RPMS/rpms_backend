package kr.co.cyberline.cmm.web.file.impl;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import kr.co.cyberline.cmm.util.file.CylFileUtil;
import kr.co.cyberline.cmm.util.lang.CylCollectionUtil;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.file.CylFileService;
import kr.co.cyberline.cmm.web.file.CylFileVO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service(value = "CylFileService")
public class CylFileServiceImpl implements CylFileService {
    @Resource
    private CylFileDAO cylFileDAO;

    public CylFileServiceImpl() {
    }

    public List<CylFileVO> selectAtchFileList(CylFileVO fileVO) {
        return this.cylFileDAO.selectAtchFileList(fileVO);
    }

    public CylFileVO selectAtchFileDetail(CylFileVO fileVO) {
        return this.cylFileDAO.selectAtchFileDetail(fileVO);
    }

    public int insertAtchFile(CylFileVO fileVO) {
        return this.cylFileDAO.insertAtchFile(fileVO);
    }

    public int insertAtchFileDetail(CylFileVO fileVO) {
        return this.cylFileDAO.insertAtchFileDetail(fileVO);
    }

    public int updateAtchFileUseAt(CylFileVO fileVO) {
        return this.cylFileDAO.updateAtchFileUseAt(fileVO);
    }

    public int updateAtchFileDetailUseAt(CylFileVO fileVO) {
        return this.cylFileDAO.updateAtchFileDetailUseAt(fileVO);
    }

    public int deleteAtchFile(CylFileVO fileVO) {
        return this.cylFileDAO.deleteAtchFile(fileVO);
    }

    public int deleteAtchFileDetail(String atchFileId, int fileSn, boolean isDelFile, boolean isUseAt) {
        CylFileVO fileVO = new CylFileVO();
        fileVO.setAtch_file_id(atchFileId);
        fileVO.setFile_sn(fileSn);
        if (isDelFile) {
            CylFileVO detail = this.selectAtchFileDetail(fileVO);
            if (detail != null) {
                CylFileUtil.deleteFile(detail.getFile_stre_cours() + CylFileUtil.SEPERATOR + detail.getStre_file_nm());
            }
        }

        if (isUseAt) {
            fileVO.setUse_at("N");
        }

        return isUseAt ? this.cylFileDAO.updateAtchFileDetailUseAt(fileVO) : this.cylFileDAO.deleteAtchFileDetail(fileVO);
    }

    public int deleteAtchFiles(CylFileVO fileVO, boolean isDelFile, boolean isUseAt) {
        int rs = 0;
        if (!CollectionUtils.isEmpty(fileVO.getAtchFiles())) {
            for(Iterator var5 = fileVO.getAtchFiles().iterator(); var5.hasNext(); rs += this.deleteAtchFileDetail(fileVO.getAtch_file_id(), fileVO.getFile_sn(), isDelFile, isUseAt)) {
                CylFileVO vo = (CylFileVO)var5.next();
                fileVO.setAtch_file_id(vo.getAtch_file_id());
                fileVO.setFile_sn(vo.getFile_sn());
            }

            if (isUseAt) {
                fileVO.setUse_at("N");
                rs += this.updateAtchFileUseAt(fileVO);
            }
        }

        return rs;
    }

    public int deleteAtchFiles(String atchFileId, boolean isDelFile, boolean isUseAt) {
        int rs = 0;
        if (CylStringUtil.isNotEmpty(atchFileId)) {
            CylFileVO cylFileVO = new CylFileVO();
            cylFileVO.setAtch_file_id(atchFileId);
            cylFileVO.setPagingEnable("0");
            List fileList = this.selectAtchFileList(cylFileVO);
            if (CylCollectionUtil.isNotEmpty(fileList)) {
                cylFileVO.setAtchFiles(fileList);
                rs = this.deleteAtchFiles(cylFileVO, isDelFile, isUseAt);
            }
        }

        return rs;
    }
}
