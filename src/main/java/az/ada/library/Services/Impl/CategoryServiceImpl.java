package az.ada.library.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.ada.library.Models.Category;
import az.ada.library.Repositories.CategoryRepository;
import az.ada.library.Services.CategoryService;

// 2.1. Library should contain information about categories of the books
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepo;

    public List<Category> getAll() {
        return categoryRepo.findAll();
    }

    public Category create(String name) {
        return categoryRepo.save(new Category(name));
    }

}
