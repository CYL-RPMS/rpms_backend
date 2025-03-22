package kr.co.cyberline.cmm.web.util;

import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.path.PathBuilder;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.web.model.CylWebDefaultVO;

/**
 * 공통 QueryDSL Util
 * @author 공통컴포넌트 개발 송치석
 * @since 2015.04.30
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일       수정자           수정내용
 *  -------       --------    ---------------------------
 *   2015. 4. 30  송치석          최초 생성
 * </pre>
 */
public class CylQueryDSLUtil {

    /**
     * ORM QueryDSL의 Order By 를 조회한다.
     * @author Chi Seok Song
     * @param object
     * @param entityName
     * @return
     */
    public static OrderSpecifier getOrderBy(Object object, String entityName){
        CylWebDefaultVO cylWebDefaultVO = (CylWebDefaultVO) object;
        PathBuilder orderByExpression = new PathBuilder(object.getClass(), entityName);
        if (CylStringUtil.isNotEmpty(cylWebDefaultVO.getCondOrder()) && CylStringUtil.isNotEmpty(cylWebDefaultVO.getCondAlign())){
            Order align;
            if ( cylWebDefaultVO.getCondAlign().equalsIgnoreCase("asc") ){
                align = Order.ASC;
            } else {
                align = Order.DESC;
            }
            return new OrderSpecifier(align, orderByExpression.getString(cylWebDefaultVO.getCondOrder().toLowerCase()));
        }
        return new OrderSpecifier(Order.ASC, orderByExpression);
    }
}
