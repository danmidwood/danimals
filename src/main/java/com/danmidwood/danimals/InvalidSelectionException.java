package com.danmidwood.danimals;

public class InvalidSelectionException extends Exception {
    Class invalidClass;

    InvalidSelectionException(String msg, Class invalidClass) {
        super(msg);
        this.invalidClass = invalidClass;
    }

    public Class getInvalidClass() {
        return invalidClass;
    }
}
