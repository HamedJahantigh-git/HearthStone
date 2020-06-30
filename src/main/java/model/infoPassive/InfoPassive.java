package model.infoPassive;

import enums.InfoPassiveEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

abstract public class InfoPassive {
    protected InfoPassiveEnum type;

    protected InfoPassive (InfoPassiveEnum infoPassiveType){
        this.type = infoPassiveType;
    }

    public static ArrayList<InfoPassiveEnum> creatRandomInfoPassive(int number) {
        Random random = new Random();
        int randomNumber;
        ArrayList<InfoPassiveEnum> result = new ArrayList<>(Arrays.asList(InfoPassiveEnum.values()));
        number = result.size() - number;
        for (int i = 0; i < number; i++) {
            randomNumber = random.nextInt(result.size());
            result.remove(randomNumber);
        }
        return result;
    }

    abstract public void applyInfo();

}
