package com.vivek.user.service.controller;

import com.vivek.user.service.entities.User;
import com.vivek.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1=userService.saveUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    //single user
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name="ratingHotelRetry",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        logger.info("Hit url getUser");
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //spring circuit breaker..when circuit is closed ie. the dependent service is not down ie.
    //user service will communicate to hotel service and rating service
    //when circuit is open by default fallback method is execute
    //when circuit is half open then the service will still communicate with hotel and rating
    //open to half open happens after certain timeout time...
    //based on the percentage of success rate during the half open stage..it will switch to open or closed state
    //we use spring resiliance4j to implement circuit breaker,
    //resiliance can also be used for retry mechanism and ratelimiter-security and performance(limiting the service to same client)

    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
        logger.info("In rating Hotel Fallback method as circuit is open "+ex.getMessage());
        User user=User.builder()
                .userId("1234")
                .name("dummy")
                .about("dummy")
                .email("dummy@gmail.com")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }



    //all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return new ResponseEntity<>(allUser,HttpStatus.OK);
    }


}
