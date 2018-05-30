package com.example.demo.common.exception;

import com.example.demo.common.exception.base.BaseException;
import com.example.demo.common.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    ResponseEntity<Object> handleBaseException(HttpServletRequest request, BaseException ex, WebRequest webRequest) {
        HttpStatus status = ex.getStatus();

        if (null == status) {
            status = getStatus(request);
        }

        logger.info("处理http请求异常, errrno: " + ex.getCode() + ", errmsg: " + ex.getMessage(), ex);
        Response response = new Response(ex.getErrorCode());
        response.setData(ex.getMessage());
        return super.handleExceptionInternal(ex, response, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handleException(Exception ex, WebRequest webRequest, HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        logger.error("处理http请求异常, msg" + ex.getMessage(), ex);
        return super.handleExceptionInternal(ex, buildErrorResponse("发生了未定义类型的错误", status), new HttpHeaders(), status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder errors = new StringBuilder();
        if (ex.getBindingResult().hasErrors()) {
            for (ObjectError error : ex.getBindingResult().getAllErrors()) {
                errors.append(error.getDefaultMessage()).append(System.getProperty("line.separator"));
            }
        }
        return handleExceptionInternal(ex, errors, headers, status, request);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("处理http请求异常, 错误信息{}, body:{}", ex, body);
        return super.handleExceptionInternal(ex, buildErrorResponse(body, status), headers, status, request);
    }

    private Response<Object> buildErrorResponse(Object body, HttpStatus status) {
        return new Response<>(status.value(), status.getReasonPhrase(), body);
    }
}
