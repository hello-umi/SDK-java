package requests.exceptions;

import requests.Response;

public class UnauthorizedException extends ResponseException {

    public UnauthorizedException(Response response) {
        super(response);
    }
}
