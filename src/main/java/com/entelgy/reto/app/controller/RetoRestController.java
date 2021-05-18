package com.entelgy.reto.app.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.entelgy.reto.app.domain.Reto;
import com.entelgy.reto.app.service.RetoService;

@RestController
@RequestMapping(value = "/api/reto")
public class RetoRestController {

	@Autowired
	RetoService retoService;
	
	@RequestMapping(value = "/reestructura", method = RequestMethod.POST)
	    public Reto reestructura() throws Exception {
		 Reto reto =new Reto();
	        try {
	        	reto = retoService.refactorizar();

	        } catch (Exception e) {
	            //En caso de alguna excepción, esta se mostrará en consola.
	        	System.out.println("Error -> "+ e.getMessage());

	        } finally {
	            //Finalmente se retorna el Objeto.
	            return reto;
	        }

	    }

}
