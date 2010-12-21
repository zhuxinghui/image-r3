package net.semanticmetadata.lire.imageanalysis;

import at.lux.imageanalysis.VisualDescriptor;

import java.awt.image.BufferedImage;

/**
 * Created by IntelliJ IDEA.
 * User: mlux
 * Date: 25.02.2010
 * Time: 12:50:56
 * To change this template use File | Settings | File Templates.
 */
public class PseudoFeature implements LireFeature{
    public void extract(BufferedImage bimg) {
        // not implemented as it is pseudo, user has to use setStringRep or other method to set values
    }

    public float getDistance(VisualDescriptor visualDescriptor) {
        // f (!(vd instanceof CEDD))
        //     throw new UnsupportedOperationException("Wrong descriptor.");
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getStringRepresentation() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setStringRepresentation(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
