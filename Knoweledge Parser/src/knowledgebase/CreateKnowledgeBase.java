package knowledgebase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import parser.IConstants;
import parser.Utils;

/**
 * This is a base class to store the knowledge base
 * 
 * @author <a href="mailto:complanboy2@gmail.com">G.V.Sekhar</a>
 * @version $Revision$, $Date$, $Author$.
 * @since March 25, 2014
 */
public class CreateKnowledgeBase {

	// static instance for the knoweldge base class
	private static CreateKnowledgeBase instance = null;

	// private constuctor to make sure one instance
	private CreateKnowledgeBase(String inputFile) throws FileNotFoundException, IOException {
		buildKnowledgeBase(inputFile);
	}

	// init method to intialize the KnoweldgeBase
	synchronized public final static void init(String inputFile) throws FileNotFoundException, IOException {
		instance = new CreateKnowledgeBase(inputFile);
	}

	// method to get the knoweldge base when ever requied
	synchronized public final static CreateKnowledgeBase getInstance() throws FileNotFoundException, IOException {
		if (instance == null) {
			init(IConstants.INPUT_FILE);
		}
		return instance;
	}

	private List<Triplet> edgesList = new ArrayList<Triplet>();

	public int size() {
		return edgesList.size();
	}

	public List<Triplet> getEdgesList() {
		return edgesList;
	}

	/**
	 * read the knowledge base from the input file and build a structure
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void buildKnowledgeBase(String fileName) throws FileNotFoundException, IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String values[] = Utils.removeEnds(line).split(",");
				String fromVertex = Utils.removeEnds(values[0].trim());
				String edge = Utils.removeEnds(values[1].trim());
				String toVertex = Utils.removeEnds(values[2].trim());
				
				edgesList.add(new Triplet(fromVertex, edge, toVertex));
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
}
