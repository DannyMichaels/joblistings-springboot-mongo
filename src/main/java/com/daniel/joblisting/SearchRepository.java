package com.daniel.joblisting;

import com.daniel.joblisting.model.Post;

import java.util.List;

public interface SearchRepository {
  List<Post> findByText(String text);
}