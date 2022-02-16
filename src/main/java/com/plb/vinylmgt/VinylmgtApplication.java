package com.plb.vinylmgt;

import com.plb.vinylmgt.repository.AuthorRepository;
import com.plb.vinylmgt.repository.VinylRepository;
import com.plb.vinylmgt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
public class VinylmgtApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(VinylmgtApplication.class);
        ConfigurableEnvironment environment = new StandardEnvironment();
        environment.setDefaultProfiles("dev");
        application.setEnvironment(environment);
        application.run(args);
    }

    @Bean
    public CommandLineRunner createData(UserService userService,
                                        VinylRepository vinylRepository,
                                        AuthorRepository authorRepository) {
        return args -> {
//            User newUser = new User("toto@toto.com", "azerty",
//                    "toto", "titi","USER");
//            userService.save(newUser);
//
//            Author linkinPark = new Author("Linkin Park", LocalDate.of(1996, 1, 1));
//            authorRepository.save(linkinPark);
//
//            Vinyl inTheEnd = new Vinyl("In the end", LocalDate.of(2000, 10, 24), linkinPark, newUser);
//            Vinyl papercut = new Vinyl("Papercut", LocalDate.of(2000, 10, 24), linkinPark, newUser);
//            Vinyl oneStepCloser = new Vinyl("One step closer", LocalDate.of(2000, 10, 24), linkinPark, newUser);
//            Vinyl pointsOfAuthority = new Vinyl("Points of Authority", LocalDate.of(2000, 10, 24), linkinPark, newUser);
//
//            vinylRepository.save(inTheEnd);
//            vinylRepository.save(papercut);
//            vinylRepository.save(oneStepCloser);
//            vinylRepository.save(pointsOfAuthority);
//            vinylRepository.findAll().forEach(System.out::println);
//            authorRepository.findAll().forEach(System.out::println);
        };
    }
}
