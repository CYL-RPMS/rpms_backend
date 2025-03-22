/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 프로그램 관리 Service Interface
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.pgm.service;

import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.web.model.ProgramManageVO;

import java.util.List;

/**
 * <p>프로그램 관리 Service Interface</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
public interface ProgramManageService {

    /**
     * <p>프로그램관리 목록 조회</p>
     *
     * @param programManageVO
     * @return list
     */
    List<ProgramManageVO> selectProgramManageList(ProgramManageVO programManageVO);

    /**
     * <p>메뉴에 대한 프로그램 목록 조회</p>
     *
     * @param programManageVO
     * @return list
     */
    List<ProgramManageVO> selectMenuProgramList(ProgramManageVO programManageVO);

    /**
     * <p>권한에 대한 메뉴의 프로그램 목록 조회</p>
     *
     * @param programManageVO
     * @return list
     */
    List<ProgramManageVO> selectAuthorMenuProgramList(ProgramManageVO programManageVO);

    /**
     * <p>사용자 권한에 해당하는 메뉴의 프로그램 목록 조회</p>
     *
     * @param userVO
     * @return list
     */
    List<ProgramManageVO> selectUserAuthorMenuProgramList(UserVO userVO);

    /**
     * <p>프로그램관리 상세 조회</p>
     *
     * @param programManageVO
     * @return programManageVO
     */
    ProgramManageVO selectProgramManageDetail(ProgramManageVO programManageVO);

    /**
     * <p>프로그램관리 등록</p>
     *
     * @param programManageVO
     * @return int
     */
    int insertProgramManage(ProgramManageVO programManageVO);

    /**
     * <p>프로그램관리 수정</p>
     *
     * @param programManageVO
     * @return int
     */
    int updateProgramManage(ProgramManageVO programManageVO);

    /**
     * <p>프로그램관리 삭제</p>
     *
     * @param programManageVO
     * @return int
     */
    int deleteProgramManage(ProgramManageVO programManageVO);

    /**
     * <p>프로그램 권한 관계 등록</p>
     *
     * @param programManageVO
     * @return int
     */
    int insertProgramAuthorRelate(ProgramManageVO programManageVO);

    /**
     * <p>프로그램 권한 관계 삭제</p>
     *
     * @param programManageVO
     * @return int
     */
    int deleteProgramAuthorRelate(ProgramManageVO programManageVO);
}
