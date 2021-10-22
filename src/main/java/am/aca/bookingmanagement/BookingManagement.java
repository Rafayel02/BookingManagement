package am.aca.bookingmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beans/passwordEncoder.xml")
public class BookingManagement {

    public static void main(String[] args) {
        SpringApplication.run(BookingManagement.class, args);
    }
}