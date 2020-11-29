import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ElectronicStoreView extends Pane {


    private Button resetStore,addCart,removeCart,completeSale;
    private ListView<String> itemList,popularList,cartList;
    private TextField sales,$PerSale,revenue;
    private Label storeSummary,storeStock,currentCart,sale,rev,perSale,popItem;
    private String[] desc, cartDesc, popProducts;



    ElectronicStore model = new ElectronicStore("");
    ElectronicStore buy = new ElectronicStore("");


    public ElectronicStoreView(ElectronicStore iModel){
        model = iModel;



        //creating buttons
        resetStore = new Button("Reset Store");
        resetStore.relocate(10,340);
        resetStore.setPrefSize(150,50);
        getChildren().add(resetStore);


        addCart = new Button("Add To Cart");
        addCart.relocate(240,340);
        addCart.setPrefSize(150,50);
        addCart.setDisable(true);
        getChildren().add(addCart);


        removeCart = new Button("Remove From Cart");
        removeCart.relocate(470,340);
        removeCart.setPrefSize(150,50);
        removeCart.setDisable(true);
        getChildren().add(removeCart);

        completeSale = new Button("Complete Sale");
        completeSale.relocate(620,340);
        completeSale.setPrefSize(150,50);
        completeSale.setDisable(true);
        getChildren().add(completeSale);



        //creating lists of items
        itemList = new ListView<String>();
        itemList.relocate(180, 30);
        itemList.setPrefSize(290,300);

        getChildren().add(itemList);



        popularList = new ListView<String>();
        popularList.relocate(5, 180);
        popularList.setPrefSize(170,150);
        getChildren().add(popularList);


        cartList = new ListView<String>();
        cartList.relocate(490, 30);
        cartList.setPrefSize(290,300);
        getChildren().add(cartList);



        //creating text fields
        sales = new TextField("0");
        sales.relocate(100, 30);
        sales.setPrefSize(75,25);
        getChildren().add(sales);


        revenue = new TextField("0.0");
        revenue.relocate(100, 60);
        revenue.setPrefSize(75,25);
        getChildren().add(revenue);


        $PerSale = new TextField("N/A");
        $PerSale.relocate(100, 90);
        $PerSale.setPrefSize(75,25);
        getChildren().add($PerSale);



        //creating labels
        storeSummary = new Label("Store Summary");
        storeSummary.relocate(50,0);
        storeSummary.setPrefSize(100,40);
        getChildren().add(storeSummary);


        storeStock = new Label("Store Stock");
        storeStock.relocate(280,0);
        storeStock.setPrefSize(100,40);
        getChildren().add(storeStock);

        currentCart = new Label("Current Cart");
        currentCart.relocate(590,0);
        currentCart.setPrefSize(150,40);
        getChildren().add(currentCart);



        sale = new Label("# Sale:");
        sale.relocate(30,22);
        sale.setPrefSize(100,40);
        getChildren().add(sale);

        rev = new Label("Revenue:");
        rev.relocate(30,53);
        rev.setPrefSize(100,40);
        getChildren().add(rev);

        perSale = new Label("$ / Sale:");
        perSale.relocate(30,82);
        perSale.setPrefSize(100,40);
        getChildren().add(perSale);

        popItem = new Label("Most Popular items");
        popItem.relocate(30,130);
        popItem.setPrefSize(150,40);
        getChildren().add(popItem);



        popProducts = new String[3];
        popProducts = Popular(model);
        update();

    }

    public void setPopProducts(String[] temp){
        for (int i = 0; i<3; i++){
            popProducts[i]=temp[i];
        }
    }

    public void update(){


        int i=0;

        desc = new String[model.getCurProducts()];
        for (i=0;i<model.getCurProducts();i++){
            if (model.stock[i].getStockQuantity()>0){
                desc[i]= model.stock[i].toString();
            }
            else{
                desc[i]="";
                i++;
            }
        }

        itemList.setItems(FXCollections.observableArrayList(desc));



        cartDesc = new String[buy.getCurProducts()];
        for (i = 0; i < buy.getCurProducts(); i++) {
            cartDesc[i] = buy.stock[i].toString();
        }
        cartList.setItems(FXCollections.observableArrayList(cartDesc));


        int index = itemList.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            addCart.setDisable(true);
        } else {
            addCart.setDisable(false);
        }
        index = cartList.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            removeCart.setDisable(true);
        } else {
            removeCart.setDisable(false);
        }


        if (cartDesc.length == 0) {
            completeSale.setDisable(true);

        } else {
            completeSale.setDisable(false);
        }

        int sum = 0;
        for (i = 0; i < buy.getCurProducts(); i++) {
            sum  += buy.stock[i].getPrice();
        }
        currentCart.setText("Current Cart ($" + String.valueOf(sum) + ")");
        popularList.setItems(FXCollections.observableArrayList(popProducts));


        //sorting(desc);

    }





    public String[] Popular(ElectronicStore mod) {
        int j = 0;
        int check = 0;
        Product[] temp1 = new Product[mod.stock.length];
        for (int i = 0; i < mod.stock.length; i++) {
            temp1[i] = mod.stock[i];
        }
        String[] temp = new String[3];
        while (j < 3) {
            for (int i = 0; i < mod.getCurProducts(); i++) {
                if (temp1[check].getSoldQuantity() < temp1[i].getSoldQuantity()) {
                    check = i;
                }

            }
            temp[j] = temp1[check].toString();
            if (check == 0) {
                temp1[check] = temp1[check + 1];
            } else {
                temp1[check] = temp1[check - 1];
            }

            j++;
        }
        return temp;

    }



    public void updateFields(ElectronicStore update) {

        //for(int i =0; i<10;i++){
            //cartDesc[i]= null;
        //}



        sales.setText("0");
        revenue.setText("0.00");
        $PerSale.setText("N/A");
        desc = null;
        currentCart.setText("Current Cart ($0)");
        model = update;
    }


    public ArrayList<String> updateStockList(){
        Product[] products = model.getStock();
        ArrayList<String> stock = new ArrayList<>();

        for(int i =0; i<model.getCurProducts();i++){
            if (products[i].getStockQuantity()>0){
                stock.add(products[i].toString());

            }
            else{
                stock.remove(products[i]);

            }
        }
        return stock;
    }

    public Button getAddCart() {
        return addCart;
    }

    public Button getCompleteSale() {
        return completeSale;
    }


    public Button getRemoveCart() {
        return removeCart;
    }

    public Button getResetStore() {
        return resetStore;
    }

    public TextField get$PerSale() {
        return $PerSale;
    }

    public ListView<String> getItemList() {
        return itemList;
    }

    public ListView<String> getCartList() {
        return cartList;
    }

    public ListView<String> getPopularList() {
        return popularList;
    }


    public Label getCurrentCart() {
        return currentCart;
    }



    public TextField getRevenue() {
        return revenue;
    }

    public TextField getSales() {
        return sales;
    }

    public ElectronicStore getModel() {
        return model;
    }


    public String[] sorting(String array[]){

        String temp[] = new String[0];

        boolean flag= true;
        while (flag){
            flag = false;
            for(int i=0;i<array[i].length();i++){
                if (array[i]==null){
                    temp[i]= array[i];
                    array[i]=array[i+1];
                    array[i+1]=temp[i];
                    flag = true;
                }
            }
        }

        return array;
    }
}
