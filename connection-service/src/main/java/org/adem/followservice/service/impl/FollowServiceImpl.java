package org.adem.followservice.service.impl;

import org.adem.followservice.dto.follow.FollowPageResponse;
import org.adem.followservice.dto.follow.FollowRequest;
import org.adem.followservice.dto.customer.CustomerRegistrationRequestDto;
import org.adem.followservice.exception.CustomerNotFoundException;
import org.adem.followservice.exception.FollowNotFoundException;
import org.adem.followservice.exception.FollowedAccountAlreadyExists;
import org.adem.followservice.mapper.FollowMapper;
import org.adem.followservice.model.Follow;
import org.adem.followservice.repository.FollowRepository;
import org.adem.followservice.service.FollowService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;


@Service
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;
    private final FollowMapper followMapper;
    private final WebClient.Builder webClientBuilder;

    public FollowServiceImpl(FollowRepository followRepository, FollowMapper followMapper, WebClient.Builder webClientBuilder) {
        this.followRepository = followRepository;
        this.followMapper = followMapper;
        this.webClientBuilder = webClientBuilder;
    }


    @Override
    public Follow follow(FollowRequest followRequest) {
        String username = followRequest.getUsername();

        Optional<Follow> existingFollow = followRepository.getFollowByUsername(followRequest.getUsername());
        if (existingFollow.isPresent()){
            throw new FollowedAccountAlreadyExists("You are already following this account");
        }

        CustomerRegistrationRequestDto customerDto = webClientBuilder.build().get()
                .uri("http://CUSTOMER-SERVICE/v1/customer/exist-user", uriBuilder -> uriBuilder.queryParam("username", username).build())
                .retrieve()
                .bodyToMono(CustomerRegistrationRequestDto.class)
                .block();

        if (customerDto != null && username.equals(customerDto.getUsername())) {
           return followRepository.save(followMapper.toFollowModel(followRequest));
        } else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }


    @Override
    @Transactional
    public void unfollow(String username) {
        Optional<Follow> follow = followRepository.getFollowByUsername(username);
        if (follow.isEmpty()){
            throw new FollowNotFoundException("You are not following this account");
        }
        followRepository.deleteByUsername(username);
    }

    @Override
    public FollowPageResponse getFollowedCustomers(int page, int count) {
        Page<Follow> followPage = followRepository.findAll(PageRequest.of(page,count));
        if (followPage.isEmpty()){
            throw new CustomerNotFoundException("No followed accounts available");
        }
        return new FollowPageResponse(
            followPage.stream().map(followMapper::toFollowRequest).toList()
            ,followPage.getTotalElements()
            ,followPage.getTotalPages()
            ,followPage.hasNext()
        );
    }

    @Override
    public FollowRequest getFollowedCustomerByUsername(String username) {

        return followRepository.getFollowByUsername(username)
                .stream()
                .map(followMapper::toFollowRequest)
                .findFirst()
                .orElseThrow(() -> new FollowNotFoundException("Follow not found for user: " + username));
    }


    @Override
    public FollowRequest getFollowedCustomerByID(Integer id) {
        return followRepository.findById(id)
                .stream()
                .map(followMapper::toFollowRequest)
                .findFirst()
                .orElseThrow(()->new CustomerNotFoundException("Customer not found"));
    }


}
