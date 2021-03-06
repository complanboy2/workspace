package parser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import knowledgebase.Triplet;

/**
 * main class for the parsing.
 * 
 * @author <a href="mailto:complanboy2@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since March 25, 2014
 */
public class Parser {

	private static Map<String, List<String>> variables;

	/**
	 * This method will parse the input query and returns the result in an CSV
	 * file with first row as variable names and next rows as binding of these
	 * variables.
	 * 
	 * @throws IOException
	 */
	public static void parse(String givenQuery, String outputFile) throws IOException {
		outputFile = IConstants.OUPUT_FILE_DIRECTORY + outputFile;

		try {
			List<String> selectValues = QueryParseUtils.getSelectValues(givenQuery);
			variables = new LinkedHashMap<String, List<String>>(selectValues.size());
			for (String string : selectValues) {
				variables.put(string, new ArrayList<String>());
			}

			List<Triplet> queries = QueryParseUtils.getWhereValues(givenQuery);
			if (queries == null || queries.size() == 0) {
				QueryParseUtils.parseError(outputFile);
				System.exit(0);
			}

			for (Triplet query : queries) {
				if (query.edge.startsWith("?")) {
					ParserUtils.evaluateStarCase(queries, variables);
					continue;
				}
				List<Triplet> edgeResult = QueryParseUtils.getQueriesForEdge(query.edge);
				getQueryResult(query, edgeResult);
			}

			Utils.retainOnlySelect(variables, selectValues);
			Utils.writeToFile(outputFile, variables);
		} catch (Exception e) {
			QueryParseUtils.parseError(outputFile);
		}
	}

	/**
	 * get the result of the given query
	 * 
	 * @param query
	 * @param edgeResult
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void getQueryResult(Triplet query, List<Triplet> edgeResult) throws FileNotFoundException,
			IOException {
		if (query.fromVertex.startsWith("?")) {
			ParserUtils.fromList(query, edgeResult, variables);
		}
		if (query.toVertex.startsWith("?")) {
			ParserUtils.toList(query, edgeResult, variables);
		}
		if (ParserUtils.isCompositeTriplet(query, variables)) {
			ParserUtils.getComposeList(variables, edgeResult, query);
		}
	}
}
