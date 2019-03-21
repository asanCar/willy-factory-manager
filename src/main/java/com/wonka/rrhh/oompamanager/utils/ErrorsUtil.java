package com.wonka.rrhh.oompamanager.utils;

import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static org.apache.logging.log4j.util.Strings.EMPTY;

public enum ErrorsUtil {

    INSTANCE;

    private static final String BINDING_ERROR_TEXT = "%d errors: ";

    private static final String FIELD_ERROR_TEXT = "Field error in object '%s' on field '%s': rejected value [%s]; (%2$s %s)";

    public String getErrorMessage(BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            final StringBuilder sb = new StringBuilder();
            final String errorMessage = String.format(BINDING_ERROR_TEXT, bindingResult.getErrorCount());
            sb.append(errorMessage);

            for (FieldError error : bindingResult.getFieldErrors()) {
                final String fieldErrorMessage = String.format(FIELD_ERROR_TEXT, error.getObjectName(), error.getField(),
                        ObjectUtils.nullSafeToString(error.getRejectedValue()), error.getDefaultMessage());
                sb.append(fieldErrorMessage).append(";");
            }

            return sb.toString();
        } else {
            return EMPTY;
        }
    }
}
