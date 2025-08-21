package com.gabrielalbernazdev.techforumjavaweb.post.infra.repository;

import com.gabrielalbernazdev.techforumjavaweb.post.domain.model.Post;
import com.gabrielalbernazdev.techforumjavaweb.post.domain.repository.PostRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SqlitePostRepository implements PostRepository {
    @Override
    public List<Post> findRecentPosts(Connection connection, int limit) throws SQLException {
        return null;
    }
}
