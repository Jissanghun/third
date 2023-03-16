package com.example.third.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="order_item")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
    private int orderPrice; // 판매가
    private int orderQuantity; // 재고 수량

    public static OrderItem createOrderItem(Item item, int orderPrice, int orderQuantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setOrderQuantity(orderQuantity);

        item.removeStock(orderQuantity); // 주문수량만큼 재고를 감소시킨다

        return orderItem;
    }

    public void cancel() {
        this.item.addstock(orderQuantity); // 취소수량만큼 재고수량
    }
}
