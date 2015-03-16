package com.suwadi.web.controller.accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.suwadi.domain.Product;
import com.suwadi.domain.Status;
import com.suwadi.service.ProductService;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.ProductValidator;

@Controller
@SessionAttributes("product")
@RequestMapping(value = "/accounting/products")
public class ProductsController {
	private ProductService productService;
	private ProductValidator productValidator;
	
	@Autowired
	public void setProductService(ProductService productServices,
			ProductValidator productValidator) {
		this.productService = productServices;
		this.productValidator = productValidator;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		model.addAttribute("pager", this.productService.findAllActiveProducts(pager));
		return "/accounting/products/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);

		return "/accounting/products/add";
	}
	
	@RequestMapping(value = "processAdd", method = RequestMethod.POST)
	public String processAdd(@ModelAttribute("product") Product product,
			BindingResult result, SessionStatus status, Model model) {
		this.productValidator.validate(product, result);
		if (result.hasErrors()) {
			model.addAttribute("product", product);

			return "accounting/products/add";
		} else {
			productService.save(product);
			status.setComplete();
			
			//flash.info("district.added.success");
			return "redirect:/accounting/products/list";
		}
	}
	
	@RequestMapping(value = "/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@ModelAttribute("product") Product product,
			BindingResult result, SessionStatus status, Model model) {
		this.productValidator.validate(product, result);
		if (result.hasErrors()) {
			model.addAttribute("product", product);
			return "accounting/products/edit";
		} else {
			productService.update(product);
			status.setComplete();
			
			//flash.info("district.added.success");
			return "redirect:/accounting/products/list";
		}
	}
	
	@RequestMapping(value = "/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("product", this.productService.findById(id));
		return "accounting/products/show";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		Product product = this.productService.findById(id);
		model.addAttribute("product", product);

		return "accounting/products/edit";
	}
	
	@RequestMapping(value = "/{id}/deactivate", method = RequestMethod.GET)
	public String deActivate(@PathVariable Long id,SessionStatus status) {
		Product product = this.productService.findById(id);
		product.setStatus(Status.INACTIVE);
		this.productService.update(product);
		status.setComplete();
		return "redirect:/accounting/products/list";
	}
	
}