package com.mbb.assignment.service.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;

/**
 * The LoggingReqResInterceptor class will be used to log request and response in this microservice
 *
 * @author little940301@gmail.com
 * @version 1.0
 */
@Component
@Slf4j
public class LoggingReqResInterceptor implements HandlerInterceptor {
  
  /**
   * Pre-handle method to intercept the request before it is handled by the controller.
   *
   * @param request  The HTTP request object.
   * @param response The HTTP response object.
   * @param handler  The handler for the request.
   * @return true to proceed with handling the request, false to abort.
   * @throws Exception if an error occurs during pre-handling.
   * @author little940301@gmail.com
   * @version 1.0
   * @since 1.0
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpServletRequest wrappedRequest = wrapRequestIfNeeded(request);
    logRequestInfo(wrappedRequest);
    return true;
  }

  /**
   * After-completion method to intercept the response after the controller has finished handling the request.
   *
   * @param request  The HTTP request object.
   * @param response The HTTP response object.
   * @param handler  The handler for the request.
   * @param ex       Any exception that occurred during request processing, or null if no exception occurred.
   * @author little940301@gmail.com
   * @version 1.0
   * @since 1.0
   */
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    HttpServletResponse wrappedResponse = wrapResponseIfNeeded(response);
    logResponseInfo(request, wrappedResponse);
  }

  /**
   * Method to wrap the request with ContentCachingRequestWrapper if needed.
   *
   * @param request The original HTTP request object.
   * @return The wrapped request if needed, otherwise the original request.
   * @author little940301@gmail.com
   * @version 1.0
   * @since 1.0
   */
  private HttpServletRequest wrapRequestIfNeeded(HttpServletRequest request) {
    return request instanceof ContentCachingRequestWrapper ? request :
            new ContentCachingRequestWrapper(request);
  }

  /**
   * Method to wrap the response with ContentCachingResponseWrapper if needed.
   *
   * @param response The original HTTP response object.
   * @return The wrapped response if needed, otherwise the original response.
   * @author little940301@gmail.com
   * @version 1.0
   * @since 1.0
   */
  private HttpServletResponse wrapResponseIfNeeded(HttpServletResponse response) {
    return response instanceof ContentCachingResponseWrapper ? response :
            new ContentCachingResponseWrapper(response);
  }

  /**
   * Method to log request information including body.
   *
   * @param request The HTTP request object.
   * @author little940301@gmail.com
   * @version 1.0
   * @since 1.0
   */
  private void logRequestInfo(HttpServletRequest request) {
    StringBuilder requestInfo = new StringBuilder();
    requestInfo.append("Incoming request: ").append(request.getMethod()).append(" ").append(request.getRequestURI()).append("\n");

    if (request instanceof ContentCachingRequestWrapper requestWrapper) {
      byte[] requestBody = requestWrapper.getContentAsByteArray();
      if (requestBody.length > 0) {
        try {
          String requestBodyString = new String(requestBody, requestWrapper.getCharacterEncoding());
          requestInfo.append("Request body: ").append(requestBodyString).append("\n");
        } catch (UnsupportedEncodingException e) {
          log.error("Error reading request body", e);
        }
      }
    }

    log.info(requestInfo.toString());
  }

  /**
   * Method to log response information including body.
   *
   * @param request  The HTTP request object.
   * @param response The HTTP response object.
   * @author little940301@gmail.com
   * @version 1.0
   * @since 1.0
   */
  private void logResponseInfo(HttpServletRequest request, HttpServletResponse response) {
    StringBuilder responseInfo = new StringBuilder();
    responseInfo.append("Outgoing response: ").append(response.getStatus()).append(" ").append(request.getRequestURI()).append("\n");

    if (response instanceof ContentCachingResponseWrapper responseWrapper) {
      byte[] responseBody = responseWrapper.getContentAsByteArray();
      if (responseBody.length > 0) {
        try {
          String responseBodyString = new String(responseBody, responseWrapper.getCharacterEncoding());
          responseInfo.append("Response body: ").append(responseBodyString).append("\n");
        } catch (UnsupportedEncodingException e) {
          log.error("Error reading response body", e);
        }
      }
    }

    log.info(responseInfo.toString());
  }

}
