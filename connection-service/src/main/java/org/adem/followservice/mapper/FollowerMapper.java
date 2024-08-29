package org.adem.followservice.mapper;

import org.adem.followservice.dto.follow.FollowRequest;
import org.adem.followservice.dto.follower.FollowerDto;
import org.adem.followservice.exception.FollowerNotFoundException;
import org.adem.followservice.model.Follower;
import org.springframework.stereotype.Service;

@Service
public class FollowerMapper {

    public Follower toFollowerModel(FollowerDto followerDto){
        if (followerDto==null){
            throw new FollowerNotFoundException("Follower not found");
        }
        return Follower.builder()
                .username(followerDto.getUsername())
                .build();
    }

    public FollowerDto toFollowerDto(Follower follower){
        if (follower==null){
            throw new FollowerNotFoundException("Follower not found");
        }
        return FollowerDto.builder()
                .username(follower.getUsername())
                .build();
    }

    public FollowerDto toFollowerDtoFromFollowRequest(FollowRequest followRequest){
        if (followRequest==null){
            throw new FollowerNotFoundException("Follower not found");
        }
        return FollowerDto.builder()
                .username(followRequest.getUsername())
                .build();
    }


    public Follower toFollowerFromFollowRequest(FollowRequest followRequest){
        if (followRequest==null){
            throw new FollowerNotFoundException("Follower not found");
        }
        return Follower.builder()
                .username(followRequest.getUsername())
                .build();
    }
}
