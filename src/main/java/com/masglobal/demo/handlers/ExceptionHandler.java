package com.masglobal.demo.handlers;

import com.masglobal.demo.Template;
import com.masglobal.demo.services.impl.APIManagerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.HttpClientErrorException;
import spark.Route;

import static com.masglobal.demo.ModelEntry.withModel;
import static com.masglobal.demo.Template.render;

public class ExceptionHandler {
    private final static Logger LOGGER = LogManager.getLogger(APIManagerImpl.class);

    public ExceptionHandler() {}

    public Route showError(HttpClientErrorException ex) {
        return (req, resp) -> {
            LOGGER.error(ex);
            return render(req, Template.ERROR_PAGE,
                    withModel("error", true),
                    withModel("errorCode", ex.getCause().getMessage()));
        };
    }
}
