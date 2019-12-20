package com.zhang.template.util;

import com.zhang.template.config.GrantedAuthorityImpl;
import com.zhang.template.config.JwtAuthenticationToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class JwtTokenUtils {
    /**
     * 密钥
     */
    private static final String SECRET = "abcdefgh";

    /**
     * 用户名称
     */
    private static final String USERNAME = Claims.SUBJECT;

    /**
     * 创建时间
     */
    private static final String CREATED = "created";

    /**
     * 权限列表
     */
    private static final String AUTHORITIES = "authorities";

    /**
     * 有效期12小时
     */
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;

    /**
     * 根据请求信息中的token获取登录认证信息
     * @param request
     * @return
     */
    public static Authentication getAuthenticationFromToken(HttpServletRequest request) {
        Authentication authentication = null;
        //获取请求中的token
        String token = JwtTokenUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            //请求令牌不能为空
            if (SecurityUtils.getAuthentication() == null) {
                //上下文中authentication为空
                Claims claims = getClaimsFromToken(token);
                if (claims != null) {
                    String username = claims.getSubject();
                    if (username != null) {
                        if (!isTokenExpired(token)) {
                            Object authors = claims.get(AUTHORITIES);
                            List<GrantedAuthority> authorities = new ArrayList<>();
                            if (authors != null && authors instanceof List) {
                                for(Object object : (List)authors) {
                                    authorities.add(new GrantedAuthorityImpl((String) ((Map) object).get("authority")));
                                }
                            }
                            authentication = new JwtAuthenticationToken(username, null, authorities, token);
                        }
                    }
                }
            } else{
                if (validateToken(token,SecurityUtils.getUsername())) {
                    //如果上下文中authentication非空，且请求令牌合法，直接返回当前登录信息
                    authentication = SecurityUtils.getAuthentication();
                }
            }
        }
        return authentication;
    }

    private static boolean validateToken(String token, String username) {
        String usernameByToken = getUsernameFromToken(token);
        if (StringUtils.isNotEmpty(usernameByToken)) {
            return usernameByToken.equalsIgnoreCase(username) && !isTokenExpired(token);
        }
        return false;
    }

    /**
     * 判断令牌是否过期
     * @param token
     * @return
     */
    private static boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    private static String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    /**
     * 从令牌中获取数据声明
     * @param token
     * @return
     */
    private static Claims getClaimsFromToken(String token) {
        if (StringUtils.isNotEmpty(token)) {
            try{
                Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJwt(token).getBody();
                return claims;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String tokenHead = "Bearer ";
        if (token == null) {
            token = request.getHeader("token");
        } else if (token.contains(tokenHead)) {
            token = token.substring(tokenHead.length());
        }
        if (StringUtils.isEmpty(token)) {
            token = null;
        }
        return token;
    }

    /**
     * 生成令牌
     * @param authentication
     * @return
     */
    public static String generateToken(Authentication authentication) {
        Map<String,Object> claims = new HashMap<>(3);
        claims.put(USERNAME,SecurityUtils.getUsername(authentication));
        claims.put(CREATED,new Date());
        claims.put(AUTHORITIES, authentication.getAuthorities());
        return generateToken(claims);
    }

    /**
     * 从数据声明中获取令牌
     * @param claims
     * @return
     */
    private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }
}
