package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.credit.ICreditModel;
import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.model.user.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddCreditViewModel {
    private final ObservableList<Credit> creditList = FXCollections.observableArrayList();
    private final ListProperty<Credit> listProperty = new SimpleListProperty<>(creditList);

    private String channelName;
    private String productionTitle;
    @FXML
    private TextField channelNameTxtField;
    @FXML
    private TextField productionTitleTxtField;
    @FXML
    private TextArea creditsTxtArea;

    public AddCreditViewModel(ICreditModel creditModel, IUserModel userModel, ViewModelFactory viewModelFactory) {
    }

    public void setChannelNameTxtField(TextField channelNameTxtField) {
        this.channelNameTxtField = channelNameTxtField;
    }

    public void setProductionTitleTxtField(TextField productionTitleTxtField) {
        this.productionTitleTxtField = productionTitleTxtField;
    }

    public void setCreditsTxtArea(TextArea creditsTxtArea) {
        this.creditsTxtArea = creditsTxtArea;
    }

    public void setListProperty(ObservableList<Credit> listProperty) {
        this.listProperty.set(listProperty);
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getProductionTitle() {
        return productionTitle;
    }

    public void setProductionTitle(String productionTitle) {
        this.productionTitle = productionTitle;
    }

    private void addCreditsToTextArea() {
        for (Credit cred : listProperty) {
            creditsTxtArea.appendText(cred.getPerson().getName() + "\t" + cred.getJob() + "\n");
        }
    }

    public void refreshValues() {
        // Set channelName, productionTitle and credits for chosen production
        Platform.runLater(() -> {
            channelNameTxtField.setText(getChannelName());
            productionTitleTxtField.setText(getProductionTitle());
            addCreditsToTextArea();
        });
    }
}
