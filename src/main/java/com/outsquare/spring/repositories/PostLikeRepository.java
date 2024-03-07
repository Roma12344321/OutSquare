package com.outsquare.spring.repositories;

import com.outsquare.spring.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<Post,Integer> {
}
