package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code NeedsOptimization} annotation is used to mark methods that require optimization.
 * It provides a way to flag methods that might need performance improvements or refactoring.
 *
 * This annotation can be used by developers as a reminder to revisit the method and improve its efficiency,
 * or by tools that analyze the code for performance bottlenecks.
 *
 * The annotation includes an optional description (via the {@code value} attribute) that can provide
 * additional context on why the method needs optimization or suggestions for improvements.
 *
 * Example usage:
 * <pre>
 *     {@code
 *     @NeedsOptimization("Consider using streams for better performance.")
 *     public void slowMethod() {
 *         // method implementation
 *     }
 *     }
 * </pre>
 *
 * The annotation is retained in the compiled class files but is not available at runtime.
 *
 * @author Oliwier Gebczynski
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface NeedsOptimization {
    /**
     * A message providing additional details about why the method requires optimization.
     *
     * @return A string description of the optimization suggestion.
     */
    String value() default "This method requires optimization.";
}
