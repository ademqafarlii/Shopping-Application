package org.adem.followservice.service.impl;

import org.adem.followservice.dto.follow.FollowRequest;
import org.adem.followservice.dto.follower.FollowerDto;
import org.adem.followservice.dto.follower.FollowerPageResponseDto;
import org.adem.followservice.exception.FollowerNotFoundException;
import org.adem.followservice.exception.YouAreAlreadyFollowingException;
import org.adem.followservice.mapper.FollowerMapper;
import org.adem.followservice.model.Follower;
import org.adem.followservice.repository.FollowerRepository;
import org.adem.followservice.service.FollowerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class FollowerServiceImpl implements FollowerService {
    private final FollowerRepository followerRepository;
    private final FollowerMapper followerMapper;
    private final WebClient.Builder webCLientBuilder;

    public FollowerServiceImpl(FollowerRepository followerRepository, FollowerMapper followerMapper, WebClient.Builder webCLientBuilder) {
        this.followerRepository = followerRepository;
        this.followerMapper = followerMapper;
        this.webCLientBuilder = webCLientBuilder;
    }


    @Override
    public FollowerPageResponseDto getAllFollowers(int page, int count) {
        Page<Follower> followers = followerRepository.findAll(PageRequest.of(page,count));
        if (followers.isEmpty()){
            throw new FollowerNotFoundException("Follower not found");
        }
        return new FollowerPageResponseDto(
            followers.getContent().stream().map(followerMapper::toFollowerDto).toList()
                ,followers.getTotalElements()
                ,followers.getTotalPages()
                ,followers.hasNext()
        );
    }

    @Override
    public FollowerDto getFollowerByUsername(String username) {
        return followerRepository.findByUsername(username)
                .stream()
                .map(followerMapper::toFollowerDto)
                .findFirst()
                .orElseThrow(()->new FollowerNotFoundException("Follower not found"));
    }

    @Override
    public FollowerDto getFollowerByID(Integer id) {
        return followerRepository.findById(id)
                .stream()
                .map(followerMapper::toFollowerDto)
                .findFirst()
                .orElseThrow(()->new FollowerNotFoundException("Follower not found"));
    }

    @Override
    public void deleteFollowerById(Integer id) {
        followerRepository.deleteById(id);
    }

    @Override
    public void deleteFollowerByUsername(String userName) {
        followerRepository.deleteByUsername(userName);
    }

    @Override
    public void followBackByUsername(FollowRequest followRequest) {
        Optional<Follower> followerByUsername = followerRepository.findByUsername(followRequest.getUsername());
        if (followerByUsername.isEmpty()){
            throw new FollowerNotFoundException("Follower not found");
        }
        FollowRequest existingFollow = webCLientBuilder.build().get()
                .uri("https://FOLLOW-SERVICE/v1/follow/get-followed-customer-by-username",uriBuilder -> uriBuilder.queryParam("customerName",followerByUsername).build())
                .retrieve()
                .bodyToMono(FollowRequest.class)
                .block();
        if (existingFollow==null){
            followerRepository.save(followerMapper.toFollowerFromFollowRequest(followRequest));
        }else {
            throw new YouAreAlreadyFollowingException("You are already following this account");
        }
    }

}
