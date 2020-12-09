package com.matafe.person;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.matafe.ErrorResponse;

/**
 * Person Resource
 * 
 * @author ferrazm
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/person")
public class PersonResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final PersonService personService;

	@Inject
	PersonResource(PersonService personService) {
		this.personService = personService;
	}

	@GET
	public Response getPersons() {
		// PersonService personService = new PersonService();
		// PersonService personService = ClassUtils.createInstance(PersonService.class);
		Collection<Person> persons = personService.findAllPersons();

		logger.debug("Found {} persons from service {}", persons.size(), personService);
		return Response.ok(persons).build();
	}

	@GET
	@Path("{id}")
	public Response getPersonById(@PathParam("id") final Long id) {
		Person person = personService.findPersonById(id);
		logger.debug("Found person with id {} - {} from service: {}", id, person, personService);

		if (person == null) {
			return Response.ok(new ErrorResponse.Builder().withMessge("Person not found!").build()).build();
		}

		return Response.ok(person).build();
	}

	@POST
	@Path("create")
	public Response create(final Person person) {
		personService.addPerson(person);
		return Response.ok(person).build();
	}

	@POST
	@Path("update")
	public Response update(final Person person) {
		personService.updatePerson(person);
		return Response.ok(person).build();
	}

}
