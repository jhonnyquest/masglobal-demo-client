package com.masglobal.demo.services;

import com.masglobal.demo.dto.EmployeeDTO;

import java.util.List;

public interface APIManager {

    EmployeeDTO getEmployeeById(Integer employeeId);

    List<EmployeeDTO> getEmployees();
}
