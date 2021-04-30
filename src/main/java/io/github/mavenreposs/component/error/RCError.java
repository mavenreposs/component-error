package io.github.mavenreposs.component.error;

import java.util.*;

/**
 * Royalcms Error class.
 *
 * Container for checking for errors and error messages. Return
 * DscError and use {@link #is_error(Object)}} to check if this class is returned.
 * Many core functions pass this class in the event of an error and
 * if not handled properly will result in code errors.
 *
 */
public class RCError {

    /**
     * Stores the list of errors.
     */
    private final Map<String, List<String>> errors = new LinkedHashMap<>();

    /**
     * Stores the list of data for error codes.
     */
    private final Map<String, Object> error_data = new LinkedHashMap<>();

    public RCError() {
    }

    /**
     * Constructor - Sets up error message.
     *
     * If code parameter is empty then nothing will be done. It is possible to
     * add multiple messages to the same code, but with other methods in the
     * class.
     *
     * All parameters are optional, but if the code parameter is set, then the
     * data parameter is optional.
     *
     * @param code Error code
     * @param message Error message
     */
    public RCError(String code, String message) {
        List<String> messages = new ArrayList<>();
        messages.add(message);
        this.errors.put(code, messages);
    }

    public RCError(Integer code, String message) {
        this(code.toString(), message);
    }

    /**
     * Constructor - Sets up error message.
     *
     * If code parameter is empty then nothing will be done. It is possible to
     * add multiple messages to the same code, but with other methods in the
     * class.
     *
     * All parameters are optional, but if the code parameter is set, then the
     * data parameter is optional.
     *
     * @param code Error code
     * @param message Error message
     * @param data Optional. Error data.
     */
    public RCError(String code, String message, Object data) {
        this(code, message);
        this.error_data.put(code, data);
    }

    public RCError(Integer code, String message, Object data) {
        this(code.toString(), message, data);
    }

    /**
     * Retrieve all error codes.
     *
     * @return array List of error codes, if available.
     */
    public List<String> get_error_codes() {
        if (this.errors.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(this.errors.keySet());
    }

    /**
     * Retrieve first error code available.
     *
     * @since 3.1.0
     *
     * @return string Empty string, if no error codes.
     */
    public String get_error_code() {
        List<String> codes = get_error_codes();
        if (codes.isEmpty()) {
            return "";
        }
        return codes.get(0);
    }

    /**
     * Retrieve all error messages or error messages matching code.
     *
     * @return array Error strings on success, or empty array on failure (if using code parameter).
     */
    public List<String> get_error_messages() {
        List<String> all_messages = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : errors.entrySet()) {
            all_messages.addAll(entry.getValue());
        }
        return all_messages;
    }

    /**
     * Retrieve all error messages or error messages matching code.
     *
     * @param code Optional. Retrieve messages matching code, if exists.
     * @return array Error strings on success, or empty array on failure (if using code parameter).
     */
    public List<String> get_error_messages(String code) {
        if (errors.containsKey(code)) {
            return errors.get(code);
        }
        return new ArrayList<>();
    }

    public List<String> get_error_messages(Integer code) {
        return get_error_messages(code.toString());
    }

    /**
     * Get single error message.
     *
     * This will get the first message available for the code. If no code is
     * given then the first code available will be used.
     *
     * @return string
     */
    public String get_error_message() {
        String code = get_error_code();
        return get_error_message(code);
    }

    /**
     * Get single error message.
     *
     * This will get the first message available for the code. If no code is
     * given then the first code available will be used.
     *
     * @param code Optional. Error code to retrieve message.
     * @return string
     */
    public String get_error_message(String code) {
        List<String> message = get_error_messages(code);
        if (message.isEmpty()) {
            return "";
        }
        return message.get(0);
    }

    public String get_error_message(Integer code) {
        return get_error_message(code.toString());
    }

    /**
     * Retrieve error data for error code.
     *
     * @return mixed Null, if no errors.
     */
    public Object get_error_data() {
        String code = get_error_code();
        return get_error_data(code);
    }

    /**
     * Retrieve error data for error code.
     *
     * @param code Optional. Error code.
     * @return mixed Null, if no errors.
     */
    public Object get_error_data(String code) {
        if (error_data.containsKey(code)) {
            return error_data.get(code);
        }
        return null;
    }

    public Object get_error_data(Integer code) {
        return get_error_data(code.toString());
    }

    /**
     * Append more error messages to list of error messages.
     *
     * @param code Error code.
     * @param message Error message.
     */
    public void add(String code, String message) {
        List<String> messages;
        if (errors.containsKey(code)) {
            messages = errors.get(code);
        }
        else {
            messages = new ArrayList<>();
        }

        messages.add(message);

        errors.put(code, messages);
    }

    public void add(Integer code, String message) {
        add(code.toString(), message);
    }

    /**
     * Append more error messages to list of error messages.
     *
     * @param code Error code.
     * @param message Error message.
     * @param data Optional. Error data.
     */
    public void add(String code, String message, Object data) {
        this.add(code, message);
        error_data.put(code, data);
    }

    public void add(Integer code, String message, Object data) {
        add(code.toString(), message, data);
    }

    /**
     * Add data for error code.
     *
     * The error code can only contain one error data.
     *
     * @param data Error data.
     */
    public void add_data(Object data) {
        String code = get_error_code();
        error_data.put(code, data);
    }

    /**
     * Add data for error code.
     *
     * The error code can only contain one error data.
     *
     * @param data Error data.
     * @param code Error code.
     */
    public void add_data(Object data, String code) {
        error_data.put(code, data);
    }

    public void add_data(Object data, Integer code) {
        add_data(data, code.toString());
    }

    @Override
    public String toString() {
        return "RCError{" +
                "errors=" + errors +
                ", error_data=" + error_data +
                '}';
    }

    /**
     * Check whether variable is a Error.
     *
     * Returns true if thing is an object of the DscError class.
     *
     * @param thing Check if unknown variable is a DscError object.
     * @return bool True, if DscError. False, if not DscError.
     */
    public static boolean is_error(Object thing) {
        return thing instanceof RCError;
    }

}
