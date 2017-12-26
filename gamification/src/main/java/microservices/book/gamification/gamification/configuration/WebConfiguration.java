package microservices.book.gamification.gamification.configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(final CorsRegistry registry){
        registry.addMapping("/**");
    }
}
