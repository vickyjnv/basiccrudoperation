package com.example.springcrud.controller;

import com.example.springcrud.exception.ResourceNotFoundException;
import com.example.springcrud.model.Employee;
import com.example.springcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

@GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();

    }
    //create employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
    return employeeRepository.save(employee);
    }
    //get employee by rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity <Employee> getEmployeeById(@PathVariable Long id ){
    Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee not exist with id:"+id));
    return ResponseEntity.ok(employee);

    }
    //update employee rest api
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee not exist with id:"  + id));


        employee.setFirstName(employeeDetails.getFirstName());
        employeeDetails.setLastName(employeeDetails.getLastName());
        employeeDetails.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee=employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    //delete employee rest
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<java.util.Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
    Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id"+id));
    employeeRepository.delete(employee);
    Map<String, Boolean> response=new HashMap<>();
    response.put("deleted",Boolean.TRUE);
    return ResponseEntity.ok(response);

    }
}


