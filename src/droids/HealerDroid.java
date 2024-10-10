package droids;

public class HealerDroid extends BaseDroid {
    private int healingPower;

    public HealerDroid(String name) {
        super(name, 60, 10);
        this.healingPower = 15;
    }

    public void heal(BaseDroid ally) {
        if (ally.isAlive()) {
            ally.health += healingPower;
            System.out.println(name + " heals " + ally.getName() + " for " + healingPower + " health");
        } else {
            System.out.println(ally.getName() + " Is already dead, and can't be healed anymore.");
        }
    }

    @Override
    public void attack(BaseDroid opponent) {
        super.attack(opponent);
    }

    @Override
    public String getDroidType() {
        return "Healer";
    }
}
