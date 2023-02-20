package com.vivek.hotel.service.respositories;

import com.vivek.hotel.service.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface HotelRepository extends JpaRepository<Hotel,String> {

}
