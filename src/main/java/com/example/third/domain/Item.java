package com.example.third.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Item{
    @Id // javax.persistence.Id ==> primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql auto-increment
    @Column(name = "item_id")
    private Long id;
    @Column(name="item_name" , nullable = false, length = 100)
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
