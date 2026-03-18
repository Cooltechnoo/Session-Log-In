package de.Cooltechno.sessionlogin.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import java.util.ArrayList;
import java.util.List;

public class SessionInputGui extends Screen {
    private final Screen parent;
    private TextFieldWidget tokenField;

    public SessionInputGui(Screen parent) {
        super(Text.literal("Session Manager"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int x = this.width / 2 - 100;

        this.tokenField = new TextFieldWidget(textRenderer, x, 40, 200, 20, Text.literal("Token"));
        this.tokenField.setMaxLength(2048);
        this.addDrawableChild(tokenField);

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Login with Token"), b -> {
            if (!tokenField.getText().isEmpty()) {
                SetSession.loginWithToken(tokenField.getText().trim());
                this.client.setScreen(parent);
            }
        }).dimensions(x, 65, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Reset to Main Account"), b -> {
            SetSession.useOriginal = true;
            this.client.setScreen(parent);
        }).dimensions(x, 90, 200, 20).build());

        int yOffset = 135;
        List<SetSession.SessionData> sessions = new ArrayList<>(SetSession.history.values());

        for (int i = sessions.size() - 1; i >= 0; i--) {
            final SetSession.SessionData data = sessions.get(i);

            String status = data.isValid ? "§a✔ " : "§c✘ ";

            this.addDrawableChild(ButtonWidget.builder(Text.literal(status + data.name), b -> {
                long currentTime = Util.getMeasuringTimeMs();
                if (currentTime - data.lastClickTime < 250) {
                    SetSession.applySession(data.name, data.uuid, data.token);
                    this.client.setScreen(parent);
                } else {
                    data.lastClickTime = currentTime;
                    b.setMessage(Text.literal("§eDouble-Click to Join"));
                }
            }).dimensions(x, yOffset, 200, 20).build());

            yOffset += 25;
            if (yOffset > this.height - 40) break;
        }

        if (!sessions.isEmpty()) {
            this.addDrawableChild(ButtonWidget.builder(Text.literal("§cClear History"), b -> {
                SetSession.history.clear();
                this.clearAndInit();
            }).dimensions(x + 50, yOffset + 5, 100, 20).build());
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderInGameBackground(context);
        context.drawCenteredTextWithShadow(textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);

        if (!SetSession.history.isEmpty()) {
            context.drawCenteredTextWithShadow(textRenderer, "Recent Sessions:", this.width / 2, 122, 0xFFAA00);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}