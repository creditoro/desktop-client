package dk.creditoro.client.view.production;

import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.credit.ICreditModel;
import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.model.user.IUserModel;
import dk.creditoro.client.view.shared_viewmodel_func.SharedViewModelFunc;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * The type Production view model.
 */
public class ProductionViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final ICreditModel creditModel;
    private IUserModel userModel;

    private final StringProperty queryParam = new SimpleStringProperty();
    private final ObservableList<Credit> creditList = FXCollections.observableArrayList();
    private final ListProperty<Credit> listProperty = new SimpleListProperty<>(creditList);
    private final SharedViewModelFunc sharedViewModelFunc = new SharedViewModelFunc();

    private ArrayList<Credit> cachedCredits = new ArrayList<>();
    private Button btnAccount;
    private ImageView btnAddCredit;
    private Boolean whichView;
    private String id;
    private String channelId;
    private StringProperty title = new SimpleStringProperty();
    /**
     * Instantiates a new Production view model.
     *
     * @param creditModel      the credit model
     * @param userModel        the user model
     * @param viewModelFactory the view model factory
     */
    public ProductionViewModel(ICreditModel creditModel, IUserModel userModel, ViewModelFactory viewModelFactory) {
        this.creditModel = creditModel;
        this.userModel = userModel;
        this.creditModel.addListener("kek", (this::onSearchProductionsResult));
    }

    public Boolean getWhichView() {
        return whichView;
    }

    public void setWhichView(Boolean whichView) {
        this.whichView = whichView;
    }

    public void setBtnAccount(Button btnAccount) {
        this.btnAccount = btnAccount;
    }

    public void setBtnAddCredit(ImageView btnAddCredit) {
        this.btnAddCredit = btnAddCredit;
    }

    public void setAcountEmail(){
        if (userModel.getCurrentUser() != null){
            btnAddCredit.setVisible(true);
        }
        sharedViewModelFunc.setUserEmail(btnAccount, userModel);
    }

    /**
     * Query param property string property.
     *
     * @return the string property
     */
    public StringProperty queryParamProperty() {
        return queryParam;
    }

    /**
     * Gets credits.
     */
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

    /**
     * List property property list property.
     *
     * @return the list property
     */
    public ListProperty<Credit> listPropertyProperty() {
        return listProperty;
    }

    /**
     * Search.
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets channel id.
     *
     * @return the channel id
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * Sets channel id.
     *
     * @param id the id
     */
    public void setChannelId(String id) {
        this.channelId = id;
    }

    /**
     * Title property string property.
     *
     * @return the string property
     */
    public StringProperty titleProperty() {
        return title;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title.get();
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title.set(title);
    }
}