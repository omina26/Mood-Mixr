package view;


import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class LoginView  extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";

    public final LoginViewModel loginViewModel;

    final JButton logIn;

    private final LoginController loginController;
    public LoginView(LoginViewModel loginViewModel, LoginController loginController){
        this.loginController = loginController;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);

        logIn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();
                            try {
                                loginController.executeUseCase();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            } catch (URISyntaxException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }

                }


        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click" + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
    }
}
