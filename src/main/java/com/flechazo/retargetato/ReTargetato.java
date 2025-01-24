package com.flechazo.retargetato;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class ReTargetato implements ModInitializer {
	public static final String MOD_ID = "retargetato";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	private static ReTargetatoConfig config;

	@Override
	public void onInitialize() {
		AutoConfig.register(ReTargetatoConfig.class, JanksonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ReTargetatoConfig.class).getConfig();
	}

	public static void injectSetTarget(LivingEntity target, CallbackInfo ci, MobEntity mob) {
		if (!(mob instanceof Monster) || !(target instanceof Monster)) {
			return;
		}

		String mobType = mob.getType().toString();
		String targetType = target.getType().toString();
		int mobIndex = config.filteredMobList.indexOf(mobType);
		int targetIndex = config.filteredTargetList.indexOf(targetType);
		if (mobIndex == targetIndex && mobIndex != -1) {
			return;
		}
		boolean checker = config.mobList.contains(mobType);
		if (config.listType.equals("B")) {
			if (checker) {
				return;
			}
		} else {
			if (!checker) {
				return;
			}
		}

		ci.cancel();
	}
}