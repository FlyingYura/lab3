package droids;

public abstract class BaseDroid {
    protected String name;
    protected int health;
    protected int damage;

    public BaseDroid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
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
        System.out.println(name + " Attacks " + opponent.getName() + " for " + damage + "damage");
    }

    public abstract String getDroidType();
}
