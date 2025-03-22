
package kr.co.cyberline.cmm.web.handler.impl;

import kr.co.cyberline.cmm.web.handler.CylITreeHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CylJsTreeJsonHandlerImpl implements CylITreeHandler {

	@Override
	public List<Map<String, Object>> process(List<Map<String, Object>> param, String selectedId) {
		int length = param.size();

		List<Map<String, Object>> jstreeList = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < length; i++) {
			Map<String, Object> map = param.get(i);

			Map<String, Object> dataMap = new HashMap<String, Object>();

			Map<String, Object> subDataMap = new HashMap<String, Object>();
			Map<String, Object> subDataAttrMap = new HashMap<String, Object>(); // a link 관려 
			subDataMap.put("title", map.get("title"));
			subDataMap.put("attr", subDataAttrMap);

			String id = (String) map.get("id");
			
			if (map.get("id") != null) {
				subDataAttrMap.put("id", map.get("id"));
			}

			if (map.get("href") != null) {
				subDataAttrMap.put("href", map.get("href"));
			}

			if (map.get("target") != null) {
				subDataAttrMap.put("target", map.get("target"));
			}

			subDataAttrMap.put("title", map.get("title"));

			dataMap.put("data", subDataMap);

			Map<String, Object> attrMap = new HashMap<String, Object>();
			attrMap.put("id", id);

			attrMap.put("title", map.get("title"));

			if (map.get("msgId") != null) {
				attrMap.put("data-msgid", map.get("msgId"));
			}

			if (map.get("rel") != null) {
				attrMap.put("rel", map.get("rel"));
			}

			dataMap.put("attr", attrMap);

			if (map.get("state") != null) {
				dataMap.put("state", map.get("state"));
			} else {
				dataMap.put("state", "closed");
			}

			dataMap.put("children", new ArrayList(0));
			dataMap.put("metadata", map.get("metadata"));

			jstreeList.add(dataMap);
		}

		return jstreeList;
	}

}
