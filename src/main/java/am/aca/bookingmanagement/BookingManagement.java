package am.aca.bookingmanagement;

import am.aca.bookingmanagement.service.filter.FilterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beans/passwordEncoder.xml")
public class BookingManagement {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BookingManagement.class, args);
        run.getBean(FilterService.class).findAll();
    }

}