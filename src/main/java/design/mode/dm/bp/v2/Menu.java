package design.mode.dm.bp.v2;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/25 16:25
 */
public class Menu {

    private Drinks drinksList;

    private Dishes dishesList;

    private Food foodList;

    public static class ProductBuild{

        private Drinks drinksList;

        private Dishes dishesList;

        private Food foodList;

        public ProductBuild drinksList(Drinks drinksList) {
            this.drinksList = drinksList;
            return this;
        }

        public ProductBuild dishesList(Dishes dishesList) {
            this.dishesList = dishesList;
            return this;

        }

        public ProductBuild foodList(Food foodList) {
            this.foodList = foodList;
            return this;
        }

        private ProductBuild(){}

        public static ProductBuild builder(){
            return new ProductBuild();
        }

        public Menu build(){
            Menu menu = new Menu();
            menu.dishesList =  this.dishesList;
            menu.drinksList = this.drinksList;
            menu.foodList = this.foodList;
            return menu;
        }
    }

    public void showMenu(){
        if(foodList != null){
            foodList.showFood();
        }
        if(drinksList != null){
            drinksList.showSmell();
        }
        if(dishesList != null){
            dishesList.showDishes();
        }
    }

}
