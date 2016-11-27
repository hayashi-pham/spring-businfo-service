package com.lampnc.businfo;

import java.util.Locale;
import java.util.TimeZone;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// @SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class BusInfoApplication extends SpringBootServletInitializer {

	private static Class<BusInfoApplication> applicationClass = BusInfoApplication.class;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.getSessionFactory();
	}

	@Bean
	public LocaleResolver localeResolver() {
		// TimeZone zone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
		// TimeZone.setDefault(zone);
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("vi", "VN"));
		localeResolver.setDefaultTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		return localeResolver;
	}

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}

}
