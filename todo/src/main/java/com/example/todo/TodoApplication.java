package com.example.todo;



import java.util.List;

import com.example.todo.model.Person;
import com.example.todo.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class TodoApplication {

    private static DriverManagerDataSource dataSource;

	
	@Autowired
	private static PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);

		dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
		dataSource.setPassword("postgres");
		
		personService = new PersonService(dataSource);

		List<Person> people = personService.getAllPerson();

		people.forEach(p -> {
			System.out.println(p.getId() + "-" + p.getName());
		});
	}

}
