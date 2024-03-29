package com.carlita.service.showalluniversities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlita.model.entity.University;
import com.carlita.repository.university.UniversityRepository;

@Service
public class ShowAllUniversitiesServiceImpl implements ShowAllUniversitiesService {

	@Autowired
	private UniversityRepository universityRepository;
	
	public List<University> getAllUniversities() {
		return universityRepository.getAllUniversities();
	}
}
