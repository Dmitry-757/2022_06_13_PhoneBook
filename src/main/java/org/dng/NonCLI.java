package org.dng;

public @interface NonCLI {
    String value() default "not console output";
}
