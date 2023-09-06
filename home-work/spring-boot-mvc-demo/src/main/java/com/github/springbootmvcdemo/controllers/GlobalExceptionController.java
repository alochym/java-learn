package com.github.springbootmvcdemo.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;

/* 
 * Logging
 */
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.springbootmvcdemo.exceptions.ErrorDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

/* 
 * There are 2 type of Exception Handlers in SPRING BOOT
 * 
 *  1. Exception Handler per Controller - Controller base.
 *  2. Global Exception Handler - Applied for all Controller
 *      1. @ControllerAdvice
 *  3. Extending of ResponseEntityExceptionHandler for customize Error Response.
 *  4. Spring Boot Validation depends
 *      1. Jakarta Validation - spec.
 *          1. https://beanvalidation.org
 *      2. Hibernate Validator - implementation.
 *          1. https://hibernate.org
*/
@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    // private static final Logger LOGGER =
    // LoggerFactory.getLogger(GlobalExceptionController.class);

    /*
     * General Exception class - Exception.class
     */
    @ExceptionHandler(Exception.class)

    /*
     * Set Http Status Code for Response.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    /*
     * return the content of Response
     */
    @ResponseBody
    public ErrorDTO handleGenericException(Exception e, HttpServletRequest req) {
        ErrorDTO err = new ErrorDTO();

        err.setTimestamp(new Date());
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setPath(req.getServletPath());
        // err.setErrs(e.getMessage());
        err.addErr(e.getMessage());

        /*
         * log exception TODO
         */
        // LOGGER.error(e.getMessage(), e);

        return err;
    }

    @Override
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorDTO err = new ErrorDTO();

        err.setTimestamp(new Date());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setPath(((ServletWebRequest)request).getRequest().getServletPath());
        

        List<FieldError> fieldErrs = e.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrs) {
            err.addErr(fieldError.getDefaultMessage());
        }

        /*
         * log exception TODO
         */
        // LOGGER.error(e.getMessage(), e);

        return new ResponseEntity<>(err, headers, HttpStatus.BAD_REQUEST.value());
    }

   /*
     * General Exception class - Exception.class
     */
    @ExceptionHandler(ConstraintViolationException.class)

    /*
     * Set Http Status Code for Response.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    /*
     * return the content of Response
     */
    @ResponseBody
    public ErrorDTO handleConstraintViolationException(Exception e, HttpServletRequest req) {
        ErrorDTO err = new ErrorDTO();

        err.setTimestamp(new Date());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setPath(req.getServletPath());
        err.addErr(e.getMessage());

        /*
         * log exception TODO
         */
        // LOGGER.error(e.getMessage(), e);

        return err;
    }   
}
