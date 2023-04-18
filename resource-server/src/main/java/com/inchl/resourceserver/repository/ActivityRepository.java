package com.inchl.resourceserver.repository;

import com.inchl.resourceserver.entity.ActivityModelEntity;
import com.inchl.resourceserver.entity.BugModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityModelEntity, Long> {
}
