package org.adem.followservice.mapper;

import org.adem.followservice.dto.follow.FollowRequest;
import org.adem.followservice.exception.CustomerNotFoundException;
import org.adem.followservice.model.Follow;
import org.springframework.stereotype.Service;

@Service
public class FollowMapper {
    public Follow toFollowModel(FollowRequest followRequest){
        if (followRequest ==null){
            throw new CustomerNotFoundException("Customer not found");
        }
        return Follow.builder()
                .username(followRequest.getUsername())
                .build();
    }
    public FollowRequest toFollowRequest(Follow follow){
        if (follow==null){
            throw new CustomerNotFoundException("Customer not found");
        }
        return FollowRequest.builder()
                .username(follow.getUsername())
                .build();
    }
}
