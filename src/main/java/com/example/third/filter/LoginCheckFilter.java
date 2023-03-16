package com.example.third.filter;

import com.example.third.controller.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LoginCheckFilter implements Filter {
    //특정사이트에는 적용을 하지 않을것이다. 화이트 리스트(로그인 , 회원가입, 로그아웃)
    //화이트 리스트를 제외한 나머지는 모두 적용
    private static final String[] whiteLists = {"/", "/login", "/members/join", "/logout" , "/css/*", "/templates/order/"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        try {
            log.info("로그인 인증 필터 시작 :  [{}] , [{}] ", requestURI, uuid);
            // whitelist 를 제외한 나머지 사이트에 대해서만 로그인 체크 할것
            if(!PatternMatchUtils.simpleMatch(whiteLists, requestURI)){
                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.NAME) == null){
                    log.info("미 인증 사용자의 요청 {} ", requestURI);
                    httpResponse.sendRedirect("/login?redirectURL="+requestURI);
                    return; // 미인증 사용자는 내보냄.
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e){
            throw e;
        } finally {
            log.info("로그인 인증 필터 종료:  [{}] , [{}] ", requestURI, uuid);
        }
    }

}

