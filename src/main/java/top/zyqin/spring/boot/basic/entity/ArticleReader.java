package top.zyqin.spring.boot.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authoradmin
 * @date 2021/3/6 20:11
 * @description ArticleReader
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleReader {
    private String name;
    private Integer age;
}
