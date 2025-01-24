package com.flechazo;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Monster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * ReTargetato Fabric Mod主类
 * 用于控制生物的目标设定行为
 * 
 * @author Flechazo
 */
public class ReTargetatoFabric implements ModInitializer {
	public static final String MOD_ID = "retargetato";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	private static ReTargetatoFabricConfig config;

	@Override
	public void onInitialize() {
		// 注册配置
		AutoConfig.register(ReTargetatoFabricConfig.class, JanksonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ReTargetatoFabricConfig.class).getConfig();
		
		LOGGER.info("ReTargetato mod has been initialized!");
	}

	/**
	 * 注入setTarget方法的处理逻辑
	 * 
	 * @param target 目标实体
	 * @param ci 回调信息
	 * @param mob 执行setTarget的生物
	 */
	public static void injectSetTarget(LivingEntity target, CallbackInfo ci, net.minecraft.entity.mob.MobEntity mob) {
		// 检查mob和target是否都是Monster
		if (!(mob instanceof Monster) || !(target instanceof Monster)) {
			return;
		}

		String mobType = mob.getType().toString();
		String targetType = target.getType().toString();

		// 检查高级过滤规则
		int mobIndex = config.filteredMobList.indexOf(mobType);
		int targetIndex = config.filteredTargetList.indexOf(targetType);
		if (mobIndex == targetIndex && mobIndex != -1) {
			return;
		}

		// 检查黑/白名单
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