package com.sm.technical_test.repository;

import com.sm.technical_test.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {

}
