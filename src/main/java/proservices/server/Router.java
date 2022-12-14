package proservices.server;

import proservices.controllers.*;
import proservices.helpers.PermisoHelper;
import proservices.middlewares.AuthMiddleware;
import proservices.models.entities.usuarios.Permiso;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import proservices.spark.utils.BooleanHelper;
import proservices.spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure() {
        ServiciosController serviciosController = new ServiciosController();
        TareasController tareasController = new TareasController();
        LoginController loginController = new LoginController();

        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
            Spark.post("", loginController::login);
            Spark.post("/logout", loginController::logout);
        });

        Spark.get("/prohibido", loginController::prohibido, engine);

        Spark.path("/servicios", () -> {
            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);

            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, Permiso.VER_SERVICIOS)) {
                    response.redirect("/prohibido");
                    Spark.halt();
                }
            }));

            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, Permiso.VER_SERVICIOS)) {
                    response.redirect("/prohibido");
                    Spark.halt();
                }
            }));

            Spark.get("", serviciosController::mostrarTodos, engine);
            Spark.get("/crear", serviciosController::crear, engine);
            Spark.get("/:id", serviciosController::mostrar, engine);
            Spark.get("/:id/editar", serviciosController::editar, engine);
            Spark.post("/:id", serviciosController::modificar);
            Spark.post("", serviciosController::guardar);

            Spark.path("/:id/tareas", () -> {
                Spark.get("", tareasController::mostrarTodos, engine);
            });
        });
    }

    private static void metodosDePrueba() {
        SaludoController saludoController = new SaludoController();

        //Spark.get("/hola", (request, response) -> "Hola mundo");
        Spark.get("/hola", saludoController::holaMundoBasico);

        //QUERY PARAMS
        //Spark.get("/saludar", ((request, response) -> "Hola " + request.queryParams("apellido")));
        Spark.get("/saludar", saludoController::saludarPorApellido);

        //ROUTE PARAM
        //Spark.get("/saludo/:nombre", ((request, response) -> "Hola " + request.params("nombre")));
        Spark.get("/saludo/:nombre", saludoController::saludarPorNombre);
    }
}
