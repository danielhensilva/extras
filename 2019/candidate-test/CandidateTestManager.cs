using System;
using System.Net.Http;
using System.Threading.Tasks;
using ConsoleApp.Models;
using Jil;

namespace ConsoleApp
{
    public class CandidateTestManager
    {
        private readonly string _userKey;
        private readonly string _endpoint;

        public CandidateTestManager(string userKey)
        {
            _userKey = userKey;
            _endpoint = "<endpoint>";
        }
        
        public async Task<PartnersResponse> GetPartnersAsync()
        {
            using (var httpClient = new HttpClient())
            {
                var endpoint = $"{_endpoint}/dataset?userKey={_userKey}";
                
                var response = await httpClient.GetAsync(endpoint);
                response.EnsureSuccessStatusCode();

                var jsonContent = await response.Content.ReadAsStringAsync();
                var partners = JSON.Deserialize<PartnersResponse>(jsonContent, Options.CamelCase);
                
                return partners;
            }
        }

        public async Task<bool> PostInvitationsAsync(AttendeesPerCountry attendees)
        {
            using (var httpClient = new HttpClient())
            {
                var endpoint = $"{_endpoint}/result?userKey={_userKey}";

                var jsonContent = JSON.Serialize(attendees, Options.CamelCase);
                var httpContent = new StringContent(jsonContent);
                
                var response = await httpClient.PostAsync(endpoint, httpContent);
                response.EnsureSuccessStatusCode();

                return true;
            }
        }
    }
}
