package com.ecommerce.repository;

import java.util.List;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductOrder;
import com.ecommerce.model.ShoppingCart;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer>{
	
	//@Query("select pO from Product_Order pO.ProductOrder.cartId = ?1 ")
	//List<ProductOrder> findOrder_By_Cart(int cart_Id) ;
	
	
	

List<ProductOrder> findByobjShoppingCart(ShoppingCart objShoppingCart);
	

//@Query("select sum(qtyOrdered) from productOrder group by product.barcode")
//@Query("select new com.ecommerce.model.ProductOrder(pO.cart_id as shoppingCArtId, "
//		+" pO.barcode as barcode, "
	//	+ "sum(pO.qtyOrdered) as quantityOrdered, "
	//	+ "from ProductOrder pO "
	//	+ "group by pO.barcode")
//public List<ProductOrder> groupByobjProduct(int barcode);

@Query("select productOrder from ProductOrder productOrder where productOrder.orderId = :orderId ")
List<ProductOrder> getProductOrderByOrderId(@Param("orderId")int orderId) ;
}
