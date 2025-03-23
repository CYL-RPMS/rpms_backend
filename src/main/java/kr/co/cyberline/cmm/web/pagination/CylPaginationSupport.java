package kr.co.cyberline.cmm.web.pagination;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;

public class CylPaginationSupport {
    public static CylPaginationInfoExtension setPaginationVO(HttpServletRequest request, CylWebDefaultVO paramVO) {
        return setPaginationVO(request, paramVO, paramVO.getPagingEnable(), paramVO.getRecordCountPerPage(), paramVO.getPageSize(), paramVO.getCondOrder(), paramVO.getCondAlign());
    }

    public static CylPaginationInfoExtension setPaginationVO(HttpServletRequest request, CylWebDefaultVO paramVO, String pageEnable, int recordCount, int pageSize) {
        return setPaginationVO(request, paramVO, pageEnable, recordCount, pageSize, paramVO.getCondOrder(), paramVO.getCondAlign());
    }

    public static CylPaginationInfoExtension setPaginationVO(HttpServletRequest request, CylWebDefaultVO paramVO, String pageEnable, int recordCount, int pageSize, String condOrder, String condAlign) {
        paramVO.setPagingEnable(CylStringUtil.isNotEmpty(request.getParameter("pagingEnable")) ? paramVO.getPagingEnable() : pageEnable);
        paramVO.setRecordCountPerPage(CylStringUtil.isNotEmpty(request.getParameter("recordCountPerPage")) ? paramVO.getRecordCountPerPage() : recordCount);
        paramVO.setPageSize(CylStringUtil.isNotEmpty(request.getParameter("pageSize")) ? paramVO.getPageSize() : pageSize);
        paramVO.setCondOrder(CylStringUtil.isNotEmpty(request.getParameter("condOrder")) ? paramVO.getCondOrder() : condOrder);
        paramVO.setCondAlign(CylStringUtil.isNotEmpty(request.getParameter("condAlign")) ? paramVO.getCondAlign() : condAlign);
        CylPaginationInfoExtension pagination = new CylPaginationInfoExtension(paramVO);
        pagination.createCustomVO();
        return pagination;
    }

    public static CylPaginationInfoExtension setPaginationMap(Map<String, Object> paramMap) {
        CylPaginationInfoExtension pagination = new CylPaginationInfoExtension(paramMap);
        pagination.createCustomMap();
        return pagination;
    }

    public static CylPaginationInfoExtension setPaginationMap(HttpServletRequest request, Map<String, Object> paramMap, String pageEnable, int recordCount, int pageSize, String condOrder, String condAlign) {
        paramMap.put("pageIndex", CylStringUtil.isNotEmpty(request.getParameter("pageIndex")) ? paramMap.get("pageIndex") : Integer.valueOf(1));
        paramMap.put("pagingEnable", CylStringUtil.isNotEmpty(request.getParameter("pagingEnable")) ? paramMap.get("pagingEnable") : pageEnable);
        paramMap.put("recordCountPerPage", CylStringUtil.isNotEmpty(request.getParameter("recordCountPerPage")) ? paramMap.get("recordCountPerPage") : Integer.valueOf(recordCount));
        paramMap.put("pageSize", CylStringUtil.isNotEmpty(request.getParameter("pageSize")) ? paramMap.get("pageSize") : Integer.valueOf(pageSize));
        paramMap.put("condOrder", CylStringUtil.isNotEmpty(request.getParameter("condOrder")) ? paramMap.get("condOrder") : condOrder);
        paramMap.put("condAlign", CylStringUtil.isNotEmpty(request.getParameter("condAlign")) ? paramMap.get("condAlign") : condAlign);
        CylPaginationInfoExtension pagination = new CylPaginationInfoExtension(paramMap);
        pagination.createCustomMap();
        return pagination;
    }
}
