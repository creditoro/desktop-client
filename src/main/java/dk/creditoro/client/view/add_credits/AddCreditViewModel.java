package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.credit.ICreditModel;
import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.model.user.IUserModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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


    public AddCreditViewModel(ICreditModel creditModel, IUserModel userModel, ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.creditModel = creditModel;
        this.userModel = userModel;
    }

    public void setChannelId (String channelId){
        this.channelId = channelId;
    }

    public void setProductionId (String productionId){
        this.productionId = productionId;
    }

    public String getChannelId(){
        return channelId;
    }

    public String getProductionId() {
        return productionId;
    }
}
