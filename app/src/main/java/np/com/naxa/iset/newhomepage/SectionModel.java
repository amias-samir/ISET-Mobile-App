package np.com.naxa.iset.newhomepage;

import java.util.ArrayList;

/**
 * Created by samir on 01/12/18..
 */

public class SectionModel {
    private String sectionLabel;
    private ArrayList<String> itemArrayList;

    public SectionModel(String sectionLabel, ArrayList<String> itemArrayList) {
        this.sectionLabel = sectionLabel;
        this.itemArrayList = itemArrayList;
    }

    public String getSectionLabel() {
        return sectionLabel;
    }

    public ArrayList<String> getItemArrayList() {
        return itemArrayList;
    }
}
