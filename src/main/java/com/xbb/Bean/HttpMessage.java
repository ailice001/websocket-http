package com.xbb.Bean;

import javax.servlet.http.HttpServletResponse;

public class HttpMessage {
    private HttpServletResponse response;
    private String requestId;
    private String message;

    public HttpMessage(HttpServletResponse response,String requestId) {
        this.response = response;
        this.requestId = requestId;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
