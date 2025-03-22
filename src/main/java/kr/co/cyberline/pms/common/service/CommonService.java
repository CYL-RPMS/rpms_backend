/**
 * Author    : ktj
 * Date      : 2015. 11. 23
 * Company   : Cyber Line Co., Ltd.
 * Description : 공통조회 Service Interface
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.common.service;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.cyberline.cmm.web.file.CylFileVO;

import java.util.List;
import java.util.Map;

/**
 * <p>공통조회 Service Interface</p>
 *
 * @author ktj
 * @since 2015. 11. 23.
 * @version 1.0
 */
public interface CommonService {

    /**
     * <p>내부사용자 목록</p>
     *
     * @param commonVO
     * @return list
     */
    List<CommonVO> selectInUserList(CommonVO commonVO);

    /**
     * <p>외부사용자 목록</p>
     *
     * @param commonVO
     * @return list
     */
    List<CommonVO> selectOutUserList(CommonVO commonVO);

    /**
     * <p>부서 목록</p>
     *
     * @param commonVO
     * @return list
     */
    List<CommonVO> selectDeptList(CommonVO commonVO);

    /**
     * <p>부서 목록 맵으로</p>
     *
     * @param commonVO
     * @return list
     */
    Map selectDeptMap(CommonVO commonVO);

    /**
     * <p>기관 목록</p>
     *
     * @param commonVO
     * @return list
     */
    List<CommonVO> selectOrgList(CommonVO commonVO);
    /**
     * <p>평가위원 목록</p>
     *
     * @param commonVO
     * @return list
     */
    List<CommonVO> selectEvlMfcmmList(CommonVO commonVO);
    /**
     * <p>평가표 목록</p>
     *
     * @param commonVO
     * @return list
     */
    List<CommonVO> selectEvlList(CommonVO commonVO);
    /**
     * <p>과제제안 목록</p>
     *
     * @param commonVO
     * @return list
     */
    List<CommonVO> selectPropseList(CommonVO commonVO);

    /**
     * <p>파일업로드</p>
     * @param pAtchFileId
     * @return
     */
    String doFileCopy(String pAtchFileId) throws FdlException;


    /**
     * <p>파일업로드</p>
     * @param fileVO
     * @param request
     * @param pAtchFileId
     * @param storeFilePath
     * @return
     */
    boolean doFileUpload(HttpServletRequest request, CylFileVO fileVO, String pAtchFileId, String storeFilePath) throws FdlException;

    /**
     * <p>파일업로드</p>
     * @param fileVO
     * @param pAtchFileId
     * @return
     */
    boolean doFileUpload(CylFileVO fileVO, String pAtchFileId) throws FdlException;

    /**
     * <p>파일목록 삭제</p>
     * @param delAtchFileIds
     * @return
     */
    int deleteAtchFiles(String delAtchFileIds);

    /**
     * <p>파일 삭제</p>
     * @param atch_file_id
     * @return
     */
    int deleteAtchFile(String atch_file_id);

    /**
     * <p>과제 목록</p>
     *
     * @param commonVO
     * @return list
     */
    List<CommonVO> selectRschList(CommonVO commonVO);
    /**
     * <p>성과중복 목록</p>
     *
     * @param commonVO
     * @return list
     */
    List<CommonVO> selectRstDplctList(CommonVO commonVO);

    CommonVO selectTabList(CommonVO commonVO);
    /**
     * <p>계층코드 목록</p>
     *
     * @param commonVO
     * @return list
     */
    List<CommonVO> selectHierCodeList(CommonVO commonVO);
    /**
     * <p>본과제 목록</p>
     *
     * @param commonVO
     * @return list
     */
    List<CommonVO> selectOriPjtList(CommonVO commonVO);

    /**
     * <p>연구비 차수</p>
     *
     * @param commonVO
     * @return CommonVO
     */
    CommonVO selectRndLinkOdr(CommonVO commonVO);
}
