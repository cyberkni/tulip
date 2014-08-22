package tulip.util;

import org.apache.commons.lang.ObjectUtils;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月5日 下午5:29:03
 */
public class StringUtil {
	public static final String EMPTY = "";

	public static final int INDEX_NOT_FOUND = -1;

	/**
	 * <p>
	 * <code>StringUtil</code> instances should NOT be constructed in standard
	 * programming. Instead, the class should be used as
	 * <code>StringUtil.trim(" foo ");</code>.
	 * </p>
	 * 
	 * <p>
	 * This constructor is public to permit tools that require a JavaBean
	 * instance to operate.
	 * </p>
	 */
	private StringUtil() {
		super();
	}

	// Empty checks
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if a String is empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = true
	 * StringUtil.isEmpty("")        = true
	 * StringUtil.isEmpty(" ")       = false
	 * StringUtil.isEmpty("bob")     = false
	 * StringUtil.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * <p>
	 * NOTE: This method changed in Lang version 2.0. It no longer trims the
	 * String. That functionality is available in isBlank().
	 * </p>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is empty or null
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * <p>
	 * Checks if a String is not empty ("") and not null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isNotEmpty(null)      = false
	 * StringUtil.isNotEmpty("")        = false
	 * StringUtil.isNotEmpty(" ")       = true
	 * StringUtil.isNotEmpty("bob")     = true
	 * StringUtil.isNotEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null
	 */
	public static boolean isNotEmpty(String str) {
		return !StringUtil.isEmpty(str);
	}

