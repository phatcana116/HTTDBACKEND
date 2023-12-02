package com.example.HTTD.Controller;

import com.example.HTTD.Entity.Wallet;
import com.example.HTTD.Service.WalletService;
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
@RequestMapping("api/auth/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping
    public ResponseEntity<ResponseObject> createWallet(@RequestBody Wallet wallet){
        try{
            Wallet savedWallet = walletService.createWallet(wallet);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Tạo Ví thành công",true, savedWallet)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Có lỗi xảy ra khi tạo Ví",false, "")
            );
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getWalletById(@PathVariable("id") Long walletId){
        try{
            Wallet wallet = walletService.getWalletById(walletId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, wallet)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Mã ví không tồn tại",false, "")
            );
        }

    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllWallets(){
        try{
            List<Wallet> listwallet = walletService.getAllWallet();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, listwallet)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Thất bại, có lỗi xảy ra",false, "")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateWallet(@PathVariable("id") Long walletId, @RequestBody Wallet wallet){
        try{
            wallet.setId(walletId);
            Wallet updatedWallet = walletService.updateWallet(wallet);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Cập nhật thành công",true, updatedWallet)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Cập nhật thất bại",false, "")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteWallet(@PathVariable("id") Long walletId){
        try {
            boolean isDeleted = walletService.deleteWallet(walletId);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(1, "Xóa thành công", true, "")
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(0, "Xóa thất bại, không tìm thấy ví có ID: " + walletId, false, "")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject(0, "Lỗi khi xóa ví: " + e.getMessage(), false, "")
            );
        }
    }
}
