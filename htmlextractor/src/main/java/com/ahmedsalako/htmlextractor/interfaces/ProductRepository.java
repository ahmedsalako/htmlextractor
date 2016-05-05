package com.ahmedsalako.htmlextractor.interfaces;

import java.util.List;

import com.ahmedsalako.htmlextractor.models.ProductInfo;

/**
 * Represents the interface which is implemented by the product repository implementation class
 * @author Ahmed
 *
 */
public interface ProductRepository {
	List<ProductInfo> getAll();
}
