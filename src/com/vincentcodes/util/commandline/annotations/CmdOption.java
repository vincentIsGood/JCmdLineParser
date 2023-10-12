package com.vincentcodes.util.commandline.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CmdOption {
    /**
     * eg. "option" for "--option"
     */
    String value() default "";

    /**
     * boolean fields are false and the rest are true.
     * eg. "--option" is needed, instead of "--option <value>"
     * @deprecated
     */
    boolean isStandalone() default true;
    
    String shortForm() default "";

    /**
     * eg. "<value>"
     */
    String parameterDescription() default "";
    
    /**
     * eg. "this '--option <value>' takes an integer value"
     */
    String description() default "";
}
