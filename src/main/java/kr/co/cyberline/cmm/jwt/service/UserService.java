package kr.co.cyberline.cmm.jwt.service;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.cyberline.cmm.jwt.exception.CustomException;
import kr.co.cyberline.cmm.jwt.security.JwtTokenProvider;
import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.pms.uat.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserLoginService userLoginService;
    private final JwtTokenProvider jwtTokenProvider;

    private String getClientIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 여러 프록시를 통과한 경우 첫 번째 IP만 사용
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        return ip;
    }

    public UserVO actionLogin(UserVO userVO) {
        UserVO user = userLoginService.actionLogin(userVO);
        if (user == null) {
            throw new CustomException("--------------존재하는 사용자가 없습니다 ---------------" + userVO.getLogin_id(), HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public void updateRefreshToken(UserVO userVO) {
        userLoginService.updateRefreshToken(userVO);
    }

    public UserVO getUserByRefreshToken(String refreshToken) {
        UserVO user = userLoginService.findByRefreshToken(refreshToken);
        if (user == null) {
            throw new CustomException("Invalid refresh token", HttpStatus.UNAUTHORIZED);
        }
        return user;
    }

    public void insertLoginHist(String login_id, String login_mthd) {
        String login_ip = getClientIp();

        Map<String, Object> param = new HashMap<>();
        param.put("login_id", login_id);
        param.put("login_ip", login_ip);
        param.put("login_mthd", login_mthd);

        userLoginService.insertLoginHist(param);
    }

    public void insertLogoutHist(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            String loginId = jwtTokenProvider.getLoginIdFromToken(token);

            String login_ip = getClientIp();

            Map<String, Object> param = new HashMap<>();
            param.put("login_id", loginId);
            param.put("login_ip", login_ip);
            param.put("login_mthd", "02");

            userLoginService.insertLoginHist(param);
        }

    }

}
