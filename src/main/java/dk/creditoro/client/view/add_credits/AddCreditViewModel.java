package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.credit.ICreditModel;
import dk.creditoro.client.model.user.IUserModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddCreditViewModel {
    public AddCreditViewModel(ICreditModel creditModel, IUserModel userModel, ViewModelFactory viewModelFactory) {
    }

    private StringProperty productionTitle = new SimpleStringProperty();

    public void setProductionTitle(String productionTitle) {
        this.productionTitle.set(productionTitle);
    }

    public String getProductionTitle() {
        return productionTitle.get();
    }

    public StringProperty productionTitleProperty() {
        return productionTitle;
    }
}
