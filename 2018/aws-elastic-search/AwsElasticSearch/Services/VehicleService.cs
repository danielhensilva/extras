using System.Collections.Generic;
using System.Linq;
using AwsElasticSearch.Models;
using Newtonsoft.Json;

namespace AwsElasticSearch.Services
{
    public class VehicleService
    {
        private readonly IList<VehicleModel> _vehicles;
    
        public VehicleService()
        {
            _vehicles = JsonConvert.DeserializeObject<VehicleModel[]>(
                @"[{ ""id"": 64543, ""name"": ""Voltsillam"", ""VIN"": ""WAUHFAFL1EA430952""}, { ""id"": 54005, ""name"": ""Lotstring"", ""VIN"": ""KNADM4A36F6019208""}, { ""id"": 48301, ""name"": ""Daltfresh"", ""VIN"": ""1HGCR2E52FA952635""}, { ""id"": 64481, ""name"": ""Subin"", ""VIN"": ""TRUTX28N311702979""}, { ""id"": 50149, ""name"": ""Sonair"", ""VIN"": ""1N6AA0CC6FN316759""}, { ""id"": 18242, ""name"": ""Namfix"", ""VIN"": ""1LNHL9DKXEG349901""}, { ""id"": 12332, ""name"": ""Tin"", ""VIN"": ""WDDHF5KB2EA989200""}, { ""id"": 88177, ""name"": ""Bitwolf"", ""VIN"": ""1C6RD7JT0CS359927""}, { ""id"": 45314, ""name"": ""Pannier"", ""VIN"": ""WBAYM1C57DD578774""}, { ""id"": 12240, ""name"": ""Namfix"", ""VIN"": ""WBAUP9C56DV827497""}, { ""id"": 35502, ""name"": ""Tresom"", ""VIN"": ""19UUA655X6A851930""}, { ""id"": 59210, ""name"": ""Zoolab"", ""VIN"": ""1HGCP2F3XAA202377""}, { ""id"": 30563, ""name"": ""Kanlam"", ""VIN"": ""TRUDD38J481319420""}, { ""id"": 61678, ""name"": ""Y-Solowarm"", ""VIN"": ""WA1EV74L97D261113""}, { ""id"": 33193, ""name"": ""Tampflex"", ""VIN"": ""1FMJK1H56EE108047""}, { ""id"": 68714, ""name"": ""Holdlamis"", ""VIN"": ""2T1BU4EE3DC042408""}, { ""id"": 61124, ""name"": ""Vagram"", ""VIN"": ""1GYUCHEF9AR755739""}, { ""id"": 83567, ""name"": ""Veribet"", ""VIN"": ""WA1VGAFP3FA316503""}, { ""id"": 82829, ""name"": ""Cookley"", ""VIN"": ""1GTN1UEH9FZ701617""}, { ""id"": 77738, ""name"": ""Lotlux"", ""VIN"": ""3GYFNCE38DS811568""}, { ""id"": 80458, ""name"": ""Veribet"", ""VIN"": ""SAJWA4DC6FM142472""}, { ""id"": 85604, ""name"": ""Zathin"", ""VIN"": ""WAUVT68E44A718597""}, { ""id"": 86169, ""name"": ""Ronstring"", ""VIN"": ""1G6DL1E3XC0948284""}, { ""id"": 80764, ""name"": ""Toughjoyfax"", ""VIN"": ""WBA3D5C51FK566684""}, { ""id"": 43626, ""name"": ""Lotstring"", ""VIN"": ""WBAVB73588K921430""}, { ""id"": 44020, ""name"": ""Zoolab"", ""VIN"": ""1G6EL12Y81B561433""}, { ""id"": 18633, ""name"": ""Zamit"", ""VIN"": ""3N1AB6AP0AL444483""}, { ""id"": 59619, ""name"": ""Stronghold"", ""VIN"": ""1FTWF3B52AE975345""}, { ""id"": 49308, ""name"": ""Cardify"", ""VIN"": ""1GD020CG3BF415053""}, { ""id"": 48741, ""name"": ""Wrapsafe"", ""VIN"": ""KNALN4D73E5890336""}, { ""id"": 56003, ""name"": ""Gembucket"", ""VIN"": ""1G4HE57Y78U674496""}, { ""id"": 13862, ""name"": ""Sonsing"", ""VIN"": ""WBAXH5C56DD814810""}, { ""id"": 26016, ""name"": ""Trippledex"", ""VIN"": ""1G6AH5R35F0096260""}, { ""id"": 41602, ""name"": ""Span"", ""VIN"": ""2G4WF551431450231""}, { ""id"": 16337, ""name"": ""Treeflex"", ""VIN"": ""1G6DW677660921849""}, { ""id"": 33119, ""name"": ""Mat Lam Tam"", ""VIN"": ""WBALX3C50CC958413""}, { ""id"": 76768, ""name"": ""Holdlamis"", ""VIN"": ""SCBBR93W878790701""}, { ""id"": 50202, ""name"": ""Holdlamis"", ""VIN"": ""1GKKRNED2EJ956044""}, { ""id"": 60668, ""name"": ""Pannier"", ""VIN"": ""2HKRM3H31EH884651""}, { ""id"": 72060, ""name"": ""Gembucket"", ""VIN"": ""1G6KE54Y45U909479""}]");
        }

        public IEnumerable<VehicleModel> GetFromDatabase(int page, int size, string column, string orientation)
        {
            IEnumerable<VehicleModel> vehicles = null;
            
            if (orientation == "asc")
            {
                if (column == "id") vehicles = _vehicles.OrderBy(x => x.Id);
                if (column == "vin") vehicles = _vehicles.OrderBy(x => x.Vin);
                if (column == "name") vehicles = _vehicles.OrderBy(x => x.Name);
            }
            else
            {
                if (column == "id") vehicles = _vehicles.OrderByDescending(x => x.Id);
                if (column == "vin") vehicles = _vehicles.OrderByDescending(x => x.Vin);
                if (column == "name") vehicles = _vehicles.OrderByDescending(x => x.Name);
            }

            return vehicles.Skip((page-1) * size).Take(size);
        }

        public int Count()
        {
            return _vehicles.Count;
        }
    }
}