package com.example.springcrud.repository;

import com.example.springcrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;
@Repository
public interface EmployeeRepository extends JpaRepository <Employee,Long>{
}
