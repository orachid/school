package fr.wati.school.web.rebirth.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import fr.wati.school.web.rebirth.utils.CustomObjectMapper;
import fr.wati.school.web.rebirth.utils.ImprovedMustacheTemplateLoader;

@Configuration
@ComponentScan(basePackages = {"fr.wati.school","fr.wati.school.web.elfinder"})
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
    private ImprovedMustacheTemplateLoader improvedMustacheTemplateLoader;
	
	@Bean
    public ViewResolver getViewResolver(ResourceLoader resourceLoader) {
        
		InternalResourceViewResolver  internalResourceViewResolver=new InternalResourceViewResolver();
		internalResourceViewResolver.setViewClass(JstlView.class);
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		
//		MustacheViewResolver mustacheViewResolver = new MustacheViewResolver();
//        mustacheViewResolver.setPrefix("/WEB-INF/views/");
//        mustacheViewResolver.setSuffix(".mustache");
//        mustacheViewResolver.setCache(false);
//        mustacheViewResolver.setContentType("text/html;charset=utf-8");
//
//        MustacheTemplateLoader mustacheTemplateLoader = new MustacheTemplateLoader();
//        mustacheTemplateLoader.setResourceLoader(resourceLoader);
//
//        improvedMustacheTemplateLoader.setResourceLoader(resourceLoader);
//        
//        mustacheViewResolver.setTemplateLoader(improvedMustacheTemplateLoader);
        return internalResourceViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*/resources/**").addResourceLocations("/resources/");
    }

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJacksonHttpMessageConverter();
		mappingJacksonHttpMessageConverter.setObjectMapper(new CustomObjectMapper());
		List<MediaType> mediaTypes=new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_JSON);
		mappingJacksonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
		converters.add(mappingJacksonHttpMessageConverter);
	}

    
}
