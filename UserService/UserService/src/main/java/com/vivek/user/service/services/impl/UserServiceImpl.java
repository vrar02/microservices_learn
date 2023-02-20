package com.vivek.user.service.services.impl;

import com.vivek.user.service.entities.Hotel;
import com.vivek.user.service.entities.Rating;
import com.vivek.user.service.entities.User;
import com.vivek.user.service.exceptions.ResourceNotFoundException;
import com.vivek.user.service.external.services.HotelService;
import com.vivek.user.service.repositories.UserRepository;
import com.vivek.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        logger.info("In get User");

        User user= userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User ID not found on server : "+userId));

        ResponseEntity<Rating[]> responseRating=restTemplate
                .getForEntity("http://RATING-SERVICE:8083/ratings/user/"+user.getUserId(),Rating[].class);
        Rating[] ratingList=responseRating.getBody();
        List<Rating> updatedRatingList =new ArrayList<>();
        for(Rating rating: ratingList){
//            ResponseEntity<Hotel> responseHotel=restTemplate.getForEntity(
//                    "http://HOTEL-SERVICE:8082/hotels/"+rating.getHotelId(),Hotel.class);
//            rating.setHotel(responseHotel.getBody());

            Hotel hotel=hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            updatedRatingList.add(rating);
        }

        user.setRatings(updatedRatingList);
        return user;
    }
}
