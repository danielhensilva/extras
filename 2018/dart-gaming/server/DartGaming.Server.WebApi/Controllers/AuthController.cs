using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DartGaming.Server.WebApi.ViewModels;
using DartGaming.Server.WebApi.ViewModels.Auth;
using Microsoft.AspNetCore.Mvc;

namespace DartGaming.Server.WebApi.Controllers
{
    [Route("auth")]
    public class AuthController : Controller
    {
        [HttpPost()]
        [ProducesResponseType(typeof(string), 200)]
        [ProducesResponseType(typeof(IDictionary<string, string>), 400)]
        [ProducesResponseType(typeof(void), 500)]
        public IActionResult SignIn([FromBody] AuthViewModel model)
        {
            return this.Ok("Hello, world");
        }
    }
}
