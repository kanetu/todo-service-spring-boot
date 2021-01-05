package com.example.todo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.example.todo.model.Person;
import com.example.todo.service.PersonService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class PersonServiceTest {
    private DriverManagerDataSource dataSource;
    private PersonService personService;

    @BeforeEach
    void setupBeforeEach(){
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        personService = new PersonService(dataSource);
    }

    @Test
    public void testGetPerson(){
        Integer id = 1;
        Person person = personService.getPerson(id);

        assertNotNull(person);
    }

	@Test
	public void testInsertPerson() {
        Person person = new Person("Sufuijk");
        int result = personService.insertPerson(person);

        assertTrue(result > 0);
    }

	@Test
	public void testDeletePerson() {
        Integer id = 5;
        int result = personService.deletePerson(id);

        assertTrue(result > 0);
	}

	@Test
	public void testUpdatePerson() {
        Person person = new Person("Sufuijk");
        int result = personService.updatePerson(2, person);
        assertTrue(result > 0);
    }
    
    @Test
	public void testGetAllPerson() {
        List<Person> people = personService.getAllPerson();

        for(Person person: people){
            System.out.println(person);
        }

        assertTrue(!people.isEmpty());
	}
}
