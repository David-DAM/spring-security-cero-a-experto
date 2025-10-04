package com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.database.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ProtectDataSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String data, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String masked = data.replaceAll("(?<=.{1}).(?=[^@]*@)", "x");

        jsonGenerator.writeString(masked);
    }
}
