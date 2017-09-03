package requests.exceptions;

import requests.Response;

public class RequestTooLargeException extends ResponseException {

    RequestTooLargeException(Response response){
        super(response);
    }

}
