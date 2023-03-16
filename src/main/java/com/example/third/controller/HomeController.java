package com.example.third.controller;

import com.example.third.controller.session.CookieConst;
import com.example.third.controller.session.MemberSession;
import com.example.third.controller.session.SessionConst;
import com.example.third.domain.Member;
import com.example.third.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class HomeController {
    MemberService memberService;
    @Autowired
    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/")
    public String home(  //@CookieValue(value = CookieConst.NAME, required=false) Long memberId,
            HttpServletRequest request,
            Model model) {

        HttpSession session = request.getSession(false);// 없으면 null 반환, 있으면 있는걸 반환
        log.info("member id : [ {} ]  ", session) ;
        if(session == null) { // 로그인 안한 사용자 , // 로그인 하지 않은 사람은 home 으로 화면 보여져서 로그인 하도록
            return "home";
        } else { // 로그인 한 사람은 상픔관리 목록으로 이동하거나, 로그아웃 하도록

            MemberSession memberSession = (MemberSession) session.getAttribute(SessionConst.NAME);
            model.addAttribute("member", memberSession);
            return "loginHome";
        }
    }
}
