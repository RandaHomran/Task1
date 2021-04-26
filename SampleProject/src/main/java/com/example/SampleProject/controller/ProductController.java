package com.example.SampleProject.controller;
import com.example.SampleProject.exception.ProductNotFoundException;
import com.example.SampleProject.model.Product;
import com.example.SampleProject.service.ProductRestService;
import com.example.SampleProject.service.ProductRestServiceImp;
import java.net.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/products")
public class ProductController {

    private ProductRestService productService = new ProductRestServiceImp();

    /**
     * Get request to get a specific product
     * @param id product id number
     * @return HTTP 200 OK success status response contains the requested product object
     * @throws ProductNotFoundException when the product not found or it is null
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") int id) throws ProductNotFoundException {
        Product product = productService.get(id);
        if (product != null) {
            return Response.ok(product, MediaType.APPLICATION_JSON).build();
        }

        else {
            throw new ProductNotFoundException( "product with id number = "+ id +" not found");
        }
    }

    /**
     * post request to add a new product to the database
     * @param product product object to add it.
     * @return HTTP 200 OK success status response contains product object
     * @throws URISyntaxException
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) throws URISyntaxException {
        Product p = productService.add(product);
        return Response.ok(p , MediaType.APPLICATION_JSON).build();
    }

}