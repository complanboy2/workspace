package parser;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * conatins general util methods
 * 
 * @author <a href="mailto:complanboy2@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since March 25, 2014
 */
public class Utils {

	/**
	 * Writes to a csv file row wise
	 * 
	 * @throws IOException
	 */
	public static void writeToFile(String outputFile, Map<String, List<String>> variables) throws IOException {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(outputFile));

			// write key set first
			String outputValue = getKeySetAsString(variables, ",");
			bw.append(outputValue);
			bw.newLine();

			// go for values in the row wise
			writeValuesAsString(variables, bw);
		} finally {
			if (bw != null) {
				bw.close();
			}
		}
	}
	
	/**
	 * writes the given error message to the output file
	 * 
	 * @param errorMessage
	 * @throws IOException
	 */
	public static void writeError(String outputFile, String errorMessage) throws IOException {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(outputFile));
			bw.append(errorMessage);
		} finally {
			if (bw != null) {
				bw.close();
			}
		}
	}

	/**
	 * removes the special chars at the end of the given string
	 * 
	 * @param s
	 * @return
	 */
	public static String removeEnds(String s) {
		if (s == null || (s = s.trim()).isEmpty()) {
			return null;
		}

		if (s.startsWith("<") || s.startsWith("(") || s.startsWith("\"")) {
			s = s.substring(1, s.length());
		}

		if (s.endsWith(">") || s.endsWith(")") || s.endsWith("\"")) {
			s = s.substring(0, s.length() - 1);
		}

		return s;
	}

	/**
	 * checks whether the given list is empty or not
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List<?> list) {
		return list == null || list.size() == 0;
	}

	/**
	 * gives the substring between two given strings from the original string,
	 * it ignores case while searching
	 * 
	 * @param str
	 * @param open
	 * @param close
	 * @return
	 */
	public static String substringBetweenIgnoreCase(final String str, final String open, final String close) {
		if (str == null || open == null || close == null) {
			return null;
		}
		final int INDEX_NOT_FOUND = -1;

		final int start = indexOfIgnoreCase(str, open);
		if (start != INDEX_NOT_FOUND) {
			final int end = indexOfIgnoreCase(str, close, start + open.length());
			if (end != INDEX_NOT_FOUND) {
				return str.substring(start + open.length(), end);
			}
		}
		return null;
	}

	/**
	 * gives the substring between two given strings from the original string
	 * 
	 * @param str
	 * @param open
	 * @param close
	 * @return
	 */
	public static String substringBetween(final String str, final String open, final String close) {
		if (str == null || open == null || close == null) {
			return null;
		}
		final int INDEX_NOT_FOUND = -1;

		final int start = indexOfIgnoreCase(str, open);
		if (start != INDEX_NOT_FOUND) {
			final int end = indexOfIgnoreCase(str, close, start + open.length());
			if (end != INDEX_NOT_FOUND) {
				return str.substring(start + open.length(), end);
			}
		}
		return null;
	}

	/**
	 * gives the index of the given string in the original string, ignores the
	 * case
	 * 
	 * @param strs
	 * @param text
	 * @return
	 */
	public static int indexOfIgnoreCase(String strs, String text) {
		return indexOfIgnoreCase(strs, text, 0);
	}

	/**
	 * gives the index of the given string in the original string
	 * 
	 * @param strs
	 * @param text
	 * @param fromIndex
	 * @return
	 */
	public static int indexOfIgnoreCase(String strs, String text, int fromIndex) {
		strs = strs.toLowerCase();
		text = text.toLowerCase();

		return strs.indexOf(text, fromIndex);
	}

	/**
	 * it retains only select values from the variables map
	 * 
	 * @param variables
	 * @param selectValues
	 */
	public static void retainOnlySelect(Map<String, List<String>> variables, List<String> selectValues) {
		if("*".equals(selectValues.get(0))) {
			return;
		}
		for (String string : variables.keySet()) {
			if (!selectValues.contains(string)) {
				variables.remove(string);
			}
		}
	}
	
	/**
	 * get the key set as string as given string seperated
	 * 
	 * @param variables
	 * @return
	 */
	private static String getKeySetAsString(Map<String, List<String>> variables, String ch) {
		String outputValue = "";
		for (String key : variables.keySet()) {
			outputValue += QueryParseUtils.removeSpecials(key) + ch;
		}
		if (outputValue != null && outputValue.endsWith(ch)) {
			outputValue = outputValue.substring(0, outputValue.length() - 1);
		}
		return outputValue;
	}
	
	/**
	 * write values as string to file
	 * @param variables
	 * @param bw
	 * @throws IOException
	 */
	private static void writeValuesAsString(Map<String, List<String>> variables, BufferedWriter bw) throws IOException {
		String outputValue;
		List<List<String>> newset = new ArrayList<List<String>>();
		for (List<String> values : variables.values()) {
			newset.add(values);
		}

		for (int i = 0, j = 0; i < newset.get(0).size(); i++) {
			outputValue = "";
			for (int k = 0; k < newset.size(); k++) {
				List<String> list = newset.get(k);
				if (j < list.size()) {
					String string = list.get(j);
					if (string == null || string.isEmpty()) {
						continue;
					}
					outputValue += string + ",";
				}
			}
			j++;
			if (outputValue != null && outputValue.endsWith(",")) {
				outputValue = outputValue.substring(0, outputValue.length() - 1);
			}
			bw.append(outputValue);
			bw.newLine();
		}
	}
}
