package io.tntra.bankdemoapi.Enum.pkg;

public enum AccountType {
    SAVINGS("SAVINGS"),
    CURRENT("CURRENT"),
    FD("FD");

    final String value;

    AccountType(String value) {
        this.value = value;

    }

    public String getValue() {
        return this.value;
    }

    public static AccountType create(String value){
        AccountType[] accountTypes =AccountType.values();
        for(AccountType type:accountTypes){
            if(type.getValue().equalsIgnoreCase(value)){
                return  type;
            }

        }
        return SAVINGS;
    }


}
