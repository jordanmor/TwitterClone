package com.tts.TwitterClone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tts.TwitterClone.model.Tweet;
import com.tts.TwitterClone.model.User;
import com.tts.TwitterClone.repository.TweetRepository;

@Service
public class TweetService {
   @Autowired
    private TweetRepository tweetRepository;

    public List<Tweet> findAll() {
        List<Tweet> tweets = tweetRepository.findAllByOrderByCreatedAtDesc();
        return tweets;
    }
	
    public List<Tweet> findAllByUser(User user) {
        List<Tweet> tweets = tweetRepository.findAllByUserOrderByCreatedAtDesc(user);
        return tweets;
    }
	
    public List<Tweet> findAllByUsers(List<User> users){
        List<Tweet> tweets = tweetRepository.findAllByUserInOrderByCreatedAtDesc(users);
        return tweets;
    }
	
    public void save(Tweet tweet) {
        tweetRepository.save(tweet);
    }
}
