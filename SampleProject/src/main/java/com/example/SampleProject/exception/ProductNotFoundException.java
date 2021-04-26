package com.example.SampleProject.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProductNotFoundException extends Exception implements
        ExceptionMapper<ProductNotFoundException>
{
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException() {
        super("No product found with given name !!");
    }

    public ProductNotFoundException(String string) {
        super(string);
    }

    @Override
    public Response toResponse(ProductNotFoundException exception) {
        return Response.status(404).entity(exception.getMessage())
                .type("text/plain").build();
    }
}


