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

import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.compressors.bzip2.BZip2Utils;
import org.apache.commons.compress.compressors.gzip.GzipUtils;
import org.apache.commons.compress.compressors.xz.XZUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Utility class to identify file type based on file suffix
 */
public class FileNameUtil {

    /**
     * Compressed file type
     */
    public static final String TYPE_COMPRESSED = "TYPE_COMPRESSED";
    /**
     * Archive file type
     */
    public static final String TYPE_ARCHIVE = "TYPE_ARCHIVE";
    /**
     * Normal File (i.e. not archive or compressed file)
     */
    public static final String TYPE_NORMAL_FILE = "TYPE_NORMAL_FILE";


    private static List<String> archieveExtensionList = new ArrayList<String>();

    static {
        archieveExtensionList.add(".zip");
        archieveExtensionList.add(".tar");
        archieveExtensionList.add(".jar");
        archieveExtensionList.add(".war");
        archieveExtensionList.add(".ear");
    }

    /**
     *
     * @param name the filename
     * @return the file extension or empty string if there is no extension
     */
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


    /**
     *
     * @param suffix Add to existing list of suffixes that are considered as of type Archive
     */
    public static void addToArchiveList(String suffix) {
        if (!suffix.startsWith("."))
            suffix = "." + suffix;
        archieveExtensionList.add(suffix.toLowerCase());
    }

    /**
     *
     * @param name the file name
     * @return whether the file is an valid archive file or not (based on suffix)
     */
    public static boolean isArchieveFile(String name) {
        if (archieveExtensionList.contains(getExtension(name)))
            return true;

        return false;
    }

    /**
     *
     * @param name the file name
     * @return whether the file is an valid compressed file or not (based on suffix)
     */
    public static boolean isCompressedFile(String name) {
        if (BZip2Utils.isCompressedFilename(name) || GzipUtils.isCompressedFilename(name) || XZUtils.isCompressedFilename(name))
            return true;
        return false;
    }

    /**
     *
     * @param fileName the file name
     * @return  Type of the file, based on suffix
     */
    public static String getFileType(String fileName) {
        if (isArchieveFile(fileName))
            return TYPE_ARCHIVE;
        else if (isCompressedFile(fileName))
            return TYPE_COMPRESSED;
        else
            return TYPE_NORMAL_FILE;
    }

    /**
     *
     * @param fileName the file name
     * @return  Archive file type, as defined in ArchiveStreamFactory
     */
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

    /**
     *
     * @param fileName  the file name
     * @return   Compressed file type, as defined in CompressorStreamFactory
     */
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

}
