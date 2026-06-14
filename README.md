# 🔑 Session Log-In

A sleek, client-side utility mod for Fabric that lets you re-authenticate, switch accounts, and refresh expired Microsoft sessions directly from the in-game main menu. No more restarting Minecraft just because your login token timed out.

![Minecraft version](https://img.shields.io/badge/Minecraft-1.21.x-blue?style=for-the-badge&logo=minecraft)
![Loader](https://img.shields.io/badge/Loader-Fabric-orange?style=for-the-badge)
![Security](https://img.shields.io/badge/Security-OAuth2-success?style=for-the-badge)

---

## ✨ Features

* **Instant Session Refresh:** Did your game stay open too long and hit an "Invalid Session" error? Fix it in two clicks without closing your client.
* **Seamless Multi-Account Switching:** Easily switch between alt accounts or main profiles directly from a dedicated in-game interface.
* **Secure Microsoft Auth:** Uses official, secure Microsoft OAuth2 login procedures. Your credentials are safe and handled directly through standard secure browser popups—never saved locally in plain text.
* **Clean UI Integration:** Adds a subtle, aesthetic button to your main menu that blends beautifully with the vanilla UI.

---

## 🤔 Why use this?

Anyone who plays Minecraft long-term or runs multiple accounts knows the pain of seeing **"Invalid Session (Try restarting your game)"**. It breaks up your flow, takes forever if you run heavy modpacks, and is just an overall hassle.

**Session Log-In** replaces the need for full client restarts. It clears out your invalid session token and securely requests a fresh one from Microsoft, getting you back into your favorite multiplayer servers instantly.

---

## 🛠️ Requirements

To use this mod, ensure you have the following installed:
* [Fabric Loader](https://fabricmc.net/)
* [Fabric API](https://modrinth.com/mod/fabric-api)

---

## 🔒 Security Notice

This mod strictly complies with Mojang and Microsoft's account guidelines. It hooks into the official Microsoft authentication API via your standard web browser. The mod **never** asks for, sees, or stores your account password. 

---

## 📝 License

This project is licensed under the MIT License - feel free to use it in your custom utility modpacks!
