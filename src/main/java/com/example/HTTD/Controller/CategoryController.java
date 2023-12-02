package com.example.HTTD.Controller;

import com.example.HTTD.Entity.Category;
import com.example.HTTD.Service.CategoryService;
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
@RequestMapping("api/auth/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseObject> createCategory(@RequestBody Category category){
        try{
            Category savedCategory = categoryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Tạo danh mục thành công",true, savedCategory)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Có lỗi xảy ra khi tạo danh mục",false, "")
            );
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getCategoryById(@PathVariable("id") Long categoryId){
        try{
            Category category = categoryService.getCategoryById(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, category)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(0, "Mã danh mục không tồn tại",false, "")
            );
        }

    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllCategories(){
        try{
            List<Category> listcategory = categoryService.getAllCategory();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, listcategory)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Thất bại, có lỗi xảy ra",false, "")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateCategory(@PathVariable("id") Long categoryId, @RequestBody Category category){
        try{
            category.setId(categoryId);
            Category updatedCategory = categoryService.updateCategory(category);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Cập nhật thành công",true, updatedCategory)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Cập nhật thất bại",false, "")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteCategory(@PathVariable("id") Long categoryId){
        try {
            boolean isDeleted = categoryService.deleteCategory(categoryId);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(1, "Xóa danh mục thành công", true, "")
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(0, "Xóa thất bại, không tìm thấy danh mục có ID: " + categoryId, false, "")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject(0, "có lỗi xảy ra khi xóa danh mục: " + e.getMessage(), false, "")
            );
        }
    }
}
