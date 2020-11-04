package sample.Controllers;

public class ConnectControllers {
    public static MenuController menuController;
    public static NewStudentsController newStudentsController;
    public static EditStudentsController editStudentsController;
    public static MenuController getmenuController() {
        return menuController;
    }

    public static void setmenuController(MenuController menuController) {
        ConnectControllers.menuController = menuController;
    }

    public static NewStudentsController getnewStudentsController() {

        return newStudentsController;
    }

    public static void setnewStudentsController(NewStudentsController newStudentsController) {
        ConnectControllers.newStudentsController = newStudentsController;
    }
    public static EditStudentsController geteditStudentsController() {

        return editStudentsController;
    }

    public static void seteditStudentsController(EditStudentsController editStudentsController) {
        ConnectControllers.editStudentsController = editStudentsController;
    }
}
