package kr.co.cyberline.cmm.web.hcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import org.apache.commons.lang3.StringUtils;

public class CylHierCodeMap {
    private static CylHierCodeMap m_HierCodeMap;
    private static HashMap<Object, List<CylHierCodeVO>> codeValues;
    private static String hierCodeImplClass;
    private static String hierDetailCodeImplClass;

    private CylHierCodeMap() {
    }

    public static CylHierCodeMap getInstance() {
        if (m_HierCodeMap == null) {
            Class var0 = CylHierCodeMap.class;
            synchronized(CylHierCodeMap.class) {
                if (m_HierCodeMap == null) {
                    m_HierCodeMap = new CylHierCodeMap();
                }
            }
        }

        return m_HierCodeMap;
    }

    public void setHierCode(HashMap<Object, List<CylHierCodeVO>> codeValues) {
        CylHierCodeMap.codeValues = codeValues;
    }

    public List<CylHierCodeVO> getHierCode(String codeType) {
        List<CylHierCodeVO> resultList = null;
        if (codeValues != null) {
            resultList = (List)codeValues.get(codeType);
        }

        return resultList;
    }

    public String getHierCodeName(String groupId, String code) {
        String codeName = "";
        List<CylHierCodeVO> codeList = (List)codeValues.get(groupId);
        Iterator var5 = codeList.iterator();

        while(var5.hasNext()) {
            CylHierCodeVO codeVo = (CylHierCodeVO)var5.next();
            if (StringUtils.equals(codeVo.getCode(), code)) {
                codeName = codeVo.getCode_nm();
            }
        }

        return codeName;
    }

    public String getUprCode(String groupId, String code) {
        String upperCode = "";
        List<CylHierCodeVO> codeList = (List)codeValues.get(groupId);
        Iterator var5 = codeList.iterator();

        while(var5.hasNext()) {
            CylHierCodeVO codeVo = (CylHierCodeVO)var5.next();
            if (CylStringUtil.equals(codeVo.getCode(), code)) {
                upperCode = codeVo.getUpr_code();
                break;
            }
        }

        return upperCode;
    }

    public String getHierCodeImplClass() {
        return hierCodeImplClass;
    }

    public void setHierCodeImplClass(String hierCodeImplClass) {
        CylHierCodeMap.hierCodeImplClass = hierCodeImplClass;
    }

    public String getHierDetailCodeImplClass() {
        return hierDetailCodeImplClass;
    }

    public void setHierDetailCodeImplClass(String hierDetailCodeImplClass) {
        CylHierCodeMap.hierDetailCodeImplClass = hierDetailCodeImplClass;
    }
}
