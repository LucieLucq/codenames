package fr.codenames;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext myContext = new AnnotationConfigApplicationContext(AppConfig.class);
		myContext.getBeanFactory().createBean(AppListe.class).run(args);
		myContext.close();
	}

}
