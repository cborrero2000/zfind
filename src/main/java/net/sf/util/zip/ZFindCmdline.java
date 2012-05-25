/*
 * Dipankar Datta (dipdatta@user.sourceforge.net)
 * https://github.com/dipdatta/zfind
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.sf.util.zip;


import joptsimple.OptionParser;
import joptsimple.OptionSet;
import net.sf.util.zip.analyzer.FileAnalyzerFactory;
import net.sf.util.zip.ui.UIThread;
import net.sf.util.zip.ui.ZFindUI;
import sun.tools.jar.resources.jar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Immutable class for searching or listing (or finding within) a directory/archive file contents
 */


public class ZFindCmdline {


    private static void printHelpOnError(String msg, OptionParser parser) throws IOException {
        System.err.println(msg + "\n\n");
        System.out.println("To invoke GUI, simply double click the jar or type : java -jar zfind.jar\n");
        System.out.println("Command line USAGE:");
        System.out.println("java - jar zfind.jar - t < target file or folder where to look > [OPTIONS]\n");
        parser.printHelpOn(System.out);
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {

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
        System.out.println("args.len:" + args.length);
        if (args.length == 0) {
            //printHelpOnError("Error parsing arguments. At least target zip file/folder option (-t) must be provided.", parser);

            Thread t = new Thread(new UIThread());
            t.start();
            t.join();

        } else {
            //search is it's a help request
            for (String opt : args) {
                if (opt.equals("-h")) //print help and exit
                    printHelpOnError("", parser);
            }


            try {
                options = parser.parse(args);
            } catch (Throwable e) {
                printHelpOnError("Error parsing arguments. Please check for correct arguments.", parser);
            }

            File tagetF = new File(options.valueOf("t").toString());
            ZipFinder zFind = null;
            try {
                zFind = new ZipFinder(tagetF);
            } catch (IllegalArgumentException i) {
                printHelpOnError(i.getMessage(), parser);
            }
            if (options.has("r"))
                zFind.setRegularExpression(true);

            if (options.has("i"))
                zFind.setIgnoreCase(true);

            boolean isFind = false;
            if (options.has("f")) {
                zFind.setSearchFileName(options.valueOf("f").toString());
                isFind = true;
            }

            List<String> entries = (isFind) ? zFind.getMatchedEntries() : zFind.getAllEntries();
            System.out.println(entries.size() + " Total entries found.\n");
            for (int i = 0; i < entries.size(); i++) {
                System.out.println(entries.get(i));
            }
        }

    }
}
