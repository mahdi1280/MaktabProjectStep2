package ir.maktab.maktabprojectstep2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ir", "com"})
public class MaktabProjectStep2Application {

    public static void main(String[] args) {
        SpringApplication.run(MaktabProjectStep2Application.class, args);
    }

}
