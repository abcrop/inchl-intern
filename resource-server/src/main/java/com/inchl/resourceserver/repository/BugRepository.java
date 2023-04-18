package com.inchl.resourceserver.repository;

import com.inchl.resourceserver.entity.BugModelEntity;
import com.inchl.resourceserver.entity.ProjectModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<BugModelEntity, Long> {
}
