package com.frankmoley.business.reservation.client;

import com.frankmoley.business.reservation.domain.Guest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by frankmoley on 5/25/17.
 */
@Component
public class GuestServiceFallbackImpl implements GuestService {
    @Override
    public List<Guest> findAll(String emailAddress) {
        return Collections.emptyList();
    }

    @Override
    public Guest findOne(long id) {
        Guest guest = new Guest();
        guest.setFirstName("Guest");
        guest.setLastName("Occupied");
        return guest;
    }
}
