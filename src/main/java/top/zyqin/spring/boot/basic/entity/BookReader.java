package top.zyqin.spring.boot.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authoradmin
 * @date 2021/3/5 2:58
 * @description BookReader
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookReader {
    private String name;
    private Integer age;

}
