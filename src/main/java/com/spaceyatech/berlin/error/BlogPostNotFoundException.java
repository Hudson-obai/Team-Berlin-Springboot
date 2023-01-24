package com.spaceyatech.berlin.error;

public class BlogPostNotFoundException extends Exception{
    public BlogPostNotFoundException() {
        super();
    }

    public BlogPostNotFoundException(String message) {
        super(message);
    }

    public BlogPostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogPostNotFoundException(Throwable cause) {
        super(cause);
    }

    protected BlogPostNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}