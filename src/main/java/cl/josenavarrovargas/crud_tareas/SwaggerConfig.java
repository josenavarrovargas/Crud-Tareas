package cl.josenavarrovargas.crud_tareas;

import java.util.Collections;

import com.google.common.base.Predicates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan(basePackages = {"cl.josenavarrovargas.crud_tareas.controllers"})
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket apiSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
        .groupName("crud_tareas")
            .select()
            .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework")))
            .build()
            .apiInfo(getApiInfo())
            .enableUrlTemplating(false)
            .useDefaultResponseMessages(false);
    }

    private ApiInfo getApiInfo() {
		return new ApiInfo(
				"API Registro de Tareas",
				"API permite el registro de tareas en la BD. ",
				"1.0",
				"http://codmind.com/terms",
				new Contact("José Navarro Vargas", "https://twitter.com/josenavarrovar", "josemanuelnavarro@outlook.cl"),
				"MIT LICENSE",
				"https://opensource.org/licenses/MIT",
				Collections.emptyList()
				);
	}
}
