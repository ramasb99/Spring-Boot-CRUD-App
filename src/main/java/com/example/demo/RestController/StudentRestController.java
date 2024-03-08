package com.example.demo.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.StudentRepo;
import com.example.demo.model.Student;

@RestController
public class StudentRestController {

	@Autowired
	StudentRepo repo;
	
	@PostMapping("/add")
	public String saveStudent(@RequestBody Student s)
	{
		Student s1=repo.save(s);
		return s1!=null?"Student Added.....":"Something wrong.....";
	}
	@GetMapping("/view")
	public List<Student> getAll()
	{
		List<Student> l=repo.findAll();
		return l;
	}
	
	@GetMapping("/del/{sid}")
	public String delStud(@PathVariable("sid")Integer id)
	{
		List<Student> l=repo.findAll();
		boolean b=false;
		for(Student s:l)
		{
			if(s.getId()==id)
			{
				repo.deleteById(id);
				b=true;
			}
		}
		return b?"Deleted Student...":"Something worng....";
	}
	
	@GetMapping("/update/{sid}")
	public String delStud(@PathVariable("sid")Integer id, @RequestBody Student s)
	{
        Optional o=repo.findById(id);
		if(o.isPresent())
		{
			Student s1=(Student)o.get();
			repo.save(s);
			return "Updated Student..";
		}
		else
		{
			return "Something Wrong";
		}
	}
	
	@GetMapping("/search/{sname}")
	public String searchStudent(@PathVariable("sname")String name)
	{
		List<Student> l=repo.findAll();
		boolean b=false;
		for(Student s:l)
		{
			if(s.getName().equals(name))
			{
				b=true;
			}	
		}
	    return b?"Student Found...":"Something Wrong....";	
	}
}
