/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 게시판 관리 Data Access Object
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.bbs.service.impl;

import kr.co.cyberline.cmm.web.dao.CylAbstractDAO;
import kr.co.cyberline.pms.sys.bbs.service.BoardMasterVO;
import kr.co.cyberline.pms.sys.bbs.service.BoardVO;
import kr.co.cyberline.pms.sys.bbs.service.CommentVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>게시판 관리 Data Access Object</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Repository("bbsManageDAO")
public class BBSManageDAO extends CylAbstractDAO {

    @Override
    protected String getNameSpace() {
        return "BBSManage";
    }

    /**
     * <p>게시판 관리 목록 조회</p>
     *
     * @param boardMasterVO
     * @return list
     */
    public List<BoardMasterVO> selectBBSMasterList(BoardMasterVO boardMasterVO) {
        return selectList("selectBBSMasterList", boardMasterVO);
    }

    /**
     * <p>게시판 관리 상세 조회</p>
     *
     * @param boardMasterVO
     * @return boardMasterVO
     */
    public BoardMasterVO selectBBSMasterDetail(BoardMasterVO boardMasterVO) {
        return (BoardMasterVO) select("selectBBSMasterDetail", boardMasterVO);
    }

    /**
     * <p>게시판 관리 등록</p>
     *
     * @param boardMasterVO
     * @return int
     */
    public int insertBBSMaster(BoardMasterVO boardMasterVO) {
        return insert("insertBBSMaster", boardMasterVO);
    }

    /**
     * <p>게시판 관리 수정</p>
     *
     * @param boardMasterVO
     * @return int
     */
    public int updateBBSMaster(BoardMasterVO boardMasterVO) {
        return update("updateBBSMaster", boardMasterVO);
    }

    /**
     * <p>게시판 관리 삭제</p>
     *
     * @param boardMasterVO
     * @return int
     */
    public int deleteBBSMaster(BoardMasterVO boardMasterVO) {
        return delete("deleteBBSMaster", boardMasterVO);
    }

    /**
     * <p>게시물 목록 조회</p>
     *
     * @param boardVO
     * @return list
     */
    public List<BoardVO> selectBBSArticleList(BoardVO boardVO) {
        return selectList("selectBBSArticleList", boardVO);
    }

    /**
     * <p>게시물 상세 조회</p>
     *
     * @param boardVO
     * @return boardVO
     */
    public BoardVO selectBBSArticleDetail(BoardVO boardVO) {
        return (BoardVO) select("selectBBSArticleDetail", boardVO);
    }

    /**
     * <p>게시물 댓글 목록 조회</p>
     *
     * @param commentVO
     * @return list
     */
    public List<CommentVO> selectBBSArticleCommentList(CommentVO commentVO) {
        return selectList("selectBBSArticleCommentList", commentVO);
    }

    /**
     * <p>게시물 댓글 상세 정보 조회</p>
     *
     * @param commentVO
     * @return int
     */
    public CommentVO selectBBSArticleCommentDetail(CommentVO commentVO) {
        return (CommentVO) select("selectBBSArticleCommentDetail", commentVO);
    }

    /**
     * <p>게시물 등록</p>
     *
     * @param boardVO
     * @return int
     */
    public int insertBBSArticle(BoardVO boardVO) {
        return insert("insertBBSArticle", boardVO);
    }

    /**
     * <p>게시물 수정</p>
     *
     * @param boardVO
     * @return int
     */
    public int updateBBSArticle(BoardVO boardVO) {
        return update("updateBBSArticle", boardVO);
    }

    /**
     * <p>게시물 조회수 증가</p>
     *
     * @param boardVO
     * @return int
     */
    public int updateBBSArticleReadCnt(BoardVO boardVO) {
        return update("updateBBSArticleReadCnt", boardVO);
    }

    /**
     * <p>게시물 사용 여부 수정</p>
     *
     * @param boardVO
     * @return int
     */
    public int updateBBSArticleUseAt(BoardVO boardVO) {
        return update("updateBBSArticleUseAt", boardVO);
    }

    /**
     * <p>게시물 댓글 등록</p>
     *
     * @param commentVO
     * @return int
     */
    public int insertBBSArticleComment(CommentVO commentVO) {
        return insert("insertBBSArticleComment", commentVO);
    }

    /**
     * <p>게시물 댓글 수정</p>
     *
     * @param commentVO
     * @return int
     */
    public int updateBBSArticleComment(CommentVO commentVO) {
        return update("updateBBSArticleComment", commentVO);
    }

    /**
     * <p>게시물 댓글 사용여부 수정</p>
     *
     * @param commentVO
     * @return int
     */
    public int updateBBSArticleCommentUseAt(CommentVO commentVO) {
        return update("updateBBSArticleCommentUseAt", commentVO);
    }
}
