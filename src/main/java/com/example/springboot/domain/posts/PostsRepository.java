package com.example.springboot.domain.posts;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostsRepository extends MongoRepository<Posts,Long> {
}
