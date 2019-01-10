package fr.codenames;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext myContext = new AnnotationConfigApplicationContext(AppConfig.class);
		myContext.getBeanFactory().createBean(AppPrincipale.class).run(args);
		myContext.close();
	}

}
