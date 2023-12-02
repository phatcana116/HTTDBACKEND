package com.example.HTTD.Controller;


import com.example.HTTD.Entity.Goal;
import com.example.HTTD.Service.GoalService;
import com.example.HTTD.reponse.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("api/auth/goal")
public class GoalController {
    @Autowired
    private GoalService goalService;

    @PostMapping
    public ResponseEntity<ResponseObject> createGoal(@RequestBody Goal goal){
        try{
            Goal savedGoal = goalService.createGoal(goal);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Tạo mục tiêu thành công",true, savedGoal)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Có lỗi xảy ra khi tạo mục tiêu",false, "")
            );
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getGoalById(@PathVariable("id") Long GoalId){
        try{
            Goal goal = goalService.getGoalById(GoalId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, goal)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Mã mục tiêu không tồn tại",false, "")
            );
        }

    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllGoals(){
        try{
            List<Goal> listGoal = goalService.getAllGoal();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, listGoal)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(0, "Thất bại, có lỗi xảy ra",false, "")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateGoal(@PathVariable("id") Long GoalId, @RequestBody Goal goal){
        try{
            goal.setId(GoalId);
            Goal updatedGoal = goalService.updateGoal(goal);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Cập nhật thành công",true, updatedGoal)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(0, "Cập nhật thất bại",false, "")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteGoal(@PathVariable("id") Long GoalId){
        try {
            boolean isDeleted = goalService.deleteGoal(GoalId);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(1, "Xóa thành công", true, "")
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(0, "Xóa thất bại, không tìm thấy mục tiêu có ID: " + GoalId, false, "")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject(0, "Lỗi khi xóa mục tiêu: " + e.getMessage(), false, "")
            );
        }
    }
}
