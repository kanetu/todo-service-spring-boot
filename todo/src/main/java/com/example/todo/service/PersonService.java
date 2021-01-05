package com.example.todo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.example.todo.dao.PersonDao;
import com.example.todo.model.Person;

import org.hibernate.exception.DataException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonService implements PersonDao {

    private JdbcTemplate jdbcTemplate;

    public PersonService(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Person getPerson(Integer id){
		String query = "SELECT * FROM Person WHERE person_id=" + id;

		ResultSetExtractor<Person> extractor = new ResultSetExtractor<Person>(){
			@Override
			public Person extractData(ResultSet rs) throws SQLException, DataException{
				if(rs.next()){
					return new Person(id, rs.getString("person_name"));
				}
				return null;
			}
		};

		return jdbcTemplate.query(query, extractor);
    }

	@Override
	public int insertPerson(Person person) {
        // TODO Auto-generated method stub
        String query = "INSERT INTO Person(person_name) VALUES(?)";
        return jdbcTemplate.update(query, person.getName());
	}

	@Override
	public int deletePerson(Integer id) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM Person WHERE person_id=" + id;
		return jdbcTemplate.update(query);
	}

	@Override
	public int updatePerson(Integer id, Person person) {
		// TODO Auto-generated method stub
		String query = "UPDATE Person SET person_name=? WHERE person_id=?";
		return jdbcTemplate.update(query, person.getName(), id);
	}

	@Override
	public List<Person> getAllPerson() {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM Person";
		RowMapper<Person> rowMapper = new RowMapper<Person>(){
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Person(rs.getInt("person_id"), rs.getString("person_name"));
			}
		};

		return jdbcTemplate.query(query, rowMapper);
	}

}