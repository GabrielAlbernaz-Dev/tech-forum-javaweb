package com.gabrielalbernazdev.techforumjavaweb.user.domain.model;

import com.gabrielalbernazdev.techforumjavaweb.common.domain.vo.Email;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.vo.Password;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.vo.Username;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private UUID userId;
    private Username username;
    private Email email;
    private String plainPassword;
    private LocalDateTime createdAt;
    private Set<Role> roles;
    private Set<UUID> followers;
    private Set<UUID> following;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        username = Username.of("testusername");
        email = Email.of("test@example.com");
        plainPassword = "StrongP@ssw0rd";
        createdAt = LocalDateTime.now();
        roles = Set.of(Role.create("ROLE_USER"));
        followers = Set.of(UUID.randomUUID());
        following = Set.of(UUID.randomUUID());
    }

    @Test
    void createShouldCreateUserWithValidAttributes() {
        User user = User.create(username.getValue(), email.getValue(), plainPassword, roles);

        assertNotNull(user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertNotNull(user.getCreatedAt());
        assertTrue(user.getPassword().matches(plainPassword));
    }

    @Test
    void reconstructShouldRestoreUserFromHash() {
        Password password = Password.fromPlain(plainPassword);
        User user = User.reconstruct(userId, username.getValue(), email.getValue(), password.getHash(), createdAt, roles, followers, following);

        assertEquals(userId, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(createdAt, user.getCreatedAt());
        assertTrue(user.getPassword().matches(plainPassword));
    }

    @Test
    void followShouldReturnSuccessWhenAddingNewUser() {
        User user = User.create(username.getValue(), email.getValue(), plainPassword, roles);
        UUID userIdToFollow = UUID.randomUUID();

        FollowStatus status = user.follow(userIdToFollow);

        assertEquals(FollowStatus.SUCCESS, status);
        assertTrue(user.getFollowing().contains(userIdToFollow));
    }

    @Test
    void followShouldReturnCannotFollowSelfWhenFollowingSelf() {
        User user = User.create(username.getValue(), email.getValue(), plainPassword, roles);

        FollowStatus status = user.follow(user.getId());

        assertEquals(FollowStatus.CANNOT_FOLLOW_SELF, status);
        assertFalse(user.getFollowing().contains(user.getId()));
    }

    @Test
    void followShouldReturnAlreadyFollowingWhenUserAlreadyFollowed() {
        UUID userIdToFollow = UUID.randomUUID();
        User user = User.reconstruct(
            userId,
            username.getValue(),
            email.getValue(),
            plainPassword,
            createdAt,
            roles,
            followers,
            Set.of(userIdToFollow)
        );

        FollowStatus status = user.follow(userIdToFollow);

        assertEquals(FollowStatus.ALREADY_FOLLOWING, status);
        assertTrue(user.getFollowing().contains(userIdToFollow));
    }

    @Test
    void unfollowShouldReturnSuccessWhenRemovingFollowedUser() {
        UUID userIdToUnfollow = UUID.randomUUID();
        User user = User.reconstruct(
            userId,
            username.getValue(),
            email.getValue(),
            plainPassword,
            createdAt,
            roles,
            followers,
            Set.of(userIdToUnfollow)
        );

        FollowStatus status = user.unfollow(userIdToUnfollow);

        assertEquals(FollowStatus.SUCCESS, status);
        System.out.println(user);
        assertFalse(user.getFollowing().contains(userIdToUnfollow));
    }

    @Test
    void unfollowShouldReturnCannotUnfollowSelfWhenUnfollowingSelf() {
        User user = User.create(username.getValue(), email.getValue(), plainPassword, roles);

        FollowStatus status = user.unfollow(user.getId());

        assertEquals(FollowStatus.CANNOT_UNFOLLOW_SELF, status);
    }

    @Test
    void unfollowShouldReturnAlreadyUnfollowingWhenUserNotFollowed() {
        User user = User.create(username.getValue(), email.getValue(), plainPassword, roles);
        UUID userIdToUnfollow = UUID.randomUUID();

        FollowStatus status = user.unfollow(userIdToUnfollow);

        assertEquals(FollowStatus.ALREADY_UNFOLLOWING, status);
        assertFalse(user.getFollowing().contains(userIdToUnfollow));
    }
}
