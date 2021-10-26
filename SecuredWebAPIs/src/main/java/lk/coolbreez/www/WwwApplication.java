package lk.coolbreez.www;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class WwwApplication {

    public static void main(String[] args) {
        SpringApplication.run(WwwApplication.class, args);
    }

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Colombo"));
        log.info("Application TimeZone - Asia/Colombo");
    }

}
