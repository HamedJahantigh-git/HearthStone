package model;

import enums.InfoPassiveEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class InfoPassive {
    private InfoPassiveEnum type;

    public InfoPassive(InfoPassiveEnum type) {
        this.type = type;
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
}
