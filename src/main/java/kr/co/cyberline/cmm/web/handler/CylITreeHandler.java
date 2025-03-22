package kr.co.cyberline.cmm.web.handler;

import java.util.List;
import java.util.Map;

/**
 * Created by misoboy on 2015-08-24.
 */
public interface CylITreeHandler {

    List<Map<String, Object>> process(List<Map<String, Object>> param, String selectedId);
}
