package com.github.arqweb.fidelus.rest;

import com.github.arqweb.fidelus.ejb.ReglaDAO;
import com.github.arqweb.fidelus.model.Regla;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("regla")
@Consumes("application/json")
@Produces("application/json")
public class ReglaRest {

    @Inject
    private ReglaDAO reglaDAO;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(reglaDAO.obtenerReglas()).build();
    }

    @POST
    @Path("/")
    public Response crear(Regla cliente) {
        this.reglaDAO.agregar(cliente);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam(value = "id") Integer id) {
        try {
            this.reglaDAO.eliminar(id);
            return Response.ok().build();
        }catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam(value = "id") Integer id, Regla cliente) {
        cliente.setId(id);
        this.reglaDAO.actualizar(cliente);
        return Response.ok().build();
    }

}
