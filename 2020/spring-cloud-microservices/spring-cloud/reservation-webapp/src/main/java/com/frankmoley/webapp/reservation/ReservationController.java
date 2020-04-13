package com.frankmoley.webapp.reservation;

import com.frankmoley.webapp.reservation.client.RoomReservationService;
import com.frankmoley.webapp.reservation.domain.RoomReservation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping(value="/reservations")
public class ReservationController {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final RoomReservationService roomReservationService;


    @Autowired
    public ReservationController(RoomReservationService roomReservationService){
        super();
        this.roomReservationService = roomReservationService;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String getReservations(@RequestParam(value="date", required=false)String dateString, Model model){
        String date = dateString;
        if(StringUtils.isBlank(dateString)){
            date = this.createTodayDateString();
        }
        List<RoomReservation> roomReservations = this.roomReservationService.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations", roomReservations);
        return "reservations";
    }

    public String createTodayDateString(){
        return DATE_FORMAT.format(new Date());
    }
}