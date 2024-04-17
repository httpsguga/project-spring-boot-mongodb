package com.gustavoluiz.projectmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gustavoluiz.projectmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
}
