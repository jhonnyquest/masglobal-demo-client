package com.masglobal.demo.services.impl;

import com.masglobal.demo.dto.EmployeeDTO;
import com.masglobal.demo.services.APIManager;
import com.masglobal.demo.utils.PropertyManager;
import com.masglobal.demo.utils.RestTemplateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class APIManagerImpl implements APIManager {

	@Autowired
	private RestTemplateHelper client;

  private Properties pm;

	public APIManagerImpl(RestTemplateHelper client, PropertyManager pm) {
		this.client = client;
    this.pm = pm.getInstance();
	}

  @Override
  public EmployeeDTO getEmployeeById(Integer employeeId) {
      ResponseEntity<EmployeeDTO> response = client.processRequestGet(
          pm.getProperty("employees.api.base.endpoint") + "/" + employeeId, EmployeeDTO.class);
      if (response.getStatusCode().equals(HttpStatus.OK)) {
          return Objects.requireNonNull(response.getBody());
      } else {
          return null;
      }
  }

    @Override
    public List<EmployeeDTO> getEmployees() {
	    ResponseEntity<EmployeeDTO[]> response = client.processRequestGet(
          pm.getProperty("employees.api.base.endpoint"), EmployeeDTO[].class);
	    if (response != null && response.getStatusCode().equals(HttpStatus.OK)) {
	        return Arrays.asList(Objects.requireNonNull(response.getBody()));
	    } else {
	        return null;
	    }
    }
}
