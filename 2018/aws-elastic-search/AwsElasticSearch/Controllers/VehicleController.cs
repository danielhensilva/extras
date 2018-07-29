using System;
using AwsElasticSearch.Services;
using Microsoft.AspNetCore.Mvc;

namespace AwsElasticSearch.Controllers
{
    public class VehicleController : Controller
    {
        [HttpGet("Vehicle")]
        [HttpGet("Vehicle/Index")]
        public IActionResult Index()
        {
            return View("Index");
        }

        [HttpGet("api/database/vehicles")]
        public IActionResult GetDatabaseVehicles(
            [FromQuery(Name="page")] int page,
            [FromQuery(Name="size")] int size,
            [FromQuery(Name="column")] string column,
            [FromQuery(Name="orientation")] string orientation)
        {
            var service = new VehicleService();
            return Json(new {
                TotalPages = Math.Ceiling(service.Count() / (float)size),
                PageRows = service.GetFromDatabase(page, size, column, orientation)
            });
        }
    }
}