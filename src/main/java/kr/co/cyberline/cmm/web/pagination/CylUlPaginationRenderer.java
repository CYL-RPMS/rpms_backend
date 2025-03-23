package kr.co.cyberline.cmm.web.pagination;

import jakarta.servlet.ServletContext;

public class CylUlPaginationRenderer extends CylAbstractUlPaginationRenderer {
    private ServletContext servletContext;

    public String renderPagination(CylPaginationInfo paginationInfo, String jsFunction) {
        return super.renderPagination(paginationInfo, jsFunction);
    }
}
