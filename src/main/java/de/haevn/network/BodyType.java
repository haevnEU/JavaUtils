package de.haevn.network;

import java.net.http.HttpRequest;

/**
 * The type of the body of a {@link HttpRequest}.
 * <ul>
 *     <li>{@link #DEFAULT} - The default body type. This is the same as {@link #STRING}.</li>
 *     <li>{@link #STRING} - The body is a {@link String}.</li>
 *     <li>{@link #BYTE_ARRAY} - The body is a {@link byte[]}.</li>
 *     <li>{@link #FILE} - The body is a {@link java.io.File}.</li>
 *     <li>{@link #INPUT_STREAM} - The body is an {@link java.io.InputStream}.</li>
 *     <li>{@link #NONE} - The body is empty.</li>
 * </ul>
 *
 * @author haevn
 * @version 1.0
 * @see HttpRequest
 * @see HttpRequest.BodyPublisher
 * @since 1.0
 */
public enum BodyType {
    DEFAULT,
    STRING,
    BYTE_ARRAY,
    FILE,
    INPUT_STREAM,
    NONE

}
