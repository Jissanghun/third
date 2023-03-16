package com.example.third;

import com.example.third.repository.*;
import com.example.third.service.ItemService;
import com.example.third.service.MemberService;
import com.example.third.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final EntityManager em;
    //private final DataSource dataSource;
//    @Autowired
//    public SpringConfig(EntityManager em) {//DataSource dataSource,
//        //this.dataSource = dataSource;
//        this.em = em;
//    }


    @Bean
    public ItemService itemService(){
        return new ItemService(itemRepository());
    }
    @Bean
    public ItemRepository itemRepository(){
        return new JpaItemRepository(em);
        //return new MemoryItemRepository();
    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }




    @Bean
    public OrderService orderService(){
        return new OrderService(orderRepository(),memberRepository(), itemRepository());

    }

    @Bean
    public OrderRepository orderRepository(){
        return new OrderRepository(em);
    }

}
