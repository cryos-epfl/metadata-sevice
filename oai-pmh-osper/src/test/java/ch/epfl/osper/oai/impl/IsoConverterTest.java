package ch.epfl.osper.oai.impl;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * Created by kryvych on 22/10/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class IsoConverterTest {

    @Mock
    private DifConverter difConverterMock;

    private Iso1Converter subject;

    @Before
    public void init() {
        when(difConverterMock.convert(null)).thenReturn(DIF);
        subject = new Iso1Converter(difConverterMock);
    }
    @Test
    public void testConvert() throws Exception {
        String result = subject.convert(null);
        System.out.println("result = " + result);
    }

    public static final String DIF = "<DIF xmlns=\"http://gcmd.gsfc.nasa.gov/Aboutus/xml/dif/\"\n" +
            "     xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "     xsi:schemaLocation=\"http://gcmd.gsfc.nasa.gov/Aboutus/xml/dif/ http://gcmd.gsfc.nasa.gov/Aboutus/xml/dif/dif_v9.7.1.xsd\">\n" +
            "\n" +
            "    <Entry_ID>WFJ_VF_MODELS</Entry_ID>\n" +
            "    <!--should we create numerical ID?-->\n" +
            "    <Entry_Title>Automatic meteorological and snowpack measurements from Weissfluhjoch, Davos, Switzerland.\n" +
            "    </Entry_Title>\n" +
            "\n" +
            "    <Data_Set_Citation>\n" +
            "        <Dataset_Creator>\n" +
            "            SwissEX/OSPER data platform\n" +
            "        </Dataset_Creator>\n" +
            "        <Dataset_Title>Automatic meteorological and snowpack measurements from Weissfluhjoch, Davos, Switzerland.\n" +
            "        </Dataset_Title>\n" +
            "        <Dataset_Release_Date>$TODAY</Dataset_Release_Date>\n" +
            "        <Dataset_Release_Place>Davos, Switzerland</Dataset_Release_Place>\n" +
            "        <Dataset_Publisher>WSL Institute for Snow and Avalanche Research SLF</Dataset_Publisher>\n" +
            "        <Online_Resource>http://montblanc.slf.ch:22001/gsnweb/app/index.html#/plot?sensors=wfj_vf_models</Online_Resource>\n" +
            "    </Data_Set_Citation>\n" +
            "\n" +
            "    <Personnel>\n" +
            "        <Role>Investigator</Role>\n" +
            "        <First_Name>Nander</First_Name>\n" +
            "        <Last_Name>Wever</Last_Name>\n" +
            "        <Phone> +41 81 4170 270 </Phone>\n" +
            "        <Fax>+41 81 4170 110</Fax>\n" +
            "        <Contact_Address>\n" +
            "            <Address>Fluelastrasse 11</Address>\n" +
            "            <City>Davos</City>\n" +
            "            <Province_or_State>Graubunden</Province_or_State>\n" +
            "            <Postal_Code>7260</Postal_Code>\n" +
            "            <Country>Switzerland</Country>\n" +
            "        </Contact_Address>\n" +
            "    </Personnel>\n" +
            "\n" +
            "    <Personnel>\n" +
            "        <Role>Technical Contact</Role>\n" +
            "        <First_Name>Admin</First_Name>\n" +
            "        <Last_Name>OSPER</Last_Name>\n" +
            "        <Phone></Phone>\n" +
            "        <Fax></Fax>\n" +
            "        <Email></Email>\n" +
            "        <Contact_Address> <!-- generate a standard 'data' email-->\n" +
            "            <Address>Fluelastrasse 11</Address>\n" +
            "            <City>Davos</City>\n" +
            "            <Province_or_State>Graubunden</Province_or_State>\n" +
            "            <Postal_Code>7260</Postal_Code>\n" +
            "            <Country>Switzerland</Country>\n" +
            "        </Contact_Address>\n" +
            "    </Personnel>\n" +
            "\n" +
            "\n" +
            "    <Parameters>\n" +
            "        <Category>EARTH SCIENCE</Category>\n" +
            "        <Topic>ATMOSPHERE</Topic>\n" +
            "        <Term>ATMOSPHERIC WINDS</Term>\n" +
            "        <Variable_Level_1>SURFACE WINDS</Variable_Level_1>\n" +
            "        <Variable_Level_2>WIND SPEED/WIND DIRECTION</Variable_Level_2>\n" +
            "    </Parameters>\n" +
            "\n" +
            "    <Parameters>\n" +
            "        <Category>EARTH SCIENCE</Category>\n" +
            "        <Topic>ATMOSPHERE</Topic>\n" +
            "        <Term>ATMOSPHERIC TEMPERATURE</Term>\n" +
            "        <Variable_Level_1>SURFACE TEMPERATURE</Variable_Level_1>\n" +
            "        <Variable_Level_2>AIR TEMPERATURE</Variable_Level_2>\n" +
            "    </Parameters>\n" +
            "\n" +
            "    <Parameters>\n" +
            "        <Category>EARTH SCIENCE</Category>\n" +
            "        <Topic>ATMOSPHERE</Topic>\n" +
            "        <Term>ATMOSPHERIC WATER VAPOUR</Term>\n" +
            "        <Variable_Level_1>HUMIDITY</Variable_Level_1>\n" +
            "    </Parameters>\n" +
            "\n" +
            "    <Parameters>\n" +
            "        <Category>EARTH SCIENCE</Category>\n" +
            "        <Topic>ATMOSPHERE</Topic>\n" +
            "        <Term>ATMOSPHERIC TEMPERATURE</Term>\n" +
            "        <Variable_Level_1>SURFACE TEMPERATURE</Variable_Level_1>\n" +
            "        <Variable_Level_2>SKIN TEMPERATURE</Variable_Level_2>\n" +
            "    </Parameters>\n" +
            "    <Temporal_Coverage>\n" +
            "        <Start_Date>1996-09-01T02:00:00+0200</Start_Date>\n" +
            "        <Stop_Date>2015-08-03T01:30:00+0200</Stop_Date>\n" +
            "    </Temporal_Coverage>\n" +
            "\n" +
            "    <Spatial_Coverage>\n" +
            "        <Southernmost_Latitude>46.829598</Southernmost_Latitude>\n" +
            "        <Northernmost_Latitude>46.829598</Northernmost_Latitude>\n" +
            "        <Westernmost_Longitude>9.809568</Westernmost_Longitude>\n" +
            "        <Easternmost_Longitude>9.809568</Easternmost_Longitude>\n" +
            "    </Spatial_Coverage>\n" +
            "\n" +
            "    <Location>\n" +
            "        <Location_Category>Continent</Location_Category>\n" +
            "        <Location_Type>Europe</Location_Type>\n" +
            "        <Location_Subregion1>Switzerland</Location_Subregion1>\n" +
            "        <Location_Subregion2/>\n" +
            "        <Location_Subregion3/>\n" +
            "        <Detailed_Location>\n" +
            "            Weissfluhjoch research site, Davos, Switzerland\n" +
            "        </Detailed_Location>\n" +
            "    </Location>\n" +
            "\n" +
            "    <Data_Set_Language>EN</Data_Set_Language>\n" +
            "\n" +
            "    <Data_Center>\n" +
            "        <Data_Center_Name>\n" +
            "            <Short_Name>OSPER</Short_Name>\n" +
            "            <Long_Name>SwissEX/OSPER data platform</Long_Name>\n" +
            "        </Data_Center_Name>\n" +
            "        <Data_Center_URL>http://montblanc.slf.ch:22001/gsnweb/app/index.html\n" +
            "        </Data_Center_URL>\n" +
            "\n" +
            "        <Personnel>\n" +
            "            <Role>Data Center Contact</Role>\n" +
            "            <First_Name>OSPER</First_Name>\n" +
            "            <Last_Name>Admin</Last_Name>\n" +
            "            <Email>osper@slf.ch</Email>\n" +
            "            <Phone></Phone>\n" +
            "            <Fax></Fax>\n" +
            "            <Contact_Address>\n" +
            "                <Address>WSL Institute for Snow and Avalanche Research SLF</Address>\n" +
            "                <Address>Fluelastrasse 11</Address>\n" +
            "                <City>Davos</City>\n" +
            "                <Province_or_State>Graubunden</Province_or_State>\n" +
            "                <Postal_Code>7260</Postal_Code>\n" +
            "                <Country></Country>\n" +
            "            </Contact_Address>\n" +
            "        </Personnel>\n" +
            "    </Data_Center>\n" +
            "\n" +
            "    <Distribution>\n" +
            "        <Distribution_Media>URL</Distribution_Media>\n" +
            "        <Distribution_Format>CSV</Distribution_Format>\n" +
            "    </Distribution>\n" +
            "\n" +
            "    <Reference>\n" +
            "\n" +
            "    </Reference>\n" +
            "    <Summary>\n" +
            "        <Abstract>\n" +
            "Dataset of meteorological and snowpack measurements from the automatic weather station at Weissfluhjoch experimental site, Davos, Switzerland, suitable for driving snowpack models. The dataset contains standard meteorological measurements, and additionally snowpack runoff data from a snow lysimeter. Where possible, data is quality checked and missing data are replaced from backup sensors from the measurement site itself, or (in only a few cases) from the MeteoSwiss weather station at 470m distance and 150m above the measurement site.\n" +
            "\n" +
            "The experimental site at the Weissfluhjoch (WFJ, 46.83 N, 9.81 E) is located at an altitude of 2540 m in the Swiss Alps near Davos. During the winter months, almost all precipitation falls as snow at this altitude. As a consequence, a continuous seasonal snow cover builds up every winter, with a maximum snow height ranging from 153--366 cm over the period 1934--2012. The measurement site is located in an almost flat part of a southeast oriented slope.\n" +
            "\n" +
            "LICENSE: This dataset is made available under the Open Database License: http://opendatacommons.org/licenses/odbl/1.0/. Any rights in individual contents of the database are licensed under the Database Contents License: http://opendatacommons.org/licenses/dbcl/1.0/. (C) Copyright WSL Institute for Snow and Avalanche Research SLF.\n" +
            "        </Abstract>\n" +
            "    </Summary>\n" +
            "\n" +
            "    <Related_URL>\n" +
            "        <URL_Content_Type>\n" +
            "            <Type/>\n" +
            "        </URL_Content_Type>\n" +
            "        <URL>\n" +
            "            http://montblanc.slf.ch:22001/gsnweb/app/index.html#/plot?sensors=wfj_vf_models\n" +
            "        </URL>\n" +
            "        <Description>Documentation</Description>\n" +
            "    </Related_URL>\n" +
            "    <Related_URL>\n" +
            "        <URL_Content_Type>\n" +
            "            <Type>GET DATA</Type>\n" +
            "        </URL_Content_Type>\n" +
            "        <URL>\n" +
            "            http://montblanc.slf.ch:22001/gsnweb/app/index.html#/plot?sensors=wfj_vf_models\n" +
            "        </URL>\n" +
            "        <Description>URL</Description>\n" +
            "    </Related_URL>\n" +
            "  <Related_URL>\n" +
            "        <URL_Content_Type>\n" +
            "            <Type>VIEW EXTENDED METADATA</Type>\n" +
            "        </URL_Content_Type>\n" +
            "        <URL>\n" +
            "            NA\n" +
            "        </URL>\n" +
            "        <Description>URL</Description>\n" +
            "    </Related_URL>\n" +
            "\n" +
            "    <Metadata_Name>CEOS IDN DIF</Metadata_Name>\n" +
            "\n" +
            "    <Metadata_Version>VERSION 9.9</Metadata_Version>\n" +
            "\n" +
            "    <DIF_Creation_Date>2015-09-25</DIF_Creation_Date>\n" +
            "\n" +
            "    <Last_DIF_Revision_Date>$MODIFYTIME</Last_DIF_Revision_Date>\n" +
            "\n" +
            "    <DIF_Revision_History>\n" +
            "\n" +
            "    </DIF_Revision_History>\n" +
            "\n" +
            "\n" +
            "</DIF>";
}