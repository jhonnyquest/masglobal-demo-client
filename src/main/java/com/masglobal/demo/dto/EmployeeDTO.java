/******************************************************************
 *
 * MAS Global - Hiring process application test.
 *
 *
 * © 2019, Jhonny Munoz All rights reserved.
 *
 ******************************************************************/
package com.masglobal.demo.dto;

import java.math.BigDecimal;

/**********************************************************************************************
 * Data transfer object to manage request/responses of employees
 *      note that DTO object class is built using Builder Pattern to make easier and more consistent
 *      the objects creation/update process.
 * @author jmunoz
 * @since 09/03/2019
 * @version 1.0.0
 */

public class EmployeeDTO {
  private Integer id;
  private String name;
  private String contractTypeName;
  private Integer roleId;
  private String roleName;
  private String roleDescription;
  private Double hourlySalary;
  private Double monthlySalary;
  private BigDecimal annualSalary;

  public EmployeeDTO() {
    super();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContractTypeName() {
    return contractTypeName;
  }

  public void setContractTypeName(String contractTypeName) {
    this.contractTypeName = contractTypeName;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleDescription() {
    return roleDescription;
  }

  public void setRoleDescription(String roleDescription) {
    this.roleDescription = roleDescription;
  }

  public Double getHourlySalary() {
    return hourlySalary;
  }

  public void setHourlySalary(Double hourlySalary) {
    this.hourlySalary = hourlySalary;
  }

  public Double getMonthlySalary() {
    return monthlySalary;
  }

  public void setMonthlySalary(Double monthlySalary) {
    this.monthlySalary = monthlySalary;
  }

  public BigDecimal getAnnualSalary() {
    return annualSalary;
  }

  public void setAnnualSalary(BigDecimal annualSalary) {
    this.annualSalary = annualSalary;
  }

  @Override
  public String toString() {
    return "EmployeeDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", contractTypeName='" + contractTypeName + '\'' +
        ", roleId=" + roleId +
        ", roleName='" + roleName + '\'' +
        ", roleDescription='" + roleDescription + '\'' +
        ", hourlySalary=" + hourlySalary +
        ", monthlySalary=" + monthlySalary +
        ", annualSalary=" + annualSalary +
        '}';
  }
}
