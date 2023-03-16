package com.example.third.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @ManyToOne  // 단방향 연관관계
    @JoinColumn(name="member_id") // FK = member_id
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /// orderItem 추가예정 , delivery , 결제
    public static Order createOrder(Member member){
        Order order = new Order();
        order.setMember(member);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ORDER);
        /// orderItem 추가예정 , delivery , 결제
        return order;
    }

    // orderitem 추가 예정
    public void cancel(){
        status = OrderStatus.CANCEL;
        // orderitem 추가 예정 . 아이템 별 하나씩 cancel
    }

}
