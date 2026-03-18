package de.Cooltechno.sessionlogin.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import de.Cooltechno.sessionlogin.client.SetSession;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Mixin(targets = "net.minecraft.client.session.ProfileKeysImpl")
public class ProfileKeysMixin {

    @Inject(method = "fetchKeyPair", at = @At("HEAD"), cancellable = true, remap = true)
    private void onFetch(CallbackInfoReturnable<CompletableFuture<?>> cir) {
        // Stop the client from trying to sign chat with the wrong account keys
        if (!SetSession.useOriginal) {
            cir.setReturnValue(CompletableFuture.completedFuture(Optional.empty()));
        }
    }
}