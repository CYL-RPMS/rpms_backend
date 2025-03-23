package kr.co.cyberline.cmm.jwt.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cyberline.cmm.model.CustomUserDetails;
import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.service.SystemService;
import kr.co.cyberline.cmm.web.model.ProgramManageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Resource(name = "systemService")
    private SystemService systemService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 인증 제외 URL인지 확인
        if (isSkip(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        UserVO userVO = new UserVO();
        String token = jwtTokenProvider.resolveToken(request);
        List<GrantedAuthority> authorities = new ArrayList<>();

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                // 토큰에서 사용자 정보 추출
                String userId = jwtTokenProvider.getUserIdFromToken(token);
                String authorId = jwtTokenProvider.getAuthorIdFromToken(token);

                userVO.setUser_id(userId);
                userVO.setAuthor_id(authorId);

                // 사용자 권한 목록 설정
                authorities.add(new SimpleGrantedAuthority(authorId));

                // CustomUserDetails 생성
                CustomUserDetails userDetails = null;
                userDetails.setUser_id(userId);
                userDetails.setAuthor_id(authorId);

                // Authentication 객체 생성 및 SecurityContext에 저장
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userId, null, authorities);
                ((UsernamePasswordAuthenticationToken) authentication).setDetails(userDetails);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // 인증되지 않은 사용자는 ANONYMOUS 권한 설정
                userVO.setAuthor_id("ROLE_ANONYMOUS");
                authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        "anonymousUser", null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            // URL 권한 체크
            boolean hasAccess = checkUrlAccess(request, userVO);

            if (!hasAccess) {
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Unauthorized\",\"message\":\"해당 페이지에 권한이 없습니다.\"}");
                return; // 필터 체인 중단
            }

            // 권한 확인 후 계속 진행
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Authentication error: {}", e.getMessage());
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Unauthorized\",\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    /**
     * URL 접근 권한 체크
     */
    private boolean checkUrlAccess(HttpServletRequest request, UserVO userVO) {
        // 인증 제외 URL은 접근 허용
        if (isSkip(request)) {
            return true;
        }

        // 권한에 따른 프로그램 목록 조회
        List<ProgramManageVO> prgmList = systemService.selectUserAuthorMenuProgramList(userVO);

        if (prgmList == null || prgmList.isEmpty()) {
            return false;
        }

        //api/validate는 클라이언트 url 검사, 나머지는 클라이언트에서 요청하는 api 검사
        String requestUrl = request.getRequestURI().equals("/api/auth/validate") ? request.getParameter("pathName") : request.getRequestURI();

        // URL 권한 체크
        for (ProgramManageVO vo : prgmList) {
            if ("01".equals(vo.getPrgm_url_cd())) {
                if (requestUrl.equals(vo.getPrgm_url())) {
                    return true;
                }
            } else {
                RegexRequestMatcher requestMatcher = new RegexRequestMatcher(vo.getPrgm_url(), null, false);
                if (requestMatcher.matches(request)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 익명 권한 URL 체크
     * @param request
     * @return
     */
    public boolean isSkip(HttpServletRequest request) {
        boolean flag = false;

        if (new RegexRequestMatcher("/api/auth/.*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("/uat/usp/MenuList", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("/systemInitConfig.com", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("/common/cmmCodeList", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/error/.*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/comm/(select|selectOne|selectMap|selectOj|insert|update|delete)/.*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/comm/lz/(select|selectOne|selectMap|insert|update|delete)/.*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/comm/file/(select|selectOne|selectMap|insert|update|delete)/.*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/common/sessionLoad.do*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/common/sessionCodeLoad.do*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/comm/exceldown/.*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/comm/excelupload/.*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/comm/lz/exceldown/.*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/comm/download/.*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/comm/ckEditor/imgUpload.*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/comm/cmmFileUpload.*\\Z", null, false).matches(request)) {
            flag = true;
        } else if (new RegexRequestMatcher("\\A/clipreport5/.*\\Z", null, false).matches(request)) {
            flag = true;
        }

        return flag;
    }
}