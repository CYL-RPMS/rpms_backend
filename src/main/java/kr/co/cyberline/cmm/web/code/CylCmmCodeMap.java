package kr.co.cyberline.cmm.web.code;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CylCmmCodeMap {
    private static CylCmmCodeMap m_CylCmmCodeMap;
    private static Map<Object, CylCmmCodeVO> codeValues;
    private static Map<Object, CylCmmCodeVO> cmmCodeValues;
    private static String cmmCodeImplClass = "cylCmmCodeService";

    private CylCmmCodeMap() {
    }

    public static CylCmmCodeMap getInstance() {
        if (m_CylCmmCodeMap == null) {
            Class var0 = CylCmmCodeMap.class;
            synchronized(CylCmmCodeMap.class) {
                if (m_CylCmmCodeMap == null) {
                    m_CylCmmCodeMap = new CylCmmCodeMap();
                }
            }
        }

        return m_CylCmmCodeMap;
    }

    public void setCommonCode(Map<Object, CylCmmCodeVO> codeValues) {
        CylCmmCodeMap.codeValues = codeValues;
    }

    public void setCmmCode(Map<Object, CylCmmCodeVO> cmmCodeValues) {
        CylCmmCodeMap.cmmCodeValues = cmmCodeValues;
    }

    public CylCmmCodeVO getCode(String code_id) {
        CylCmmCodeVO result = null;
        if (codeValues != null) {
            result = (CylCmmCodeVO)codeValues.get(code_id);
        }

        return result;
    }

    public List<CylCmmCodeDetailVO> getCodeDetail(String code_id) {
        List<CylCmmCodeDetailVO> resultList = null;
        if (codeValues != null && codeValues.get(code_id) != null) {
            resultList = ((CylCmmCodeVO)codeValues.get(code_id)).getCodeDetailList();
        }

        return resultList;
    }

    public String getCodeName(String codeId) {
        String codeName = "";
        CylCmmCodeVO codeVO = (CylCmmCodeVO)codeValues.get(codeId);
        if (codeVO != null) {
            codeName = codeVO.getCode_id_nm();
        }

        return codeName;
    }

    public String getCodeDetailName(String codeId, String code) {
        String codeName = "";
        List<CylCmmCodeDetailVO> codeDetailList = ((CylCmmCodeVO)codeValues.get(codeId)).getCodeDetailList();
        if (codeDetailList != null) {
            Iterator var5 = codeDetailList.iterator();

            while(var5.hasNext()) {
                CylCmmCodeDetailVO vo = (CylCmmCodeDetailVO)var5.next();
                if (vo.getCode().equals(code)) {
                    codeName = vo.getCode_nm();
                    break;
                }
            }
        }

        return codeName;
    }

    public String getCmmCodeImplClass() {
        return cmmCodeImplClass;
    }

    public void setCmmCodeImplClass(String commonCodeImplClass) {
        cmmCodeImplClass = commonCodeImplClass;
    }

    public static Map<Object, CylCmmCodeVO> getCodeValues() {
        return codeValues;
    }

    public static Map<Object, CylCmmCodeVO> getCmmCodeValues() {
        return cmmCodeValues;
    }

    public static void setCodeValues(Map<Object, CylCmmCodeVO> codeValues) {
        CylCmmCodeMap.codeValues = codeValues;
    }

    public static void setCmmCodeValues(Map<Object, CylCmmCodeVO> cmmCodeValues) {
        CylCmmCodeMap.cmmCodeValues = cmmCodeValues;
    }
}
