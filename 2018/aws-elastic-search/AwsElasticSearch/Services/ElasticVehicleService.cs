using System;
using System.Linq;
using AwsElasticSearch.Models;
using Elasticsearch.Net;
using Elasticsearch.Net.Aws;
using Nest;

namespace AwsElasticSearch.Services
{
    public class ElasticVehicleService
    {
        private ElasticClient CreateClient()
        {
            var awsRegion = "eu-west-1";
            
            var awsCredentials = new AwsCredentials();
            awsCredentials.AccessKey = "<aws-key>";
            awsCredentials.SecretKey = "<aws-secret>";

            var provider = new StaticCredentialsProvider(awsCredentials);
            var connection = new AwsHttpConnection(awsRegion, provider);

            var uri = new Uri("<aws-cluster-uri>");
            var pool = new SingleNodeConnectionPool(uri);
            
            var config = new ConnectionSettings(pool, connection);
            config.DefaultIndex("dva");
            config.DefaultMappingFor<VehicleModel>(x => x.IndexName("vehicles"));
            
            var client = new ElasticClient(config);
            return client;
        }

        public (VehicleModel[] items, int count, string queryText) GetItems(
            int page, int size, string column, string orientation, string search)
        {
            var queryText = "";
            var client = CreateClient();
            var response = client.Search<VehicleModel>(criteria =>
            {
                criteria = criteria
                    .From((page - 1) * size)
                    .Size(size);

                // https://www.elastic.co/guide/en/elasticsearch/client/net-api/current/full-text-queries.html
                
                if (!string.IsNullOrWhiteSpace(search))
                {
                    criteria = criteria.Query(q => q.MultiMatch(m => m
                        .Fields(f => f
                            .Field(x => x.Number, 1.0)
                            .Field(x => x.Name, 1.0)
                            .Field(x => x.Registration, 0.5)
                            .Field(x => x.Driver, 1.0)
                            .Field(x => x.PrimaryGroup, 0.4)
                            .Field(x => x.Make, 0.3)
                            .Field(x => x.Year, 0.2)
                            .Field(x => x.Registration, 1.0))
                        .Type(TextQueryType.CrossFields)
                        .Operator(Operator.Or)
                        .Query(search)));
                    
                    criteria = criteria
                        .Sort(s => s.Descending(SortSpecialField.Score));
                }
                else if (orientation == "asc")
                {
                    if (column == "id")
                        criteria = criteria.Sort(s => s.Ascending(x => x.Id));
                    
                    // Sort by not_analyzed instead of indexed value
                    else if (column == "number") 
                        criteria = criteria.Sort(s => s.Ascending(x => x.NumberRaw));
                    
                    else if (column == "name") 
                        criteria = criteria.Sort(s => s.Ascending(x => x.Name));
                    else if (column == "registration") 
                        criteria = criteria.Sort(s => s.Ascending(x => x.Registration));
                    else if (column == "driver") 
                        criteria = criteria.Sort(s => s.Ascending(x => x.Driver));
                    else if (column == "primaryGroup")
                        criteria = criteria.Sort(s => s.Ascending(x => x.PrimaryGroup));
                    else if (column == "make") 
                        criteria = criteria.Sort(s => s.Ascending(x => x.Make));
                    
                    // This sort will crash as Year is string and has FieldData=false
                    else if (column == "year") 
                        criteria = criteria.Sort(s => s.Ascending(x => x.Year));
                }
                else
                {
                    if (column == "id")
                        criteria = criteria.Sort(s => s.Descending(x => x.Id));
                    
                    // Sort by not_analyzed instead of indexed value
                    else if (column == "number") 
                        criteria = criteria.Sort(s => s.Descending(x => x.NumberRaw));
                    
                    else if (column == "name") 
                        criteria = criteria.Sort(s => s.Descending(x => x.Name));
                    else if (column == "registration") 
                        criteria = criteria.Sort(s => s.Descending(x => x.Registration));
                    else if (column == "driver") 
                        criteria = criteria.Sort(s => s.Descending(x => x.Driver));
                    else if (column == "primaryGroup")
                        criteria = criteria.Sort(s => s.Descending(x => x.PrimaryGroup));
                    else if (column == "make") 
                        criteria = criteria.Sort(s => s.Descending(x => x.Make));
                    
                    // This sort will crash as Year is string and has FieldData=false
                    else if (column == "year") 
                        criteria = criteria.Sort(s => s.Descending(x => x.Year));
                }

                queryText += client.SourceSerializer.SerializeToString(criteria);

                return criteria;
            });
            
            return (
                response.Documents.ToArray(),
                (int)response.Total,
                
                (response.ServerError != null ? string.Join(Environment.NewLine, response.ServerError.Error.RootCause) : "") 
                    + Environment.NewLine + "GET " + response.ApiCall.Uri.PathAndQuery 
                    + Environment.NewLine + queryText
                    + Environment.NewLine
                    + Environment.NewLine + "RESPONSE" 
                    + Environment.NewLine + client.RequestResponseSerializer.SerializeToString(response)
                
            );
        }

        public void IndexItems(VehicleModel[] items)
        {
            var client = CreateClient();

            client.DeleteIndex(Indices.All);
            
            client.CreateIndex("vehicles", c => c
                .Mappings(x => x
                    .Map<VehicleModel>(m => m.AutoMap())));
            
            client.Bulk(x => x.IndexMany(items));
        }
    }
}