/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 프로그램관리 Data Access Object
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.pgm.service.impl;

import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.web.dao.CylAbstractDAO;
import kr.co.cyberline.cmm.web.model.MenuManageVO;
import kr.co.cyberline.cmm.web.model.ProgramManageVO;
import kr.co.cyberline.pms.sys.mnu.service.impl.MenuManageDAO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>프로그램 관리 Data Access Object</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Repository("programManageDAO")
public class ProgramManageDAO extends CylAbstractDAO<ProgramManageVO> {

    @Resource(name = "menuManageDAO")
    private MenuManageDAO menuManageDAO;

    @Override
    protected String getNameSpace() {
        return "ProgramManage";
    }

    /**
     * <p>프로그램관리 목록 조회</p>
     *
     * @param programManageVO
     * @return list
     */
    public List<ProgramManageVO> selectProgramManageList(ProgramManageVO programManageVO) {
        return selectList("selectProgramManageList", programManageVO);
    }

    /**
     * <p>메뉴에 대한 프로그램 목록 조회</p>
     *
     * @param programManageVO
     * @return list
     */
    public List<ProgramManageVO> selectMenuProgramList(ProgramManageVO programManageVO) {
        return selectList("selectMenuProgramList", programManageVO);
    }

    /**
     * <p>권한 및 메뉴에 대한 프로그램 목록 조회</p>
     *
     * @param programManageVO
     * @return list
     */
    public List<ProgramManageVO> selectAuthorMenuProgramList(ProgramManageVO programManageVO) {
        return selectList("selectAuthorMenuProgramList", programManageVO);
    }

    /**
     * <p>사용자 권한에 해당하는 메뉴의 프로그램 목록 조회</p>
     *
     * @param userVO
     * @return programManageVO
     */
    public List<ProgramManageVO> selectUserAuthorMenuProgramList(UserVO userVO) {
        return selectList("selectUserAuthorMenuProgramList", userVO);
    }

    /**
     * <p>프로그램관리 상세 조회</p>
     *
     * @param programManageVO
     * @return programManageVO
     */
    public ProgramManageVO selectProgramManageDetail(ProgramManageVO programManageVO) {
        return select("selectProgramManageDetail", programManageVO);
    }

    /**
     * <p>프로그램관리 등록</p>
     *
     * @param programManageVO
     * @return int
     */
    public int insertProgramManage(ProgramManageVO programManageVO) {
        // 프로그램 등록
        return insert("insertProgramManage", programManageVO);
    }

    /**
     * <p>프로그램관리 수정</p>
     *
     * @param programManageVO
     * @return int
     */
    public int updateProgramManage(ProgramManageVO programManageVO) {
        return update("updateProgramManage", programManageVO);
    }

    /**
     * <p>프로그램관리 삭제</p>
     *
     * @param programManageVO
     * @return int
     */
    public int deleteProgramManage(ProgramManageVO programManageVO) {
        if ( programManageVO != null ){
            MenuManageVO menuManageVO = new MenuManageVO();
            menuManageVO.setPrgm_id(programManageVO.getPrgm_id());
            return deleteProgramAuthorRelate(programManageVO) +
                    menuManageDAO.deleteMenuProgramRelate(menuManageVO) +
                    delete("deleteProgramManage", programManageVO);
        }
        return 0;
    }

    /**
     * <p>권한별 메뉴의 프로그램 등록</p>
     *
     * @param programManageVO
     * @return int
     */
    public int insertProgramAuthorRelate(ProgramManageVO programManageVO) {
        return insert("insertProgramAuthorRelate", programManageVO);
    }

    /**
     * <p>권한별 메뉴의 프로그램 삭제</p>
     *
     * @param programManageVO
     * @return int
     */
    public int deleteProgramAuthorRelate(ProgramManageVO programManageVO) {
        return delete("deleteProgramAuthorRelate", programManageVO);
    }
}
