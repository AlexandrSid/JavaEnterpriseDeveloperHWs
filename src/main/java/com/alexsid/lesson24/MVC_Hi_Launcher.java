package com.alexsid.lesson24;

public class MVC_Hi_Launcher {

    public static void main(String[] args) {
        ModelHi model = new ModelHi();
        model.setName("");
        model.setButtonText("Say Hello");
        model.setPanelHeader("New MVC-shaped application");
        model.setDisplayedMessage("<= Press this button>");

        new ControllerHi(model);
    }
}
