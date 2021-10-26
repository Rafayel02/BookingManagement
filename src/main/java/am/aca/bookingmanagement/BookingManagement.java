package am.aca.bookingmanagement;

import org.springframework.context.annotation.ImportResource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@ImportResource("classpath:beans/passwordEncoder.xml")
public class BookingManagement {

    public static void main(String[] args) {
        SpringApplication.run(BookingManagement.class, args);
    }

}