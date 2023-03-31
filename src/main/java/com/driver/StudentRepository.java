package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentPair;

    public StudentRepository() {
        studentMap = new HashMap<>();
        teacherMap = new HashMap<>();
        teacherStudentPair = new HashMap<>();
    }

    //1
    public ResponseEntity<String> addStudent(Student student){
        String studentName = student.getName();

        studentMap.put(studentName, student);

        return new ResponseEntity<>("Added the Student", HttpStatus.CREATED);
    }

    //2
    public ResponseEntity<String> addTeacher(Teacher teacher){
        String teacherName = teacher.getName();

        teacherMap.put(teacherName, teacher);
        teacherStudentPair.put(teacherName, new ArrayList<>());

        return new ResponseEntity<>("Added the Teacher", HttpStatus.CREATED);
    }

    //3
    public ResponseEntity<String> addStudentTeacherPair(String studentName, String teacherName){
        if(studentMap.containsKey(studentName) && teacherStudentPair.containsKey(teacherName)){
            teacherStudentPair.get(teacherName).add(studentName);

            return new ResponseEntity<>("Added the Pair", HttpStatus.CREATED);
        }

        return null;
    }

    //4
    public ResponseEntity<Student> getStudentByName(String studentName){
        Student student = studentMap.getOrDefault(studentMap, null);

        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }

    //5
    public ResponseEntity<Teacher> getTeacherByName(String teacherName){
        Teacher teacher = teacherMap.getOrDefault(teacherName, null);

        return new ResponseEntity<>(teacher, HttpStatus.NOT_FOUND);
    }

    //6
    public ResponseEntity<List<String>> getStudentsByTeacherName(String teacherName){
        List<String> studentsList = teacherStudentPair.getOrDefault(teacherName, null);

        return new ResponseEntity<>(studentsList, HttpStatus.FOUND);
    }

    //7
    public ResponseEntity<List<String>> getAllStudentsList(){
        List<String> studentsList = new ArrayList<>(studentMap.keySet());

        return new ResponseEntity<>(studentsList, HttpStatus.FOUND);
    }

    //8
    public void deleteTeacherByName(String teacherName){
        teacherMap.remove(teacherName);

        for(String studentName : teacherStudentPair.get(teacherName)){
            studentMap.remove(studentName);
        }

        teacherStudentPair.remove(teacherName);
    }

    //9
    public void deleteAllTeachers(){
        teacherMap.clear();

        for(String teacherName : teacherStudentPair.keySet()){
            for(String studentName : teacherStudentPair.get(teacherName)){
                studentMap.remove(studentName);
            }
        }

        teacherStudentPair.clear();
    }
}
