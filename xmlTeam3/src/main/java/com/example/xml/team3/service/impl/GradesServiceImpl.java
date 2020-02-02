package com.example.xml.team3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xml.team3.model.grades.Grades;
import com.example.xml.team3.repository.GradesRepository;
import com.example.xml.team3.service.GradesService;

@Service
public class GradesServiceImpl implements GradesService {

	@Autowired
	private GradesRepository gradesRepository;

	public String createNewGrades(Grades grades) throws Exception {
		return gradesRepository.save(grades);
	}

	public Grades findById(String id) throws Exception {
		return gradesRepository.findById(id);
	}

	public String updateGrades(String id, Grades grades) throws Exception {
		return gradesRepository.update(id, grades);
	}

	public boolean deleteGrades(String id) throws Exception {
		return gradesRepository.delete(id);
	}

}
