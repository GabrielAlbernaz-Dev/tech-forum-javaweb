package com.gabrielalbernazdev.techforumjavaweb.post.service;

import com.gabrielalbernazdev.techforumjavaweb.post.domain.model.Post;
import com.gabrielalbernazdev.techforumjavaweb.post.domain.repository.PostRepository;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class PostService {
    private final DataSource dataSource;
    private final PostRepository postRepository;

    @Inject
    public PostService(DataSource dataSource, PostRepository postRepository) {
        this.dataSource = dataSource;
        this.postRepository = postRepository;
    }

    public List<Post> getRecentPosts(int limit) throws SQLException {
        return postRepository.findRecentPosts(dataSource.getConnection(), limit);
    }
}
