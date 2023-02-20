package com.vivek.rating.service.controllers;

import com.vivek.rating.service.entities.Rating;
import com.vivek.rating.service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService){
        this.ratingService=ratingService;
    }

    //post rating
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        Rating rating1 = ratingService.create(rating);
        return new ResponseEntity<>(rating1, HttpStatus.CREATED);
    }

    //get rating
    @RequestMapping("/{ratingId}")
    public ResponseEntity<Rating> getRating(@PathVariable String ratingId){
        Rating rating = ratingService.get(ratingId);
        return new ResponseEntity<>(rating,HttpStatus.OK);
    }

    //get all ratings
    @RequestMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> ratings = ratingService.getRatings();
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

    //get rating by userid
    @RequestMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable String userId){
        List<Rating> ratingsByUserId = ratingService.getRatingsByUserId(userId);
        return new ResponseEntity<>(ratingsByUserId,HttpStatus.OK);
    }

    //get rating by hotelid
    @RequestMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable String hotelId){
        List<Rating> ratingsByHotelId = ratingService.getRatingsByHotelId(hotelId);
        return new ResponseEntity<>(ratingsByHotelId,HttpStatus.OK);
    }

}
