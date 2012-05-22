package net.sf.util.zip.analyzer;

import net.sf.util.zip.FileNameUtil;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/21/12
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArchieveAnalyzer extends AbstractFileAnalyzer {


    private ArchiveInputStream getArchiveInputStream(String name, InputStream stream) throws Exception {

        stream=new ProxyInputStream(stream);
        String type = FileNameUtil.getArchieveFileType(name);
        ArchiveInputStream newArchiveInputStream = (type == null) ? new ArchiveStreamFactory()
                .createArchiveInputStream(stream) : new ArchiveStreamFactory()
                .createArchiveInputStream(type, stream);
        return newArchiveInputStream;
    }

    private void listZipContent(ArchiveInputStream zipInputStream, String path, List<String> entries) throws Exception {
        addEntry(path, entries);
        ArchiveEntry entry = zipInputStream.getNextEntry();
        while (entry != null) {
            String newPath = path + "/" + entry.getName();
            if (FileNameUtil.isArchieveFile(entry.getName())) {
                ArchiveInputStream newZipInputStream = getArchiveInputStream(entry.getName(), zipInputStream);
                listZipContent(newZipInputStream, newPath, entries);
            } else if (FileNameUtil.isCompressedFile(entry.getName())) {
                List<String> vals=FileAnalyzerFactory.getCompressorAnalyzer().analyze(zipInputStream,newPath);
                entries.addAll(vals);
            } else
                addEntry(newPath, entries);

            entry = zipInputStream.getNextEntry();
        }
        zipInputStream.close();
    }


    @Override
    public List<String> analyze(File f) throws Exception {
        //System.out.println("Inside ArchiveAnalyzer.analyze()");
        List<String> entries = new ArrayList<String>();
        FileInputStream fin=new FileInputStream(f);
        ArchiveInputStream newArchiveInputStream = getArchiveInputStream(f.getName(), fin);
        listZipContent(newArchiveInputStream, f.getAbsolutePath(), entries);
        newArchiveInputStream.close();
        fin.close();
        return entries;
    }
}
