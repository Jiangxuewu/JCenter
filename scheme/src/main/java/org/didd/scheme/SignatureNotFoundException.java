package org.didd.scheme;

/**
 * Created by Jiangxuewu on 2017/12/20.
 */

class SignatureNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public SignatureNotFoundException(final String message) {
        super(message);
    }

    public SignatureNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
