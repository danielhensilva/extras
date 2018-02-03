using System;
using DartGaming.Server.WebApi.Models;
using DartGaming.Server.WebApi.Services;
using FluentAssertions;
using Xunit;

namespace DartGaming.Server.Tests.Services
{
    public class AuthServiceTests
    {
        [Fact]
        public void ShouldGenerateAndVerifyTokenOverSameUser()
        {
            var user = new User();
            user.Id = 100;

            var date = DateTime.Now;
            var epochService = new EpochService(date);
            var service = new AuthService(epochService);
            
            var token = service.Auth(user);
            token.Should().NotBeNullOrWhiteSpace();

            var verifiedUser = service.Verify(token);
            verifiedUser.Should().NotBeNull();
            verifiedUser.Id.Should().Be(user.Id);
        }
    }
}