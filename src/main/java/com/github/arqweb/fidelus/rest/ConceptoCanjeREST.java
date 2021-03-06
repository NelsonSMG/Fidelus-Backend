package com.github.arqweb.fidelus.rest;
import com.github.arqweb.fidelus.ejb.ConceptoCanjeDAO;
import com.github.arqweb.fidelus.model.ConceptoCanje;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("concepto-canje")
@Consumes("application/json")
@Produces("application/json")
public class ConceptoCanjeREST {

    @Inject
    private ConceptoCanjeDAO conceptoCanjeDao;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(conceptoCanjeDao.obtenerConceptosCanje()).build();
    }

    @POST
    @Path("/")
    public Response crear(ConceptoCanje conceptoCanje) {
        this.conceptoCanjeDao.agregar(conceptoCanje);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam(value = "id") Integer id){
        try {
            this.conceptoCanjeDao.eliminar(id);
            return Response.ok().build();
        }catch (Exception a) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam(value = "id") Integer id, ConceptoCanje conceptoCanje) {
        conceptoCanje.setId(id);
        this.conceptoCanjeDao.actualizar(conceptoCanje);
        return Response.ok().build();
    }
}
