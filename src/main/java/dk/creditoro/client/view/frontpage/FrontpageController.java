package dk.creditoro.client.view.frontpage;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.view.IViewController;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.logging.Logger;

/**
 * The type Frontpage controller.
 */
public class FrontpageController implements IViewController {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ViewHandler viewHandler;

    @FXML
    private ChoiceBox<String> choiceBox;

    ObservableList<String> searchingList = FXCollections.observableArrayList("Produktion", "Kanal");


    private TranslateTransition openNav;
    private TranslateTransition openBtn;
    private TranslateTransition closeNav;
    private TranslateTransition closeBtn;

    @FXML
    private Button btnMenu;
    @FXML
    private AnchorPane drawer;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button btnlogin;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        choiceBox.setValue("Produktion");
        choiceBox.setItems(searchingList);
        openNav = new TranslateTransition(new Duration(350), drawer);
        openNav.setToX(0);
        openBtn = new TranslateTransition(new Duration(350), btnMenu);
        openBtn.setToX(0);
        closeNav = new TranslateTransition(new Duration(350), drawer);
        closeBtn = new TranslateTransition(new Duration(350), btnMenu);
    }

    /**
     * Login action.
     *
     * @param actionEvent the action event
     */
    public void loginAction(ActionEvent actionEvent) {
        viewHandler.openView(Views.LOGIN);
    }

    /**
     * Handle search bar.
     *
     * @param actionEvent the action event
     */
    public void handleSearchBar(ActionEvent actionEvent) {
        drawerAction(actionEvent);
        LOGGER.info("search pressed");
    }

    /**
     * Btn channels.
     *
     * @param actionEvent the action event
     */
    public void btnChannels(ActionEvent actionEvent) {
        drawerAction(actionEvent);
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }

    /**
     * Btn productions.
     *
     * @param actionEvent the action event
     */
    public void btnProductions(ActionEvent actionEvent) {
        drawerAction(actionEvent);
        viewHandler.openView(Views.BROWSE_PRODUCTIONS);
    }

    /**
     * On search action.
     *
     * @param actionEvent the action event
     */
    public void onSearchAction(ActionEvent actionEvent) {
        LOGGER.info("Hvad f skal der ske med den her søgefunktion");
        var view = choiceBox.getSelectionModel().getSelectedItem();
        if (view.equals("Kanal")) {
            viewHandler.openView(Views.BROWSE_CHANNELS, searchTextField.getText());

        } else {
            viewHandler.openView(Views.BROWSE_PRODUCTIONS, searchTextField.getText());
        }
        searchTextField.clear();
    }


    /**
     * Drawer action.
     *
     * @param actionEvent the action event
     */
    public void drawerAction(ActionEvent actionEvent) {
        if (drawer.getTranslateX() != 0) {
            openNav.play();
            openBtn.play();
        } else {
            closeNav.setToX(drawer.getWidth());
            closeBtn.setToX(drawer.getWidth());
            closeNav.play();
            closeBtn.play();
        }
    }

    @FXML
    public void btnSearch(ActionEvent actionEvent) {
        viewHandler.openView(Views.FRONTPAGE);
    }
}
