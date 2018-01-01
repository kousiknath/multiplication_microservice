package microservices.book.apigateway.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(final CorsRegistry registry){
        registry.addMapping("/**");
    }
}
