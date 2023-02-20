package com.vivek.hotel.service.services;

import com.vivek.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    Hotel get(String id);

    List<Hotel> getAll();
}
