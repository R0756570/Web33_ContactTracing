package domain.db;

import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonService {
	private Map<String, Person> persons = new HashMap<>();
	
	public PersonService () {
		Person administrator = new Person("admin", "admin@ucll.be", "t", "Ad", "Ministrator");
		Person user = new Person("user", "user@ucll.be", "t", "Mr", "Users");
		Person test = new Person("test", "test@ucll.be", "t", "Mr", "Test");

		add(administrator);
		add(test);
		add(user);
	}
	
	public Person get(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		return persons.get(personId);
	}
	
	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());	
	}

	public boolean correctLogin (String userid, String password) {
		return (persons.containsKey(userid) && persons.get(userid).isCorrectPassword(password));
	}

	/*public Person vind(String naam,String voornaam) {
		if (naam == null || voornaam==null || naam.trim().length()==0 || voornaam.trim().length()==0) throw new IllegalArgumentException("Gelieve alles in te vullen");
		if(persons.containsValue(voornaam))
		return null;
	}*/

	public void add(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if (persons.containsKey(person.getUserid())) {
			throw new DbException("User already exists");
		}
		persons.put(person.getUserid(), person);
	}
	
	public void update(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if(!persons.containsKey(person.getUserid())){
			throw new DbException("No person found");
		}
		persons.put(person.getUserid(), person);
	}
	
	public void delete(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		persons.remove(personId);
	}

	public int getNumberOfPersons() {
		return persons.size();
	}
}
