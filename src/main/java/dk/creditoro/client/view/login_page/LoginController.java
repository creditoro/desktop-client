package dk.creditoro.client.view.login_page;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * The type Login controller.
 */
public class LoginController {
    /**
     * The Txt email.
     */
    @FXML
    public TextField txt_mail;
    /**
     * The Txt password.
     */
    @FXML
    public TextField txt_password;
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
        txt_mail.textProperty().bindBidirectional(vm.emailProperty());
        txt_password.textProperty().bindBidirectional(vm.passwordProperty());
    }

    /**
     * On sign in button.
     */
    public void SignIn() {
        viewModel.signIn(txt_mail.getText(), txt_password.getText());
    }

}
