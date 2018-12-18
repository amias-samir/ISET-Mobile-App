package np.com.naxa.iset.utils.sectionmultiitemUtils;

import java.util.ArrayList;
import java.util.List;

public class DataServer {

    public static List<SectionMultipleItem> getSectionMultiData() {
        List<SectionMultipleItem> list = new ArrayList<>();
//        MultiItemSectionModel video = new MultiItemSectionModel(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD);

        // add section data
        list.add(new SectionMultipleItem(true, "Kathmandu Metropolitian City", false, false));
        // add multiple type item data ---start---
//        list.add(new SectionMultipleItem(SectionMultipleItem.IMG, new MultiItemSectionModel("http://3.bp.blogspot.com/-SsnyMNpjZWA/UvpdMrkMb-I/AAAAAAAAAGE/8iuSHzis858/s1600/Landuse+Zoning+Map.jpg","", "")));
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG, new MultiItemSectionModel("https://www.researchgate.net/profile/Johannes_Anhorn/publication/275581818/figure/fig2/AS:393509669490688@1470831424593/Distribution-of-open-spaces-in-Kathmandu-Metropolitan-City.png","", "")));
        // ---end---

        list.add(new SectionMultipleItem(true, "General Information", false, false));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Location", "Kathmandu District")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Website", "kathmanduMetro.com.np")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Email", "kathmandumetro@gmail.com")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total Houses", "1,23,45,665")));

        list.add(new SectionMultipleItem(true, "Counts", false, false));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Male", "54564564")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Female", "445445")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total population", "564645645")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Houses", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Hospital", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Schools", "1,23,45,665")));

        list.add(new SectionMultipleItem(true, "Information", false, false));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Male", "54564564")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Female", "445445")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total population", "564645645")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Houses", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Hospital", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Schools", "1,23,45,665")));

        return list;
    }


    public static List<SectionMultipleItem> getThingsToDoBefore() {
        List<SectionMultipleItem> list = new ArrayList<>();
//        MultiItemSectionModel video = new MultiItemSectionModel(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD);

        // add section data

        list.add(new SectionMultipleItem(true, "Always be ready with emergency kit", false, true));
        // add multiple type item data ---start---

        list.add(new SectionMultipleItem(SectionMultipleItem.IMG_TEXT, new MultiItemSectionModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzVmAv8RpDE5LX-5TnDHICNSaEnNL1TuDra4UczpDKiXvWsULP", "Bag Pack", "Kathmandu District")));
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG_TEXT, new MultiItemSectionModel("https://media.istockphoto.com/photos/referee-whistle-picture-id520288272?k=6&m=520288272&s=612x612&w=0&h=pwECJTMFoVYt56JkZevfGJb3CirlP7t05MkUoOLPk1s=",  "Whistle", "kathmanduMetro.com.np")));
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG_TEXT, new MultiItemSectionModel("https://previews.123rf.com/images/dariy/dariy1612/dariy161200020/69684345-old-radio-isolated-on-white-background-style-50-ies-of-the-19th-century.jpg",  "Radio", "kathmanduMetro.com.np")));
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG_TEXT, new MultiItemSectionModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS78sds-Gau5fd1Xtd_sRmXxeWq0O-dAncNyjfsIJLYgLKcAZbO", "Tourch Light", "1,23,45,665")));

        list.add(new SectionMultipleItem(true, "Plan about the safe place with family", false, true));
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG, new MultiItemSectionModel("https://www.researchgate.net/profile/Johannes_Anhorn/publication/275581818/figure/fig2/AS:393509669490688@1470831424593/Distribution-of-open-spaces-in-Kathmandu-Metropolitan-City.png","", "")));
        // ---end---

        return list;
    }

    public static List<SectionMultipleItem> getThingsToDoWhenHappens() {
        List<SectionMultipleItem> list = new ArrayList<>();
//        MultiItemSectionModel video = new MultiItemSectionModel(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD);

        // add section data
        list.add(new SectionMultipleItem(true, "Kathmandu Metropolitian City", false, true));
        // add multiple type item data ---start---
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG, new MultiItemSectionModel("https://www.researchgate.net/profile/Johannes_Anhorn/publication/275581818/figure/fig2/AS:393509669490688@1470831424593/Distribution-of-open-spaces-in-Kathmandu-Metropolitan-City.png","", "")));
        // ---end---

        list.add(new SectionMultipleItem(true, "General Information", false, true));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Location", "Kathmandu District")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Website", "kathmanduMetro.com.np")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Email", "kathmandumetro@gmail.com")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total Houses", "1,23,45,665")));

        list.add(new SectionMultipleItem(true, "Counts", false, true));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Male", "54564564")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Female", "445445")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total population", "564645645")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Houses", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Hospital", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Schools", "1,23,45,665")));



        return list;
    }

    public static List<SectionMultipleItem> getThingsToDoAfter() {
        List<SectionMultipleItem> list = new ArrayList<>();
//        MultiItemSectionModel video = new MultiItemSectionModel(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD);

        // add section data
        list.add(new SectionMultipleItem(true, "Kathmandu Metropolitian City", false, true));
        // add multiple type item data ---start---
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG, new MultiItemSectionModel("https://www.researchgate.net/profile/Johannes_Anhorn/publication/275581818/figure/fig2/AS:393509669490688@1470831424593/Distribution-of-open-spaces-in-Kathmandu-Metropolitan-City.png","", "")));
        // ---end---

        list.add(new SectionMultipleItem(true, "General Information", false, true));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Location", "Kathmandu District")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Website", "kathmanduMetro.com.np")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Email", "kathmandumetro@gmail.com")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total Houses", "1,23,45,665")));

        return list;
    }
}
