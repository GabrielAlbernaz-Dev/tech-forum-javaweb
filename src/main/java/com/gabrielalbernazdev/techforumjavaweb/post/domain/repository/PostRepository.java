package com.gabrielalbernazdev.techforumjavaweb.post.domain.repository;

import com.gabrielalbernazdev.techforumjavaweb.post.domain.model.Post;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PostRepository {
    List<Post> findRecentPosts(Connection connection, int limit) throws SQLException;
}
