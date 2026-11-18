package edu.kiet.controller;

import edu.kiet.entity.HostelStudent;
import edu.kiet.repository.HostelStudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/students")
public class HostelStudentController {

    @Autowired
    private HostelStudentRepository repository;


    // 1. ADD STUDENT

    @PostMapping
    public HostelStudent addStudent(
            @RequestBody HostelStudent student) {

        return repository.save(student);
    }


    // 2. GET ALL STUDENTS

    @GetMapping
    public List<HostelStudent> getAllStudents() {

        return repository.findAll();
    }


    // 3. GET STUDENT BY ID

    @GetMapping("/{id}")
    public ResponseEntity<HostelStudent> getStudentById(
            @PathVariable int id) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // 4. UPDATE STUDENT

    @PutMapping("/{id}")
    public ResponseEntity<HostelStudent> updateStudent(
            @PathVariable int id,
            @RequestBody HostelStudent updatedStudent) {

        return repository.findById(id)
                .map(student -> {

                    student.setHostelName(
                            updatedStudent.getHostelName());

                    student.setRoomNumber(
                            updatedStudent.getRoomNumber());

                    student.setFee(
                            updatedStudent.getFee());

                    HostelStudent saved =
                            repository.save(student);

                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    // 5. DELETE STUDENT

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable int id) {

        if (repository.existsById(id)) {

            repository.deleteById(id);

            return ResponseEntity.ok(
                    "Student deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Student not found");
    }

}
