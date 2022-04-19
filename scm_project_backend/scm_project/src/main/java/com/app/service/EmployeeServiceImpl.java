package com.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Employee;
import com.app.repository.IEmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private IEmployeeRepository employeeRepo;
	
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepo.findAll();
	}

	@Override
	public Employee addOrUpdateEmployeeDetails(Employee emp) {
		// TODO Auto-generated method stub
		Employee newEmp = employeeRepo.save(emp);
		newEmp.setDepartment(emp.getDepartment());
		return newEmp;
	}

	@Override
	public String deleteEmpDetails(long id) {
		employeeRepo.deleteById(id);
		return "Emp Details with ID " + id + " deleted successfuly... ";
	}

	@Override
	public Employee fetchEmpDetails(long empId) {
		// TODO Auto-generated method stub
		return employeeRepo.findById(empId).orElseThrow(() -> new com.app.customexceptionhandler.ResourceNotFoundException("Emp by ID " + empId + " not found!!!!"));
	}

	

}
