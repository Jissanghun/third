package com.example.third.repository;

import com.example.third.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryItemRepositoryTest {
    MemoryItemRepository itemRepository = new MemoryItemRepository();
    @Test
    @DisplayName("상품 저장 테스트")
    void 상품_저장_테스트() {
        //given
        Item item = new Item("itemA", 1000, 100);
        //when
        Item save = itemRepository.save(item);
        System.out.println("save = " + save + " : " + save.getClass());;
        //then
        Item find = itemRepository.findById(save.getId()).get();
        System.out.println("find = " + find + " : " + find.getClass());
        assertThat(find).isEqualTo(save);
    }

    @Test
    void findAll() {
        //given
        Item item = new Item("itemA", 1000, 100);
        Item item1 = new Item("itemB", 2000, 200);
        itemRepository.save(item);
        itemRepository.save(item1);
        //when
        List<Item> all = itemRepository.findAll();
        //then
        assertThat(all).hasSize(2);
        assertThat(all).contains(item);
        System.out.println("all = " + all);
        assertThat(all.get(0)).isEqualTo(item);
        assertThat(all.get(1)).isEqualTo(item1);
    }

    @Test
    void update() {
        //given
        Item item = new Item("itemA", 1000, 100);
        Item save = itemRepository.save(item);

        Item item2 = new Item("itemChanged", 2000, 200);
        System.out.println("save = " + save);
        //when
        itemRepository.update(save.getId(), item2);
        System.out.println("item2 = " + item2);
        //then
        Item result = itemRepository.findById(save.getId()).get();
        //System.out.println("find = " + find);
        System.out.println("result = " + result);
        System.out.println("save = " + save);
        assertThat(result.getId()).isEqualTo(save.getId());
        assertThat(result.getItemName()).isEqualTo("itemChanged");
        assertThat(result.getPrice()).isEqualTo(2000);
        assertThat(result.getQuantity()).isEqualTo(200);
    }
}