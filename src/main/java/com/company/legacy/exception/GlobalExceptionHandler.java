package com.company.legacy.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Global exception handler for REST APIs.
 *
 * Legacy Spring MVC exception handling approach.
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Handle resource not found errors.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException exception) {


        ErrorResponse response =
                new ErrorResponse();


        response.setTimestamp(
                new Date());


        response.setStatus(
                HttpStatus.NOT_FOUND.value());


        response.setError(
                "Resource Not Found");


        response.setMessage(
                exception.getMessage());


        return new ResponseEntity<ErrorResponse>(
                response,
                HttpStatus.NOT_FOUND);

    }




    /**
     * Handle invalid arguments.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            IllegalArgumentException exception) {


        ErrorResponse response =
                new ErrorResponse();


        response.setTimestamp(
                new Date());


        response.setStatus(
                HttpStatus.BAD_REQUEST.value());


        response.setError(
                "Bad Request");


        response.setMessage(
                exception.getMessage());


        return new ResponseEntity<ErrorResponse>(
                response,
                HttpStatus.BAD_REQUEST);

    }





    /**
     * Generic exception handler.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception exception) {


        ErrorResponse response =
                new ErrorResponse();


        response.setTimestamp(
                new Date());


        response.setStatus(
                HttpStatus.INTERNAL_SERVER_ERROR.value());


        response.setError(
                "Internal Server Error");


        response.setMessage(
                exception.getMessage());



        return new ResponseEntity<ErrorResponse>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR);

    }





    /**
     * Legacy error response object.
     *
     * Modern Java 17 version could replace this
     * with a record.
     */
    public static class ErrorResponse {


        private Date timestamp;

        private int status;

        private String error;

        private String message;



        public ErrorResponse() {

        }



        public Date getTimestamp() {
            return timestamp;
        }



        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }



        public int getStatus() {
            return status;
        }



        public void setStatus(int status) {
            this.status = status;
        }



        public String getError() {
            return error;
        }



        public void setError(String error) {
            this.error = error;
        }



        public String getMessage() {
            return message;
        }



        public void setMessage(String message) {
            this.message = message;
        }



        @Override
        public String toString() {


            // SRAO: Replaced StringBuffer with StringBuilder for better performance in a non-thread-safe context.
            StringBuilder buffer =
                    new StringBuilder();


            buffer.append("ErrorResponse [");


            buffer.append("timestamp=");
            buffer.append(timestamp);


            buffer.append(", status=");
            buffer.append(status);


            buffer.append(", error=");
            buffer.append(error);


            buffer.append(", message=");
            buffer.append(message);


            buffer.append("]");


            return buffer.toString();

        }


    }


}