package kr.co.cyberline.cmm.jwt.service;

import kr.co.cyberline.cmm.jwt.vo.TokenVO;
import kr.co.cyberline.cmm.jwt.exception.CustomException;
import kr.co.cyberline.cmm.jwt.security.JwtTokenProvider;
import kr.co.cyberline.cmm.model.UserVO;
import kr.co.cyberline.cmm.util.lang.CylStringUtil;
import kr.co.cyberline.cmm.util.sec.CylScrtyUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenVO login(UserVO userVO) {
        String userId = userVO.getUser_id();
        String userPwd = userVO.getPasswd();

        if ( CylStringUtil.isNotEmpty(userId) && CylStringUtil.isNotEmpty(userPwd) ) {

            logger.info("로그인 ID : " + userId);
            logger.info("로그인 PWD : " + userPwd);

            try {
                userVO = userService.actionLogin(new UserVO(userId, CylScrtyUtil.encryptPassword(userId,userPwd)));
            } catch(NoSuchAlgorithmException e) {
                logger.error("로그인 시도 사용자 유효 검증 시 비밀번호 암호화 부분 오류 발생!");
                logger.error(e.getMessage());
            } catch(IllegalArgumentException e) {
                logger.error("로그인 시도 사용자 유효 검증 시 비밀번호 암호화 부분 오류 발생!");
                logger.error(e.getMessage());
            } catch (Exception e) {
                logger.error("로그인 시도 사용자 유효 검증 시 비밀번호 암호화 부분 오류 발생!");
                logger.error(e.getMessage());
            }

            if (userVO == null) {
                logger.info("사용자 인증에 실패 하였습니다.");
                throw new BadCredentialsException("사용자 인증에 실패 하였습니다.");
            }

            // 로그인 이력 저장
            userService.insertLoginHist(userVO.getLogin_id(), "01");

            String accessToken = jwtTokenProvider.createAccessToken(userVO);
            String refreshToken = jwtTokenProvider.createRefreshToken();

            // Save refreshToken to DB
            userService.updateRefreshToken(userVO);

            return TokenVO.builder()
                    .grantType("Bearer")
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .accessTokenExpiresIn(jwtTokenProvider.getExpiration(accessToken).getTime())
                    .build();
        } else {
            throw new BadCredentialsException("사용자 정보가 유효하지 않습니다.");
        }
    }

    @Transactional
    public TokenVO refresh(String refreshToken) {
        // 사용자 찾기 (만료되었어도 DB에 저장된 토큰으로 사용자를 찾을 수 있어야 함)
        UserVO userVO = userService.getUserByRefreshToken(refreshToken);

        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new CustomException("유효하지 않은 Refresh Token입니다.", HttpStatus.UNAUTHORIZED);

        }

        // 새 Access Token 생성
        String newAccessToken = jwtTokenProvider.createAccessToken(userVO);

        return TokenVO.builder()
                .grantType("Bearer")
                .accessToken(newAccessToken)
                .accessTokenExpiresIn(jwtTokenProvider.getExpiration(newAccessToken).getTime())
                .build();
    }
}
