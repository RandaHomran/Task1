package com.example.SampleProject.service;

import com.example.SampleProject.exception.ProductNotFoundException;
import com.example.SampleProject.model.Product;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ProductSoapService {
    @WebMethod
    public boolean deleteProduct(int id) throws ProductNotFoundException;
    @WebMethod
    public Product updateProductPrice(int price,int productId) throws ProductNotFoundException;
}
