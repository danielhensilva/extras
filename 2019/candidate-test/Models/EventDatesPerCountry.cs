using System;
using System.Collections.Generic;
using System.Linq;

namespace ConsoleApp.Models
{
    public class EventDatesPerCountry
    {
        public List<EventDate> Countries { get; set; }

        public AttendeesPerCountry ConvertToAttendeesPerCountry()
        {
            return new AttendeesPerCountry {
                Countries = Countries
                    .Select(x => x.ToCountry())
                    .ToList()
            };
        }
    }
    
    public class EventDate
    {
        public string CountryName { get; set; }
        public List<string> Attendees { get; set; }
        public DateTime? StartDate { get; set; }

        public AttendeeCollection ToCountry()
        {
            return new AttendeeCollection {
                Name = CountryName,
                Attendees = Attendees,
                AttendeeCount = Attendees.Count,
                StartDate = StartDate?.ToString("yyyy-MM-dd")
            };
        }
    }
}