package com.alexsid.lesson24;

import lombok.Getter;

import javax.swing.*;

@Getter
public class ViewHi extends JFrame{
    private JPanel panel;
    private JTextField userInputTextField;
    private JButton sayButton;
    private JLabel displayMessage;

    public ViewHi(String displayMessage, String panelHeader, String buttonText) {
        this.panel = new JPanel();
        this.userInputTextField = new JTextField(20);
        this.sayButton = new JButton(buttonText);
        this.displayMessage = new JLabel(displayMessage);

        panel.add(new JLabel("Enter your name:"));//в самом конце вспомнил((

        panel.add(userInputTextField);

        panel.add(sayButton);

        panel.add(this.displayMessage);

        // Add a mouse listener to the button

//        sayButton.addMouseListener(new ControllerHi());//TODO move to controller

        this.setContentPane(panel);

        this.pack();

        this.setTitle(panelHeader);

        // The window closing event should probably be passed to the

        // Controller in a real program, but this is a short example.

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
