package com.example.xml.team3.service;

import com.example.xml.team3.model.grades.Grades;

public interface GradesService {

	public String createNewGrades(Grades grades) throws Exception;

	public Grades findById(String id) throws Exception;

	public String updateGrades(String id, Grades grades) throws Exception;

	public boolean deleteGrades(String id) throws Exception;
}
