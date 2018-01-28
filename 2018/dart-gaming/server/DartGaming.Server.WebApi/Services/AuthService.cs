using System;
using System.Collections.Generic;
using DartGaming.Server.WebApi.Models;
using JWT;
using JWT.Algorithms;
using JWT.Serializers;

namespace DartGaming.Server.WebApi.Services
{
    public interface IAuthService
    {

    }

    public class AuthService : IAuthService
    {
        private static string _tokenSecret;

        public AuthService()
        {
            _tokenSecret = Guid.NewGuid().ToString();
        }

        public string Auth(User user)
        {
            return GenerateToken(user.Id);
        }

        public User Verify(string token)
        {
            var userId = VerifyToken(token);
            return new User() { Id = userId };
        }

        private string GenerateToken(int userId)
        {
            var payload = new Dictionary<string, object>
            {
                { "jti", userId },
                { "iat", ConvertToUnix(DateTime.Now) },
                { "exp", ConvertToUnix(DateTime.Now.AddYears(1)) },
            };

            var algorithm = new HMACSHA256Algorithm();
            var serializer = new JsonNetSerializer();
            var urlEncoder = new JwtBase64UrlEncoder();
            var encoder = new JwtEncoder(algorithm, serializer, urlEncoder);

            var token = encoder.Encode(payload, _tokenSecret);
            return token;
        }

        private int VerifyToken(string token)
        {
            var serializer = new JsonNetSerializer();
            var provider = new UtcDateTimeProvider();
            var validator = new JwtValidator(serializer, provider);
            var urlEncoder = new JwtBase64UrlEncoder();
            var decoder = new JwtDecoder(serializer, validator, urlEncoder);

            var payload = decoder.DecodeToObject<IDictionary<string, object>>(token, _tokenSecret, verify:true);
            var userId = Convert.ToInt32(payload["jti"]);
            return userId;
        }

        private int ConvertToUnix(DateTime dateTime)
        {
            var epoch = new DateTime(1970, 1, 1);
            var timeSpan = dateTime - epoch;
            return (int)timeSpan.TotalSeconds;
        }
    }
}