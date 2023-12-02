package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Entity.Category;
import com.example.HTTD.Entity.Expense;
import com.example.HTTD.Repository.CategoryRepository;
import com.example.HTTD.Service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;

    @Override
    public Category getById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        return optional.orElse(null);
    }
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        Optional<Category> optional = categoryRepository.findById(categoryId);
        return optional.orElse(null);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Category category) {
        Category existingcategory = categoryRepository.findById(category.getId()).get();
        existingcategory.setName(category.getName());
        Category updatedcategory = categoryRepository.save(existingcategory);
        return updatedcategory;
    }

    @Override
    public boolean deleteCategory(Long categoryId) {
        Optional<Category> Optional = categoryRepository.findById(categoryId);
        if (Optional.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return true;
        }
        return false;
    }

}
