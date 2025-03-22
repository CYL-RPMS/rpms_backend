package kr.co.cyberline.cmm.jwt.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.cyberline.cmm.jwt.vo.TokenVO;
import kr.co.cyberline.cmm.jwt.service.AuthService;
import kr.co.cyberline.cmm.jwt.service.UserService;
import kr.co.cyberline.cmm.model.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenVO> login(UserVO userVO) {
        return ResponseEntity.ok(authService.login(userVO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenVO> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        userService.insertLogoutHist(request);
    }
}
