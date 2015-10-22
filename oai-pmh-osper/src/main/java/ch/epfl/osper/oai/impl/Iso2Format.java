package ch.epfl.osper.oai.impl;

import ch.epfl.osper.oai.interfaces.MetadataFormat;

import javax.inject.Named;

/**
 * Created by kryvych on 22/10/15.
 */
@Named
public class Iso2Format implements MetadataFormat {

    @Override
    public String getName() {
        return "iso2";
    }

    @Override
    public String getSchema() {
        return "http://wis.wmo.int/2011/schemata/iso19139_2007/schema/gmd/gmd.xsd";
    }

    @Override
    public String getNamespace() {
        return "http://www.isotc211.org/2005/gco";
    }

}

