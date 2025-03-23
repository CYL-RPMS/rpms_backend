package kr.co.cyberline.cmm.jwt.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.cyberline.cmm.model.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.accession.validated}")
    private long accessTokenValidityInSeconds;

    @Value("${jwt.refresh.validated}")
    private long refreshTokenValidityInSeconds;

    private Key key;

    @PostConstruct
    protected void init() {
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        key = Keys.hmacShaKeyFor(encodedKey.getBytes());
    }

    // Access Token 생성
    public String createAccessToken(UserVO userVO) {
        Claims claims = Jwts.claims().setSubject(userVO.getLogin_id());
        claims.put("user_nm", userVO.getUser_nm());
        claims.put("user_id", userVO.getUser_id());
        claims.put("author_id", userVO.getAuthor_id());

        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenValidityInSeconds * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Refresh Token 생성
    public String createRefreshToken() {
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityInSeconds * 1000);

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Request Header에서 토큰 정보 가져오기
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    // 토큰 파싱
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰 만료 시간 가져오기
    public Date getExpiration(String token) {
        return parseClaims(token).getExpiration();
    }

    // 토큰 권한 추출
    public String getAuthorIdFromToken(String token) {
        Claims claims = parseClaims(token);
        return claims.get("author_id", String.class);

    }
    
    public String getUserIdFromToken(String token) {
        Claims claims = parseClaims(token);

        // Claims에서 "userId"를 추출
        return claims.get("user_id", String.class);

    }

    public String getLoginIdFromToken(String token) {
        Claims claims = parseClaims(token);

        // Claims에서 "LoginId"를 추출
        return claims.get("login_id", String.class);

    }
}