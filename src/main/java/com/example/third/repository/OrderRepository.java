package com.example.third.repository;

import com.example.third.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class OrderRepository {
    private final EntityManager em;

    @Autowired
    public OrderRepository(EntityManager em){
        this.em = em;
    }

    public void save(Order order){
        em.persist(order);
    }

    public Order findByOne(Long id){
        return Optional.ofNullable(em.find(Order.class, id)).get();
    }

    public List<Order> findAll(String name){
        return em.createQuery("select o from orders o join o.member m where m.name = :name", Order.class)
                .setParameter("name", name)
                .setMaxResults(100)
                .getResultList();
    }
}