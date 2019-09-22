using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;

namespace DotNetCore.Controllers
{
    [Route("api/[controller]")]
    public class ValuesController : Controller
    {
        public ValuesController()
        {
        }

        [HttpGet]
        public IEnumerable<string> Get()
        {
            return new[] { "Olá", "mundo" };
        }

        [HttpGet("{id}")]
        public object Get(int id)
        {
            return new
            {
                Id = id,
                Random = new Random().Next(0, int.MaxValue),
                DateTime = DateTime.Now.ToString("F")
            };
        }
    }
}
