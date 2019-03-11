package com.masglobal.demo;

import com.masglobal.demo.handlers.EmployeeHandler;
import com.masglobal.demo.handlers.HomeHandler;
import com.masglobal.demo.services.impl.APIManagerImpl;
import com.masglobal.demo.utils.Filters;
import com.masglobal.demo.utils.PropertyManager;
import com.masglobal.demo.utils.RestTemplateHelper;
import org.springframework.web.client.RestTemplate;
import spark.Service;
import java.util.Properties;

import static spark.Spark.notFound;

public class Main {
    public static void main(String[] args) {
        PropertyManager pm = new PropertyManager();
        Properties instance = pm.getInstance();
        setupLoadsFe(Service.ignite(), Integer.valueOf(instance.getProperty("app.port")));
    }

    private static void setupLoadsFe(Service http, int port) {
        http.staticFiles.location("/public");

        HomeHandler homeHandler = new HomeHandler(
            new APIManagerImpl(new RestTemplateHelper(new RestTemplate()), new PropertyManager()));
        EmployeeHandler employeeHandler = new EmployeeHandler(
            new APIManagerImpl(new RestTemplateHelper(new RestTemplate()), new PropertyManager()));

        http.port(port);
        notFound((req, res) -> {
            res.type("text/html");
            String render = Template.render(req, Template.ERROR_PAGE,
                    ModelEntry.withModel("error", true),
                    ModelEntry.withModel("errorCode", 404));
            return render;
        });

        http.before("*", Filters::captureLanguage);
        http.before("*", Filters::checkNotFound);

        http.get("/", homeHandler.viewHomepage());
        http.get("/employees", employeeHandler.showResults());
    }

}