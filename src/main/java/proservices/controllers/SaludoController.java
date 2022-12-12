package proservices.controllers;

import spark.Request;
import spark.Response;

public class SaludoController {

    public String holaMundoBasico(Request request, Response response) {
        return "Hola Mundo";
    }

    public String saludarPorApellido(Request request, Response response) {
        return "Hola " + request.queryParams("apellido");
    }

    public String saludarPorNombre(Request request, Response response) {
        return "Hola " + request.params("nombre");
    }
}
