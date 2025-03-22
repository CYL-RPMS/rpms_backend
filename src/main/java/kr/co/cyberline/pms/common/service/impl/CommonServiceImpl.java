/**
 * Author    : ktj
 * Date      : 2015. 11. 23
 * Company   : Cyber Line Co., Ltd.
 * Description : 공통 Service 구현체
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.common.service.impl;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import kr.co.cyberline.cmm.util.file.CylFileUtil;
import kr.co.cyberline.cmm.web.file.CylFileService;
import kr.co.cyberline.cmm.web.file.CylFileVO;
import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;
import kr.co.cyberline.cmm.web.util.CylFileUploadUtil;
import kr.co.cyberline.pms.common.service.CommonService;
import kr.co.cyberline.pms.common.service.CommonVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>공통조회 Service 구현체</p>
 *
 * @author ktj
 * @version 1.0
 * @since 2015. 11. 23
 */
@Service("CommonService")
public class CommonServiceImpl implements CommonService {

    @Value("${system.storage.tempPath}")
    private String tempPath;

    @Value("${system.storage.path}")
    private String stroagePath;

    @Value("${system.storage.atch.path}")
    private String atchPath;

    @Resource(name = "commonDAO")
    private CommonDAO commonDAO;

    @Resource(name = "atchFileIdGnrService")
    private EgovIdGnrService egovIdGnrService;

    @Resource(name = "CylFileService")
    private CylFileService cylFileService;

    @Override
    public List<CommonVO> selectInUserList(CommonVO commonVO) {
        return commonDAO.selectInUserList(commonVO);
    }

    @Override
    public List<CommonVO> selectOutUserList(CommonVO commonVO) {
        return commonDAO.selectOutUserList(commonVO);
    }

    @Override
    public List<CommonVO> selectDeptList(CommonVO commonVO) {
        return commonDAO.selectDeptList(commonVO);
    }

    @Override
    public Map selectDeptMap(CommonVO commonVO) {
        return commonDAO.selectDeptMap(commonVO);
    }

    @Override
    public List<CommonVO> selectOrgList(CommonVO commonVO) {
        return commonDAO.selectOrgList(commonVO);
    }

    @Override
    public List<CommonVO> selectEvlMfcmmList(CommonVO commonVO) {
        return commonDAO.selectEvlMfcmmList(commonVO);
    }

    @Override
    public List<CommonVO> selectEvlList(CommonVO commonVO) {
        return commonDAO.selectEvlList(commonVO);
    }

    @Override
    public boolean doFileUpload(HttpServletRequest request, CylFileVO fileVO, String pAtchFileId, String storeFilePath) throws FdlException {

        //업로드 파일이 없으면 리턴
        if (CollectionUtils.isEmpty(fileVO.getAtchFiles())){
            fileVO.setAtch_file_id(pAtchFileId);
            return true;
        }

        // 첨부파일아이디 신규는 생성 기존은 그대로
        if ( pAtchFileId == null || pAtchFileId.equals("")){
            fileVO.setAtch_file_id(egovIdGnrService.getNextStringId());
        } else {
            fileVO.setAtch_file_id(pAtchFileId);
        }

        // 사용여부
        fileVO.setUse_at("Y");

        // 임시 폴더 경로
        String tempFilePath = FilenameUtils.separatorsToSystem(request.getSession().getServletContext().getRealPath(""))
                + tempPath + CylFileUtil.SEPERATOR + request.getRequestedSessionId();

        // 파일 업로드
        List<CylFileVO> uploadFileList = CylFileUploadUtil.copyUploadFiles(fileVO.getAtchFiles(), tempFilePath, storeFilePath, fileVO.getAtch_file_id());

        // 파일속성 등록 - 신규는 생성 기존은 그대로
        if ( pAtchFileId == null || pAtchFileId.equals("") ){
            cylFileService.insertAtchFile(fileVO);
        }

        if (CollectionUtils.isNotEmpty(uploadFileList)){
            for ( CylFileVO vo : uploadFileList ){
                // 파일속성상세 등록
                vo.setAtch_file_id(fileVO.getAtch_file_id());
                vo.setUse_at("Y");
                cylFileService.insertAtchFileDetail(vo);
            }
        }
        return true;
    }

    /*
    * 새로 파일 아이디 생성 후 상세 파일 정보만 등록한다.
    * */
    @Override
    public String doFileCopy(String pAtchFileId) throws FdlException {

        //상세파일 정보 가져오기
        CylFileVO tmpFileVO = new CylFileVO(CylWebDefaultVO.PAGING_ENABLE_OFF, "A.CRTE_DTTM", "DESC");
        tmpFileVO.setAtch_file_id(pAtchFileId);
        tmpFileVO.setUse_at("Y");
        List<CylFileVO> uploadFileList = cylFileService.selectAtchFileList(tmpFileVO);

        //신규파일 생성
        CylFileVO fileVO = new CylFileVO(CylWebDefaultVO.PAGING_ENABLE_OFF, "A.CRTE_DTTM", "DESC");
        fileVO.setAtch_file_id(egovIdGnrService.getNextStringId());
        fileVO.setUse_at("Y");
        // 파일속성 등록
        cylFileService.insertAtchFile(fileVO);

        //파일 상세 정보 등록
        if (CollectionUtils.isNotEmpty(uploadFileList)){
            for ( CylFileVO vo : uploadFileList ){
                // 파일속성상세 등록
                vo.setAtch_file_id(fileVO.getAtch_file_id());
                vo.setUse_at("Y");
                cylFileService.insertAtchFileDetail(vo);
            }
        }

        return fileVO.getAtch_file_id();
    }

