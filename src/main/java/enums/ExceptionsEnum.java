package enums;

public enum ExceptionsEnum {
    userNoExist ("User Name not Exist"),
    userRepeated ("Repeated Username."),
    wrongPassword ("Wrong Password"),
    emptyImport("Empty Import");

    private String message;

    private ExceptionsEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
