package com.example.todo.dao;

import java.util.List;

import com.example.todo.model.Person;


public interface PersonDao{

    Person getPerson(Integer id);
    List<Person> getAllPerson();
    int insertPerson(Person person);
    int deletePerson(Integer id);
    int updatePerson(Integer id, Person person);

}
