using System;
using System.Collections.Generic;
using JWT;
using JWT.Algorithms;
using JWT.Serializers;

namespace DartGaming.Server.Business.Auth
{
    public interface IAuthService
    {

    }

    public class AuthService
    {
        const string secret = "5H(MeSf2>#(U?3R_?Sd;-tb.9G99bxKz";

        private string GenerateToken()
        {
            var payload = new Dictionary<string, object>
            {
                { "claim1", 0 },
                { "claim2", "claim2-value" }
            };

            var algorithm = new HMACSHA256Algorithm();
            var serializer = new JsonNetSerializer();
            var urlEncoder = new JwtBase64UrlEncoder();
            var encoder = new JwtEncoder(algorithm, serializer, urlEncoder);

            var token = encoder.Encode(payload, secret);
            return token;
        }
    }
}