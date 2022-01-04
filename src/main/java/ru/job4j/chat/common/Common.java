package ru.job4j.chat.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Common {
    public static <T> void patch(T entity, T currentEntity)
            throws IllegalAccessException, InvocationTargetException {
        if (currentEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        var methods = currentEntity.getClass().getDeclaredMethods();
        var namePerMethod = new HashMap<String, Method>();
        for (var method : methods) {
            var name = method.getName();
            if (name.startsWith("get") || name.startsWith("set")) {
                namePerMethod.put(name, method);
            }
        }
        for (var name : namePerMethod.keySet()) {
            if (name.startsWith("get")) {
                var getMethod = namePerMethod.get(name);
                var setMethod = namePerMethod.get(name.replace("get", "set"));
                if (setMethod == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Invalid properties mapping");
                }
                var newValue = getMethod.invoke(entity);
                if (newValue != null) {
                    setMethod.invoke(currentEntity, newValue);
                }
            }
        }
    }
}
