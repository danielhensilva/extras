using System;
using System.Collections.Generic;
using JWT;
using JWT.Algorithms;
using JWT.Serializers;

namespace DartGaming.Server.WebApi.Services
{
    public interface ITokenService
    {
        string Encode(int identifier);
        int Decode(string token);
    }
    
    internal class TokenService : ITokenService
    {
        private static string _secret;
        private readonly IEpochService _epochService;

        public TokenService(IEpochService epochService)
        {
            _secret = Guid.NewGuid().ToString();
            _epochService = epochService;
        }

        public string Encode(int identifier)
        {
            var payload = new Dictionary<string, object>
            {
                {"jti", identifier},
                {"iat",_epochService.GetCurrentEpoch()},
                {"exp", _epochService.GetFutureEpoch(1)}
            };

            var algorithm = new HMACSHA256Algorithm();
            var serializer = new JsonNetSerializer();
            var urlEncoder = new JwtBase64UrlEncoder();
            var encoder = new JwtEncoder(algorithm, serializer, urlEncoder);

            var token = encoder.Encode(payload, _secret);
            return token;
        }

        public int Decode(string token)
        {
            var serializer = new JsonNetSerializer();
            var provider = new UtcDateTimeProvider();
            var validator = new JwtValidator(serializer, provider);
            var urlEncoder = new JwtBase64UrlEncoder();
            var decoder = new JwtDecoder(serializer, validator, urlEncoder);

            var payload = decoder.DecodeToObject<IDictionary<string, object>>(token, _secret, true);
            var userId = Convert.ToInt32(payload["jti"]);
            return userId;
        }
    }
}