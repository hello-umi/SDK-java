package requests.exceptions;

import requests.Response;

public abstract class ResponseException extends RuntimeException {

    private Response response;

    ResponseException(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return this.response;
    }

    public static ResponseException get(Response response) {
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        switch (response.getStatusCode()){
            case 401:
                return new UnauthorizedException(response);
//            case 402:
//                return new PaymentRequiredException(response);
//            case 403:
//                return new ForbiddenException(response);
//            case 404:
//                return new NotFoundException(response);
//            case 412:
//                return new PreconditionFailedException(response);
//            case 413:
//                return new RequestTooLargeException(response);
//            case 429:
//                return new TooManyRequestsException(response);
        }
        return null;
    }

}
