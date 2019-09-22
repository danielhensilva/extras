using System;
using System.Collections.Generic;
using System.Linq;

namespace ConsoleApp.Models
{
    public class CountryAvailabilities : List<CountryAvailability>
    {
        public CountryAvailability GetOrCreateByCountry(string countryName)
        {
            var item = this.FirstOrDefault(x => countryName == x.Country);

            if (item != null)
                return item;

            item = new CountryAvailability {
                Country = countryName,
                Availabilities = new AvailabilityCollection()
            };

            this.Add(item);
            return item;
        }
    }

    public class CountryAvailability
    {
        public string Country { get; set; }
        public AvailabilityCollection Availabilities { get; set; }
        
        public EventDate FindBestEventDate()
        {
            var availabilities = Availabilities.OrderBy(x => x.Date).ToArray();
            
            var bestEventDate = new EventDate {
                CountryName = Country,
                Attendees = new List<string>(),
                StartDate = null
            };
            
            for (var i = 0; i < availabilities.Length - 1; i++)
            {
                var current = availabilities[i];
                var next = availabilities[i + 1];

                // Skip non subsequent days
                if (current.Date.AddDays(1) != next.Date)
                    continue;

                // Skip worst days
                var emailsCanMakeBothDays = current.Emails.Intersect(next.Emails).ToList();
                if (emailsCanMakeBothDays.Count <= bestEventDate.Attendees.Count)
                    continue;

                bestEventDate.StartDate = current.Date;
                bestEventDate.Attendees = emailsCanMakeBothDays;
            }

            return bestEventDate;
        }
    }

    public class AvailabilityCollection : List<Availability>
    {
        public Availability GetOrCreateByDate(DateTime eventDate)
        {
            var item = this.FirstOrDefault(x => x.Date == eventDate);

            if (item != null)
                return item;

            item = new Availability {
                Date = eventDate,
                Emails = new List<string>()
            };

            this.Add(item);
            return item;
        }
    }

    public class Availability
    {
        public DateTime Date { get; set; }
        public List<string> Emails { get; set; }
    }
}