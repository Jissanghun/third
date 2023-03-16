package com.example.third.controller;

import com.example.third.controller.session.MemberSession;
import com.example.third.controller.session.SessionConst;
import com.example.third.service.MemberService;
import com.example.third.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;

    @GetMapping("/add")
    public String order(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        MemberSession memberSession = (MemberSession)session.getAttribute(SessionConst.NAME);
        model.addAttribute("member", memberSession);
        return "/order/orderForm";
    }
}
