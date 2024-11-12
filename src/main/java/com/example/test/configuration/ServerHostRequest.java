package com.example.test.configuration;


import jakarta.servlet.http.HttpServletRequest;


public class ServerHostRequest {

    public static String getHost(HttpServletRequest request) {
        int port = request.getLocalPort();
        String ip = getClientIp(request,port+"");
        return ip + port;
    }

    private static String getClientIp(HttpServletRequest request,String port) {
        return request.getRequestURL().toString().split(port)[0];
    }
}
