package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;

@SpringBootApplication
public class JsonwebtokenValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonwebtokenValidationApplication.class, args);
	}

}
