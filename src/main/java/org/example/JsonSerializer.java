package org.example;

import org.json.JSONObject;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import java.lang.reflect.Field;
import java.util.Set;


public class JsonSerializer<T> {
    private final Set<Field> publishedFields;

    public JsonSerializer(Class<T> serializedClass) {
        final Reflections reflections = new Reflections(serializedClass, new FieldAnnotationsScanner());
        publishedFields = reflections.getFieldsAnnotatedWith(Published.class);
    }

    public JSONObject serialize(T o) {
        JSONObject result = new JSONObject();
        for (Field publishedField : publishedFields) {
            try {
                Field field = o.getClass().getDeclaredField(publishedField.getName());
                field.setAccessible(true);
                result.put(publishedField.getName(), field.get(o).toString());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                result.put(publishedField.getName(), e.toString());
            }
        }
        return result;
    }
}
