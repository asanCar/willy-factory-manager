package com.wonka.rrhh.oompamanager.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorMessage {

    private Date timestamp;

    private String status;

    private String message;

    private String details;

    private String requestInfo;

    ErrorMessage(Date timestamp, HttpStatus status, String details, String requestInfo) {

        this.timestamp = timestamp;
        this.status = String.valueOf(status.value());
        this.message = status.getReasonPhrase();
        this.details = details;
        this.requestInfo = requestInfo;
    }

    public Date getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(Date timestamp) {

        this.timestamp = timestamp;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public String getDetails() {

        return details;
    }

    public void setDetails(String details) {

        this.details = details;
    }

    public String getRequestInfo() {

        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {

        this.requestInfo = requestInfo;
    }
}
