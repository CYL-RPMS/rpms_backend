package kr.co.cyberline.cmm.web.pagination;

import java.util.List;
import java.util.Map;
import kr.co.cyberline.cmm.util.lang.CylNumberUtil;
import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;

public class CylPaginationInfoExtension<T> extends CylPaginationInfo {
    private CylWebDefaultVO cylWebDefaultVO;

    private Map<String, Object> paramMap;

    private String defaultPageEnable;

    private int defaultRecordCountPerPage;

    private int defaultPageSize;

    public CylPaginationInfoExtension(Object o) {
        this(o, "1", 10, 10);
    }

    public CylPaginationInfoExtension(Map<String, Object> paramMap) {
        this(paramMap, "1", 10, 10);
    }

    public CylPaginationInfoExtension(Object o, String defaultPageEnable, int defaultRecordCountPerPage, int defaultPageSize) {
        this.defaultPageEnable = defaultPageEnable;
        this.defaultRecordCountPerPage = defaultRecordCountPerPage;
        this.defaultPageSize = defaultPageSize;
        this.cylWebDefaultVO = (CylWebDefaultVO)o;
        setCurrentPageNo(this.cylWebDefaultVO.getPageIndex());
        if (this.cylWebDefaultVO.getRecordCountPerPage() <= 0) {
            setRecordCountPerPage(this.defaultRecordCountPerPage);
        } else {
            setRecordCountPerPage(this.cylWebDefaultVO.getRecordCountPerPage());
        }
        if (this.cylWebDefaultVO.getPageSize() <= 0) {
            setPageSize(this.defaultPageSize);
        } else {
            setPageSize(this.cylWebDefaultVO.getPageSize());
        }
    }

    public CylPaginationInfoExtension(Map<String, Object> paramMap, String defaultPageEnable, int defaultRecordCountPerPage, int defaultPageSize) {
        this.defaultPageEnable = defaultPageEnable;
        this.defaultRecordCountPerPage = defaultRecordCountPerPage;
        this.defaultPageSize = defaultPageSize;
        this.paramMap = paramMap;
        setCurrentPageNo(CylNumberUtil.objToInt(paramMap.get("pageIndex")));
        if (CylNumberUtil.objToInt(paramMap.get("recordCountPerPage")) <= 0) {
            setRecordCountPerPage(this.defaultRecordCountPerPage);
        } else {
            setRecordCountPerPage(CylNumberUtil.objToInt(paramMap.get("recordCountPerPage")));
        }
        if (CylNumberUtil.objToInt(paramMap.get("pageSize")) <= 0) {
            setPageSize(this.defaultPageSize);
        } else {
            setPageSize(CylNumberUtil.objToInt(paramMap.get("pageSize")));
        }
    }

    public Object createPaginationInfo() {
        return this;
    }

    public CylWebDefaultVO createCustomVO() {
        this.cylWebDefaultVO.setFirstIndex(getFirstRecordIndex());
        this.cylWebDefaultVO.setLastIndex(getLastRecordIndex());
        if (this.cylWebDefaultVO.getRecordCountPerPage() <= 0)
            this.cylWebDefaultVO.setRecordCountPerPage(this.defaultRecordCountPerPage);
        if (this.cylWebDefaultVO.getPageSize() <= 0)
            this.cylWebDefaultVO.setPageSize(this.defaultPageSize);
        return this.cylWebDefaultVO;
    }

    public Object createCustomMap() {
        this.paramMap.put("firstIndex", Integer.valueOf(getFirstRecordIndex()));
        this.paramMap.put("lastIndex", Integer.valueOf(getLastRecordIndex()));
        if (CylNumberUtil.objToInt(this.paramMap.get("recordCountPerPage")) <= 0)
            this.paramMap.put("recordCountPerPage", Integer.valueOf(this.defaultRecordCountPerPage));
        if (CylNumberUtil.objToInt(this.paramMap.get("pageSize")) <= 0)
            this.paramMap.put("pageSize", Integer.valueOf(this.defaultPageSize));
        return this.paramMap;
    }

    public void setTotalRecordCountVO(List<? extends CylWebDefaultVO> list) {
        int totalCnt = 0;
        if (list != null &&
                list.size() > 0)
            totalCnt = ((CylWebDefaultVO)list.get(0)).getTot();
        setTotalRecordCount(totalCnt);
    }

    public void setTotalRecordCountMap(List<Map<String, Object>> list) {
        int totalCnt = 0;
        if (list != null &&
                list.size() > 0)
            totalCnt = CylNumberUtil.objToInt(((Map)list.get(0)).get("TOT"));
        setTotalRecordCount(totalCnt);
    }
}
