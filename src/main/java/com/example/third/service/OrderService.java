package com.example.third.service;

import com.example.third.domain.Member;
import com.example.third.domain.Order;
import com.example.third.domain.OrderStatus;
import com.example.third.repository.MemberRepository;
import com.example.third.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long order(Long member_id){
        Member member = memberRepository.findById(member_id).get();
        // order + orderItem + item
        Order order = Order.createOrder(member);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findByOne(orderId);
        // jpa 가 dirty checking을 해서 자동으로 db에 update를 해줌.
        order.cancel();
    }
    public List<Order> findOrders(String username){
        return orderRepository.findAll(username);
    }
}


