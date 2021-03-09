package top.zyqin.spring.boot.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class SpringBootBasicApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootBasicApplication.class, args);

//        String string = "{\n" +
//                "  \"name\": \"aaa\",\n" +
//                "  \"age\": 21\n" +
//                "}";
    }

}
