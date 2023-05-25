package br.com.serratec.serramed.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.serratec.serramed.domain.model.Login;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    @Value("${auth.jwt.secret}")
    private String secret;

    @Value("${auth.jwt.expiration}")
    private Long expiration;

    public String generateToken(Authentication authentication) {

        Date expirationDate = new Date(new Date().getTime() + expiration);

        Login login = (Login) authentication.getPrincipal();

        try {

            Key secretKey = Keys.hmacShaKeyFor(secret.getBytes("UTF-8"));

            return Jwts.builder()
                    .setIssuer("Serramed API")
                    .setSubject(login.getEmail())
                    .setIssuedAt(new Date())
                    .setExpiration(expirationDate)
                    .signWith(secretKey)
                    .compact();
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean isValidToken(String token) {

        Claims claims = getClaims(token);

        if (claims.isEmpty()) {
            return false;
        }

        Date expirationDate = claims.getExpiration();
        Date now = new Date(System.currentTimeMillis());

        return now.before(expirationDate);
    }

    public String getEmail(String token) {

        Claims claims = getClaims(token);

        if (claims.isEmpty()) {
            return null;
        }

        return claims.getSubject();
    }

    private Claims getClaims(String token) {

        try {

            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();

        } catch (Exception ex) {
            ex.printStackTrace();
            return Jwts.claims(null);
        }
    }
}
