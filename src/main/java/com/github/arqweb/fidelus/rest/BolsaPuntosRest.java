package com.github.arqweb.fidelus.rest;

import com.github.arqweb.fidelus.ejb.BolsaPuntosDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("bolsa-puntos")
@Consumes("application/json")
@Produces("application/json")
public class BolsaPuntosRest {

    @Inject
    private BolsaPuntosDAO bolsaPuntosDAO;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(bolsaPuntosDAO.listar()).build();
    }

    @POST
    @Path("/")
    public Response agregarPuntos(@QueryParam("idCliente") Integer idCliente,
                                  @QueryParam("monto") Integer monto) throws Exception {
        this.bolsaPuntosDAO.calcBolsaPuntos(idCliente, monto);
        return Response.ok().build();
    }

}
