package top.zyqin.spring.boot.basic.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @authoradmin
 * @date 2021/3/9 11:14
 * @description FileSize
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FileSize {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxSize;
}
