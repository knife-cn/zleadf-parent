package com.zlead.utils;

import com.puqian.util.holder.BeanValidatorHolder;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by syn on 2016-12-26.
 */
public class ValidateUtil {

    public static Map<String, String> validate(Object obj) {
        Set<ConstraintViolation<Object>> set = BeanValidatorHolder.validate(obj);
        if (!set.isEmpty()) {
            Map<String, String> errorMap = new HashMap<String, String>();
            Iterator<ConstraintViolation<Object>> it = set.iterator();
            while (it.hasNext()) {
                ConstraintViolation<Object> error = it.next();
                errorMap.put(error.getPropertyPath().toString(), error.getMessage());
            }
            return errorMap;
        }
        return null;
    }
}
