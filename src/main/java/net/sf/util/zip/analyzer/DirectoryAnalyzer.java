package net.sf.util.zip.analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/21/12
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class DirectoryAnalyzer extends AbstractFileAnalyzer {
    @Override
    public List<String>  analyze(File f) throws Exception {
        //System.out.println("Inside DirectoryAnalyzer.analyze()");
        List<String> entries=new ArrayList<String>();
        addEntry(f.getAbsolutePath(), entries);
        File[] files = f.listFiles();
        Arrays.sort(files, getComparator());
        for (int i = 0; i < files.length; i++) {
            FileAnalyzer analyzer = FileAnalyzerFactory.getAnalyzer(files[i]);
            List<String> entriesN=analyzer.analyze(files[i]);
            entries.addAll(entriesN);
        }
        return entries;
    }
}
