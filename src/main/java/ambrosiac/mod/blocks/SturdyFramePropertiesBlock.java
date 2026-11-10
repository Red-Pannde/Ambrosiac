package ambrosiac.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

public class SturdyFramePropertiesBlock extends Block {
    // Intpropertes
    public static final IntProperty DELAY = Properties.DELAY;

    // Booleanproperties
    public static final BooleanProperty LOCKED = Properties.LOCKED;

    public SturdyFramePropertiesBlock(Settings settings) {
        super(settings);
    }
    public void resetProperties() {

    }
}
