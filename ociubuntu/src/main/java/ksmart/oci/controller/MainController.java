package ksmart.oci.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	
	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@GetMapping("/")
	public String main(Model model, HttpServletRequest request) {
		log.info(request.getServerName());
		return "main";
	}
}
