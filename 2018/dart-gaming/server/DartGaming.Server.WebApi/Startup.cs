using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;
using Swashbuckle.AspNetCore;
using Swashbuckle.AspNetCore.Swagger;
using Swashbuckle.AspNetCore.SwaggerGen;

namespace DartGaming.Server.WebApi
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        public void ConfigureServices(IServiceCollection services)
        {
            services
                .AddMvcCore()
                .AddJsonFormatters()
                .AddApiExplorer();

            services.AddSwaggerGen(
                ConfigureSwaggerGen
            );
        }

        public void ConfigureSwaggerGen(SwaggerGenOptions options)
        {
            var info = new Info();
            info.Title = typeof(Startup).Namespace;
            info.Version = "v1";

            var basePath = AppDomain.CurrentDomain.BaseDirectory;
            var xmlDocsName = string.Concat(info.Title, ".xml");
            var xmlDocsFullPath = Path.Combine(basePath, xmlDocsName);

            options.SwaggerDoc(info.Version, info);
            options.IncludeXmlComments(xmlDocsFullPath);
        }

        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
                app.UseDeveloperExceptionPage();

            app.UseMvc();
            app.UseSwagger();
            app.UseSwaggerUI(c =>
                c.SwaggerEndpoint("/swagger/v1/swagger.json", "DartGaming.Server.WebApi v1")
            );
        }
    }
}
