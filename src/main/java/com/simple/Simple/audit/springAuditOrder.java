package com.simple.Simple.audit;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class springAuditOrder {

    private static final List<String> AUDIT_FIELDS = List.of(
            "createdAt", "modifiedAt", "createdBy", "modifiedBy"
    );

    private static class AuditFieldLastModifier extends BeanSerializerModifier {

        @Override
        public List<BeanPropertyWriter> changeProperties(
                SerializationConfig config,
                BeanDescription beanDesc,
                List<BeanPropertyWriter> beanProperties) {

            List<BeanPropertyWriter> normalFields = new ArrayList<>();
            List<BeanPropertyWriter> auditFields = new ArrayList<>();

            for (BeanPropertyWriter writer : beanProperties) {
                if (AUDIT_FIELDS.contains(writer.getName())) {
                    auditFields.add(writer);
                } else {
                    normalFields.add(writer);
                }
            }

            normalFields.addAll(auditFields);
            return normalFields;
        }
    }


    @Bean
    public Module auditFieldOrderModule() {
        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new AuditFieldLastModifier());
        return module;
    }
}
