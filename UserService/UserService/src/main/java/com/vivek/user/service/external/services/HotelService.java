package com.vivek.user.service.external.services;

import com.vivek.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("HOTEL-SERVICE")
public interface HotelService {

    @RequestMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);

}
