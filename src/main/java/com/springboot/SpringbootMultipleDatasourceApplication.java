package com.springboot;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.model.Employee;
import com.springboot.employee.repository.EmployeeRepository;
import com.springboot.user.model.User;
import com.springboot.user.repository.UserRepository;

@SpringBootApplication

@RestController
public class SpringbootMultipleDatasourceApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@PostConstruct
	public void init() {

		userRepository.saveAll(
				Stream.of(new User("Satish", "prime"), new User("Mahesh", "Non-prime")).collect(Collectors.toList()));

		employeeRepository
				.saveAll(Stream.of(new Employee("John", "Software Engineer"), new Employee("Sonali", "Teacher"))
						.collect(Collectors.toList()));
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMultipleDatasourceApplication.class, args);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		List<User> useList = userRepository.findAll();

		return ResponseEntity.ok().body(useList);
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> empList = employeeRepository.findAll();
		return ResponseEntity.ok().body(empList);
	}

}
