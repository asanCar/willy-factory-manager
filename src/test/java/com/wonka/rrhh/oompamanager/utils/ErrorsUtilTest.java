package com.wonka.rrhh.oompamanager.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ErrorsUtilTest {

    private static final String RESOURCE_NAME = "Resource";

    private static final String FIELD_NAME = "Field";

    private static final String REJECTED_VALUE = "Rejected";

    private static final String DEFAULT_MESSAGE = "Default";

    private ErrorsUtil sut = ErrorsUtil.INSTANCE;

    @Mock
    private BindingResult result;

    @Mock
    private FieldError error;

    @Test
    public void errorMessageShouldBeReturned() {
        //given
        final List<FieldError> errorList = new ArrayList<>();
        errorList.add(error);

        when(result.hasErrors()).thenReturn(true);
        when(result.getErrorCount()).thenReturn(1);
        when(result.getFieldErrors()).thenReturn(errorList);
        when(error.getField()).thenReturn(FIELD_NAME);
        when(error.getRejectedValue()).thenReturn(REJECTED_VALUE);
        when(error.getObjectName()).thenReturn(RESOURCE_NAME);
        when(error.getDefaultMessage()).thenReturn(DEFAULT_MESSAGE);

        //when
        final String outcome = sut.getErrorMessage(result);

        //then
        assertThat(outcome).isEqualTo("1 errors: Field error in object '"
                + RESOURCE_NAME + "' on field '"
                + FIELD_NAME + "': rejected value ["
                + REJECTED_VALUE + "]; ("
                + FIELD_NAME + " "
                + DEFAULT_MESSAGE + ");");
        verify(result).hasErrors();
        verify(result).getErrorCount();
        verify(result).getFieldErrors();
        verify(error).getField();
        verify(error).getRejectedValue();
        verify(error).getObjectName();
        verify(error).getDefaultMessage();
    }

    @Test
    public void emptyMessageShouldBeReturned() {
        //given
        when(result.hasErrors()).thenReturn(false);

        //when
        final String outcome = sut.getErrorMessage(result);

        //then
        assertThat(outcome).isEmpty();
        verify(result).hasErrors();
        verify(result, never()).getErrorCount();
        verify(result, never()).getFieldErrors();
        verify(error, never()).getField();
        verify(error, never()).getRejectedValue();
        verify(error, never()).getObjectName();
        verify(error, never()).getDefaultMessage();
    }
}