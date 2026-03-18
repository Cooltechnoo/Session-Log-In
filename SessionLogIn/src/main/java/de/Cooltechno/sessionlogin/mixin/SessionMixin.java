package de.Cooltechno.sessionlogin.mixin;

import net.minecraft.client.session.Session;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import de.Cooltechno.sessionlogin.client.SetSession;

import java.util.UUID;

@Mixin(Session.class)
public class SessionMixin {

    @Inject(at = @At("HEAD"), method = "getAccessToken", cancellable = true)
    private void getAccessToken(CallbackInfoReturnable<String> cir) {
        if (!SetSession.useOriginal) {
            cir.setReturnValue(SetSession.accessToken);
        }
    }

    @Inject(at = @At("HEAD"), method = "getUsername", cancellable = true)
    private void getUsername(CallbackInfoReturnable<String> cir) {
        if (!SetSession.useOriginal) {
            cir.setReturnValue(SetSession.username);
        }
    }

    @Inject(at = @At("HEAD"), method = "getUuidOrNull", cancellable = true)
    private void getUuidOrNull(CallbackInfoReturnable<UUID> cir) {
        if (!SetSession.useOriginal) {
            cir.setReturnValue(SetSession.getUuid());
        }
    }
}