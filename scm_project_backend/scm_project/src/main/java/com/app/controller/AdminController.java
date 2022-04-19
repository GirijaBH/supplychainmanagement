package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.customexceptionhandler.ControllerUtils;
import com.app.pojos.Employee;
import com.app.pojos.Order;
import com.app.pojos.Product;
import com.app.repository.IOrderRepository;
import com.app.service.IEmployeeService;
import com.app.service.IProductService;
import com.app.service.IUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/scm/api/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

	@Value("$(upload.path}")
	private String path;

	// product service as a dependency
	@Autowired
	private IProductService productService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IEmployeeService empService;
	
	@Autowired
	private IOrderRepository orderService;

	// get single user by id
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") long userId) {
		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
	}

	// get all users
	@GetMapping("/user/all")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	// delete user
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long userId) {
		userService.deleteUserById(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// To get all Products
	@GetMapping("/products")
	public ResponseEntity<?> findAllProdcuts() {

		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);

	}

	// To get Product by Product Id
	@GetMapping("/product/{pid}")
	public ResponseEntity<?> getProductBiId(MultipartFile image, @PathVariable @Valid long pid) {
		return new ResponseEntity<>(productService.getProductById(pid), HttpStatus.OK);
	}

	// To save or update the product
	@PostMapping("/product/add")
	public ResponseEntity<?> addProduct(@Valid Product product,
			BindingResult bindingResult,
			@RequestPart(name = "file", required = false) MultipartFile file) throws IOException {
		if (bindingResult.hasErrors()) {
			Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
			return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
		} else {
			saveFile(product, file);
			
			Product savedProduct = productService.save(product);
			return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
		}
		
	}

	// update and save product
	@PutMapping("/product/edit")
	public ResponseEntity<?> updateProduct(@Valid Product product, BindingResult bindingResult,
			@RequestPart(name = "file", required = false) MultipartFile file) throws IOException {
		if (bindingResult.hasErrors()) {
			Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
			return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
		} else {
			saveFile(product, file);

			productService.saveProductInfoById(product.getName(), product.getBrand(), product.getStock(),
					product.getDescription(), product.getPrice(), product.getCategory(), product.getFilename(), product.getId());
			return new ResponseEntity<>(HttpStatus.OK);
		}

	}

	// to delete product by id
	@DeleteMapping("/product/{pid}")
	public String deleteProduct(@PathVariable long id) {
		return productService.deleteProduct(id);
	}

	
	private void saveFile(Product product, @RequestParam("file") MultipartFile file) throws IOException {
		if (file == null)
			product.setFilename("empty.jpg");
		else {
			File uploadDir = new File(path);

			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			String uuidFile = UUID.randomUUID().toString();
			String resultFilename = uuidFile + "." + file.getOriginalFilename();
			file.transferTo(new File(path + "/" + resultFilename));
			product.setFilename(resultFilename);
		}

	}
	
	//get all orders
	public ResponseEntity<?> getAllOrders()
	{
		List<Order> orders = orderService.findAll();
		
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	
	@GetMapping("/emp/all")
	public ResponseEntity<?> getAllEmpDetails() {

		return new ResponseEntity<>(empService.getAllEmployees(), HttpStatus.OK);
	}

	@PostMapping("/emp/add")
	public Employee addEmpDetails(@RequestBody @Valid Employee e) // de-serial (un marshalling) + apply validation rules
	{
		return empService.addOrUpdateEmployeeDetails(e);
	}

	@DeleteMapping("/emp/{id}")
	public String deleteEmpDetails(@PathVariable long id) {
		return empService.deleteEmpDetails(id);
	}

	@PutMapping("/emp/edit")
	public Employee updateEmpDetails(@RequestBody @Valid Employee e) // de-serial (un marshalling)
	{
		// e : DETACHED POJO , containing updated state

		return empService.addOrUpdateEmployeeDetails(e);
	}
	
	
}
