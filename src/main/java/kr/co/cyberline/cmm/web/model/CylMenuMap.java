package kr.co.cyberline.cmm.web.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;

public class CylMenuMap {
    private static final CylMenuMap menuMap = new CylMenuMap();

    private static final Map<String, List<MenuManageVO>> tempMap = new HashMap<>();

    private static final Map<String, List<ProgramManageVO>> prgmMap = new HashMap<>();

    public static CylMenuMap getInstance() {
        return menuMap;
    }

    public List<MenuManageVO> getMenuList(String key) {
        return tempMap.get(key);
    }

    public List<ProgramManageVO> getPrgmList(String key) {
        return prgmMap.get(key);
    }

    public List<Map<String, Object>> getMenuList(String key, int lvl, String hide_at) {
        List<Map<String, Object>> list = null;
        if (tempMap.containsKey(key) && tempMap.get(key) != null) {
            list = new ArrayList<>();
            for (MenuManageVO vo : tempMap.get(key)) {
                if (vo.getLvl() == lvl && vo.getHide_at().equals(hide_at)) {
                    Map<String, Object> menuMap = new HashMap<>();
                    menuMap.put("menu_id", vo.getMenu_id());
                    menuMap.put("upr_menu_id", vo.getUpr_menu_id());
                    menuMap.put("menu_nm_kr", vo.getMenu_nm_kr());
                    menuMap.put("menu_url", vo.getMenu_url());
                    menuMap.put("hide_at", vo.getHide_at());
                    menuMap.put("menu_path", vo.getMenu_path());
                    menuMap.put("author_slct1", vo.getAuthor_slct1());
                    menuMap.put("author_slct2", vo.getAuthor_slct2());
                    menuMap.put("author_slct3", vo.getAuthor_slct3());
                    menuMap.put("author_slct4", vo.getAuthor_slct4());
                    menuMap.put("author_slct5", vo.getAuthor_slct5());
                    menuMap.put("author_slct1_url", vo.getAuthor_slct1_url());
                    menuMap.put("author_slct2_url", vo.getAuthor_slct2_url());
                    menuMap.put("author_slct3_url", vo.getAuthor_slct3_url());
                    menuMap.put("author_slct4_url", vo.getAuthor_slct4_url());
                    menuMap.put("author_slct5_url", vo.getAuthor_slct5_url());
                    menuMap.put("subMenuList", getSubMenuList(key, vo.getMenu_id(), hide_at));
                    list.add(menuMap);
                }
            }
        }
        return list;
    }

    public List<Map<String, Object>> getMenuList(String key, String menu_id, String hide_at) {
        List<Map<String, Object>> list = null;
        if (tempMap.containsKey(key) && tempMap.get(key) != null) {
            list = new ArrayList<>();
            for (MenuManageVO vo : tempMap.get(key)) {
                if (CylStringUtil.nvl(vo.getUpr_menu_id()).equals(menu_id) && vo.getHide_at().equals(hide_at)) {
                    Map<String, Object> menuMap = new HashMap<>();
                    menuMap.put("menu_id", vo.getMenu_id());
                    menuMap.put("upr_menu_id", vo.getUpr_menu_id());
                    menuMap.put("menu_nm_kr", vo.getMenu_nm_kr());
                    menuMap.put("menu_url", vo.getMenu_url());
                    menuMap.put("hide_at", vo.getHide_at());
                    menuMap.put("menu_path", vo.getMenu_path());
                    menuMap.put("author_slct1", vo.getAuthor_slct1());
                    menuMap.put("author_slct2", vo.getAuthor_slct2());
                    menuMap.put("author_slct3", vo.getAuthor_slct3());
                    menuMap.put("author_slct4", vo.getAuthor_slct4());
                    menuMap.put("author_slct5", vo.getAuthor_slct5());
                    menuMap.put("author_slct1_url", vo.getAuthor_slct1_url());
                    menuMap.put("author_slct2_url", vo.getAuthor_slct2_url());
                    menuMap.put("author_slct3_url", vo.getAuthor_slct3_url());
                    menuMap.put("author_slct4_url", vo.getAuthor_slct4_url());
                    menuMap.put("author_slct5_url", vo.getAuthor_slct5_url());
                    menuMap.put("subMenuList", getSubMenuList(key, vo.getMenu_id()));
                    list.add(menuMap);
                }
            }
        }
        return list;
    }

    public String getMenuTop(String key, String menu_id, String hide_at) {
        String topMenuId = null;
        if (tempMap.containsKey(key) && tempMap.get(key) != null) {
            int cnt = 0;
            for (MenuManageVO vo : tempMap.get(key)) {
                if (cnt > 500)
                    break;
                if (CylStringUtil.nvl(vo.getMenu_id()).equals(menu_id) && vo.getHide_at().equals(hide_at)) {
                    if (vo.getUpr_menu_id() == null) {
                        topMenuId = menu_id;
                        return topMenuId;
                    }
                    topMenuId = getMenuTop(key, vo.getUpr_menu_id(), hide_at);
                    if (topMenuId != null)
                        return topMenuId;
                }
                cnt++;
            }
        }
        return topMenuId;
    }

