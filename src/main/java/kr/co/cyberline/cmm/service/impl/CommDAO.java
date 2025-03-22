package kr.co.cyberline.cmm.service.impl;

import kr.co.cyberline.cmm.model.ExcelDVO;
import kr.co.cyberline.cmm.model.ExcelMVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository("commDAO")
public class CommDAO{

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	@Qualifier(value = "sqlSessionTemplate2")
	private SqlSession sqlSession2;

	@Autowired
	@Qualifier(value = "sqlSessionTemplate3")
	private SqlSession sqlSession3;

	public List commonSelect(Map params) throws SQLException {
		if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "2")) {
			return sqlSession2.selectList(params.get("namespace")+"."+params.get("queryid"), params);
		} else if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "3")) {
			return sqlSession3.selectList(params.get("namespace")+"."+params.get("queryid"), params);
		} else {
			return sqlSession.selectList(params.get("namespace")+"."+params.get("queryid"), params);
		}
	}

	public Map commonSelectOne(Map params) throws SQLException {
		if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "2")) {
			return sqlSession2.selectOne(params.get("namespace")+"."+params.get("queryid"), params);
		} else if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "3")) {
			return sqlSession3.selectOne(params.get("namespace")+"."+params.get("queryid"), params);
		} else {
			return sqlSession.selectOne(params.get("namespace")+"."+params.get("queryid"), params);
		}

	}

	public Map commonSelectMap(Map params) throws SQLException {
		if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "2")) {
			return sqlSession2.selectMap(params.get("namespace")+"."+params.get("queryid"), params, (String)params.get("key"));
		} else if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "3")) {
			return sqlSession3.selectMap(params.get("namespace")+"."+params.get("queryid"), params, (String)params.get("key"));
		} else {
			return sqlSession.selectMap(params.get("namespace")+"."+params.get("queryid"), params, (String)params.get("key"));
		}

	}
	
	public Object commonInsert(Map params) throws SQLException {
		if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "2")) {
			return sqlSession2.insert(params.get("namespace")+"."+params.get("queryid"), params);
		} else if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "3")) {
			return sqlSession3.insert(params.get("namespace")+"."+params.get("queryid"), params);
		} else {
			return sqlSession.insert(params.get("namespace")+"."+params.get("queryid"), params);
		}

	}

	public int commonUpdate(Map params) throws SQLException {
		if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "2")) {
			return sqlSession2.update(params.get("namespace")+"."+params.get("queryid"), params);
		} else if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "3")) {
			return sqlSession3.update(params.get("namespace")+"."+params.get("queryid"), params);
		} else {
			return sqlSession.update(params.get("namespace")+"."+params.get("queryid"), params);
		}

	}

	public int commonDelete(Map params) throws SQLException {
		if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "2")) {
			return sqlSession2.delete(params.get("namespace")+"."+params.get("queryid"), params);
		} else if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "3")) {
			return sqlSession3.delete(params.get("namespace")+"."+params.get("queryid"), params);
		} else {
			return sqlSession.delete(params.get("namespace")+"."+params.get("queryid"), params);
		}

	}

	public ExcelMVO commExcelMstInfo(Map params) throws SQLException {
		if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "2")) {
			return (ExcelMVO)sqlSession2.selectOne("Common.commExcelMstInfo", params);
		} else if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "3")) {
			return (ExcelMVO)sqlSession3.selectOne("Common.commExcelMstInfo", params);
		} else {
			return (ExcelMVO)sqlSession.selectOne("Common.commExcelMstInfo", params);
		}

	}

	public List<ExcelDVO> commExcelDtlInfo(Map params) throws SQLException {
		if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "2")) {
			return sqlSession2.selectList("Common.commExcelDtlInfo", params);
		} else if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "3")) {
			return sqlSession3.selectList("Common.commExcelDtlInfo", params);
		} else {
			return sqlSession.selectList("Common.commExcelDtlInfo", params);
		}

	}

	public int ckEditorInsert(Map params) throws SQLException {
		if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "2")) {
			return sqlSession2.insert("Common.ckEditorInsert", params);
		} else if(params.get("dbType") != null && StringUtils.equals((String)params.get("dbType"), "3")) {
			return sqlSession3.insert("Common.ckEditorInsert", params);
		} else {
			return sqlSession.insert("Common.ckEditorInsert", params);
		}
	}
}