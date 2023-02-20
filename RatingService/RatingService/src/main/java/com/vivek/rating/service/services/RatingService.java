package com.vivek.rating.service.services;

import com.vivek.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating create(Rating rating);

    Rating get(String id);

    List<Rating> getRatings();

    List<Rating> getRatingsByUserId(String userId);

    List<Rating> getRatingsByHotelId(String hotelId);

}
