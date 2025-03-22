package kr.co.cyberline.cmm.web.pagination;

import java.text.MessageFormat;

public abstract class CylAbstractUlPaginationRenderer implements CylIPaginationRenderer {
    public String firstPageLabel;

    public String previousPageLabel;

    public String currentPageLabel;

    public String otherPageLabel;

    public String nextPageLabel;

    public String lastPageLabel;

    public String startCssPageLabel;

    public String lastCssPageLabel;

    public String renderPagination(CylPaginationInfo paginationInfo, String jsFunction) {
        StringBuffer strBuff = new StringBuffer();
        int firstPageNo = paginationInfo.getFirstPageNo();
        int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
        int totalPageCount = paginationInfo.getTotalPageCount();
        int pageSize = paginationInfo.getPageSize();
        int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
        int currentPageNo = paginationInfo.getCurrentPageNo();
        int lastPageNo = paginationInfo.getLastPageNo();
        String url = paginationInfo.getUrl();
        strBuff.append(this.startCssPageLabel);
        if (totalPageCount > pageSize)
            if (firstPageNoOnPageList > pageSize) {
                strBuff.append(MessageFormat.format(this.firstPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo), url }));
                strBuff.append(MessageFormat.format(this.previousPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList - 1), url }));
            } else {
                strBuff.append(MessageFormat.format(this.firstPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo), url }));
                strBuff.append(MessageFormat.format(this.previousPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo), url }));
            }
        for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
            if (i == currentPageNo) {
                strBuff.append(MessageFormat.format(this.currentPageLabel, new Object[] { Integer.toString(i) }));
            } else {
                strBuff.append(MessageFormat.format(this.otherPageLabel, new Object[] { jsFunction, Integer.toString(i), Integer.toString(i) }));
            }
        }
        if (totalPageCount > pageSize)
            if (lastPageNoOnPageList < totalPageCount) {
                strBuff.append(MessageFormat.format(this.nextPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList + pageSize), url }));
                strBuff.append(MessageFormat.format(this.lastPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo), url }));
            } else {
                strBuff.append(MessageFormat.format(this.nextPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo), url }));
                strBuff.append(MessageFormat.format(this.lastPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo), url }));
            }
        strBuff.append(this.lastCssPageLabel);
        return strBuff.toString();
    }
}
