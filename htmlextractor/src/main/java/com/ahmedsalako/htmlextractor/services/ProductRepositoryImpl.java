package com.ahmedsalako.htmlextractor.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ahmedsalako.htmlextractor.interfaces.ProductRepository;
import com.ahmedsalako.htmlextractor.interfaces.ResourceConnection;
import com.ahmedsalako.htmlextractor.models.HTMLFragment;
import com.ahmedsalako.htmlextractor.models.ProductDetail;
import com.ahmedsalako.htmlextractor.models.ProductInfo;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * This class is based on the repository pattern, it consumes the ResourceConnection and open a reader on it for HTML object traversal.
 * It is the entry point responsible for fetching the products, it translates the HTMLFragments into project info objects.
 * A future implementation may allow the addition of create, findBy etc.
 * 
 * @author Ahmed
 *
 */
public class ProductRepositoryImpl implements ProductRepository
{
	private HTMLConnectionFactory connectionFactory;
	private String productUrl;
	
	@Inject
	public ProductRepositoryImpl(HTMLConnectionFactory connectionFactory, @Named("products-url") String productUrl){
		this.connectionFactory = connectionFactory;
		this.productUrl = productUrl;
	}
	
	/**
	 *  Retrieves all the products by opening a connection on the HTML resource, using the reader to read through and then translating into the ProductInfo POJO entities.
	 */
	public List<ProductInfo> getAll(){
		
		List<ProductInfo> productInfos = new ArrayList<ProductInfo>();
		
		try(ResourceConnection connection = connectionFactory.createConnection(productUrl)){
			
			HTMLFragmentReader	reader = connection.openReader();
			
			for(HTMLFragment fragment : reader.many(".product")){
				ProductInfo productInfo = new ProductInfo();
								
				HTMLFragment productTitle = reader.single(fragment.id(), ".productInfo > h3 a");
				HTMLFragment priceUnit = reader.single(fragment.id(), ".pricing > p.pricePerUnit");			
				ProductDetail productDetail = getProductDetail(productTitle.getAttribute("href"));
				
				productInfo.setDescription(productDetail.getDescription());
				productInfo.setUnitPrice(new BigDecimal(priceUnit.text()));
				productInfo.setSize(productDetail.getSize());
				productInfo.setTitle(productTitle.text());
								
				productInfos.add(productInfo);
			}		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return productInfos;
	}
	
	/**
	 * Responsible for making additional connection to the product detail page to fetch product detail information.
	 * For each product this method is called to retrieve the product detail.
	 * It also retrieve the size of the product detail page.
	 * @param url
	 * @return
	 */
	public ProductDetail getProductDetail(String url){
		
		ProductDetail productDetail = new ProductDetail();
		StringBuilder description = new StringBuilder();
		
		try(ResourceConnection connection = connectionFactory.createConnection(url)){
			
			HTMLFragmentReader reader = connection.openReader();
			
			HTMLFragment productDescription = reader.single("productcontent > htmlcontent > div.productText");
			
			for(HTMLFragment fragment : reader.many(productDescription.id(), "p")){
				description.append(fragment.text());
			}
			
			productDetail.setDescription(description.toString());
			productDetail.setSize(reader.getSize());
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return productDetail;
	}
}
