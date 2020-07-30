import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtDemo {
    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder().setId("11")
                .setSubject("lqk")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"teskinfly");
        String token = builder.compact();
        Claims claims = Jwts.parser().setSigningKey("teskinfly").parseClaimsJws(token).getBody();
        System.out.println(claims.getSubject());
    }
}