	/**
	 * <p>
	 * Checks if a String is whitespace, empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isBlank(null)      = true
	 * StringUtil.isBlank("")        = true
	 * StringUtil.isBlank(" ")       = true
	 * StringUtil.isBlank("bob")     = false
	 * StringUtil.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is null, empty or whitespace
	 * @since 2.0
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a String is not empty (""), not null and not whitespace only.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isNotBlank(null)      = false
	 * StringUtil.isNotBlank("")        = false
	 * StringUtil.isNotBlank(" ")       = false
	 * StringUtil.isNotBlank("bob")     = true
	 * StringUtil.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null and not
	 *         whitespace
	 * @since 2.0
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtil.isBlank(str);
	}

	// Trim
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String,
	 * handling <code>null</code> by returning <code>null</code>.
	 * </p>
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32. To strip whitespace use {@link #strip(String)}.
	 * </p>
	 * 
	 * <p>
	 * To trim your choice of characters, use the {@link #strip(String, String)}
	 * methods.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.trim(null)          = null
	 * StringUtil.trim("")            = ""
	 * StringUtil.trim("     ")       = ""
	 * StringUtil.trim("abc")         = "abc"
	 * StringUtil.trim("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to be trimmed, may be null
	 * @return the trimmed string, <code>null</code> if null String input
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String
	 * returning <code>null</code> if the String is empty ("") after the trim or
	 * if it is <code>null</code>.
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32. To strip whitespace use
	 * {@link #stripToNull(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.trimToNull(null)          = null
	 * StringUtil.trimToNull("")            = null
	 * StringUtil.trimToNull("     ")       = null
	 * StringUtil.trimToNull("abc")         = "abc"
	 * StringUtil.trimToNull("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to be trimmed, may be null
	 * @return the trimmed String, <code>null</code> if only chars &lt;= 32,
	 *         empty or null String input
	 * @since 2.0
	 */
	public static String trimToNull(String str) {
		String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String
	 * returning an empty String ("") if the String is empty ("") after the trim
	 * or if it is <code>null</code>.
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32. To strip whitespace use
	 * {@link #stripToEmpty(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.trimToEmpty(null)          = ""
	 * StringUtil.trimToEmpty("")            = ""
	 * StringUtil.trimToEmpty("     ")       = ""
	 * StringUtil.trimToEmpty("abc")         = "abc"
	 * StringUtil.trimToEmpty("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to be trimmed, may be null
	 * @return the trimmed String, or an empty String if <code>null</code> input
	 * @since 2.0
	 */
	public static String trimToEmpty(String str) {
		return str == null ? EMPTY : str.trim();
	}

	// Equals
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Compares two Strings, returning <code>true</code> if they are equal.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code>s are handled without exceptions. Two <code>null</code>
	 * references are considered to be equal. The comparison is case sensitive.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.equals(null, null)   = true
	 * StringUtil.equals(null, "abc")  = false
	 * StringUtil.equals("abc", null)  = false
	 * StringUtil.equals("abc", "abc") = true
	 * StringUtil.equals("abc", "ABC") = false
	 * </pre>
	 * 
	 * @see java.lang.String#equals(Object)
	 * @param str1
	 *            the first String, may be null
	 * @param str2
	 *            the second String, may be null
	 * @return <code>true</code> if the Strings are equal, case sensitive, or
	 *         both <code>null</code>
	 */
	public static boolean equals(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	/**
	 * <p>
	 * Compares two Strings, returning <code>true</code> if they are equal
	 * ignoring the case.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code>s are handled without exceptions. Two <code>null</code>
	 * references are considered equal. Comparison is case insensitive.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.equalsIgnoreCase(null, null)   = true
	 * StringUtil.equalsIgnoreCase(null, "abc")  = false
	 * StringUtil.equalsIgnoreCase("abc", null)  = false
	 * StringUtil.equalsIgnoreCase("abc", "abc") = true
	 * StringUtil.equalsIgnoreCase("abc", "ABC") = true
	 * </pre>
	 * 
	 * @see java.lang.String#equalsIgnoreCase(String)
	 * @param str1
	 *            the first String, may be null
	 * @param str2
	 *            the second String, may be null
	 * @return <code>true</code> if the Strings are equal, case insensitive, or
	 *         both <code>null</code>
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
	}

	// IndexOf
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Finds the first index within a String, handling <code>null</code>. This
	 * method uses {@link String#indexOf(int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> or empty ("") String will return
	 * <code>INDEX_NOT_FOUND (-1)</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *)         = -1
	 * StringUtil.indexOf("", *)           = -1
	 * StringUtil.indexOf("aabaabaa", 'a') = 0
	 * StringUtil.indexOf("aabaabaa", 'b') = 2
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchChar
	 *            the character to find
	 * @return the first index of the search character, -1 if no match or
	 *         <code>null</code> string input
	 * @since 2.0
	 */
	public static int indexOf(String str, char searchChar) {
		if (isEmpty(str)) {
			return INDEX_NOT_FOUND;
		}
		return str.indexOf(searchChar);
	}

	/**
	 * <p>
	 * Finds the first index within a String from a start position, handling
	 * <code>null</code>. This method uses {@link String#indexOf(int, int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> or empty ("") String will return
	 * <code>(INDEX_NOT_FOUND) -1</code>. A negative start position is treated
	 * as zero. A start position greater than the string length returns
	 * <code>-1</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *, *)          = -1
	 * StringUtil.indexOf("", *, *)            = -1
	 * StringUtil.indexOf("aabaabaa", 'b', 0)  = 2
	 * StringUtil.indexOf("aabaabaa", 'b', 3)  = 5
	 * StringUtil.indexOf("aabaabaa", 'b', 9)  = -1
	 * StringUtil.indexOf("aabaabaa", 'b', -1) = 2
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchChar
	 *            the character to find
	 * @param startPos
	 *            the start position, negative treated as zero
	 * @return the first index of the search character, -1 if no match or
	 *         <code>null</code> string input
	 * @since 2.0
	 */
	public static int indexOf(String str, char searchChar, int startPos) {
		if (isEmpty(str)) {
			return INDEX_NOT_FOUND;
		}
		return str.indexOf(searchChar, startPos);
	}

	/**
	 * <p>
	 * Finds the first index within a String, handling <code>null</code>. This
	 * method uses {@link String#indexOf(String)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *)          = -1
	 * StringUtil.indexOf(*, null)          = -1
	 * StringUtil.indexOf("", "")           = 0
	 * StringUtil.indexOf("", *)            = -1 (except when * = "")
	 * StringUtil.indexOf("aabaabaa", "a")  = 0
	 * StringUtil.indexOf("aabaabaa", "b")  = 2
	 * StringUtil.indexOf("aabaabaa", "ab") = 1
	 * StringUtil.indexOf("aabaabaa", "")   = 0
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @return the first index of the search String, -1 if no match or
	 *         <code>null</code> string input
	 * @since 2.0
	 */
	public static int indexOf(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		return str.indexOf(searchStr);
	}

	/**
	 * <p>
	 * Finds the n-th index within a String, handling <code>null</code>. This
	 * method uses {@link String#indexOf(String)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.ordinalIndexOf(null, *, *)          = -1
	 * StringUtil.ordinalIndexOf(*, null, *)          = -1
	 * StringUtil.ordinalIndexOf("", "", *)           = 0
	 * StringUtil.ordinalIndexOf("aabaabaa", "a", 1)  = 0
	 * StringUtil.ordinalIndexOf("aabaabaa", "a", 2)  = 1
	 * StringUtil.ordinalIndexOf("aabaabaa", "b", 1)  = 2
	 * StringUtil.ordinalIndexOf("aabaabaa", "b", 2)  = 5
	 * StringUtil.ordinalIndexOf("aabaabaa", "ab", 1) = 1
	 * StringUtil.ordinalIndexOf("aabaabaa", "ab", 2) = 4
	 * StringUtil.ordinalIndexOf("aabaabaa", "", 1)   = 0
	 * StringUtil.ordinalIndexOf("aabaabaa", "", 2)   = 0
	 * </pre>
	 * 
	 * <p>
	 * Note that 'head(String str, int n)' may be implemented as:
	 * </p>
	 * 
	 * <pre>
	 * str.substring(0, lastOrdinalIndexOf(str, &quot;\n&quot;, n))
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @param ordinal
	 *            the n-th <code>searchStr</code> to find
	 * @return the n-th index of the search String, <code>-1</code> (
	 *         <code>INDEX_NOT_FOUND</code>) if no match or <code>null</code>
	 *         string input
	 * @since 2.1
	 */
	public static int ordinalIndexOf(String str, String searchStr, int ordinal) {
		return ordinalIndexOf(str, searchStr, ordinal, false);
	}

	/**
	 * <p>
	 * Finds the n-th index within a String, handling <code>null</code>. This
	 * method uses {@link String#indexOf(String)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>.
	 * </p>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @param ordinal
	 *            the n-th <code>searchStr</code> to find
	 * @param lastIndex
	 *            true if lastOrdinalIndexOf() otherwise false if
	 *            ordinalIndexOf()
	 * @return the n-th index of the search String, <code>-1</code> (
	 *         <code>INDEX_NOT_FOUND</code>) if no match or <code>null</code>
	 *         string input
	 */
	// Shared code between ordinalIndexOf(String,String,int) and
	// lastOrdinalIndexOf(String,String,int)
	private static int ordinalIndexOf(String str, String searchStr, int ordinal, boolean lastIndex) {
		if (str == null || searchStr == null || ordinal <= 0) {
			return INDEX_NOT_FOUND;
		}
		if (searchStr.length() == 0) {
			return lastIndex ? str.length() : 0;
		}
		int found = 0;
		int index = lastIndex ? str.length() : INDEX_NOT_FOUND;
		do {
			if (lastIndex) {
				index = str.lastIndexOf(searchStr, index - 1);
			} else {
				index = str.indexOf(searchStr, index + 1);
			}
			if (index < 0) {
				return index;
			}
			found++;
		} while (found < ordinal);
		return index;
	}

	/**
	 * <p>
	 * Finds the first index within a String, handling <code>null</code>. This
	 * method uses {@link String#indexOf(String, int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>. A negative start
	 * position is treated as zero. An empty ("") search String always matches.
	 * A start position greater than the string length only matches an empty
	 * search String.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *, *)          = -1
	 * StringUtil.indexOf(*, null, *)          = -1
	 * StringUtil.indexOf("", "", 0)           = 0
	 * StringUtil.indexOf("", *, 0)            = -1 (except when * = "")
	 * StringUtil.indexOf("aabaabaa", "a", 0)  = 0
	 * StringUtil.indexOf("aabaabaa", "b", 0)  = 2
	 * StringUtil.indexOf("aabaabaa", "ab", 0) = 1
	 * StringUtil.indexOf("aabaabaa", "b", 3)  = 5
	 * StringUtil.indexOf("aabaabaa", "b", 9)  = -1
	 * StringUtil.indexOf("aabaabaa", "b", -1) = 2
	 * StringUtil.indexOf("aabaabaa", "", 2)   = 2
	 * StringUtil.indexOf("abc", "", 9)        = 3
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @param startPos
	 *            the start position, negative treated as zero
	 * @return the first index of the search String, -1 if no match or
	 *         <code>null</code> string input
	 * @since 2.0
	 */
	public static int indexOf(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		// JDK1.2/JDK1.3 have a bug, when startPos > str.length for "", hence
		if (searchStr.length() == 0 && startPos >= str.length()) {
			return str.length();
		}
		return str.indexOf(searchStr, startPos);
	}

	/**
	 * <p>
	 * Case in-sensitive find of the first index within a String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>. A negative start
	 * position is treated as zero. An empty ("") search String always matches.
	 * A start position greater than the string length only matches an empty
	 * search String.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOfIgnoreCase(null, *)          = -1
	 * StringUtil.indexOfIgnoreCase(*, null)          = -1
	 * StringUtil.indexOfIgnoreCase("", "")           = 0
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "a")  = 0
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "b")  = 2
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "ab") = 1
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @return the first index of the search String, -1 if no match or
	 *         <code>null</code> string input
	 * @since 2.5
	 */
	public static int indexOfIgnoreCase(String str, String searchStr) {
		return indexOfIgnoreCase(str, searchStr, 0);
	}

	/**
	 * <p>
	 * Case in-sensitive find of the first index within a String from the
	 * specified position.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>. A negative start
	 * position is treated as zero. An empty ("") search String always matches.
	 * A start position greater than the string length only matches an empty
	 * search String.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOfIgnoreCase(null, *, *)          = -1
	 * StringUtil.indexOfIgnoreCase(*, null, *)          = -1
	 * StringUtil.indexOfIgnoreCase("", "", 0)           = 0
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "A", 0)  = 0
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "B", 0)  = 2
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "AB", 0) = 1
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "B", 3)  = 5
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "B", 9)  = -1
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "B", -1) = 2
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "", 2)   = 2
	 * StringUtil.indexOfIgnoreCase("abc", "", 9)        = 3
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @param startPos
	 *            the start position, negative treated as zero
	 * @return the first index of the search String, -1 if no match or
	 *         <code>null</code> string input
	 * @since 2.5
	 */
	public static int indexOfIgnoreCase(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		if (startPos < 0) {
			startPos = 0;
		}
		int endLimit = (str.length() - searchStr.length()) + 1;
		if (startPos > endLimit) {
			return INDEX_NOT_FOUND;
		}
		if (searchStr.length() == 0) {
			return startPos;
		}
		for (int i = startPos; i < endLimit; i++) {
			if (str.regionMatches(true, i, searchStr, 0, searchStr.length())) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}

	// LastIndexOf
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Finds the last index within a String, handling <code>null</code>. This
	 * method uses {@link String#lastIndexOf(int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> or empty ("") String will return <code>-1</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *)         = -1
	 * StringUtil.lastIndexOf("", *)           = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'a') = 7
	 * StringUtil.lastIndexOf("aabaabaa", 'b') = 5
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchChar
	 *            the character to find
	 * @return the last index of the search character, -1 if no match or
	 *         <code>null</code> string input
	 * @since 2.0
	 */
	public static int lastIndexOf(String str, char searchChar) {
		if (isEmpty(str)) {
			return INDEX_NOT_FOUND;
		}
		return str.lastIndexOf(searchChar);
	}

	/**
	 * <p>
	 * Finds the last index within a String from a start position, handling
	 * <code>null</code>. This method uses {@link String#lastIndexOf(int, int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> or empty ("") String will return <code>-1</code>. A
	 * negative start position returns <code>-1</code>. A start position greater
	 * than the string length searches the whole string.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *, *)          = -1
	 * StringUtil.lastIndexOf("", *,  *)           = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'b', 8)  = 5
	 * StringUtil.lastIndexOf("aabaabaa", 'b', 4)  = 2
	 * StringUtil.lastIndexOf("aabaabaa", 'b', 0)  = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'b', 9)  = 5
	 * StringUtil.lastIndexOf("aabaabaa", 'b', -1) = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'a', 0)  = 0
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchChar
	 *            the character to find
	 * @param startPos
	 *            the start position
	 * @return the last index of the search character, -1 if no match or
	 *         <code>null</code> string input
	 * @since 2.0
	 */
	public static int lastIndexOf(String str, char searchChar, int startPos) {
		if (isEmpty(str)) {
			return INDEX_NOT_FOUND;
		}
		return str.lastIndexOf(searchChar, startPos);
	}

	/**
	 * <p>
	 * Finds the last index within a String, handling <code>null</code>. This
	 * method uses {@link String#lastIndexOf(String)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *)          = -1
	 * StringUtil.lastIndexOf(*, null)          = -1
	 * StringUtil.lastIndexOf("", "")           = 0
	 * StringUtil.lastIndexOf("aabaabaa", "a")  = 7
	 * StringUtil.lastIndexOf("aabaabaa", "b")  = 5
	 * StringUtil.lastIndexOf("aabaabaa", "ab") = 4
	 * StringUtil.lastIndexOf("aabaabaa", "")   = 8
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @return the last index of the search String, -1 if no match or
	 *         <code>null</code> string input
	 * @since 2.0
	 */
	public static int lastIndexOf(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		return str.lastIndexOf(searchStr);
	}

	/**
	 * <p>
	 * Finds the n-th last index within a String, handling <code>null</code>.
	 * This method uses {@link String#lastIndexOf(String)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastOrdinalIndexOf(null, *, *)          = -1
	 * StringUtil.lastOrdinalIndexOf(*, null, *)          = -1
	 * StringUtil.lastOrdinalIndexOf("", "", *)           = 0
	 * StringUtil.lastOrdinalIndexOf("aabaabaa", "a", 1)  = 7
	 * StringUtil.lastOrdinalIndexOf("aabaabaa", "a", 2)  = 6
	 * StringUtil.lastOrdinalIndexOf("aabaabaa", "b", 1)  = 5
	 * StringUtil.lastOrdinalIndexOf("aabaabaa", "b", 2)  = 2
	 * StringUtil.lastOrdinalIndexOf("aabaabaa", "ab", 1) = 4
	 * StringUtil.lastOrdinalIndexOf("aabaabaa", "ab", 2) = 1
	 * StringUtil.lastOrdinalIndexOf("aabaabaa", "", 1)   = 8
	 * StringUtil.lastOrdinalIndexOf("aabaabaa", "", 2)   = 8
	 * </pre>
	 * 
	 * <p>
	 * Note that 'tail(String str, int n)' may be implemented as:
	 * </p>
	 * 
	 * <pre>
	 * str.substring(lastOrdinalIndexOf(str, &quot;\n&quot;, n) + 1)
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @param ordinal
	 *            the n-th last <code>searchStr</code> to find
	 * @return the n-th last index of the search String, <code>-1</code> (
	 *         <code>INDEX_NOT_FOUND</code>) if no match or <code>null</code>
	 *         string input
	 * @since 2.5
	 */
	public static int lastOrdinalIndexOf(String str, String searchStr, int ordinal) {
		return ordinalIndexOf(str, searchStr, ordinal, true);
	}

	/**
	 * <p>
	 * Finds the first index within a String, handling <code>null</code>. This
	 * method uses {@link String#lastIndexOf(String, int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>. A negative start
	 * position returns <code>-1</code>. An empty ("") search String always
	 * matches unless the start position is negative. A start position greater
	 * than the string length searches the whole string.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *, *)          = -1
	 * StringUtil.lastIndexOf(*, null, *)          = -1
	 * StringUtil.lastIndexOf("aabaabaa", "a", 8)  = 7
	 * StringUtil.lastIndexOf("aabaabaa", "b", 8)  = 5
	 * StringUtil.lastIndexOf("aabaabaa", "ab", 8) = 4
	 * StringUtil.lastIndexOf("aabaabaa", "b", 9)  = 5
	 * StringUtil.lastIndexOf("aabaabaa", "b", -1) = -1
	 * StringUtil.lastIndexOf("aabaabaa", "a", 0)  = 0
	 * StringUtil.lastIndexOf("aabaabaa", "b", 0)  = -1
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @param startPos
	 *            the start position, negative treated as zero
	 * @return the first index of the search String, -1 if no match or
	 *         <code>null</code> string input
	 * @since 2.0
	 */
	public static int lastIndexOf(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		return str.lastIndexOf(searchStr, startPos);
	}

	/**
	 * <p>
	 * Case in-sensitive find of the last index within a String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>. A negative start
	 * position returns <code>-1</code>. An empty ("") search String always
	 * matches unless the start position is negative. A start position greater
	 * than the string length searches the whole string.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOfIgnoreCase(null, *)          = -1
	 * StringUtil.lastIndexOfIgnoreCase(*, null)          = -1
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "A")  = 7
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B")  = 5
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "AB") = 4
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @return the first index of the search String, -1 if no match or
	 *         <code>null</code> string input
	 * @since 2.5
	 */
	public static int lastIndexOfIgnoreCase(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		return lastIndexOfIgnoreCase(str, searchStr, str.length());
	}

	/**
	 * <p>
	 * Case in-sensitive find of the last index within a String from the
	 * specified position.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>. A negative start
	 * position returns <code>-1</code>. An empty ("") search String always
	 * matches unless the start position is negative. A start position greater
	 * than the string length searches the whole string.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOfIgnoreCase(null, *, *)          = -1
	 * StringUtil.lastIndexOfIgnoreCase(*, null, *)          = -1
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "A", 8)  = 7
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", 8)  = 5
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "AB", 8) = 4
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", 9)  = 5
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", -1) = -1
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "A", 0)  = 0
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", 0)  = -1
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @param startPos
	 *            the start position
	 * @return the first index of the search String, -1 if no match or
	 *         <code>null</code> string input
	 * @since 2.5
	 */
	public static int lastIndexOfIgnoreCase(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		if (startPos > (str.length() - searchStr.length())) {
			startPos = str.length() - searchStr.length();
		}
		if (startPos < 0) {
			return INDEX_NOT_FOUND;
		}
		if (searchStr.length() == 0) {
			return startPos;
		}
		for (int i = startPos; i >= 0; i--) {
			if (str.regionMatches(true, i, searchStr, 0, searchStr.length())) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}

	// Contains
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if String contains a search character, handling <code>null</code>.
	 * This method uses {@link String#indexOf(int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> or empty ("") String will return <code>false</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.contains(null, *)    = false
	 * StringUtil.contains("", *)      = false
	 * StringUtil.contains("abc", 'a') = true
	 * StringUtil.contains("abc", 'z') = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchChar
	 *            the character to find
	 * @return true if the String contains the search character, false if not or
	 *         <code>null</code> string input
	 * @since 2.0
	 */
	public static boolean contains(String str, char searchChar) {
		if (isEmpty(str)) {
			return false;
		}
		return str.indexOf(searchChar) >= 0;
	}

	/**
	 * <p>
	 * Checks if String contains a search String, handling <code>null</code>.
	 * This method uses {@link String#indexOf(String)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>false</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.contains(null, *)     = false
	 * StringUtil.contains(*, null)     = false
	 * StringUtil.contains("", "")      = true
	 * StringUtil.contains("abc", "")   = true
	 * StringUtil.contains("abc", "a")  = true
	 * StringUtil.contains("abc", "z")  = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @return true if the String contains the search String, false if not or
	 *         <code>null</code> string input
	 * @since 2.0
	 */
	public static boolean contains(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return false;
		}
		return str.indexOf(searchStr) >= 0;
	}

	/**
	 * <p>
	 * Checks if String contains a search String irrespective of case, handling
	 * <code>null</code>. Case-insensitivity is defined as by
	 * {@link String#equalsIgnoreCase(String)}.
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>false</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.contains(null, *) = false
	 * StringUtil.contains(*, null) = false
	 * StringUtil.contains("", "") = true
	 * StringUtil.contains("abc", "") = true
	 * StringUtil.contains("abc", "a") = true
	 * StringUtil.contains("abc", "z") = false
	 * StringUtil.contains("abc", "A") = true
	 * StringUtil.contains("abc", "Z") = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param searchStr
	 *            the String to find, may be null
	 * @return true if the String contains the search String irrespective of
	 *         case or false if not or <code>null</code> string input
	 */
	public static boolean containsIgnoreCase(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return false;
		}
		int len = searchStr.length();
		int max = str.length() - len;
		for (int i = 0; i <= max; i++) {
			if (str.regionMatches(true, i, searchStr, 0, len)) {
				return true;
			}
		}
		return false;
	}

	// Substring
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Gets a substring from the specified String avoiding exceptions.
	 * </p>
	 * 
	 * <p>
	 * A negative start position can be used to start <code>n</code> characters
	 * from the end of the String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>null</code>. An empty ("")
	 * String will return "".
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.substring(null, *)   = null
	 * StringUtil.substring("", *)     = ""
	 * StringUtil.substring("abc", 0)  = "abc"
	 * StringUtil.substring("abc", 2)  = "c"
	 * StringUtil.substring("abc", 4)  = ""
	 * StringUtil.substring("abc", -2) = "bc"
	 * StringUtil.substring("abc", -4) = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to get the substring from, may be null
	 * @param start
	 *            the position to start from, negative means count back from the
	 *            end of the String by this many characters
	 * @return substring from start position, <code>null</code> if null String
	 *         input
	 */
	public static String substring(String str, int start) {
		if (str == null) {
			return null;
		}
		// handle negatives, which means last n characters
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}
		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return EMPTY;
		}
		return str.substring(start);
	}

	/**
	 * <p>
	 * Gets a substring from the specified String avoiding exceptions.
	 * </p>
	 * 
	 * <p>
	 * A negative start position can be used to start/end <code>n</code>
	 * characters from the end of the String.
	 * </p>
	 * 
	 * <p>
	 * The returned substring starts with the character in the
	 * <code>start</code> position and ends before the <code>end</code>
	 * position. All position counting is zero-based -- i.e., to start at the
	 * beginning of the string use <code>start = 0</code>. Negative start and
	 * end positions can be used to specify offsets relative to the end of the
	 * String.
	 * </p>
	 * 
	 * <p>
	 * If <code>start</code> is not strictly to the left of <code>end</code>, ""
	 * is returned.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.substring(null, *, *)    = null
	 * StringUtil.substring("", * ,  *)    = "";
	 * StringUtil.substring("abc", 0, 2)   = "ab"
	 * StringUtil.substring("abc", 2, 0)   = ""
	 * StringUtil.substring("abc", 2, 4)   = "c"
	 * StringUtil.substring("abc", 4, 6)   = ""
	 * StringUtil.substring("abc", 2, 2)   = ""
	 * StringUtil.substring("abc", -2, -1) = "b"
	 * StringUtil.substring("abc", -4, 2)  = "ab"
	 * </pre>
	 * 
	 * @param str
	 *            the String to get the substring from, may be null
	 * @param start
	 *            the position to start from, negative means count back from the
	 *            end of the String by this many characters
	 * @param end
	 *            the position to end at (exclusive), negative means count back
	 *            from the end of the String by this many characters
	 * @return substring from start position to end positon, <code>null</code>
	 *         if null String input
	 */
	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}
		// handle negatives
		if (end < 0) {
			end = str.length() + end; // remember end is negative
		}
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}
		// check length next
		if (end > str.length()) {
			end = str.length();
		}
		// if start is greater than end, return ""
		if (start > end) {
			return EMPTY;
		}
		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}
		return str.substring(start, end);
	}

	// Defaults
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Returns either the passed in String, or if the String is
	 * <code>null</code>, an empty String ("").
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.defaultString(null)  = ""
	 * StringUtil.defaultString("")    = ""
	 * StringUtil.defaultString("bat") = "bat"
	 * </pre>
	 * 
	 * @see ObjectUtils#toString(Object)
	 * @see String#valueOf(Object)
	 * @param str
	 *            the String to check, may be null
	 * @return the passed in String, or the empty String if it was
	 *         <code>null</code>
	 */
	public static String defaultString(String str) {
		return str == null ? EMPTY : str;
	}

	/**
	 * <p>
	 * Returns either the passed in String, or if the String is
	 * <code>null</code>, the value of <code>defaultStr</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.defaultString(null, "NULL")  = "NULL"
	 * StringUtil.defaultString("", "NULL")    = ""
	 * StringUtil.defaultString("bat", "NULL") = "bat"
	 * </pre>
	 * 
	 * @see ObjectUtils#toString(Object,String)
	 * @see String#valueOf(Object)
	 * @param str
	 *            the String to check, may be null
	 * @param defaultStr
	 *            the default String to return if the input is <code>null</code>
	 *            , may be null
	 * @return the passed in String, or the default if it was <code>null</code>
	 */
	public static String defaultString(String str, String defaultStr) {
		return str == null ? defaultStr : str;
	}

	/**
	 * <p>
	 * Returns either the passed in String, or if the String is whitespace,
	 * empty ("") or <code>null</code>, the value of <code>defaultStr</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.defaultIfBlank(null, "NULL")  = "NULL"
	 * StringUtil.defaultIfBlank("", "NULL")    = "NULL"
	 * StringUtil.defaultIfBlank(" ", "NULL")   = "NULL"
	 * StringUtil.defaultIfBlank("bat", "NULL") = "bat"
	 * StringUtil.defaultIfBlank("", null)      = null
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param defaultStr
	 *            the default String to return if the input is whitespace, empty
	 *            ("") or <code>null</code>, may be null
	 * @return the passed in String, or the default
	 * @see StringUtil#defaultString(String, String)
	 * @since 2.6
	 */
	public static String defaultIfBlank(String str, String defaultStr) {
		return StringUtil.isBlank(str) ? defaultStr : str;
	}

	/**
	 * <p>
	 * Returns either the passed in String, or if the String is empty or
	 * <code>null</code>, the value of <code>defaultStr</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.defaultIfEmpty(null, "NULL")  = "NULL"
	 * StringUtil.defaultIfEmpty("", "NULL")    = "NULL"
	 * StringUtil.defaultIfEmpty("bat", "NULL") = "bat"
	 * StringUtil.defaultIfEmpty("", null)      = null
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param defaultStr
	 *            the default String to return if the input is empty ("") or
	 *            <code>null</code>, may be null
	 * @return the passed in String, or the default
	 * @see StringUtil#defaultString(String, String)
	 */
	public static String defaultIfEmpty(String str, String defaultStr) {
		return StringUtil.isEmpty(str) ? defaultStr : str;
	}
}