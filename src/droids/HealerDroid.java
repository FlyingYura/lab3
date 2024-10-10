package droids;

public class HealerDroid extends BaseDroid {
    public HealerDroid(String name) {
        super(name, 120, 10, 30, 10);
    }

    @Override
    public String getDroidType() {
        return "Healer Droid";
    }

    @Override
    public void useSpecialAbility(BaseDroid target) {
        if (abilityUses > 0) {
            target.health += this.specialDamage;
            abilityUses--;
            System.out.println(name + " лікує " + target.getName() + " на " + specialDamage + " очків здоров'я.");
        } else {
            System.out.println(name + " В лікаря більше немає мани, вміння не можна більше використовувати.");
        }
    }
}
