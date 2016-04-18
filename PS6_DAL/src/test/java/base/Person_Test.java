package base;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.expression.ParseException;

import domain.PersonDomainModel;

public class Person_Test {

	static PersonDomainModel per;
	
	@BeforeClass
	
	public static void setUpBeforeClass() throws Exception {
		
		per = new PersonDomainModel();
		per.setPersonID(UUID.randomUUID());
		per.setFirstName("Brianna");
		per.setLastName("Falcone");
		per.setStreet("Charles Avenue");
		per.setPostalCode(11762);
		per.setCity("Massapequa Park");
		
		Date exDate = new Date();
		per.setBirthday(exDate);
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		UUID ID1 = per.getPersonID();
		PersonDAL.deletePerson(ID1);
	}
	
	@Test
	public void add_test() {
		UUID ID1 = per.getPersonID();
		PersonDomainModel per2;
		per2 = PersonDAL.getPerson(ID1);
		assertNull(per2);
		
		PersonDAL.addPerson(per);
		per2 = PersonDAL.getPerson(ID1);
		assertNotNull(per2);
	}
	
	@Test
	public void delete_test(){
		
		UUID ID1 = per.getPersonID();
		PersonDomainModel per2;
		per2 = PersonDAL.getPerson(ID1);
		assertNull(per2);
		
		PersonDAL.addPerson(per);
		per2 = PersonDAL.getPerson(ID1);
		assertNotNull(per2);
		
		PersonDAL.deletePerson(ID1);
		per2 = PersonDAL.getPerson(ID1);
		assertNull(per2);
	}
	
	@Test
	public void update_test(){
		UUID ID1 = per.getPersonID();
		PersonDomainModel per2;
		per2 = PersonDAL.getPerson(ID1);
		assertNull(per2);
		
		PersonDAL.addPerson(per);
		
		final String fname = "Nicole";
		
		PersonDomainModel per3 = per;
		per3.setFirstName(fname);
		
		PersonDAL.updatePerson(per3);
		
		assertTrue(per.getFirstName() == fname);
		
		
	}

}
