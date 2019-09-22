using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace DotNetCore.Models
{
    public class Product
    {
        [Key]
        public int Id { get; set; }

        [MinLength(3)]
        [MaxLength(30)]
        public string Name { get; set; }

        [Range(0.01, 9999.99)]
        public decimal Price { get; set; }
    }
}
