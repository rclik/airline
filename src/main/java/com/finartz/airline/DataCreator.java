package com.finartz.airline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public abstract class DataCreator<ENTITY, ID> {

	protected CrudRepository<ENTITY, ID> repository;

	@Autowired
	public DataCreator(CrudRepository<ENTITY, ID> repository) {
		this.repository = repository;
	}

	public void load() {
		createInitialAirlines().forEach(airline -> {
			repository.save(airline);
		});
	}

	protected abstract List<ENTITY> createInitialAirlines();

}
