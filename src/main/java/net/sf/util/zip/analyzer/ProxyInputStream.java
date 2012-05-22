package net.sf.util.zip.analyzer;

import java.io.FilterInputStream;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: dattadi
 * Date: 5/21/12
 * Time: 10:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProxyInputStream extends FilterInputStream {

    public ProxyInputStream(InputStream in){
        super(in);
    }



    @Override
        public void close() {
            //Do nothing
        }
}
