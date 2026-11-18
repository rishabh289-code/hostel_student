package edu.kiet.repository;

import edu.kiet.entity.HostelStudent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HostelStudentRepository
        extends JpaRepository<HostelStudent, Integer> {

}
