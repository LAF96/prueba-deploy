package proservices.controllers;

import proservices.models.entities.servicios.Servicio;
import proservices.models.repositorios.RepositorioDeServicios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class TareasController {
    private RepositorioDeServicios repositorioDeServicios;

    public TareasController() {
        this.repositorioDeServicios = new RepositorioDeServicios();
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Servicio servicio = this.repositorioDeServicios.buscar(new Integer(request.params("id")));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("tareas", servicio.getTareas());
            put("nombre_servicio", servicio.getNombre());
        }}, "/servicios/tareas/tareas.hbs");
    }
}
