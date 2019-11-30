/**
 * @program: zleadf-parent
 * @description:自定义注解
 * @author: ytchen
 * @create: 2019-02-14 17:32
 **/
package com.zlead.util;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangH on 2019-02-14 17:33
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
}