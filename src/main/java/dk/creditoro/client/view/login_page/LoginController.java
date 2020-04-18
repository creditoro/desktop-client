package dk.creditoro.client.view.login_page;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * The type Login controller.
 */
public class LoginController {
    /**
     * The Txt email.
     */
    @FXML
    public TextField txtmail;
    /**
     * The Txt password.
     */
    @FXML
    public TextField txtpassword;
    /**
     * The Btn sign in.
     */
    @FXML
    public Button btnSignIn;

    private LoginViewModel viewModel;

    /**
     * Init.
     *
     * @param vm the vm
     */
    public void init(LoginViewModel vm) {
        viewModel = vm;
        txtmail.textProperty().bindBidirectional(vm.emailProperty());
        txtpassword.textProperty().bindBidirectional(vm.passwordProperty());
    }

    /**
     * On sign in button.
     */
    public void signIn() {
        viewModel.signIn(txtmail.getText(), txtpassword.getText());
    }

}
