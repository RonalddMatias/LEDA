import java.util.ArrayList;

public class ClasseA implements A<String> {
    ArrayList<String> classes = new ArrayList<String>();

    @Override
    public void metodo(String o) {
        classes.add(o);
    }

}
