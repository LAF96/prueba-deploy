package test;

import proservices.db.EntityManagerHelper;
import proservices.models.entities.servicios.Servicio;
import proservices.models.entities.servicios.Tarea;
import proservices.models.entities.usuarios.Permiso;
import proservices.models.entities.usuarios.Rol;
import proservices.models.entities.usuarios.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {

    public static void main(String[] args) {
        crearEntidades();
    }

    private static void crearEntidades() {
        Map<String, String[]> servicios = new HashMap<String, String[]>(){{
            put("Plomería", new String[]{
                    "Instalación de grifería de baño",
                    "Instalación de tanque de agua a gran altura",
                    "Reparación de cañerías"
            });
            put("Gasista", new String[]{
                    "Instalación de cocinas industriales",
                    "Instalación de calderas",
                    "Instalación de termotanques"
            });
            put("Técnico de aire acondicionado", new String[]{
                    "Recarga de gas",
                    "Reparación del compresor"
            });
        }};

        EntityManagerHelper.beginTransaction();

        servicios.forEach(((nombreServicio, tareas) -> {
            Servicio unServicio = new Servicio();
            unServicio.setNombre(nombreServicio);

            Arrays.asList(tareas).forEach(t -> {
                Tarea unaTarea = new Tarea();
                unaTarea.setDescripcion(t);
                unServicio.agregarTareas(unaTarea);
            });

            EntityManagerHelper.getEntityManager().persist(unServicio); // SE PREPARA LA SENTENCIA DE INSERT
        }));

        Rol rol = new Rol();
        rol.setNombre("Admin");
        rol.agregarPermiso(Permiso.CREAR_SERVICIOS);
        rol.agregarPermiso(Permiso.VER_SERVICIOS);
        rol.agregarPermiso(Permiso.EDITAR_SERVICIOS);
        rol.agregarPermiso(Permiso.ELIMIAR_SERVICIOS);
        EntityManagerHelper.getEntityManager().persist(rol); // SE PREPARA LA SENTENCIA DE INSERT

        Rol rolComun = new Rol();
        rolComun.setNombre("Base");
        EntityManagerHelper.getEntityManager().persist(rolComun); // SE PREPARA LA SENTENCIA DE INSERT

        Usuario unUsuario = new Usuario();
        unUsuario.setNombre("Eze");
        unUsuario.setApellido("Escobar");
        unUsuario.setNombreDeUsuario("ezescobar");
        unUsuario.setContrasenia("123456789");
        unUsuario.setRol(rol);
        EntityManagerHelper.getEntityManager().persist(unUsuario); // SE PREPARA LA SENTENCIA DE INSERT

        Usuario lucas = new Usuario();
        lucas.setNombre("Lucas");
        lucas.setApellido("Saclier");
        lucas.setNombreDeUsuario("lsaclier");
        lucas.setContrasenia("123456789");
        lucas.setRol(rolComun);
        EntityManagerHelper.getEntityManager().persist(lucas); // SE PREPARA LA SENTENCIA DE INSERT

        EntityManagerHelper.commit();// ACÁ SE EJECUTAN TODAS LAS SENTENCIAS ENCOLADAS

        EntityManagerHelper.closeEntityManager();
        EntityManagerHelper.closeEntityManagerFactory();
    }

    private static void recuperarEntidades() {
        Servicio servicioBuscado = (Servicio) EntityManagerHelper
                .getEntityManager()
                .find(Servicio.class, 1);

        System.out.println(servicioBuscado.getNombre()); // PLOMERIA

        System.out.println(servicioBuscado.cantTareas()); // 3

        //HQL
        Servicio otroServicioBuscado = (Servicio) EntityManagerHelper
                .createQuery("from " + Servicio.class.getName() + " where nombre = 'Gasista'")
                .getSingleResult();

        System.out.println(otroServicioBuscado.getNombre()); // GASISTA

        List<Tarea> todasLasTareas = EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Tarea.class.getName())
                .getResultList();

        System.out.println(todasLasTareas);

        CriteriaBuilder cb = EntityManagerHelper.getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Servicio> criteriaQuery = cb.createQuery(Servicio.class);
        Root<Servicio> root = criteriaQuery.from(Servicio.class);//FROM Servicio

        criteriaQuery
                .select(root)//SELECT * FROM Servicio
                .where(
                        cb.and(
                                cb.like(root.get("nombre"), "%Técnico%"), // nombre LIKE '%Técnico%'
                                cb.between(root.get("id"), 1, 10) // between 1 and 3
                        )
                );// WHERE nombre LIKE '%Técnico%' AND between 1 and 3

        List<Servicio> serviciosBuscados = EntityManagerHelper.getEntityManager().createQuery(criteriaQuery).getResultList();

        System.out.println(serviciosBuscados);

        EntityManagerHelper.closeEntityManager();
        EntityManagerHelper.closeEntityManagerFactory();
    }

    private void actualizarEntidades() {
        Servicio servicioBuscado = (Servicio) EntityManagerHelper
                .getEntityManager()
                .find(Servicio.class, 1);

        servicioBuscado.setNombre("Plomero");

        servicioBuscado.getTareas().forEach(t -> {
            t.setDescripcion(t.getDescripcion() + " - " + servicioBuscado.getNombre());
        });

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(servicioBuscado); // PREPARA EL UPDATE
        EntityManagerHelper.commit();// ACÁ SE EJECUTAN TODAS LAS SENTENCIAS ENCOLADAS

        EntityManagerHelper.closeEntityManager();
        EntityManagerHelper.closeEntityManagerFactory();
    }
}
