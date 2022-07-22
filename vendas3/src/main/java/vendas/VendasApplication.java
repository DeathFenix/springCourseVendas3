package vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import vendas.entity.Cliente;
import vendas.repository.Clientes;


@SpringBootApplication
//public class VendasApplication extends SpringBootServletInitializer {  <-- aplicação virar web e nao stand alone (WAR)
public class VendasApplication {

// #usado na aula 37#
//	@Bean
//	public CommandLineRunner CommandLineRunner(@Autowired Clientes clientes) {
//		return args -> {
//			Cliente c =  new Cliente(null, "Fulano");
//			clientes.save(c);
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
