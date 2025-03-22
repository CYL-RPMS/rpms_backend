/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 메뉴 Cache Map
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.sys.mnu.service;

import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.model.MenuManageVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>메뉴 Cache Map</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
public class MenuMap {

    private static final MenuMap menuMap = new MenuMap();
    private static final Map<String, List<MenuManageVO>> tempMap = new HashMap<String, List<MenuManageVO>>();

    private MenuMap(){
    }

    public static MenuMap getInstance(){
        return menuMap;
    }

    public List<MenuManageVO> getMenuList(String key){
        return tempMap.get(key);
    }

    /**
     * 전체 메뉴 Dept Level 와 숨김 여부에 따른 메뉴 목록을 조회한다.
     * @author Chi Seok Song
     * @param lvl : 시작 dept 레벨
     * @param hide_at : 숨김여부
     * @return
     */
    public List<Map<String, Object>> getMenuList(String key, int lvl, String hide_at){
        List<Map<String, Object>> list = null;
        if ( tempMap.containsKey(key) && tempMap.get(key) != null ){
            list = new ArrayList<Map<String, Object>>();
            for ( MenuManageVO vo :  tempMap.get(key) ){
                if ( vo.getLvl() == lvl && vo.getHide_at().equals(hide_at)){
                    Map<String, Object> menuMap = new HashMap<String, Object>();
                    menuMap.put("menu_id", vo.getMenu_id());
                    menuMap.put("upr_menu_id", vo.getUpr_menu_id());
                    menuMap.put("menu_nm_kr", vo.getMenu_nm_kr());
                    menuMap.put("menu_url", vo.getMenu_url());
                    menuMap.put("subMenuList", getSubMenuList(key, vo.getMenu_id(), hide_at,vo.getMenu_se()));
                    menuMap.put("menu_se", vo.getMenu_se());
                    list.add(menuMap);
                }
            }
            destroy(key);
        }
        return list;
    }

    /**
     * 해당 메뉴 ID에 대한 하위 메뉴 와 숨김 여부에 따른 메뉴 목록을 조회한다.
     * @author Chi Seok Song
     * @param menu_id : 메뉴 ID
     * @param hide_at : 숨김여부
     * @return
     */
    public List<Map<String, Object>> getMenuList(String key, String menu_id, String hide_at){
        List<Map<String, Object>> list = null;
        if ( tempMap.containsKey(key) && tempMap.get(key) != null ){
            list = new ArrayList<Map<String, Object>>();
            for ( MenuManageVO vo : tempMap.get(key) ){
                if ( CylStringUtil.nvl(vo.getUpr_menu_id()).equals(menu_id) && vo.getHide_at().equals(hide_at)){
                    Map<String, Object> menuMap = new HashMap<String, Object>();
                    menuMap.put("menu_id", vo.getMenu_id());
                    menuMap.put("upr_menu_id", vo.getUpr_menu_id());
                    menuMap.put("menu_nm_kr", vo.getMenu_nm_kr());
                    menuMap.put("menu_url", vo.getMenu_url());
                    menuMap.put("subMenuList", getSubMenuList(key, vo.getMenu_id(),vo.getMenu_se()));
                    menuMap.put("menu_se", vo.getMenu_se());
                    list.add(menuMap);
                }
            }
            destroy(key);
        }
        return list;
    }

    public void setMenuList(String key, List<MenuManageVO> list){
        tempMap.put(key, list);
    }

    /**
     * Menu ID 에 대한 하위메뉴 목록 조회
     * @param menu_id
     * @return
     */
    private List<Map<String, Object>> getSubMenuList(String key, String menu_id, String menu_se) {
        List<Map<String, Object>> subMenuList = null;
        if ( tempMap.containsKey(key) && tempMap.get(key) != null ){
            subMenuList = new ArrayList<Map<String, Object>>();
            findSubMenu(key, subMenuList, menu_id, "",menu_se);
        }
        return subMenuList;
    }

    /**
     * Menu ID 와 숨김여부에 대한 하위메뉴 목록 조회
     * @param menu_id
     * @param hide_at
     * @return
     */
    private List<Map<String, Object>> getSubMenuList(String key, String menu_id, String hide_at, String menu_se) {
        List<Map<String, Object>> subMenuList = null;
        if ( tempMap.containsKey(key) && tempMap.get(key) != null ){
            subMenuList = new ArrayList<Map<String, Object>>();
            findSubMenu(key, subMenuList, menu_id, hide_at,menu_se);
        }
        return subMenuList;
    }

    /**
     * Menu ID 에 대한 하위 메뉴 재귀 함수
     * @author Chi Seok Song
     * @param paramList
     * @param menu_id
     * @param hide_at
     * @return
     */
    private List<Map<String, Object>> findSubMenu(String key, List<Map<String, Object>> paramList, String menu_id, String hide_at, String menu_se){
        if(paramList == null) {
            return null;
        }
        if (tempMap.containsKey(key) && tempMap.get(key) != null) {
            for (MenuManageVO vo : tempMap.get(key)) {
                if ("".equals(hide_at)) {
                    if (menu_id.equals(CylStringUtil.nvl(vo.getUpr_menu_id()))) {
                        Map<String, Object> menuMap = new HashMap<String, Object>();
                        menuMap.put("menu_id", vo.getMenu_id());
                        menuMap.put("upr_menu_id", vo.getUpr_menu_id());
                        menuMap.put("menu_nm_kr", vo.getMenu_nm_kr());
                        menuMap.put("menu_url", vo.getMenu_url());
                        menuMap.put("subMenuList", findSubMenu(key, new ArrayList<Map<String, Object>>(), vo.getMenu_id(), hide_at,vo.getMenu_se()));
                        menuMap.put("menu_se", menu_se);
                        paramList.add(menuMap);
                    }
                } else {
                    if (menu_id.equals(CylStringUtil.nvl(vo.getUpr_menu_id())) && vo.getHide_at().equals(hide_at)) {
                        Map<String, Object> menuMap = new HashMap<String, Object>();
                        menuMap.put("menu_id", vo.getMenu_id());
                        menuMap.put("upr_menu_id", vo.getUpr_menu_id());
                        menuMap.put("menu_nm_kr", vo.getMenu_nm_kr());
                        menuMap.put("menu_url", vo.getMenu_url());
                        menuMap.put("subMenuList", findSubMenu(key, new ArrayList<Map<String, Object>>(), vo.getMenu_id(), hide_at,vo.getMenu_se()));
                        menuMap.put("menu_se", menu_se);
                        paramList.add(menuMap);
                    }
                }
            }
        }
        return paramList;
    }

    /**
     * 데이터 삭제
     * @param key
     */
    private void destroy(String key){
        if ( tempMap.containsKey(key) && tempMap.get(key) != null ){
            tempMap.remove(key);
        }
    }
}
