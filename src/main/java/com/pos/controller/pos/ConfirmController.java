package com.pos.controller.pos;

import com.pos.entity.Item;
import com.pos.pdf.PrintInvoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmController implements Initializable {

    @FXML
    private TextArea billingArea;
    @FXML
    private Label retailLabel;
    private double retail;
    private ObservableList<Item> items;
    private String barcode;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        retailLabel.setText("Change: RS." + retail);

        StringBuilder details = new StringBuilder("Item Name\t\t" + "Cost\t\t" + "Quantity\t\t" + "Total\n");

        for (Item i : items) {
            details.append(i.getItemName())
                    .append("\t\t\t")
                    .append(i.getUnitPrice())
                    .append("\t\t\t")
                    .append(i.getQuantity())
                    .append("\t\t\t")
                    .append(i.getTotal())
                    .append("\n");
        }

        billingArea.setText(details.toString());
    }

    public void setData(double retail, ObservableList<Item> items, String barcode) {
        this.retail = retail;
        this.items = FXCollections.observableArrayList(items);
        this.barcode = barcode;
    }

    @FXML
    public void doneAction(ActionEvent event) {
        billingArea.setText("");
        PrintInvoice pi = new PrintInvoice(items, barcode);
        pi.generateReport();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void closeAction(ActionEvent actionEvent) {
    }
}
