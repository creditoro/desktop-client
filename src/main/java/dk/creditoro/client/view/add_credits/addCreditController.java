package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.view.IViewController;
import dk.creditoro.client.view.production.ProductionViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.apache.xmlgraphics.java2d.ps.AbstractPSDocumentGraphics2D;

import java.util.logging.Logger;

public class addCreditController implements IViewController {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ViewHandler viewHandler;

    @FXML
    private Label channelLabel;
    @FXML
    private Label productionLabel;
    @FXML
    private Label lblStartMenu;
    @FXML
    private TextField channelNameTxtField;
    @FXML
    private TextField productionTitleTxtField;
    @FXML
    private TextField nameTxtField;
    @FXML
    private TextField jobTxtField;
    @FXML
    private TextField emailTxtField;
    @FXML
    private TextArea creditsTxtArea;
    @FXML
    private Button addBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button btnAccount;
    @FXML
    private Button Productions;
    @FXML
    private Button Channels;
    @FXML
    private Button Search;


    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;

        btnAccount.setText("user.getEmail();");
        channelNameTxtField.setText("channel.getName();");
        productionTitleTxtField.setText("production.getTitle();");
    }

    public void handleSearchBar(ActionEvent actionEvent) {
        viewHandler.openView(Views.FRONTPAGE);
    }

    public void btnChannels(ActionEvent actionEvent) {
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }

    public void btnProductions(ActionEvent actionEvent) {
        viewHandler.openView(Views.BROWSE_PRODUCTIONS);
    }

    public void lblStartMenuPressed(MouseEvent mouseEvent) {
        viewHandler.openView(Views.FRONTPAGE);
    }

    public void btnAccount(ActionEvent actionEvent) {
        LOGGER.info("Account button pressed.");
    }

    public void addCreditOnAction(ActionEvent actionEvent) {
        String name = nameTxtField.getText();
        String email = emailTxtField.getText();
        String job = jobTxtField.getText();

        creditsTxtArea.appendText(name + "\t" + job + "\n");

        nameTxtField.clear();
        emailTxtField.clear();
        jobTxtField.clear();
        nameTxtField.requestFocus();
        LOGGER.info("Credit added");
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        LOGGER.info("Credit deleted");
    }

    public void exitOnAction(ActionEvent actionEvent) {
        LOGGER.info("Exit pressed - Changing view to production");
    }
}
