package com.example.demo;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 

@Controller 
public class AuthController { 
   @GetMapping("/") public String home() {
	   return "index"; 
	   }
   @GetMapping("/login")
   public String Logn() {
	   return "login";
   }
}