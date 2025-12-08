package ambrosiac.mod;

import ambrosiac.mod.datagen.AmbrosiacBiomeTagGen;
import ambrosiac.mod.datagen.AmbrosiacRecipeProvider;
import ambrosiac.mod.datagen.AmbrosiacBlockTagGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AmbrosiacDataGenerator implements DataGeneratorEntrypoint {


	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(AmbrosiacRecipeProvider::new);
		pack.addProvider(AmbrosiacBlockTagGen::new);
		pack.addProvider(AmbrosiacBiomeTagGen::new);
	}
}
