package net.sf.util.zip.analyzer;

import net.sf.util.zip.FileNameUtil;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/21/12
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileAnalyzerFactory {

    private static DirectoryAnalyzer directoryAnalyzer = new DirectoryAnalyzer();
    private static ArchieveAnalyzer archieveAnalyzer = new ArchieveAnalyzer();
    private static CompressorAnalyzer compressorAnalyzer = new CompressorAnalyzer();
    private static SimpleFileAnalyzer simpleFileAnalyzer = new SimpleFileAnalyzer();

    public static DirectoryAnalyzer getDirectoryAnalyzer() {
        return directoryAnalyzer;
    }

    public static ArchieveAnalyzer getArchieveAnalyzer() {
        return archieveAnalyzer;
    }

    public static CompressorAnalyzer getCompressorAnalyzer() {
        return compressorAnalyzer;
    }

    public static SimpleFileAnalyzer getSimpleFileAnalyzer() {
        return simpleFileAnalyzer;
    }




    public static FileAnalyzer getAnalyzer(File file) {
        if (file.isDirectory())
            return directoryAnalyzer;
        else if (FileNameUtil.getFileType(file.getName()) == FileNameUtil.TYPE_ARCHIEVE)
            return archieveAnalyzer;
        else if (FileNameUtil.getFileType(file.getName()) == FileNameUtil.TYPE_COMPRESSED)
            return compressorAnalyzer;

        return simpleFileAnalyzer;
    }
}
