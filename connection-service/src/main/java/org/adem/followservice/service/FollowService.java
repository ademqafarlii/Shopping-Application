package org.adem.followservice.service;

import org.adem.followservice.dto.follow.FollowPageResponse;
import org.adem.followservice.dto.follow.FollowRequest;
import org.adem.followservice.model.Follow;

public interface FollowService {
    Follow follow(FollowRequest followRequest);

    void unfollow(String username);

    FollowPageResponse getFollowedCustomers(int page, int count);

    FollowRequest getFollowedCustomerByUsername(String username);
    FollowRequest getFollowedCustomerByID(Integer id);
}
