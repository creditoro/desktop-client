package dk.creditoro.client.view.frontpage;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.view.IViewController;
import dk.creditoro.client.view.shared_viewmodel_func.SharedViewModelFunc;
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
    ObservableList<String> searchingList = FXCollections.observableArrayList("Produktion", "Kanal");
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;
    @FXML
    private ChoiceBox<String> choiceBox;
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
        this.viewModelFactory = viewModelFactory;
        choiceBox.setValue("Produktion");
        choiceBox.setItems(searchingList);
        openNav = new TranslateTransition(new Duration(350), drawer);
        openNav.setToX(0);
        openBtn = new TranslateTransition(new Duration(350), btnMenu);
        openBtn.setToX(0);
        closeNav = new TranslateTransition(new Duration(350), drawer);
        closeBtn = new TranslateTransition(new Duration(350), btnMenu);
        SharedViewModelFunc sharedViewModelFunc = new SharedViewModelFunc();
        sharedViewModelFunc.setUserEmail(btnlogin,viewModelFactory.getModelFactory().getUserModel());
    }

    /**
     * Login action.
     *
     * @param actionEvent the action event
     */
    public void loginAction(ActionEvent actionEvent) {
        btnlogin.setVisible(false);
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
        viewModelFactory.getBrowseChannelsViewModel().setMail();
    }

    /**
     * Btn productions.
     *
     * @param actionEvent the action event
     */
    public void btnProductions(ActionEvent actionEvent) {
        drawerAction(actionEvent);
        viewHandler.openView(Views.BROWSE_PRODUCTIONS);
        viewModelFactory.getBrowseProductionsViewModel().setMail();
    }

    /**
     * On search action.
     *
     * @param actionEvent the action event
     */
    public void onSearchAction(ActionEvent actionEvent) {
        var view = choiceBox.getSelectionModel().getSelectedItem();
        if (view.equals("Kanal")) {
            viewHandler.openView(Views.BROWSE_CHANNELS, searchTextField.getText());
            viewModelFactory.getBrowseChannelsViewModel().setMail();

        } else {
            viewHandler.openView(Views.BROWSE_PRODUCTIONS, searchTextField.getText());
            viewModelFactory.getBrowseProductionsViewModel().setMail();
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
        searchTextField.requestFocus();
        drawerAction(actionEvent);
    }
}
