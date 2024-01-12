package io.github.dfdlSchemas.xmlMultiValidator;

import java.io.File;
import java.net.URL;

public abstract class Validator {
    public abstract void validate(URL xmlFile);

    public void toss(ValidatorException t) {
        throw t;
    }
}