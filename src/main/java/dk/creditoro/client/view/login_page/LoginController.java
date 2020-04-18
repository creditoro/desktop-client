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
    public TextField txtEmail;
    /**
     * The Txt password.
     */
    @FXML
    public TextField txtPassword;
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
        txtEmail.textProperty().bindBidirectional(vm.emailProperty());
        txtPassword.textProperty().bindBidirectional(vm.passwordProperty());
    }

    /**
     * On sign in button.
     */
    public void onSignInButton() {
        viewModel.signIn(txtEmail.getText(), txtPassword.getText());
    }
}
