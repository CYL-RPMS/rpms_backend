package kr.co.cyberline.service;

import kr.co.cyberline.dao.CylAbstract2DAO;
import kr.co.cyberline.modal.ExcelDVO;
import kr.co.cyberline.modal.ExcelMVO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository("commDAO2")
public class CommDAO2 extends CylAbstract2DAO<Map> {

    @Override
    protected String getNameSpace() {
        return "comm";
    }

    public List commonSelect(Map params) throws SQLException {
        return baseDAO.selectList(params.get("namespace")+"."+params.get("queryid"), params);
    }

    public Map commonSelectOne(Map params) throws SQLException {
        return (Map)baseDAO.get(params.get("namespace")+"."+params.get("queryid"), params);
    }

    public Map commonSelectMap(Map params) throws SQLException {
        return (Map)baseDAO.map(params.get("namespace")+"."+params.get("queryid"), params, (String)params.get("key"));
    }

    public Object commonSelectOj(Map params) throws SQLException {
        return baseDAO.get(params.get("namespace")+"."+params.get("queryid"), params);
    }

    public Object commonInsert(Map params) throws SQLException {
        return baseDAO.insert(params.get("namespace")+"."+params.get("queryid"), params);
    }

    public int commonUpdate(Map params) throws SQLException {
        return baseDAO.update(params.get("namespace")+"."+params.get("queryid"), params);
    }

    public int commonDelete(Map params) throws SQLException {
        return baseDAO.delete(params.get("namespace")+"."+params.get("queryid"), params);
    }

    public ExcelMVO commExcelMstInfo(Map params) throws SQLException {
        return (ExcelMVO)baseDAO.get("Common.commExcelMstInfo", params);
    }

    public List<ExcelDVO> commExcelDtlInfo(Map params) throws SQLException {
        return (List<ExcelDVO>)baseDAO.selectList("Common.commExcelDtlInfo", params);
    }

    public int ckEditorInsert(Map params) throws SQLException {
        return baseDAO.insert("Common.ckEditorInsert", params);
    }
//	public Object commonFileInsert(CommonFileVO fileVO) throws SQLException {
//		return baseDAO.insert("CommonFile.insert", fileVO);
//	}
//	public CommonFileVO commonFileDownload(Map params) throws SQLException {
//		return (CommonFileVO)baseDAO.get("CommonFile.select_file", params);
//	}
//	public Map commonExcelMstInfo(Map params) throws SQLException {
//		return (Map)baseDAO.get("CommonFile.search_excl_mst_info", params);
//	}
//	public List commonExcelDtlInfo(Map params) throws SQLException {
//		return baseDAO.list("CommonFile.search_excl_dtl_info", params);
//	}
}