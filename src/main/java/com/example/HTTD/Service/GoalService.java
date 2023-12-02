package com.example.HTTD.Service;

import com.example.HTTD.Entity.Goal;

import java.util.List;

public interface GoalService {
    Goal createGoal(Goal goal);

    Goal getGoalById(Long goalId);

    List<Goal> getAllGoal();

    Goal updateGoal(Goal goal);

    boolean deleteGoal(Long goalId);
}
