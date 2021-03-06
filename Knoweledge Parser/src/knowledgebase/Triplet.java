package knowledgebase;

import parser.Utils;

/**
 * data holder for the triplet
 * 
 * @author <a href="mailto:complanboy2@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since March 25, 2014
 */
public class Triplet {
	public String fromVertex;
	public String edge;
	public String toVertex;

	public Triplet(String fromVertex, String edge, String toVertex) {
		super();
		this.fromVertex = fromVertex;
		this.edge = edge;
		this.toVertex = toVertex;
	}

	public Triplet(String fromVertex, String toVertex) {
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
	}

	public Triplet(String triplet) {
		String[] values = triplet.split("\\ ");
		String[] mains = new String[3];

		for (int i = 0, j = 0; i < values.length; i++) {
			if (! QueryParseUtils.isValidString(values[i])) {
				continue;
			}
			mains[j++] = values[i];
		}

		this.fromVertex = Utils.removeEnds(mains[0].trim()); 
		this.edge = Utils.removeEnds(mains[1].trim());
		this.toVertex = Utils.removeEnds(mains[2].trim());
	}
}
