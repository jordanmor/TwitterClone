package com.tts.TwitterClone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tts.TwitterClone.model.Tweet;
import com.tts.TwitterClone.model.User;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{
    List<Tweet> findAllByOrderByCreatedAtDesc();
    List<Tweet> findAllByUserOrderByCreatedAtDesc(User user);
    List<Tweet> findAllByUserInOrderByCreatedAtDesc(List<User> users);
}
