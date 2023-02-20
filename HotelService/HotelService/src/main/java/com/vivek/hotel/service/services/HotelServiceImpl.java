package com.vivek.hotel.service.services;

import com.vivek.hotel.service.entities.Hotel;
import com.vivek.hotel.service.exceptions.ResourceNotFoundException;
import com.vivek.hotel.service.respositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class HotelServiceImpl implements HotelService  {

    private HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository){
        this.hotelRepository=hotelRepository;
    }


    //create
    public Hotel create(Hotel hotel){
        String uid = UUID.randomUUID().toString();
        hotel.setId(uid);
        Hotel hotel1 = hotelRepository.save(hotel);
        return hotel1;
    }


    //get by hotel id
    public Hotel get(String hotelId){
        Hotel hotel= hotelRepository.findById(hotelId).orElseThrow(
                ()->new ResourceNotFoundException("Hotel ID not found on server : "+hotelId));

        return hotel;
    }

    //get all hotels
    public List<Hotel> getAll(){
        List<Hotel> allHotels = hotelRepository.findAll();
        return allHotels;
    }
}
