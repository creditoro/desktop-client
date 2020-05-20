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

    private String channelId;
    private String productionId;
    private String productionTitle;
    private TextField channelNameTxtField;
    private TextField productionTitleTxtField;
    private TextArea creditsTxtArea;

    public AddCreditViewModel(ICreditModel creditModel, IUserModel userModel, ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.creditModel = creditModel;
        this.userModel = userModel;
    }

    public void setProductionTitle(String productionTitle) {
        this.productionTitle = productionTitle;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getProductionId() {
        return productionId;
    }

    public void setProductionId(String productionId) {
        this.productionId = productionId;
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

    public void refreshValues() {
        // set channelName for chosen production
        BrowseChannelsViewModel channelsViewModel = viewModelFactory.getBrowseChannelsViewModel();
        viewModelFactory.getBrowseChannelsViewModel().queryParamProperty().setValue("");
        viewModelFactory.getBrowseChannelsViewModel().search();
        for (Channel channel : channelsViewModel.listPropertyProperty()) {
            if (channel.getIdentifier().equals(getChannelId())) {
                Platform.runLater(() -> channelNameTxtField.setText(channel.getName()));
            }
        }

        // Set productionTitle for chosen production
        Platform.runLater(() -> productionTitleTxtField.setText(productionTitle));

        // Clear credits textArea
        Platform.runLater(() -> creditsTxtArea.clear());

        // Set credits for chosen production
        for (Credit cred : listProperty) {
            Platform.runLater(() -> creditsTxtArea.appendText(cred.getPerson().getName() + "\t" + cred.getJob() + "\n"));
        }
    }
}
