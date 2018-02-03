using System;
using System.IO;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Swashbuckle.AspNetCore.Swagger;
using Swashbuckle.AspNetCore.SwaggerGen;

namespace DartGaming.Server.WebApi
{
    public class Startup
    {
        private IConfiguration _configuration;

        public Startup(IConfiguration configuration)
        {
            _configuration = configuration;
        }

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

        private void ConfigureSwaggerGen(SwaggerGenOptions options)
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
    }
}