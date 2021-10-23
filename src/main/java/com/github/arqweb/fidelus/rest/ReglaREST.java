package com.github.arqweb.fidelus.rest;

import com.github.arqweb.fidelus.ejb.ReglaDAO;
import com.github.arqweb.fidelus.model.Regla;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("regla")
@Consumes("application/json")
@Produces("application/json")
public class ReglaREST {

    @Inject
    private ReglaDAO reglaDAO;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(reglaDAO.obtenerReglas()).build();
    }

    @POST
    @Path("/")
    public Response crear(Regla regla) {
        this.reglaDAO.agregar(regla);
        return Response.ok().build();
    }

    @POST
    @Path("/{monto}")
    public Response obtenerEquivalenciaPuntos(@PathParam(value = "monto") Integer monto) throws Exception {
        try {
            return Response.ok(this.reglaDAO.obtenerEquivalenciaPuntos(monto)).build();
        } catch (Exception e) {
            throw e;
        }
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
    public Response actualizar(@PathParam(value = "id") Integer id, Regla regla) {
        regla.setId(id);
        this.reglaDAO.actualizar(regla);
        return Response.ok().build();
    }

}
