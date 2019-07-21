using System;
using System.Collections.Generic;

namespace ConsoleApp.Models
{
    public class AttendeesPerCountry
    {
        public List<AttendeeCollection> Countries { get; set; }
    }
    
    public class AttendeeCollection
    {
        public int AttendeeCount { get; set; }
        public List<string> Attendees { get; set; }
        public string Name { get; set; }
        public string StartDate { get; set; }
    }
}