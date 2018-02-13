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
        private readonly ITokenService _tokenService;

        public AuthService(ITokenService tokenService)
        {
            _tokenService = tokenService;
        }

        public string Auth(User user)
        {
            return _tokenService.Encode(user.Id);
        }

        public User Verify(string token)
        {
            var id = _tokenService.Decode(token);
            return new User { Id = id };
        }
    }
}