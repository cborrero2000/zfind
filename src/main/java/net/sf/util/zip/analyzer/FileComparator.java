package net.sf.util.zip.analyzer;

import net.sf.util.zip.FileNameUtil;

import java.io.File;
import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/21/12
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileComparator implements Comparator<File> {


    @Override
    public int compare(File o1, File o2) {
        if (o1.isDirectory() && !o2.isDirectory())
            return -1;
        else if (o2.isDirectory() && !o1.isDirectory())
            return 1;
        else if (!o1.isDirectory() && !o2.isDirectory()) {
            if (FileNameUtil.isArchieveFile(o1.getName()) || FileNameUtil.isCompressedFile(o1.getName()))
                return -1;
            else if (FileNameUtil.isArchieveFile(o1.getName()) || FileNameUtil.isCompressedFile(o1.getName()))
                return 1;
        }
        return 0;
    }

}