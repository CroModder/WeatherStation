package controller;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Server application
 *
 * @author
 *      T13_SWP5 (t13swp5@gmail.com)
 */
public class CLI {

    @Option(name="-HELP" , usage="Print this message", forbids={"-USERS", "-CONFIG", "-GUI", "-START"})
    private boolean help;

    @Option(name="-USERS", usage="Go to user settings", forbids={"-HELP", "-CONFIG", "-GUI", "-START"})
    private boolean users;
    
    @Option(name="-CONFIG", usage="Enter the device configuration menu", forbids={"-USERS", "-HELP", "-GUI", "-START"})
    private boolean config;
    
    @Option(name="-GUI", usage="Start server in graphical mode", forbids={"-USERS", "-CONFIG", "-HELP", "-START"})
    private boolean gui;
    
    @Option(name="-START", usage="Start the server", forbids={"-USERS", "-CONFIG", "-GUI", "-HELP"})
    private boolean start;

    // receives other command line parameters than options
    @Argument
    private List<String> arguments = new ArrayList<String>();
    
    FirstUse firstUse = new FirstUse();
    CmdLineParser parser = new CmdLineParser(this);
    DbController dbController = new DbController();

    public static void main(String[] args) throws IOException {
    	
    	if (args.length>0){
    		new CLI().doMain(args);
		}
			else { 
				new CLI().printUsage();
			}
    }

    public void doMain(String[] args) throws IOException {
    	if(!dbController.dbInit()){
    		System.out.println("No connection to the database!\nThe application is not usable. Please check your Internet connection.");
    		System.exit(0);
    	}
    	parser = new CmdLineParser(this);
        try {
            // parse the arguments.
            parser.parseArgument(args);
            
            if(!arguments.isEmpty()) {
            	printUsage();
            }
            else if( help ) {
            	System.out.println("Usage: java -jar server.jar [option]");
            	System.out.println("Options:");
                parser.printUsage(System.out);
            }
            else if( start ) {
            	firstUse.runServer("");
            }
            else if( gui ) {
            	firstUse.runServer("GUI");
            }
            else if( users ) {
            	Users usersController = new Users();
            	usersController.doMain();
            }
            else if( config ) {
            	Config configController = new Config();
            	configController.doMain();
            }

        } catch( CmdLineException e ) {
            System.err.println(e.getMessage());
            System.err.println("Usage: java -jar server.jar [option]");
            System.err.println("Options:");
            parser.printUsage(System.err);
            System.err.println();

            return;
        }
    }

	public void printUsage() {
		System.err.println("- Invalid argument given!");
    	System.err.println("Usage: java -jar server.jar [option]");
        System.err.println("Options:");
        parser.printUsage(System.err);
		
	}
}