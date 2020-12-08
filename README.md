# jersey-guice-example

Simple Person JAX-RS Resource (managed by Jersey) using com.google.inject.Inject annotation to inject a Guice Service (managed by Guice)

 - [http://localhost:8080/jersey-guice-sample/api/person](http://localhost:8080/jersey-guice-sample/api/person)
 - [http://localhost:8080/jersey-guice-sample/api/person/1](http://localhost:8080/jersey-guice-sample/api/person/1)
 
 Example:
 
 ```java
@Produces(MediaType.APPLICATION_JSON)
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
		Collection<Person> persons = personService.findAllPersons();

		logger.debug("Found {} persons from service {}", persons.size(), personService);
		return Response.ok(persons).build();
	}
```