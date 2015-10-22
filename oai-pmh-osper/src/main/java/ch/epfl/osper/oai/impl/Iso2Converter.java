package ch.epfl.osper.oai.impl;

import ch.epfl.osper.oai.interfaces.MetadataFormat;
import ch.epfl.osper.oai.model.OsperRecord;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by kryvych on 22/10/15.
 */
@Named
public class Iso2Converter extends XslConverter {

    @Inject
    public Iso2Converter(DifConverter difConverter) {
        super(difConverter);
    }

    @Override
    protected String xslLocation() {
        return "xslt/DIF-ISO-3.3.xsl";
    }


    @Override
    public <M extends MetadataFormat> boolean isForFormat(Class<M> formatClass) {
        return formatClass.equals(Iso2Format.class);
    }

    @Override
    public boolean canConvertRecord(OsperRecord osperRecord) {
        return osperRecord != null;
    }

}
