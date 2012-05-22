package net.sf.util.zip;

import static junit.framework.Assert.*;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/22/12
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestFileNameUtil {

    @Test
    public void testGetExtension(){
        assertEquals(FileNameUtil.getExtension("foo.tar.gz"),".gz");
        assertEquals(FileNameUtil.getExtension("foo"),"");
    }

    @Test
    public void testIsArchiveFile(){
        assertTrue(FileNameUtil.isArchieveFile("foo.jar"));
        assertTrue(FileNameUtil.isArchieveFile("foo.zip"));
        assertTrue(FileNameUtil.isArchieveFile("foo.war"));
        assertTrue(FileNameUtil.isArchieveFile("foo.ear"));

        assertFalse(FileNameUtil.isArchieveFile("foo"));
        assertFalse(FileNameUtil.isArchieveFile("foo.xjar"));
        assertFalse(FileNameUtil.isArchieveFile("foo.tar.gz"));
    }


}
