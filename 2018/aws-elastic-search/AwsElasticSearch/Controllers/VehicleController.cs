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
    }
}