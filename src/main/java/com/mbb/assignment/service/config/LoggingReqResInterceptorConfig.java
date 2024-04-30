package com.mbb.assignment.service.config;

import com.mbb.assignment.service.interceptor.LoggingReqResInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The AuditInterceptorConfig class will configure interceptors
 *
 * @author little940301@gmail.com
 * @version 1.0
 */
@Configuration
public class LoggingReqResInterceptorConfig implements WebMvcConfigurer {

	/**
	 * auditInterceptor
	 */
	private final LoggingReqResInterceptor loggingReqResInterceptor;

	/**
	 * This Constructor is used to initialise the interceptors
	 *
	 * @param loggingReqResInterceptor LoggingReqResInterceptor
	 * @author little940301@gmail.com
	 * @since 1.0
	 */
	public LoggingReqResInterceptorConfig(LoggingReqResInterceptor loggingReqResInterceptor) {
		this.loggingReqResInterceptor = loggingReqResInterceptor ;
	}

	/**
	 * This callback will register all interceptors to the registry
	 * @param registry  InterceptorRegistry
	 * @author little940301@gmail.com
	 * @since 1.0
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggingReqResInterceptor);
	}

}
