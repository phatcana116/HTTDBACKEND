package com.example.HTTD.Controller;

import com.example.HTTD.Dto.ExpenseDto;
import com.example.HTTD.Entity.Category;
import com.example.HTTD.Entity.Expense;
import com.example.HTTD.Entity.User;
import com.example.HTTD.Entity.Wallet;
import com.example.HTTD.Service.CategoryService;
import com.example.HTTD.Service.ExpenseService;
import com.example.HTTD.Service.UserService;
import com.example.HTTD.Service.WalletService;
import com.example.HTTD.reponse.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("api/auth/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping("/wallets")
    public ResponseEntity<List<Wallet>> getAllWallet() {
        List<Wallet> wallets = walletService.getAllWallet();
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/{month}/{year}")
    public ResponseEntity<ResponseObject> getExpensesByMonthAndYear(@PathVariable int month, @PathVariable int year) {
        List<Expense> expense =  expenseService.getExpensesByMonthAndYear(month, year);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(0, "thành công",true, expense)
        );

    }
    @GetMapping("/chart/{month}/{year}")
    public ResponseEntity<ResponseObject> getExpenseChartData(@PathVariable int month, @PathVariable int year) {
        List<ExpenseDto> expenseDto = expenseService.getExpenseChartData(month, year);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(0, "Tạo chi phí thành công",true, expenseDto)
        );
    }
    @PostMapping("/{id}")
    public ResponseEntity<ResponseObject> createExpense(@PathVariable("id") Long walletId, @RequestBody ExpenseDto expenseDto) {
        Wallet wallet = walletService.getWalletById(walletId);
        if (wallet.getAmount() >= expenseDto.getAmount()) {
            wallet.setAmount(wallet.getAmount() - expenseDto.getAmount());
            walletService.createWallet(wallet);
            // Tạo chi phí mới
            Expense expense = new Expense();
            expense.setName(expenseDto.getName());
            expense.setDescription(expenseDto.getDescription());
            expense.setAmount(expenseDto.getAmount());
            expense.setDate_created(expenseDto.getDate_created());
            Wallet wallets = walletService.getWalletById(walletId);
            expense.setWallet(wallets);
            Category category = categoryService.getById(expenseDto.getCategoryId());
            expense.setCategory(category);
//                        expense.setUsers(user);
            Expense savedExpense = expenseService.createExpense(expense);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(0, "Tạo chi phí thành công", true, savedExpense)
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(0, "Không có đủ tiền trong ví", true, "")
            );
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getExpenseById(@PathVariable("id") Long ExpenseId){
        try{
            Expense expense = expenseService.getExpenseById(ExpenseId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, expense)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Mã chi phí không tồn tại",false, "")
            );
        }

    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllExpenses(){
        try{
            List<Expense> listExpense = expenseService.getAllExpense();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, listExpense)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(0, "Thất bại, có lỗi xảy ra",false, "")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateExpense(@PathVariable("id") Long expenseId, @RequestBody ExpenseDto expenseDto){
        try{
            Expense expense = expenseService.getExpenseById(expenseId);
            if(expense != null)
            {
                expense.setName(expenseDto.getName());
                expense.setDescription(expenseDto.getDescription());
                expense.setAmount(expenseDto.getAmount());
                Long walletId = expenseDto.getWalletId();
                Category category = categoryService.getById(expenseDto.getCategoryId());
                expense.setCategory(category);
                if(walletId != null)
                {
                    Wallet wallet = walletService.getWalletById(walletId);
                    // Kiểm tra xem có đủ tiền trong ví không
                    Float newAmount = expenseDto.getAmount();
                    if (wallet.getAmount() + expense.getAmount() >= newAmount){
                        // Trả lại tiền vào ví cũ
                        wallet.setAmount(wallet.getAmount() + expense.getAmount());
                        // Trừ tiền từ ví mới
                        wallet.setAmount(wallet.getAmount() - newAmount);
                        walletService.createWallet(wallet);
                        expense.setWallet(wallet);
                        walletService.createWallet(wallet);
                    }
                }else {
                    Expense savedExpense = expenseService.createExpense(expense);
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject(1, "Cập nhật thành công",true, savedExpense)
                    );
                }
                return null;
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(0, "Không tìm thấy id",true, "")
                );
            }

        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(0, "Cập nhật thất bại",false, "")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteExpense(@PathVariable("id") Long ExpenseId){
        try {
            boolean isDeleted = expenseService.deleteExpense(ExpenseId);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(1, "Xóa thành công", true, "")
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(0, "Xóa thất bại, không tìm thấy chi phí có ID: " + ExpenseId, false, "")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject(0, "Lỗi khi xóa chi phí: " + e.getMessage(), false, "")
            );
        }
    }
}
