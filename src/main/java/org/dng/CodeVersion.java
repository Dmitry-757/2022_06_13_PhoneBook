package org.dng;

public @interface CodeVersion {
    String version();
    String description();
    String lastModification() default "2022.06.05";
}
