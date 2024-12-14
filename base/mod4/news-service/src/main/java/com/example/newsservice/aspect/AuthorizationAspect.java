package com.example.newsservice.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class AuthorizationAspect {

    @Before("execution(* com.example.newsservice.controller.NewsController.update*(..)) || " +
            "execution(* com.example.newsservice.controller.NewsController.delete*(..)) || " +
            "execution(* com.example.newsservice.controller.CommentController.update*(..)) || " +
            "execution(* com.example.newsservice.controller.CommentController.delete*(..))")
    public void checkOwnership() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String currentUsername = request.getUserPrincipal().getName();

        Long resourceId = Long.valueOf(request.getAttribute("id").toString()); // ID ресурса из пути
        String resourceOwner = ""; // Логика получения автора ресурса (например, из базы данных)

        if (!currentUsername.equals(resourceOwner)) {
            throw new SecurityException("You are not authorized to modify this resource.");
        }
    }
}