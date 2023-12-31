package com.geekster.UniversityEventManagement.service;

import com.geekster.UniversityEventManagement.model.Event;
import com.geekster.UniversityEventManagement.model.Student;
import com.geekster.UniversityEventManagement.model.type;
import com.geekster.UniversityEventManagement.repository.IEventRepo;
import com.geekster.UniversityEventManagement.repository.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class UEMService {
    @Autowired
    static IEventRepo iEventRepo;
    static IStudentRepo iStudentRepo;

    public static Iterable<Student> GetAllStudents() {
        return iStudentRepo.findAll();
    }

    public static String addStudent(Student student) {
        iStudentRepo.save(student);
        return "Student added";
    }

    public static String deleteStudent(Integer id) {
        iStudentRepo.deleteById(id);
        return "Student deleted";
    }

    public static Student studentById(Integer id) {
        Optional<Student> obj = iStudentRepo.findById(id);
        return obj.get();
    }

    public static String UpdateDept(Integer id , type Type) {
        Optional<Student> obj=iStudentRepo.findById(id);
        Student student=null;
        if(obj.isPresent()){
            student=obj.get();
        }
        else{
            return "Invalid Id";
        }
        student.setDepartment(Type);
        iStudentRepo.save(student);
        return "Department Updated for Student id "+id;
    }

    public static String addEvent(Event event) {
        iEventRepo.save(event);
        return "Event added";
    }

    public static String removeEvent(Integer id) {
        iEventRepo.deleteById(id);
        return "Event deleted ";
    }

    public static List<Event> getEventByData(LocalDate date) {
        Iterable<Event> obj=iEventRepo.findAll();
        List<Event> list = null;
        for(Event event:obj){
            if(event.getDate().equals(date)){
                list.add(event);
            }
        }
        return list;
    }

    public static String updateEvent(Integer id, String name, String loc, LocalDate d, LocalTime st, LocalTime et) {
        Optional<Event> obj=iEventRepo.findById(id);
        Event eve1=null;
        if(obj.isPresent()){
            eve1=obj.get();
        }
        else {
            return "Invalid Id";
        }
        eve1.setEventName(name);
        eve1.setLocation(loc);
        eve1.setDate(d);
        eve1.setStartTime(st);
        eve1.setEndTime(et);
        iEventRepo.save(eve1);
        return "Event updated";
    }
}
