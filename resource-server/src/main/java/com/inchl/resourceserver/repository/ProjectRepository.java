package com.inchl.resourceserver.repository;

import com.inchl.resourceserver.entity.ProjectModelEntity;
import com.inchl.resourceserver.entity.UserModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModelEntity, Long> {
}
