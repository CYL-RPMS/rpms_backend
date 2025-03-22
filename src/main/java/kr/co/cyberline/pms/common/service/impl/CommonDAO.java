/**
 * Author    : ktj
 * Date      : 2015. 11. 23
 * Company   : Cyber Line Co., Ltd.
 * Description : 공통조회 Data Access Object
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.common.service.impl;

import kr.co.cyberline.cmm.web.dao.CylAbstractDAO;
import kr.co.cyberline.pms.common.service.CommonVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>공통조회 DAO</p>
 *
 * @author ktj
 * @since 2015. 11. 23
 * @version 1.0
 */
@Repository("commonDAO")
public class CommonDAO extends CylAbstractDAO {

    @Override
    protected String getNameSpace() {
        return "Common";
    }

    /**
     * <p>내부사용자 목록</p>
     * @param commonVO
     * @return list
     */
    public List<CommonVO> selectInUserList(CommonVO commonVO) {
        return selectList("selectInUserList", commonVO);
    }

    /**
     * <p>외부사용자 목록</p>
     * @param commonVO
     * @return list
     */
    public List<CommonVO> selectOutUserList(CommonVO commonVO) {
        return selectList("selectOutUserList", commonVO);
    }

    /**
     * <p>부서 목록</p>
     * @param commonVO
     * @return list
     */
    public List<CommonVO> selectDeptList(CommonVO commonVO) {
        return selectList("selectDeptList", commonVO);
    }

    /**
     * <p>부서 목록</p>
     * @param commonVO
     * @return list
     */
    public Map selectDeptMap(CommonVO commonVO) {
        return selectMap("selectDeptMap", commonVO, "dept_cd");
    }

    /**
     * <p>기관 목록</p>
     * @param commonVO
     * @return list
     */
    public List<CommonVO> selectOrgList(CommonVO commonVO) {
        return selectList("selectOrgList", commonVO);
    }

    /**
     * <p>평가위원 목록</p>
     * @param commonVO
     * @return list
     */
    public List<CommonVO> selectEvlMfcmmList(CommonVO commonVO) {
        return selectList("selectEvlMfcmmList", commonVO);
    }

    /**
     * <p>평가표 목록</p>
     * @param commonVO
     * @return list
     */
    public List<CommonVO> selectEvlList(CommonVO commonVO) {
        return selectList("selectEvlList", commonVO);
    }

    /**
     * <p>과제 목록</p>
     * @param commonVO
     * @return list
     */
    public List<CommonVO> selectRschList(CommonVO commonVO) {
        return selectList("selectRschList", commonVO);
    }

    /**
     * <p>성과중복 목록</p>
     * @param commonVO
     * @return list
     */
    public List<CommonVO> selectRstDplctList(CommonVO commonVO) {
        return selectList("selectRstDplctList", commonVO);
    }
    /**
     * <p>과제제안 목록</p>
     * @param commonVO
     * @return list
     */
    public List<CommonVO> selectPropseList(CommonVO commonVO) {
        return selectList("selectPropseList", commonVO);
    }
    /**
     * <p>본과제 목록</p>
     * @param commonVO
     * @return list
     */
    public List<CommonVO> selectOriPjtList(CommonVO commonVO) {
        return selectList("selectOriPjtList", commonVO);
    }

    public List<CommonVO> selectTab(CommonVO commonVO) {
        return selectList("selectTab", commonVO);
    }

    public List<CommonVO> selectHierCodeList(CommonVO commonVO) {
        return selectList("selectHierCodeList", commonVO);
    }

    public CommonVO selectRndLinkOdr(CommonVO commonVO) {
        return (CommonVO)select("selectRndLinkOdr", commonVO);
    }
}