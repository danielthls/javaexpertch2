package com.devsuperior.dscatalog.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class ProductServiceIT {
	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductRepository repository;
	
	private Long existingId;
	private Long nonExistingId;
	private Long countTotalProducts;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
	}
	
	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {
		
		service.delete(existingId);
		
		Assertions.assertEquals(countTotalProducts - 1, repository.count());
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundWhenIdNotExists() {
		
		//service.delete(nonExistingId);
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}
	
	@Test
	public void findAllPagedShouldReturnPageWhenPage0Size10() {
		
		PageRequest pageRequest = PageRequest.of(0,10);

		Page<ProductDTO> result = service.findAllPaged(pageRequest);
		
		Assertions.assertFalse(result.isEmpty()); //Testa se a página está vazia
		Assertions.assertEquals(0, result.getNumber()); //Testa se a página retornada é 0
		Assertions.assertEquals(10, result.getSize()); //Testa o número de elementos da página
		Assertions.assertEquals(25, result.getTotalElements()); //Testa o número total de elementos
	}
	
	@Test
	public void findAllPagedShouldReturnEmptyPageWhenPageDoesNotExist() {
		
		PageRequest pageRequest = PageRequest.of(50,10);

		Page<ProductDTO> result = service.findAllPaged(pageRequest);
		
		Assertions.assertTrue(result.isEmpty()); //Testa se a página está vazia

	}
	
	@Test
	public void findAllPagedShouldReturnSortedPageWhenSortedByName() {
		
		PageRequest pageRequest = PageRequest.of(0,10, Sort.by("name"));

		Page<ProductDTO> result = service.findAllPaged(pageRequest);
		
		Assertions.assertFalse(result.isEmpty()); //Testa se a página está vazia
		Assertions.assertEquals("Macbook Pro",result.getContent().get(0).getName());
		Assertions.assertEquals("PC Gamer",result.getContent().get(1).getName());
		Assertions.assertEquals("PC Gamer Alfa",result.getContent().get(2).getName());
	}
	
}
