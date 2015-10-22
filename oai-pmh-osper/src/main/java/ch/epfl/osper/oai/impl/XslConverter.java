package ch.epfl.osper.oai.impl;

import ch.epfl.osper.oai.interfaces.Converter;
import ch.epfl.osper.oai.interfaces.DataAccessException;
import ch.epfl.osper.oai.model.OsperRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by kryvych on 22/10/15.
 */
public abstract class XslConverter implements Converter<OsperRecord> {

    protected static final Logger logger = LoggerFactory.getLogger(XslConverter.class);

    protected final DifConverter difConverter;

    public XslConverter(DifConverter difConverter) {
        this.difConverter = difConverter;
    }

    public String convert(OsperRecord osperRecord) {
        String dif = difConverter.convert(osperRecord);
        TransformerFactory factory = TransformerFactory.newInstance();

        ClassPathResource classPathResource = new ClassPathResource(xslLocation());

        try {
            Source stylesheetSource = new StreamSource(classPathResource.getInputStream());
            StringWriter output = new StringWriter();
            Result outputResult = new StreamResult(output);

            Source inputSource = new StreamSource(new StringReader(dif));

            Transformer transformer = factory.newTransformer(stylesheetSource);
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(inputSource, outputResult);

            return output.toString();
        } catch ( TransformerException | IOException e) {
            logger.error("Cannot convert to format " + xslLocation(), e);
            throw new DataAccessException("Cannot convert ", e);
        }

    }

    protected abstract String xslLocation();
}
