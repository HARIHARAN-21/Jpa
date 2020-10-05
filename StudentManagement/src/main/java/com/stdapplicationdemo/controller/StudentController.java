package com.stdapplicationdemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stdapplicationdemo.model.Student;
import com.stdapplicationdemo.model.Studentdto;
import com.stdapplicationdemo.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	public StudentRepository studentRepository;
	
	
	@PostMapping(value="/save")
	public String insertData(@RequestBody Student student) {
		studentRepository.save(student);
		return "Inserted Successfully";
	}
	
	@GetMapping(value="/getdata")
	public List<Student> getStudent(){
		return studentRepository.findAll();
	}
	
	@DeleteMapping(value="/delete/{id}")
	public String deleteStudent(@PathVariable Integer id) {
		studentRepository.deleteById(id);
		return "Deleted Successfully";
	}
	
	@PutMapping(value="/update")
	public String updateStudent(@RequestBody Studentdto studentdto) {
		Optional<Student> student=studentRepository.findById(studentdto.getId());
		if(student.isPresent()) {
			student.get().setCity(studentdto.getCity());
			student.get().setMail(studentdto.getMail());
		}
		studentRepository.save(student.get());
		return "Updated Successfully";
	}

}
