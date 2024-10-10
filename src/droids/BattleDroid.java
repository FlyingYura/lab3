package droids;

public class BattleDroid extends BaseDroid {
    public BattleDroid(String name) {
        super(name, 100, 20, 40, 2);
    }

    @Override
    public String getDroidType() {
        return "Battle Droid";
    }

    @Override
    public void useSpecialAbility(BaseDroid opponent) {
        super.useSpecialAbility(opponent);
    }
}
