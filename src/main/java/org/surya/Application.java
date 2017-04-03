
package org.surya;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.surya.domain.primary.Company;
import org.surya.domain.primary.CompanyRepository;
import org.surya.domain.secondary.Customer;
import org.surya.domain.secondary.CustomerRepository;
@SpringBootApplication
public class Application {


	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		//SpringApplication.run(Application.class);
		SpringApplication app = new SpringApplication(Application.class);
		app.setWebEnvironment(false); //<<<<<<<<<
		app.run(args);

		//ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

		//Application mainObj = ctx.getBean(Application.class);

	}

	@Bean
	public CommandLineRunner demoCompany(  CompanyRepository repository) {
		return (args) -> {
			// save a couple of customers

			repository.save(new Company("ibm", "10010"));
			repository.save(new Company("Microsoft", "10012"));

			repository.save(new Company("CTS", "10014"));
			repository.save(new Company("ibm", "10015"));

			// fetch all customers
			log.info("company found with findAll():");
			log.info("-------------------------------");
			for (Company company : repository.findAll()) {
				log.info(company.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Company company = repository.findOne(1L);

			if(null !=company)
			{
				log.info("Customer found with findOne(1L):");
				log.info("--------------------------------");
				log.info(company.toString());
			}
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Company bauer : repository.findByName("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}
	@Bean("customerIn")
	public CommandLineRunner demoCustomerIn(  CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);

			if(null !=customer)
			{
				log.info("Customer found with findOne(1L):");
				log.info("--------------------------------");
				log.info(customer.toString());
			}
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}

}