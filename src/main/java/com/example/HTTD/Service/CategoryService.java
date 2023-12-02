package com.example.HTTD.Service;

import com.example.HTTD.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);

    Category getCategoryById(Long categoryId);

    List<Category> getAllCategory();

    Category updateCategory(Category category);

    boolean deleteCategory(Long categoryId);

    Category getById(Long id);
}
