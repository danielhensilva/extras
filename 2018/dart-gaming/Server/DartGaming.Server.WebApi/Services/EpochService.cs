using System;

namespace DartGaming.Server.WebApi.Services
{
    public interface IEpochService
    {
        long GetCurrentEpoch();
        long GetFutureEpoch(int years);
    }
    
    internal class EpochService : IEpochService
    {
        private readonly DateTime _now;
        private static DateTime _zero;

        public EpochService() : this(DateTime.Now)
        {
        }

        public EpochService(DateTime now)
        {
            _now = now;
            _zero = new DateTime(1970, 1, 1);
        }
        
        public long GetCurrentEpoch()
        {
            return GetEpoch(_now);
        }

        public long GetFutureEpoch(int yearsToAdd)
        {
            return GetEpoch(_now.AddYears(yearsToAdd));
        }

        private long GetEpoch(DateTime date)
        {
            return (long) (date - _zero).TotalSeconds;
        }
    }
}