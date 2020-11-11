package com.alexsid.lesson24;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerHi implements MouseListener {
    ModelHi model;
    ViewHi view;

    public ControllerHi(ModelHi model) {
        this.model = model;
        this.view = new ViewHi(
                model.getDisplayedMessage(),
                model.getPanelHeader(),
                model.getButtonText()
        );
        view.getSayButton().addMouseListener(this);//или передаваить this через конструктор
        view.setVisible(true);//возможно правильнее было вынести в отдельный метод
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        view.getDisplayMessage().setText("Hello " + view.getUserInputTextField().getText());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
