package com.github.arqweb.fidelus.ejb;

import com.github.arqweb.fidelus.model.BolsaPuntos;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class BolsaPuntosDAO {

    @PersistenceContext(unitName = "fidelizacionPU")
    private EntityManager em;
    @Inject
    private ReglaDAO reglaDAO;
    @Inject
    private VencimientoPuntosDAO vencimientoPuntosDAO;

    public List<BolsaPuntos> listar(){
        Query q = this.em.createQuery("select p from BolsaPuntos p");
        List<BolsaPuntos> bolsaPuntos = (List<BolsaPuntos>) q.getResultList();
        return bolsaPuntos;
    }

    public void agregar(BolsaPuntos entidad) {
        this.em.persist(entidad);
    }

    public BolsaPuntos obtenerPorId(Integer id){
        return this.em.find(BolsaPuntos.class, id);
    }

    public void eliminar(Integer id) {
        try {
            BolsaPuntos entity = obtenerPorId(id);
            this.em.remove(entity);
        } catch (Exception e) {
            throw e;
        }
    }

    public void actualizar(BolsaPuntos entidad) {
        this.em.merge(entidad);
    }

    public void calcBolsaPuntos(Integer idCliente, Integer monto) throws Exception {
        try {
            Integer puntos = reglaDAO.obtenerEquivalenciaPuntos(monto);
            BolsaPuntos bolsa = new BolsaPuntos();
            bolsa.setIdCliente(idCliente);
            bolsa.setMonto(monto);
            bolsa.setPuntajeAsignado(puntos);
            bolsa.setSaldo(puntos);
            bolsa.setFechaAsignacion(new Date());
            bolsa.setFechaVencimiento(vencimientoPuntosDAO.calculaFechaVencimiento(new Date()));
            agregar(bolsa);
        } catch (Exception e) {
            throw e;
        }
    }

}
