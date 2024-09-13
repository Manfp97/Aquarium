package com.example.aquarium.Service;

import com.example.aquarium.Entities.Employee;
import com.example.aquarium.Repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServi extends AbstractBusinessService<Employee, Integer, EmployeeRepository>{
    protected EmployeeServi(EmployeeRepository employeeRepository) {super(employeeRepository);}
}
