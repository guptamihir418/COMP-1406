
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.*;
import javafx.scene.input.*;


public class ElectronicStoreApp extends Application {
    ElectronicStoreView view;
    private int quantity = 0;
    ElectronicStore model;



    public void start(Stage cart){
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);



        cart.setTitle("Electronic Store App");
        cart.setResizable(false);
        cart.setScene(new Scene(view,800,400));
        cart.show();



        view.getAddCart().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleAdd();
            }
        });

        view.getItemList().setOnMouseReleased(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent mouseEvent) {
                handleProduct();

            }
        });

        view.getCartList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                handleCart();
            }
        });

        view.getRemoveCart().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleRemove();
            }
        });

        view.getCompleteSale().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleComplete();
            }
        });

        view.getResetStore().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {

                handleReset();
            }
        });


    }



    public static void main(String[] args) { launch(args); }

    public void handleAdd() {
        int index = view.getItemList().getSelectionModel().getSelectedIndex();
        view.buy.addProduct(view.model.stock[index]);
        view.model.sellProducts(index, 1);
        view.update();


    }


    public void handleProduct(){
        view.update();
    }


    public void handleCart(){
        view.update();
    }


    public void handleRemove(){

        int index = view.getCartList().getSelectionModel().getSelectedIndex();
        for (int i =0 ; i < view.buy.getCurProducts();i++){
            if (view.model.stock[i] == view.buy.stock[index]){
                view.model.buyProducts(i,1);
            }
        }


        view.buy.removeProducts(index);
        view.update();
    }


    public void handleReset(){
        ElectronicStore update = ElectronicStore.createStore();
        quantity = 0;
        //view.getCurrentCart().setText("Current Cart ($0)");
        model = update;
        view.updateFields(model);
        view.update();
        view.updateFields(model);
    }


    public void handleComplete(){
        quantity++;
        String[] temp = new String[0];
        view.get$PerSale().setText("0.0");
        view.getCurrentCart().setText("Current Cart:");
        view.getSales().setText(String.valueOf(quantity));
        view.getRevenue().setText(String.valueOf(view.model.getRevenue()));
        view.get$PerSale().setText(String.valueOf(view.model.getRevenue()/quantity));
        int Num = view.buy.getCurProducts();
        view.setPopProducts(view.Popular(view.model));
        for(int i = 0; i< Num; i++){
            view.buy.removeProducts(0);}
        view.update();
        view.Popular(view.model);

    }




}
