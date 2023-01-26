
import java.util.LinkedList;
import java.util.*;
import java.io.*;

public class DictionaryBSTTester {

	private String dictFile = "words.small.txt";

	DictionaryBST emptyDict;
	DictionaryBST smallDict;
	DictionaryBST largeDict;

	public void setUp() throws Exception
	{
		emptyDict = new DictionaryBST();
		smallDict = new DictionaryBST();
		largeDict = new DictionaryBST();

		smallDict.addWord("Hello");
		smallDict.addWord("HElLo");
		smallDict.addWord("help");
		smallDict.addWord("a");
		smallDict.addWord("subsequent");

		Scanner file = new Scanner(new FileInputStream(new File(dictFile)));
		while(file.hasNext()){
			largeDict.addWord(file.next());
		}
	}

	/** Test if the size method is working correctly.
	 */
	public void testSize()
	{
		System.out.println("Testing size for empty dict. " + (0 == emptyDict.size()));
		System.out.println("Testing size for small dict. " + (4 == smallDict.size()));
		System.out.println("Testing size for large dict. " + (4438 == largeDict.size()));
	}

	/** Test the isWord method */
	public void testIsWord()
	{
		System.out.println("Testing isWord on empty: Hello. " + (false==emptyDict.isWord("Hello")));
		System.out.println("Testing isWord on small: Hello. " + smallDict.isWord("Hello"));
		System.out.println("Testing isWord on large: Hello. " + largeDict.isWord("Hello"));

		System.out.println("Testing isWord on small: hello. " + smallDict.isWord("hello"));
		System.out.println("Testing isWord on large: hello. " + largeDict.isWord("hello"));

		System.out.println("Testing isWord on small: hellow. " + (false==smallDict.isWord("hellow")));
		System.out.println("Testing isWord on large: hellow." + (false==largeDict.isWord("hellow")));

		System.out.println("Testing isWord on empty: empty string. " + (false==emptyDict.isWord("")));
		System.out.println("Testing isWord on small: empty string. " + (false==smallDict.isWord("")));
		System.out.println("Testing isWord on large: empty string. " + (false==largeDict.isWord("")));

		System.out.println("Testing isWord on small: no. " +  (false==smallDict.isWord("no")));
		System.out.println("Testing isWord on large: no. " + largeDict.isWord("no"));

		System.out.println("Testing isWord on small: subsequent. " + smallDict.isWord("subsequent"));
		System.out.println("Testing isWord on large: subsequent. " + largeDict.isWord("subsequent"));


	}

	/** Test the addWord method */
	public void addWord()
	{

		System.out.println("Asserting hellow is not in empty dict. " + (false==emptyDict.isWord("hellow")));
		System.out.println("Asserting hellow is not in small dict. " + (false==smallDict.isWord("hellow")));
		System.out.println("Asserting hellow is not in large dict. " +  (false==largeDict.isWord("hellow")));

		emptyDict.addWord("hellow");
		smallDict.addWord("hellow");
		largeDict.addWord("hellow");

		System.out.println("Asserting hellow is in empty dict. " + emptyDict.isWord("hellow"));
		System.out.println("Asserting hellow is in small dict. " + smallDict.isWord("hellow"));
		System.out.println("Asserting hellow is in large dict. " + largeDict.isWord("hellow"));

		System.out.println("Asserting xyzabc is not in empty dict. " + (false==emptyDict.isWord("xyzabc")));
		System.out.println("Asserting xyzabc is not in small dict. " + (false==smallDict.isWord("xyzabc")));
		System.out.println("Asserting xyzabc is in large dict. " + (false==largeDict.isWord("xyzabc")));


		emptyDict.addWord("XYZAbC");
		smallDict.addWord("XYZAbC");
		largeDict.addWord("XYZAbC");

		System.out.println("Asserting xyzabc is in empty dict. " + emptyDict.isWord("xyzabc"));
		System.out.println("Asserting xyzabc is in small dict. " + smallDict.isWord("xyzabc"));
		System.out.println("Asserting xyzabc is large dict. " + largeDict.isWord("xyzabc"));


		System.out.println("Testing isWord on empty: empty string. " + (false==emptyDict.isWord("")));
		System.out.println("Testing isWord on small: empty string. " + (false==smallDict.isWord("")));
		System.out.println("Testing isWord on large: empty string. " + (false==largeDict.isWord("")));

		System.out.println("Testing isWord on small: no. " + (false==smallDict.isWord("no")));
		System.out.println("Testing isWord on large: no. " + largeDict.isWord("no"));

		System.out.println("Testing isWord on small: subsequent. " + smallDict.isWord("subsequent"));
		System.out.println("Testing isWord on large: subsequent. " + largeDict.isWord("subsequent"));
	}
	public static void main(String[] args)throws Exception{
		DictionaryBSTTester dl = new DictionaryBSTTester();
		dl.setUp();
		dl.testSize();
		dl.testIsWord();
		dl.addWord();
	}
}
