package org.senechka.exceptions;

import java.io.Serial;

public class CorruptetFilenameException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public CorruptetFilenameException(String msg) {
        super(msg);
    }

}
