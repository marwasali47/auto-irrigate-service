package com.irrigate;

import com.irrigate.config.DatabaseConfig;
import com.irrigate.config.FlywayConfig;
import com.irrigate.config.PersistenceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {FlywayAutoConfiguration.class, DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@Import({FlywayConfig.class, DatabaseConfig.class, PersistenceConfig.class})
@EnableScheduling
@EnableRetry
@EnableSwagger2
public class AutoIrrigateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoIrrigateServiceApplication.class, args);
	}

}
