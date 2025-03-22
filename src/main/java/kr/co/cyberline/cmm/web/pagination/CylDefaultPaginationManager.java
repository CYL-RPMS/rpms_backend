package kr.co.cyberline.cmm.web.pagination;

import java.util.Map;

public class CylDefaultPaginationManager implements CylIPaginationManager {
    private Map<String, CylIPaginationRenderer> rendererType;

    public void setRendererType(Map<String, CylIPaginationRenderer> rendererType) {
        this.rendererType = rendererType;
    }

    public CylIPaginationRenderer getRendererType(String type) {
        return (this.rendererType != null && this.rendererType.containsKey(type)) ? this.rendererType.get(type) : new CylDefaultPaginationRenderer();
    }
}
