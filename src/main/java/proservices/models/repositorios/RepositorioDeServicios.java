package proservices.models.repositorios;

import proservices.db.EntityManagerHelper;
import proservices.models.entities.servicios.Servicio;

import java.util.List;

public class RepositorioDeServicios {

    public List<Servicio> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Servicio.class.getName())
                .getResultList();
    }

    public Servicio buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Servicio.class, id);
    }

    public void modificar(Servicio servicio) {
        this.guardar(servicio);
    }

    public void guardar(Servicio servicio) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(servicio);
        EntityManagerHelper.commit();
    }

    public void eliminar(Servicio servicio) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(servicio);
        EntityManagerHelper.commit();
    }
}
