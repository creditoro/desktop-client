package dk.creditoro.client.view.production;

import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.credit.ICreditModel;
import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.model.user.IUserModel;
import dk.creditoro.client.view.browse_channels.BrowseChannelsViewModel;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class ProductionViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final ICreditModel creditModel;
    private final IUserModel userModel;
    private final ViewModelFactory viewModelFactory;

    private final StringProperty queryParam = new SimpleStringProperty();
    private final ObservableList<Credit> creditList = FXCollections.observableArrayList();
    private final ListProperty<Credit> listProperty = new SimpleListProperty<>(creditList);

    private final ObservableList<Production> productionList = FXCollections.observableArrayList();
    private final ListProperty<Production> productionListProperty = new SimpleListProperty<>(productionList);

    private final ArrayList<Credit> cachedCredits = new ArrayList<>();

    private String id;
    private String channelId;
    private ImageView channelLogo;
    private Label titleLabel;
    private Label channelName;

    public ProductionViewModel(ICreditModel creditModel, IUserModel userModel, ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.creditModel = creditModel;
        this.userModel = userModel;
        this.creditModel.addListener("kek", (this::onSearchProductionsResult));
    }

    public StringProperty queryParamProperty() {
        return queryParam;
    }

    public void getCredits() {
        var q = queryParam.get();
        var message = String.format("Called search, q: '%s'", q);
        LOGGER.info(message);
        creditModel.getCredits(id);
    }

    private void onSearchProductionsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search credit result called.");
        var credits = (Credit[]) propertyChangeEvent.getNewValue();
        listProperty.clear();
        listProperty.addAll(credits);

        cachedCredits.addAll(Arrays.asList(credits));
    }

    public ListProperty<Credit> listPropertyProperty() {
        return listProperty;
    }

    public ListProperty<Production> productionListPropertyProperty() {
        return productionListProperty;
    }


    public void search() {
        listProperty.clear();

        for (Credit c : cachedCredits) {
            String job = c.getJob().toLowerCase();
            String name = c.getPerson().getName().toLowerCase();
            String q = queryParam.getValue().toLowerCase();

            if (job.contains(q) || name.contains(q)) {
                listProperty.add(c);
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String id) {
        this.channelId = id;
    }

    public ImageView getChannelLogo() {
        return channelLogo;
    }

    public void setChannelLogo(ImageView channelLogo) {
        this.channelLogo = channelLogo;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(Label titleLabel) {
        this.titleLabel = titleLabel;
    }

    public Label getChannelName() {
        return channelName;
    }

    public void setChannelName(Label channelName) {
        this.channelName = channelName;
    }

    public void setProductionListProperty(ObservableList<Production> productionListProperty) {
        this.productionListProperty.set(productionListProperty);
    }

    private void setProductionTitle() {
        for (Production prod : productionListProperty) {
            if (getId().equals(prod.getIdentifier())) {
                titleLabel.setText(prod.getTitle());
            }
        }
    }

    public void refreshValues() {
        BrowseChannelsViewModel channelsViewModel = viewModelFactory.getBrowseChannelsViewModel();
        viewModelFactory.getBrowseChannelsViewModel().queryParamProperty().setValue("");
        viewModelFactory.getBrowseChannelsViewModel().search();
        for (Channel channel : channelsViewModel.listPropertyProperty()) {
            if (channel.getIdentifier().equals(getChannelId())) {
                Platform.runLater(() -> {
                    channelLogo.setImage(new Image(channel.getIconUrl()));
                    channelName.setText(channel.getName());
                });
            }
        }

        Platform.runLater(this::setProductionTitle);
    }
}
