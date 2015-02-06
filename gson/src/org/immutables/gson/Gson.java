package org.immutables.gson;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
public @interface Gson {
  /**
   * Use on a top level class to generate type adapted factory.
   * <p>
   * Type adapter factories are also registered statically as services
   * {@code META-INF/services/com.google.gson.TypeAdapterFactory}. Easy way to configure
   * {@link com.google.gson.Gson}.
   * <p>
   * Certain are gson options are supported for immutable objects in deliberate fashion:
   * <ul>
   * <li>{@link GsonBuilder#serializeNulls()} - When enabled, {@code null} fields and empty array
   * fields will be included, otherwise omited</li>
   * </ul>
   */
  @Documented
  @Retention(RetentionPolicy.SOURCE)
  @Target({ElementType.TYPE})
  public @interface TypeAdapted {}

  /**
   * Expected subclasses for marshaling could be specified on attribute level or an abstract
   * supertype directly, however the former declaration site has precedence.
   * @see #value()
   * @see Named
   */
  @Retention(RetentionPolicy.SOURCE)
  @Target({ElementType.METHOD, ElementType.TYPE})
  public @interface Subtypes {

    /**
     * Specifies expected subclasses of an abstract type that is matched during parsing by
     * structural compatibility of settable attributes.
     * If all attributes of subclasses are the same, then it will result in error due to undecidable
     * situation.
     * @return subclasses of an abstract type
     */
    Class<?>[] value();
  }

  /**
   * Specify attribute's custom name in JSON representation.
   * <p>
   * This example used to define JSON attribute name as "_id" during marshaling and unmarshaling.
   * 
   * <pre>
   * &#064;Gson.Named(&quot;_id&quot;)
   * public abstract String id();
   * </pre>
   * 
   * This annotation is analogous to {@link SerializedName}, but works on attributes (expressed as
   * methods).
   * @see SerializedName
   */
  @Documented
  @Retention(RetentionPolicy.SOURCE)
  @Target(ElementType.METHOD)
  public @interface Named {
    /**
     * @return custom name string.
     */
    String value();
  }

  /**
   * Indicates if marshaler should skip this attribute during marshaling.
   * Applies only to non-mandatory attributes.
   */
  @Documented
  @Retention(RetentionPolicy.SOURCE)
  @Target(ElementType.METHOD)
  public @interface Ignore {}
}
