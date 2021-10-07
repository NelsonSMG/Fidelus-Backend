package com.github.arqweb.fidelus.ejb;

import com.github.arqweb.fidelus.model.Cliente;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ClienteDAO {

    @PersistenceContext(unitName = "fidelizacionPU")
    private EntityManager em;

    public void agregar(Cliente entidad) {
        this.em.persist(entidad);
    }

    public Cliente obtenerCliente(Integer id) {
        return this.em.find(Cliente.class, id);
    }

    public Object obtenerClientes() {
        List<Cliente> clientes = null;
        Query q = null;

        q = this.em.createQuery("select p from Cliente p");

        clientes = (List<Cliente>) q.getResultList();

        return clientes;
    }

    public void eliminar(Integer id) {
        try {
            Cliente entity = obtenerCliente(id);
            this.em.remove(entity);
        } catch (Exception e) {
            throw e;
        }
    }

    public void actualizar(Cliente entidad) {
        this.em.merge(entidad);
    }

}
