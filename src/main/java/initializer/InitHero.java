package initializer;

import controller.FileManagement;
import model.hero.*;

public class InitHero {

    public static void saveHeroModels(){
        FileManagement.getInstance().saveHeroModelToFile(new Hunter());
        FileManagement.getInstance().saveHeroModelToFile(new Mage());
        FileManagement.getInstance().saveHeroModelToFile(new Priest());
        FileManagement.getInstance().saveHeroModelToFile(new Warlock());
        FileManagement.getInstance().saveHeroModelToFile(new Rogue());
    }
}
