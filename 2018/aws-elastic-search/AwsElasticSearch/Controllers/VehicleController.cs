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
            [FromQuery(Name="search")] string search,
            [FromQuery(Name="page")] int page,
            [FromQuery(Name="size")] int size,
            [FromQuery(Name="column")] string column,
            [FromQuery(Name="orientation")] string orientation)
        {
            var service = new DatabaseVehicleService();
            var output = service.GetItems(page, size, column, orientation, search);
            
            return Json(new {
                QueryText = output.queryText,
                TotalPages = (int)Math.Ceiling(output.count / (float)size),
                PageRows = output.items
            });
        }

        [HttpGet("api/elastic/vehicles")]
        public IActionResult GetElasticVehicles(
            [FromQuery(Name="search")] string search,
            [FromQuery(Name="page")] int page,
            [FromQuery(Name="size")] int size,
            [FromQuery(Name="column")] string column,
            [FromQuery(Name="orientation")] string orientation)
        {
            var service = new ElasticVehicleService();
            var output = service.GetItems(page, size, column, orientation, search);
            
            return Json(new {
                QueryText = output.queryText,
                TotalPages = (int)Math.Ceiling(output.count / (float)size),
                PageRows = output.items
            });
        }

        [HttpPut("api/elastic/vehicles")]
        public IActionResult PutVehicles()
        {
            var databaseService = new DatabaseVehicleService();
            var databaseOutput = databaseService.GetItems(1, 1000, "id", "asc", null);
            
            var elasticService = new ElasticVehicleService();
            elasticService.IndexItems(databaseOutput.items);

            return Ok();
        }
    }
}