package org.property;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by sowmyaparameshwara on 3/29/17.
 */
@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean value) {
        return (value != null && value) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
        return "Y".equals(value);
    }
}