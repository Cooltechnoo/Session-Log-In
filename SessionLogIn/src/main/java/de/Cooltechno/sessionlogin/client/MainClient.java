package de.Cooltechno.sessionlogin.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class MainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("GetCurrentSession")
                    .executes(context -> {
                        MinecraftClient client = MinecraftClient.getInstance();

                        if (client.getSession() != null) {
                            String token = client.getSession().getAccessToken();

                            client.keyboard.setClipboard(token);

                            context.getSource().sendFeedback(Text.literal("§a[SessionLogin] Token copied to clipboard!"));
                        } else {
                            context.getSource().sendError(Text.literal("§cSession not found!"));
                        }

                        return 1;
                    }));
        });
    }
}