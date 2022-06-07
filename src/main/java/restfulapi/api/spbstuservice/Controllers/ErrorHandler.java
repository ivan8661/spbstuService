package restfulapi.api.spbstuservice.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import restfulapi.api.spbstuservice.Exceptions.UserException;
import restfulapi.api.spbstuservice.Exceptions.UserExceptionType;


@ControllerAdvice
public class ErrorHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserException.class)
    protected ResponseEntity<UserException> handleUserException(UserException ex) {
        return new ResponseEntity<>(ex, ex.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ClassNotFoundException.class)
    protected ResponseEntity<UserException> handleFoundException(ServerErrorException exception) {
        UserException ex = new UserException(UserExceptionType.OBJECT_NOT_FOUND, exception);
        return handleUserException(ex);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ServerErrorException.class)
    protected ResponseEntity<UserException> handleInternalErrorException(ServerErrorException exception) {
        UserException ex = new UserException(UserExceptionType.OBJECT_NOT_FOUND, exception);
        return handleUserException(ex);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchFieldException.class)
    protected ResponseEntity<UserException> handleNoSuchFieldException(NoSuchFieldException exception) {
        // Костыль чтоб не словить пустой ответ из-за ошибки сериализации
        UserException ex = new UserException(UserExceptionType.OBJECT_NOT_FOUND, exception);
        return handleUserException(ex);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.BadRequest.class)
    protected ResponseEntity<UserException> handleBadRequestException(HttpClientErrorException exception) {
        UserException ex = new UserException(UserExceptionType.OBJECT_NOT_FOUND, exception);
        return handleUserException(ex);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    protected ResponseEntity<UserException> handleOtherException(Exception exception) {
        UserException ex = new UserException(UserExceptionType.OBJECT_NOT_FOUND, exception);
        return handleUserException(ex);
    }
}