    @Override
    public boolean doFileUpload(CylFileVO fileVO, String pAtchFileId) throws FdlException {
        //업로드 파일이 없으면 리턴
        if ( CollectionUtils.isEmpty(fileVO.getAtchFiles())){
            fileVO.setAtch_file_id(pAtchFileId);
            return true;
        }

        // 첨부파일아이디 신규는 생성 기존은 그대로
        if ( pAtchFileId == null || pAtchFileId.equals("")){
            fileVO.setAtch_file_id(egovIdGnrService.getNextStringId());
        } else {
            fileVO.setAtch_file_id(pAtchFileId);
        }

        // 사용여부
        fileVO.setUse_at("Y");

        // 저장 대상 폴더 경로
        String storeFilePath = stroagePath + atchPath + CylFileUtil.SEPERATOR;

        // 임시 폴더 경로
        String tempFilePath = FilenameUtils.separatorsToSystem(fileVO.getReal_path())
                + tempPath + CylFileUtil.SEPERATOR + fileVO.getSession_id();

        // 파일 업로드
        List<CylFileVO> uploadFileList = CylFileUploadUtil.copyUploadFiles(fileVO.getAtchFiles(), tempFilePath, storeFilePath, fileVO.getAtch_file_id());

        // 파일속성 등록 - 신규는 생성 기존은 그대로
        if ( pAtchFileId == null || pAtchFileId.equals("") ){
            cylFileService.insertAtchFile(fileVO);
        }

        if (CollectionUtils.isNotEmpty(uploadFileList)){
            for ( CylFileVO vo : uploadFileList ){
                // 파일속성상세 등록
                vo.setAtch_file_id(fileVO.getAtch_file_id());
                vo.setUse_at("Y");
                cylFileService.insertAtchFileDetail(vo);
            }
        }
        return true;
    }


    // 파일 삭제 ( fileVO : 삭제할 파일 객체, boolean : 파일실제삭제여부, boolean : 사용 여부 수정 )
    /* ex )
     * 파일 상세 목록 실제 파일 삭제 하면서 DB 삭제 하겠다. ( fileVO, true, false )
     * 파일 상세 목록 실제 파일 삭제 하지 않고 DB 삭제 하겠다. ( fileVO, false, false )
     * 파일 상세 목록 실제 파일 삭제 하지 않고 사용하지 않음으로 하겠다. ( fileVO, false, true )
     * 파일 상세 목록 실제 파일 삭제 하면서 사용하지 않음으로 하겠다. ( fileVO, true, true )
     */
    @Override
    public int deleteAtchFiles(String delAtchFileIds){

        List<CylFileVO> delFiles = new ArrayList();

        CylFileVO fileVO = new CylFileVO();
        String[] delAtchFiles = delAtchFileIds.split(",");

        for(int i=0; i < delAtchFiles.length; i++){
            CylFileVO tmpFileVO = new CylFileVO();
            String[] delFile = delAtchFiles[i].split(":");
            tmpFileVO.setAtch_file_id(delFile[0]);
            if(delFile.length > 1) {
                tmpFileVO.setFile_sn(Integer.parseInt(delFile[1]));
            }
            delFiles.add(tmpFileVO);
        }

        fileVO.setAtchFiles(delFiles);

        return cylFileService.deleteAtchFiles(fileVO, false, false);
    }

    @Override
    public int deleteAtchFile(String atch_file_id){
        return cylFileService.deleteAtchFiles(atch_file_id, false, false);
    }

    @Override
    public List<CommonVO> selectRschList(CommonVO commonVO) {
        return commonDAO.selectRschList(commonVO);
    }

    @Override
    public List<CommonVO> selectRstDplctList(CommonVO commonVO) {
        return commonDAO.selectRstDplctList(commonVO);
    }
    @Override
    public List<CommonVO> selectHierCodeList(CommonVO commonVO) {
        return commonDAO.selectHierCodeList(commonVO);
    }
    @Override
    public List<CommonVO> selectPropseList(CommonVO commonVO) {
        return commonDAO.selectPropseList(commonVO);
    }
    @Override
    public List<CommonVO> selectOriPjtList(CommonVO commonVO) {
        return commonDAO.selectOriPjtList(commonVO);
    }

    @Override
    public CommonVO selectRndLinkOdr(CommonVO commonVO) {
        return commonDAO.selectRndLinkOdr(commonVO);
    }

    @Override
    public CommonVO selectTabList(CommonVO commonVO) {
        CommonVO rtnVO = new CommonVO();
        rtnVO.setVoList(commonDAO.selectTab(commonVO));
        return rtnVO;
    }
}
