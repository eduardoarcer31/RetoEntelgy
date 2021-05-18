package com.entelgy.reto.app.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.entelgy.reto.app.domain.Reto;


@SpringBootTest
@AutoConfigureMockMvc
class RetoRestControllerTest {

	@Autowired
	RetoRestController controller;

	@Test
	void testReestructurar() throws Exception {
		Reto resp = controller.reestructura();
		assertTrue(resp.getData()!=null);
	}
}
