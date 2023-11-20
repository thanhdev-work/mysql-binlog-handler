package vn.thanhdev.core.exception;

public class BinLogCoreException extends RuntimeException {

    public BinLogCoreException() {
        super();
    }

    public BinLogCoreException(String message) {
        super(message);
    }

    public BinLogCoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
