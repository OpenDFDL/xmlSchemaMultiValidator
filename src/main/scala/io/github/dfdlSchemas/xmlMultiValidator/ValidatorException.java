package io.github.dfdlSchemas.xmlMultiValidator;

import java.util.Objects;

public class ValidatorException extends RuntimeException {

  public ValidatorException(String message) {
    super(message, null);
  }

  public ValidatorException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public ValidatorException(Throwable cause) {
    super(null, cause);
  }

  @Override
  public String getMessage() {
    if (Objects.isNull(getCause()))
      return super.getMessage();
    else
      return "ValidatorException: " + getCause().getMessage();
  }
}
