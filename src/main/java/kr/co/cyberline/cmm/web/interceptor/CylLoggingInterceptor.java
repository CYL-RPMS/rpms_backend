package kr.co.cyberline.cmm.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;

/**
 * 웹 로깅 클래스
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
@Component
public class CylLoggingInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(CylLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("====================Request Info====================");
        logger.info("request.getRequestURI() : " + request.getRequestURI());
        logger.info("request.getRequestURL() : " + request.getRequestURL());
        logger.info("request.getServletPath() : " + request.getServletPath());
        logger.info("request.getContextPath() : " + request.getContextPath());
        logger.info("request.getPathInfo() : " + request.getPathInfo());
        logger.info("request.getMethod() : " + request.getMethod());
        logger.info("this.getClass().getName() : " + handler.getClass().getName());
        logger.info("request.getRemoteAddr() : " + request.getRemoteAddr());

        Enumeration enums = request.getParameterNames();

        while (enums.hasMoreElements()) {
            String paramName = (String) enums.nextElement();
            String[] parameters = request.getParameterValues(paramName);

            for (int i = 0; i < parameters.length; i++) {
                logger.info("parameter ::: [" + paramName + "] " + parameters[i]);
            }
        }
        logger.info("====================Request Info====================");
        return true;
    }
}
