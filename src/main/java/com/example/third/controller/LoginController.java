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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Controller
public class LoginController {
    MemberService memberService;
    @Autowired
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/login")
    public String memberLogin(Model model){
        //log.info(" ===== 로그인 get ==== redirectURL: {}",redirectURL);
        Member member = new Member();
        model.addAttribute("member",member);
        return "login";
    }
    @PostMapping("/login")
    public String memberLogin(
            @ModelAttribute Member member,
            HttpServletRequest request,
            @RequestParam(defaultValue = "/") String redirectURL){ // HttpServletResponse response,

        log.info(" ===== 로그인 post ==== redirectURL: {}",redirectURL);

        String loginId = member.getLoginId();
        Optional<Member> member1 = memberService.findMember(loginId);// member 가 존재해야 함. member.password == 화면에서 입력한 password 랑 일치해야 함

        // false && false ==> false  두번째 조건식은 pass
        // false && true ==> false   두번째 조건식은 pass
        // true && false ==> false   두번째 조건식에 따라 true이거나 false가 되기 때문에 두번째 조건식을 반드시 조사함.
        // true && true ==> true     두번째 조건식에 따라 true이거나 false가 되기 때문에 두번째 조건식을 반드시 조사함.
        // 문제 상황 -- > member1 empty 인 경우 [ ] 빈 리스트, NoSuchElementException 발생
        if(!member1.isEmpty() && member1.get().getPassword().equals(member.getPassword())){ // 로그인에 성공하면

            Member loginMember = member1.get();

            MemberSession memberSession = new MemberSession();
            memberSession.setId(loginMember.getId());
            memberSession.setLoginId(loginMember.getLoginId());
            memberSession.setName(loginMember.getName());

            HttpSession session = request.getSession(true);//true 세션이 없으면 만들어주고, 있으면 있는 세션을 반환
            session.setAttribute(SessionConst.NAME, memberSession);

//            Cookie memberId = new Cookie(CookieConst.NAME, String.valueOf(member1.get().getId()));
//            response.addCookie(memberId);
            // 미인증 사용자가 로그인 성공한 후에 최초 요청한 페이지로 돌려보내기 위함.
            return "redirect:" + redirectURL;
        }
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(
            HttpServletRequest request) { // HttpServletResponse response){

        HttpSession session = request.getSession(false); // 있으면 있는 것 반환, 없으면 null 반환
        if(session != null) {
            session.invalidate();
        }
//        Cookie cookie = new Cookie(CookieConst.NAME, null);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
        return "redirect:/" ;
    }
}
