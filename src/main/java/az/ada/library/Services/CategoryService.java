package az.ada.library.Services;

import java.util.List;

import az.ada.library.Models.Category;

// 2.1. Library should contain information about categories of the books
public interface CategoryService {

    public List<Category> getAll();

    public Category create(String name);

}
