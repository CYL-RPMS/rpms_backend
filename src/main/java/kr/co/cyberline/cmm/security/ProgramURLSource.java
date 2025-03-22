package kr.co.cyberline.cmm.security;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.cyberline.cmm.model.CustomUserDetails;
import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.service.SystemService;
import kr.co.cyberline.cmm.web.model.ProgramManageVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * URL 에 따른 권한체크 클래스
 * @author 프레임워크 개발 송치석
 * @since 2015.04.30
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------        --------    ---------------------------
 *   2015. 4. 30  송치석          최초 생성
 * </pre>
 */
public class ProgramURLSource implements FilterInvocationSecurityMetadataSource {

    private static final Logger logger = LoggerFactory.getLogger(ProgramURLSource.class);
    
    @Resource(name = "systemService")
    private SystemService systemService;

    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        CustomUserDetails userDetail = null;

        String url = fi.getRequestUrl();

        UserVO userVO = new UserVO();
        List<String> authorities = new ArrayList<String>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ( authentication == null ){
            return SecurityConfig.createListFromCommaDelimitedString("ROLE_ANONYMOUS");
        }

        // 인증이 안됬거나 익명 사용자라면....
        if ( authentication.getAuthorities().size() == 1 && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS")) ) {

            authorities.add("ROLE_ANONYMOUS");
            userVO.setAuthor_id("ROLE_ANONYMOUS");

        } else {    // 인증된 사용자라면...
            userDetail = (CustomUserDetails) authentication.getDetails();
            userVO.setUser_id(userDetail.getUser_id());
            for ( GrantedAuthority ca : userDetail.getAuthorities() ){
                authorities.add(ca.getAuthority());
            }
        }

        // TODO : 권한에 따른 프로그램 목록 조회
        //List<ProgramManageVO> prgmList = programManageService.selectUserAuthorMenuProgramList(userVO);
        List<ProgramManageVO> prgmList = systemService.selectUserAuthorMenuProgramList(userVO);
//        String menu_id = fi.getRequest().getParameter("menu_id");
//        String upr_menu_id = fi.getRequest().getParameter("upr_menu_id");
//        String uupr_menu_id = fi.getRequest().getParameter("uupr_menu_id");
//        String dwn_menu_id = fi.getRequest().getParameter("dwn_menu_id");
//        fi.getRequest().getSession().setAttribute("menu_id", menu_id);
//        fi.getRequest().getSession().setAttribute("upr_menu_id", upr_menu_id);
//        fi.getRequest().getSession().setAttribute("uupr_menu_id", uupr_menu_id);
//        fi.getRequest().getSession().setAttribute("dwn_menu_id", dwn_menu_id);
        // TODO : 프로그램에 대한 권한 체크
        boolean flag = isSkip(fi.getRequest());
        if ( !flag && prgmList != null && prgmList.size() > 0 ) {
            for (ProgramManageVO vo : prgmList) {
                if ("01".equals(vo.getPrgm_url_cd())) {
                    if (fi.getRequestUrl().equals(vo.getPrgm_url())) {
                        flag = true;
                        break;
                    }
                } else {
                    RegexRequestMatcher requestMatcher = new RegexRequestMatcher(vo.getPrgm_url(), null, false);
                    if (requestMatcher.matches(fi.getHttpRequest())) {
                        flag = true;
                        break;
                    }
                }
            }
        }

        if ( flag ){
            return SecurityConfig.createListFromCommaDelimitedString(StringUtils.join(authorities, ","));
        } else {
            throw new AccessDeniedException("해당 페이지에 권한이 없습니다.");
        }
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
    /**
     * 익명 권한 URL 체크
     * @param request
     * @return
     */
    public boolean isSkip(HttpServletRequest request){
        boolean flag = false;
        if ( new RegexRequestMatcher("\\A/uat/userLogin.*\\.do.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/uat/usp/userMenuList.do*\\Z", null, false).matches(request) ) {
            flag = true;
		} /*else if ( new RegexRequestMatcher("\\A/main/.*\\.do*\\Z", null, false).matches(request) ) {
            flag = true;
        } */else if ( new RegexRequestMatcher("\\A/index.jsp*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/error/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/(select|selectOne|selectMap|selectOj|insert|update|delete)/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/lz/(select|selectOne|selectMap|insert|update|delete)/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/file/(select|selectOne|selectMap|insert|update|delete)/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/common/sessionLoad.do*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/common/sessionCodeLoad.do*\\Z", null, false).matches(request) ) {
            flag = true;
        }else if ( new RegexRequestMatcher("\\A/comm/exceldown/.*\\Z", null, false).matches(request) ) {
            flag = true;
        }else if ( new RegexRequestMatcher("\\A/comm/multi/exceldown/.*\\Z", null, false).matches(request) ) {
            flag = true;
        }else if ( new RegexRequestMatcher("\\A/comm/excelupload/.*\\Z", null, false).matches(request) ) {
            flag = true;
        }else if ( new RegexRequestMatcher("\\A/comm/excelmultiupload/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/lz/exceldown/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/download/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/.*/viewPage.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/.*/commPopup.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/ckEditor/imgUpload.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/cmmFileUpload.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/clipreport5/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/report/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/report_viewOnly/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/template/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/system/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/synchrnUserInfo.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/synchrnBudgetInfo.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/synchrnCommCodeInfo.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/synchrnBudgetSave.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/userId/(select|selectOne|selectMap|selectOj|insert|update|delete)/.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/uat/userDoJoin.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/uat/loginDelegation.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/reportMng/rsltEvl/mainPopup.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/dlbrt/applyDlbrt/mainPopup.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/dlbrt/mngDlbrt/mngIntrlMmbr/mainPopup.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/.*/noticePopup.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/mabikGroupwareLoginChk.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/groupwareLoginChk.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/comm/sessionClose.*\\Z", null, false).matches(request) ) {
            flag = true;
        } else if ( new RegexRequestMatcher("\\A/sanctn/.*\\Z", null, false).matches(request) ) {
            flag = true;
        }

        return flag;
    }
}