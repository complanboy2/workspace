package parser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class testParser implements Serializable {
	private static final long serialVersionUID = 1L;

	static int flag = 0;

	private static int CompareFile(String expectedResult, String producedResult) throws Exception {

		File f1 = new File(IConstants.EXPECTED_RESULTS_FILE_DIRECTORY + expectedResult);
		File f2 = new File(IConstants.OUPUT_FILE_DIRECTORY + producedResult);

		FileReader fR1 = new FileReader(f1);
		FileReader fR2 = new FileReader(f2);

		BufferedReader reader1 = new BufferedReader(fR1);
		BufferedReader reader2 = new BufferedReader(fR2);

		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();

		String line = null;

		while ((line = reader1.readLine()) != null) {
			list1.add(line.toUpperCase());
		}
		reader1.close();

		Collections.sort(list1);

		while ((line = reader2.readLine()) != null) {
			list2.add(line.toUpperCase());
		}
		reader2.close();

		Collections.sort(list2);

		if (!list1.equals(list2)) {
			flag = 1;
		}

		return flag;

	}

	@Test
	public void testParser1() throws Exception {
		String outputFile = "result1.csv";
		String expectedResult = "expectedresult1.csv";

		Parser.parse("Select ?person1 ?person2 where { ?person1 <hasFriend> ?person2. } ", outputFile);
		int resultflag = CompareFile(expectedResult, outputFile);
		if (resultflag == 0) {
			assertEquals(1, 1);
		} else {
			fail("results are not same");
		}
	}

	@Test
	public void testParser2() throws Exception {
		String outputFile = "result2.csv";
		String expectedResult = "expectedresult2.csv";

		Parser.parse(
				"select ?person ?university  where { <Shelden> <hasFriend> ?person . ?person <worksAt> ?university. } ",
				outputFile);
		int resultflag = CompareFile(expectedResult, outputFile);
		if (resultflag == 0) {
			assertEquals(1, 1);
		} else {
			fail("results are not same");
		}
	}

	@Test
	public void testParser3() throws Exception {
		String outputFile = "result3.csv";
		String expectedResult = "expectedresult3.csv";

		Parser.parse(
				"Select ?person1 ?person2 where { ?person1 <worksAt> <Caltech> . ?person2 <bornIn> < Nabraska> . ?person1 "
				+ "<hasFriend> ?person2 .}",
				outputFile);
		int resultflag = CompareFile(expectedResult, outputFile);
		if (resultflag == 0) {
			assertEquals(1, 1);
		} else {
			fail("results are not same");
		}
	}

	@Test
	public void testParser4() throws Exception {
		String outputFile = "result4.csv";
		String expectedResult = "expectedresult4.csv";

		Parser.parse("select * where {?a ?b ?c.}", outputFile);
		int resultflag = CompareFile(expectedResult, outputFile);
		if (resultflag == 0) {
			assertEquals(1, 1);
		} else {
			fail("results are not same");
		}

	}

	@Test
	public void testParser5() throws Exception {
		String outputFile = "result5.csv";
		String expectedResult = "expectedresult5.csv";

		Parser.parse("select * where {}", outputFile);
		int resultflag = CompareFile(expectedResult, outputFile);
		if (resultflag == 0) {
			assertEquals(1, 1);
		} else {
			fail("results are not same");
		}
	}

	@Test
	public void testParser6() throws Exception {
		String outputFile = "result6.csv";
		String expectedResult = "expectedresult6.csv";

		Parser.parse("select * where {?person2 <hasFriend> ?person2 . ?person 2 <worksAt>. }", outputFile);
		int resultflag = CompareFile(expectedResult, outputFile);
		if (resultflag == 0) {
			assertEquals(1, 1);
		} else {
			fail("results are not same");
		}
	}

	@Test
	public void testParser7() throws Exception {
		String outputFile = "result7.csv";
		String expectedResult = "expectedresult7.csv";

		Parser.parse("select ?person1 where { ?person1 <worksAt> <Caltech> . ?person1 <bornIn> <New_Delhi> . "
				+ "?person1 <hasSister> <Priya> . }", outputFile);
		int resultflag = CompareFile(expectedResult, outputFile);
		if (resultflag == 0) {
			assertEquals(1, 1);
		} else {
			fail("results are not same");
		}
	}
}