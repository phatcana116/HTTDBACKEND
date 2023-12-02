package com.example.HTTD.Controller;

import com.example.HTTD.Entity.Debt;


import com.example.HTTD.Service.DebtService;
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
@RequestMapping("api/auth/debt")

public class DebtController {
    @Autowired
    private DebtService debtService;

    @PostMapping
    public ResponseEntity<ResponseObject> created (@RequestBody Debt debt){
        try{
            Debt saved = debtService.createDebt(debt);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Tạo khoản nợ thành công",true, saved)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Có lỗi xảy ra khi tạo khoản nợ",false, "")
            );
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable("id") Long id){
        try{
            Debt debt = debtService.getDebtById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, debt)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(0, "Mã khoản nợ không tồn tại",false, "")
            );
        }

    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        try{
            List<Debt> list = debtService.getAllDebt();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, list)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Thất bại, có lỗi xảy ra",false, "")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updated(@PathVariable("id") Long id, @RequestBody Debt debt){
        try{
            debt.setId(id);
            Debt updated = debtService.updateDebt(debt);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Cập nhật thành công",true, updated)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(0, "Cập nhật thất bại",false, "")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleted (@PathVariable("id") Long id){
        try {
            boolean isDeleted = debtService.deleteDebt(id);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(1, "Xóa thành công", true, "")
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(0, "Xóa thất bại, không tìm thấy khoản nợ có ID: " + id, false, "")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject(0, "Lỗi khi xóa khoản nợ: " + e.getMessage(), false, "")
            );
        }
    }
}
