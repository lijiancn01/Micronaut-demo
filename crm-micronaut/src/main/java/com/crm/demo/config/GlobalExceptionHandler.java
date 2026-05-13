package com.crm.demo.config;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Produces
@Singleton
@Requires(classes = {EntityNotFoundException.class, ExceptionHandler.class})
public class GlobalExceptionHandler implements ExceptionHandler<Exception, HttpResponse<?>> {

    @Override
    public HttpResponse<?> handle(HttpRequest request, Exception exception) {
        Map<String, Object> response = new HashMap<>();
        
        if (exception instanceof EntityNotFoundException) {
            response.put("message", "客户不存在");
            return HttpResponse.notFound(response);
        } else if (exception instanceof IllegalArgumentException) {
            response.put("message", exception.getMessage());
            return HttpResponse.badRequest(response);
        } else if (exception.getMessage() != null && exception.getMessage().contains("email")) {
            response.put("message", "邮箱格式不正确");
            return HttpResponse.badRequest(response);
        }
        
        response.put("message", exception.getMessage() != null ? exception.getMessage() : "服务器内部错误");
        return HttpResponse.serverError(response);
    }
}
