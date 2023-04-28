package enums;

/**
 * Enumeration of the 3 ways to represent an arithmetic expression as a String:
 */
public enum TypeString
{
    INTEGER, /* 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 */
    OPERATOR, /* +, -, *, /, ^, !, % */
    BRACKET, /* (, ) */
    REAL, /* 0.1, 0.2, 0.3, 0.4, 0.5, 0.6 */
    BOOLEAN, /* true, false */
    E_NOTATION, /* 1E2, 1E3, 1E4, 1E5, 1E6 */
    SCIENTIFIC, /* 1x10^2, 1x10^3, 1x10^4, 1x10^5, 1x10^6 */
    COMPLEX, /* 1+2i, 1-2i, 1+i, 1-i */
    REAL_COMPLEX, /* 1.0+2.0i, 1.0-2.0i, 1.0+i, 1.0-i */
    E_NOTATION_COMPLEX, /* 1E2+2E3i, 1E3-2E4i, 1E4+2E5i, 1E5-2E6i */
    SCIENTIFIC_COMPLEX, /* 1x10^2+2x10^3i, 1x10^3-2x10^4i, 1x10^4+2x10^5i, 1x10^5-2x10^6i */
    BINARY, /* 0b101, 0b110, 0b111, 0b1000, 0b1001, 0b1010 */
}
