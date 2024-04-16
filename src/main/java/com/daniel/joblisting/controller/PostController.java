package com.daniel.joblisting.controller;

import com.daniel.joblisting.PostRepository;
import com.daniel.joblisting.SearchRepository;
import com.daniel.joblisting.SearchRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.daniel.joblisting.model.Post;
import java.util.List;
import java.util.Optional;

@RestController
public class
PostController {
    @Autowired
    PostRepository postRepo;

    @Autowired
    SearchRepository searchRepo;

    @RequestMapping("/")
    public String index() {
        return "Welcome to the Job Listing App!";
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    @GetMapping("/posts/search")
    public List<Post> searchPosts(@RequestParam String text) {
        System.out.println("Searching for: " + text);
        return searchRepo.findByText(text);
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable String id)
    {
        Optional optional = postRepo.findById(id);
        if (optional.isPresent()) {
            return (Post) optional.get();
        } else {
            return null;
        }
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        System.out.println("Post: " + post.toString());
        return postRepo.save(post);
    }
}
