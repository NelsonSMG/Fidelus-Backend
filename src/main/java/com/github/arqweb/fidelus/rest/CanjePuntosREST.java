package com.github.arqweb.fidelus.rest;

import com.github.arqweb.fidelus.ejb.CanjePuntosDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("canje-puntos")
@Consumes("application/json")
@Produces("application/json")
public class CanjePuntosREST {

    @Inject
    private CanjePuntosDAO canjePuntosDAO;

    @GET
    @Path("/")
    public Response listarCanjes(){
        return Response.ok(canjePuntosDAO.obtenerCanjePuntos()).build();
    }

    @POST
    @Path("/canjear")
    public Response canjearPuntos(@QueryParam("idCliente") Integer idCliente,
                                  @QueryParam("idConceptoCanje") Integer idConceptoCanje) throws Exception {
        this.canjePuntosDAO.agregarCanjePuntos(idCliente, idConceptoCanje);
        return Response.ok().build();
    }
    @GET
    @Path("/listar/concepto")
    public Response listarPorConcepto(@QueryParam("idConcepto") Integer idConcepto){
        return Response.ok(canjePuntosDAO.obtenerCanjesPorConceptoUso(idConcepto)).build();
    }

    @GET
    @Path("/listar/cliente")
    public Response listarPorCliente(@QueryParam("idCliente") Integer idCliente){
        return Response.ok(canjePuntosDAO.obtenerCanjesPorIdCliente(idCliente)).build();
    }

    @GET
    @Path("/listar/canje")
    public Response listarPorId(@QueryParam("idCanje") Integer idCanje){
        return Response.ok(canjePuntosDAO.obtenerCanjePuntosPorId(idCanje)).build();
    }

    @GET
    @Path("/listar/fecha-uso")
    public Response listarPorFechaUso(@QueryParam("fechaCanje") String fechaCanje){
        return Response.ok(canjePuntosDAO.obtenerCanjesPorFechaCanje(fechaCanje)).build();
    }
}
