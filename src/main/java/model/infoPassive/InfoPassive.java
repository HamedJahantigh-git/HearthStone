package model.infoPassive;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import controller.game.GameController;
import enums.InfoPassiveEnum;
import model.Player;
import model.hero.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FreePower.class, name = "FreePower"),
        @JsonSubTypes.Type(value = ManaJump.class, name = "ManaJump"),
        @JsonSubTypes.Type(value = Nurse.class, name = "Nurse"),
        @JsonSubTypes.Type(value = OffCards.class, name = "OffCards"),
        @JsonSubTypes.Type(value = TwiceDraw.class, name = "TwiceDraw"),
})
abstract public class InfoPassive {
    protected InfoPassiveEnum type;

    protected InfoPassive (InfoPassiveEnum infoPassiveType){
        this.type = infoPassiveType;
    }

    public InfoPassiveEnum getType() {
        return type;
    }

    abstract public void applyInfo(GameController gameController, int playerIndex);

}
