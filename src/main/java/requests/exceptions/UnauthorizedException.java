package requests.exceptions;

import requests.Response;

public class UnauthorizedException extends ResponseException {

    UnauthorizedException(Response response) {
        super(response);
    }
}
