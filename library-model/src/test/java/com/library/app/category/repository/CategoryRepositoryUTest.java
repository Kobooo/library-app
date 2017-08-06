package com.library.app.category.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.library.app.category.model.Category;
import com.library.app.commontest.utils.DBCommandTransactionalExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.library.app.commontest.category.CategoryForTestRepository.java;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CategoryRepositoryUTest {
	private EntityManager em;
	private EntityManagerFactory emf;
	private CategoryRepository categoryRepository;
	private com.library.app.commontest.utils.DBCommandTransactionalExecutor dbCommandTransactionalExcecutor;

	@Before
	public void initTestCase() {
	    emf = Persistence.createEntityManagerFactory("libraryPU");
	    em = emf.createEntityManager();

		categoryRepository = new CategoryRepository();
	    categoryRepository.em = em;
		dbCommandTransactionalExcecutor = new DBCommandTransactionalExecutor(em);
	}

	@After
    public void closeEntityManager() {
	    em.close();
	    emf.close();
    }

    @Test
    public void addCategoryAndFindIt(){

		final Long categoryAddedId =  dbCommandTransactionalExcecutor.executeCommand(() -> {
			return categoryRepository.add(java()).getId();
		});

		assertThat(categoryAddedId, is(notNullValue()));
		Category category = categoryRepository.findById(categoryAddedId);
		assertThat(category, is(notNullValue()));
		assertThat(category.getName(), is(equalTo(java().getName())));
    }
}