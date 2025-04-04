package mirror.normalasm.core;

import mirror.normalasm.NormalLogger;
import mirror.normalasm.config.NormalConfig;
import net.minecraftforge.fml.common.Loader;
import twelvefold.twelvefoldbooter.api.LateMixinLoader;

@LateMixinLoader(value = {"mixins.bakedquadsquasher.json",
        "mixins.modfixes_immersiveengineering.json",
        "mixins.modfixes_astralsorcery.json",
        "mixins.capability_astralsorcery.json",
        "mixins.modfixes_evilcraftcompat.json",
        "mixins.modfixes_ebwizardry.json",
        "mixins.modfixes_xu2.json",
        "mixins.modfixes_b3m.json",
        "mixins.searchtree_mod.json",
        "mixins.modfixes_railcraft.json",
        "mixins.modfixes_disable_broken_particles.json"},shouldMixinConfigQueue = "shouldMixinConfigQueueLate")
public class NormalLateLoader {
    public static boolean shouldMixinConfigQueueLate(String mixinConfig) {
        switch (mixinConfig) {
            case "mixins.bakedquadsquasher.json":
                return NormalTransformer.squashBakedQuads;
            case "mixins.modfixes_immersiveengineering.json":
                return NormalConfig.instance.fixBlockIEBaseArrayIndexOutOfBoundsException && Loader.isModLoaded("immersiveengineering");
            case "mixins.modfixes_evilcraftcompat.json":
                return NormalConfig.instance.repairEvilCraftEIOCompat && Loader.isModLoaded("evilcraftcompat") && Loader.isModLoaded("enderio") &&
                        Loader.instance().getIndexedModList().get("enderio").getVersion().equals("5.3.70"); // Only apply on newer EIO versions where compat was broken
            case "mixins.modfixes_ebwizardry.json":
                return NormalConfig.instance.optimizeArcaneLockRendering && Loader.isModLoaded("ebwizardry");
            case "mixins.modfixes_xu2.json":
                return (NormalConfig.instance.fixXU2CrafterCrash || NormalConfig.instance.disableXU2CrafterRendering) && Loader.isModLoaded("extrautils2");
            case "mixins.searchtree_mod.json":
                return NormalConfig.instance.replaceSearchTreeWithJEISearching && Loader.isModLoaded("jei");
            case "mixins.modfixes_astralsorcery.json":
                return NormalConfig.instance.optimizeAmuletRelatedFunctions && Loader.isModLoaded("astralsorcery");
            case "mixins.capability_astralsorcery.json":
                return NormalConfig.instance.fixAmuletHolderCapability && Loader.isModLoaded("astralsorcery");
            case "mixins.modfixes_b3m.json":
                return NormalConfig.instance.resourceLocationCanonicalization && Loader.isModLoaded("B3M"); // Stupid
            case "mixins.modfixes_railcraft.json":
                return NormalConfig.instance.efficientHashing && Loader.isModLoaded("railcraft");
            case "mixins.modfixes_disable_broken_particles.json":
                return NormalConfig.instance.disableBrokenParticles;
        }
        return false;
    }
}
