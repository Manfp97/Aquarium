package com.example.aquarium.Controllers;

import com.example.aquarium.Entities.Employee;
import com.example.aquarium.Service.EmployeeServi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServi employeeServi;

    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> listEmployees = employeeServi.SearchForEntities();
        model.addAttribute("employees", listEmployees);
        return "employees";
    }

    @GetMapping("/id")
    public String showEmployee(@PathVariable Integer id, Model model) {
        Optional<Employee> employeeOptional = employeeServi.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            model.addAttribute("employee", employee);
            return "employee";
        } else {
            return "error";
        }
    }

    @PostMapping
    public String createEmployee(@RequestBody Employee employee) throws Exception {
        employeeServi.keep(employee);
        return "redirect:/employee";
    }

    @DeleteMapping("/id")
    public String deleteEmployee(@PathVariable Integer id, Model model) {
        employeeServi.deleteById(id);
        return "redirect:/employee";
    }
}
