package com.example.springdatajest;

import com.example.springdatajest.entity.Item;
import com.example.springdatajest.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ESTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testCreateIndex(){
        elasticsearchRestTemplate.createIndex(Item.class);
        elasticsearchRestTemplate.putMapping(Item.class);
    }

    @Test
    public void testAdd(){
        Item item = new Item();
        item.setBrand("SONY").setId(1L).setCategory("camera").setPrice(1500.0).setTitle("asd").setImages("asasf");
        itemRepository.save(item);
    }

}
