package com.mbb.assignment.service.exception.base;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * The ResourceNotFoundException class will be used to throw exception when the requested
 * resource is not found.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
public class ResourceNotFoundException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = -3098669988509514800L;

  /** module error code */
  private final String moduleErrorCode;

  /**
   * This constructor is used to initialize message and moduleErrorCode.
   *
   * @param messageSource messageResource
   * @param moduleCode module code
   * @param errorCode error code
   * @param parameters list of values to be replaced in the message.
   * @author little940301@gmail.com
   * @since 1.0
   */
  public ResourceNotFoundException(
      MessageSource messageSource, String moduleCode, String errorCode, Object[] parameters) {
    super(messageSource.getMessage(errorCode, parameters, Locale.getDefault()));
    this.moduleErrorCode = moduleCode.concat(errorCode);
  }

  /**
   * Gets module error code.
   *
   * @return moduleErrorCode String
   * @author little940301@gmail.com
   * @since 1.0
   */
  public String getModuleErrorCode() {
    return moduleErrorCode;
  }
}
