package top.zyqin.spring.boot.basic.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @authoradmin
 * @date 2021/3/9 20:56
 * @description Family
 */
@Data
@Component
public class Family {
    @Value("${family.family-name}")
    private String familyName;
    @Resource
    private Father father;
    @Resource
    private Mother mother;
    @Resource
    private Child  child;
}
