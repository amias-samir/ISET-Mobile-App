package np.com.naxa.iset.profile.municipalityprofile;

import java.util.ArrayList;
import java.util.List;

public class DataServer {

    public static List<MunicipalitySectionMultipleItem> getSectionMultiData() {
        List<MunicipalitySectionMultipleItem> list = new ArrayList<>();
//        MunicipalityProfileModel video = new MunicipalityProfileModel(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD);

        // add section data
        list.add(new MunicipalitySectionMultipleItem(true, "Kathmandu Metropolitian City", false));
        // add multiple type item data ---start---
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.IMG, new MunicipalityProfileModel("http://3.bp.blogspot.com/-SsnyMNpjZWA/UvpdMrkMb-I/AAAAAAAAAGE/8iuSHzis858/s1600/Landuse+Zoning+Map.jpg","", "")));
        // ---end---

        list.add(new MunicipalitySectionMultipleItem(true, "General Information", false));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Location", "Kathmandu District")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Website", "kathmanduMetro.com.np")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Email", "kathmandumetro@gmail.com")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Total Houses", "1,23,45,665")));

        list.add(new MunicipalitySectionMultipleItem(true, "Counts", false));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Male", "54564564")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Female", "445445")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Total population", "564645645")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Houses", "1,23,45,665")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Hospital", "1,23,45,665")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Schools", "1,23,45,665")));

        list.add(new MunicipalitySectionMultipleItem(true, "Information", false));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Male", "54564564")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Female", "445445")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Total population", "564645645")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Houses", "1,23,45,665")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Hospital", "1,23,45,665")));
        list.add(new MunicipalitySectionMultipleItem(MunicipalitySectionMultipleItem.TEXT, new MunicipalityProfileModel("", "Schools", "1,23,45,665")));

        return list;
    }
}
