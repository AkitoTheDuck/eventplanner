package DataWrapperSorter;

import DataWrapper.Company;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Christian
 */
public class CompanySorter {

    public void sortByStudentsCount(ArrayList<Company> list) {
        list.sort(new Comparator<Company>() {
            @Override
            public int compare(Company c1, Company c2) {
                return Integer.compare(c2.getSchuelerListe().size(), c1.getSchuelerListe().size());
            }
        });

    }

    public void sortByNr(ArrayList<Company> list) {
        list.sort(new Comparator<Company>() {
            @Override
            public int compare(Company c1, Company c2) {
                return Integer.compare(Integer.parseInt(c1.getNr()), Integer.parseInt(c2.getNr()));
            }
        });

    }

}
