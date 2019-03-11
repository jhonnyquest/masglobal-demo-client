package com.masglobal.demo.handlers;

import com.masglobal.demo.Template;
import com.masglobal.demo.services.APIManager;
import com.masglobal.demo.services.impl.APIManagerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import spark.Route;

import static com.masglobal.demo.ModelEntry.withModel;
import static com.masglobal.demo.Template.render;

public class HomeHandler {

  @Autowired
  APIManager apiManager;

  private final static Logger LOGGER = LogManager.getLogger(APIManagerImpl.class);

  public HomeHandler(APIManager apiManager) {
    this.apiManager = apiManager;
  }

  public Route viewHomepage() {
    return (req, resp) -> render(req, Template.HOME,
        withModel("search", false),
        withModel("employeeId", ""));
  }
}
