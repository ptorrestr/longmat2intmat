package hujo.deri.org.longMat2intMat;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

public class App 
{
	private static final Logger log = Logger.getLogger(App.class);
	public static final String CMDNAME = "long2Int";
	public static final String INPUT = "input";
	public static final String OUTPUT = "output";
	public static final String DICTIONARY = "dict";
	
	public static final Option input =
			OptionBuilder.hasArg(true)
				.isRequired(true)
				.withDescription("The input file path <long, vector> mat")
				.create(INPUT);
	public static final Option output = 
			OptionBuilder.hasArg(true)
				.isRequired(true)
				.withDescription("The output file path for <int, vector> mat")
				.create(OUTPUT);
	public static final Option dictionary =
			OptionBuilder.hasArg(true)
				.isRequired(true)
				.withDescription("The dictionary file path")
				.create(DICTIONARY);


	public static void printHelp()
	{
		Options options = new Options();
    	options.addOption(input);
    	options.addOption(output);
    	options.addOption(dictionary);
    	
		HelpFormatter help = new HelpFormatter();
		help.printHelp(CMDNAME, options);
	}
	
	public static Map<String, String> validOptions( String[] args ) throws ParseException 
	{
		// Parse input arguments
    	Options options = new Options();
    	options.addOption(input);
    	options.addOption(output);
    	options.addOption(dictionary);
    	 
    	CommandLineParser parser = new GnuParser();
    	CommandLine cmdLine = parser.parse(options, args);
		
		Map<String, String> result = new HashMap<String, String>();
		result.put(INPUT, cmdLine.getOptionValue(INPUT));
		result.put(OUTPUT, cmdLine.getOptionValue(OUTPUT));
		result.put(DICTIONARY, cmdLine.getOptionValue(DICTIONARY));
		
		return result;
	}
	
    public static void main( String[] args )
    {
    	// Parse args
    	Map<String, String> opts;
		try {
			opts = validOptions(args);
			
			// Execute transformation
			Long2Int l2i = new Long2Int();
			l2i.readMahoutSequenceFile(
					opts.get(INPUT),
					opts.get(OUTPUT),
					opts.get(DICTIONARY));
			
		} catch (ParseException e) {
			log.error(e);
			printHelp();
		}
		// End program
		System.exit(0);
    }
}
