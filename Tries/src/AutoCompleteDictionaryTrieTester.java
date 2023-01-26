
import java.util.LinkedList;
import java.util.List;

public class AutoCompleteDictionaryTrieTester {

	private String dictFile = "words.small.txt";

	AutoCompleteDictionaryTrie emptyDict;
	AutoCompleteDictionaryTrie smallDict;
	AutoCompleteDictionaryTrie largeDict;

	public void setUp() throws Exception
	{
		emptyDict = new AutoCompleteDictionaryTrie();
		smallDict = new AutoCompleteDictionaryTrie();
		largeDict = new AutoCompleteDictionaryTrie();

		smallDict.addWord("Hello");
		smallDict.addWord("HElLo");
		smallDict.addWord("help");
		smallDict.addWord("he");
		smallDict.addWord("hem");
		smallDict.addWord("hot");
		smallDict.addWord("hey");
		smallDict.addWord("a");
		smallDict.addWord("subsequent");

		DictionaryLoader.loadDictionary(largeDict, dictFile);
	}


	/** Test if the size method is working correctly.
	 */
	public void testSize()
	{
		System.out.println("Testing size for empty dict. " + (0==emptyDict.size()));
		System.out.println("Testing size for small dict. " + (8==smallDict.size()));
		System.out.println("Testing size for large dict. " + (4438==largeDict.size()));
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
		System.out.println("Testing isWord on large: hellow. " + (false==largeDict.isWord("hellow")));

		System.out.println("Testing isWord on empty: empty string. " + (false==emptyDict.isWord("")));
		System.out.println("Testing isWord on small: empty string. " + (false==smallDict.isWord("")));
		System.out.println("Testing isWord on large: empty string. " + (false==largeDict.isWord("")));

		System.out.println("Testing isWord on small: no. " + (false==smallDict.isWord("no")));
		System.out.println("Testing isWord on large: no. " + largeDict.isWord("no"));

		System.out.println("Testing isWord on small: subsequent. " + smallDict.isWord("subsequent"));
		System.out.println("Testing isWord on large: subsequent. " + largeDict.isWord("subsequent"));


	}

	/** Test the addWord method */
	public void addWord()
	{

		System.out.println("Asserting hellow is not in empty dict. " + (false==emptyDict.isWord("hellow")));
		System.out.println("Asserting hellow is not in small dict. " + (false==smallDict.isWord("hellow")));
		System.out.println("Asserting hellow is not in large dict. " + (false==largeDict.isWord("hellow")));

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

	public void testPredictCompletions()
	{
		List<String> completions;
		completions = smallDict.predictCompletions("", 0);
		System.out.println(0==completions.size());

		completions = smallDict.predictCompletions("",  4);
		System.out.println(4==completions.size());
		System.out.println(completions.contains("a"));
		System.out.println(completions.contains("he"));
		boolean twoOfThree = completions.contains("hey") && completions.contains("hot") ||
				             completions.contains("hey") && completions.contains("hem") ||
				             completions.contains("hot") && completions.contains("hem");
		System.out.println(twoOfThree);

		completions = smallDict.predictCompletions("he", 2);
		boolean allIn = completions.contains("he") &&
				(completions.contains("hem") || completions.contains("hey"));
		System.out.println(2==completions.size());
		System.out.println(allIn);

		completions = smallDict.predictCompletions("hel", 10);
		allIn = completions.contains("hello") && completions.contains("help");
		System.out.println(allIn);
	}
	public static void main(String[] args)throws Exception{
		AutoCompleteDictionaryTrieTester dl = new AutoCompleteDictionaryTrieTester();
		dl.setUp();
		dl.testSize();
		dl.testIsWord();
		dl.addWord();
		dl.testPredictCompletions();
	}

}
