using System;
using DartGaming.Server.WebApi.Models;
using DartGaming.Server.WebApi.Services;
using FluentAssertions;
using Xunit;

namespace DartGaming.Server.Tests
{
    public class AuthServiceTests
    {
        [Fact]
        public void ShouldGenerateAndVerifyTokenOverSameUser()
        {
            var user = new User();
            user.Id = 100;

            var service = new AuthService();
            var token = service.Auth(user);
            token.Should().NotBeNullOrWhiteSpace();
            
            var verifiedUser = service.Verify(token);
            verifiedUser.Should().NotBeNull();
            verifiedUser.Id.Should().Be(user.Id);
        }
    }
}