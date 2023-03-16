package com.example.third.service;

import com.example.third.domain.*;
import com.example.third.repository.ItemRepository;
import com.example.third.repository.MemberRepository;
import com.example.third.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j // log 작성
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long member_id, Long id, int count){
        Member member = memberRepository.findById(member_id).get();
        Item item = itemRepository.findById(id).get();

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, orderItem);
        log.info("orderService ==> order : {}", order);
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


