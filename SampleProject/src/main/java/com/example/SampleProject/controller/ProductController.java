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

    //to get a specific item
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) throws ProductNotFoundException {
        Product product = productService.get(id);
        if (product != null) {
            return Response.ok(product, MediaType.APPLICATION_JSON).build();
        }

        else {
            throw new ProductNotFoundException( "product with id number ="+ id +" not found");
        }
    }
    //to add a new item
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Product product) throws URISyntaxException {
        Product p= productService.add(product);
        return Response.ok(product, MediaType.APPLICATION_JSON).build();
    }

}