/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 게시판관리 Service 구현
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.bbs.service.impl;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.cyberline.cmm.util.file.CylFileUtil;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.file.CylFileService;
import kr.co.cyberline.cmm.web.file.CylFileVO;
import kr.co.cyberline.cmm.web.util.CylFileUploadUtil;
import kr.co.cyberline.pms.common.service.CommonService;
import kr.co.cyberline.pms.sys.bbs.service.BBSManageService;
import kr.co.cyberline.pms.sys.bbs.service.BoardMasterVO;
import kr.co.cyberline.pms.sys.bbs.service.BoardVO;
import kr.co.cyberline.pms.sys.bbs.service.CommentVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>게시판 관리 Service 구현</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Service("bbsManageService")
public class BBSManageServiceImpl implements BBSManageService {

    @Value("${system.storage.path}")
    private String stroagePath;

    @Value("${system.storage.atch.path}")
    private String atchPath;

    @Value("${system.storage.tempPath}")
    private String tempPath;

    @Resource(name = "cylFileService")
    private CylFileService fileService;

    @Resource(name = "bbsManageDAO")
    private BBSManageDAO bbsManageDAO;

    @Resource(name = "commonService")
    private CommonService commonService;


    @Override
    public List<BoardMasterVO> selectBBSMasterList(BoardMasterVO boardMasterVO) {
        return bbsManageDAO.selectBBSMasterList(boardMasterVO);
    }

    @Override
    public BoardMasterVO selectBBSMasterDetail(BoardMasterVO boardMasterVO) {
        return bbsManageDAO.selectBBSMasterDetail(boardMasterVO);
    }

    @Override
    public int insertBBSMaster(BoardMasterVO boardMasterVO) {
        return bbsManageDAO.insertBBSMaster(boardMasterVO);
    }

    @Override
    public int updateBBSMaster(BoardMasterVO boardMasterVO) {
        return bbsManageDAO.updateBBSMaster(boardMasterVO);
    }

    @Override
    public int deleteBBSMaster(BoardMasterVO boardMasterVO) {
        return bbsManageDAO.deleteBBSMaster(boardMasterVO);
    }

    @Override
    public List<BoardVO> selectBBSArticleList(BoardVO boardVO) {
        return bbsManageDAO.selectBBSArticleList(boardVO);
    }

    @Override
    public List<CommentVO> selectBBSArticleCommentList(CommentVO commentVO) {
        return bbsManageDAO.selectBBSArticleCommentList(commentVO);
    }

    @Override
    public CommentVO selectBBSArticleCommentDetail(CommentVO commentVO) {
        return bbsManageDAO.selectBBSArticleCommentDetail(commentVO);
    }

    @Override
    public BoardVO selectBBSArticleDetail(BoardVO boardVO) {
        return bbsManageDAO.selectBBSArticleDetail(boardVO);
    }

    @Override
    public int insertBBSArticle(BoardVO boardVO) {
        return bbsManageDAO.insertBBSArticle(boardVO);
    }

    @Override
    public int updateBBSArticle(BoardVO boardVO) {
        return bbsManageDAO.updateBBSArticle(boardVO);
    }

    @Override
    public int updateBBSArticleReadCnt(BoardVO boardVO) {
        return bbsManageDAO.updateBBSArticleReadCnt(boardVO);
    }

    @Override
    public int updateBBSArticleUseAt(BoardVO boardVO) {
        return bbsManageDAO.updateBBSArticleUseAt(boardVO);
    }

    @Override
    public int insertBBSArticleComment(CommentVO commentVO) {
        return bbsManageDAO.insertBBSArticleComment(commentVO);
    }

    @Override
    public int updateBBSArticleComment(CommentVO commentVO) {
        return bbsManageDAO.updateBBSArticleComment(commentVO);
    }

    @Override
    public int updateBBSArticleCommentUseAt(CommentVO commentVO) {
        return bbsManageDAO.updateBBSArticleCommentUseAt(commentVO);
    }

    @Override
    public boolean doBBSArticleFileUpload(HttpServletRequest request, CylFileVO fileVO, boolean mstrRegAt) throws FdlException {
        //파일삭제
        if(StringUtils.isNotEmpty(fileVO.getDelAtchFileIds())){
            int delRs = commonService.deleteAtchFiles(fileVO.getDelAtchFileIds());
        }

        // 임시 폴더 경로
        String tempFilePath = FilenameUtils.separatorsToSystem(request.getSession().getServletContext().getRealPath(""))
                + tempPath + CylFileUtil.SEPERATOR + request.getRequestedSessionId();
        // 저장파일경로
        String storeFilePath = stroagePath + atchPath;

        int rs = 0;
        if ( fileVO != null ){
            // 파일 업로드
            List<CylFileVO> uploadFileList = CylFileUploadUtil.copyUploadFiles(fileVO.getAtchFiles(), CylStringUtil.nvl(tempFilePath), CylStringUtil.nvl(storeFilePath), fileVO.getAtch_file_id());

            // 파일속성 등록
            if ( mstrRegAt ){
                rs = fileService.insertAtchFile(fileVO);
            }

            if ( CollectionUtils.isNotEmpty(uploadFileList) ){
                for ( CylFileVO vo : uploadFileList ){
                    // 파일속성상세 등록
                    vo.setAtch_file_id(fileVO.getAtch_file_id());
                    vo.setUse_at(fileVO.getUse_at());
                    rs += fileService.insertAtchFileDetail(vo);
                }
            }
        }
        return rs > 0;
    }
}