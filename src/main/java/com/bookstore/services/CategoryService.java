package com.bookstore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entities.Category;
import com.bookstore.exception.InternalServerException;
import com.bookstore.exception.NotFoundException;
import com.bookstore.repositories.ICategoryRepository;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	private ICategoryRepository iCategoryRepository;

	@Override
	public Category addCategory(Category category) throws InternalServerException, NotFoundException {
		if(category == null || category.getCategoryName().isEmpty() || category.getCategoryName() == null) {
			throw new InternalServerException("Catgeory is null");
		}
		else {
			return iCategoryRepository.saveAndFlush(category);
		}
	}

	@Override
	public Category editCategory(Category cat) throws NotFoundException {
		if(iCategoryRepository.existsById(cat.getCategoryId())/*cat == null || cat.getCategoryName().isEmpty() || cat.getCategoryName() == null*/) {
			throw new NotFoundException("Catgeory is null");
		}
		else {
			return iCategoryRepository.saveAndFlush(cat);
		}
	}

	@Override
	public List<Category> viewAllCategories() throws NotFoundException {
		List<Category> categoryList = new ArrayList<Category>();
		categoryList = iCategoryRepository.findAll();
		if(categoryList.isEmpty()) {
			throw new NotFoundException("Category list is empty.");
		}
		return categoryList;
	}

	@Override
	public Category removeCategory(Category cat) throws InternalServerException {
		if(!iCategoryRepository.existsById(cat.getCategoryId())) {
			throw new InternalServerException("Category does not exist.");
		}
		iCategoryRepository.delete(cat);
		return cat;
	}

}
