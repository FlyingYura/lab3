package droids;

public class SniperDroid extends BaseDroid {
    public SniperDroid(String name) {
        super(name, 80, 30);
    }

    @Override
    public String getDroidType() {
        return "Sniper";
    }
}
