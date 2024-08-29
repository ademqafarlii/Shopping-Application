package org.adem.followservice.controller;


import org.adem.followservice.dto.follow.FollowRequest;
import org.adem.followservice.dto.follower.FollowerDto;
import org.adem.followservice.dto.follower.FollowerPageResponseDto;
import org.adem.followservice.service.FollowerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/follower")
public class FollowerController {
    private final FollowerService followerService;

    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get-all-followers")
    public FollowerPageResponseDto getFollowers(@RequestParam int page, @RequestParam int count){
        return followerService.getAllFollowers(page,count);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get-follower-by-username")
    public FollowerDto getFollowerByUsername(@RequestParam String username){
        return followerService.getFollowerByUsername(username);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get-follower-by-id")
    public FollowerDto getFollowerByID(@RequestParam Integer id){
        return followerService.getFollowerByID(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete-follower-by-id")
    public void deleteFollowerByID(@RequestParam Integer id){
        followerService.deleteFollowerById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete-follower-by-username")
    public void deleteFollowerByUsername(@RequestParam String username){
        followerService.deleteFollowerByUsername(username);
    }

    @PostMapping("/follow-back-by-username")
    @ResponseStatus(HttpStatus.OK)
    void followBackByUsername(@RequestBody FollowRequest followRequest){
        followerService.followBackByUsername(followRequest);
    }





}
