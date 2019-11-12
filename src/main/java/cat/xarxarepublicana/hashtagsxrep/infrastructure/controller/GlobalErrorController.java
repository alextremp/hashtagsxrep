package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.domain.core.error.EntityNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalErrorController implements ErrorController {

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());
      if (statusCode == HttpStatus.NOT_FOUND.value()) {
        return "error-404";
      } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        if (exception != null && exception.getCause() != null) {
          if (EntityNotFoundException.class.isAssignableFrom(exception.getCause().getClass())) {
            return "error-404";
          }
        }
      }
    }
    return "error";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
