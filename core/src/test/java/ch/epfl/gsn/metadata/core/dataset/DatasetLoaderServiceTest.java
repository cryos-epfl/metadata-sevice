package ch.epfl.gsn.metadata.core.dataset;

import ch.epfl.gsn.metadata.core.dataset.model.ContributingSensor;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by kryvych on 07/03/16.
 */
public class DatasetLoaderServiceTest {

    private DatasetLoaderService subject;


    @Before
    public void init() {
        subject = new DatasetLoaderService();
    }

    @Test
    public void testParseContributingSensorsAllFields() throws Exception {
        String members = "[\n" +
                "    {\n" +
                "      \"name\": \"wfj_vf_meteo\",\n" +
                "      \"parameters\": [\n" +
                "        \"air_temp_min_rotronic\",\n" +
                "        \"rel_humidity_rotronic\"\n" +
                "      ],\n" +
                "      \"from\": \"1996-01-08\",\n" +
                "      \"to\": \"2016-03-04\"\n" +
                "    }]";

        Set<ContributingSensor> contributingSensors = subject.parseContributingSensors(members);
        assertThat(contributingSensors.size(), is(1));
        ContributingSensor sensor = contributingSensors.iterator().next();
        assertThat(sensor.getName(), is("wfj_vf_meteo"));
        assertThat(sensor.getParameters().size(), is(2));
        assertThat(sensor.getParameters().contains("air_temp_min_rotronic"), is(true));
        assertThat(sensor.getFrom(), is("1996-01-08"));
        assertThat(sensor.getTo(), is("2016-03-04"));

    }

    @Test
    public void testParseContributingSensorsMissingFields() throws Exception {
        String members = "[{\n" +
                "     \"name\": \"wfj_vf_mod\"\n" +
                "   }]";

        Set<ContributingSensor> contributingSensors = subject.parseContributingSensors(members);
        assertThat(contributingSensors.size(), is(1));
        ContributingSensor sensor = contributingSensors.iterator().next();
        assertThat(sensor.getName(), is("wfj_vf_mod"));
        assertThat(sensor.getParameters(), is(nullValue()));
        assertThat(sensor.getFrom(), is(nullValue()));
        assertThat(sensor.getTo(), is(nullValue()));

    }
}