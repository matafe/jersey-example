package com.matafe.person;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.google.inject.Singleton;

/**
 * Person Service
 * 
 * @author ferrazm
 */
@Singleton
public class PersonService {

	// This should not exits and data comes from some Repository.
	public final Map<Long, Person> personsMap = new LinkedHashMap<>();
	private final AtomicLong idGenerator = new AtomicLong();

	public PersonService() {
		personsMap.put(idGenerator.incrementAndGet(),
				new Person.Builder().withId(idGenerator.get()).withName("John").withSex("M").withActive(true).build());
		personsMap.put(idGenerator.incrementAndGet(),
				new Person.Builder().withId(idGenerator.get()).withName("Ann").withSex("F").withActive(true).build());
		personsMap.put(idGenerator.incrementAndGet(),
				new Person.Builder().withId(idGenerator.get()).withName("Paul").withSex("M").withActive(false).build());
		personsMap.put(idGenerator.incrementAndGet(),
				new Person.Builder().withId(idGenerator.get()).withName("Kate").withSex("F").withActive(false).build());
	}

	public Collection<Person> findAllPersons() {
		return personsMap.values();
	}

	public Person findPersonById(Long id) {
		return personsMap.get(id);
	}

	public Person addPerson(Person p) {
		long id = idGenerator.incrementAndGet();
		p.setId(id);
		return personsMap.put(id, p);
	}

	public Person updatePerson(Person p) {
		Person person = personsMap.get(p.getId());
		person.setName(p.getName());
		person.setSex(p.getSex());
		person.setActive(p.isActive());
		return personsMap.put(p.getId(), person);
	}
	
	public void removePerson(Person p) {
		personsMap.remove(p.getId());
	}
}
