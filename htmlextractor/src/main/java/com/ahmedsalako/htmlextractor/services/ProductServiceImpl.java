package com.ahmedsalako.htmlextractor.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import com.ahmedsalako.htmlextractor.interfaces.ProductRepository;
import com.ahmedsalako.htmlextractor.interfaces.ProductService;
import com.ahmedsalako.htmlextractor.models.ProductInfo;
import com.ahmedsalako.htmlextractor.models.ProductResult;
import com.google.inject.Inject;

/**
 * The Product service is responsible for performing computation requirements on the retrieved products.
 * 
 * @author Ahmed
 *
 */
public class ProductServiceImpl implements ProductService
{
	/**
	 * ProductRepository
	 */
	private ProductRepository productRepository;
	
	@Inject
	public ProductServiceImpl(ProductRepository productRepository){
		this.productRepository = productRepository;
	}
	
	/**
	 * Computes the total of product items fetched from the product repository.
	 * The total unit price for each product are added.
	 */
	public ProductResult computeProducts(){
		ProductResult productResult = new ProductResult();
		List<ProductInfo> products = productRepository.getAll();
		
		BigDecimal total = products.stream().map
		   ( e->e.getUnitPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
			
		productResult.setResults(products);
		productResult.setTotal(total);
		
		return productResult;
	}
}
