
package com.java4.camping.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.java4.camping.product.domain.Product;
import com.java4.camping.product.service.ProductService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String listProducts(Model model) {

		model.addAttribute("products", productService.getAll());
		return "/payment/product";
	}

	@RequestMapping(value = "/availableProduct", method = RequestMethod.GET)
	public String listAvailableProducts(Model model) {
		model.addAttribute("products", productService.getAvailableProducts());
		return "payment/reserve";
	}

	@RequestMapping(value = "/deepReserve/{id}", method = RequestMethod.GET)
	public String deepReserve(Model model,
			@RequestParam(value = "id", required = false, defaultValue = "1") int productId) {
		Product selectedProduct = productService.getProductById(productId);
		List<Product> availableProducts = productService.getAvailableProducts();

		model.addAttribute("selectedProduct", selectedProduct);
		model.addAttribute("availableProducts", availableProducts);

		return "/payment/deepReserve";
	}
}
