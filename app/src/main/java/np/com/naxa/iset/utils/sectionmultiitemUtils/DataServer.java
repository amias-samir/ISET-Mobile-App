package np.com.naxa.iset.utils.sectionmultiitemUtils;

import java.util.ArrayList;
import java.util.List;

public class DataServer {

    public static List<SectionMultipleItem> getSectionMultiData() {
        List<SectionMultipleItem> list = new ArrayList<>();
//        MultiItemSectionModel video = new MultiItemSectionModel(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD);

        // add section data
        list.add(new SectionMultipleItem(true, "Kathmandu Metropolitian City", false));
        // add multiple type item data ---start---
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG, new MultiItemSectionModel("http://3.bp.blogspot.com/-SsnyMNpjZWA/UvpdMrkMb-I/AAAAAAAAAGE/8iuSHzis858/s1600/Landuse+Zoning+Map.jpg","", "")));
        // ---end---

        list.add(new SectionMultipleItem(true, "General Information", false));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Location", "Kathmandu District")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Website", "kathmanduMetro.com.np")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Email", "kathmandumetro@gmail.com")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total Houses", "1,23,45,665")));

        list.add(new SectionMultipleItem(true, "Counts", false));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Male", "54564564")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Female", "445445")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total population", "564645645")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Houses", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Hospital", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Schools", "1,23,45,665")));

        list.add(new SectionMultipleItem(true, "Information", false));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Male", "54564564")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Female", "445445")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total population", "564645645")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Houses", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Hospital", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Schools", "1,23,45,665")));

        return list;
    }
}
