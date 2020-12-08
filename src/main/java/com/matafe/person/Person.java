package com.matafe.person;

import java.util.Objects;

/**
 * Person
 * 
 * @author ferrazm
 */
public class Person {

	private Long id;
	private String name;
	private String sex;
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", sex=" + sex + ", active=" + active + "]";
	}

	/**
	 * Person Builder
	 * 
	 * @author ferrazm
	 */
	public static final class Builder {

		private final Person person;

		public Builder() {
			this.person = new Person();
		}

		public Builder withId(final Long id) {
			this.person.setId(id);
			return this;
		}

		public Builder withName(final String name) {
			this.person.setName(name);
			return this;
		}

		public Builder withActive(final boolean active) {
			this.person.setActive(active);
			return this;
		}

		public Builder withSex(final String sex) {
			this.person.setSex(sex);
			return this;
		}

		public Person build() {
			return this.person;
		}
	}

}
