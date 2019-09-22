using Nest;
using Newtonsoft.Json;

namespace AwsElasticSearch.Models
{
    [ElasticsearchType(Name="vehiclemodel")]
    public class VehicleModel
    {
        [Number(Name="id")]
        public int Id { get; set; }
        
        [Text(Name="number")]
        public string Number { get; set; }

        // Proxy property to index as not_analyzed 
        [JsonIgnore]
        [Keyword(Name = "numberRaw")]
        public string NumberRaw
        {
            get => this.Number;
            set => this.Number = value;
        }
        
        [Text(Name="name", Fielddata = true)]
        public string Name { get; set; }
        
        [Text(Name="registration", Fielddata = true)]
        public string Registration { get; set; }
        
        [Text(Name="driver", Fielddata = true)]
        public string Driver { get; set; }
        
        [Text(Name="primaryGroup", Fielddata = true)]
        public string PrimaryGroup { get; set; }
        
        [Text(Name="make", Fielddata = true)]
        public string Make { get; set; }
        
        // This property cannot be used in sorting clause
        [Text(Name="year")]
        public string Year { get; set; }
    }
}