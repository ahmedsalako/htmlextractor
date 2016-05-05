package com.ahmedsalako.htmlextractor.interfaces;

import com.ahmedsalako.htmlextractor.models.ProductResult;

/**
 * The interface used by the ProductServiceImpl implementation class for providing product related service to consumes.
 * Example of service provided is the compute Products method, which computes all the products retrieved from the repository
 * @author Ahmed
 *
 */
public interface ProductService {
	ProductResult computeProducts();
}
