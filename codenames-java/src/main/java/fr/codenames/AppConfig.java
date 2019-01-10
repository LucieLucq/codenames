package fr.codenames;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "fr.codenames.data.jpa" })
public class AppConfig {

}
