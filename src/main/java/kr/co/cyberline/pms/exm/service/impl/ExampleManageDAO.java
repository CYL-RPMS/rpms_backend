/**
 * Author    : 송치석
 * Date      : 2015. 08. 09
 * Company   : Cyber Line Co., Ltd.
 * Description : 예제 관리 구현 Data Access Object
 * Copyright (C) 2015 by Cyber Line  All right reserved.
 */
package kr.co.cyberline.pms.exm.service.impl;

import kr.co.cyberline.cmm.web.dao.CylAbstractDAO;

/**
 * <p>예제 관리 Data Access Object</p>
 *
 * @author 송 치 석
 * @since 2015. 08. 09
 * @version 1.0
 */
public class ExampleManageDAO extends CylAbstractDAO {

    @Override
    protected String getNameSpace() {
        return "Example";
    }
}
