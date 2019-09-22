using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DotNetCore.Models
{
    public class ProductRepository
    {
        private readonly DatabaseContext context;

        public ProductRepository(DatabaseSettings settings)
        {
            this.context = DatabaseContextFactory.Create(settings);
        }

        public IEnumerable<Product> GetAll()
        {
            return context.Products.OrderBy(x => x.Name).ThenBy(x => x.Id).ToArray();
        }

        public Product GetSingle(int id)
        {
            return context.Products.FirstOrDefault(x => x.Id == id);
        }

        public int Insert(Product product)
        {
            context.Products.Add(product);
            return context.SaveChanges();
        }

        public int Delete(int id)
        {
            var product = this.GetSingle(id);

            if (product == null)
                return 0;

            context.Products.Remove(product);
            return context.SaveChanges();
        }
    }
}
