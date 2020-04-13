package com.frankmoley.business.reservation.client;

import com.frankmoley.business.reservation.domain.Room;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by frankmoley on 5/23/17.
 */
@FeignClient(value="ROOMSERVICES")
public interface RoomService {

    @RequestMapping(value="/rooms", method= RequestMethod.GET)
    List<Room> findAll(@RequestParam(name="roomNumber", required=false)String roomNumber);

    @RequestMapping(value="/rooms/{id}", method = RequestMethod.GET)
    Room findOne(@PathVariable("id")long id);
}
