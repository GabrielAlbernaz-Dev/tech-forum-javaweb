package com.gabrielalbernazdev.techforumjavaweb.user.domain.model;

public enum FollowStatus {
    SUCCESS,
    INVALID_USER,
    CANNOT_FOLLOW_SELF,
    CANNOT_UNFOLLOW_SELF,
    NOT_FOLLOWING,
    ALREADY_FOLLOWING,
    ALREADY_UNFOLLOWING,
}
