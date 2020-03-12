package com.rayject.dataurl;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * encode() is copied from android.net.Uri.encode
 * decode() is copied from libcore.net.UriCodec
 */
public class UriCodec {

    /** Index of a component which was not found. */
    private final static int NOT_FOUND = -1;

    /** Default encoding. */
    private static final String DEFAULT_ENCODING = "UTF-8";

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    public static String encode(String s) {
        return encode(s, DEFAULT_ENCODING, null);
    }

    public static String encode(String s, String charset) {
        return encode(s, charset, null);
    }

    /**
     * Encodes characters in the given string as '%'-escaped octets
     * using the charset . Leaves letters ("A-Z", "a-z"), numbers
     * ("0-9"), and unreserved characters ("_-!.~'()*") intact. Encodes
     * all other characters with the exception of those specified in the
     * allow argument.
     *
     * @param s string to encode
     * @param charset string charset
     * @param allow set of additional characters to allow in the encoded form,
     *  null if no characters should be skipped
     * @return an encoded version of s suitable for use as a URI component,
     *  or null if s is null
     */
    public static String encode(String s, String charset, String allow) {
        if (s == null) {
            return null;
        }

        // Lazily-initialized buffers.
        StringBuilder encoded = null;

        int oldLength = s.length();

        // This loop alternates between copying over allowed characters and
        // encoding in chunks. This results in fewer method calls and
        // allocations than encoding one character at a time.
        int current = 0;
        while (current < oldLength) {
            // Start in "copying" mode where we copy over allowed chars.

            // Find the next character which needs to be encoded.
            int nextToEncode = current;
            while (nextToEncode < oldLength
                    && isAllowed(s.charAt(nextToEncode), allow)) {
                nextToEncode++;
            }

            // If there's nothing more to encode...
            if (nextToEncode == oldLength) {
                if (current == 0) {
                    // We didn't need to encode anything!
                    return s;
                } else {
                    // Presumably, we've already done some encoding.
                    encoded.append(s, current, oldLength);
                    return encoded.toString();
                }
            }

            if (encoded == null) {
                encoded = new StringBuilder();
            }

            if (nextToEncode > current) {
                // Append allowed characters leading up to this point.
                encoded.append(s, current, nextToEncode);
            } else {
                // assert nextToEncode == current
            }

            // Switch to "encoding" mode.

            // Find the next allowed character.
            current = nextToEncode;
            int nextAllowed = current + 1;
            while (nextAllowed < oldLength
                    && !isAllowed(s.charAt(nextAllowed), allow)) {
                nextAllowed++;
            }

            // Convert the substring to bytes and encode the bytes as
            // '%'-escaped octets.
            String toEncode = s.substring(current, nextAllowed);
            try {
                byte[] bytes = toEncode.getBytes(charset);
                int bytesLength = bytes.length;
                for (int i = 0; i < bytesLength; i++) {
                    encoded.append('%');
                    encoded.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4]);
                    encoded.append(HEX_DIGITS[bytes[i] & 0xf]);
                }
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }

            current = nextAllowed;
        }

        // Encoded could still be null at this point if s is empty.
        return encoded == null ? s : encoded.toString();
    }

    /**
     * Returns true if the given character is allowed.
     *
     * @param c character to check
     * @param allow characters to allow
     * @return true if the character is allowed or false if it should be
     *  encoded
     */
    private static boolean isAllowed(char c, String allow) {
        return (c >= 'A' && c <= 'Z')
                || (c >= 'a' && c <= 'z')
                || (c >= '0' && c <= '9')
                || "_-!.~'()*".indexOf(c) != NOT_FOUND
                || (allow != null && allow.indexOf(c) != NOT_FOUND);
    }

    public static String decode(String s, String charset) {
        return decode(s, false, Charset.forName(charset), false);
    }

    public static String decode(String s, Charset charset) {
        return decode(s, false, charset, false);
    }

    /**
     * @param convertPlus true to convert '+' to ' '.
     * @param throwOnFailure true to throw an IllegalArgumentException on
     *     invalid escape sequences; false to replace them with the replacement
     *     character (U+fffd).
     */
    public static String decode(String s, boolean convertPlus, Charset charset,
                                boolean throwOnFailure) {
        if (s.indexOf('%') == -1 && (!convertPlus || s.indexOf('+') == -1)) {
            return s;
        }
        StringBuilder result = new StringBuilder(s.length());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);
            if (c == '%') {
                do {
                    int d1, d2;
                    if (i + 2 < s.length()
                            && (d1 = hexToInt(s.charAt(i + 1))) != -1
                            && (d2 = hexToInt(s.charAt(i + 2))) != -1) {
                        out.write((byte) ((d1 << 4) + d2));
                    } else if (throwOnFailure) {
                        throw new IllegalArgumentException("Invalid % sequence at " + i + ": " + s);
                    } else {
                        byte[] replacement = "\ufffd".getBytes(charset);
                        out.write(replacement, 0, replacement.length);
                    }
                    i += 3;
                } while (i < s.length() && s.charAt(i) == '%');
                result.append(new String(out.toByteArray(), charset));
                out.reset();
            } else {
                if (convertPlus && c == '+') {
                    c = ' ';
                }
                result.append(c);
                i++;
            }
        }
        return result.toString();
    }

    /**
     * Like {@link Character#digit}, but without support for non-ASCII
     * characters.
     */
    private static int hexToInt(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        } else if ('a' <= c && c <= 'f') {
            return 10 + (c - 'a');
        } else if ('A' <= c && c <= 'F') {
            return 10 + (c - 'A');
        } else {
            return -1;
        }
    }
}
