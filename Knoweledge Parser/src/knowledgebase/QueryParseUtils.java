package knowledgebase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parser.IConstants;
import parser.Utils;
import knowledgebase.CreateKnowledgeBase;
import knowledgebase.Triplet;

/**
 * this class contains util methods for query processing
 * 
 * @author <a href="mailto:complanboy2@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since March 25, 2014
 */
public class QueryParseUtils {

	/**
	 * read all the select values from the given query
	 * 
	 * @param query
	 * @return
	 */
	public static List<String> getSelectValues(String query) {
		String[] selectValues = Utils
				.substringBetweenIgnoreCase(query, IConstants.QUERY_PARAMS_SELECT, IConstants.QUERY_PARAMS_WHERE)
				.trim().split("\\ ");

		return Arrays.asList(selectValues);
	}

	/**
	 * remove any special symbols from the input query
	 * 
	 * @param string
	 * @return
	 */
	public static String removeSpecials(String string) {
		if (string == null || string.length() == 0) {
			return string;
		}
		if (string.startsWith("?")) {
			string = string.substring(1, string.length());
		}

		return string;
	}

	/**
	 * read the query and get all the triplets
	 * 
	 * @param query
	 * @return
	 */
	public static List<Triplet> getWhereValues(String query) {
		String whereStr = Utils.substringBetween(query, "{", "}");
		String triplets[] = whereStr.trim().split("\\.");
		List<Triplet> where = new ArrayList<Triplet>(triplets.length);

		for (String triplet : triplets) {
			where.add(new Triplet(triplet.trim()));
		}
		return where;
	}

	/**
	 * write the "parse error" message to the output file
	 * 
	 * @param outputFile
	 * @throws IOException
	 */
	public static void parseError(String outputFile) throws IOException {
		Utils.writeError(outputFile, "Parse Error");
	}

	/**
	 * Get the list of queries that match an edge
	 * 
	 * @param edge
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static List<Triplet> getQueriesForEdge(String edge) throws FileNotFoundException, IOException {
		List<Triplet> resultList = new ArrayList<Triplet>();
		List<Triplet> edgesList = CreateKnowledgeBase.getInstance().getEdgesList();

		for (Triplet q : edgesList) {
			if (edge.equalsIgnoreCase(q.edge)) {
				resultList.add(q);
			}
		}
		return resultList;
	}

	/**
	 * checks whether it is a valid memeber of the triplet. this rules might
	 * change.
	 * 
	 * @param fromVertex
	 * @return
	 */
	public static boolean isValidString(String fromVertex) {
		if (fromVertex == null || fromVertex.isEmpty()) {
			return false;
		}

		fromVertex = fromVertex.trim();
		if (fromVertex.length() < 2) {
			return false;
		}

		return true;
	}
}
