package com.gabrielalbernazdev.techforumjavaweb.user.domain.model;

import com.gabrielalbernazdev.techforumjavaweb.common.domain.vo.Email;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.vo.Password;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.vo.Username;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class User {
    private final UUID id;
    private final Username username;
    private final Email email;
    private final Password password;
    private final LocalDateTime createdAt;
    private final Set<Role> roles;
    private final Set<UUID> followers;
    private final Set<UUID> following;

    private User(
        final UUID id,
        final String username,
        final String email,
        final Password password,
        final LocalDateTime createdAt,
        final Set<Role> roles,
        final Set<UUID> followers,
        final Set<UUID> following) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.username = Username.of(username);
        this.email = Email.of(email);
        this.password = Objects.requireNonNull(password, "Password cannot be null");
        this.createdAt = Objects.requireNonNull(createdAt, "CreatedAt cannot be null");
        this.roles = Set.copyOf(roles);
        this.followers = new HashSet<>(followers);
        this.following = new HashSet<>(following);
    }

    public static User create(String username, String email, String plainPassword, Set<Role> roles) {
        return new User(
                UUID.randomUUID(),
                username,
                email,
                Password.fromPlain(plainPassword),
                LocalDateTime.now(),
                roles,
                new HashSet<>(),
                new HashSet<>()
        );
    }

    public static User reconstruct(
        final UUID id,
        final String username,
        final String email,
        final String passwordHash,
        final LocalDateTime createdAt,
        final Set<Role> roles,
        final Set<UUID> followers,
        final Set<UUID> following) {
        return new User(
                id,
                username,
                email,
                Password.fromHash(passwordHash),
                createdAt,
                roles,
                followers,
                following
        );
    }

    public FollowStatus follow(UUID userIdToFollow) {
        if(getId().equals(userIdToFollow)) {
            return FollowStatus.CANNOT_FOLLOW_SELF;
        }

        if(following.add(userIdToFollow)) {
            return FollowStatus.SUCCESS;
        }

        return FollowStatus.ALREADY_FOLLOWING;
    }

    public FollowStatus unfollow(UUID userIdToUnfollow) {
        if(getId().equals(userIdToUnfollow)) {
            return FollowStatus.CANNOT_UNFOLLOW_SELF;
        }

        if(following.remove(userIdToUnfollow)) {
            return FollowStatus.SUCCESS;
        }

        return FollowStatus.ALREADY_UNFOLLOWING;
    }

    public UUID getId() {
        return id;
    }

    public Username getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Stream<UUID> getFollowers() {
        return followers.stream();
    }

    public Set<UUID> getFollowing() {
        return following;
    }

    public Password getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(createdAt, user.createdAt) && Objects.equals(roles, user.roles) && Objects.equals(followers, user.followers) && Objects.equals(following, user.following);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, createdAt, roles, followers, following);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", roles=" + roles +
                ", followers=" + followers +
                ", following=" + following +
                '}';
    }
}
