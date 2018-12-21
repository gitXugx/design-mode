package design.mode.dm.fp.sfp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/21 14:51
 */
public class Client {
    public  static void main(String[] args){
        Car a = SimpleFactory.createCar(CompactCar.class);
        System.out.println(a);
        Car b = SimpleFactory.createCar(SUVCar.class);
        System.out.println(b);
    }
}