    public String getMenuTopUri(String key, String uri, String hide_at) {
        String topMenuId = null;
        if (tempMap.containsKey(key) && tempMap.get(key) != null) {
            int cnt = 0;
            for (MenuManageVO vo : tempMap.get(key)) {
                if (cnt > 500)
                    break;
                if (CylStringUtil.nvl(vo.getMenu_url()).equals(uri) && vo.getHide_at().equals(hide_at)) {
                    if (vo.getUpr_menu_id() == null) {
                        topMenuId = vo.getMenu_id();
                        return topMenuId;
                    }
                    topMenuId = getMenuTop(key, vo.getUpr_menu_id(), hide_at);
                    if (topMenuId != null)
                        return topMenuId;
                }
                cnt++;
            }
        }
        return topMenuId;
    }

    public String getMenuNm(String key, String menu_id, String hide_at) {
        String titNm = null;
        if (tempMap.containsKey(key) && tempMap.get(key) != null)
            for (MenuManageVO vo : tempMap.get(key)) {
                if (CylStringUtil.nvl(vo.getMenu_id()).equals(menu_id) && vo.getHide_at().equals(hide_at))
                    titNm = vo.getMenu_nm_kr();
            }
        return titNm;
    }

    public void setMenuList(String key, List<MenuManageVO> list) {
        tempMap.put(key, list);
    }

    public void setPrgmList(String key, List<ProgramManageVO> list) {
        prgmMap.put(key, list);
    }

    private List<Map<String, Object>> getSubMenuList(String key, String menu_id) {
        List<Map<String, Object>> subMenuList = null;
        if (tempMap.containsKey(key) && tempMap.get(key) != null) {
            subMenuList = new ArrayList<>();
            findSubMenu(key, subMenuList, menu_id, "");
        }
        return subMenuList;
    }

    private List<Map<String, Object>> getSubMenuList(String key, String menu_id, String hide_at) {
        List<Map<String, Object>> subMenuList = null;
        if (tempMap.containsKey(key) && tempMap.get(key) != null) {
            subMenuList = new ArrayList<>();
            findSubMenu(key, subMenuList, menu_id, hide_at);
        }
        return subMenuList;
    }

    private List<Map<String, Object>> findSubMenu(String key, List<Map<String, Object>> paramList, String menu_id, String hide_at) {
        if (paramList == null)
            return null;
        if (tempMap.containsKey(key) && tempMap.get(key) != null)
            for (MenuManageVO vo : tempMap.get(key)) {
                if ("".equals(hide_at)) {
                    if (menu_id.equals(CylStringUtil.nvl(vo.getUpr_menu_id()))) {
                        Map<String, Object> menuMap = new HashMap<>();
                        menuMap.put("menu_id", vo.getMenu_id());
                        menuMap.put("upr_menu_id", vo.getUpr_menu_id());
                        menuMap.put("menu_nm_kr", vo.getMenu_nm_kr());
                        menuMap.put("menu_url", vo.getMenu_url());
                        menuMap.put("hide_at", vo.getHide_at());
                        menuMap.put("menu_path", vo.getMenu_path());
                        menuMap.put("author_slct1", vo.getAuthor_slct1());
                        menuMap.put("author_slct2", vo.getAuthor_slct2());
                        menuMap.put("author_slct3", vo.getAuthor_slct3());
                        menuMap.put("author_slct4", vo.getAuthor_slct4());
                        menuMap.put("author_slct5", vo.getAuthor_slct5());
                        menuMap.put("author_slct1_url", vo.getAuthor_slct1_url());
                        menuMap.put("author_slct2_url", vo.getAuthor_slct2_url());
                        menuMap.put("author_slct3_url", vo.getAuthor_slct3_url());
                        menuMap.put("author_slct4_url", vo.getAuthor_slct4_url());
                        menuMap.put("author_slct5_url", vo.getAuthor_slct5_url());
                        menuMap.put("subMenuList", findSubMenu(key, new ArrayList<>(), vo.getMenu_id(), hide_at));
                        paramList.add(menuMap);
                    }
                    continue;
                }
                if (menu_id.equals(CylStringUtil.nvl(vo.getUpr_menu_id())) && vo.getHide_at().equals(hide_at)) {
                    Map<String, Object> menuMap = new HashMap<>();
                    menuMap.put("menu_id", vo.getMenu_id());
                    menuMap.put("upr_menu_id", vo.getUpr_menu_id());
                    menuMap.put("menu_nm_kr", vo.getMenu_nm_kr());
                    menuMap.put("menu_url", vo.getMenu_url());
                    menuMap.put("hide_at", vo.getHide_at());
                    menuMap.put("menu_path", vo.getMenu_path());
                    menuMap.put("author_slct1", vo.getAuthor_slct1());
                    menuMap.put("author_slct2", vo.getAuthor_slct2());
                    menuMap.put("author_slct3", vo.getAuthor_slct3());
                    menuMap.put("author_slct4", vo.getAuthor_slct4());
                    menuMap.put("author_slct5", vo.getAuthor_slct5());
                    menuMap.put("author_slct1_url", vo.getAuthor_slct1_url());
                    menuMap.put("author_slct2_url", vo.getAuthor_slct2_url());
                    menuMap.put("author_slct3_url", vo.getAuthor_slct3_url());
                    menuMap.put("author_slct4_url", vo.getAuthor_slct4_url());
                    menuMap.put("author_slct5_url", vo.getAuthor_slct5_url());
                    menuMap.put("subMenuList", findSubMenu(key, new ArrayList<>(), vo.getMenu_id(), hide_at));
                    paramList.add(menuMap);
                }
            }
        return paramList;
    }

    private void destroy(String key) {
        if (tempMap.containsKey(key) && tempMap.get(key) != null)
            tempMap.remove(key);
    }
}
