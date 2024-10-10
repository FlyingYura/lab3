package droids;

public class BattleDroid extends BaseDroid {
    public BattleDroid(String name) {
        super(name, 100, 20);
    }

    @Override
    public String getDroidType() {
        return "Battle Droid";
    }
}
