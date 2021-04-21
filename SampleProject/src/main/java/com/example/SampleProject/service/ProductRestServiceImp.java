package com.example.SampleProject.service;
import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;
import com.example.SampleProject.model.Product;

public class ProductRestServiceImp implements ProductRestService {

    AerospikeClient client = new AerospikeClient("172.28.128.3", 3000);

    // to add a new product to aerospike database
    @Override
    public Product add(Product product) {
        WritePolicy wPolicy = new WritePolicy();
        Key key = new Key("test", "products" ,product.getId());
        Bin bin = new Bin("Product", product);
        client.put(wPolicy, key, bin);
        return product;
    }

    //to get a specific product from aerospike database
    @Override
    public Product get(int productId) {
        Key userKey = new Key("test", "products", productId);
        Record productRecord = client.get(null, userKey);
        if(productRecord != null) {
            return (Product) productRecord.getValue("Product");
        }
        else
            return null;
    }

}
