package com.masglobal.demo.handlers;

import com.masglobal.demo.Template;
import com.masglobal.demo.dto.*;
import com.masglobal.demo.services.APIManager;
import com.masglobal.demo.services.impl.APIManagerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import spark.Route;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.masglobal.demo.ModelEntry.withModel;
import static com.masglobal.demo.Template.render;

public class EmployeeHandler {

    @Autowired
    APIManager apiManager;

    private final static Logger LOGGER = LogManager.getLogger(APIManagerImpl.class);

    public EmployeeHandler(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    public Route showResults() {
        return (req, resp) -> {
          try{
            List<EmployeeDTO> employees = new ArrayList<>();
            String employeeId = req.queryParams("employeeId");
            if(Objects.nonNull(employeeId) && !employeeId.isEmpty()) {
                EmployeeDTO employee = apiManager.getEmployeeById(Integer.valueOf(employeeId));
                if(Objects.nonNull(employee)) {
                  employees.add(employee);
                }
              } else {
                employees = apiManager.getEmployees();
              }

              if(employees.isEmpty()) {
                return render(req, Template.HOME,
                    withModel("search", false),
                    withModel("employeeId", employeeId),
                    withModel("error", true));
              }

            for (EmployeeDTO empl : employees) {
              empl.setAnnualSalary(empl.getAnnualSalary().setScale(2, BigDecimal.ROUND_HALF_UP));
            }

            return render(req, Template.HOME,
                      withModel("employees", employees),
                      withModel("search", true),
                      withModel("employeeId", employeeId),
                      withModel("error", false));
            } catch (HttpClientErrorException ex) {
                LOGGER.error(ex);
                return render(req, Template.ERROR_PAGE,
                        withModel("error", true),
                        withModel("errorCode", ex.getStatusCode().value()));
            }
        };
    }
}