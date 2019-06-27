package com.javatechie.spring.mongo.api.resource;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.mongo.api.model.Staff;
import com.javatechie.spring.mongo.api.repository.StaffRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
public class StaffController {
	
	@Autowired
	private StaffRepository repository;
	
      @PostMapping("/addStaff")
      @ApiOperation(value = "Stores the details of the staff in database")
	  public String saveStaff(@Valid @RequestBody Staff staff){
   	  Optional<Staff> s = repository.findById(staff.getId());
  	  if(s.isPresent()) {
  		  return "Id already exists";
  	  }
  	  { Optional<Staff> s1 =repository.findByEmail(staff.getEmail());
    	 if(s1.isPresent()) {
    		  return "This Email Already Exists";
    	 }
		repository.save(staff);
		return "Added Staff with id : "+staff.getId();
  	  }
  	  	
    }
	@GetMapping("/findAllStaff")
	@ApiOperation(value = "Searches all the details of the staff present in database")
	public java.util.List<Staff> getStaff(){
		return repository.findAll();
	}
	@GetMapping("findAllStaff/{id}")
	@ApiOperation(value = "Searches the single details of the staff")
	public Optional<Staff>getStaff(@PathVariable int id){
		return repository.findById(id);
	}
   //@GetMapping("findAllStaff/{Email}")
	//  public 	java.util.List<Staff>getStaff(@PathVariable String Email){
	//	return repository.findByEmail(Email);
	//}
	
   @DeleteMapping("/delete/{id}")
   @ApiOperation(value = "Delete a particular staff details from database")
	public String deleteStaff(@PathVariable int id){
		repository.deleteById(id);
		return "Staff deleted with id :"+id;
	}
}
	
