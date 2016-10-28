using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DotNetCore.Models;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Options;

namespace DotNetCore.Controllers
{
    [Route("api/[controller]")]
    public class ProductsController : Controller
    {
        private readonly ProductRepository repository;

        public ProductsController(IOptions<DatabaseSettings> options)
        {
            repository = new ProductRepository(options.Value);
        }

        [HttpGet]
        [Route("")]
        public ActionResult GetAll()
        {
            var collection = repository.GetAll();
            return this.Ok(collection);
        }

        [HttpGet]
        [Route("{id}")]
        public ActionResult GetSingle(int id)
        {
            var product = repository.GetSingle(id);

            if (product == null)
                return this.NotFound();

            return this.Ok(product);
        }

        [HttpPost]
        [Route("")]
        public ActionResult Post([FromBody] Product product)
        {
            var affectedRows = repository.Insert(product);

            if (affectedRows == 0)
                return this.NoContent();

            return this.Ok();
        }

        [HttpDelete]
        [Route("{id}")]
        public ActionResult Delete(int id)
        {
            var affectedRows = repository.Delete(id);

            if (affectedRows == 0)
                return this.NoContent();

            return this.Ok();
        }
    }
}
