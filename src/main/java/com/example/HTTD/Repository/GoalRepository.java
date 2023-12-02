package com.example.HTTD.Repository;

import com.example.HTTD.Entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
}
