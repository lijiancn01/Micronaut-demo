package com.crm.demo.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class HomeController {

    @Get("/")
    public HttpResponse<?> index() {
        return HttpResponse.permanentRedirect(java.net.URI.create("/index.html"));
    }
}
