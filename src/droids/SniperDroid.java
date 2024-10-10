package droids;

public class SniperDroid extends BaseDroid {
    public SniperDroid(String name) {
        super(name, 80, 30, 50, 3);
    }

    @Override
    public String getDroidType() {
        return "Sniper";
    }

    @Override
    public void useSpecialAbility(BaseDroid opponent) {
        super.useSpecialAbility(opponent);
    }
}
