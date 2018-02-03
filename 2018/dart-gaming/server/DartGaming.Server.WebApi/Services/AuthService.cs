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

    internal class AuthService : IAuthService
    {
        private static string _tokenSecret;
        private readonly IEpochService _epochService;

        public AuthService(IEpochService epochService)
        {
            _tokenSecret = Guid.NewGuid().ToString();
            _epochService = epochService;
        }

        public string Auth(User user)
        {
            return GenerateToken(user.Id);
        }

        public User Verify(string token)
        {
            var userId = VerifyToken(token);
            var user = new User {Id = userId};
            return user;
        }

        private string GenerateToken(int userId)
        {
            var payload = new Dictionary<string, object>
            {
                {"jti", userId},
                {"iat",_epochService.GetCurrentEpoch()},
                {"exp", _epochService.GetFutureEpoch(1)}
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

            var payload = decoder.DecodeToObject<IDictionary<string, object>>(token, _tokenSecret, true);
            var userId = Convert.ToInt32(payload["jti"]);
            return userId;
        }
    }
}