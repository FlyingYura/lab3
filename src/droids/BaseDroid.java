package droids;

public abstract class BaseDroid {
    protected String name;
    protected int health;
    protected int damage;
    protected int specialDamage;
    protected int abilityUses;

    public BaseDroid(String name, int health, int damage, int specialDamage, int abilityUses) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.specialDamage = specialDamage;
        this.abilityUses = abilityUses;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public void attack(BaseDroid opponent) {
        opponent.takeDamage(this.damage);
        System.out.println(name + " атакує " + opponent.getName() + " на " + damage + " dmg");
    }

    public void useSpecialAbility(BaseDroid opponent) {
        if (abilityUses > 0) {
            opponent.takeDamage(specialDamage);
            abilityUses--;
            System.out.println(name + " використовує спеціальне вміння " + opponent.getName() + " на " + specialDamage + " dmg");
        } else {
            System.out.println(name + " Не може більше використовувати спеціальне вміння!");
        }
    }

    public abstract String getDroidType();
}
