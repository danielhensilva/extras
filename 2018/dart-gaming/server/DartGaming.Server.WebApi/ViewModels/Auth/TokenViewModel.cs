using System;

namespace DartGaming.Server.WebApi.ViewModels.Auth
{
    public class TokenViewModel
    {
        public string Token { get; set; }

        public DateTime ExpirationDate { get; set; }
    }
}