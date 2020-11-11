package com.bookstore.services;

import java.util.List;

import com.bookstore.entities.Category;
import com.bookstore.exception.InternalServerException;
import com.bookstore.exception.NotFoundException;

public interface ICategoryService {

	public Category addCategory(Category category) throws InternalServerException, NotFoundException;

	public Category editCategory(Category cat) throws NotFoundException, InternalServerException;

	public List<Category> viewAllCategories() throws NotFoundException;

	public Category removeCategory(Category cat) throws InternalServerException;

}
