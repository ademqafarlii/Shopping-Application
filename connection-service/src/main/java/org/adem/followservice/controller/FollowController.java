package org.adem.followservice.controller;

import org.adem.followservice.dto.follow.FollowPageResponse;
import org.adem.followservice.dto.follow.FollowRequest;
import org.adem.followservice.model.Follow;
import org.adem.followservice.service.FollowService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/follow")
    @ResponseStatus(HttpStatus.CREATED)
    public Follow follow(@RequestBody FollowRequest followRequest) {
        return followService.follow(followRequest);
    }

    @DeleteMapping("/unfollow")
    @ResponseStatus(HttpStatus.OK)
    public void unfollow(@RequestParam String username) {
        followService.unfollow(username);
    }

    @GetMapping("/get-followed-customers")
    @ResponseStatus(HttpStatus.OK)
    public FollowPageResponse getFollowedCustomers(@RequestParam int page, @RequestParam int count) {
        return followService.getFollowedCustomers(page,count);
    }


    @GetMapping("/get-followed-customer-by-username")
    @ResponseStatus(HttpStatus.OK)
    public FollowRequest getFollowedCustomerByUsername(@RequestParam String username) {
        return followService.getFollowedCustomerByUsername(username);
    }

    @GetMapping("/get-followed-customer-by-id")
    @ResponseStatus(HttpStatus.OK)
    public FollowRequest getFollowedCustomerByID(@RequestParam Integer id) {
        return followService.getFollowedCustomerByID(id);
    }
}
