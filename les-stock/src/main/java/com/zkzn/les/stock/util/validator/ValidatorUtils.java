package com.zkzn.les.stock.util.validator;


import com.zkzn.les.stock.controller.CheckInventoryController;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * hibernate-validator校验工具类
 * <p>
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 * @author Hush.
 */
public class ValidatorUtils {

    private static final Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    /**
     * 使用@Valid的方式返回错误信息
     * 使用demo: {@link CheckInventoryController#saveCheckInventoryTask}
     * @param result:represents binding results
     * @return java.util.List<java.lang.String>
     * @author Hush.
     * @since 2021/12/16 13:50
     */
    public static List<String> getValidationMessByBindingResult(BindingResult result) {

        return result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
