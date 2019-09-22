using System.Collections.Generic;

namespace ConsoleApp.Models
{
    public class PartnersResponse
    {
        public List<Partner> Partners { get; set; }
    }
    
    public class Partner
    {
        public string Email { get; set; }
        public string Country { get; set; }
        public List<string> AvailableDates { get; set; }
    }
}