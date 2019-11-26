package creational.builder;

/**
 * Title: 构造者模式 Builder Pattern
 * Desc: 何时： 一个类是由很复杂的对象组合而成
 * 建造者返还给客户端的是一个已经建造完毕的完整产品对象，而用户无须关心该对象所包含的属性以及它们的组装方式，这就是建造者模式的模式动机。
 * Created by Myth-Lab on 11/26/2019
 */
// 套餐例子

//    Builder：抽象建造者
//    ConcreteBuilder：具体建造者
//    Director：指挥者
//    Product：产品角色
class Burger {
    private String name;
    private float price;

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Burger: " + name + " Price" + price;
    }
}
class Drink {
    private String name;
    private float price;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Drink: " + name + " Price" + price;
    }
}
class Meal {
    private Burger burger;
    private Drink drink;

    public void setBurger(Burger burger) {
        this.burger = burger;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }
    public void showMeal() {
        System.out.println("Meal:" + burger + drink);
    }
}
abstract class Builder {

    abstract void addBurger();
    abstract void addDrink();
    abstract Meal getMeal();
}
class MealABuilder extends Builder {
    private Meal meal = new Meal();
    @Override
    void addBurger() {
        Burger burger = new Burger();
        burger.setName("BurgerA");
        burger.setPrice(2.8f);
        meal.setBurger(burger);
    }

    @Override
    void addDrink() {
        Drink drink = new Drink();
        drink.setName("DrinkA");
        drink.setPrice(1.8f);
        meal.setDrink(drink);
    }

    @Override
    Meal getMeal() {
        return meal;
    }
}
class MealBBuilder extends Builder {
    private Meal meal = new Meal();
    @Override
    void addBurger() {
        Burger burger = new Burger();
        burger.setName("BurgerB");
        burger.setPrice(6.6f);
        meal.setBurger(burger);
    }

    @Override
    void addDrink() {
        Drink drink = new Drink();
        drink.setName("DrinkB");
        drink.setPrice(2.6f);
        meal.setDrink(drink);
    }
    @Override
    Meal getMeal() {
        return meal;
    }
}
class Waiter {
    private Builder mealBuilder;

    public void setMealBuilder(Builder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }
    public Meal produceMeal() {
        mealBuilder.addBurger();
        mealBuilder.addDrink();
        return mealBuilder.getMeal();
    }
}
public class BuilderDemo {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        Builder mealABuilder = new MealABuilder();
        waiter.setMealBuilder(mealABuilder);
        Meal meal = waiter.produceMeal();
        meal.showMeal();
    }

}
