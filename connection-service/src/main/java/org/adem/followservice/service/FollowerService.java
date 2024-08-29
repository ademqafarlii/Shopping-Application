package org.adem.followservice.service;


import org.adem.followservice.dto.follow.FollowRequest;
import org.adem.followservice.dto.follower.FollowerDto;
import org.adem.followservice.dto.follower.FollowerPageResponseDto;

public interface FollowerService {

    FollowerPageResponseDto getAllFollowers(int page, int count);

    FollowerDto getFollowerByUsername(String username);

    FollowerDto getFollowerByID(Integer id);

    void deleteFollowerById(Integer id);

    void deleteFollowerByUsername(String userName);

    void followBackByUsername(FollowRequest followRequest);

}
