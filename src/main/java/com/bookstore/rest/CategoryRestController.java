package com.bookstore.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entities.Category;
import com.bookstore.exception.InternalServerException;
import com.bookstore.exception.NotFoundException;
import com.bookstore.services.ICategoryService;

@CrossOrigin
@RestController
@RequestMapping(value = "/category")
public class CategoryRestController {
	
	@Autowired
	private ICategoryService iCategoryService;
	
	// URL: http://localhost:8080/category/addCategory
	@PostMapping(value = "/addCategory", consumes = "application/json")
	public Category addCategory(@RequestBody Category category) throws InternalServerException, NotFoundException {
		return iCategoryService.addCategory(category);
	}
	
	// URL: http://localhost:8080/category/editCategory
	@PutMapping(value = "/editCategory", consumes = "application/json")
	public Category editCategory(@RequestBody Category category) throws NotFoundException, InternalServerException{
		return iCategoryService.editCategory(category);
	}
	
	// URL: http://localhost:8080/category/viewAllCategories
	@GetMapping(value = "/viewAllCategories", produces = "application/json")
	public List<Category> viewAllCategories() throws NotFoundException{
		return iCategoryService.viewAllCategories();
	}
	
	// URL: http://localhost:8080/category/deleteCategory
	@PostMapping(value = "/deleteCategory", consumes = "application/json")
	public Category deleteCategory(@RequestBody Category category) throws InternalServerException {
		return iCategoryService.removeCategory(category);
	}

}
