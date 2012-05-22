package net.sf.util.zip;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.compressors.bzip2.BZip2Utils;
import org.apache.commons.compress.compressors.gzip.GzipUtils;
import org.apache.commons.compress.compressors.xz.XZUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/20/12
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileNameUtil {

    public static final String TYPE_COMPRESSED = "TYPE_COMPRESSED";
    public static final String TYPE_ARCHIEVE = "TYPE_ARCHIEVE";
    public static final String TYPE_NORMAL_FILE = "TYPE_NORMAL_FILE";


    private static List<String> archieveExtensionList = new ArrayList<String>();

    static {
        archieveExtensionList.add(".zip");
        archieveExtensionList.add(".tar");
        archieveExtensionList.add(".jar");
        archieveExtensionList.add(".war");
        archieveExtensionList.add(".ear");
    }

    public static String getExtension(String name) {
        final String lower = name.toLowerCase(Locale.ENGLISH);
        String ext = null;
        try {
            ext = lower.substring(lower.lastIndexOf("."), lower.length());
        } catch (Throwable t) {
            ext = "";
        }
        return ext;
    }


    public static void addToArchiveList(String suffix) {
        if (!suffix.startsWith("."))
            suffix = "." + suffix;
        archieveExtensionList.add(suffix.toLowerCase());
    }

    public static boolean isArchieveFile(String name) {
        if (archieveExtensionList.contains(getExtension(name)))
            return true;

        return false;
    }

    public static boolean isCompressedFile(String name) {
        if (BZip2Utils.isCompressedFilename(name) || GzipUtils.isCompressedFilename(name) || XZUtils.isCompressedFilename(name))
            return true;
        return false;
    }

    public static String getFileType(String fileName) {
        if (isArchieveFile(fileName))
            return TYPE_ARCHIEVE;
        else if (isCompressedFile(fileName))
            return TYPE_COMPRESSED;
        else
            return TYPE_NORMAL_FILE;
    }

    public static String getArchieveFileType(String fileName) {
        String s = fileName.toLowerCase();
        if (s.endsWith(".jar") || s.endsWith(".war") || s.endsWith(".ear"))
            return ArchiveStreamFactory.JAR;
        else if (s.endsWith(".tar"))
            return ArchiveStreamFactory.TAR;
        else if (s.endsWith(".zip"))
            return ArchiveStreamFactory.ZIP;

        return null;
    }

    public static String[] getCompressFileType(String fileName) {
        String s = fileName.toLowerCase();
        String[] ret={null,null};
        if (GzipUtils.isCompressedFilename(s)){
            ret[0]= CompressorStreamFactory.GZIP;
            ret[1]=GzipUtils.getUncompressedFilename(fileName);
        }
        else if (BZip2Utils.isCompressedFilename(s)){
            ret[0]= CompressorStreamFactory.BZIP2;
            ret[1]=BZip2Utils.getUncompressedFilename(fileName);
        }
        else if (XZUtils.isCompressedFilename(s)){
            ret[0]= CompressorStreamFactory.XZ;
            ret[1]=XZUtils.getUncompressedFilename(fileName);
        }

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(isCompressedFile("foo.jar"));
    }
}
