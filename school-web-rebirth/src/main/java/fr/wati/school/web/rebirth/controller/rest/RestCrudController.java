package fr.wati.school.web.rebirth.controller.rest;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import fr.wati.school.web.rebirth.controller.response.JqgridResponse;

public interface RestCrudController<T, DTO extends Serializable> {

	DTO read(int id);

	JqgridResponse<DTO> records(Boolean search, String filters, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * Helper method for returning filtered records
	 */
	JqgridResponse<DTO> getFilteredRecords(String filters, Pageable pageRequest);

	void update(long id, DTO dto);

	ResponseEntity<String> create(DTO dto);

	void delete(long id);

}