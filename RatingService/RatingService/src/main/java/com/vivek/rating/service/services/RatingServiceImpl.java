package com.vivek.rating.service.services;

import com.vivek.rating.service.entities.Rating;
import com.vivek.rating.service.exceptions.ResourceNotFoundException;
import com.vivek.rating.service.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        Rating rating1 = ratingRepository.save(rating);
        return rating1;
    }

    @Override
    public Rating get(String id) {
        return ratingRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("not found by id "+id));
    }

    @Override
    public List<Rating> getRatings() {

        List<Rating> allRatings = ratingRepository.findAll();
        return allRatings;
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        List<Rating> allByUserId = ratingRepository.findAllByUserId(userId);
        return allByUserId;
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        List<Rating> allByHotelId = ratingRepository.findAllByHotelId(hotelId);
        return allByHotelId;
    }
}
