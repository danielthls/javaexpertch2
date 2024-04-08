package com.devsuperior.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.EmployeeDTO;
import com.devsuperior.demo.entities.Department;
import com.devsuperior.demo.entities.Employee;
import com.devsuperior.demo.repositories.DepartmentRepository;
import com.devsuperior.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired 
	private DepartmentRepository departmentRepository;
	
	@Transactional(readOnly = true) 
	public Page<EmployeeDTO> findAll(Pageable pageable) {
		Page<Employee> page = repository.findAll(pageable);//Sort.by("name"));
		return page.map(x -> new EmployeeDTO(x));
	}
	
	@Transactional
	public EmployeeDTO insert(EmployeeDTO dto) {
		Department department = departmentRepository.findById(dto.getDepartmentId()).get();
		Employee entity = new Employee();
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setDepartment(department);
		entity = repository.save(entity);
		return new EmployeeDTO(entity);
	}
}
