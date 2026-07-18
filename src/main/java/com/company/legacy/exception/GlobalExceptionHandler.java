package com.company.legacy.exception;

import java.time.Instant; // SRAO: Replaced java.util.Date with java.time.Instant for modern date handling.

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
                Instant.now()); // SRAO: Replaced new Date() with Instant.now().


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
                Instant.now()); // SRAO: Replaced new Date() with Instant.now().


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
                Instant.now()); // SRAO: Replaced new Date() with Instant.now().


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


        private Instant timestamp; // SRAO: Changed type from Date to Instant.

        private int status;

        private String error;

        private String message;



        public ErrorResponse() {

        }



        public Instant getTimestamp() { // SRAO: Changed return type from Date to Instant.
            return timestamp;
        }



        public void setTimestamp(Instant timestamp) { // SRAO: Changed parameter type from Date to Instant.
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


            StringBuilder buffer = // SRAO: Replaced StringBuffer with StringBuilder for performance.
                    new StringBuilder();


            buffer.append("ErrorResponse [");


            buffer.append("timestamp=");
            buffer.append(timestamp); // SRAO: Instant's toString() is suitable for direct appending.


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
