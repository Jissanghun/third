package com.example.third.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ItemDTO {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    // toString 여부
}
