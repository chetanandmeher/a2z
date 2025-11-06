package com.a2z.config;

/**
 * Constants used for JSON Web Token handling across the application.
 * <p>
 * This class only contains public static final constants and is not
 * intended to be instantiated.
 */
public final class JWT_CONSTANT {
    private JWT_CONSTANT() { /* prevent instantiation */ }

    public static final String SECRET_KEY = "AWERXTCYGVUBHIJNNOBVICUYTXREZXTCYVBiyvwdfiwvifvwefvewuifvwiueviwhjvwhdvvavhwvjhvwddkhwdwjhgasdvjytvaduywoiuweuyg2r762398y24t873rviubbvwvbvkjsbvipvvbr973rf7g3r8-g37f8703f73f02389r39qwe6r962grhnfkq2AESDRTFYGUI3465DR7TYGUJNKsdvkh";

    /**
     * HTTP header name where the JWT is expected (e.g. "Authorization").
     */
    public static final String JWT_HEADER = "Authorization";

}
