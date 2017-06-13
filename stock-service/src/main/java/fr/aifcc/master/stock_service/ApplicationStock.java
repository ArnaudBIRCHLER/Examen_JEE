package fr.aifcc.master.stock_service;

import org.springframework.boot.builder.*;
import org.springframework.boot.web.support.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;  
/**
 * @author 
 */
@SpringBootApplication
@ImportResource( "classpath:spring-config.xml" ) 
public class ApplicationStock extends SpringBootServletInitializer
{
	

    @Override
    protected SpringApplicationBuilder configure ( SpringApplicationBuilder application )
    {
        return application.sources( ApplicationStock.class );
    }

}