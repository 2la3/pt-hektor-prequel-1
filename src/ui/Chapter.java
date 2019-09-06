package ui;

public abstract class Chapter {

    Main menu;

    public Chapter(Main menu) {
        this.menu = menu;
    }

    public abstract void display();
}
