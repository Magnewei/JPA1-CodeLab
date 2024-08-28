package app.cupcake.persistence.daos;

import app.cupcake.entities.Person;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {
    PersonDAO personDAO = new PersonDAO();

    @Test
    void create() {
        personDAO.create(Person.builder().name("Magnus").age(27).build());
    }

    @Test
    void getById() {
        Person person = personDAO.getById(1);
        String personName =  person.getName();

        // Person's name is Magnus, hence it should not be equal to Anton.
        assertNotEquals("Anton", personName);
    }

    @Test
    void getAll() {
        Set<Person> people = personDAO.getAll();
        assertTrue(people.size() > 0);
    }

    @Test
    void update() {

    }
}