import be.euromoon.persoon.Personeelsleed;
import be.euromoon.persoon.Persoon;
import be.euromoon.persoon.TypePersoneel;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    Personeelsleed p = new Personeelsleed("Lilian", "Levano", "123-123", LocalDate.now(), TypePersoneel.CONDUCTEURS);
    System.out.println(p.getVasteRol());
}
