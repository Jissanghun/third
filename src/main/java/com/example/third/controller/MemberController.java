package com.example.third.controller;

import com.example.third.domain.Member;
import com.example.third.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {

    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/join")
    public String memberJoin(Model model){
        Member member = new Member();
        model.addAttribute("member",member);
        return "members/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute Member member){
        System.out.println("member = " + member);
        Long id = memberService.join(member);
        System.out.println(id+" 번 회원 가입 완료");
        return "redirect:/login";
    }

}
