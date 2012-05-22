package net.sf.util.zip.analyzer;

import net.sf.util.zip.FileNameUtil;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/21/12
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompressorAnalyzer extends AbstractFileAnalyzer {

    private CompressorInputStream getCompressedInputStream(String type, InputStream stream) throws Exception {

        stream=new ProxyInputStream(stream);

        CompressorInputStream newCInputStream = (type == null) ? new CompressorStreamFactory()
                .createCompressorInputStream(stream) : new CompressorStreamFactory()
                .createCompressorInputStream(type, stream);
        return newCInputStream;
    }

    private File createTmpDir(String fname){
        String outf=null;
       outf=System.getProperty("java.io.tmpdir")+"/"+fname;


        File output=new File(outf);
        //output.mkdirs();
        return output;
    }


    private void addOriginalEntry(String entry, String origPath, String dummyPath,List<String> entries) {
         addEntry(entry.replace(dummyPath,origPath),entries);
    }
    
    private void list(File f, String origPath, List<String> entries) throws Exception {
          FileAnalyzer analyzer = FileAnalyzerFactory.getAnalyzer(f);
            List<String> entriesN=analyzer.analyze(f);
            for(String e:entriesN)
                addOriginalEntry(e,origPath,f.getAbsolutePath().replace("\\","/"),entries);

    }
    
    @Override
    public List<String>  analyze(File f) throws Exception {
        //System.out.println("Inside CompressorAnalyzer.analyze(f)");
        FileInputStream fin=new FileInputStream(f);
        String[] res = FileNameUtil.getCompressFileType(f.getName());
        CompressorInputStream in =getCompressedInputStream(res[0],fin);
        File tmpDir=createTmpDir(res[1]);
        IOUtils.copy(in,new FileOutputStream(tmpDir));
        in.close();
        fin.close();

        List<String> entries=new ArrayList<String>();
        addEntry(f.getAbsolutePath(),entries);
        list(tmpDir,f.getAbsolutePath().replace("\\","/")+"/"+tmpDir.getName(),entries);
        tmpDir.delete();

        return entries;
    }

    @Override
    public List<String>  analyze(InputStream fin, String path) throws Exception {
        //System.out.println("Inside CompressorAnalyzer.analyze(fin,path): <path>:"+path);
        String nPath=path.replace("\\","/");
        if(nPath.indexOf("/")!=-1)
            nPath=nPath.substring(nPath.lastIndexOf("/")+1);

        String[] res = FileNameUtil.getCompressFileType(nPath);
        CompressorInputStream in =getCompressedInputStream(res[0],fin);
        File tmpDir=createTmpDir(res[1]);
        IOUtils.copy(in,new FileOutputStream(tmpDir));
        in.close();

        List<String> entries=new ArrayList<String>();
        addEntry(path,entries);
        list(tmpDir,path+"/"+tmpDir.getName(),entries);
        tmpDir.delete();

        return entries;
    }
}
