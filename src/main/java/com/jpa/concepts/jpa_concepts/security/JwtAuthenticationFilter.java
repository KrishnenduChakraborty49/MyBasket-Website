package com.jpa.concepts.jpa_concepts.security;

import com.jpa.concepts.jpa_concepts.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper=new ModelMapper();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header=request.getHeader("Authorization");

        //if this condition will be true
        if(header!=null && header.startsWith("Bearer")){
            String token=header.substring(7);

            try
            {

                if(!jwtService.isAccessToken(token)){
                    System.out.println("Token is not access Token");
                    filterChain.doFilter(request,response);
                    return;
                }
                Jws<Claims> parsedData=jwtService.parse(token);
                Claims payload=parsedData.getPayload();
                String userId=payload.getSubject();
                var optionalUser = userRepository.findById(Integer.parseInt(userId));

                if (optionalUser.isPresent() && optionalUser.get().isEnable()) {
                    var user = optionalUser.get();
                    ROLE role= user.getRole();
                    List<GrantedAuthority> authorities=(role==null)? List.of():List.of(new SimpleGrantedAuthority(role.toString()));

                    var authentication =new UsernamePasswordAuthenticationToken(user.getEmail(), null,authorities);

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    if(SecurityContextHolder.getContext().getAuthentication()==null){
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }catch(ExpiredJwtException ex){
                ex.printStackTrace();
            }catch(MalformedJwtException ex){
                ex.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        //forward the request
        filterChain.doFilter(request,response);
    }
}
