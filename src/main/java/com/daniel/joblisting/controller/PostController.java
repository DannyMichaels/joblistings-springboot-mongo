package com.daniel.joblisting.controller;

import com.daniel.joblisting.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.daniel.joblisting.model.Post;
import java.util.List;

@RestController
public class
PostController {
    @Autowired
    PostRepository postRepo;

    @RequestMapping("/")
    public String index() {
        return "Welcome to the Job Listing App!";
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        System.out.println("Post: " + post.toString());
        return postRepo.save(post);
    }
}
