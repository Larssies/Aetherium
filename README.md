# 🛡️ Aetherium AntiCheat

Aetherium is a lightweight, modular AntiCheat system for Minecraft 1.8 servers built on Spigot. It is designed to detect common cheats like speed, reach, and more — while remaining efficient, configurable, and easy to maintain.

---

## 📦 Features

- ✨ Modular check system
- 🔍 Movement & combat detections
- ❌ Per-check violation limits
- 🔐 Permission-based bypass
- 🧠 Clean, object-oriented architecture
- ⚙️ Easy to extend with new checks
- 📂 Organized package structure

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 8
- Maven or Gradle
- Spigot 1.8.8 (or compatible fork like PaperSpigot)

### 📥 Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/YourUsername/AetheriumAntiCheat.git
   ```

2. Open it in your IDE (IntelliJ / Eclipse)

3. Build the plugin using Maven or Gradle

4. Drop the compiled `.jar` into your server's `plugins/` folder

---

## ⚙️ Permissions

| Permission                | Description                                  |
|---------------------------|----------------------------------------------|
| `aetherium.bypass`        | Global bypass for all checks                 |
| `aetherium.bypass.speed`  | Bypass Speed check                           |
| `aetherium.bypass.reach`  | Bypass Reach check                           |

You can assign these permissions to trusted players or staff.

---

## 📁 Project Structure

```
src/main/java/com/larssies/aetherium/
├── checks/                 # All movement & combat checks
├── events/                 # Listeners for player events
├── violations/             # Violation tracking logic
├── utils/                  # Math & helper classes
├── config/                 # Config loading & settings
└── Aetherium.java          # Main plugin entry
```

---

## 🛠️ Development

Want to add your own check? Follow this simple pattern:

1. Create a new class under `checks.movement` or `checks.combat`
2. Implement `handle(event, CheckType.YOUR_CHECK)`
3. Add it to the `ChecksManager`
4. Register the event in your main listener

---

## 🧹 Future Plans

- [ ] Configurable violation thresholds (via `config.yml`)
- [ ] Staff alert system (`/alerts`)
- [ ] GUI dashboard (`/ac gui`)
- [ ] Log all detections to a file
- [ ] Add more checks (KillAura, AutoClicker, NoFall, etc.)

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

## 📄 License

MIT License — open source and free to use.

---

> Built with ☕ and love by [@Larssies](https://github.com/Larssies)

