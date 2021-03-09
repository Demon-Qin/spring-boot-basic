package top.zyqin.spring.boot.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authoradmin
 * @date 2021/3/9 20:55
 * @description Friend
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    private  String hobby;
    private String gender;
}
