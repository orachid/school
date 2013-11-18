package fr.wati.school.web.rebirth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.mustache.MustacheTemplateLoader;
import org.springframework.web.servlet.view.mustache.MustacheViewResolver;

import fr.wati.school.web.rebirth.utils.ImprovedMustacheTemplateLoader;

@Configuration
@ComponentScan(basePackages = "fr.wati.school.web")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
    private ImprovedMustacheTemplateLoader improvedMustacheTemplateLoader;
	
	@Bean
    public ViewResolver getViewResolver(ResourceLoader resourceLoader) {
        MustacheViewResolver mustacheViewResolver = new MustacheViewResolver();
        mustacheViewResolver.setPrefix("/WEB-INF/views/");
        mustacheViewResolver.setSuffix(".mustache");
        mustacheViewResolver.setCache(false);
        mustacheViewResolver.setContentType("text/html;charset=utf-8");

        MustacheTemplateLoader mustacheTemplateLoader = new MustacheTemplateLoader();
        mustacheTemplateLoader.setResourceLoader(resourceLoader);

        improvedMustacheTemplateLoader.setResourceLoader(resourceLoader);
        
        mustacheViewResolver.setTemplateLoader(improvedMustacheTemplateLoader);
        return mustacheViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*/resources/**").addResourceLocations("/resources/");
    }

}
