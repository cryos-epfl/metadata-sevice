package ch.epfl.osper.oai.impl;


import ch.epfl.osper.oai.interfaces.MetadataFormat;

import javax.inject.Named;

/**
 * Created by kryvych on 07/09/15.
 */
@Named
public class DcOaiFormat implements MetadataFormat {
    @Override
    public String getName() {
        return "oai_dc";
    }

    @Override
    public String getSchema() {
        return "http://www.openarchives.org/OAI/2.0/oai_dc.xsd";
    }

    @Override
    public String getNamespace() {
        return "http://www.openarchives.org/OAI/2.0/oai_dc/";
    }

}
