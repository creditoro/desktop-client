package dk.creditoro.client.view.frontpage;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.view.IViewController;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.logging.Logger;

/**
 * The type Frontpage controller.
 */
public class FrontpageController implements IViewController {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ViewHandler viewHandler;
    private FrontpageViewModel frontpageViewModel;

    private TranslateTransition openNav;
    private TranslateTransition closeNav;

    @FXML
    private Button btnMenu;
    @FXML
    private VBox drawer;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button btnlogin;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.frontpageViewModel = viewModelFactory.getFrontpageViewModel();

        searchTextField.textProperty().bindBidirectional(frontpageViewModel.queryParamProperty());


        openNav = new TranslateTransition(new Duration(350), drawer);
        openNav.setToX(0);
        closeNav = new TranslateTransition(new Duration(350), drawer);
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
        frontpageViewModel.search();
    }

    /**
     * Drawer action.
     *
     * @param actionEvent the action event
     */
    public void drawerAction(ActionEvent actionEvent) {
        if (drawer.getTranslateX() != 0) {
            openNav.play();
        } else {
            closeNav.setToX(drawer.getWidth());
            closeNav.play();
        }
    }
}
