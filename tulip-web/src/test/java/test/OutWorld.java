package test;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutWorld {

	public static void main(String[] args) throws Exception {
		LineNumberReader reader = null;
		PrintWriter out = null;
		try {
			reader = new LineNumberReader(new FileReader("/Users/feiliu/dev/ipdata/ipmap_2014_78910.txt"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/feiliu/dev/ipdata/ip_2014_78910.txt")));
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] fs = splitWorker(line, ",", -1, false);
				if(fs == null || fs.length <= 0) {
					continue;
				}
				Ip ip = null;
				try {
					ip = new Ip(fs[1]);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				if(!ip.success) {
					continue;
				}
				if(ip.check(new Ip.CheckIP() {
					@Override
					public boolean check(Ip ip) {
						if(
								search(ip.field_1, new int[]{51, 52, 53, 54, 55, 56, 57, 62, 63, 142, 151, 200, 201, 204, 205, 206, 207, 208, 209})
								|| (ip.field_1 == 64 && between(ip.field_2, 0, 210) && between(ip.field_3, 99, 248))
								|| (ip.field_1 == 65 && ip.field_2 == 160)
								|| (ip.field_1 == 66 && ip.field_2 == 27)
								|| (ip.field_1 == 130 && ip.field_2 == 34 && ip.field_3 == 195)
								|| (ip.field_1 == 130 && ip.field_2 == 39)
								|| (ip.field_1 == 130 && ip.field_2 == 88 && between(ip.field_3, 1, 255))
								|| (ip.field_1 == 131 && ip.field_2 == 107)
								|| (ip.field_1 == 131 && ip.field_2 == 230)
								|| (ip.field_1 == 131 && ip.field_2 == 246)
								|| (ip.field_1 == 134 && ip.field_2 == 208)
								|| (ip.field_1 == 137 && search(ip.field_2, new int[]{132, 226}))
								|| (ip.field_1 == 141 && ip.field_2 == 44)
								|| (ip.field_1 == 143 && ip.field_2 == 90 && ip.field_3 == 209)
								|| (ip.field_1 == 144 && ((ip.field_2 == 92) || (ip.field_2 == 132 && ip.field_3 == 99)))
								|| (ip.field_1 == 150 && ip.field_2 == 204)
								|| (ip.field_1 == 152 && ip.field_2 == 174 && ip.field_3 == 114)
								|| (ip.field_1 == 153 && ip.field_2 == 91 && ip.field_3 == 120)
								|| (ip.field_1 == 154 && ip.field_2 == 5)
								|| (ip.field_1 == 155 && ip.field_2 == 69)
								|| (ip.field_1 == 161 && (search(ip.field_2, new int[]{53, 58, 142})))
								|| (ip.field_1 == 162 && ip.field_2 == 42)
								|| (ip.field_1 == 165 && (search(ip.field_2, new int[]{21, 76, 194, 246})))
								|| (ip.field_1 == 171 && between(ip.field_2, 208, 220))
								|| (ip.field_1 == 172 && ((ip.field_2 == 161 && ip.field_3 == 213) || (ip.field_2 == 183 && ip.field_3 == 102)))
								|| (ip.field_1 == 203 && 
										(
											(search(ip.field_2, new int[]{87, 105, 106, 107, 96, 115, 128, 130, 131, 134, 142, 145, 146, 152, 163}) && between(ip.field_3, 0, 63)) || 
											(ip.field_2 == 88 && between(ip.field_3, 64, 255)) || 
											(search(ip.field_2, new int[]{78, 109, 112, 129, 167}) && (between(ip.field_3, 0, 31) || between(ip.field_3, 128, 159) || between(ip.field_3, 224, 255) || between(ip.field_3, 31, 192))) || 
											(search(ip.field_2, new int[]{77, 98, 131, 142, 152, 160}) && between(ip.field_3, 224, 255)) || 
											(search(ip.field_2, new int[]{160}) && between(ip.field_3, 0, 223)) || 
											search(ip.field_2, new int[]{216, 197, 76, 89, 91, 92, 94, 98, 99, 100, 101, 102, 103, 104, 108, 110, 111, 132, 153, 161, 162, 166}) || 
											between(ip.field_2, 0, 63) || 
											between(ip.field_2, 116, 127) ||
											between(ip.field_2, 136, 141) ||
											between(ip.field_2, 148, 151) ||
											between(ip.field_2, 154, 159) ||
											between(ip.field_2, 168, 177) ||
											between(ip.field_2, 178, 183) ||
											between(ip.field_2, 188, 190) ||
											between(ip.field_2, 214, 255) ||
											(ip.field_2 == 121 && between(ip.field_3, 4, 25)) ||
											(search(ip.field_2, new int[]{130, 133, 144, 147, 167}) && between(ip.field_3, 192, 255)) || 
											(search(ip.field_2, new int[]{143}) && between(ip.field_3, 0, 31)) ||
											(search(ip.field_2, new int[]{185}) && between(ip.field_3, 64, 159)) ||
											(search(ip.field_2, new int[]{186}) && between(ip.field_3, 0, 95)) ||
											(search(ip.field_2, new int[]{188}) && between(ip.field_3, 128, 225)) 
										)
									)
								|| (ip.field_1 == 211 && (between(ip.field_2, 8, 19) || between(ip.field_2, 32, 51)))
								
								) {
							return true;
						}
						return false;
					}
				})) {
					line += "Y\n";
					out.write(line);
					continue;
				}
				line += "N\n";
				out.write(line);
				//System.err.println(line);
			}
			System.err.println("OK!!!!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				reader.close();
				reader = null;
			}
			if(out != null) {
				out.flush();
				out.close();
				out = null;
			}
		}
	}
	
	private static boolean search(int src, int[] arr) {
		Arrays.sort(arr);
		return Arrays.binarySearch(arr, src) >= 0;
	}
	
	private static boolean between(int src, int min, int max) {
		return src >= min && src <= max;
	}
	
	public static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)
        // Direct code is quicker than StringTokenizer.
        // Also, StringTokenizer uses isSpace() not isWhitespace()

        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return new String[0];
        }
        List list = new ArrayList();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || (preserveAllTokens && lastMatch)) {
            list.add(str.substring(start, i));
        }
        return (String[]) list.toArray(new String[list.size()]);
    }
}

class Ip {
	public int field_1;
	public int field_2;
	public int field_3;
	public int field_4;
	
	public boolean success = false;
	
	public Ip(String ip) {
		String[] fs = OutWorld.splitWorker(ip, ".", -1, false);
		if(fs == null || fs.length != 4) {
			return;
		}
		field_1 = parse(fs[0], 0);
		field_2 = parse(fs[1], 0);
		field_3 = parse(fs[2], 0);
		field_4 = parse(fs[3], 0);
		success = true;
	}
	
	private int parse(String str, int defaultVaue) {
		if(str == null || str.isEmpty()) {
			return defaultVaue;
		}
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return defaultVaue;
	}
	
	public boolean check(CheckIP check) {
		return check.check(this);
	}
	
	interface CheckIP {
		boolean check(Ip ip);
	}
}

