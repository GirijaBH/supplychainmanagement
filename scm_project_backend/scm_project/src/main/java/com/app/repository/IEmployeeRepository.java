package com.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Long>{

	Employee findByFirstName(String name);
	
	List<Employee> findByJoinDateAfter(LocalDate date);
	
	List<Employee> findByJoinDateBefore(LocalDate date);
	
}
