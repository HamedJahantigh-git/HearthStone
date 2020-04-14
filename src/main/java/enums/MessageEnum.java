package enums;

public enum MessageEnum {
    emptyImport("Empty Import","<html><center>Please Fill Empty Fields.</center><br><center></center><br><center></center></html>"),
    successSignUp("Success Sign Up", "<html>Your Account has been Successfully Created.</html>"),
    repeatedUsername("Repeated Username", "<html><center>Repeated Username.</center>" +
            "<br><center>Please Enter other Username.</center><br><center></center><br><center></center></html>"),
    userNoExist("User Name not Exist", "<html><center>Username is Wrong.</center><br><center></center><br><center></center></html>"),
    wrongPassword("Wrong Password", "<html><center>Password is Wrong.</center><br><center></center><br><center></center></html>");

    private String title, text;

    private MessageEnum(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
