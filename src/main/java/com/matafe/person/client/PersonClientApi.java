package com.matafe.person.client;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import com.matafe.person.Person;

public class PersonClientApi {

	private final WebTarget webTarget;

	public PersonClientApi(final String url) {
		Client client = ClientBuilder.newClient();
		this.webTarget = client.target(url);
	}

	public static void main(String[] args) {

		String url = "http://localhost:8080/jersey-guice-sample/api";
		PersonClientApi api = new PersonClientApi(url);

		List<Person> persons = api.listPersons();
		persons.forEach(System.out::println);

		System.out.println("==================");

		Person person = api.listPerson(1L);
		System.out.println(person);

		System.out.println("==================");

		Person pc = new Person.Builder().withName("Person" + new Random().nextLong()).withSex("F").build();
		Person createdPerson = api.createPerson(pc);
		System.out.println(createdPerson);

		System.out.println("==================");

		Person pu = new Person.Builder().withId(1L).withName("John Snow " + LocalDateTime.now()).withSex("M")
				.withActive(true).build();
		Person updatePerson = api.updatePerson(pu);
		System.out.println(updatePerson);
	}

	List<Person> listPersons() {

		Response response = this.webTarget.path("person").request().get();

		// String json = response.readEntity(String.class);
		// System.out.println(json);

		// or

		return response.readEntity(new GenericType<List<Person>>() {
		});
	}

	Person listPerson(long personId) {

		Response response = this.webTarget.path("person/{id}").resolveTemplate("id", personId).request().get();

		// String json = response.readEntity(String.class);
		// System.out.println(json);

		// or
		return response.readEntity(Person.class);
	}

	Person createPerson(Person person) {
		Response response = webTarget.path("person/create").request().post(Entity.json(person));
		return response.readEntity(Person.class);
	}

	Person updatePerson(Person person) {
		Response response = this.webTarget.path("person/update").request().post(Entity.json(person));
		return response.readEntity(Person.class);
	}
}
