/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 프로그램관리 Service 구현
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.pgm.service.impl;

import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.web.model.ProgramManageVO;
import kr.co.cyberline.pms.sys.pgm.service.ProgramManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>프로그램 관리 Service 구현</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
@Service("programManageService")
public class ProgramManageServiceImpl implements ProgramManageService {

    @Resource(name = "programManageDAO")
    private ProgramManageDAO programManageDAO;

    @Override
    public List<ProgramManageVO> selectProgramManageList(ProgramManageVO programManageVO) {
        return programManageDAO.selectProgramManageList(programManageVO);
    }

    @Override
    public List<ProgramManageVO> selectMenuProgramList(ProgramManageVO programManageVO) {
        return programManageDAO.selectMenuProgramList(programManageVO);
    }

    @Override
    public List<ProgramManageVO> selectAuthorMenuProgramList(ProgramManageVO programManageVO) {
        return programManageDAO.selectAuthorMenuProgramList(programManageVO);
    }

    @Override
    public List<ProgramManageVO> selectUserAuthorMenuProgramList(UserVO userVO) {
        return programManageDAO.selectUserAuthorMenuProgramList(userVO);
    }

    @Override
    public ProgramManageVO selectProgramManageDetail(ProgramManageVO programManageVO) {
        return programManageDAO.selectProgramManageDetail(programManageVO);
    }

    @Override
    public int insertProgramManage(ProgramManageVO programManageVO) {
        return programManageDAO.insertProgramManage(programManageVO);
    }

    @Override
    public int updateProgramManage(ProgramManageVO programManageVO) {
        return programManageDAO.updateProgramManage(programManageVO);
    }

    @Override
    public int deleteProgramManage(ProgramManageVO programManageVO) {
        return programManageDAO.deleteProgramManage(programManageVO);
    }

    @Override
    public int insertProgramAuthorRelate(ProgramManageVO programManageVO) {
        return programManageDAO.insertProgramAuthorRelate(programManageVO);
    }

    @Override
    public int deleteProgramAuthorRelate(ProgramManageVO programManageVO) {
        return programManageDAO.deleteProgramAuthorRelate(programManageVO);
    }
}
