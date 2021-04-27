package com.example.springdatajest.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Document 作用在类，标记实体类为文档对象
 * @Id 作用在成员变量，标记一个字段作为id主键
 * @Field 作用在成员变量，标记为文档的字段，并指定字段映射属性
 *   - type：字段类型，
 *   - index：是否索引
 *   - store：是否存储
 *   - analyzer：分词器名称
 *
 */
@Document(indexName = "item",shards = 1,replicas = 0)
public class Item {
    @Id
    Long id;

    @Field(type = FieldType.Text)
    String title;

    @Field(type = FieldType.Keyword)
    String category;

    @Field(type = FieldType.Keyword)
    String brand;

    @Field(type = FieldType.Double)
    Double price;

    @Field(index = false, type = FieldType.Keyword)
    String images;

    public Long getId() {
        return id;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Item setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Item setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Item setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Item setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getImages() {
        return images;
    }

    public Item setImages(String images) {
        this.images = images;
        return this;
    }

}
