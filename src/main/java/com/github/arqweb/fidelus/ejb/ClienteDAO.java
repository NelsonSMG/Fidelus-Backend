package com.github.arqweb.fidelus.ejb;

import com.github.arqweb.fidelus.model.Cliente;

import java.util.Calendar;
import java.util.Date;
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

    public Object obtenerClientesPorParametro(String nombre, String apellido, String fechaNacimiento) {
        List<Cliente> clientes = null;
        Query q = null;
        if(nombre != null && !nombre.equals("")) {
            q = this.em.createQuery("select p from Cliente p where p.nombre like :param");
            q.setParameter("param", "%"+nombre+"%");
        } else if(apellido != null && !apellido.equals("")) {
            q = this.em.createQuery("select p from Cliente p where p.apellido like :param");
            q.setParameter("param", "%"+apellido+"%");
        } else if(fechaNacimiento != null && !fechaNacimiento.equals("")) {
            q = this.em.createQuery("select p from Cliente p where to_char(p.fechaNacimiento, 'MM-dd-YYYY') like :param");
            q.setParameter("param", "%"+fechaNacimiento+"%");
        } else {
            q = this.em.createQuery("select p from Cliente p");
        }
        clientes = (List<Cliente>) q.getResultList();

        return clientes;
    }

    public Object obtenerClientePuntosAVencer(Integer dias){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, dias); //agregamos los días
        Date dateToLookBackAfter = calendar.getTime();

        List<Cliente> clientes = null;
        Query q = null;
        q = this.em.createQuery("select c from BolsaPuntos b join Cliente c on b.idCliente = c.id where b.fechaVencimiento < :param");
        q.setParameter("param", dateToLookBackAfter);
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
