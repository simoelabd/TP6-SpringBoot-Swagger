package com.example.studentmanagement;


import com.example.studentmanagement.controller.StudentController;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentControllerTest {

	@Mock
	private StudentService studentService;

	@InjectMocks
	private StudentController studentController;

	@Test
	void testSaveStudent() {
		Student student = new Student();
		student.setId(1);
		student.setNom("mohamed");

		when(studentService.save(any(Student.class))).thenReturn(student);

		ResponseEntity<Student> response = studentController.save(student);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("mohamed", response.getBody().getNom());
	}

	@Test
	void testDeleteStudent() {
		when(studentService.delete(1)).thenReturn(true);
		ResponseEntity<Void> response = studentController.delete(1);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void testFindAllStudents() {
		Student student1 = new Student();
		Student student2 = new Student();
		when(studentService.findAll()).thenReturn(Arrays.asList(student1, student2));

		ResponseEntity<List<Student>> response = studentController.findAll();

		assertEquals(2, response.getBody().size());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testCountStudents() {
		when(studentService.countStudents()).thenReturn(10L);
		ResponseEntity<Long> response = studentController.countStudent();
		assertEquals(10L, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testFindByYear() {
		when(studentService.findNbrStudentByYear()).thenReturn(Arrays.asList());
		ResponseEntity<Collection<?>> response = studentController.findByYear();
		assertEquals(0, response.getBody().size());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}