using System;
using DartGaming.Server.WebApi.Services;
using FluentAssertions;
using Xunit;

namespace DartGaming.Server.Tests.Services
{
    public class EpochServiceTest
    {
        [Fact]
        public void ShouldReturnZeroForFirstDayOf1970()
        {
            var date = new DateTime(1970, 1, 1);
            var service = new EpochService(date);
            var epoch = service.GetCurrentEpoch();
            epoch.Should().Be(0);
        }

        [Fact]
        public void ShouldReturnOneForFirstSecondOf1970()
        {
            var date = new DateTime(1970, 1, 1, 0, 0, 1);
            var service = new EpochService(date);
            var epoch = service.GetCurrentEpoch();
            epoch.Should().Be(1);
        }

        [Fact]
        public void ShouldReturnMinusOneForLastSecondOf1969()
        {
            var date = new DateTime(1969, 12, 31, 23, 59, 59);
            var service = new EpochService(date);
            var epoch = service.GetCurrentEpoch();
            epoch.Should().Be(-1);
        }

        [Fact]
        public void ShouldReturnEpochForRecentDate()
        {
            var date = new DateTime(2018, 1, 15, 8, 30, 30);
            var service = new EpochService(date);
            var epoch = service.GetCurrentEpoch();
            epoch.Should().Be(1516005030);
        }

        [Theory]
        [InlineData(0, 1516005030)]
        [InlineData(1, 1547541030)]
        [InlineData(2, 1579077030)]
        public void ShouldReturnFutureEpochForRecentDate(int yearsToAdd, long expectedEpoch)
        {
            var date = new DateTime(2018, 1, 15, 8, 30, 30);
            var service = new EpochService(date);
            var epoch = service.GetFutureEpoch(yearsToAdd);
            epoch.Should().Be(expectedEpoch);
        }
    }
}