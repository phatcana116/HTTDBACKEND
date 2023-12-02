package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Entity.Goal;
import com.example.HTTD.Entity.Wallet;
import com.example.HTTD.Repository.GoalRepository;
import com.example.HTTD.Service.GoalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GoalServiceImpl implements GoalService {

    GoalRepository goalRepository;

    @Override
    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public Goal getGoalById(Long goalId) {
        Optional<Goal> optional = goalRepository.findById(goalId);
        return optional.orElse(null);
    }

    @Override
    public List<Goal> getAllGoal() {
        return goalRepository.findAll();
    }

    @Override
    public Goal updateGoal(Goal goal) {
        Goal existinggoal = goalRepository.findById(goal.getId()).get();
        existinggoal.setName(goal.getName());
        existinggoal.setAmount(goal.getAmount());
        existinggoal.setCurrentAmount(goal.getCurrentAmount());
        existinggoal.setStartDay(goal.getStartDay());
        existinggoal.setEndDay(goal.getEndDay());
        Goal updatedGoal = goalRepository.save(existinggoal);
        return updatedGoal;
    }

    @Override
    public boolean deleteGoal(Long goalId) {
        Optional<Goal> goalOptional = goalRepository.findById(goalId);
        if (goalOptional.isPresent()) {
            goalRepository.deleteById(goalId);
            return true;
        }
        return false;
    }
}
