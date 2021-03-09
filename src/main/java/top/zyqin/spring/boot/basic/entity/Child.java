package top.zyqin.spring.boot.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @authoradmin
 * @date 2021/3/9 20:56
 * @description Child
 */
@Data
@Component
@ConfigurationProperties(prefix = "family.child")
public class Child {
    private String name;
    private Integer age;
    private List<Map<String,Object>> friends;


}
