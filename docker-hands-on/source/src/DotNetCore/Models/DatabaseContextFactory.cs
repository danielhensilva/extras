using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using MySQL.Data.EntityFrameworkCore.Extensions;

namespace DotNetCore.Models
{
    public static class DatabaseContextFactory
    {
        private static object locker = new object();

        private static DatabaseContext context = null;

        public static DatabaseContext Create(DatabaseSettings settings)
        {
            if (context == null)
            {
                lock (locker)
                {
                    if (context == null)
                    {
                        var builder = new DbContextOptionsBuilder<DatabaseContext>();
                        builder.UseMySQL(settings.MySqlConnectionString);

                        context = new DatabaseContext(builder.Options);
                        context.Database.EnsureCreated();

                        var serviceProvider = context.GetInfrastructure<IServiceProvider>();
                        var loggerFactory = serviceProvider.GetService<ILoggerFactory>();
                        loggerFactory.AddProvider(new DefaultLoggerProvider());
                    }
                }
            }

            return context;
        }
    }
}
