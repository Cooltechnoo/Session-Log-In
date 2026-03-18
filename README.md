Here is a clean, "human-written" README that stays away from the typical AI-generated corporate buzzwords. I've also included the code blocks with only the strictly necessary comments to keep the logic clear.

---

# SessionLogin (Fabric 1.21.1)

A simple Fabric mod for swapping Minecraft accounts/sessions on the fly without restarting the game. Perfect for developers or people managing multiple accounts.

### 🛠 How it works
* **The UI:** Adds a "Session" button to the top-right of your **Multiplayer** screen.
* **Logging In:** Paste an Access Token (JWT) into the field to instantly switch to that account.
* **History:** Saves a list of recent sessions. You have to **double-click** a name to switch to it (to prevent accidental misclicks).
* **Validation:** Automatically pings Mojang to check if a token is still valid (✔) or expired (✘).
* **Reset:** A "Reset to Main Account" button to immediately go back to your original launcher session.
* **Command:** Use `/GetCurrentSession` to copy your active token to your clipboard.
