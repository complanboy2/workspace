package parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import knowledgebase.CreateKnowledgeBase;
import knowledgebase.Triplet;

/**
 * this class contains all the util methods required for a query parsing
 * 
 * @author <a href="mailto:complanboy2@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since March 25, 2014
 */
public class ParserUtils {

	/**
	 * evalute the star case
	 * 
	 * @param queries
	 * @param variables
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void evaluateStarCase(List<Triplet> queries, Map<String, List<String>> variables)
			throws FileNotFoundException, IOException {
		if (variables.size() == 1 && variables.get("*") != null) {
			CreateKnowledgeBase ckb = CreateKnowledgeBase.getInstance();
			int size = ckb.size();
			List<String> fromList = new ArrayList<String>(size);
			List<String> edgeList = new ArrayList<String>(size);
			List<String> toList = new ArrayList<String>(size);

			for (Triplet q : ckb.getEdgesList()) {
				fromList.add(q.fromVertex);
				edgeList.add(q.edge);
				toList.add(q.toVertex);
			}
			variables.remove("*");
			variables.put(queries.get(0).fromVertex, fromList);
			variables.put(queries.get(0).edge, edgeList);
			variables.put(queries.get(0).toVertex, toList);
		}
	}

	/**
	 * get fromlist corresponding to the tolist (if any)
	 * 
	 * @param query
	 * @param edgeResult
	 * @param variables
	 */
	public static void fromList(Triplet query, List<Triplet> edgeResult, Map<String, List<String>> variables) {
		List<String> toList = new ArrayList<String>();
		if (query.toVertex.startsWith("?")) {
			toList = variables.get(query.toVertex);
		} else {
			toList.add(query.toVertex);
		}
		List<String> fromList = variables.get(query.fromVertex);
		if (Utils.isEmpty(fromList)) {
			getFromList(edgeResult, query, toList, variables);
		}
	}

	/**
	 * Get the fromList corresponding to the tolist (if any)
	 * 
	 * @param mainList
	 * @param triplet
	 * @param toList
	 * @return
	 */
	private static final void getFromList(List<Triplet> mainList, Triplet triplet, List<String> toList,
			Map<String, List<String>> variables) {
		List<String> fromList = new ArrayList<String>(mainList.size());
		if (Utils.isEmpty(toList)) {
			for (Triplet q : mainList) {
				fromList.add(q.fromVertex);
			}
		}

		for (int j = 0; j < mainList.size(); j++) {
			for (int i = 0; i < toList.size(); i++) {
				Triplet query = mainList.get(j);
				if (query.toVertex.equalsIgnoreCase(toList.get(i))) {
					fromList.add(query.fromVertex);
					break;
				}
			}
		}

		if (fromList != null && fromList.size() > 0) {
			variables.put(triplet.fromVertex, fromList);
		}
	}

	/**
	 * Get the to list corresponding to the from list
	 * 
	 * @param query
	 * @param edgeResult
	 * @param variables
	 */
	public static void toList(Triplet query, List<Triplet> edgeResult, Map<String, List<String>> variables) {
		List<String> fromList = new ArrayList<String>();
		if (query.fromVertex.startsWith("?")) {
			fromList = variables.get(query.fromVertex);
		} else {
			fromList.add(query.fromVertex);
		}
		List<String> toList = variables.get(query.toVertex);
		if (Utils.isEmpty(toList)) {
			getToList(edgeResult, query, fromList, variables);
		}
	}

	/**
	 * Get the to list corresponding to the from list
	 * 
	 * @param mainList
	 * @param in
	 * @param fromList
	 * @return
	 */
	private static final void getToList(List<Triplet> mainList, Triplet in, List<String> fromList,
			Map<String, List<String>> variables) {
		List<String> toList = new ArrayList<String>(mainList.size());
		if (Utils.isEmpty(fromList)) {
			for (Triplet q : mainList) {
				toList.add(q.toVertex);
			}
		}

		List<String> resultListWithOrder = new ArrayList<String>(fromList.size());
		for (int j = 0; j < mainList.size(); j++) {
			for (int i = 0; i < fromList.size(); i++) {
				Triplet query = mainList.get(j);
				if (query.fromVertex.equalsIgnoreCase(fromList.get(i))) {
					resultListWithOrder.add(query.fromVertex);
					toList.add(query.toVertex);
					break;
				}
			}
		}
		if (in.fromVertex.startsWith("?")) {
			variables.put(in.fromVertex, resultListWithOrder);
		}
		variables.put(in.toVertex, toList);
	}

	/**
	 * verify whether the current triplet is a composite triple A triplet is
	 * composite if it requires a further operation on non-empty from and to
	 * lists
	 * 
	 * @param triplet
	 * @param variables
	 * @return
	 */
	public static boolean isCompositeTriplet(Triplet triplet, Map<String, List<String>> variables) {
		if (triplet == null) {
			return false;
		}
		if (triplet.fromVertex.startsWith("?") && triplet.toVertex.startsWith("?")) {
				
			if (Utils.isEmpty(variables.get(triplet.fromVertex)))
				return false;
			if (Utils.isEmpty(variables.get(triplet.toVertex)))
				return false;
		}

		return true;
	}

	/**
	 * Modify the to and from lists accordingly
	 * 
	 * @param mainList
	 * @param triplet
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static final void getComposeList(Map<String, List<String>> variables, List<Triplet> mainList, Triplet triplet)
			throws FileNotFoundException, IOException {
		List<String> fromList = variables.get(triplet.fromVertex);
		List<String> toList = variables.get(triplet.toVertex);

		if (Utils.isEmpty(fromList)) {
			fromList = new ArrayList<String>();
			fromList.add(triplet.fromVertex);
		}
		if (Utils.isEmpty(toList)) {
			toList = new ArrayList<String>();
			toList.add(triplet.toVertex);
		}

		List<Triplet> result = QueryParseUtils.getQueriesForEdge(triplet.edge);
		if (toList.size() == 1) {
			List<String> fromResult = new ArrayList<String>(result.size());
			for (Triplet q : result) {
				if (q.toVertex.equalsIgnoreCase(toList.get(0))) {
					fromResult.add(q.fromVertex);
				}
			}
			fromList.retainAll(fromResult);
		} else if (fromList.size() == 1) {
			List<String> toResult = new ArrayList<String>(result.size());
			for (Triplet q : result) {
				if (q.fromVertex.equalsIgnoreCase(fromList.get(0))) {
					toResult.add(q.toVertex);
				}
			}
			fromList.retainAll(toResult);
		}
	}
}
