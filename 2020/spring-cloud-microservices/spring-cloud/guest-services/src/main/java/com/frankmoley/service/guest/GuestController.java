package com.frankmoley.service.guest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by frankmoley on 5/23/17.
 */
@RestController
@RequestMapping(value="/guests")
@Api(value="guests", description = "Data service operations on guests", tags=("guests"))
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    @RequestMapping(method= RequestMethod.GET)
    @ApiOperation(value="Get All Guests", notes="Gets all guests in the system", nickname="getGuests")
    public List<Guest> findAll(@RequestParam(name="emailAddress", required = false)String emailAddress){
        if(StringUtils.isNotBlank(emailAddress)){
            return Collections.singletonList(this.guestRepository.findByEmailAddress(emailAddress));
        }
        return (List<Guest>) this.guestRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Guest findOne(@PathVariable(name="id")long id){
        return this.guestRepository.findOne(id);
    }
}
