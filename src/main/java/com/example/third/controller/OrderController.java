package com.example.third.controller;

import com.example.third.controller.session.MemberSession;
import com.example.third.controller.session.SessionConst;
import com.example.third.domain.Item;
import com.example.third.domain.Order;
import com.example.third.service.ItemService;
import com.example.third.service.MemberService;
import com.example.third.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;

    private final ItemService itemService;


    @GetMapping("/add")
    public String order(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        MemberSession memberSession = (MemberSession)session.getAttribute(SessionConst.NAME);
        List<Item> items = itemService.allItems();
        model.addAttribute("items", items);
        model.addAttribute("member", memberSession);
        return "order/orderForm";
    }

    @PostMapping("/add")
    public String order(@RequestParam Long id,
                        @RequestParam Long itemId,
                        @RequestParam int count){
        System.out.println("id :" +id);
        orderService.order(id, itemId, count);
        return "redirect:/order/orders";
    }

    @GetMapping("/orders")
    public String orderList(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        MemberSession memberSession = (MemberSession) session.getAttribute(SessionConst.NAME);
        log.info("order controller ==> membersession : {} ", memberSession);
        List<Order> orders = orderService.findOrders(memberSession.getName());

        for (Order order : orders){
            log.info("orders ==> {} ", order);
        }
        model.addAttribute("orders", orders);
        return "order/orderList";
    }
}