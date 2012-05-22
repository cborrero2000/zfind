package net.sf.util.zip;


import joptsimple.OptionParser;
import joptsimple.OptionSet;
import net.sf.util.zip.analyzer.FileAnalyzerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/19/12
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */


public class ZFind {

    private File targetFile = null;
    private String fileName = null;
    private boolean ignoreCase = false;
    private boolean regularExpression = false;


    /**
     * @param targetFile
     */
    public ZFind(File targetFile) throws IllegalArgumentException {
        if (!targetFile.exists()) {
            throw new IllegalArgumentException("Target file/folder must exist.");
        } else if (!FileNameUtil.isArchieveFile(targetFile.getName()) && !FileNameUtil.isCompressedFile(targetFile.getName()) && !targetFile.isDirectory()) {
            throw new IllegalArgumentException("Target must be either a directory or a zip file.");
        } else
            this.targetFile = targetFile;
    }


    /**
     * @return
     * @throws Exception
     */
    public List<String> getAllEntries() throws IOException {
        List<String> entries = null;
        try {
            entries=FileAnalyzerFactory.getAnalyzer(targetFile).analyze(targetFile);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return entries;
    }


    /**
     * @return
     * @throws Exception
     */
    public List<String> getMatchedEntries() throws IOException {
        List<String> matchedEntries = null;

        if (fileName != null) {
            List<String> entries = getAllEntries();
            matchedEntries = new ArrayList<String>();

            Pattern p = null;
            if (regularExpression) {
                if (!fileName.endsWith("$")) //we want only node matching
                    fileName = fileName + "$";
                p = (ignoreCase) ? Pattern.compile(fileName, Pattern.CASE_INSENSITIVE) : Pattern.compile(fileName);
            }

            for (String entry : entries) {
                if (regularExpression) {
                    Matcher m = p.matcher(entry);
                    if (m.find())
                        matchedEntries.add(entry);
                } else {
                    String s = (ignoreCase) ? entry.toLowerCase() : entry;
                    String f = (ignoreCase) ? fileName.toLowerCase() : fileName;
                    if (s.endsWith(f))
                        matchedEntries.add(entry);

                }
            }
        }
        return matchedEntries;
    }


    /**
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    /**
     * @param ignoreCase
     */
    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }


    /**
     * @param regularExpression
     */
    public void setRegularExpression(boolean regularExpression) {
        this.regularExpression = regularExpression;
    }


    private static void printHelpOnError(String msg, OptionParser parser) throws IOException {
        System.err.println(msg + "\n\n");
        System.out.println("USAGE: java -jar zfind.jar -t <target file or folder where to look> [OPTIONS]\n");
        parser.printHelpOn(System.out);
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {

        OptionParser parser = new OptionParser() {
            {
                accepts("t").withRequiredArg().required().describedAs("target folder or zip file where to look");
                accepts("h", "show help");
                accepts("f").withRequiredArg().describedAs("file name to search)");
                accepts("r", "treat <file name to search> as regular expression pattern");
                accepts("i", "perform case insensitive search");
            }
        };


        OptionSet options = null;

        if (args.length == 0)
            printHelpOnError("Error parsing arguments. At least target zip file/folder option (-t) must be provided.", parser);
        else {
            //search is it's a help request
            for (String opt : args) {
                if (opt.equals("-h")) //print help and exit
                    printHelpOnError("", parser);
            }
        }

        try {
            options = parser.parse(args);
        } catch (Throwable e) {
            printHelpOnError("Error parsing arguments. Please check for correct arguments.", parser);
        }

        File tagetF = new File(options.valueOf("t").toString());
        ZFind zFind = null;
        try {
            zFind = new ZFind(tagetF);
        } catch (IllegalArgumentException i) {
            printHelpOnError(i.getMessage(), parser);
        }
        if (options.has("r"))
            zFind.setRegularExpression(true);

        if (options.has("i"))
            zFind.setIgnoreCase(true);

        boolean isFind = false;
        if (options.has("f")) {
            zFind.setFileName(options.valueOf("f").toString());
            isFind = true;
        }

        List<String> entries = (isFind) ? zFind.getMatchedEntries() : zFind.getAllEntries();
        System.out.println(entries.size() + " Total entries found.\n");
        for (int i = 0; i < entries.size(); i++) {
            System.out.println(entries.get(i));
        }
    }
}
