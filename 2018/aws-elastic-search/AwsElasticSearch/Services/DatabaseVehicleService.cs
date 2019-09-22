using System.Collections.Generic;
using System.Data.SqlClient;
using System.IO.Pipelines;
using AwsElasticSearch.Models;

namespace AwsElasticSearch.Services
{
    public class DatabaseVehicleService
    {
        public (VehicleModel[] items, int count, string queryText) GetItems(int page, int size, string column, string orientation, string search)
        {
            var connectionString = "<database connection>";

            var sortIndex = 2;
            if (column == "id") sortIndex = 1;
            if (column == "number") sortIndex = 2;
            if (column == "name") sortIndex = 3;
            if (column == "registration") sortIndex = 2;
            if (column == "driver") sortIndex = 5;
            if (column == "primarygroup") sortIndex = 6;
            if (column == "make") sortIndex = 8;
            if (column == "year") sortIndex = 7;
            
            var commandText = $@"<database procedure>";

            var count = 0;
            var items = new List<VehicleModel>(size);

            using (var connection = new SqlConnection(connectionString))
            {
                connection.Open();
                using (var command = new SqlCommand())
                {
                    command.Connection = connection;
                    command.CommandText = commandText;
                    
                    using (var reader = command.ExecuteReader())
                    {
                        var i = -1;
                        
                        while (reader.Read())
                        {
                            i++;
                            var model = new VehicleModel();
                            items.Add(model);
                            
                            model.Id = reader.GetInt32(0);
                            model.Driver = reader.IsDBNull(4) ? "" : reader.GetString(4);
                            model.Make =  reader.IsDBNull(7) ? "" : reader.GetString(7);
                            model.Name = reader.IsDBNull(2) ? "" : reader.GetString(2);
                            model.Number = reader.IsDBNull(1) ? "" : reader.GetString(1);
                            model.PrimaryGroup = reader.IsDBNull(5) ? "" : reader.GetString(5);
                            model.Registration =  reader.IsDBNull(3) ? "" : reader.GetString(3);
                            model.Year = reader.IsDBNull(6) ? "" : reader.GetInt32(6).ToString();
                        }

                        reader.NextResult();
                        
                        if (reader.Read())
                        {
                            count = reader.GetInt32(0);
                        }
                    }
                }
                connection.Close();
            }
            
            return (items.ToArray(), count, commandText);
        }
    }
}