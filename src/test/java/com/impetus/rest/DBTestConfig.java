package com.impetus.rest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.impetus.rest.common.CommonsConfig;
import com.impetus.rest.domain.JpaConfig;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
		CommonsConfig.class,
		JpaConfig.class
})
public class DBTestConfig {

}
