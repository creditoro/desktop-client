package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.credit.ICreditModel;
import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.model.user.IUserModel;
import dk.creditoro.client.view.browse_channels.BrowseChannelsViewModel;
import dk.creditoro.client.view.browse_productions.BrowseProductionsViewModel;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.logging.Logger;

public class AddCreditViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final ICreditModel creditModel;
    private final IUserModel userModel;
    private final ViewModelFactory viewModelFactory;

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
        this.viewModelFactory = viewModelFactory;
        this.creditModel = creditModel;
        this.userModel = userModel;
    }

    public void setProductionTitle(String productionTitle) {
        this.productionTitle = productionTitle;
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

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getProductionTitle() {
        return productionTitle;
    }

    public void refreshValues() {

        // Set channelName and productionTitle for chosen production
        Platform.runLater(() -> {
            channelNameTxtField.setText(getChannelName());
            productionTitleTxtField.setText(getProductionTitle());
        });

        // Set credits for chosen production
        for (Credit cred : listProperty) {
            Platform.runLater(() -> creditsTxtArea.appendText(cred.getPerson().getName() + "\t" + cred.getJob() + "\n"));
        }
    }
}
