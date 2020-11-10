package com.alexsid.lesson24;


import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
/**
 * Продемонстрировать применение основных паттернов, описанных в лекции.
 *
 * Представлена маленькая программа, которая говорит привет пользователю, чье имя введено.
 * 1 java файл. Для начала скопируйте себе и запустите.
 *
 * Нужно переделать Hi.java файл в файл, соответствующий модели MVC .
 * Т е появится файл ViewHi.java, который контролирует только визуальные элементы,
 * ModelHi.java, который содержит данные и методы доступа к ним,
 * и ControllerHi.java - ответственен за логику, которая связывает первые два.
 *
 * Важно понять:
 * - какой код ответственен за взаимодействие
 * - какой код отвечает за данные
 * - как может выглядеть “клей”, склеивающий взаимодействие и данные
 * - переделать немного Hi.java, чтоб все это запускалось
 */

class Hi extends JFrame implements MouseListener {

    // components

    protected JLabel helloLabel = new JLabel("Hello");

    protected JTextField userInputTextField = new JTextField(20);

    private JButton sayHiBtn = new JButton("Say Hi");

    /**
     * Constructor
     */

    Hi() {

        //... Layout the components.

        JPanel content = new JPanel();

        content.setLayout(new FlowLayout());

        content.add(new JLabel("Enter your name"));

        content.add(userInputTextField);

        content.add(sayHiBtn);

        content.add(helloLabel);

        // Add a mouse listener to the button

        sayHiBtn.addMouseListener(this);

        //... finalize layout

        this.setContentPane(content);

        this.pack();

        this.setTitle("Simple App - Not MVC");

        // The window closing event should probably be passed to the

        // Controller in a real program, but this is a short example.

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // Methods I am forced to implement because of the MouseListener

    public void mouseClicked(MouseEvent e) {
        helloLabel.setText("Hello " + userInputTextField.getText());

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public static void main(String[] args) {

        Hi s = new Hi();

        s.setVisible(true);

    }

}

