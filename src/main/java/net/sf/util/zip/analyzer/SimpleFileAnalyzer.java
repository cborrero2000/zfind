package net.sf.util.zip.analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/21/12
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleFileAnalyzer extends AbstractFileAnalyzer {
    @Override
    public List<String> analyze(File f) throws Exception {
        List<String> entries=new ArrayList<String>();
        addEntry(f.getAbsolutePath(), entries);
        return entries;
    }
}
