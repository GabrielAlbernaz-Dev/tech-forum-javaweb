package com.gabrielalbernazdev.techforumjavaweb.post.domain.model;

import com.gabrielalbernazdev.techforumjavaweb.post.domain.vo.Content;
import com.gabrielalbernazdev.techforumjavaweb.post.domain.vo.Title;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private final Long id;
    private final Title title;
    private final Content content;
    private final User author;
    private final Long categoryId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final PostStatus status;

    private Post(
        final Long id,
        final String title,
        final String content,
        final User author,
        final Long categoryId,
        final LocalDateTime createdAt,
        final LocalDateTime updatedAt,
        final PostStatus status
    ) {
        this.id = id;
        this.title = Title.of(title);
        this.content = Content.of(content);
        this.author = Objects.requireNonNull(author, "Author cannot be null");
        this.categoryId = Objects.requireNonNull(categoryId, "Category ID cannot be null");
        this.createdAt = Objects.requireNonNull(createdAt, "Created date cannot be null");
        this.updatedAt = updatedAt;
        this.status = Objects.requireNonNull(status, "Status cannot be null");
    }

    public static Post create(
        final String title,
        final String content,
        final User author,
        final Long categoryId
    ) {
        return new Post(
            null,
            title,
            content,
            author,
            categoryId,
            LocalDateTime.now(),
            LocalDateTime.now(),
            PostStatus.ACTIVE
        );
    }

    public static Post reconstruct(
        final Long id,
        final String title,
        final String content,
        final User author,
        final Long categoryId,
        final LocalDateTime createdAt,
        final LocalDateTime updatedAt,
        final PostStatus status
    ) {
        return new Post(
            Objects.requireNonNull(id, "ID cannot be null"),
            title,
            content,
            author,
            categoryId,
            createdAt,
            updatedAt,
            status
        );
    }

    public Post update(String newTitle, String newContent) {
        return new Post(
            this.id,
            newTitle,
            newContent,
            this.author,
            this.categoryId,
            this.createdAt,
            LocalDateTime.now(),
            this.status
        );
    }

    public Post incrementViews() {
        return new Post(
            this.id,
            this.title.getValue(),
            this.content.getValue(),
            this.author,
            this.categoryId,
            this.createdAt,
            this.updatedAt,
            this.status
        );
    }

    public Post changeStatus(PostStatus newStatus) {
        return new Post(
            this.id,
            this.title.getValue(),
            this.content.getValue(),
            this.author,
            this.categoryId,
            this.createdAt,
            LocalDateTime.now(),
            Objects.requireNonNull(newStatus, "New status cannot be null")
        );
    }

    public Long getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public Content getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public PostStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(content, post.content) &&
                Objects.equals(author, post.author) &&
                Objects.equals(categoryId, post.categoryId) &&
                Objects.equals(createdAt, post.createdAt) &&
                Objects.equals(updatedAt, post.updatedAt) &&
                status == post.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, author, categoryId, createdAt, updatedAt, status);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title=" + title +
                ", content=" + content +
                ", author=" + author +
                ", categoryId=" + categoryId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                '}';
    }
}
