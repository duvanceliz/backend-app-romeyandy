package com.appRomeAndy.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appRomeAndy.Model.Coment;
import com.appRomeAndy.Model.Reply;
import com.appRomeAndy.Model.User;
import com.appRomeAndy.Repository.ReplyRepository;
import com.appRomeAndy.Repository.UserRepository;
import com.appRomeAndy.Repository.comentRepository;

//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("app")
public class Controller {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	comentRepository comentRepo;
	
	@Autowired
	ReplyRepository replyRepo;
	
	@PostMapping("/user")
	public void users(@RequestBody User user) {
		userRepo.save(user);
	}
	
	
	@GetMapping("/user")
	public List<User> userlist(){
	
		return userRepo.findAll();
		
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
	    userRepo.deleteById(id);
	}
	
	@PutMapping("/user")
	public User replaceUser(@RequestBody User newUser) {
		
		 return userRepo.findById(newUser.getId()).map(user ->{
			 user.setNombre(newUser.getNombre());
			 user.setApellido(newUser.getApellido());
			 user.setEmail(newUser.getEmail());
			 return userRepo.save(user);
		 }).get();
		 	 
	}
	@GetMapping("/user/{id}")
	public User finById(@PathVariable int id){
	
		return userRepo.findById(id).get();
		
	}
	
	@PostMapping("/coment")
	public void comentSave(@RequestBody Coment coment) {
		
		//coment.setFecha(new Date());
		
		comentRepo.save(coment);
		
	}
	
	@GetMapping("/coment")
	public List<Coment> comentList(){
		
		return comentRepo.findAll();
	}
	
	@PutMapping("/coment/like")
	public Coment saveLike(@RequestBody Coment coment) {

	 return comentRepo.findById(coment.getId()).map(c -> {
			c.setNombre(coment.getNombre());
			c.setComentario(coment.getComentario());
			c.setFecha(coment.getFecha());
			c.setMeGustas(coment.getMeGustas());
			return comentRepo.save(c);
		}).get();
		
	}
	
	@PostMapping("/coment/reply/{id}")
	public void reply(@RequestBody Reply reply, @PathVariable int id) {
		
		Set<Reply> r = new HashSet<Reply>();
		Coment coment = new Coment();
		Reply re = new Reply();	
		
		
        re = replyRepo.save(reply);
		coment = comentRepo.findById(id).get();
		r = coment.getReply();
		r.add(re);
		coment.setReply(r);
		comentRepo.save(coment);
	}
	   
	   

}
