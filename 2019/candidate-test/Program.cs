using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ConsoleApp.Models;

namespace ConsoleApp
{
    class Program
    {
        static async Task Main(string[] args)
        {
            Console.WriteLine("Initializing...");
            const string userKey = "f15c41a0b7ca57a92f4bb44d2714";
            var candidateTestManager = new CandidateTestManager(userKey);

            Console.WriteLine("Fetching partners");
            var response = await candidateTestManager.GetPartnersAsync();

            Console.WriteLine("Converting partners to availabilities per country");
            var countryAvailabilities = new CountryAvailabilities();
            foreach (var partner in response.Partners)
            {
                var countryAvailability = countryAvailabilities.GetOrCreateByCountry(partner.Country);

                foreach (var availability in partner.AvailableDates)
                {
                    var date = DateTime.Parse(availability);
                    var dateAvailability = countryAvailability.Availabilities.GetOrCreateByDate(date);
                    dateAvailability.Emails.Add(partner.Email);
                }
            }

            Console.WriteLine("Find best event dates per country");
            var bestEventDates = new EventDatesPerCountry {
                Countries = new List<EventDate>(),
            };
            
            foreach (var countryAvailability in countryAvailabilities)
            {
                var bestEventDate = countryAvailability.FindBestEventDate();
                bestEventDate.CountryName = countryAvailability.Country;
                bestEventDates.Countries.Add(bestEventDate);
            }

            Console.WriteLine("Submitting event dates and attendees");
            var request = bestEventDates.ConvertToAttendeesPerCountry();
            await candidateTestManager.PostInvitationsAsync(request);

            Console.WriteLine("Done!");
        }
    }
}