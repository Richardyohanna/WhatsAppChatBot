package com.firebase;
 

import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class firebaseController {
	
	FirebaseService firebaseService;
	
	public firebaseController(FirebaseService firebaseService) {
		 
		this.firebaseService = firebaseService;
	}

	
	@PostMapping("/create")
	public String createCRUD(@RequestBody firebaseCrud crud ) throws InterruptedException, ExecutionException {
		return firebaseService.createCRUD(crud);
	}
	
	@GetMapping("/get")
	public firebaseCrud getCRUD(@RequestParam String documentID ) throws InterruptedException, ExecutionException {
		return firebaseService.getCRUD(documentID);
	}
	
	@PutMapping("/update")
	public String updateCRUD(@RequestBody firebaseCrud crud ) throws InterruptedException, ExecutionException {
		return firebaseService.updateCRUD(crud);
	}
	
	@PutMapping("/delete")
	public String deleteCRUD(@RequestParam String documentID ) throws InterruptedException, ExecutionException {
		return firebaseService.deleteCRUD(documentID);
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> testGetEndpoint(){
		return ResponseEntity.ok("Test get Endpoint is Working");
	}
	
	
	
	
	
	
	 
}
