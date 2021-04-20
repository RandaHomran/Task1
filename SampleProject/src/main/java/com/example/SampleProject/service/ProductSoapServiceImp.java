package com.example.SampleProject.service;
import com.aerospike.client.*;
import com.aerospike.client.policy.WritePolicy;
import com.example.SampleProject.Exception.ProductNotFoundException;
import com.example.SampleProject.model.Product;
import javax.jws.WebService;

@WebService(endpointInterface = "com.example.SampleProject.service.ProductSoapService")
public class ProductSoapServiceImp implements ProductSoapService {

    AerospikeClient client = new AerospikeClient("172.28.128.3", 3000);

    //to delete a product by id
    @Override
    public boolean delete(int productId) throws ProductNotFoundException {
        WritePolicy wPolicy = new WritePolicy();
        Key key = new Key("test", "products", productId);
        boolean isDeleted= client.delete(wPolicy, key);
        if(!isDeleted){
            throw new ProductNotFoundException( "product with id number ="+ productId +" not found");
        }
        return isDeleted;
    }

    //to update the price of a specific product
    @Override
    public Product updatePrice(int price, int productId) throws ProductNotFoundException {
        Key productKey = new Key("test", "products", productId);
        Record productRecord = client.get(null, productKey);
        if(productRecord==null){
            throw new ProductNotFoundException( "product with id number ="+ productId +" not found");
        }
        Product product = (Product) productRecord.getValue("Product");
        product.setPrice(price);
        WritePolicy wPolicy = new WritePolicy();
        Bin bin = new Bin("Product", product);
        client.put(wPolicy, productKey , bin);
        return product;
    }

}
