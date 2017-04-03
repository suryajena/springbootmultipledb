package org.surya.domain.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
public interface CompanyRepository extends CrudRepository<Company, Long> {

	List<Company> findByName(String name);
}