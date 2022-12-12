package proservices.controllers;

import proservices.db.EntityManagerHelper;
import proservices.models.entities.servicios.Servicio;
import proservices.models.repositorios.RepositorioDeServicios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiciosController {
    private RepositorioDeServicios repo;

    public ServiciosController() {
        this.repo = new RepositorioDeServicios();
    }

    //LISTAR TODOS, MOSTRAR UN SERVICIO EN PARTICULAR, GUARDAR, VISTA PARA CREAR, MODIFICAR, VISTA PARA MODIFICAR, ELIMINAR

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<Servicio> serviciosBuscados = this.repo.buscarTodos();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("servicios", serviciosBuscados);
        }},"servicios/servicios.hbs");
    }

    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");

        Servicio servicioBuscado = this.repo.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("servicio", servicioBuscado);
        }}, "servicios/servicio.hbs");
    }

    //DEVUELVE LA VISTA QUE PERMITE EDITAR EL RECURSO
    public ModelAndView editar(Request request, Response response) {
        String idBuscado = request.params("id");

        Servicio servicioBuscado = this.repo.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("servicio", servicioBuscado);
        }}, "servicios/servicio.hbs");
    }

    // METODO QUE REALMENTE MODIFICA EL RECURSO
    public Response modificar(Request request, Response response) {
        Servicio servicioAModificar = this.repo.buscar(new Integer(request.params("id")));
        this.asignarParametros(servicioAModificar, request);
        this.repo.modificar(servicioAModificar);
        response.redirect("/servicios");
        return response;
    }

    //DEVOLVER LA PANTALLA QUE PERMITE CREAR UN NUEVO SERVICIO
    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "/servicios/servicio.hbs");
    }

    // METODO QUE INSTANCIA UN NUEVO SERVICIO Y LO GUARDAR EN LA BASE DE DATOS
    public Response guardar(Request request, Response response) {
        Servicio nuevoServicio = new Servicio();
        this.asignarParametros(nuevoServicio, request);
        this.repo.guardar(nuevoServicio);
        response.redirect("/servicios");
        return response;
    }

    private void asignarParametros(Servicio servicio, Request request) {
        if(request.queryParams("nombre") != null) {
            servicio.setNombre(request.queryParams("nombre"));
        }
    }
}
