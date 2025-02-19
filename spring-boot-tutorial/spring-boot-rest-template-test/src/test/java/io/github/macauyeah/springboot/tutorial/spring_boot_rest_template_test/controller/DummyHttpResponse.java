package io.github.macauyeah.springboot.tutorial.spring_boot_rest_template_test.controller;

import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import javax.net.ssl.SSLSession;

public class DummyHttpResponse implements HttpResponse<String> {
    private String body;
    public DummyHttpResponse(String body){
        this.body = body;
    }

    @Override
    public String body() {
        return this.body;
    }

    @Override
    public HttpHeaders headers() {
        throw new UnsupportedOperationException("Unimplemented method 'headers'");
    }

    @Override
    public Optional<HttpResponse<String>> previousResponse() {
        throw new UnsupportedOperationException("Unimplemented method 'previousResponse'");
    }

    @Override
    public HttpRequest request() {
        throw new UnsupportedOperationException("Unimplemented method 'request'");
    }

    @Override
    public Optional<SSLSession> sslSession() {
        throw new UnsupportedOperationException("Unimplemented method 'sslSession'");
    }

    @Override
    public int statusCode() {
        throw new UnsupportedOperationException("Unimplemented method 'statusCode'");
    }

    @Override
    public URI uri() {
        throw new UnsupportedOperationException("Unimplemented method 'uri'");
    }

    @Override
    public Version version() {
        throw new UnsupportedOperationException("Unimplemented method 'version'");
    }
    
}
