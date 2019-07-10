package com.tts.TwitterClone.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.TwitterClone.model.Tweet;
import com.tts.TwitterClone.model.User;
import com.tts.TwitterClone.service.TweetService;
import com.tts.TwitterClone.service.UserService;

@Controller
public class TweetController {
    @Autowired
    private UserService userService;
	
    @Autowired
    private TweetService tweetService;
    
    // GET all tweets
    @GetMapping(value= {"/tweets", "/"})
    public String getFeed(Model model){
        List<Tweet> tweets = tweetService.findAll();
        model.addAttribute("tweetList", tweets);
        return "feed";
    }
    
    // Serve up the newTweet page
    @GetMapping(value = "/tweets/new")
    public String getTweetForm(Model model) {
        model.addAttribute("tweet", new Tweet());
        return "newTweet";
    }
    
    // Handles the form submission from the newTweet page
    // Gets the logged in user and associates them with the tweet
    @PostMapping(value = "/tweets")
    public String submitTweetForm(@Valid Tweet tweet, BindingResult bindingResult, Model model) {
        User user = userService.getLoggedInUser();
        if (!bindingResult.hasErrors()) {
            tweet.setUser(user);
            tweetService.save(tweet);
            model.addAttribute("successMessage", "Tweet successfully created!");
            model.addAttribute("tweet", new Tweet());
        }
        return "newTweet";
    }
}
