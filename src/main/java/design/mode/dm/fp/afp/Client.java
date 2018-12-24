package design.mode.dm.fp.afp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/24 16:03
 */
public class Client {
    public  static void main(String[] args){
        Factory adidasFactory =  new AdidasFactory();
        IShoe shoe = adidasFactory.createShoe();
        shoe.showShoe();
        IClothes adidasClothes = adidasFactory.createClothes();
        adidasClothes.showClothes();
        Factory jordonFactory =  new JordonFactory();
        IShoe jordonShoe = jordonFactory.createShoe();
        jordonShoe.showShoe();

        IClothes jordonClothes = jordonFactory.createClothes();
        jordonClothes.showClothes();



    }
}
