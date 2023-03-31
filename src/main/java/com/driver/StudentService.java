package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    //1
    public ResponseEntity<String> addStudent(Student student){
        return studentRepository.addStudent(student);
    }

    //2
    public ResponseEntity<String> addTeacher(Teacher teacher){
        return studentRepository.addTeacher(teacher);
    }

    //3
    public ResponseEntity<String> addStudentTeacherPair(String studentName, String teacherName){
        return addStudentTeacherPair(studentName, teacherName);
    }

    //4
    public ResponseEntity<Student> getStudentByName(String studentName){
        return studentRepository.getStudentByName(studentName);
    }

    //5
    public ResponseEntity<Teacher> getTeacherByName(String teacherName){
        return studentRepository.getTeacherByName(teacherName);
    }

    //6
    public ResponseEntity<List<String>> getStudentsByTeacherName(String teacherName){
        return studentRepository.getStudentsByTeacherName(teacherName);
    }

    //7
    public ResponseEntity<List<String>> getAllStudents(){
        return studentRepository.getAllStudentsList();
    }

    //8
    public ResponseEntity<String> deleteTeacherByName(String teacherName){
        studentRepository.deleteTeacherByName(teacherName);

        return new ResponseEntity<>("Teacher data deleted", HttpStatus.OK);
    }

    //9
    public ResponseEntity<String> deleteAllTeachers(){
        studentRepository.deleteAllTeachers();

        return new ResponseEntity<>("All teacher's data deleted", HttpStatus.OK);
    }
}
