package io.github.naughtytom001.sesame.data;

import java.util.LinkedHashMap;

//@Data
public final class ModelFields extends LinkedHashMap<String, ModelField<?>> {

    //private BooleanModelField enable = new BooleanModelField("enable", "开启", true);

    public void addField(ModelField<?> modelField) {
        put(modelField.getCode(), modelField);
    }

}