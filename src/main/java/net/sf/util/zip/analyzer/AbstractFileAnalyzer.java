package net.sf.util.zip.analyzer;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/21/12
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */


public abstract class AbstractFileAnalyzer implements FileAnalyzer {
    private Comparator<File> comparator
            = new FileComparator();

    public Comparator<File> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<File> comparator) {
        this.comparator = comparator;
    }

    protected void addEntry(String entry, List<String> entries) {
        if (entries == null)
            entries = new ArrayList<String>();
        entry = entry.replace("\\", "/");
        if (entry.endsWith("/"))
            entry = entry.substring(0, entry.length() - 1);
        //System.out.println(entry);
        entries.add(entry);
    }

    @Override
    public List<String> analyze(InputStream fin, String path) throws Exception {
        throw new UnsupportedOperationException("This method should not be called");
    }

}
