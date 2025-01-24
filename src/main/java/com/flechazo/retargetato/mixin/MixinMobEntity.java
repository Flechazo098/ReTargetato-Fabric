package com.flechazo.retargetato.mixin;

import com.flechazo.retargetato.ReTargetato;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(MobEntity.class)
public abstract class MixinMobEntity {
	@Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
	private void injectSetTarget(LivingEntity target, CallbackInfo ci) {
		ReTargetato.injectSetTarget(target, ci, (MobEntity)(Object)this);
	}
}