package com.github.arqweb.fidelus.ejb;

import com.github.arqweb.fidelus.model.Regla;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ReglaDAO {

    @PersistenceContext(unitName = "fidelizacionPU")
    private EntityManager em;

    public Object obtenerReglas() {
        Query q = this.em.createQuery("select p from Regla p");
        List<Regla> reglas = (List<Regla>) q.getResultList();
        return reglas;
    }

    public void agregar(Regla entidad) {
        this.em.persist(entidad);
    }

    public Regla obtenerRegla(Integer id) {
        return this.em.find(Regla.class, id);
    }

    public void eliminar(Integer id) {
        try {
            Regla entity = obtenerRegla(id);
            this.em.remove(entity);
        } catch (Exception e) {
            throw e;
        }
    }

    public void actualizar(Regla entidad) {
        this.em.merge(entidad);
    }

}
