package ch.epfl.gsn.metadata.web.page;

import org.junit.Test;

/**
 * Created by kryvych on 05/08/16.
 */
public class FilterModelTest {


    @Test
    public void testGetJsonString() throws Exception {
        FilterModel subject = new FilterModel();
        subject.addGroup("G1");
        subject.addPublicSensor("G1", "S1");
        subject.addPublicSensor("G1", "S2");
        subject.addPublicSensor("G1", "S3");
        subject.addSensor("G1", "S4");
        subject.addSensor("G1", "S5");

        subject.addParameter("G1", "P1");
        subject.addPublicParameter("G1", "P2");

        String jsonString = subject.getJsonString();
        System.out.println("jsonString = " + jsonString);

    }
}