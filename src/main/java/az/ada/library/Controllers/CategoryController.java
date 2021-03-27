package az.ada.library.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import az.ada.library.Controllers.dtos.CategoryCreateRequest;
import az.ada.library.Models.Category;
import az.ada.library.Services.CategoryService;

@RestController
public class CategoryController {
	@Autowired
    CategoryService categoryService;

    @GetMapping({ "/categories" })
	public List<Category> getAll() {
        List<Category> categories = categoryService.getAll();
        return categories;
    }

    @PostMapping({ "/categories" })
	public Category create(@RequestBody CategoryCreateRequest request) {
        return categoryService.create(request.getName());
    }
    
}
