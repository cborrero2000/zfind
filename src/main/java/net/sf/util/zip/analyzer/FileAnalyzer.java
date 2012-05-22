package net.sf.util.zip.analyzer;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/21/12
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FileAnalyzer {
    List<String>  analyze(File f) throws Exception;

    List<String>  analyze(InputStream fin, String path) throws Exception;
}
