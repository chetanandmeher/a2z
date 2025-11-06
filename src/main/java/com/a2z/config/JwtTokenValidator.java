package com.a2z.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.a2z.config.JWT_CONSTANT;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

import static io.jsonwebtoken.Jwts.*;


/**
 * @author Chetanand Meher
 */
public class JwtTokenValidator extends OncePerRequestFilter {

    /**
     * @param request
     * @param response
     * @param filterChain
//     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
         String jwt = request.getHeader("Authorization");

         if (jwt != null) {
             jwt = jwt.substring(7);
             try {
                 SecretKey key = Keys.hmacShaKeyFor(JWT_CONSTANT.SECRET_KEY.getBytes());
                 Claims claims = Jwts.parserBuilder()
                         .setSigningKey(key)
                         .build()
                         .parseClaimsJws(jwt)
                         .getBody();

                 String email = String.valueOf(claims.get("email"));
                 String authorities = String.valueOf(claims.get("authorities"));

                 List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                 Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);

                 SecurityContextHolder.getContext().setAuthentication(authentication);

             } catch (Exception e) {
                 throw new BadCredentialsException("Invalid JWT token: " + e.getMessage());
             }
         }

         filterChain.doFilter(request, response);
    }
}
