package org.surya.domain.primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company  {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String zipCode;

	protected Company() {}

	public Company( String name, String zipCode) {
		this.name = name;
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return String.format(
				"Company[id=%d, name='%s', zipcode='%s']",
				id, name, zipCode);
	}

	// end::sample[]

	public Long getId() {
		return id;
	}

	public String getName()
	{
		return name;
	}
	public String getZipCode()
	{
		return zipCode;
	}
}