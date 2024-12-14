package com.example.newsservice.specification;

import com.example.newsservice.entity.News;
import org.springframework.data.jpa.domain.Specification;

public class NewsSpecifications {
    public static Specification<News> hasAuthor(String author) {
        return (root, query, cb) -> cb.equal(root.get("author").get("username"), author);
    }

    public static Specification<News> hasCategory(String category) {
        return (root, query, cb) -> cb.equal(root.get("category").get("name"), category);
    }
}