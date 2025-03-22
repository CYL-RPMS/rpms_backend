/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 게시판 관리 Service Interface
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.bbs.service;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.cyberline.cmm.web.file.CylFileVO;

import java.util.List;

/**
 * <p>게시판 관리 Service Interface</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
public interface BBSManageService {

    /**
     * <p>게시판 관리 목록 조회</p>
     *
     * @param boardMasterVO
     * @return list
     */
    List<BoardMasterVO> selectBBSMasterList(BoardMasterVO boardMasterVO);

    /**
     * <p>게시판 관리 상세 조회</p>
     *
     * @param boardMasterVO
     * @return boardMasterVO
     */
    BoardMasterVO selectBBSMasterDetail(BoardMasterVO boardMasterVO);

    /**
     * <p>게시판 관리 등록</p>
     *
     * @param boardMasterVO
     * @return int
     */
    int insertBBSMaster(BoardMasterVO boardMasterVO);

    /**
     * <p>게시판 관리 수정</p>
     *
     * @param boardMasterVO
     * @return int
     */
    int updateBBSMaster(BoardMasterVO boardMasterVO);

    /**
     * <p>게시판 관리 삭제</p>
     *
     * @param boardMasterVO
     * @return int
     */
    int deleteBBSMaster(BoardMasterVO boardMasterVO);

    /**
     * <p>게시물 목록 조회</p>
     *
     * @param boardVO
     * @return list
     */
    List<BoardVO> selectBBSArticleList(BoardVO boardVO);

    /**
     * <p>게시물의 댓글 목록 조회</p>
     *
     * @param commentVO
     * @return list
     */
    List<CommentVO> selectBBSArticleCommentList(CommentVO commentVO);

    /**
     * <p>게시물의 댓글 상세 조회</p>
     *
     * @param commentVO
     * @return commentVO
     */
    CommentVO selectBBSArticleCommentDetail(CommentVO commentVO);

    /**
     * <p>게시물 상세</p>
     *
     * @param boardVO
     * @return boardVO
     */
    BoardVO selectBBSArticleDetail(BoardVO boardVO);

    /**
     * <p>게시물 등록</p>
     *
     * @param boardVO
     * @return int
     */
    int insertBBSArticle(BoardVO boardVO);

    /**
     * <p>게시물 수정</p>
     *
     * @param boardVO
     * @return
     */
    int updateBBSArticle(BoardVO boardVO);

    /**
     * <p>게시물 조회수 수정</p>
     *
     * @param boardVO
     * @return int
     */
    int updateBBSArticleReadCnt(BoardVO boardVO);

    /**
     * <p>게시물 사용 여부 수정</p>
     *
     * @param boardVO
     * @return boolean
     */
    int updateBBSArticleUseAt(BoardVO boardVO);

    /**
     * <p>게시물 댓글 등록</p>
     *
     * @param commentVO
     * @return int
     */
    int insertBBSArticleComment(CommentVO commentVO);

    /**
     * <p>게시물 댓글 수정</p>
     *
     * @param commentVO
     * @return int
     */
    int updateBBSArticleComment(CommentVO commentVO);

    /**
     * <p>게시물 댓글 사용여부 수정</p>
     *
     * @param commentVO
     * @return int
     */
    int updateBBSArticleCommentUseAt(CommentVO commentVO);

    /**
     * <p>첨부파일 업로드</p>
     *
     * @param request
     * @param fileVO
     * @return boolean
     */
    boolean doBBSArticleFileUpload(HttpServletRequest request, CylFileVO fileVO, boolean mstrRegAt) throws FdlException;
}
