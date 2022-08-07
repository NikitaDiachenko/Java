import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        ArrayList<Phone> phones = new ArrayList<>();
        phones.add(new iOSPhone("Iphone 13 Pro", "eqweqw234234wrer",
                "15", "660x800", "ios", "Anton", true, true, "1235ddfsd32"));
        phones.add(new AndroidPhone("S22", "xxxcc2123ddcd",
                "17", "700x800", "android", "", false, true, false));
        phones.add(new AndroidPhone("s9", "asdzxcas", "13", "400x400", "android",
                "", false, true, true));
        phones.add(new iOSPhone("Iphone 12", "asd321as32512", "15", "760x860", "ios",
                "", false, true, "xxxxxxxx"));
        //sort by operating system
        phones.sort(Comparator.comparing(Phone::getOperatingSystem));
        for(Phone i: phones){
            System.out.println(i.objectToString());
        }
        System.out.println("\n");
        // sort by name
        phones.sort(Comparator.comparing(Phone::getName));
        for(Phone i: phones){
            System.out.println(i.objectToString());
        }
        System.out.println("\n");
        //sort by displaySize
        phones.sort(Comparator.comparing(Phone::getDisplaySize));
        for(Phone i: phones){
            System.out.println(i.objectToString());
        }
        // output single object
        System.out.println("\n" + phones.get(0).objectToString());

        //structured output
        System.out.println("\n" + phones.get(0).objectBeautifyOutput());

        //vibrate parent method
        phones.get(0).vibrate();

    }
}
