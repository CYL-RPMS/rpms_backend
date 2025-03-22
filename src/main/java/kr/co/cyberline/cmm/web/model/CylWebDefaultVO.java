package kr.co.cyberline.cmm.web.model;

import java.io.Serializable;
import kr.co.cyberline.cmm.web.annotation.CylKeepCondition;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CylWebDefaultVO implements Serializable {
    public static final String PAGING_ENABLE_ON = "1";

    public static final String PAGING_ENABLE_OFF = "0";

    public static final String MODE_UPDATE = "update";

    public static final String MODE_CREATE = "create";

    public static final String[] PJT_INFO = new String[] { "과제정보", "pjtinfo" };

    public static final String[] PRTCP = new String[] { "참여인력", "prtcp" };

    public static final String[] PCOI = new String[] { "투자현황", "pcoi" };

    public static final String[] RSLT = new String[] { "성과", "rslt" };

    public static final String[] PJT = new String[] { "과제별", "pjt" };

    public static final String[] PROG = new String[] { "세부사업별", "prog" };

    public static final String[] ORG = new String[] { "기관별", "org" };

    public static final String[] MAJOR = new String[] { "전공별", "major" };

    public static final String[] TYPE = new String[] { "분류체계벼", "type" };

    public static final String[] PHASE = new String[] { "연구단계별", "phase" };

    public static final String[] TOTAL = new String[] { "총과", "total" };

    public static final String[] CLSYS = new String[] { "분류체계별", "clsys" };

    public CylWebDefaultVO() {}

    public CylWebDefaultVO(String pagingEnable, String condOrder, String condAlign) {
        this.pagingEnable = pagingEnable;
        this.condOrder = condOrder;
        this.condAlign = condAlign;
    }

    @CylKeepCondition
    private int pageIndex = 1;

    @CylKeepCondition
    private int pageUnit = 10;

    private int pageSize = 10;

    @CylKeepCondition
    private int firstIndex = 1;

    @CylKeepCondition
    private int lastIndex = 1;

    private int recordCountPerPage = 10;

    @CylKeepCondition
    private String pagingEnable = "1";

    @CylKeepCondition
    private String condOrder = "";

    @CylKeepCondition
    private String condAlign = "";

    private String jsessionId;

    private String mode;

    private String menuNo;

    private String upperMenuNo;

    private String customView;

    private String authUserId;

    private int tot;

    private String ord_ex;

    private String contextPath;

    private int rnum;

    public String getMenuNo() {
        return this.menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    public String getUpperMenuNo() {
        return this.upperMenuNo;
    }

    public void setUpperMenuNo(String upperMenuNo) {
        this.upperMenuNo = upperMenuNo;
    }

    public int getFirstIndex() {
        return this.firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getLastIndex() {
        return this.lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public int getRecordCountPerPage() {
        return this.recordCountPerPage;
    }

    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return this.pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getPagingEnable() {
        return this.pagingEnable;
    }

    public void setPagingEnable(String pagingEnable) {
        this.pagingEnable = pagingEnable;
    }

    public String getCondOrder() {
        return this.condOrder;
    }

    public void setCondOrder(String condOrder) {
        this.condOrder = condOrder;
    }

    public String getCondAlign() {
        return this.condAlign;
    }

    public void setCondAlign(String condAlign) {
        this.condAlign = condAlign;
    }

    public String getJsessionId() {
        return this.jsessionId;
    }

    public void setJsessionId(String jsessionId) {
        this.jsessionId = jsessionId;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCustomView() {
        return this.customView;
    }

    public void setCustomView(String customView) {
        this.customView = customView;
    }

    public int getTot() {
        return this.tot;
    }

    public void setTot(int tot) {
        this.tot = tot;
    }

    public int getRnum() {
        return this.rnum;
    }

    public void setRnum(int rnum) {
        this.rnum = rnum;
    }

    public String getOrd_ex() {
        return this.ord_ex;
    }

    public void setOrd_ex(String ord_ex) {
        this.ord_ex = ord_ex;
    }

    public String getAuthUserId() {
        return this.authUserId;
    }

    public void setAuthUserId(String authUserId) {
        this.authUserId = authUserId;
    }

    public String getContextPath() {
        return this.contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
