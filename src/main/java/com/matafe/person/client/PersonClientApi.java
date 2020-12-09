package com.matafe.person.client;

import java.util.List;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.matafe.person.Person;

public class PersonClientApi {

	public static void main(String[] args) {

		PersonClientApi api = new PersonClientApi();

		api.listPersons();

		System.out.println("==================");

		api.listPerson(1L);

		System.out.println("==================");

		api.createPerson();

		System.out.println("==================");

		api.updatePerson();
	}

	private List<Person> listPersons() {

		Client client = ClientBuilder.newClient();

		String link = "http://localhost:8080/jersey-guice-sample/api";
		String path = "person";

		WebTarget webTarget = client.target(link).path(path);

		Response response = webTarget.request().get();

		// String json = response.readEntity(String.class);
		// System.out.println(json);

		// or

		List<Person> persons = response.readEntity(new GenericType<List<Person>>() {
		});
		persons.forEach(System.out::println);

		client.close();

		return persons;
	}

	private Person listPerson(long personId) {

		Client client = ClientBuilder.newClient();

		String link = "http://localhost:8080/jersey-guice-sample/api";
		String path = "person/{id}";

		WebTarget webTarget = client.target(link).path(path).resolveTemplate("id", personId);

		Response response = webTarget.request().get();

		// String json = response.readEntity(String.class);
		// System.out.println(json);

		// or

		Person person = response.readEntity(Person.class);
		System.out.println(person);

		client.close();
		return person;
	}

	private void createPerson() {
		Client client = ClientBuilder.newClient();

		String link = "http://localhost:8080/jersey-guice-sample/api";
		String path = "person/create";

		WebTarget webTarget = client.target(link).path(path);

		long nextLong = new Random().nextLong();
		Person p = new Person.Builder().withName("Person" + nextLong).withSex("F").build();
		Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).post(Entity.json(p));

		Person person = response.readEntity(Person.class);
		System.out.println(person);

		client.close();

	}

	private void updatePerson() {

		Client client = ClientBuilder.newClient();

		String link = "http://localhost:8080/jersey-guice-sample/api";
		String path = "person/update";

		WebTarget webTarget = client.target(link).path(path);

		Person p = new Person.Builder().withId(1L).withName("John Snow").withSex("M").withActive(true).build();
		Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).post(Entity.json(p));

		Person person = response.readEntity(Person.class);
		System.out.println(person);

		client.close();

	}
}
