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
