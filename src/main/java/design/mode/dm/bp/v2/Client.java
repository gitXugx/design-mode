package design.mode.dm.bp.v2;

/**
 * @author ：ex-xugaoxiang001
 * @description ：建造者模式
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/25 16:46
 */
public class Client {

    public  static void main(String[] args){

        System.out.println("中国套餐:");
        Menu ch = Menu.ProductBuild.builder()
                .dishesList(new FriedChicken())
                .foodList(new Rice())
                .drinksList(new OrangeJuice()).build();
        ch.showMenu();

        System.out.println("英国套餐:");
        Menu eh = Menu.ProductBuild.builder()
                .dishesList(new RoastedBeef())
                .foodList(new Bread())
                .drinksList(new OrangeJuice()).build();
        eh.showMenu();

    }

}
