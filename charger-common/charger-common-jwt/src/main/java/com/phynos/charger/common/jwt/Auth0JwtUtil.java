package com.phynos.charger.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author lupc
 * @date 2021/7/1 19:48
 */
@Slf4j
public class Auth0JwtUtil {

    public static String create(String secret, int expires, Map<String, String> claims) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer("phynos") //签发者
                    .withIssuedAt(new Date()) //签发时间
                    .withExpiresAt(DateUtils.addDays(new Date(), expires))
                    .withSubject("iot-token"); //主题
            //payload中加入自定义数据
            claims.forEach(builder::withClaim);
            String token = builder.sign(algorithm);
            log.info("当前时间：{}", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            log.debug("jwt token:{}", token);
            return token;
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            log.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    public static DecodedJWT decodeToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt;
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DecodedJWT verify(String secret, String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (JWTVerificationException exception) {
            log.error("当前时间：{}", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            log.error("验证token失败：{}", exception.getMessage());
            return null;
        }
    }

    public static void test(String secret, String token) {
        DecodedJWT jwt = verify(secret, token);

        String decodeHeader = "";
        String decodePayload = StringUtils.toEncodedString(Base64.getDecoder().decode(jwt.getPayload()), StandardCharsets.UTF_8);

        String signature = jwt.getSignature();
        String name = jwt.getClaim("name").asString();
        String introduce = jwt.getClaim("introduce").asString();

        System.out.println("header:" + jwt.getHeader());
        System.out.println("payload:" + jwt.getPayload());
        System.out.println("signature:" + signature);

        System.out.println("headerString:" + decodeHeader);
        System.out.println("payloadString:" + decodePayload);

        System.out.println("name:" + name);
        System.out.println("introduce:" + introduce);
    }

    public static void main(String[] args) {
        Map<String, String> claims = new HashMap<>();
        claims.put("aaa", "dfadfaf");
        String secret = "PDJ2544DEF4E55DF4D1";
        String token = create(secret, 7, claims);
        test(secret, token);
    }

}
