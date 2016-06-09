import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Finds the types of line separators in a given file and prints results to standard out.
 * @author Zach Wilson
 */
public class LineSeparatorExaminer {
    
    /**
     * Main.
     * @param args command line arguments: <code>-f [filename]</code>
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Ensure command line arguments are correct
        if (args.length == 0 || !args[0].equals("-f")) {
            System.out.println("Invalid command line arguments.");
            printHelp();
            System.exit(-1);
        }
        
        // Ensure file exists
        File file = new File(args[1]);
        if (!file.exists()) {
            System.out.println("File does not exist: " + file.getAbsolutePath());
            printHelp();
            System.exit(-1);
        }
        
        // Perform analysis
        findLineSeparators(file);
    }
    
    /**
     * Checks each character for a match of "\n", "\r\n", or "\r\n".
     * @param file file to be examined
     * @throws IOException
     */
    private static void findLineSeparators(File file) throws IOException {
        try (FileReader reader = new FileReader(file)) {
            int c;
            int lineNumber = 1;
            while ((c = reader.read()) != -1) {
                if (c == '\n') {
                    System.out.println("Line " + lineNumber++ + ": \\n");
                } else if (c == '\r') {
                    if (reader.read() == '\n') {
                        System.out.println("Line " + lineNumber++ + ": \\r\\n");
                    } else {
                        System.out.println("Line " + lineNumber++ + ": \\r");
                    }
                }
            }
        }
    }
    
    /**
     * Prints usage to standard out.
     */
    private static void printHelp() {
        System.out.println();
        System.out.println("LineSeparatorExaminer");
        System.out.println();
        System.out.println("Usage:");
        System.out.println();
        System.out.println("-f [filename]            specify file to be examined");
        System.out.println();
    }
}
