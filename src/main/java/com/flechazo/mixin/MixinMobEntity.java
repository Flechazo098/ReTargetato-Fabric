package com.flechazo.mixin;

import com.flechazo.ReTargetatoFabric;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 用于注入生物setTarget方法的Mixin类
 *
 * @author Flechazo
 */
@Mixin(MobEntity.class)
public abstract class MixinMobEntity {

	/**
	 * 在setTarget方法开始处注入代码
	 * 用于控制生物是否可以将某个实体设为目标
	 *
	 * @param target 目标实体
	 * @param ci 回调信息
	 */
	@Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
	private void injectSetTarget(LivingEntity target, CallbackInfo ci) {
		ReTargetatoFabric.injectSetTarget(target, ci, (MobEntity)(Object)this);
	}
}